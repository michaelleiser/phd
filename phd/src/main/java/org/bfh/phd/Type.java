package org.bfh.phd;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

/**
 * @author leism3, koblt1
 *
 */
@ManagedBean(name = "type", eager = true)
@RequestScoped
public class Type implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Map<String, Integer> type;
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
		System.out.println("Type class are used");
		List<String> list = new ArrayList<String>();
		for(String s: type.keySet()){
			list.add(s);
		}
		return list;
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
