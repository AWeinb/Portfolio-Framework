package de.axp.portfolio.framework;

public class FrameworkFactory {

	public static FrameworkInterface createFrameworkCommandInterface() {
		return new FrameworkInterfaceImpl();
	}
}
