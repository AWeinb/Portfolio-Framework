package de.axp.framework.internal.infrastructure.mainloop;

public class MainLoop {

	private MainLoopWorker inputWorker;
	private MainLoopWorker outputWorker;

	public MainLoop() {
		inputWorker = new MainLoopWorker();
		inputWorker.startWorking();
		outputWorker = new MainLoopWorker();
		outputWorker.startWorking();
	}

	public void dispose() {
		try {
			inputWorker.stopWorking();
			outputWorker.stopWorking();
		} catch (InterruptedException e) {
			throw new MainLoopBufferException(e);
		}
	}

	public void addListeners(MainLoopListener inputListener, MainLoopListener outputListener) {
		inputWorker.addListener(inputListener);
		outputWorker.addListener(outputListener);
	}

	public void addInput(MainLoopPackage request) {
		inputWorker.getAccessor().put(request);
	}

	public void addOutput(MainLoopPackage response) {
		outputWorker.getAccessor().put(response);
	}

	interface MainLoopAccessor {

		void put(MainLoopPackage aPackage);
	}
}
