package de.axp.portfolio.framework.api.interaction;

public abstract class FrameworkPromise {

	private Object futureData;

	public void setFutureOutput(Object futureData) {
		this.futureData = futureData;
	}

	public Object getFutureData() {
		return futureData;
	}

	public void resolve() {
	}

	public void reject() {
	}
}
