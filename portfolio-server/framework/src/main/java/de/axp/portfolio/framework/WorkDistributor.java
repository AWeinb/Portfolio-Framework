package de.axp.portfolio.framework;

public interface WorkDistributor {

	String POISON = "POISON";

	void initWorkers();

	void stopWorkers();

	boolean isWorking();
}
