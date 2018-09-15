package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.service.ServiceRegistry;
import de.axp.framework.internal.services.task.mainloop.MainLoop;
import de.axp.framework.internal.services.task.mainloop.MainLoopPackage;

class TaskServiceImpl implements TaskService {

	private final MainLoop mainLoop;

	private TaskPromiseNotifier promiseNotifier;

	TaskServiceImpl(ServiceRegistry serviceRegistry) {
		mainLoop = new MainLoop();

		TaskHandlerNotifier handlerNotifier = new TaskHandlerNotifier(mainLoop, serviceRegistry);
		promiseNotifier = new TaskPromiseNotifier();
		mainLoop.addListeners(handlerNotifier, promiseNotifier);
	}

	@Override
	public void triggerTask(Task task, TaskPromise promise) {
		promiseNotifier.registerPromise(task.getTaskId(), promise);
		mainLoop.addInput(new MainLoopPackage(task));
	}
}
