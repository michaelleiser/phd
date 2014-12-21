package org.bfh.phd.questionnaire;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.ValueChangeEvent;

public class QuestionnaireTools {
	private int id = 0;
	private int dbId = 0;
	private String question;
	private String type;
	private List<String> list = new ArrayList<String>();
	private boolean editable;
	private boolean isnew = false;
		
	public void setId(int i){
		id = i;
	}
	
	public void setDbId(int id){this.dbId = id;}
	public int getDbId(){return dbId;}	
	public void setQuestion(String question){this.question = question;}
	public String getQuestion(){return this.question;}
	public void setType(String type){this.type = type;}

	public String getType(){
		return this.type;
	}
	
	public void addPossibleAnswer(String answer){
		list.add(answer);
	}
	
	public List<String> getAnswer(){
		return list;
	}
	
	public String changeAnswer(ValueChangeEvent vce){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i).equals(vce.getOldValue())){
				list.set(i, vce.getNewValue().toString());
			}
		}
		return "";
	}
		
	public int getId(){
		return id;
	}
	
	public boolean isEditable(){
		return editable;
	}
	
	public void setEditable(boolean editable){
		this.editable = editable;
	}
	
	public void setIsNew(boolean isnew){
		this.isnew = isnew;
	}
	
	public boolean getIsNew(){
		return this.isnew;
	}
}
