package de.axp.framework.internal.infrastructure.mainloop;

import java.util.ArrayList;

class MainLoopImpl implements MainLoop {

	private final ArrayList<MainLoopPlugin> mainLoopPlugins = new ArrayList<>();
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
		for (MainLoopPlugin mainLoopPlugin : mainLoopPlugins) {
			mainLoopPlugin.dispose();
		}

		try {
			inputWorker.stopWorking();
			outputWorker.stopWorking();
		} catch (InterruptedException e) {
			throw new MainLoopBufferException(e);
		}
	}

	@Override
	public void addPlugin(MainLoopPlugin plugin) {
		plugin.initialize(inputWorker.getAccessor(), outputWorker.getAccessor());
		inputWorker.addListener(plugin.getInputListener());
		outputWorker.addListener(plugin.getOutputListener());
		mainLoopPlugins.add(plugin);
	}
}
