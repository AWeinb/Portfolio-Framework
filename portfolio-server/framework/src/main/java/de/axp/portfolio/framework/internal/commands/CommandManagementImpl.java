package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.CommandManagement;

import static de.axp.portfolio.framework.FrameworkCommandInterface.Command.CommandMessage;
import static de.axp.portfolio.framework.FrameworkCommandInterface.Command.Promise;
import static de.axp.portfolio.framework.internal.commands.CommandPacket.PoisonedCommandPacket;
import static de.axp.portfolio.framework.internal.commands.ResponsePacket.PoisonedResponsePacket;

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
		if (isInitialized()) {
			throw new AlreadyInitializedException();
		}
		commandWorkerThread = new Thread(new CommandWorker(commandBuffer, commandHandlerNotifier));
		commandWorkerThread.start();
		responseWorkerThread = new Thread(new ResponseWorker(responseBuffer, responseNotifier));
		responseWorkerThread.start();
	}

	@Override
	public boolean isInitialized() {
		boolean commandHandlingActive = commandWorkerThread != null && commandWorkerThread.isAlive();
		boolean responseHandlingActive = responseWorkerThread != null && responseWorkerThread.isAlive();
		return commandHandlingActive && responseHandlingActive;
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

		CommandPacket.CommandPacketBuilder commandPacketBuilder = new CommandPacket.CommandPacketBuilder();
		commandPacketBuilder.setFrameworkSession(session);
		commandPacketBuilder.setCommandMessage(commandMessage);
		CommandPacket commandPacket = commandPacketBuilder.build();
		commandBuffer.putCommand(commandPacket);
	}
}
