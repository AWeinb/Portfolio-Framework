package de.axp.portfolio.framework.api.interfaces;

import de.axp.portfolio.framework.internal.service.ui.model.UFrame;

public interface FrameworkUiInterface {

	UFrame getCurrentFrame();

	void navigate(String uri);
}
