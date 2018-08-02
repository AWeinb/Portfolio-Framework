package de.axp.framework.internal.services.task;

import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TaskService.Task;
import de.axp.framework.api.services.TaskService.TaskHandler;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.infrastructure.mainloop.MainLoopPackage;

class BaseTaskServiceImpl implements MainLoop.MainLoopPlugin, BaseTaskService {

	private MainLoop.MainLoopAccessor inputBufferAccessor;
	private TaskHandlerRegistry handlerRegistry;
	private TaskHandlerNotifier handlerNotifier;
	private TaskPromiseNotifier promiseNotifier;

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
	public void register(String sessionId, String contextId, TaskHandler handler) {
		handlerRegistry.setHandler(sessionId, contextId, handler);
	}

	@Override
	public void trigger(String sessionId, Task task, TaskService.TaskPromise promise) {
		promiseNotifier.registerPromise(sessionId, task.getTaskId(), promise);
		inputBufferAccessor.put(new MainLoopPackage(sessionId, task));
	}
}
