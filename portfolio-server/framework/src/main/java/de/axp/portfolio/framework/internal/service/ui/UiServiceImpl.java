package de.axp.portfolio.framework.internal.service.ui;

import de.axp.portfolio.framework.internal.service.ui.model.UFrame;

class UiServiceImpl implements UiService {

	private final UiChangeHandler uiChangeHandler;

	private UFrame uFrame;

	UiServiceImpl(UiChangeHandler uiChangeHandler) {
		this.uiChangeHandler = uiChangeHandler;

		uFrame = new UFrame();
	}

	@Override
	public void dispose() {

	}

	@Override
	public UFrame getCurrentFrame(String sessionID) {
		return uFrame;
	}

	@Override
	public void navigate(String sessionID, String uri) {
		uiChangeHandler.onFrameChange(sessionID, uFrame);
	}
}
