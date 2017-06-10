package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkNotice;

class UnresolvedCommandHandlerPromise implements FrameworkNotice.Promise {

	private final CommandPacket commandPacket;
	private final ResponseBuffer responseBuffer;

	private FrameworkNotice.Message responseMessage;

	UnresolvedCommandHandlerPromise(CommandPacket commandPacket, ResponseBuffer responseBuffer) {
		this.commandPacket = commandPacket;
		this.responseBuffer = responseBuffer;
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
