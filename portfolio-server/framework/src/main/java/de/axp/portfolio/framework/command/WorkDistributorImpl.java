package de.axp.portfolio.framework.command;

public class WorkDistributorImpl implements WorkDistributor {

	private CommandBuffer commandBuffer;
	private ResponseBuffer responseBuffer;
	private ResponseNotifier responseNotifier;

	private Thread commandWorkerThread;
	private Thread responseWorkerThread;

	WorkDistributorImpl(CommandBuffer commandBuffer, ResponseBuffer responseBuffer, ResponseNotifier responseNotifier) {
		this.commandBuffer = commandBuffer;
		this.responseBuffer = responseBuffer;
		this.responseNotifier = responseNotifier;
	}

	@Override
	public void initWorkers() {
		if (isWorking()) {
			return;
		}

		commandWorkerThread = new Thread(new CommandWorker(commandBuffer, responseBuffer));
		commandWorkerThread.start();

		responseWorkerThread = new Thread(new ResponseWorker(responseBuffer, responseNotifier));
		responseWorkerThread.start();
	}

	@Override
	public void stopWorkers() {
		if (!isWorking()) {
			return;
		}

		try {
			commandBuffer.putCommand(POISON);
			responseBuffer.putResponse(POISON);
			commandWorkerThread.join();
			responseWorkerThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean isWorking() {
		return commandWorkerThread != null && responseWorkerThread != null && commandWorkerThread.isAlive() && responseWorkerThread
				.isAlive();
	}
}
