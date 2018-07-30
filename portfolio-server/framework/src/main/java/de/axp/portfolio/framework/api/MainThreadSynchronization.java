package de.axp.portfolio.framework.api;

@FunctionalInterface
public interface MainThreadSynchronization {

	void makeAsyncToSync(Runnable runnable);

}
