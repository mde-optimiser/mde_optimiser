package uk.ac.kcl.ui.launch

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup
import org.eclipse.debug.ui.CommonTab
import org.eclipse.debug.ui.EnvironmentTab
import org.eclipse.debug.ui.ILaunchConfigurationDialog
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaClasspathTab
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaJRETab

class MDEOLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {
	
	override createTabs(ILaunchConfigurationDialog dialog, String mode) {
		val tabs = newArrayList(
			new MDEOptimiserSourceConfigurationTab,
			new JavaClasspathTab,
			new JavaJRETab,
			new EnvironmentTab,
			new CommonTab
		)
		setTabs(tabs)
	}
}