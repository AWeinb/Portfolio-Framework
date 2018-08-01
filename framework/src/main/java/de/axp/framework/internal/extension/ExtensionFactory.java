package de.axp.framework.internal.extension;

import de.axp.framework.api.extension.PortfolioFrameworkPlugIn;

import java.util.List;

public class ExtensionFactory {

	private static PlugInScanner plugInScanner = new PlugInScanner();

	public static List<PortfolioFrameworkPlugIn> getPlugIns() {
		return plugInScanner.getPlugIns();
	}
}
