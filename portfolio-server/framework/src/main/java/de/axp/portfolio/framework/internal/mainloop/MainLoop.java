package de.axp.portfolio.framework.internal.mainloop;

public interface MainLoop {

	void dispose() throws InterruptedException;

	void addPlugin(MainLoopPlugin plugin);

	interface MainLoopListener {

		void notify(MainLoopPackage aPackage);
	}

	interface MainLoopAccessor {

		void put(MainLoopPackage aPackage) throws InterruptedException;
	}

	interface MainLoopPlugin {

		void initialize(MainLoopAccessor input, MainLoopAccessor output);

		void dispose();

		MainLoopListener getInputListener();

		MainLoopListener getOutputListener();
	}
}
