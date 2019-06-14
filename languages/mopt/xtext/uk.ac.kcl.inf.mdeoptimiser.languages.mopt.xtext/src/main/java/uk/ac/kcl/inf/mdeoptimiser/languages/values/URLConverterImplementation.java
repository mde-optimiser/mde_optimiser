package uk.ac.kcl.inf.mdeoptimiser.languages.values;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;

/**
 * Value converter for URL strings. Removes leading and ending "<" ">" characters.
 * 
 * @author Steffen Zschaler
 */
public class URLConverterImplementation implements IValueConverter<String> {
	
	@Override
  public String toString(String value) throws ValueConverterException {
	  return "<" + value + ">";
	}
	
	@Override
  public String toValue(String string, INode node) throws ValueConverterException {
		return string.substring(1, string.length() - 1);
	}
}