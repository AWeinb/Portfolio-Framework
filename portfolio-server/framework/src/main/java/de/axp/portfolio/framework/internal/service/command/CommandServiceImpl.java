package de.axp.portfolio.framework.internal.service.command;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

public class CommandServiceImpl implements MainLoop.MainLoopPlugin, CommandService {

	private final CommandHandler commandHandler;

	private MainLoop.MainLoopAccessor inputBufferAccessor;
	private CommandListener pluginInputListener;
	private ResponseListener pluginOutputListener;

	CommandServiceImpl(CommandHandler commandHandler) {
		this.commandHandler = commandHandler;
	}

	@Override
	public void initialize(MainLoop.MainLoopAccessor inputBufferAccessor,
	                       MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.inputBufferAccessor = inputBufferAccessor;

		pluginInputListener = new CommandListener(this.commandHandler, outputBufferAccessor);
		pluginOutputListener = new ResponseListener();
	}

	@Override
	public void dispose() {

	}

	@Override
	public MainLoop.MainLoopListener getInputListener() {
		return pluginInputListener;
	}

	@Override
	public MainLoop.MainLoopListener getOutputListener() {
		return pluginOutputListener;
	}

	@Override
	public void dispatchCommand(String sessionID, String packageID, Command commandPackage)
			throws InterruptedException {
		pluginOutputListener.registerPromise(sessionID, packageID, commandPackage.getPromise());

		MainLoopPackage mainLoopPackage = new MainLoopPackage(commandPackage);
		inputBufferAccessor.put(mainLoopPackage);
	}
}
