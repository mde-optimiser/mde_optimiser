
package uk.ac.kcl.values

import org.eclipse.xtext.conversion.IValueConverter
import org.eclipse.xtext.conversion.ValueConverterException
import org.eclipse.xtext.nodemodel.INode

/**
 * Value converter for URL strings. Removes leading and ending "<" ">" characters.
 * 
 * @author Steffen Zschaler
 */
class URLConverterImplementation implements IValueConverter<String> {
	
	override toString(String value) throws ValueConverterException {
		"<" + value + ">"
	}
	
	override toValue(String string, INode node) throws ValueConverterException {
		string.substring(1, string.length - 1)
	}
}
