package org.bfh.phd;

import java.util.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "quests", eager = true)
@RequestScoped
public class ListOfQuestionnari {

	private int questId;
	private Date date;
	private String typOfQuest;
	
	public ListOfQuestionnari(){
		
	}
	
	public int getQuestId() {
		return questId;
	}
	public void setQuestId(int questId) {
		this.questId = questId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getTypOfQuest() {
		return typOfQuest;
	}

	public void setTypOfQuest(String typOfQuest) {
		this.typOfQuest = typOfQuest;
	}	
}