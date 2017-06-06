package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;

class ResponseHandlerNotifier {

	private FrameworkNotice.Promise promise;

	void registerPromise(FrameworkSessionInterface.FrameworkSession session, FrameworkNotice.Message command,
	                     FrameworkNotice.Promise promise) {
		this.promise = promise;
	}

	void notify(ResponsePacket responsePacket) {
		FrameworkSessionInterface.FrameworkSession frameworkSession = responsePacket.getFrameworkSession();
		FrameworkNotice.Message command = responsePacket.getCommandMessage();

		promise.setFuture(responsePacket.getResponseMessage());
		if (!responsePacket.wasRejected()) {
			promise.resolve();
		} else {
			promise.reject();
		}
	}
}
