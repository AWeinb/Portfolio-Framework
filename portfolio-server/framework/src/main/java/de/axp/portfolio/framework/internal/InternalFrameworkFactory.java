package de.axp.portfolio.framework.internal;

import de.axp.portfolio.framework.api.Framework;
import de.axp.portfolio.framework.api.interaction.FrameworkExtensions;

public class InternalFrameworkFactory {

	public static Framework createFramework(FrameworkExtensions frameworkExtensions) {
		return new FrameworkImpl(frameworkExtensions);
	}
}
