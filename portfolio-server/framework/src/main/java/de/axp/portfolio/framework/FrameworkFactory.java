package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.*;

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
		ResponseNotifier responseNotifier = CommandFactory.createResponseNotifier();
		CommandNotifier commandNotifier = CommandFactory.createCommandListenerNotifier();
		WorkDistributor workDistributor = CommandFactory.createWorkDistributor(commandBuffer, commandNotifier,
				responseNotifier);
		SessionManager sessionManager = createSessionManager();

		return new FrameworkInterfaceImpl(commandBuffer, commandNotifier, responseNotifier, workDistributor,
				sessionManager);
	}
}
