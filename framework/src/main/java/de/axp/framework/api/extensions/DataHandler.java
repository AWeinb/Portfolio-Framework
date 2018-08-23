package de.axp.framework.api.extensions;

import de.axp.framework.internal.infrastructure.plugin.FrameworkPlugin;

public interface DataHandler extends FrameworkPlugin {

	boolean load();

	boolean save();
}
