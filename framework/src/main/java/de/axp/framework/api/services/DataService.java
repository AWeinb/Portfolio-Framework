package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.extensions.DataHandler;

public interface DataService extends FrameworkService {

	void addDataHandler(DataHandler dataHandler);
}
