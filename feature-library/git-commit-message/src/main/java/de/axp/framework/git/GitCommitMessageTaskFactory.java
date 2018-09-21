package de.axp.framework.git;

import java.io.File;

import de.axp.framework.api.services.TaskService;

public final class GitCommitMessageTaskFactory {

	private GitCommitMessageTaskFactory() {
	}

	public static TaskService.Task create(File dotGitFile, String commitHash) {
		return new TaskService.Task() {

			@Override
			public String target() {
				return "de.axp.framework.git.git-commit-message";
			}

			@Override
			public Object getContent() {
				return new GitCommitMessageTaskInput(dotGitFile, commitHash);
			}
		};
	}
}
