package de.axp.portfolio.framework.command;

public interface WorkDistributor {

	String POISON = "POISON";

	void initWorkers();

	void stopWorkers();
}
