package org.bfh.phd;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "quest", eager = true)
@RequestScoped
public class Quest implements Serializable {
	
	private static List<String> quests;
	private static String questselected;
	
	static {
		EntityManager em = new EntityManager();
		quests = em.getTemplateNames();
	}
	
	public List<String> getQuests(){
		return quests;
	}

	public String questChanged(ActionEvent evt) {
		System.out.println("EVENT"+evt);
		UIComponent comp = evt.getComponent();
		questselected = (String) comp.getAttributes().get("value");
		return "";
	}

	public String getQuestselected() {
		return questselected;
	}

	public void setQuestselected(String questselected) {
		this.questselected = questselected;
	}
}
