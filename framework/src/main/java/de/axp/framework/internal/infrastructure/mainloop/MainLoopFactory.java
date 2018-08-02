package de.axp.framework.internal.infrastructure.mainloop;

public class MainLoopFactory {

	public static MainLoop createMainLoop() {
		return new MainLoopImpl();
	}
}
