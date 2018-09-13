package de.axp.framework.internal.services.task;

import de.axp.framework.api.extensions.TaskHandler;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

class TaskServiceImpl implements TaskService {

	private final MainLoop mainLoop;
	private final ServiceRegistry serviceRegistry;
	private TaskHandlerRegistry handlerRegistry;
	private TaskPromiseNotifier promiseNotifier;

	TaskServiceImpl(MainLoop mainLoop, ServiceRegistry serviceRegistry, PluginRegistry pluginRegistry) {
		this.mainLoop = mainLoop;
		this.serviceRegistry = serviceRegistry;

		handlerRegistry = new TaskHandlerRegistry();
		TaskHandlerNotifier handlerNotifier = new TaskHandlerNotifier(mainLoop, handlerRegistry);
		promiseNotifier = new TaskPromiseNotifier();
		mainLoop.addListeners(handlerNotifier, promiseNotifier);
		pluginRegistry.getPluginsOfType(TaskHandler.class).forEach(this::addTaskHandler);
	}

	@Override
	public void addTaskHandler(TaskHandler taskHandler) {
		handlerRegistry.setHandler(taskHandler.provideIdentifier(), taskHandler);
	}

	@Override
	public void triggerTask(Task task, TaskPromise promise) {
		promiseNotifier.registerPromise(task.getTaskId(), promise);
		mainLoop.addInput(new MainLoopPackage(task));
	}
}
