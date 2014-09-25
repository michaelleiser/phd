package org.phd.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EntityManager {
	
	private List<Patient> patients;
	private List<Staff> staff;
	
	private Connection con = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	
	public EntityManager(){
		this.patients = new ArrayList<Patient>();
		this.staff = new ArrayList<Staff>();;
	}
	
	public Staff getStaff(String name, String password) {
		Staff s = null;
		String stm = "SELECT * FROM doctor WHERE name=? AND password=?;";
		init();
		try {
			pst = con.prepareStatement(stm);
			pst.setString(1, name);
			pst.setString(2, password);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				s = new Staff();
				s.setName(rs.getString("name"));
				s.setPassword(rs.getString("password"));
				s.setRole(rs.getInt("role_role_id"));
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return s;
	}

	public List<Staff> getStaff() {
		return staff;
	}

	public Patient getPatient(int patientid) {
		Patient p = null;
		init();
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
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return p;
	}
	
	public List<Patient> getPatients() {
		List<Patient> patients = new ArrayList<Patient>();
		init();
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
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return patients;
	}

	public void registernew(String name, String password, int i) {
		String stm1 = "SELECT * FROM doctor WHERE name=?;";
		String stm2 = "INSERT INTO testdb.doctor(name, password, role_role_id) VALUES(?, ?, ?);";
		init();
		try {
			pst = con.prepareStatement(stm1);
			pst.setString(1, name);
			pst.execute();
			rs = pst.getResultSet();
			if(rs.next()) {
				return;
			} else {
				pst = con.prepareStatement(stm2);
				pst.setString(1, name);
				pst.setString(2, password);
				pst.setInt(3, i);
				pst.execute();
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return;
	}

	private void init() {
		con = MyConnection.getConnection();
	}
	
	private void close() {
		try {
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
	}

	public List<PatientData> getPatientData(int patientid) {
		List<PatientData> data = new ArrayList<PatientData>();
		init();
		String stm = "SELECT * FROM patientdata WHERE patient_patient_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, patientid);
			pst.execute();
			rs = pst.getResultSet();
			while (rs.next()) {
				PatientData d = new PatientData();
				d.setPatientdata_id(rs.getInt("patientdata_id"));
				d.setFirstdata(rs.getString("firstdata"));
				d.setSeconddata(rs.getString("seconddata"));
				data.add(d);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return data;
	}

	public List<PatientData> getPatientDatabla(int patientdataid) {
		List<PatientData> list = new ArrayList<PatientData>();
		init();
		String stm = "SELECT * FROM patientdata WHERE patientdata_id=?;";
		try {
			pst = con.prepareStatement(stm);
			pst.setInt(1, patientdataid);
			pst.execute();
			rs = pst.getResultSet();
			if (rs.next()) {
				PatientData p = new PatientData();
				p.setPatientdata_id(rs.getInt("patientdata_id"));
				p.setFirstdata(rs.getString("firstdata"));
				p.setSeconddata(rs.getString("seconddata"));
				list.add(p);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
}
