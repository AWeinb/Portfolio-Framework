package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;
import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface.TaskHandler;
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
	public void register(String sessionId, String context, TaskHandler handler) {
		handlerNotifier.addListener(sessionId, context, handler);
	}

	@Override
	public void trigger(String sessionId, String context, Task task, TaskServiceInterface.TaskPromise promise) {
		promiseNotifier.registerPromise(sessionId, task.getId(), promise);
		inputBufferAccessor.put(new MainLoopPackage(sessionId, context, task, MainLoopPackage.STATE.Unknown));
	}
}
