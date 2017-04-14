package de.axp.portfolio.framework.command;

import de.axp.portfolio.framework.FrameworkCommandInterface;

class FrameworkCommandInterfaceImpl implements FrameworkCommandInterface {

	private final CommandFoo commandFoo;
	private final ResponseFoo responseFoo;
	private final WorkerFoo workerFoo;
	private Thread worker;

	FrameworkCommandInterfaceImpl(CommandFoo commandFoo, ResponseFoo responseFoo, WorkerFoo workerFoo) {
		this.commandFoo = commandFoo;
		this.responseFoo = responseFoo;
		this.workerFoo = workerFoo;
	}

	@Override
	public void deinitialize() {
		System.err.println("deinitialize");
		worker.interrupt();
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.err.println("w " + worker.isAlive());
	}

	@Override
	public void putCommand(String command) throws InterruptedException {
		commandFoo.putCommand(command);

		if (worker == null) {
			worker = new Thread(workerFoo);
			worker.start();
		}
	}

	@Override

	public void addResponseListener(FrameworkResponseListener responseListener) {
		System.err.println("addResponseListener");
		responseFoo.addResponseListener(responseListener);
	}
}
