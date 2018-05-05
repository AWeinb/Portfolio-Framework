package de.axp.portfolio.framework.internal.mainloop;

import de.axp.portfolio.framework.api.FrameworkException;

public class MainLoopBufferException extends FrameworkException {

	public MainLoopBufferException(Throwable e) {
		addSuppressed(e);
	}
}
