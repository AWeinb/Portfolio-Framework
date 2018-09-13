package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.plugins.DataHandler;

public interface DataService extends FrameworkService {

	void addDataHandler(DataHandler dataHandler);
}
