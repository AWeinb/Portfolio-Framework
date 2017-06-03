package de.axp.portfolio.framework.internal.commands;

import de.axp.portfolio.framework.FrameworkNotice;
import de.axp.portfolio.framework.FrameworkSessionInterface;

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

	interface ResponsePacket {

		FrameworkSessionInterface.FrameworkSession getFrameworkSession();

		FrameworkNotice.Message getCommand();

		FrameworkNotice.Message getResponse();

		boolean wasRejected();
	}

	static class PoisonedResponsePacket implements ResponsePacket {

		@Override
		public FrameworkSessionInterface.FrameworkSession getFrameworkSession() {
			return null;
		}

		@Override
		public FrameworkNotice.Message getCommand() {
			return null;
		}

		@Override
		public FrameworkNotice.Message getResponse() {
			return null;
		}

		@Override
		public boolean wasRejected() {
			return false;
		}
	}
}
