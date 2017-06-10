package de.axp.portfolio.framework;

public class TestCommand implements FrameworkNotice {

	private final FrameworkSessionInterface.FrameworkSession session;
	private final TestMessage message;
	private final Promise promise;

	TestCommand(FrameworkSessionInterface.FrameworkSession session, TestMessage message, Promise promise) {
		this.session = session;
		this.message = message;
		this.promise = promise;
	}

	@Override
	public FrameworkSessionInterface.FrameworkSession getSession() {
		return session;
	}

	@Override
	public Message getMessage() {
		return message;
	}

	@Override
	public Promise getPromise() {
		return promise;
	}
}
