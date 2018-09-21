package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.task.mainloop.MainLoop;
import de.axp.framework.internal.services.task.mainloop.MainLoopPackage;

class TaskServiceImpl implements TaskService {

	private final ServiceService serviceService;
	private final MainLoop mainLoop;

	private TaskPromiseNotifier promiseNotifier;

	TaskServiceImpl(ServiceService serviceService) {
		this.serviceService = serviceService;
		mainLoop = new MainLoop();

		TaskHandlerNotifier handlerNotifier = new TaskHandlerNotifier(mainLoop, serviceService);
		promiseNotifier = new TaskPromiseNotifier();
		mainLoop.addListeners(handlerNotifier, promiseNotifier);
	}

	@Override
	public void disposeService() {
		mainLoop.dispose();
	}

	@Override
	public void registerTaskHandler(TaskHandler handler) {
		PluginService pluginService = serviceService.getService(PluginService.class);
		pluginService.addPlugin(TaskHandler.class, handler);
	}

	@Override
	public void triggerTask(Task task, TaskPromise promise) {
		promiseNotifier.registerPromise(task, promise);
		mainLoop.addInput(new MainLoopPackage(task));
	}
}
