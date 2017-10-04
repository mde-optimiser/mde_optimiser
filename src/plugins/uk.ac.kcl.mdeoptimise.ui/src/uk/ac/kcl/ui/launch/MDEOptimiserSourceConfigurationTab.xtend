package uk.ac.kcl.ui.launch

import org.eclipse.debug.ui.AbstractLaunchConfigurationTab
import org.eclipse.swt.widgets.Composite
import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy
import org.eclipse.swt.widgets.Text
import org.eclipse.jface.layout.GridLayoutFactory
import org.eclipse.jface.layout.GridDataFactory
import org.eclipse.core.runtime.CoreException
import org.eclipse.swt.widgets.Group
import org.eclipse.swt.widgets.Label
import org.eclipse.swt.SWT
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Listener
import org.eclipse.swt.widgets.Event
import org.eclipse.swt.widgets.Shell
import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.resources.IResource
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog
import org.eclipse.jface.window.Window
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.events.ModifyListener
import org.eclipse.swt.events.ModifyEvent

class MDEOptimiserSourceConfigurationTab extends AbstractLaunchConfigurationTab implements ModifyListener {

    Text filePath;

    override createControl(Composite parent) {

		var parentLayout = new FillLayout()
		
		parent.setLayout(parentLayout)
		
		var control = new Composite(parent, SWT.NONE)
		setControl(control)
		
		var controlLayout = new GridLayout(3, false);
		control.setLayout(controlLayout);
		
		val sourceGroup = new Group(control, SWT.SHADOW_ETCHED_IN)
		sourceGroup.setLayout(new GridLayout(2, false));
		sourceGroup.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		sourceGroup.setText("Source:");
		
		var filePathData = new GridData(GridData.FILL_HORIZONTAL);
		filePath = new Text(sourceGroup, SWT.BORDER);
		filePath.setLayoutData(filePathData);
		filePath.addModifyListener(this);
		
		createBrowseWorkspaceButton(sourceGroup, filePath)
		
		var extras = new Composite(control, SWT.NONE);
		var extrasData = new GridData(GridData.FILL_BOTH);
		extrasData.horizontalSpan = 3;
		extras.setLayoutData(extrasData);
		
		control.setBounds(0, 0, 300, 300);
		control.layout();
		control.pack();
		
		canSave();
    }

    @Override
    override setDefaults(ILaunchConfigurationWorkingCopy configuration) {
    }

    @Override
    override initializeFrom(ILaunchConfiguration configuration) {
			try {
				filePath.setText(configuration.getAttribute(getSourceAttributeName(), ""));
				canSave();
				updateLaunchConfigurationDialog();
			} catch (CoreException e) {
				//Ignore
			}
    }

    @Override
    override performApply(ILaunchConfigurationWorkingCopy configuration) {
			configuration.setAttribute(getSourceAttributeName(), filePath.getText());
		}

    @Override
    override String getName() {
        return "MDEO Search Launch Tab";
    }
    
    def String getImagePath(){
    	return "icons/mopt.gif"
    }
    
    def Button createBrowseWorkspaceButton(Composite parent, Text target) {
    	
    	val button = new Button(parent, SWT.NONE)
    	
    	button.setText("Browse Workspace")
    	
    	button.addListener(SWT.Selection, new Listener(){
						
			override handleEvent(Event event) {
				var selectedMoptFilePath = browseFilePath(getShell(), "Select MOPT file", "MOPT files in workspace", "mopt")
				
				if(selectedMoptFilePath != null) target.setText(selectedMoptFilePath)
				
			}
      	})
    	
    	return button
    	
    }
    
    def String browseFilePath(Shell shell, String title, String message, String ext) {
    	
    	var pattern = "";
    	
    	if(!ext.isEmpty) {
    		pattern = "*." + ext;
    	}
    	
    	var file = browseFile(shell, title, message, pattern)
    	
    	if(file != null) {
    		return file.getFullPath.toString()
    	}
    	
    	return null;
    }
    
    def IFile browseFile(Shell shell, String title, String message, String pattern) {
    	
    	var dialog = new FilteredResourcesSelectionDialog(shell, false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
    	
    	dialog.setInitialPattern(pattern, FilteredResourcesSelectionDialog.FULL_SELECTION)
    	dialog.setTitle(title)
    	dialog.setMessage(message)
    	
    	dialog.open()
    	
    	if(dialog.getReturnCode() == Window.OK) {
    		return dialog.getResult().get(0) as IFile
    	}
    	
    	null
    }
				
	override modifyText(ModifyEvent e) {
			canSave();
			updateLaunchConfigurationDialog();
	}

	def String getSourceAttributeName() {
		return "source";
	}

}