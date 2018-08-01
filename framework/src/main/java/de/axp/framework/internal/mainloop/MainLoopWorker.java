package de.axp.framework.internal.mainloop;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static de.axp.framework.internal.mainloop.MainLoop.MainLoopListener;

class MainLoopWorker {

	private static final MainLoopPackage POISON = new MainLoopPackage(null, null, null);

	private final WorkerBuffer buffer = new WorkerBuffer();
	private final Collection<MainLoopListener> listeners = Collections.synchronizedList(new LinkedList<>());
	private final List<MainLoopListener> brokenListeners = new ArrayList<>();

	private Thread thread;
	private MainLoopPackage currentThreadPackage;

	void addListener(MainLoopListener loopListener) {
		listeners.add(loopListener);
	}

	MainLoop.MainLoopAccessor getAccessor() {
		return aPackage -> {
			try {
				buffer.put(aPackage);
			} catch (InterruptedException e) {
				throw new MainLoopBufferException(e);
			}
		};
	}

	void startWorking() {
		thread = new Thread(this::doWork);
		thread.start();
	}

	void stopWorking() throws InterruptedException {
		buffer.put(POISON);
		thread.join();
	}

	private void doWork() {
		while (currentThreadPackage != POISON) {
			try {
				currentThreadPackage = buffer.waitAndGet();
			} catch (InterruptedException e) {
				currentThreadPackage = POISON;
				logException(e);
			} finally {
				if (currentThreadPackage != POISON) {
					handlePackage();
				}
			}
		}
	}

	private void handlePackage() {
		for (MainLoopListener listener : listeners) {
			try {
				listener.notify(currentThreadPackage);
			} catch (Throwable t) {
				logException(t);
				System.err.println(listener + " will be removed!");
				brokenListeners.add(listener);
			}
		}
		listeners.removeAll(brokenListeners);
		brokenListeners.clear();
	}

	private void logException(Throwable t) {
		t.printStackTrace();
	}

	private class WorkerBuffer {

		private final BlockingQueue<MainLoopPackage> loopPackages = new ArrayBlockingQueue<>(10);

		void put(MainLoopPackage aPackage) throws InterruptedException {
			loopPackages.put(aPackage);
		}

		MainLoopPackage waitAndGet() throws InterruptedException {
			return loopPackages.take();
		}
	}
}
