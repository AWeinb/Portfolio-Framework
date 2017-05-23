package de.axp.portfolio.framework.internal.commandhandling;

import de.axp.portfolio.framework.FrameworkMessage;
import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.MessageHandlerInterface;

public class MessageHandlerInterfaceImpl implements MessageHandlerInterface {

	@Override
	public void handleMessage(FrameworkSessionInterface.FrameworkSession session,
	                          FrameworkMessage.Message commandMessage, ResponsePromise responsePromise) {
		responsePromise.setFuture(new FrameworkMessage.Message() {
		});
		responsePromise.resolve();
	}
}
