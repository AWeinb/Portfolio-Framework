package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandBuffer;
import de.axp.portfolio.framework.ResponseNotifier;
import de.axp.portfolio.framework.WorkDistributor;

public class CommandFactory {

	public static CommandBuffer createCommandBuffer() {
		return new CommandBufferImpl();
	}

	public static ResponseNotifier createResponseNotifier() {
		return new ResponseNotifierImpl();
	}

	public static WorkDistributor createWorkDistributor(CommandBuffer commandBuffer,
	                                                    CommandListenerNotifier commandListenerNotifier, ResponseNotifier responseNotifier) {
		ResponseBuffer responseBuffer = new ResponseBuffer();
		return new WorkDistributorImpl(commandBuffer, responseBuffer, commandListenerNotifier, responseNotifier);
	}

	public static CommandListenerNotifier createCommandListenerNotifier() {
		return new CommandListenerNotifier();
	}
}
