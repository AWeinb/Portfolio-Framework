package de.axp.framework.internal.services.task;

import java.util.Optional;
import java.util.Set;

import de.axp.framework.api.plugins.TaskHandler;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;

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
		TaskService.TaskPromise callback = createAnswerPromise(taskId);

		if (taskHandler.isPresent()) {
			taskHandler.get().handle(task, callback);
		} else {
			callback.respond(TaskService.TaskResolution.UNHANDLED, task);
		}
	}

	private TaskService.TaskPromise createAnswerPromise(String taskId) {
		return (resolution, result) -> {
			TaskResult response = TaskResult.build(taskId, resolution, result);
			MainLoopPackage packedResponse = new MainLoopPackage(response);
			mainLoop.addOutput(packedResponse);
		};
	}
}
