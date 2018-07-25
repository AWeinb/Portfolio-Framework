package de.axp.portfolio.framework.internal.mainloop;

public final class MainLoopPackage {

	private final String sessionId;
	private final String context;
	private final Object payload;
	private final STATE state;

	public MainLoopPackage(String sessionId, String context, Object payload, STATE state) {
		this.sessionId = sessionId;
		this.context = context;
		this.payload = payload;
		this.state = state;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getContext() {
		return context;
	}

	public Object getPayload() {
		return payload;
	}

	public STATE getState() {
		return state;
	}

	public enum STATE {
		Unknown, Resolved, Rejected, Poisoned
	}
}
