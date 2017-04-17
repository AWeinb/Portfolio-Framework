package de.axp.portfolio.framework;

import de.axp.portfolio.framework.command.CommandBuffer;
import de.axp.portfolio.framework.command.CommandFactory;

public class FrameworkFactory {

	public static FrameworkInterface createFrameworkCommandInterface() {
		CommandBuffer commandBuffer = CommandFactory.createCommandBuffer();
		return new FrameworkInterfaceImpl(commandBuffer);
	}
}
