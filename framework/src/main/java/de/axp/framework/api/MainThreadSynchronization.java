package de.axp.framework.api;

@FunctionalInterface
public interface MainThreadSynchronization {

	void makeAsyncToSync(Runnable runnable);

}
