package de.axp.portfolio.framework;

public interface FrameworkMessage<M extends FrameworkMessage.Message, P extends FrameworkMessage.Promise> {

	FrameworkSessionInterface.FrameworkSession getSession();

	M getMessage();

	P getPromise();

	interface Message {

	}

	interface Promise {

		void setFuture(Message futureData);

		void resolve();

		void reject();
	}
}