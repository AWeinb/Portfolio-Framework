package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.FrameworkPromise;
import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;

public class EventPromiseBuilder {

	private FrameworkPromise.FutureCallback resolveCallback;
	private FrameworkPromise.FutureCallback rejectCallback;

	public EventPromiseBuilder onResolution(FrameworkPromise.FutureCallback callback) {
		this.resolveCallback = callback;
		return this;
	}

	public EventPromiseBuilder onRejection(FrameworkPromise.FutureCallback callback) {
		rejectCallback = callback;
		return this;
	}

	public FrameworkEventInterface.EventPromise build() {
		return new EventPromiseImpl();
	}

	private class EventPromiseImpl implements FrameworkEventInterface.EventPromise {

		private final FutureCallback resolveCallback;
		private final FutureCallback rejectCallback;

		EventPromiseImpl() {
			resolveCallback = EventPromiseBuilder.this.resolveCallback;
			rejectCallback = EventPromiseBuilder.this.rejectCallback;
		}

		@Override
		public void on(EventPromiseResult resolution, Object result) {
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
