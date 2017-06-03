package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.CommandManagement;

import static de.axp.portfolio.framework.FrameworkCommandInterface.Command.CommandMessage;
import static de.axp.portfolio.framework.FrameworkCommandInterface.Command.Promise;
import static de.axp.portfolio.framework.internal.commands.CommandBuffer.CommandPacket;
import static de.axp.portfolio.framework.internal.commands.CommandBuffer.PoisonedCommandPacket;
import static de.axp.portfolio.framework.internal.commands.ResponseBuffer.PoisonedResponsePacket;

class CommandManagementImpl implements CommandManagement {

	private final CommandBuffer commandBuffer;
	private final ResponseBuffer responseBuffer;
	private final CommandHandlerNotifier commandHandlerNotifier;
	private final ResponseNotifier responseNotifier;

	private Thread commandWorkerThread;
	private Thread responseWorkerThread;

	CommandManagementImpl(CommandBuffer commandBuffer, ResponseBuffer responseBuffer,
	                      CommandHandlerNotifier commandHandlerNotifier, ResponseNotifier responseNotifier) {
		this.commandBuffer = commandBuffer;
		this.responseBuffer = responseBuffer;
		this.commandHandlerNotifier = commandHandlerNotifier;
		this.responseNotifier = responseNotifier;
	}

	@Override
	public void initialize() {
		commandWorkerThread = new Thread(new CommandWorker(commandBuffer, commandHandlerNotifier));
		commandWorkerThread.start();
		responseWorkerThread = new Thread(new ResponseWorker(responseBuffer, responseNotifier));
		responseWorkerThread.start();
	}

	@Override
	public void dispose() throws InterruptedException {
		commandBuffer.putCommand(new PoisonedCommandPacket());
		responseBuffer.putResponse(new PoisonedResponsePacket());

		commandWorkerThread.join();
		responseWorkerThread.join();
	}

	@Override
	public void dispatchCommand(FrameworkSessionInterface.FrameworkSession session, CommandMessage commandMessage,
	                            Promise promise) throws InterruptedException {
		responseNotifier.registerPromise(session, commandMessage, promise);

		CommandPacket commandPacket = new CommandPacket() {

			@Override
			public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
				return session;
			}

			@Override
			public CommandMessage getCommand() {
				return commandMessage;
			}
		};
		commandBuffer.putCommand(commandPacket);
	}
}
