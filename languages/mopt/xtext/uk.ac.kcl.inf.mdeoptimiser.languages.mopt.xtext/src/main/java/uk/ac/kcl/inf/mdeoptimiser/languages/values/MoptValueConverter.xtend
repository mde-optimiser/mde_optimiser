package uk.ac.kcl.inf.mdeoptimiser.languages.values

import org.eclipse.xtext.common.services.DefaultTerminalConverters
import org.eclipse.xtext.conversion.IValueConverter
import org.eclipse.xtext.conversion.ValueConverter

class MoptValueConverter extends DefaultTerminalConverters {
	
	@ValueConverter(rule = "URL")
    def IValueConverter<String> getURLConverter() {
        return new URLConverterImplementation()
    }
}
