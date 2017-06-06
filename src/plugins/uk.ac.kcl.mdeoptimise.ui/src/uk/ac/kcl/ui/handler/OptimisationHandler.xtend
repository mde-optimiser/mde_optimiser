package uk.ac.kcl.ui.handler

import com.google.inject.Inject
import com.google.inject.Provider
import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException
import org.eclipse.core.commands.IHandler
import org.eclipse.core.resources.IFile
import org.eclipse.core.runtime.CoreException
import org.eclipse.core.runtime.NullProgressMonitor
import org.eclipse.emf.common.util.URI
import org.eclipse.jface.viewers.IStructuredSelection
import org.eclipse.ui.handlers.HandlerUtil
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2
import org.eclipse.xtext.generator.IGenerator2
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.xtext.ui.resource.IResourceSetProvider
import uk.ac.kcl.interpreter.OptimisationInterpreter
import uk.ac.kcl.mdeoptimise.Optimisation

class OptimisationHandler extends AbstractHandler implements IHandler {
	     
    @Inject
    private IGenerator2 generator;
 
    @Inject
    private Provider<EclipseResourceFileSystemAccess2> fileAccessProvider;
     
    @Inject
    IResourceDescriptions resourceDescriptions;
     
    @Inject
    IResourceSetProvider resourceSetProvider;
	
	
	override execute(ExecutionEvent arg0) throws ExecutionException {
		
		println("Generate on save has been ran")
	    var selection = HandlerUtil.getCurrentSelection(arg0);
        if (selection instanceof IStructuredSelection) {
            var structuredSelection = selection as IStructuredSelection;
            var firstElement = structuredSelection.getFirstElement();
            if (firstElement instanceof IFile) {
                var file = firstElement as IFile;
                var project = file.getProject();
                var srcGenFolder = project.getFolder("src-gen");
                if (!srcGenFolder.exists()) {
                    try {
                        srcGenFolder.create(true, true,
                                new NullProgressMonitor());
                    } catch (CoreException e) {
                        return null;
                    }
                }
 
                val fsa = fileAccessProvider.get();
                fsa.setOutputPath(srcGenFolder.getFullPath().toString());
                 
                val uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
                val rs = resourceSetProvider.get(project);
                val r = rs.getResource(uri, true);
                
                                
                val model = r.allContents.head as Optimisation;
                
                if(model != null){
                	
					new OptimisationInterpreter(model)
                	
                }
            }
        }
        return null;
	
	}
		
}