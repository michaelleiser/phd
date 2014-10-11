package org.bfh.phd;

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
		return "/restricted/formular_1";
	}
	public String redirectToFormular_1(){
		return "/restricted/formular_1?faces-redirect=true";
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
	public String toSearchPatient(){
		return "/restrictedfordoctor/searchPatient";
	}
	public String redirectToSearchPatient(){
		return "/restrictedfordoctor/searchPatient?faces-redirect=true";
	}
	public String toEditGroup(){
		return "/restrictedfordoctor/editGroup";
	}
	public String redirectToEditGroup(){
		return "/restrictedfordoctor/editGroup?faces-redirect=true";
	}
	public String toSearchPatientData(){
		return "/restricted/searchPatientData";
	}
	public String redirectToSearchPatientData(){
		return "/restricted/searchPatientData?faces-redirect=true";
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
	public String toRestrictedForDoctor(){
		return "/restrictedfordoctor/restrictedfordoctor";
	}
	public String redirectToRestrictedForDoctor(){
		return "/restrictedfordoctor/restrictedfordoctor?faces-redirect=true";
	}
	public String toRestrictedForStatistician(){
		return "/restrictedforstatistician/restrictedforstatistician";
	}
	public String redirectToRestrictedForStatistician(){
		return "/restrictedforstatistician/restrictedforstatistician?faces-redirect=true";
	}
	public String toNewPatient(){
		return "/restrictedfordoctor/newPatient";
	}
	public String redirectToNewPatient(){
		return "/restrictedfordoctor/newPatient?faces-redirect=true";
	}
	public String toEditPatient(){
		return "/restrictedfordoctor/editPatient";
	}
	public String redirectToEditPatient(){
		return "/restrictedfordoctor/editPatient?faces-redirect=true";
	}
	public String toEditStaff(){
		return "/restricted/editStaff";
	}
	public String redirectToEditStaff(){
		return "/restricted/editStaff?faces-redirect=true";
	}
	public String toFormular(){
		return "/restricted/formular_1";
	}
	public String redirectToFormular(){
		return "/restricted/formular_1?faces-redirect=true";
	}
	public String toShowQuestionnari(){
		return "/restrictedfordoctor/showQuestionnari";
	}
	public String toEditQuestionnari(){
		return "/restrictedfordoctor/editQuestionnari";
	}
}
