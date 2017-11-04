package de.axp.portfolio.framework.internal.service.ui;

import de.axp.portfolio.framework.api.interaction.FrameworkHandler;
import de.axp.portfolio.framework.internal.service.FrameworkService;
import de.axp.portfolio.framework.internal.service.ui.model.UFrame;

public interface UiService extends FrameworkService {

	UFrame getCurrentFrame(String sessionID);

	void navigate(String sessionID, String uri);

	interface UiChangeHandler extends FrameworkHandler {

		void onFrameChange(String sessionID, UFrame uFrame);
	}
}
