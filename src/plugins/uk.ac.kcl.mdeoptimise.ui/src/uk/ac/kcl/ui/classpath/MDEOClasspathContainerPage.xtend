package uk.ac.kcl.ui.classpath

import org.eclipse.jdt.core.IClasspathEntry
import org.eclipse.jdt.core.JavaCore
import org.eclipse.jdt.ui.wizards.IClasspathContainerPage
import org.eclipse.jface.resource.ImageDescriptor
import org.eclipse.jface.wizard.IWizard
import org.eclipse.jface.wizard.IWizardPage
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Label

class MDEOClasspathContainerPage implements IClasspathContainerPage {
	
	override finish() { true }
	
	override getSelection() {
		JavaCore.newContainerEntry(new MDEOClasspathContainer().path);
	}
	
	override setSelection(IClasspathEntry selection) { }
	
	override canFlipToNextPage() { false }
	
	override getName() '''MDEOptimiser DSL Libraries'''
	
	override getNextPage() { null }

	var IWizardPage previous = null
		
	override setPreviousPage(IWizardPage previous) {
		this.previous = previous
	}

	override getPreviousPage() { previous }
	
	var IWizard wizard = null
	
	override setWizard(IWizard wizard) {
		this.wizard = wizard
	}
	
	override getWizard() { wizard }
	
	override isPageComplete() { true }
	
	var Label control = null
	
	override createControl(Composite comp) {
		control = new Label(comp, SWT.WRAP)
		control.setText("The MDEOptimiser DSL Libraries container adds jars required for running searches with custom fitness functions in the DSL.")
	}
	
	override getControl() { control }
	
	override dispose() {
		control.dispose
	}
	
	override getDescription() '''Information about the MDEOptimiser DSL Libraries'''
	
	override getErrorMessage() { null }
	
	override getImage() { null }
	
	override getMessage() { null }
	
	override getTitle() '''MDEOptimiser DSL Libraries'''
	
	override performHelp() {}
	
	override setDescription(String description) {}
	
	override setImageDescriptor(ImageDescriptor image) {}
	
	override setTitle(String title) {}
	
	override setVisible(boolean visible) { }
}