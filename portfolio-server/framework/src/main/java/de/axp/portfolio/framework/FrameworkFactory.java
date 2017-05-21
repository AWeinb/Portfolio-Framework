package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandFactory;
import de.axp.portfolio.framework.response.ResponseFactory;

public class FrameworkFactory {

	public static final FrameworkFactory INSTANCE = new FrameworkFactory();

	private FrameworkInterface frameworkInterfaceInstance;
	private FrameworkCommandInterfaceImpl frameworkCommandInterfaceInstance;

	public synchronized FrameworkInterface getFrameworkInterface() {
		if (frameworkInterfaceInstance == null) {
			SessionManager sessionManager = new SessionManager();
			frameworkInterfaceInstance = new FrameworkInterfaceImpl(sessionManager);
		}
		return frameworkInterfaceInstance;
	}

	public synchronized FrameworkCommandInterface getFrameworkCommandInterface() {
		if (frameworkCommandInterfaceInstance == null) {
			CommandManagement commandManagement = CommandFactory.createCommandManagement();
			ResponseManagement responseManagement = ResponseFactory.createResponseManagement();
			frameworkCommandInterfaceInstance = new FrameworkCommandInterfaceImpl(commandManagement);
		}
		return frameworkCommandInterfaceInstance;
	}
}
