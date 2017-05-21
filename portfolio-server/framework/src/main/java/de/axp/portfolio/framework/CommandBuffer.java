package de.axp.portfolio.framework;

public interface CommandBuffer {
	void putCommand(String command) throws InterruptedException;
}
