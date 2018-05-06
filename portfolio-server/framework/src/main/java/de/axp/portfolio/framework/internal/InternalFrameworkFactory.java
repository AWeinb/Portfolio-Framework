package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.Framework;

public class InternalFrameworkFactory {

	public static Framework createFramework() {
		return new FrameworkImpl();
	}
}
