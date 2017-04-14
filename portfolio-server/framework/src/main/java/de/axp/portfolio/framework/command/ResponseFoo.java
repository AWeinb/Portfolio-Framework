package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.FrameworkCommandInterface.FrameworkResponseListener;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ResponseFoo {

	private BlockingQueue<String> responses = new ArrayBlockingQueue<String>(10);
	private List<FrameworkResponseListener> frameworkResponseListeners;

	ResponseFoo() {
		this.frameworkResponseListeners = new LinkedList<>();
	}

	void putResponse(String response) throws InterruptedException {
		System.err.println("putResponse: " + response);
		responses.put(response);
	}

	void addResponseListener(FrameworkResponseListener responseListener) {
		System.err.println("addResponseListener");
		frameworkResponseListeners.add(responseListener);
	}

	void notifyListeners() {
		System.err.println("notifyListeners");

		while (!responses.isEmpty()) {
			String response = responses.poll();
			for (FrameworkResponseListener frameworkResponseListener : frameworkResponseListeners) {
				frameworkResponseListener.onResponse(response);
			}
		}
	}
}
