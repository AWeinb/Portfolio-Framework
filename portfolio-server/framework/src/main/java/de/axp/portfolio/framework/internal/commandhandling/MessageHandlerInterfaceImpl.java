package de.axp.portfolio.framework.internal.commandhandling;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;
import de.axp.portfolio.framework.internal.MessageHandlerInterface;

public class MessageHandlerInterfaceImpl implements MessageHandlerInterface {

	@Override
	public void handleMessage(FrameworkSessionInterface.FrameworkSession session,
	                          FrameworkNotice.Message commandMessage, FrameworkNotice.Promise promiseToResolveOrReject) {
		promiseToResolveOrReject.setFuture(new FrameworkNotice.Message() {
		});
		promiseToResolveOrReject.resolve();
	}
}
