package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandFactory;

public class FrameworkFactory {

	public static final FrameworkFactory INSTANCE = new FrameworkFactory();

	private FrameworkInterface frameworkInterfaceInstance;

	static SessionManager createSessionManager() {
		return new SessionManager();
	}

	public synchronized FrameworkInterface getFrameworkCommandInterface() {
		if (frameworkInterfaceInstance == null) {
			frameworkInterfaceInstance = createFrameworkInterface();
		}
		return frameworkInterfaceInstance;
	}

	private synchronized FrameworkInterface createFrameworkInterface() {
		CommandBuffer commandBuffer = CommandFactory.createCommandBuffer();
		CommandNotifier commandNotifier = CommandFactory.createCommandListenerNotifier();
		ResponseNotifier responseNotifier = CommandFactory.createResponseNotifier();
		WorkDistributor workDistributor = CommandFactory.createWorkDistributor(commandBuffer, commandNotifier,
				responseNotifier);
		SessionManager sessionManager = createSessionManager();

		return new FrameworkInterfaceImpl(commandBuffer, commandNotifier, responseNotifier, workDistributor,
				sessionManager);
	}
}
