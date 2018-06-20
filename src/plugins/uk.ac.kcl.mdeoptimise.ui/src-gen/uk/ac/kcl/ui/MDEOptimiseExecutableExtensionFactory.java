/*
 * generated by Xtext 2.12.0
 */
package uk.ac.kcl.ui;

import com.google.inject.Injector;
import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;
import uk.ac.kcl.mdeoptimise.ui.internal.MdeoptimiseActivator;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class MDEOptimiseExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return MdeoptimiseActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return MdeoptimiseActivator.getInstance().getInjector(MdeoptimiseActivator.UK_AC_KCL_MDEOPTIMISE);
	}
	
}
