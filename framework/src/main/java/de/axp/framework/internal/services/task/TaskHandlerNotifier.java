package de.axp.framework.internal.services.task;

import de.axp.framework.api.plugins.TaskHandler;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;

import java.util.List;
import java.util.stream.Collectors;

class TaskHandlerNotifier implements MainLoop.MainLoopListener {

	private final MainLoop mainLoop;
	private final PluginRegistry pluginRegistry;

	TaskHandlerNotifier(MainLoop mainLoop, PluginRegistry pluginRegistry) {
		this.mainLoop = mainLoop;
		this.pluginRegistry = pluginRegistry;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskService.Task task = (TaskService.Task) aPackage.getPayload();
		String contextId = task.getContextId();
		String taskId = task.getTaskId();

		List<TaskHandler> handlers = pluginRegistry.getPlugins(TaskHandler.class).stream() //
				.filter(h -> h.isRelevant(contextId, taskId)) //
				.collect(Collectors.toList());

		handlers.forEach(h -> h.handle(task, response -> mainLoop.addOutput(new MainLoopPackage(response))));

		if (handlers.isEmpty()) {
			TaskService.TaskResponse taskResponse = TaskService.TaskResponse.build(contextId, taskId,
					TaskService.TaskResolution.UNHANDLED, null);
			mainLoop.addOutput(new MainLoopPackage(taskResponse));
		}
	}
}
