package de.axp.framework.internal.services.task.mainloop;

public final class MainLoopPackage {

	private final Object payload;

	public MainLoopPackage(Object payload) {
		this.payload = payload;
	}

	public Object getPayload() {
		return payload;
	}
}
