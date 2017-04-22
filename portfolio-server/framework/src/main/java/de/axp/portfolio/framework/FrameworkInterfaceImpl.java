package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;
import de.axp.portfolio.framework.command.ResponseNotifier;
import de.axp.portfolio.framework.command.WorkDistributor;

class FrameworkInterfaceImpl implements FrameworkInterface {

	private final CommandBuffer commandBuffer;
	private final ResponseNotifier responseNotifier;
	private final WorkDistributor workDistributor;
	private final SessionManager sessionManager;

	private boolean isInitialized;

	FrameworkInterfaceImpl(CommandBuffer commandBuffer, ResponseNotifier responseNotifier,
			WorkDistributor workDistributor, SessionManager sessionManager) {
		this.commandBuffer = commandBuffer;
		this.responseNotifier = responseNotifier;
		this.workDistributor = workDistributor;
		this.sessionManager = sessionManager;
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
	public boolean isFrameworkInitialized() {
		return isInitialized;
	}

	@Override
	public boolean hasFrameworkActiveSessions() {
		return false;
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
	public void initSession(String sessionId) {
		sessionManager.initSession(sessionId);
	}

	@Override
	public SessionState testSessionId(String sessionId) {
		return sessionManager.testSessionId(sessionId);
	}

	@Override
	public void destroySession(String sessionId) {
		sessionManager.destroySession(sessionId);
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
