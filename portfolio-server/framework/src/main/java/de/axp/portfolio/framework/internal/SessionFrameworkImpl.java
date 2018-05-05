package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.SessionFramework;
import de.axp.portfolio.framework.api.interaction.FrameworkPromise;
import de.axp.portfolio.framework.api.interfaces.FrameworkCommandInterface;
import de.axp.portfolio.framework.api.interfaces.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.service.command.CommandService;
import de.axp.portfolio.framework.internal.service.session.SessionService;

class SessionFrameworkImpl implements SessionFramework, FrameworkCommandInterface, FrameworkSessionInterface {

	private final FrameworkImpl framework;
	private final String sessionID;

	SessionFrameworkImpl(FrameworkImpl framework, String sessionID) {
		this.framework = framework;
		this.sessionID = sessionID;
	}

	@Override
	public void dispose() {
		framework.dispose();
	}

	@Override
	public void addAttribute(String key, Object value) {
		framework.addAttribute(key, value);
	}

	@Override
	public Object getAttribute(String key) {
		return framework.getAttribute(key);
	}

	@Override
	public String getSessionID() {
		return sessionID;
	}

	@Override
	public FrameworkSessionInterface getFrameworkSessionInterface() {
		return this;
	}

	@Override
	public FrameworkCommandInterface getFrameworkCommandInterface() {
		return this;
	}

	@Override
	public void dispatchCommand(String commandID, Object content, FrameworkPromise promise) {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		sessionService.checkID(sessionID);

		CommandService.Command command = new CommandService.Command(sessionID, commandID, content, promise);
		CommandService commandService = (CommandService) framework.getServiceRegistry().get(CommandService.class);
		try {
			commandService.dispatchCommand(sessionID, commandID, command);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initializeSession() {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		sessionService.initializeSession(sessionID);
	}

	@Override
	public boolean hasFrameworkActiveSessions() {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		return sessionService.getActiveSessions() != 0;
	}

	@Override
	public void destroySession() {
		SessionService sessionService = (SessionService) framework.getServiceRegistry().get(SessionService.class);
		sessionService.checkID(sessionID);
		sessionService.disposeSession(sessionID);
	}
}
