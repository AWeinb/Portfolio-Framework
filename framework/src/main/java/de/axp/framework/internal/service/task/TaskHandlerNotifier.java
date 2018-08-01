package de.axp.framework.internal.service.task;

import de.axp.framework.api.services.TaskServiceInterface;
import de.axp.framework.api.services.TaskServiceInterface.TaskHandler;
import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.mainloop.MainLoopPackage;

class TaskHandlerNotifier implements MainLoop.MainLoopListener {

	private final TaskHandlerRegistry handlerRegistry;
	private final MainLoop.MainLoopAccessor outputBufferAccessor;

	TaskHandlerNotifier(TaskHandlerRegistry handlerRegistry, MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.handlerRegistry = handlerRegistry;
		this.outputBufferAccessor = outputBufferAccessor;
	}

	@Override
	public void notify(MainLoopPackage aPackage) {
		TaskServiceInterface.Task task = (TaskServiceInterface.Task) aPackage.getPayload();
		String sessionId = aPackage.getSessionId();
		String contextId = task.getContextId();
		String taskId = task.getTaskId();

		TaskHandler handler = handlerRegistry.getTaskHandler(sessionId, contextId);
		TaskServiceInterface.TaskPromise callback = createAnswerPromise(sessionId, taskId);

		if (handler != null) {
			handler.handle(task, callback);
		} else {
			callback.respond(TaskServiceInterface.TaskResolution.UNHANDLED, task);
		}
	}

	private TaskServiceInterface.TaskPromise createAnswerPromise(String sessionId, String taskId) {
		return (resolution, result) -> {
			TaskResult response = TaskResult.build(taskId, resolution, result);
			MainLoopPackage packedResponse = new MainLoopPackage(sessionId, response);
			outputBufferAccessor.put(packedResponse);
		};
	}
}
