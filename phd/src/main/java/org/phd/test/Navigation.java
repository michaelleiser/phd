package org.phd.test;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "navigation", eager = true)
@RequestScoped
public class Navigation implements Serializable {

	@ManagedProperty(value = "#{param.pageId}")
	private String pageId;

	public String showPage() {
		if (pageId == null) {
			return "home";
		}
		if (pageId.equals("1")) {
			return "db";
		} else {
			return "home";
		}
	}

	public String getPageId() {
		return this.pageId;
	}

	public void setPageId(String pageId) {
		this.pageId = pageId;
	}

}
