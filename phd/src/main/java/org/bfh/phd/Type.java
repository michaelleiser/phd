package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "type", eager = true)
@RequestScoped
public class Type implements Serializable {
	
	private static List<String> type;
	private static String typeselected;
	private static List<Integer> y;
	private static int x;
	
	static{
		EntityManager em = new EntityManager();
		type = em.getType();
		y = new ArrayList<Integer>();
		for(int j = 1; j <= 10; j++){
			y.add(new Integer(j));
		}
	}
	
	public List<String> getTypes(){
		return type;
	}

	public String typeChanged(ActionEvent evt) {
		UIComponent comp = evt.getComponent();
		typeselected = (String) comp.getAttributes().get("value");
		System.out.println("Type: " + typeselected);
		return "";
	}

	public String getTypeselected() {
		return typeselected;
	}

	public void setTypeselected(String questselected) {
		this.typeselected = questselected;
	}
	
	public List<Integer> getI(){
		return y;
	}
	
	public String numberChanged(ActionEvent evt){
		UIComponent comp = evt.getComponent();
		x = Integer.getInteger((String) comp.getAttributes().get("value"));
		return "";
	}
	
	public int getNumberselected(){
		return x;
	}
	
	public void setNumberselected(int k){
		this.x = k;
	}

}
