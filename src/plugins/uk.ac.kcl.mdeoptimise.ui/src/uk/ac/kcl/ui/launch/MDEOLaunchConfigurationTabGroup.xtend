package uk.ac.kcl.ui.launch

import org.eclipse.debug.ui.AbstractLaunchConfigurationTabGroup
import org.eclipse.debug.ui.ILaunchConfigurationDialog
import org.eclipse.debug.ui.sourcelookup.SourceLookupTab
import org.eclipse.debug.ui.EnvironmentTab
import org.eclipse.debug.ui.CommonTab
import uk.ac.kcl.ui.launch.MDEOptimiserSourceConfigurationTab

class MDEOLaunchConfigurationTabGroup extends AbstractLaunchConfigurationTabGroup {
	
	override createTabs(ILaunchConfigurationDialog dialog, String mode) {
		val tabs = newArrayList(
			new MDEOptimiserSourceConfigurationTab,
			new SourceLookupTab,
			new EnvironmentTab,
			new CommonTab
		)
		setTabs(tabs)
	}
	
}