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
	private String gender = "";
	private String street = "";
	private int nr = 1;
	private String city = "";
	private String zip = "";
	private int telnumber = 0;
	private String birth;
//	private List<Patient> patientList = new ArrayList<Patient>();

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
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getNr() {
		return nr;
	}

	public void setNr(int nr) {
		this.nr = nr;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public int getTelnumber() {
		return telnumber;
	}

	public void setTelnumber(int telnumber) {
		this.telnumber = telnumber;
	}

	public String getBirth() {
		return birth;
	}

	public void setBirth(String birth) {
		this.birth = birth;	
	}


	// move to entytimanager
	public String addNewPatient(){
		String stm1 = "SELECT * FROM testdb.patient WHERE firstname='"+firstname+"' AND lastname='"+lastname+"' AND birthday='"+ birth + "' AND zip='"+zip+"';";
		String stm2 = "INSERT INTO testdb.patient (firstname, lastname, birthday, street, nr, city, zip, telnumber, gender) VALUES('"+firstname+"','"+lastname+"','"+birth+"','"+street+"','"+nr+"','"+city+"','"+zip+"','"+telnumber+"','"+gender+"');";
		Connection con = MyConnection.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(stm1);
			pst.execute();
			rs = pst.getResultSet();
			if(rs.next()) {
				return "newPatient";
			}
			pst.close();
			pst = con.prepareStatement(stm2);
			pst.execute();
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
		return "home";
	}
	
	@Override
	public String toString(){
		return firstname + " : " + lastname;
	}
	
}

//	public String search(){
//		Connection con = MyConnection.getConnection();
//		PreparedStatement pst = null;
//		ResultSet rs = null;
//		firstname += "%";
//		lastname += "%";
//		city += "%";
//		
//		String stm = "SELECT * FROM testdb.patient WHERE firstname='"+firstname+"' AND lastname='"+lastname+"' AND city='"+ city + "';";
//		try {
//			pst = con.prepareStatement(stm);
//			pst.execute();
//			rs = pst.getResultSet();
//
//			while (rs.next()) {
//				Patient p = new Patient();
//				p.setId(Integer.parseInt(rs.getString("patient_id")));
//				p.setFirstname(rs.getString("firstname"));
//				p.setLastname(rs.getString("lastname"));
//				patientList.add(p);
//			}
//			rs.close();
//			pst.close();
//			con.close();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (pst != null) {
//				try {
//					pst.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		return "HOME";
//	}
	
//	public List<Patient> getPatientList(){
//		return patientList;
//	}
//}
