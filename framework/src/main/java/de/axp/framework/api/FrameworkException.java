package de.axp.framework.api;

public abstract class FrameworkException extends RuntimeException {

	public FrameworkException(Throwable t) {
		super(t);
	}
}
