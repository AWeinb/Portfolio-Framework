package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

public class EventServiceImpl implements MainLoop.MainLoopPlugin, EventService {

	private final EventHandler eventHandler;

	private MainLoop.MainLoopAccessor inputBufferAccessor;
	private EventServiceInputListener inputListener;
	private EventServiceResponseListener outputListener;

	EventServiceImpl(EventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	@Override
	public void initialize(MainLoop.MainLoopAccessor inputBufferAccessor,
	                       MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.inputBufferAccessor = inputBufferAccessor;

		inputListener = new EventServiceInputListener(this.eventHandler, outputBufferAccessor);
		outputListener = new EventServiceResponseListener();
	}

	@Override
	public void dispose() {

	}

	@Override
	public MainLoop.MainLoopListener getInputListener() {
		return inputListener;
	}

	@Override
	public MainLoop.MainLoopListener getOutputListener() {
		return outputListener;
	}

	@Override
	public void dispatchEvent(String sessionID, String packageID, Event event) throws InterruptedException {
		outputListener.registerPromise(sessionID, packageID, event.getPromise());

		MainLoopPackage mainLoopPackage = new MainLoopPackage(event);
		inputBufferAccessor.put(mainLoopPackage);
	}
}
