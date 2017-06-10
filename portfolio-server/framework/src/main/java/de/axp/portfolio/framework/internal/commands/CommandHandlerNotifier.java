package de.axp.portfolio.framework.internal.commands;

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
		FrameworkNotice.Message commandMessage = commandPacket.getCommandMessage();
		FrameworkNotice.Promise promiseToResolveOrReject = new UnresolvedCommandHandlerPromise(commandPacket,
				responseBuffer);
		messageHandlerInterface.handleMessage(frameworkSession, commandMessage, promiseToResolveOrReject);
	}
}
