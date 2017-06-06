package de.axp.portfolio.framework.internal.commands;

class ResponseWorker implements Runnable {

	static final ResponsePacket POISON = new ResponsePacket.ResponsePacketBuilder().build();

	private final ResponseBuffer responseBuffer;
	private final ResponseNotifier responseNotifier;

	ResponseWorker(ResponseBuffer responseBuffer, ResponseNotifier responseNotifier) {
		this.responseBuffer = responseBuffer;
		this.responseNotifier = responseNotifier;
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
		responseNotifier.notify(responsePacket);
	}
}
