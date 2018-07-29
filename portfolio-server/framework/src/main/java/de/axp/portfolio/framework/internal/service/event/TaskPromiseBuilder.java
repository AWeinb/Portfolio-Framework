package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.api.interfaces.TaskServiceInterface;

public class TaskPromiseBuilder {

	private FrameworkPromise.FutureCallback resolveCallback;
	private FrameworkPromise.FutureCallback rejectCallback;

	public TaskPromiseBuilder onResolution(FrameworkPromise.FutureCallback callback) {
		this.resolveCallback = callback;
		return this;
	}

	public TaskPromiseBuilder onRejection(FrameworkPromise.FutureCallback callback) {
		rejectCallback = callback;
		return this;
	}

	public TaskServiceInterface.TaskPromise build() {
		return new TaskPromiseImpl();
	}

	private class TaskPromiseImpl implements TaskServiceInterface.TaskPromise {

		private final FutureCallback resolveCallback;
		private final FutureCallback rejectCallback;

		TaskPromiseImpl() {
			resolveCallback = TaskPromiseBuilder.this.resolveCallback;
			rejectCallback = TaskPromiseBuilder.this.rejectCallback;
		}

		@Override
		public void on(TaskResult resolution, Object result) {
			switch (resolution) {
				case SUCCESS:
					if (resolveCallback != null) {
						resolveCallback.execute(result);
					}
					break;
				case REJECTION:
					if (rejectCallback != null) {
						rejectCallback.execute(result);
					}
					break;
			}
		}
	}
}
