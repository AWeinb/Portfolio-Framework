package de.axp.framework.internal.services.task;

import de.axp.framework.api.plugins.TaskHandler;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.internal.services.service.ServiceRegistry;
import de.axp.framework.internal.services.task.mainloop.MainLoop;
import de.axp.framework.internal.services.task.mainloop.MainLoopListener;
import de.axp.framework.internal.services.task.mainloop.MainLoopPackage;

import java.util.List;
import java.util.stream.Collectors;

import static de.axp.framework.api.services.TaskService.*;

class TaskHandlerNotifier implements MainLoopListener {

	private final MainLoop mainLoop;
	private final ServiceRegistry serviceRegistry;

	TaskHandlerNotifier(MainLoop mainLoop, ServiceRegistry serviceRegistry) {
		this.mainLoop = mainLoop;
		this.serviceRegistry = serviceRegistry;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		Task task = (Task) aPackage.getPayload();
		String taskId = task.getTaskId();
		String handlerId = task.getHandlerId();

		PluginService pluginService = serviceRegistry.getService(PluginService.class);
		List<TaskHandler> handlers = pluginService.getPlugins(TaskHandler.class).stream() //
				.filter(h -> h.pluginId().equals(handlerId)) //
				.collect(Collectors.toList());

		handlers.forEach(h -> h.handle(task, response -> mainLoop.addOutput(new MainLoopPackage(response))));

		if (handlers.isEmpty()) {
			TaskResponse taskResponse = TaskResponse.build(taskId, TaskResolution.UNHANDLED, null);
			mainLoop.addOutput(new MainLoopPackage(taskResponse));
		}
	}
}
