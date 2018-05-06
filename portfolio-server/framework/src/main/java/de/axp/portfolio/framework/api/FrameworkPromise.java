package de.axp.portfolio.framework.api;

public class FrameworkPromise {

	private FutureCallback resolve;
	private FutureCallback reject;

	public static FrameworkPromise whenResolved(FutureCallback callback) {
		FrameworkPromise frameworkPromise = new FrameworkPromise();
		frameworkPromise.resolve = callback;
		return frameworkPromise;
	}

	public static FrameworkPromise whenRejected(FutureCallback callback) {
		FrameworkPromise frameworkPromise = new FrameworkPromise();
		frameworkPromise.reject = callback;
		return frameworkPromise;
	}

	public FrameworkPromise orResolved(FutureCallback callback) {
		this.resolve = callback;
		return this;
	}

	public FrameworkPromise orRejected(FutureCallback callback) {
		this.reject = callback;
		return this;
	}

	public void resolve(Object future) {
		resolve.execute(future);
	}

	public void reject(Object future) {
		reject.execute(future);
	}

	public interface FutureCallback {

		void execute(Object future);
	}
}
