package org.bfh.phd;

import java.sql.Timestamp;
import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "quests", eager = true)
@RequestScoped
public class ListOfQuestionnaire {

	private int questId;
	private Timestamp date;
	private String typOfQuest;		// TODO name !!!
	
	public ListOfQuestionnaire(){
		
	}
	
	public int getQuestId() {
		return questId;
	}
	public void setQuestId(int questId) {
		this.questId = questId;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public String getTypOfQuest() {
		return typOfQuest;
	}

	public void setTypOfQuest(String typOfQuest) {
		this.typOfQuest = typOfQuest;
	}	
}