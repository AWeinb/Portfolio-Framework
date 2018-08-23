package de.axp.framework.internal.services.task;

import de.axp.framework.api.extensions.TaskHandler;
import de.axp.framework.api.services.SessionService;
import de.axp.framework.api.services.TaskService;
import de.axp.framework.internal.infrastructure.plugin.PluginRegistry;
import de.axp.framework.internal.services.BaseServiceRegistry;
import de.axp.framework.internal.services.session.BaseSessionService;

import java.util.Set;

class TaskServiceImpl implements TaskService {

	private final BaseServiceRegistry serviceRegistry;
	private final PluginRegistry pluginRegistry;
	private final SessionService.FrameworkSession session;

	TaskServiceImpl(BaseServiceRegistry serviceRegistry, PluginRegistry pluginRegistry,
	                SessionService.FrameworkSession session) {
		this.serviceRegistry = serviceRegistry;
		this.pluginRegistry = pluginRegistry;
		this.session = session;

		addPredefinedTaskHandlers();
	}

	private void addPredefinedTaskHandlers() {
		BaseTaskService internalTaskService = serviceRegistry.getBaseService(BaseTaskService.class);
		Set<? extends TaskHandler> taskHandlers = pluginRegistry.getPluginsOfType(TaskHandler.class);
		for (TaskHandler taskHandler : taskHandlers) {
			internalTaskService.register(session.toString(), taskHandler.provideIdentifier(), taskHandler);
		}
	}

	@Override
	public void addTaskHandler(TaskHandler taskHandler) {
		BaseSessionService internalSessionService = serviceRegistry.getBaseService(BaseSessionService.class);
		internalSessionService.checkSession(session);

		BaseTaskService internalTaskService = serviceRegistry.getBaseService(BaseTaskService.class);
		internalTaskService.register(session.toString(), taskHandler.provideIdentifier(), taskHandler);
	}

	@Override
	public void triggerTask(String taskId, Object content, TaskPromise promise) {
		triggerTask("", taskId, content, promise);
	}

	@Override
	public void triggerTask(String contextId, String taskId, Object content, TaskPromise promise) {
		BaseSessionService internalSessionService = serviceRegistry.getBaseService(BaseSessionService.class);
		internalSessionService.checkSession(session);

		Task task = Task.build(contextId, taskId, content);
		BaseTaskService internalTaskService = serviceRegistry.getBaseService(BaseTaskService.class);
		internalTaskService.trigger(session.toString(), task, promise);
	}
}
