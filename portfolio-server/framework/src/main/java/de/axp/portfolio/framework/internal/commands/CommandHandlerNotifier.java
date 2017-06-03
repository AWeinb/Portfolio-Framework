package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkCommandInterface;
import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.MessageHandlerInterface;

class CommandHandlerNotifier {

	private final MessageHandlerInterface messageHandlerInterface;
	private final ResponseBuffer responseBuffer;

	CommandHandlerNotifier(MessageHandlerInterface messageHandlerInterface, ResponseBuffer responseBuffer) {
		this.messageHandlerInterface = messageHandlerInterface;
		this.responseBuffer = responseBuffer;
	}

	void notify(CommandPacket commandPacket) {
		FrameworkSessionInterface.FrameworkSession frameworkSession = commandPacket.getFrameworkSession();
		FrameworkCommandInterface.Command.CommandMessage commandMessage = commandPacket.getCommandMessage();
		UnresolvedPromise promiseToResolveOrReject = new UnresolvedPromise(commandPacket);
		messageHandlerInterface.handleMessage(frameworkSession, commandMessage, promiseToResolveOrReject);
	}

	private class UnresolvedPromise implements MessageHandlerInterface.ResponsePromise {

		private final CommandPacket commandPacket;
		private FrameworkNotice.Message responseMessage;

		UnresolvedPromise(CommandPacket commandPacket) {
			this.commandPacket = commandPacket;
		}

		@Override
		public void setFuture(FrameworkNotice.Message responseMessage) {
			this.responseMessage = responseMessage;
		}

		@Override
		public void resolve() {
			ResponsePacket.ResponsePacketBuilder packetBuilder = new ResponsePacket.ResponsePacketBuilder();
			packetBuilder.setFrameworkSession(commandPacket.getFrameworkSession());
			packetBuilder.setCommandMessage(commandPacket.getCommandMessage());
			packetBuilder.setResponseMessage(responseMessage);
			packetBuilder.setResolved();
			ResponsePacket responsePacket = packetBuilder.build();

			tryToPutResponse(responsePacket);
		}

		@Override
		public void reject() {
			ResponsePacket.ResponsePacketBuilder packetBuilder = new ResponsePacket.ResponsePacketBuilder();
			packetBuilder.setFrameworkSession(commandPacket.getFrameworkSession());
			packetBuilder.setCommandMessage(commandPacket.getCommandMessage());
			packetBuilder.setResponseMessage(responseMessage);
			packetBuilder.setRejected();
			ResponsePacket responsePacket = packetBuilder.build();

			tryToPutResponse(responsePacket);
		}

		private void tryToPutResponse(ResponsePacket responsePacket) {
			try {
				responseBuffer.putResponse(responsePacket);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
