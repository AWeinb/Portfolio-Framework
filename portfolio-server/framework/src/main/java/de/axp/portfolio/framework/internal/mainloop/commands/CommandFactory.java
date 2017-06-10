package de.axp.portfolio.framework.internal.mainloop.commands;

import de.axp.portfolio.framework.internal.CommandManagement;
import de.axp.portfolio.framework.internal.MessageHandlerInterface;

public class CommandFactory {

	public static CommandManagement createCommandManagement(MessageHandlerInterface messageHandlerInterface) {
		CommandBuffer commandBuffer = new CommandBuffer();
		ResponseBuffer responseBuffer = new ResponseBuffer();
		CommandHandlerNotifier commandHandlerNotifier = new CommandHandlerNotifier(messageHandlerInterface,
				responseBuffer);
		ResponseHandlerNotifier responseHandlerNotifier = new ResponseHandlerNotifier();
		return new CommandManagementImpl(commandBuffer, responseBuffer, commandHandlerNotifier,
				responseHandlerNotifier);
	}
}
