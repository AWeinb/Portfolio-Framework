package de.axp.portfolio.framework.response;

import static de.axp.portfolio.framework.response.ResponseManagementImpl.POISON;

class ResponseWorker implements Runnable {

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
			String response;
			try {
				if ((response = responseBuffer.getNextResponse()) != null) {
					if (POISON.equals(response)) {
						isRunning = false;
					} else {
						handleResponse(response);
					}
				}
			} catch (InterruptedException e) {
				isRunning = false;
			}
		}
	}

	private void handleResponse(String response) {
	}
}
