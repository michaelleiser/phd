package org.phd.test;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.event.ActionEvent;

@ManagedBean(name = "patient", eager = true)
@SessionScoped
public class Patient implements Serializable {

	private int patientid;
	private String firstname;
	private String lastname;

	public Patient() {

	}

	public int getPatientid() {
		return this.patientid;
	}
	
	public void setPatientid(int patientid)  {
		this.patientid = patientid;
	}

	public String getFirstname() {
		return this.firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	

	public static List<Patient> getPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		Connection con = MyConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String stm = "SELECT * FROM patient;";
		try {
			pst = con.prepareStatement(stm);
			pst.execute();
			rs = pst.getResultSet();

			while (rs.next()) {
				Patient p = new Patient();
				p.setPatientid(rs.getInt("patient_id"));
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
				patients.add(p);
			}
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return patients;
	}
	
	public static Patient getpatientfromid(int patientid) {
		Patient p = null;
		Connection con = MyConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String stm = "SELECT * FROM patient WHERE patient_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, patientid);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				p = new Patient();
				p.setPatientid(rs.getInt("patient_id"));
				p.setFirstname(rs.getString("firstname"));
				p.setLastname(rs.getString("lastname"));
			}
			rs.close();
			pst.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pst != null) {
				try {
					pst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return p;
	}

	public String getPatient(){
		Patient p = getpatientfromid(this.patientid);
		if(p != null){
			return "Hello id " + p.getPatientid() + " " + p.getFirstname() + " " + p.getLastname();
		}
		return "";
	}
	
}
