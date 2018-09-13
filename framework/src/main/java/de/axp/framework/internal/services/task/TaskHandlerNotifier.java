package de.axp.framework.internal.services.task;

import de.axp.framework.api.plugins.TaskHandler;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;

import java.util.Optional;
import java.util.Set;

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

		Set<TaskHandler> taskHandlers = pluginRegistry.getPlugins(TaskHandler.class);
		Optional<TaskHandler> taskHandler = taskHandlers.stream() //
				.filter(h -> h.provideIdentifier().equals(contextId)) //
				.findFirst();

		if (taskHandler.isPresent()) {
			TaskService.TaskPromise callback = response -> mainLoop.addOutput(new MainLoopPackage(response));
			taskHandler.get().handle(task, callback);

		} else {
			TaskService.TaskResponse taskResponse = TaskService.TaskResponse.build(contextId, taskId,
					TaskService.TaskResolution.UNHANDLED, null);
			mainLoop.addOutput(new MainLoopPackage(taskResponse));
		}
	}
}
