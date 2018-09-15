package de.axp.framework.api.plugins;

import de.axp.framework.api.FrameworkPlugin;

public interface DataHandler extends FrameworkPlugin {

	boolean load();

	boolean save();
}
