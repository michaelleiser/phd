package org.phd.test;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "quest", eager = true)
@SessionScoped
public class Quest implements Serializable {
	
	private static Map<String, Object> quests;
	
	static{
		quests = new LinkedHashMap<String, Object>();
		quests.put("elbow", new Knee());
		quests.put("knee", new Elbow());
	}
	
	public Map<String, Object> getQuests(){
		return quests;
	}

	public void questChanged() {
		// TODO
	}
	
}
