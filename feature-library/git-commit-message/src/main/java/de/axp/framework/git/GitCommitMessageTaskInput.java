package de.axp.framework.git;

import java.io.File;

public class GitCommitMessageTaskInput {

	private final File dotGitFile;
	private final String commitHash;

	public GitCommitMessageTaskInput(File dotGitFile, String commitHash) {
		this.dotGitFile = dotGitFile;
		this.commitHash = commitHash;
	}

	public String getCommitHash() {
		return commitHash;
	}

	public File getDotGitFile() {
		return dotGitFile;
	}
}
