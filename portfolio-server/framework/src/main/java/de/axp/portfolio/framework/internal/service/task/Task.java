package de.axp.portfolio.framework.internal.service.task;

public interface Task {

	static Task build(String taskId, Object content) {
		return new TaskImpl(taskId, content);
	}

	String getTaskId();

	Object getContent();

}
