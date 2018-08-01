package de.axp.framework.internal.mainloop;

public final class MainLoopPackage {

	private final String sessionId;
	private final Object payload;

	public MainLoopPackage(String sessionId, Object payload) {
		this.sessionId = sessionId;
		this.payload = payload;
	}

	public String getSessionId() {
		return sessionId;
	}

	public Object getPayload() {
		return payload;
	}
}
