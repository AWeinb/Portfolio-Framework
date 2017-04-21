package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;
import de.axp.portfolio.framework.command.ResponseNotifier;
import de.axp.portfolio.framework.command.WorkDistributor;

import java.util.ArrayList;
import java.util.List;

class FrameworkInterfaceImpl implements FrameworkInterface {

	private final CommandBuffer commandBuffer;
	private final ResponseNotifier responseNotifier;
	private final WorkDistributor workDistributor;

	private boolean isInitialized;
	private List<String> sessionIds = new ArrayList<>();

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
		if (sessionIds.contains(sessionId)) {
			throw new FrameworkSessionAlreadyUsedException();
		}

		sessionIds.add(sessionId);
	}

	@Override
	public SessionState testSessionId(String sessionId) {
		return sessionIds.contains(sessionId) ? SessionState.ACTIVE : SessionState.UNKNOWN;
	}

	@Override
	public void destroySession(String sessionId) {
		if (!sessionIds.contains(sessionId)) {
			throw new FrameworkSessionIsUnknownException();
		}

		sessionIds.remove(sessionId);
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

	public class FrameworkSessionAlreadyUsedException extends FrameworkException {

		@Override
		public String getMessage() {
			return "Session ID is already in use!";
		}
	}

	public class FrameworkSessionIsUnknownException extends FrameworkException {
		@Override
		public String getMessage() {
			return "Session ID is unknown!";
		}
	}
}
