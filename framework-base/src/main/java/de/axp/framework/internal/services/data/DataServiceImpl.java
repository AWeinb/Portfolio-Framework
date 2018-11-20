package de.axp.framework.internal.services.data;

import java.util.Set;

import de.axp.framework.api.services.DataService;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.ServiceManager;
import de.axp.framework.api.services.TaskService;

class DataServiceImpl implements DataService {

	private final ServiceManager serviceManager;
	private final DataCache dataCache;

	DataServiceImpl(ServiceManager serviceManager, DataCache dataCache) {
		this.serviceManager = serviceManager;
		this.dataCache = dataCache;
	}

	@Override
	public void disposeService() {

	}

	@Override
	public void registerDataDefinition(DataDefinition definition) {
		PluginService pluginService = serviceManager.getService(PluginService.class);
		pluginService.addPlugin(DataDefinition.class, definition);
	}

	@Override
	public void load(String dataId, TaskService.TaskPromise promise) {
		if (!dataCache.contains(dataId)) {
			dataCache.set(dataId, "");

			DataDefinition dataDefinition = getDataDefinition(dataId);
			TaskService taskService = serviceManager.getService(TaskService.class);
			taskService.triggerTask(dataDefinition.getLoadTask(), response -> {
				if (response.getResolution() == TaskService.TaskResolution.RESOLVED) {
					dataCache.set(dataId, response.getContent());
				}
				promise.respond(response);
			});
		}
	}

	@Override
	public void save(String dataId, TaskService.TaskPromise promise) {
		DataDefinition dataDefinition = getDataDefinition(dataId);
		TaskService taskService = serviceManager.getService(TaskService.class);
		taskService.triggerTask(dataDefinition.getSaveTask(), promise);
	}

	@Override
	public Object get(String dataId) {
		return dataCache.get(dataId);
	}

	@Override
	public void set(String dataId, String data) {
		dataCache.set(dataId, data);
	}

	private DataDefinition getDataDefinition(String dataId) {
		PluginService pluginService = serviceManager.getService(PluginService.class);
		Set<DataDefinition> dataDefinitions = pluginService.getPlugins(DataDefinition.class);
		return dataDefinitions.stream().filter(dd -> dd.dataId().equals(dataId)).findFirst().get();
	}
}
