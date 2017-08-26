package de.axp.portfolio.framework.internal.mainloop;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static de.axp.portfolio.framework.internal.mainloop.MainLoop.MainLoopListener;

class MainLoopWorker {

	private static final MainLoopPackage POISON = new MainLoopPackage(null);

	private final WorkerBuffer buffer = new WorkerBuffer();
	private final Collection<MainLoopListener> listeners = new LinkedList<>();
	private final Collection<MainLoopListener> listenersWaitingForAttachment = new LinkedList<>();

	private Thread thread;
	private boolean isPoisoned = false;

	void startWorking() {
		thread = new Thread(this::doWork);
		thread.start();
	}

	void addListener(MainLoopListener loopListener) {
		listenersWaitingForAttachment.add(loopListener);
	}

	MainLoop.MainLoopAccessor getAccessor() {
		return buffer::put;
	}

	void stopWorking() throws InterruptedException {
		buffer.put(POISON);
		thread.join();
	}

	private void doWork() {
		while (!isPoisoned) {
			updateListeners();
			handleNextPacketOrWait();
		}
	}

	private void updateListeners() {
		if (!listenersWaitingForAttachment.isEmpty()) {
			listeners.addAll(listenersWaitingForAttachment);
			listenersWaitingForAttachment.clear();
		}
	}

	private void handleNextPacketOrWait() {
		try {
			if (listeners.size() == 0) {
				Thread.sleep(50);
				return;
			}

			MainLoopPackage aPackage = buffer.waitAndGet();
			if (aPackage == POISON) {
				isPoisoned = true;
			} else {
				notifyListeners(aPackage);
			}
		} catch (InterruptedException e) {
			isPoisoned = true;
		}
	}

	private void notifyListeners(MainLoopPackage packet) {
		for (MainLoopListener listener : listeners) {
			listener.notify(packet);
		}
	}

	private class WorkerBuffer {

		private final BlockingQueue<MainLoopPackage> packets = new ArrayBlockingQueue<>(10);

		void put(MainLoopPackage packet) throws InterruptedException {
			packets.put(packet);
		}

		MainLoopPackage waitAndGet() throws InterruptedException {
			return packets.take();
		}
	}
}
