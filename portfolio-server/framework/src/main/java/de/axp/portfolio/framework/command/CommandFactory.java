package de.axp.portfolio.framework.command;

public class CommandFactory {

	public static FrameworkCommandInterfaceImpl createInterfaceImpl() {
		CommandFoo commandFoo = new CommandFoo();
		ResponseFoo responseFoo = new ResponseFoo();
		WorkerFoo workerFoo = new WorkerFoo(commandFoo, responseFoo);
		return new FrameworkCommandInterfaceImpl(commandFoo, responseFoo, workerFoo);
	}
}
