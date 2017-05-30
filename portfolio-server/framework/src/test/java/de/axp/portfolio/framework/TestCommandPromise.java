package de.axp.portfolio.framework;

public class TestCommandPromise implements FrameworkNotice.Promise {

	public FrameworkNotice.Message futureData;
	public Boolean resolved = false;
	public Boolean rejected = false;

	@Override
	public void setFuture(FrameworkNotice.Message futureData) {
		this.futureData = futureData;
	}

	@Override
	public void resolve() {
		resolved = true;
	}

	@Override
	public void reject() {
		rejected = true;
	}
}
