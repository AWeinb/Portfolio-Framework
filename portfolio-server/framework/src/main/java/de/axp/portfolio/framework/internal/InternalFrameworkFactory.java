package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.BaseFramework;

public class InternalFrameworkFactory {

	public static BaseFramework createBaseFramework() {
		return new BaseFrameworkImpl();
	}
}
