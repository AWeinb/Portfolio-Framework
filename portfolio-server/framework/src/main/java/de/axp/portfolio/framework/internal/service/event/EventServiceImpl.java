package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

public class EventServiceImpl implements MainLoop.MainLoopPlugin, EventService {

	private MainLoop.MainLoopAccessor inputBufferAccessor;
	private EventServiceInputListener inputListener;
	private EventServiceResponseListener outputListener;

	@Override
	public void initialize(MainLoop.MainLoopAccessor inputBufferAccessor,
	                       MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.inputBufferAccessor = inputBufferAccessor;

		inputListener = new EventServiceInputListener(outputBufferAccessor);
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
	public void addEventConsumer(String sessionId, String context, EventConsumer eventConsumer) {
		inputListener.addEventConsumer(sessionId, context, eventConsumer);
	}

	@Override
	public void dispatchEvent(Event event) throws InterruptedException {
		if (event.getPromise().isPresent()) {
			outputListener.registerPromise(event.getSessionId(), event.getPackageId(), event.getPromise().get());
		}

		MainLoopPackage mainLoopPackage = new MainLoopPackage(event, MainLoopPackage.STATE.Unknown);
		inputBufferAccessor.put(mainLoopPackage);
	}
}
