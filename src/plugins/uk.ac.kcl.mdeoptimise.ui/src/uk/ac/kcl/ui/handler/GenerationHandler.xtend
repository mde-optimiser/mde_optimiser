package uk.ac.kcl.ui.handler

import org.eclipse.core.commands.AbstractHandler
import org.eclipse.core.commands.ExecutionEvent
import org.eclipse.core.commands.ExecutionException

class GenerationHandler extends AbstractHandler {
	
	override execute(ExecutionEvent arg0) throws ExecutionException {
		
		if(arg0 != null){
			println("Test")
		}
		
		println("Executed 1234!");
	}
		
}