package org.bfh.phd;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

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

public String getLogPath(){
Properties prop = new Properties();
String propFile = "config.properties";
InputStream input = Patient.class.getClassLoader().getResourceAsStream(propFile);
try {
	prop.load(input);
} catch (IOException e1) {
	e1.printStackTrace();
}
String path = prop.getProperty("LOGPATH");
System.out.println(path);
return path;
}


























}