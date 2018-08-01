package de.axp.framework.internal.mainloop;

public final class MainLoopPackage {

	private final String sessionId;
	private final String contextId;
	private final Object payload;

	public MainLoopPackage(String sessionId, String contextId, Object payload) {
		this.sessionId = sessionId;
		this.contextId = contextId;
		this.payload = payload;
	}

	public String getSessionId() {
		return sessionId;
	}

	public String getContextId() {
		return contextId;
	}

	public Object getPayload() {
		return payload;
	}
}
