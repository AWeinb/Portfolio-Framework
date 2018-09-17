package de.axp.framework.internal.services.task;

import static de.axp.framework.api.services.TaskService.TaskResolution;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import de.axp.framework.api.plugins.TaskHandler;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.services.task.mainloop.MainLoop;
import de.axp.framework.internal.services.task.mainloop.MainLoopListener;
import de.axp.framework.internal.services.task.mainloop.MainLoopPackage;

class TaskHandlerNotifier implements MainLoopListener {

	private final MainLoop mainLoop;
	private final ServiceService serviceService;

	TaskHandlerNotifier(MainLoop mainLoop, ServiceService serviceService) {
		this.mainLoop = mainLoop;
		this.serviceService = serviceService;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskImpl task = (TaskImpl) aPackage.getPayload();
		String handlerId = task.target();

		PluginService pluginService = serviceService.getService(PluginService.class);
		List<TaskHandler> handlers = pluginService.getPlugins(TaskHandler.class).stream() //
				.filter(h -> h.handlerId().equals(handlerId)) //
				.collect(Collectors.toList());

		AtomicBoolean isCalled = new AtomicBoolean(false);
		triggerHandlers(task, handlers, isCalled);
		if (!isCalled.get()) {
			respondWithFallback(task);
		}
	}

	private void triggerHandlers(TaskImpl task, List<TaskHandler> handlers, AtomicBoolean isCalled) {
		for (TaskHandler handler : handlers) {
			TaskService.TaskPromise taskPromise = response -> {
				((TaskResponseImpl) response).setTaskId(task.getTaskId());
				mainLoop.addOutput(new MainLoopPackage(response));
				isCalled.set(true);
			};
			handler.handle(task, taskPromise);
		}
	}

	private void respondWithFallback(TaskImpl task) {
		TaskResponseImpl response = new TaskResponseImpl(null, TaskResolution.UNHANDLED);
		response.setTaskId(task.getTaskId());
		mainLoop.addOutput(new MainLoopPackage(response));
	}
}
