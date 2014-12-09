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

	public String toHome(){
		return "/home";
	}
	public String redirectToHome(){
		return "/home?faces-redirect=true";
	}

	public String toLoggedin(){
		return "/restricted/loggedin";
	}
	public String redirectToLoggedin(){
		return "/restricted/loggedin?faces-redirect=true";
	}
	
	public String toSearchPatient(){
		return "/restrictedfordoctor/searchPatient";
	}
	public String redirectToSearchPatient(){
		return "/restrictedfordoctor/searchPatient?faces-redirect=true";
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
		return "/public/public";
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
	
	public String toShowQuestionnaire(){
		return "/restrictedfordoctor/showQuestionnari";
	}
	public String redirectToShowQuestionnaire(){
		return "/restrictedfordoctor/showQuestionnari?faces-redirect=true";
	}
	
	public String toEditQuestionnaire(){
		return "/restrictedfordoctor/editQuestionnari";
	}
	public String redirectToEditQuestionnaire(){
		return "/restrictedfordoctor/editQuestionnari?faces-redirect=true";
	}
	
	public String toModifyGroup(){
		return "/restrictedfordoctor/modifyGroup";
	}
	public String redirectToModifyGroup(){
		return "/restrictedfordoctor/modifyGroup?faces-redirect=true";
	}
	
	public String toNewQuestionnaireTemplate(){
		return "/restricted/newTemplate";
	}
	public String redirectToNewQuestionnaireTemplate(){
		return "/restricted/newTemplate?faces-redirect=true";
	}
	
	public String toEditQuestionnaireTemplate(){
		return "/restricted/editTemplate";
	}
	public String redirectToEditQuestionnaireTemplate(){
		return "/restricted/editTemplate?faces-redirect=true";
	}
	
	public String toEditFilledQuestionnaire(){
		return "/restrictedfordoctor/editTemplate";
	}
	public String redirectToEditFilledQuestionnaire(){
		return "/restrictedfordoctor/editTemplate?faces-redirect=true";
	}	
	
	public String toNewData(){
		return "/restricted/downloads";
	}
	public String redirectToNewData(){
		return "/restricted/downloads?faces-redirect=true";
	}
	
	public String toRenewGroupKey(){
		return "/restrictedfordoctor/renewGroupKey";
	}
	public String redirectToRenewGroupKey(){
		return "/restrictedfordoctor/renewGroupKey?faces-redirect=true";
	}
}
