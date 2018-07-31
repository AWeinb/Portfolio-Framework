package de.axp.portfolio.framework.internal.mainloop;

public class MainLoopFactory {

	public static MainLoop createMainLoop() {
		return new MainLoopImpl();
	}
}
