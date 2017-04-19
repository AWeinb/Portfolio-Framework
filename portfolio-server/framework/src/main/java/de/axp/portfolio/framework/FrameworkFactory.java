package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;
import de.axp.portfolio.framework.command.CommandFactory;
import de.axp.portfolio.framework.command.ResponseNotifier;
import de.axp.portfolio.framework.command.WorkDistributor;

public class FrameworkFactory {

	public static FrameworkFactory INSTANCE = new FrameworkFactory();

	private FrameworkInterface frameworkInterfaceInstance;

	public synchronized FrameworkInterface getFrameworkCommandInterface() {
		if (frameworkInterfaceInstance == null) {
			frameworkInterfaceInstance = createFrameworkInterface();
		}
		return frameworkInterfaceInstance;
	}

	private synchronized FrameworkInterface createFrameworkInterface() {
		CommandBuffer commandBuffer = CommandFactory.createCommandBuffer();
		ResponseNotifier responseNotifier = CommandFactory.createResponseNotifier();
		WorkDistributor workDistributor = CommandFactory.createWorkDistributor(commandBuffer, responseNotifier);

		return new FrameworkInterfaceImpl(commandBuffer, responseNotifier, workDistributor);
	}
}
