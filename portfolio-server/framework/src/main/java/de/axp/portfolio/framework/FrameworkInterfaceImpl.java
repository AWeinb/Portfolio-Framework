package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;

class FrameworkInterfaceImpl implements FrameworkInterface {

	private final CommandBuffer commandBuffer;

	private Thread worker;
	private boolean isInitialized;

	FrameworkInterfaceImpl(CommandBuffer commandBuffer) {
		this.commandBuffer = commandBuffer;
	}

	@Override
	public void initializeSession() {
		if (isInitialized) {
			throw new FrameworkAlreadyInitializedException();
		}
		isInitialized = true;
	}

	@Override
	public void destroySession() {
		if (!isInitialized) {
			throw new FrameworkNotInitializedException();
		}
		isInitialized = false;
	}

	@Override
	public void putCommand(String command) throws InterruptedException {
		if (!isInitialized) {
			throw new FrameworkNotInitializedException();
		}

		commandBuffer.putCommand(command);
	}

	public abstract class FrameworkException extends RuntimeException {

	}

	public final class FrameworkAlreadyInitializedException extends FrameworkException {

		@Override
		public String getMessage() {
			return "The framework is already initialized";
		}
	}

	public final class FrameworkNotInitializedException extends FrameworkException {

		@Override
		public String getMessage() {
			return "Initialize the framework before usage!";
		}
	}
}
