package de.axp.framework.internal.extension;

import de.axp.framework.api.extension.FrameworkPlugIn;

import java.util.List;

public class ExtensionFactory {

	private static PlugInScanner plugInScanner = new PlugInScanner();

	public static List<FrameworkPlugIn> getPlugIns() {
		return plugInScanner.getPlugIns();
	}
}
