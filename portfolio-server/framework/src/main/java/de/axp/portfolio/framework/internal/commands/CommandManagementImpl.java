package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.CommandManagement;

import static de.axp.portfolio.framework.FrameworkNotice.Promise;

class CommandManagementImpl implements CommandManagement {

	private final CommandBuffer commandBuffer;
	private final ResponseBuffer responseBuffer;
	private final CommandHandlerNotifier commandHandlerNotifier;
	private final ResponseHandlerNotifier responseHandlerNotifier;

	private Thread commandWorkerThread;
	private Thread responseWorkerThread;

	CommandManagementImpl(CommandBuffer commandBuffer, ResponseBuffer responseBuffer,
	                      CommandHandlerNotifier commandHandlerNotifier,
	                      ResponseHandlerNotifier responseHandlerNotifier) {
		this.commandBuffer = commandBuffer;
		this.responseBuffer = responseBuffer;
		this.commandHandlerNotifier = commandHandlerNotifier;
		this.responseHandlerNotifier = responseHandlerNotifier;
	}

	@Override
	public void initialize() {
		if (isInitialized()) {
			throw new AlreadyInitializedException();
		}
		commandWorkerThread = new Thread(new CommandWorker(commandBuffer, commandHandlerNotifier));
		commandWorkerThread.start();
		responseWorkerThread = new Thread(new ResponseWorker(responseBuffer, responseHandlerNotifier));
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
		commandBuffer.putCommand(CommandWorker.POISON);
		responseBuffer.putResponse(ResponseWorker.POISON);

		commandWorkerThread.join();
		responseWorkerThread.join();
	}

	@Override
	public void dispatchCommand(FrameworkSessionInterface.FrameworkSession session,
	                            FrameworkNotice.Message commandMessage, Promise promise) throws InterruptedException {
		responseHandlerNotifier.registerPromise(session, commandMessage, promise);

		CommandPacket.CommandPacketBuilder commandPacketBuilder = new CommandPacket.CommandPacketBuilder();
		commandPacketBuilder.setFrameworkSession(session);
		commandPacketBuilder.setCommandMessage(commandMessage);
		CommandPacket commandPacket = commandPacketBuilder.build();
		commandBuffer.putCommand(commandPacket);
	}
}
