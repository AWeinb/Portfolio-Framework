package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;

class ResponseNotifier {

	private FrameworkNotice.Promise promise;

	void registerPromise(FrameworkSessionInterface.FrameworkSession session, FrameworkNotice.Message command,
	                     FrameworkNotice.Promise promise) {
		this.promise = promise;
	}

	void notify(ResponseBuffer.ResponsePacket responsePacket) {
		FrameworkSessionInterface.FrameworkSession frameworkSession = responsePacket.getFrameworkSession();
		FrameworkNotice.Message command = responsePacket.getCommand();

		promise.setFuture(responsePacket.getResponse());
		if (!responsePacket.wasRejected()) {
			promise.resolve();
		} else {
			promise.reject();
		}
	}
}
