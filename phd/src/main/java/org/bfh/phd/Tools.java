package org.bfh.phd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;


@ManagedBean(name = "tools", eager = true)
@ViewScoped
public class Tools implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String questselected = ""; 

	public void questChanged(ActionEvent evt) {
		UIComponent comp = evt.getComponent();
		questselected = (String) comp.getAttributes().get("value");
	}

	public String getQuestselected() {
		return questselected;
	}

	public void setQuestselected(String questselected) {
		this.questselected = questselected;
	}
}