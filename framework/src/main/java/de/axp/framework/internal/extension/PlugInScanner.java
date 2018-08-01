package de.axp.framework.internal.extension;

import de.axp.framework.api.extension.PortfolioFrameworkPlugIn;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

class PlugInScanner {

	List<PortfolioFrameworkPlugIn> getPlugIns() {
		ServiceLoader<PortfolioFrameworkPlugIn> serviceLoader = ServiceLoader.load(PortfolioFrameworkPlugIn.class);
		ArrayList<PortfolioFrameworkPlugIn> plugIns = new ArrayList<>();

		try {
			for (PortfolioFrameworkPlugIn plugIn : serviceLoader) {
				plugIns.add(plugIn);
			}
		} catch (ServiceConfigurationError e) {
			throw new FrameworkPlugInException(e);
		}

		return plugIns;
	}
}
