package de.axp.framework.internal.infrastructure.mainloop;

class MainLoopImpl implements MainLoop {

	private MainLoopWorker inputWorker;
	private MainLoopWorker outputWorker;

	MainLoopImpl() {
		inputWorker = new MainLoopWorker();
		inputWorker.startWorking();
		outputWorker = new MainLoopWorker();
		outputWorker.startWorking();
	}

	@Override
	public void dispose() {
		try {
			inputWorker.stopWorking();
			outputWorker.stopWorking();
		} catch (InterruptedException e) {
			throw new MainLoopBufferException(e);
		}
	}

	@Override
	public void addListeners(MainLoopListener inputListener, MainLoopListener outputListener) {
		inputWorker.addListener(inputListener);
		outputWorker.addListener(outputListener);
	}

	@Override
	public void addInput(MainLoopPackage request) {
		inputWorker.getAccessor().put(request);
	}

	@Override
	public void addOutput(MainLoopPackage response) {
		outputWorker.getAccessor().put(response);
	}
}
