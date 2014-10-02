package org.bfh.phd;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "languagecontrollertest", eager = true)
@SessionScoped							// Session oder Request Scoped
public class LanguageController {

	private static Map<String, Object> countries;

	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Deutsch", Locale.GERMAN);
		countries.put("Français", Locale.FRENCH);
	}
	
	public Map<String, Object> getCountries() {
		return this.countries;
	}
	
	public void setCountries(Map<String, Object> countries) {
		this.countries = countries;
	}
	
	public String changeLanguageEvent(ActionEvent evt){
		UIComponent comp = evt.getComponent();
		String value = (String) comp.getAttributes().get("value");
		Locale l = (Locale)countries.get(value);
		if(this.countries.containsValue(l)){
			FacesContext.getCurrentInstance().getViewRoot().setLocale(l);
		}
		return "";
	}

}
