package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.bfh.phd.questionary.Elbow;
import org.bfh.phd.questionary.Knee;

@ManagedBean(name = "quest", eager = true)
@RequestScoped
public class Quest implements Serializable {
	
	private static List<String> quests;
	private String questselected = "";
	
	static{
		quests = new ArrayList<String>();
		quests.add("elbow");
		quests.add("knee");
	}
	
	public List<String> getQuests(){
		return quests;
	}

	public String questChanged(ActionEvent evt) {
		UIComponent comp = evt.getComponent();
		questselected = (String) comp.getAttributes().get("value");
		System.out.println("QUEST: " + questselected);
		return "";
	}

	public String getQuestselected() {
		return questselected;
	}

	public void setQuestselected(String questselected) {
		this.questselected = questselected;
	}

}
