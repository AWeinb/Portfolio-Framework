package de.axp.portfolio.framework.internal.mainloop.commands;

class ResponseWorker implements Runnable {

	static final ResponsePacket POISON = new ResponsePacket.ResponsePacketBuilder().build();

	private final ResponseBuffer responseBuffer;
	private final ResponseHandlerNotifier responseHandlerNotifier;

	ResponseWorker(ResponseBuffer responseBuffer, ResponseHandlerNotifier responseHandlerNotifier) {
		this.responseBuffer = responseBuffer;
		this.responseHandlerNotifier = responseHandlerNotifier;
	}

	@Override
	public void run() {
		boolean isRunning = true;
		while (isRunning) {
			ResponsePacket responsePacket;
			try {
				if ((responsePacket = responseBuffer.getNextResponse()) != null) {
					if (responsePacket == POISON) {
						isRunning = false;
					} else {
						handleResponse(responsePacket);
					}
				}
			} catch (InterruptedException e) {
				isRunning = false;
			}
		}
	}

	private void handleResponse(ResponsePacket responsePacket) {
		responseHandlerNotifier.notify(responsePacket);
	}
}
