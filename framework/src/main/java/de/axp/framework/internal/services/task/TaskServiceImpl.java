package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

class TaskServiceImpl implements TaskService {

	private final MainLoop mainLoop;
	private final ServiceRegistry serviceRegistry;
	private final PluginRegistry pluginRegistry;

	private TaskPromiseNotifier promiseNotifier;

	TaskServiceImpl(MainLoop mainLoop, ServiceRegistry serviceRegistry, PluginRegistry pluginRegistry) {
		this.mainLoop = mainLoop;
		this.serviceRegistry = serviceRegistry;
		this.pluginRegistry = pluginRegistry;

		TaskHandlerNotifier handlerNotifier = new TaskHandlerNotifier(mainLoop, pluginRegistry);
		promiseNotifier = new TaskPromiseNotifier();
		mainLoop.addListeners(handlerNotifier, promiseNotifier);
	}

	@Override
	public void triggerTask(Task task, TaskPromise promise) {
		promiseNotifier.registerPromise(task.getTaskId(), promise);
		mainLoop.addInput(new MainLoopPackage(task));
	}
}
