package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkMessage;
import de.axp.portfolio.framework.FrameworkSessionInterface;

class ResponseNotifier {

	private FrameworkMessage.Promise promise;

	void registerPromise(FrameworkSessionInterface.FrameworkSession session, FrameworkMessage.Message command,
	                     FrameworkMessage.Promise promise) {
		this.promise = promise;
	}

	void notify(ResponseBuffer.ResponsePacket responsePacket) {
		FrameworkSessionInterface.FrameworkSession frameworkSession = responsePacket.getFrameworkSession();
		FrameworkMessage.Message command = responsePacket.getCommand();

		promise.setFuture(responsePacket.getResponse());
		if (!responsePacket.wasRejected()) {
			promise.resolve();
		} else {
			promise.reject();
		}
	}
}
