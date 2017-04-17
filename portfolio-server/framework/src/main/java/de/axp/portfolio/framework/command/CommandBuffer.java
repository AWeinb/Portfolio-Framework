package de.axp.portfolio.framework.command;

public interface CommandBuffer {
	void putCommand(String command) throws InterruptedException;
}
