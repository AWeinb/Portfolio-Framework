package de.axp.portfolio.framework;

public class TestCommand implements FrameworkCommandInterface.Command {

	private final CommandMessage message;
	private final Promise promise;

	TestCommand(CommandMessage message, Promise promise) {
		this.message = message;
		this.promise = promise;
	}

	@Override
	public FrameworkSessionInterface.FrameworkSession getSession() {
		return new FrameworkSessionInterface.FrameworkSession() {
		};
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
