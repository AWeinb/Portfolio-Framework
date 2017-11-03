package de.axp.portfolio.framework.internal.mainloop;

import de.axp.portfolio.framework.api.interaction.FrameworkPackage;

import static de.axp.portfolio.framework.internal.mainloop.MainLoopPackage.STATE.Unknown;

public final class MainLoopPackage {

	private final FrameworkPackage frameworkPackage;
	private STATE state = Unknown;

	public MainLoopPackage(FrameworkPackage frameworkPackage) {
		this.frameworkPackage = frameworkPackage;
	}

	public FrameworkPackage getFrameworkPackage() {
		return frameworkPackage;
	}

	public STATE getState() {
		return state;
	}

	public void setState(STATE state) {
		this.state = state;
	}

	public enum STATE {
		Unknown, Resolved, Rejected
	}
}
