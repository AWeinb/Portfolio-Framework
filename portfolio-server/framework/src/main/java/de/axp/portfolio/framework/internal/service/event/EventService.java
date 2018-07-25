package de.axp.portfolio.framework.internal.service.event;

import de.axp.portfolio.framework.api.interfaces.FrameworkEventInterface;
import de.axp.portfolio.framework.internal.service.InternalFrameworkService;

public interface EventService extends InternalFrameworkService {

	void register(String sessionId, String context, FrameworkEventInterface.EventListener listener);

	void dispatch(String sessionId, String context, Event event, FrameworkEventInterface.EventPromise promise);
}
