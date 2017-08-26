package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.UninitializedFramework;

public class InternalFrameworkFactory {

	public static UninitializedFramework createUninitializedFramework() {
		return new UninitializedFrameworkImpl();
	}
}
