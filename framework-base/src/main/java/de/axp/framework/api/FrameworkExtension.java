package de.axp.framework.api;

public interface FrameworkExtension {

	void install(InstallationContext installationContext);

	interface InstallationContext {

		PortfolioFramework getFramework();
	}
}
