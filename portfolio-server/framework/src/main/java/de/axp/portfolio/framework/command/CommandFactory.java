package de.axp.portfolio.framework.command;

public class CommandFactory {

	public static CommandBuffer createCommandBuffer() {
		return new CommandBufferImpl();
	}

	public static ResponseNotifier createResponseNotifier() {
		return new ResponseNotifierImpl();
	}

	public static WorkDistributor createWorkDistributor(CommandBuffer commandBuffer,
			ResponseNotifier responseNotifier) {
		ResponseBuffer responseBuffer = new ResponseBuffer();
		return new WorkDistributorImpl(commandBuffer, responseBuffer, responseNotifier);
	}
}
