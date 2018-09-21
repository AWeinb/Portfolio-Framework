package de.axp.framework.api.services;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.FrameworkService;

public interface DataService extends FrameworkService {

	void registerDataDefinition(DataDefinition definition);

	void load(String dataId, TaskService.TaskPromise promise);

	void save(String dataId, TaskService.TaskPromise promise);

	Object get(String dataId);

	void set(String dataId, String data);

	interface DataDefinition extends FrameworkPlugin {

		String dataId();

		TaskService.Task getLoadTask();

		TaskService.Task getSaveTask();

	}
}
