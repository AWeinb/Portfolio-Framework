package de.axp.portfolio.framework.command;

public class WorkDistributorImpl implements WorkDistributor {

	private CommandBuffer commandBuffer;
	private ResponseBuffer responseBuffer;
	private ResponseNotifier responseNotifier;

	private Thread commandWorkerThread;
	private Thread responseWorkerThread;

	public WorkDistributorImpl(CommandBuffer commandBuffer, ResponseBuffer responseBuffer,
			ResponseNotifier responseNotifier) {
		this.commandBuffer = commandBuffer;
		this.responseBuffer = responseBuffer;
		this.responseNotifier = responseNotifier;
	}

	@Override
	public void initWorkers() {
		commandWorkerThread = new Thread(new CommandWorker(commandBuffer, responseBuffer));
		commandWorkerThread.start();

		responseWorkerThread = new Thread(new ResponseWorker(responseBuffer, responseNotifier));
		responseWorkerThread.start();
	}

	@Override
	public void stopWorkers() {
		try {
			commandBuffer.putCommand(POISON);
			responseBuffer.putResponse(POISON);
			commandWorkerThread.join();
			responseWorkerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
