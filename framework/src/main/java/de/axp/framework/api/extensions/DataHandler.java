package de.axp.framework.api.extensions;

import de.axp.framework.internal.infrastructure.plugin.FrameworkPlugin;

public interface DataHandler extends FrameworkPlugin {

	String getId();

	boolean load();

	boolean validate();

	boolean save();
}
