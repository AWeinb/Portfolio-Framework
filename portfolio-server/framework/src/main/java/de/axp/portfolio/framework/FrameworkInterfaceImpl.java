package de.axp.portfolio.framework;

class FrameworkInterfaceImpl implements FrameworkInterface {

	private boolean isInitialized;

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
