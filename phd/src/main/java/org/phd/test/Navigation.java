package org.phd.test;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "navigation", eager = true)
@SessionScoped
public class Navigation implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	public String toError(){
		return "/error";
	}
	public String redirectToError(){
		return "/error?faces-redirect=true";
	}
	public String toFormular_1(){
		return "/formular_1";
	}
	public String redirectToFormular_1(){
		return "/formular_1?faces-redirect=true";
	}
	public String toHome(){
		return "/home";
	}
	public String redirectToHome(){
		return "/home?faces-redirect=true";
	}
	public String toLoggedin(){
		return "/loggedin";
	}
	public String redirectToLoggedin(){
		return "/loggedin?faces-redirect=true";
	}
	public String toNewpatient(){
		return "/newPatient";
	}
	public String redirectToNewpatient(){
		return "/newPatient?faces-redirect=true";
	}
	public String toPage2(){
		return "/page2";
	}
	public String redirectToPage2(){
		return "/page2?faces-redirect=true";
	}
	public String toPagefortesting(){
		return "/pagefortesting";
	}
	public String redirectToPagefortesting(){
		return "/pagefortesting?faces-redirect=true";
	}
	public String toRegisternew(){
		return "/registernew";
	}
	public String redirectToRegisternew(){
		return "/registernew?faces-redirect=true";
	}
	public String toPublic(){
		return "/puglic/public";
	}
	public String redirectToPublic(){
		return "/public/public?faces-redirect=true";
	}
	public String toRestricted(){
		return "/restricted/restricted";
	}
	public String redirectToRestricted(){
		return "/restricted/restricted?faces-redirect=true";
	}

}
