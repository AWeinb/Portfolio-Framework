package de.axp.framework.internal.services.data;

import de.axp.framework.api.extensions.DataHandler;
import de.axp.framework.internal.infrastructure.mainloop.MainLoop;
import de.axp.framework.internal.services.BaseFrameworkService;

public class BaseDataService implements MainLoop.MainLoopPlugin, BaseFrameworkService {

	private MainLoop.MainLoopAccessor inputBufferAccessor;

	public BaseDataService(MainLoop mainLoop) {
		mainLoop.addPlugin(this);
	}

	@Override
	public void initialize(MainLoop.MainLoopAccessor inputBufferAccessor,
	                       MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.inputBufferAccessor = inputBufferAccessor;
	}

	@Override
	public void dispose() {

	}

	@Override
	public MainLoop.MainLoopListener getInputListener() {
		return null;
	}

	@Override
	public MainLoop.MainLoopListener getOutputListener() {
		return null;
	}

	public void register(String sessionId, String contextId, DataHandler handler) {
	}
}
