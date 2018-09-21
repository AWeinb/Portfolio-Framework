package de.axp.framework.git;

import de.axp.framework.api.services.TaskService;

public class GitCommitMessageTaskHandler implements TaskService.TaskHandler {

	@Override
	public String handlerId() {
		return "de.axp.framework.git.git-commit-message";
	}

	@Override
	public void handle(TaskService.Task task, TaskService.TaskPromise promise) {
		Object content = task.getContent();

		if (!(content instanceof GitCommitMessageTaskInput)) {
			throw new IllegalArgumentException();
		}

		System.err.println(task);
	}
}
