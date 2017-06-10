package de.axp.portfolio.framework;

public class TestCommand implements FrameworkCommandInterface.Command {

	private final FrameworkSessionInterface.FrameworkSession session;
	private final CommandMessage message;
	private final Promise promise;

	TestCommand(FrameworkSessionInterface.FrameworkSession session, CommandMessage message, Promise promise) {
		this.session = session;
		this.message = message;
		this.promise = promise;
	}

	@Override
	public FrameworkSessionInterface.FrameworkSession getSession() {
		return session;
	}

	@Override
	public CommandMessage getMessage() {
		return message;
	}

	@Override
	public Promise getPromise() {
		return promise;
	}
}
