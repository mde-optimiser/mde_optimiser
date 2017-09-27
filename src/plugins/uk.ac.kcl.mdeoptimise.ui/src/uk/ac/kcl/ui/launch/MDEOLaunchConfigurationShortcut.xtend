package uk.ac.kcl.ui.launch

import org.eclipse.debug.ui.ILaunchShortcut
import org.eclipse.jface.viewers.ISelection
import org.eclipse.ui.IEditorPart

class MDEOLaunchConfigurationShortcut implements ILaunchShortcut {
		
	override launch(ISelection selection, String mode) {
		println("Selection: " + mode)
	}
	
	override launch(IEditorPart editor, String mode) {
		println("Editor" + mode)
	}

}