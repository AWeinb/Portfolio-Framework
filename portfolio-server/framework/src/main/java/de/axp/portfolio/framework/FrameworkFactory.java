package de.axp.portfolio.framework;

import de.axp.portfolio.framework.internal.InternalFrameworkFactory;

import java.util.Map;

public class FrameworkFactory {

	public static final FrameworkFactory INSTANCE = new FrameworkFactory();

	private Map<Class, FrameworkInterface> frameworkInterfaces;

	public Map<Class, FrameworkInterface> getFrameworkInterfaces() {
		if (frameworkInterfaces == null) {
			frameworkInterfaces = InternalFrameworkFactory.createFrameworkInterfaces();
		}
		return frameworkInterfaces;
	}

	public void disposeFrameworkInterfaces() {
		for (FrameworkInterface frameworkInterface : frameworkInterfaces.values()) {
			frameworkInterface.dispose();
		}
		frameworkInterfaces = null;
	}
}
