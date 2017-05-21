package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.CommandBuffer;
import de.axp.portfolio.framework.ResponseNotifier;
import de.axp.portfolio.framework.WorkDistributor;

public class WorkDistributorImpl implements WorkDistributor {

	private final CommandBuffer commandBuffer;
	private final ResponseBuffer responseBuffer;
	private final CommandNotifier commandNotifier;
	private final ResponseNotifier responseNotifier;

	private Thread commandWorkerThread;
	private Thread responseWorkerThread;

	WorkDistributorImpl(CommandBuffer commandBuffer, ResponseBuffer responseBuffer,
	                    CommandNotifier commandNotifier, ResponseNotifier responseNotifier) {
		this.commandBuffer = commandBuffer;
		this.responseBuffer = responseBuffer;
		this.commandNotifier = commandNotifier;
		this.responseNotifier = responseNotifier;
	}

	@Override
	public void initWorkers() {
		if (isWorking()) {
			return;
		}

		commandWorkerThread = new Thread(new CommandWorker(commandBuffer, commandNotifier));
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
