package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;
import de.axp.portfolio.framework.command.ResponseNotifier;
import de.axp.portfolio.framework.command.WorkDistributor;

class FrameworkInterfaceImpl implements FrameworkInterface {

	private final CommandBuffer commandBuffer;
	private final ResponseNotifier responseNotifier;
	private final WorkDistributor workDistributor;

	private boolean isInitialized;

	FrameworkInterfaceImpl(CommandBuffer commandBuffer, ResponseNotifier responseNotifier,
			WorkDistributor workDistributor) {
		this.commandBuffer = commandBuffer;
		this.responseNotifier = responseNotifier;
		this.workDistributor = workDistributor;
	}

	@Override
	public void initFramework() {
		if (isInitialized) {
			throw new FrameworkAlreadyInitializedException();
		}

		workDistributor.initWorkers();
		isInitialized = true;
	}

	@Override
	public void deinitFramework() {
		if (!isInitialized) {
			throw new FrameworkNotInitializedException();
		}

		workDistributor.stopWorkers();
		isInitialized = false;
	}

	@Override
	public void initSession() {
	}

	@Override
	public void destroySession() {
	}

	@Override
	public void putCommand(String command) throws InterruptedException {
		if (!isInitialized) {
			throw new FrameworkNotInitializedException();
		}
		commandBuffer.putCommand(command);
	}

	@Override
	public void addListener(FrameworkResponseListener responseListener) {
		if (!isInitialized) {
			throw new FrameworkNotInitializedException();
		}
		responseNotifier.addResponseListener(responseListener);
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
