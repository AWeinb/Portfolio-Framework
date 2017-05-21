package de.axp.portfolio.framework.response;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ResponseBuffer {

	private final BlockingQueue<String> responses = new ArrayBlockingQueue<>(10);

	void putResponse(String response) throws InterruptedException {
		responses.put(response);
	}

	String getNextResponse() throws InterruptedException {
		return responses.take();
	}
}
