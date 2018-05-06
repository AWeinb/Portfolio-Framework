package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

public class EventServiceImpl implements MainLoop.MainLoopPlugin, EventService {

	private final EventConsumer eventConsumer;

	private MainLoop.MainLoopAccessor inputBufferAccessor;
	private EventServiceInputListener inputListener;
	private EventServiceResponseListener outputListener;

	EventServiceImpl(EventConsumer eventConsumer) {
		this.eventConsumer = eventConsumer;
	}

	@Override
	public void initialize(MainLoop.MainLoopAccessor inputBufferAccessor,
	                       MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.inputBufferAccessor = inputBufferAccessor;

		inputListener = new EventServiceInputListener(this.eventConsumer, outputBufferAccessor);
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
	public void dispatchEvent(Event event) throws InterruptedException {
		if (event.getPromise().isPresent()) {
			outputListener.registerPromise(event.getSessionID(), event.getPackageID(), event.getPromise().get());
		}

		MainLoopPackage mainLoopPackage = new MainLoopPackage(event);
		inputBufferAccessor.put(mainLoopPackage);
	}
}
