package de.axp.portfolio.framework;

public interface FrameworkNotice<M extends FrameworkNotice.Message, P extends FrameworkNotice.Promise> {

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