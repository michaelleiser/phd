package org.bfh.phd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;


@ManagedBean(name = "tools", eager = true)
@ViewScoped
public class Tools implements Serializable{


private String string = "";
private int i = 0;
private int a = 0;
private int questions = 1;

public void setString(String s){
	this.string = s;
}

public String getString(){
	return this.string;
}

public String getA(){
	i = a;
	return "";
}

public int getI(){
	i++;
	return i;
}

public int getQuestions() {
	return questions;
}

public void incQuestions() {
	this.questions++;
}

public void decQuestions(){
	this.questions--;
}


}