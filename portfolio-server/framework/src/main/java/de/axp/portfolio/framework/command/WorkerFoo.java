package de.axp.portfolio.framework.command;

class WorkerFoo implements Runnable {

	private final CommandFoo commandFoo;
	private final ResponseFoo responseFoo;

	WorkerFoo(CommandFoo commandFoo, ResponseFoo responseFoo) {
		this.commandFoo = commandFoo;
		this.responseFoo = responseFoo;
	}

	@Override
	public void run() {
		boolean work = true;
		while (work) {
			System.err.println("loop");
			try {
				String command = commandFoo.takeCommand();
				responseFoo.putResponse(command);
			} catch (InterruptedException e) {
				System.err.println("interrupted");
				work = false;
			}
			responseFoo.notifyListeners();
		}
	}
}
