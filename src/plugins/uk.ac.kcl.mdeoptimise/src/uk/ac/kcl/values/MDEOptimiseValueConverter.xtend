package uk.ac.kcl.values

import org.eclipse.xtext.common.services.DefaultTerminalConverters
import org.eclipse.xtext.conversion.IValueConverter
import org.eclipse.xtext.conversion.ValueConverter

class MDEOptimiseValueConverter extends DefaultTerminalConverters {
	
	@ValueConverter(rule = "URL")
    def IValueConverter<String> getURLConverter() {
        return new URLConverterImplementation()
    }
}