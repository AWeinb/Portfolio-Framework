package de.axp.framework.api;

@FunctionalInterface
public interface FrameworkThreadSynchronizer {

	void makeAsyncToSync(Runnable runnable);

}
