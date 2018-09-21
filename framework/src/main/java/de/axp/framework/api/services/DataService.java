package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkService;
import de.axp.framework.api.plugins.DataDefinition;

public interface DataService extends FrameworkService {

	void registerDataDefinition(DataDefinition definition);

	void load(String dataId, TaskService.TaskPromise promise);

	void save(String dataId, TaskService.TaskPromise promise);

	Object get(String dataId);

	void set(String dataId, String data);
}
