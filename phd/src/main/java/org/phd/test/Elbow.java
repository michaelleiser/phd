package org.phd.test;

public class Elbow {

	private int id;
	private String frage1 = "Ellbogen Frage nr. 1";
	private String antwort1;
	private String frage2 = "Ellbogen zustand nr. 1";
	private String antwort2;
	
	public Elbow(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFrage1() {
		return frage1;
	}
	public void setFrage1(String frage1) {
		this.frage1 = frage1;
	}
	public String getAntwort1() {
		return antwort1;
	}
	public void setAntwort1(String antwort1) {
		this.antwort1 = antwort1;
	}
	public String getFrage2() {
		return frage2;
	}
	public void setFrage2(String frage2) {
		this.frage2 = frage2;
	}
	public String getAntwort2() {
		return antwort2;
	}
	public void setAntwort2(String antwort2) {
		this.antwort2 = antwort2;
	}
	
}
