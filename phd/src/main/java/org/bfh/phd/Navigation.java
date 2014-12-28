package org.bfh.phd;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * The navigation class is used for navigating to the different web pages.
 * 
 * @author leism3, koblt1
 */
@ManagedBean(name = "navigation", eager = true)
@SessionScoped
public class Navigation implements Serializable {

	private static final long serialVersionUID = 1L;	
	
	// No restriction
	public String toError(){
		return "/error";
	}
	public String redirectToError(){
		return "/error?faces-redirect=true";
	}

	public String toHome(){
		return "/home";
	}
	public String redirectToHome(){
		return "/home?faces-redirect=true";
	}
	
	public String toRegisterNew(){
		return "/registerNew";
	}
	public String redirectToRegisterNew(){
		return "/registerNew?faces-redirect=true";
	}
	
	// Public
	public String toPublic(){
		return "/public/public";
	}
	public String redirectToPublic(){
		return "/public/public?faces-redirect=true";
	}
	
	// Restricted	
	public String toDownloads(){
		return "/restricted/downloads";
	}
	public String redirectToDownloads(){
		return "/restricted/downloads?faces-redirect=true";
	}
	
	public String toEditGroup(){
		return "/restricted/editGroup";
	}
	public String redirectToEditGroup(){
		return "/restricted/editGroup?faces-redirect=true";
	}
	
	public String toEditStaff(){
		return "/restricted/editStaff";
	}
	public String redirectToEditStaff(){
		return "/restricted/editStaff?faces-redirect=true";
	}
	
	public String toEditQuestionnaireTemplate(){
		return "/restricted/editTemplate";
	}
	public String redirectToEditQuestionnaireTemplate(){
		return "/restricted/editTemplate?faces-redirect=true";
	}

	public String toLoggedin(){
		return "/restricted/loggedin";
	}
	public String redirectToLoggedin(){
		return "/restricted/loggedin?faces-redirect=true";
	}

	public String toNewQuestionnaireTemplate(){
		return "/restricted/newTemplate";
	}
	public String redirectToNewQuestionnaireTemplate(){
		return "/restricted/newTemplate?faces-redirect=true";
	}
	
	public String toRestricted(){
		return "/restricted/restricted";
	}
	public String redirectToRestricted(){
		return "/restricted/restricted?faces-redirect=true";
	}
	
	public String toSearchPatientData(){
		return "/restricted/searchPatientData";
	}
	public String redirectToSearchPatientData(){
		return "/restricted/searchPatientData?faces-redirect=true";
	}
	
	
	
	// Restricted for doctor
	public String toEditPatient(){
		return "/restrictedfordoctor/editPatient";
	}
	public String redirectToEditPatient(){
		return "/restrictedfordoctor/editPatient?faces-redirect=true";
	}
	
	public String toEditQuestionnaire(){
		return "/restrictedfordoctor/editQuestionnaire";
	}
	public String redirectToEditQuestionnaire(){
		return "/restrictedfordoctor/editQuestionnaire?faces-redirect=true";
	}
	
	public String toFilledQuestionnaire(){
		return "/restrictedfordoctor/filledQuestionnaire";
	}
	public String redirectToFilledQuestionnaire(){
		return "/restrictedfordoctor/filledQuestionnaire?faces-redirect=true";
	}
	
	public String toNewPatient(){
		return "/restrictedfordoctor/newPatient";
	}
	public String redirectToNewPatient(){
		return "/restrictedfordoctor/newPatient?faces-redirect=true";
	}

	public String toNewQuestionnaire(){
		return "/restrictedfordoctor/newQuestionnaire";
	}
	public String redirectToNewQuestionnaire(){
		return "/restrictedfordoctor/newQuestionnaire?faces-redirect=true";
	}	
	
	public String toRenewGroupKey(){
		return "/restrictedfordoctor/renewGroupKey";
	}
	public String redirectToRenewGroupKey(){
		return "/restrictedfordoctor/renewGroupKey?faces-redirect=true";
	}
	
	public String toRestrictedForDoctor(){
		return "/restrictedfordoctor/restrictedfordoctor";
	}
	public String redirectToRestrictedForDoctor(){
		return "/restrictedfordoctor/restrictedfordoctor?faces-redirect=true";
	}
	
	public String toSearchPatient(){
		return "/restrictedfordoctor/searchPatient";
	}
	public String redirectToSearchPatient(){
		return "/restrictedfordoctor/searchPatient?faces-redirect=true";
	}

	// Restricted for statistician
	public String toRestrictedForStatistician(){
		return "/restrictedforstatistician/restrictedforstatistician";
	}
	public String redirectToRestrictedForStatistician(){
		return "/restrictedforstatistician/restrictedforstatistician?faces-redirect=true";
	}
	
}