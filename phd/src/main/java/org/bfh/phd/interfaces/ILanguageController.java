package org.bfh.phd.interfaces;

import java.util.Map;

/**
 * @author leism3, koblt1
 *
 */
public interface ILanguageController {

	/**
	 * @return
	 * 			a map of country names and their locales
	 */
	public Map<String, Object> getCountries();

	/**
	 * @param countries
	 * 			a map of country names and their locales
	 */
	public void setCountries(Map<String, Object> countries);

}
