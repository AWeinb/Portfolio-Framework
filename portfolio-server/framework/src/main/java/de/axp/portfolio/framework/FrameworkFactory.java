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
		CommandListenerNotifier commandListenerNotifier = CommandFactory.createCommandListenerNotifier();
		WorkDistributor workDistributor = CommandFactory.createWorkDistributor(commandBuffer, commandListenerNotifier,
				responseNotifier);
		SessionManager sessionManager = createSessionManager();

		return new FrameworkInterfaceImpl(commandBuffer, commandListenerNotifier, responseNotifier, workDistributor,
				sessionManager);
	}
}
