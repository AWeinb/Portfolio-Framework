package de.axp.framework.internal.services.task;

import de.axp.framework.api.plugins.TaskHandler;
import de.axp.framework.api.services.PluginService;
import de.axp.framework.api.services.ServiceService;
import de.axp.framework.internal.services.task.mainloop.MainLoop;
import de.axp.framework.internal.services.task.mainloop.MainLoopListener;
import de.axp.framework.internal.services.task.mainloop.MainLoopPackage;

import java.util.List;
import java.util.stream.Collectors;

import static de.axp.framework.api.services.TaskService.*;

class TaskHandlerNotifier implements MainLoopListener {

	private final MainLoop mainLoop;
	private final ServiceService serviceService;

	TaskHandlerNotifier(MainLoop mainLoop, ServiceService serviceService) {
		this.mainLoop = mainLoop;
		this.serviceService = serviceService;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		Task task = (Task) aPackage.getPayload();
		String taskId = task.getTaskId();
		String handlerId = task.getHandlerId();

		PluginService pluginService = serviceService.getService(PluginService.class);
		List<TaskHandler> handlers = pluginService.getPlugins(TaskHandler.class).stream() //
				.filter(h -> h.handlerId().equals(handlerId)) //
				.collect(Collectors.toList());

		handlers.forEach(h -> h.handle(task, response -> mainLoop.addOutput(new MainLoopPackage(response))));

		if (handlers.isEmpty()) {
			TaskResponse taskResponse = TaskResponse.build(taskId, TaskResolution.UNHANDLED, null);
			mainLoop.addOutput(new MainLoopPackage(taskResponse));
		}
	}
}
