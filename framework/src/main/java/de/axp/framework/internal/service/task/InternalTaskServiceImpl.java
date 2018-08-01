package de.axp.framework.internal.service.task;

import de.axp.framework.api.MainThreadSynchronization;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.api.services.TaskService.Task;
import de.axp.framework.api.services.TaskService.TaskHandler;
import de.axp.framework.internal.mainloop.MainLoop;
import de.axp.framework.internal.mainloop.MainLoopPackage;

class InternalTaskServiceImpl implements MainLoop.MainLoopPlugin, InternalTaskService {

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
	public void setMainThreadSynchronization(MainThreadSynchronization synchronization) {
		promiseNotifier.setMainThreadSynchronization(synchronization);
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
