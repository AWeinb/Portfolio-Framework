package de.axp.framework.api;

import de.axp.framework.api.services.*;
import de.axp.framework.internal.InternalFactory;

public interface PortfolioFramework {

	static PortfolioFramework createFramework() {
		return InternalFactory.createFramework();
	}

	<T extends FrameworkService> T getServiceByType(Class<T> type);

	ServiceService getServiceService();

	PluginService getPluginService();

	TaskService getTaskService();

	DataService getDataService();

	UiService getUiService();

	TranslationService getTranslationService();

	void shutdown();
}
