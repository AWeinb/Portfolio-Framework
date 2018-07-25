package de.axp.portfolio.framework.api;

public interface FrameworkPromise {

	interface FutureCallback {

		void execute(Object future);
	}
}
