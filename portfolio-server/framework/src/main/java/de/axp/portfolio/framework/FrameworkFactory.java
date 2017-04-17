package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;
import de.axp.portfolio.framework.command.CommandFactory;
import de.axp.portfolio.framework.command.ResponseNotifier;
import de.axp.portfolio.framework.command.WorkDistributor;

public class FrameworkFactory {

	public static FrameworkInterface createFrameworkCommandInterface() {
		CommandBuffer commandBuffer = CommandFactory.createCommandBuffer();
		ResponseNotifier responseNotifier = CommandFactory.createResponseNotifier();
		WorkDistributor workDistributor = CommandFactory.createWorkDistributor(commandBuffer, responseNotifier);

		return new FrameworkInterfaceImpl(commandBuffer, responseNotifier, workDistributor);
	}
}
