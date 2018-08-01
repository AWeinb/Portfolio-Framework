package de.axp.framework.internal.mainloop;

public class MainLoopFactory {

	public static MainLoop createMainLoop() {
		return new MainLoopImpl();
	}
}
