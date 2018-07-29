package de.axp.portfolio.framework.api;

@FunctionalInterface
public interface UserSessionAccessor {

	void makeAsyncToSync(Runnable runnable);

}
