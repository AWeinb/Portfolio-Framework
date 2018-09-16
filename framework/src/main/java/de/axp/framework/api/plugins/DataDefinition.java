package de.axp.framework.api.plugins;

import de.axp.framework.api.FrameworkPlugin;
import de.axp.framework.api.services.TaskService;

public interface DataDefinition extends FrameworkPlugin {

	String dataId();

	TaskService.Task getLoadTask();

	TaskService.Task getSaveTask();

}
