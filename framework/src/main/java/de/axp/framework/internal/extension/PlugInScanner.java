package de.axp.framework.internal.extension;

import de.axp.framework.api.extension.FrameworkPlugIn;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

class PlugInScanner {

	List<FrameworkPlugIn> getPlugIns() {
		ServiceLoader<FrameworkPlugIn> serviceLoader = ServiceLoader.load(FrameworkPlugIn.class);
		ArrayList<FrameworkPlugIn> plugIns = new ArrayList<>();

		try {
			for (FrameworkPlugIn plugIn : serviceLoader) {
				plugIns.add(plugIn);
			}
		} catch (ServiceConfigurationError e) {
			throw new FrameworkPlugInException(e);
		}

		return plugIns;
	}
}
