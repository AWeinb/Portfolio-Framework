package de.axp.portfolio.framework.internal.mainloop;

public final class MainLoopPackage {

	private final Object payload;
	private final STATE state;

	public MainLoopPackage(Object payload, STATE state) {
		this.payload = payload;
		this.state = state;
	}

	public Object getPayload() {
		return payload;
	}

	public STATE getState() {
		return state;
	}

	public enum STATE {
		Unknown, Resolved, Rejected
	}
}
