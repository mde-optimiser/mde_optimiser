package uk.ac.kcl.ui.launch

import org.eclipse.debug.ui.ILaunchShortcut
import org.eclipse.jface.viewers.ISelection
import org.eclipse.ui.IEditorPart
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.core.resources.IFile
import org.eclipse.core.runtime.Platform
import org.eclipse.core.runtime.IAdaptable
import com.google.inject.Injector
import uk.ac.kcl.MDEOptimiseStandaloneSetup
import uk.ac.kcl.ui.launch.RunOptimisation

class MDEOLaunchConfigurationShortcut implements ILaunchShortcut {
	
	static val Injector injector = new MDEOptimiseStandaloneSetup().createInjectorAndDoEMFRegistration();
	
	override launch(ISelection selection, String mode) {
		println("Selection: " + mode)
		
		
		if(selection instanceof IStructuredSelection){
						
			val app = injector.getInstance(RunOptimisation)
			
			var treeSelection = selection as IStructuredSelection
			
			
			
			var treeSelectionList = treeSelection.firstElement
			
			
			var file = Platform.getAdapterManager().getAdapter(treeSelectionList, IFile) as IFile
	        if (file == null) {
	            if (treeSelectionList instanceof IAdaptable) {
	                file = (treeSelectionList as IAdaptable).getAdapter(IFile) as IFile
	            }
	        }
	        if (file != null) {
	            // do something
	        }
			
			app.run(file)
			println()
		}
	
	}
	
	override launch(IEditorPart editor, String mode) {
		println("Editor" + mode)
	}


}