package de.axp.framework.internal.infrastructure.mainloop;

public interface MainLoop {

	void dispose();

	void addListeners(MainLoopListener inputListener, MainLoopListener outputListener);

	void addInput(MainLoopPackage request);

	void addOutput(MainLoopPackage response);

	interface MainLoopListener {

		void notify(MainLoopPackage aPackage);
	}

	interface MainLoopAccessor {

		void put(MainLoopPackage aPackage);
	}
}
