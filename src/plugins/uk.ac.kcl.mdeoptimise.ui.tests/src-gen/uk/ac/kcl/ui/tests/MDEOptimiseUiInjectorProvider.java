/*
 * generated by Xtext 2.12.0
 */
package uk.ac.kcl.ui.tests;

import com.google.inject.Injector;
import org.eclipse.xtext.testing.IInjectorProvider;
import uk.ac.kcl.mdeoptimise.ui.internal.MdeoptimiseActivator;

public class MDEOptimiseUiInjectorProvider implements IInjectorProvider {

	@Override
	public Injector getInjector() {
		return MdeoptimiseActivator.getInstance().getInjector("uk.ac.kcl.MDEOptimise");
	}

}
