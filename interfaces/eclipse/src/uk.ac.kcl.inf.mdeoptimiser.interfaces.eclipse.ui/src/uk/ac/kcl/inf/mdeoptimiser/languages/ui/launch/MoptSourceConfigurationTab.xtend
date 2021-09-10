package uk.ac.kcl.inf.mdeoptimiser.languages.ui.launch

import org.eclipse.core.resources.IFile
import org.eclipse.core.resources.IResource
import org.eclipse.core.resources.ResourcesPlugin
import org.eclipse.core.runtime.CoreException
import org.eclipse.debug.core.ILaunchConfiguration
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy
import org.eclipse.debug.ui.ILaunchConfigurationTab
import org.eclipse.jdt.debug.ui.launchConfigurations.JavaLaunchTab
import org.eclipse.jdt.launching.IJavaLaunchConfigurationConstants
import org.eclipse.jface.window.Window
import org.eclipse.swt.SWT
import org.eclipse.swt.events.ModifyEvent
import org.eclipse.swt.events.ModifyListener
import org.eclipse.swt.layout.FillLayout
import org.eclipse.swt.layout.GridData
import org.eclipse.swt.layout.GridLayout
import org.eclipse.swt.widgets.Button
import org.eclipse.swt.widgets.Composite
import org.eclipse.swt.widgets.Event
import org.eclipse.swt.widgets.Group
import org.eclipse.swt.widgets.Listener
import org.eclipse.swt.widgets.Shell
import org.eclipse.swt.widgets.Text
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog
import org.eclipse.jdt.core.JavaCore
import org.eclipse.core.runtime.Path

class MoptSourceConfigurationTab extends JavaLaunchTab implements ModifyListener {

    Text moptFilePath;
    IFile moptFile;

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
		moptFilePath = new Text(sourceGroup, SWT.BORDER);
		moptFilePath.setLayoutData(filePathData);
		moptFilePath.addModifyListener(this);
		
		createBrowseWorkspaceButton(sourceGroup, moptFilePath)
		
		var extras = new Composite(control, SWT.NONE);
		var extrasData = new GridData(GridData.FILL_BOTH);
		extrasData.horizontalSpan = 3;
		extras.setLayoutData(extrasData);
		
		control.setBounds(0, 0, 300, 300);
		control.layout();
		control.pack();
		
		canSave();
    }

	/**
	 * Called when displaying this configuration in launch configurations
	 */
    override initializeFrom(ILaunchConfiguration configuration) {
		try {
			
			moptFilePath.setText(configuration.getAttribute(getSourceAttributeName(), ""));
			setMoptFile(moptFilePath.getText())
			
			canSave();
			updateLaunchConfigurationDialog();
		
		} catch (CoreException e) {
			//Ignore
		}
    }

    override performApply(ILaunchConfigurationWorkingCopy configuration) {
		
		configuration.setAttribute(getSourceAttributeName(), moptFilePath.getText());
		
		if (this.moptFilePath !== null && 
			!this.moptFilePath.getText().empty
		) {	
					
			initializeJavaProject(JavaCore.create(this.moptFile.project), configuration);
		} else {
			configuration.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "");
		}
	}

	/* (non-Javadoc)
	 * @see ILaunchConfigurationTab#setDefaults(org.eclipse.debug.core.ILaunchConfigurationWorkingCopy)
	 */
	override void setDefaults(ILaunchConfigurationWorkingCopy config) {

		if (this.moptFilePath !== null) {			
			initializeJavaProject(JavaCore.create(this.moptFile.project), config);
		}
		else {
			config.setAttribute(IJavaLaunchConfigurationConstants.ATTR_PROJECT_NAME, "");
		}
	}

    override String getName() {
        return "MDEO Search Launch Tab";
    }
    
    override modifyText(ModifyEvent e) {
		canSave();
		updateLaunchConfigurationDialog();
	}
	
    private def Button createBrowseWorkspaceButton(Composite parent, Text target) {
    	
    	val button = new Button(parent, SWT.NONE)
    	
    	button.setText("Browse Workspace")
    	
    	button.addListener(SWT.Selection, new Listener(){
						
			override handleEvent(Event event) {
				//TODO The file extension must be coming from somewhere in the xtext configs surely
				var selectedMoptFilePath = browseFilePath(getShell(), "Select MOPT file", "MOPT files in the workspace", "mopt")
				
				if(selectedMoptFilePath !== null){ 
					target.setText(selectedMoptFilePath)
				}
			}
      	})
    	
    	return button
    }
    
    private def String browseFilePath(Shell shell, String title, String message, String ext) {
    	
    	var pattern = "";
    	
    	if(!ext.isEmpty) {
    		pattern = "*." + ext;
    	}
    	
    	var file = browseFile(shell, title, message, pattern)
    	
    	if(file !== null) {
    		this.moptFile = file;
    		return file.getFullPath.toString()
    	}
    	
    	return null
    }
    
    /**
     * File browser functionality to search for all the mopt files
     */
    private def IFile browseFile(Shell shell, String title, String message, String pattern) {
    	
    	var dialog = new FilteredResourcesSelectionDialog(shell, false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
    	
    	dialog.setInitialPattern(pattern, FilteredResourcesSelectionDialog.FULL_SELECTION)
    	dialog.setTitle(title)
    	dialog.setMessage(message)
    	
    	dialog.open()
    	
    	if(dialog.getReturnCode() == Window.OK) {
    		return dialog.getResult().head as IFile
    	}
    	
    	null
    }

	/**
	 * Sets the name of the source attribute used to fetch its value from
	 * the launch configuration properties collection.
	 */
	private def String getSourceAttributeName() {
		return MoptLaunchConfigurationAttributes.ATTR_MOPT_SOURCE_PATH;
	}
	
	private def void setMoptFile(String moptFilePath) {
		
		if(moptFilePath !== null && !moptFilePath.empty) {
			this.moptFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(moptFilePath));
		}
	}
}
