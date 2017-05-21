package de.axp.portfolio.framework.response;

import de.axp.portfolio.framework.ResponseManagement;

class ResponseManagementImpl implements ResponseManagement {

	static final String POISON = "POISON";

	private final ResponseBuffer responseBuffer;
	private final ResponseNotifier responseNotifier;

	private Thread responseWorkerThread;

	ResponseManagementImpl(ResponseBuffer responseBuffer, ResponseNotifier responseNotifier) {
		this.responseBuffer = responseBuffer;
		this.responseNotifier = responseNotifier;
	}

	private void initialize() {
		responseWorkerThread = new Thread(new ResponseWorker(responseBuffer, responseNotifier));
		responseWorkerThread.start();
	}

	private void dispose() {
		try {
			responseBuffer.putResponse(POISON);
			responseWorkerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
