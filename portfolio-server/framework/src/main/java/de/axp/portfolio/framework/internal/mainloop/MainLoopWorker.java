package de.axp.portfolio.framework.internal.mainloop;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static de.axp.portfolio.framework.internal.mainloop.MainLoop.MainLoopListener;

class MainLoopWorker {

	private static final MainLoopPackage POISON = new MainLoopPackage(null, MainLoopPackage.STATE.Poisoned);

	private final WorkerBuffer buffer = new WorkerBuffer();
	private final Collection<MainLoopListener> listeners = Collections.synchronizedList(new LinkedList<>());

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
				handleException(e);
			} finally {
				for (MainLoopListener listener : listeners) {
					listener.notify(currentThreadPackage);
				}
			}
		}
	}

	private void handleException(InterruptedException e) {
		throw new MainLoopBufferException(e);
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
