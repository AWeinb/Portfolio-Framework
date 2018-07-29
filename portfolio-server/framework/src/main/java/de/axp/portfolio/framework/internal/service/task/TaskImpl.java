package de.axp.portfolio.framework.internal.service.task;

class TaskImpl implements Task {

	private final String taskId;
	private final Object content;

	TaskImpl(String taskId, Object content) {
		this.taskId = taskId;
		this.content = content;
	}

	@Override
	public String getTaskId() {
		return taskId;
	}

	@Override
	public Object getContent() {
		return content;
	}
}
