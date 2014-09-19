package org.phd.test;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "languagecontroller", eager = true)
@RequestScoped							// Session oder Request Scoped
public class LanguageController {

	private static String locale;
	private static Map<String, Object> countries;

	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Deutsch", Locale.GERMAN);
		countries.put("Français", Locale.FRENCH);
	}

	public String getLocale() {
		return this.locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public Map<String, Object> getCountries() {
		return countries;
	}

	public void localeChanged(ValueChangeEvent e) {
		String newLocaleValue = e.getNewValue().toString();
		if(countries.containsValue(new Locale(newLocaleValue))){
			FacesContext.getCurrentInstance().getViewRoot()
			.setLocale(new Locale(newLocaleValue));
		}
	}
	
	@ManagedProperty(value = "#{param.lang}")
	private static String language;
	
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String bla(){
		if(countries.containsValue(new Locale(language))){
			FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(language));
		}
		return "";
	}

	
}
