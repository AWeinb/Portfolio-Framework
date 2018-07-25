package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface.EventListener;
import de.axp.portfolio.framework.internal.mainloop.MainLoop;
import de.axp.portfolio.framework.internal.mainloop.MainLoopPackage;

public class EventServiceImpl implements MainLoop.MainLoopPlugin, EventService {

	private MainLoop.MainLoopAccessor inputBufferAccessor;
	private ListenerNotifier listenerNotifier;
	private PromiseNotifier promiseNotifier;

	@Override
	public void initialize(MainLoop.MainLoopAccessor inputBufferAccessor,
	                       MainLoop.MainLoopAccessor outputBufferAccessor) {
		this.inputBufferAccessor = inputBufferAccessor;

		listenerNotifier = new ListenerNotifier(outputBufferAccessor);
		promiseNotifier = new PromiseNotifier();
	}

	@Override
	public void dispose() {

	}

	@Override
	public MainLoop.MainLoopListener getInputListener() {
		return listenerNotifier;
	}

	@Override
	public MainLoop.MainLoopListener getOutputListener() {
		return promiseNotifier;
	}

	@Override
	public void register(String sessionId, String context, EventListener listener) {
		listenerNotifier.addListener(sessionId, context, listener);
	}

	@Override
	public void dispatch(String sessionId, String context, Event event, FrameworkEventInterface.EventPromise promise) {
		promiseNotifier.registerPromise(sessionId, event.getId(), promise);
		inputBufferAccessor.put(new MainLoopPackage(sessionId, context, event, MainLoopPackage.STATE.Unknown));
	}
}
