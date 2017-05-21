package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandBuffer;
import de.axp.portfolio.framework.CommandNotifier;
import de.axp.portfolio.framework.ResponseNotifier;
import de.axp.portfolio.framework.WorkDistributor;

public class CommandFactory {

	public static CommandBuffer createCommandBuffer() {
		return new CommandBufferImpl();
	}

	public static ResponseNotifier createResponseNotifier() {
		return new ResponseNotifierImpl();
	}

	public static WorkDistributor createWorkDistributor(CommandBuffer commandBuffer, CommandNotifier commandNotifier,
	                                                    ResponseNotifier responseNotifier) {
		ResponseBuffer responseBuffer = new ResponseBuffer();
		return new WorkDistributorImpl(commandBuffer, responseBuffer, commandNotifier, responseNotifier);
	}

	public static CommandNotifierImpl createCommandListenerNotifier() {
		return new CommandNotifierImpl();
	}
}
