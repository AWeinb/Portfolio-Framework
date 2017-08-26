package de.axp.portfolio.framework.internal.mainloop;

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
	public void dispose() throws InterruptedException {
		for (MainLoopPlugin mainLoopPlugin : mainLoopPlugins) {
			mainLoopPlugin.dispose();
		}

		inputWorker.stopWorking();
		outputWorker.stopWorking();
	}

	@Override
	public void addPlugin(MainLoopPlugin plugin) {
		plugin.initialize(inputWorker.getAccessor(), outputWorker.getAccessor());
		inputWorker.addListener(plugin.getInputListener());
		outputWorker.addListener(plugin.getOutputListener());
		mainLoopPlugins.add(plugin);
	}
}
