package org.bfh.phd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;


@ManagedBean(name = "tools", eager = true)
@SessionScoped	
public class Tools implements Serializable{


private String string = "";

public void setString(String s){
	this.string = s;
}

public String getString(){
	return this.string;
}

}