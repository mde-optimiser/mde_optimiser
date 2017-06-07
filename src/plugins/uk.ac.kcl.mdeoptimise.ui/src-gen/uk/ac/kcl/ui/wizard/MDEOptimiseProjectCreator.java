/*
 * generated by Xtext 2.10.0
 */
package uk.ac.kcl.ui.wizard;

import org.eclipse.xtext.ui.wizard.AbstractProjectCreator;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IFileSystemAccess2;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.ui.util.ProjectFactory;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class MDEOptimiseProjectCreator extends AbstractProjectCreator {
	protected static final String DSL_PROJECT_NAME = "uk.ac.kcl";

	@Inject
	private MDEOptimiseNewProjectWizardInitialContents initialContents;

	@Inject
	private Provider<EclipseResourceFileSystemAccess2> fileSystemAccessProvider;

	@Inject
	private IOutputConfigurationProvider outputConfigurationProvider;

	@Inject
	private Provider<ProjectFactory> projectFactoryProvider;
	
	@Override
	protected ProjectFactory createProjectFactory() {
		return projectFactoryProvider.get();
	}

	@Override
	protected MDEOptimiseProjectInfo getProjectInfo() {
		return (MDEOptimiseProjectInfo) super.getProjectInfo();
	}

	@Override
	protected String getModelFolderName() {
		return "src";
	}

	@Override
	protected List<String> getAllFolders() {
		Set<OutputConfiguration> outputConfigurations = outputConfigurationProvider.getOutputConfigurations();
		String outputFolder = "src-gen";
		for (OutputConfiguration outputConfiguration : outputConfigurations) {
			if (IFileSystemAccess.DEFAULT_OUTPUT.equals(outputConfiguration.getName())) {
				outputFolder = outputConfiguration.getOutputDirectory();
				break;
			}
		}
		return ImmutableList.of(getModelFolderName(), outputFolder);
	}

	@Override
	protected void enhanceProject(final IProject project, final IProgressMonitor monitor) throws CoreException {
		IFileSystemAccess2 access = getFileSystemAccess(project, monitor);
		initialContents.generateInitialContents(access);
		project.refreshLocal(IResource.DEPTH_INFINITE, monitor);
	}

	protected IFileSystemAccess2 getFileSystemAccess(final IProject project, final IProgressMonitor monitor) {
		EclipseResourceFileSystemAccess2 access = fileSystemAccessProvider.get();
		access.setContext(project);
		access.setMonitor(monitor);
		OutputConfiguration defaultOutput = new OutputConfiguration(IFileSystemAccess.DEFAULT_OUTPUT);
		defaultOutput.setDescription("Output Folder");
		defaultOutput.setOutputDirectory("./");
		defaultOutput.setOverrideExistingResources(true);
		defaultOutput.setCreateOutputDirectory(true);
		defaultOutput.setCleanUpDerivedResources(false);
		defaultOutput.setSetDerivedProperty(false);
		defaultOutput.setKeepLocalHistory(false);
		HashMap<String, OutputConfiguration> outputConfigurations = new HashMap<String, OutputConfiguration>();
		outputConfigurations.put(IFileSystemAccess.DEFAULT_OUTPUT, defaultOutput);
		access.setOutputConfigurations(outputConfigurations);
		return access;
	}
}
