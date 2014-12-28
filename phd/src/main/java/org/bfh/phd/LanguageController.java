package org.bfh.phd;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.bfh.phd.interfaces.ILanguageController;

/**
 * The language controller is used for implementing different kinds of languages.
 * 
 * @author leism3, koblt1
 */
@ManagedBean(name = "languagecontrollertest", eager = true)
@SessionScoped
public class LanguageController implements ILanguageController, Serializable {

	private static final long serialVersionUID = 1L;

	private static Map<String, Object> countries;

	static {
		countries = new LinkedHashMap<String, Object>();
		countries.put("English", Locale.ENGLISH);
		countries.put("Deutsch", Locale.GERMAN);
		countries.put("Français", Locale.FRENCH);
	}
	
	@Override
	public Map<String, Object> getCountries() {
		return this.countries;
	}
	
	@Override
	public void setCountries(Map<String, Object> countries) {
		this.countries = countries;
	}
	
	public String changeLanguageEvent(ActionEvent evt){
		UIComponent comp = evt.getComponent();
		String value = (String) comp.getAttributes().get("value");
		Locale l = (Locale)this.countries.get(value);
		if(this.countries.containsValue(l)){
			FacesContext.getCurrentInstance().getViewRoot().setLocale(l);
		}
		return "";
	}

}
