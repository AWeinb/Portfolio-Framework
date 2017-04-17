package de.axp.portfolio.framework.command;

public class CommandFactory {

	public static CommandBuffer createCommandBuffer() {
		return new CommandBufferImpl();
	}
}
