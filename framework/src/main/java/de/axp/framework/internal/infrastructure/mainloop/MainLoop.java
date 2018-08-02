package de.axp.framework.internal.infrastructure.mainloop;

public interface MainLoop {

	void dispose();

	void addPlugin(MainLoopPlugin plugin);

	interface MainLoopListener {

		void notify(MainLoopPackage aPackage);
	}

	interface MainLoopAccessor {

		void put(MainLoopPackage aPackage);
	}

	interface MainLoopPlugin {

		void initialize(MainLoopAccessor inputBufferAccessor, MainLoopAccessor outputBufferAccessor);

		void dispose();

		MainLoopListener getInputListener();

		MainLoopListener getOutputListener();

	}
}
