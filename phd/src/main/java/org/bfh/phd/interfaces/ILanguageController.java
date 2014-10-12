package org.bfh.phd.interfaces;

import java.util.Map;

/**
 * @author leism3, koblt1
 *
 */
public interface ILanguageController {

	/**
	 * @return
	 */
	public Map<String, Object> getCountries();

	/**
	 * @param countries
	 */
	public void setCountries(Map<String, Object> countries);

}
