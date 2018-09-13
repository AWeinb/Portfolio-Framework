package de.axp.framework.internal.services.task;

import de.axp.framework.api.extensions.TaskHandler;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.ServiceRegistry;

class TaskServiceImpl implements MainLoop.MainLoopPlugin, TaskService {

	private final ServiceRegistry serviceRegistry;

	private MainLoop.MainLoopAccessor inputBufferAccessor;
	private TaskHandlerRegistry handlerRegistry;
	private TaskHandlerNotifier handlerNotifier;
	private TaskPromiseNotifier promiseNotifier;

	TaskServiceImpl(ServiceRegistry serviceRegistry, PluginRegistry pluginRegistry) {
		this.serviceRegistry = serviceRegistry;

		pluginRegistry.getPluginsOfType(TaskHandler.class).forEach(this::addTaskHandler);
	}

	@Override
	public void initialize(MainLoop.MainLoopAccessor inputBufferAccessor,
			MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.inputBufferAccessor = inputBufferAccessor;

		handlerRegistry = new TaskHandlerRegistry();
		handlerNotifier = new TaskHandlerNotifier(handlerRegistry, outputBufferAccessor);
		promiseNotifier = new TaskPromiseNotifier();
	}

	@Override
	public void dispose() {

	}

	@Override
	public MainLoop.MainLoopListener getInputListener() {
		return handlerNotifier;
	}

	@Override
	public MainLoop.MainLoopListener getOutputListener() {
		return promiseNotifier;
	}

	@Override
	public void addTaskHandler(TaskHandler taskHandler) {
		handlerRegistry.setHandler(taskHandler.provideIdentifier(), taskHandler);
	}

	@Override
	public void triggerTask(Task task, TaskPromise promise) {
		promiseNotifier.registerPromise(task.getTaskId(), promise);
		inputBufferAccessor.put(new MainLoopPackage(task));
	}
}
