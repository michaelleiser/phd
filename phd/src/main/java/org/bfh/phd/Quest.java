package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "quest", eager = true)
@RequestScoped
public class Quest implements Serializable {
	
	private static List<String> quests;
	private String questselected;
	
	static{
		quests = new ArrayList<String>();
		quests.add("elbow");
		quests.add("knee");
	}
	
	public List<String> getQuests(){
		return quests;
	}

	public void questChanged() {
		String value = questselected;
		System.out.println("Value " + value);
		questselected = value;
	}

	public String getQuestselected() {
		return questselected;
	}

	public void setQuestselected(String questselected) {
		this.questselected = questselected;
	}

}
