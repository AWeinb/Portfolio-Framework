package de.axp.portfolio.framework.internal.service.task;

import de.axp.portfolio.framework.api.UserSessionAccessor;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface;
import de.axp.portfolio.framework.api.serviceinterfaces.TaskServiceInterface.TaskHandler;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

public class TaskServiceImpl implements MainLoop.MainLoopPlugin, TaskService {

	private MainLoop.MainLoopAccessor inputBufferAccessor;
	private TaskHandlerNotifier handlerNotifier;
	private TaskPromiseNotifier promiseNotifier;

	@Override
	public void initialize(MainLoop.MainLoopAccessor inputBufferAccessor,
	                       MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.inputBufferAccessor = inputBufferAccessor;

		handlerNotifier = new TaskHandlerNotifier(outputBufferAccessor);
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
	public void setUserSessionAccessor(UserSessionAccessor accessor) {
		promiseNotifier.setUserSessionAccessor(accessor);
	}

	@Override
	public void register(String sessionId, String contextId, TaskHandler handler) {
		handlerNotifier.addListener(sessionId, contextId, handler);
	}

	@Override
	public void trigger(String sessionId, String contextId, Task task, TaskServiceInterface.TaskPromise promise) {
		promiseNotifier.registerPromise(sessionId, contextId, task.getTaskId(), promise);
		inputBufferAccessor.put(new MainLoopPackage(sessionId, contextId, task));
	}
}
