package de.axp.portfolio.framework.internal.commands;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

class ResponseBuffer {

	private final BlockingQueue<ResponsePacket> responses = new ArrayBlockingQueue<>(10);

	void putResponse(ResponsePacket responsePacket) throws InterruptedException {
		responses.put(responsePacket);
	}

	ResponsePacket getNextResponse() throws InterruptedException {
		return responses.take();
	}
}
