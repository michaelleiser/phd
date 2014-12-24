package org.bfh.phd;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * 
 * 
 * @author leism3, koblt1
 */
@ManagedBean(name = "fileDownloadBean")
@SessionScoped
public class DownloadManager implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * @param template
	 * @throws Exception
	 */
	public void export(String template) throws Exception {
		EntityManager em = new EntityManager();
		em.generateCsvFiles();
		if (em.isLegalTemplate(template) || template.equals("total")) {
			FacesContext context = FacesContext.getCurrentInstance();
			HttpServletResponse response = (HttpServletResponse) context
					.getExternalContext().getResponse();
			template = template.replace(" ", "_");
			// tell browser program going to return an application file
			// instead of html page
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition",
					"attachment;filename="+ template +".csv");
			try {
				ServletOutputStream out = response.getOutputStream();
				StringBuffer sb = new StringBuffer();
				sb.append(em.getCSV(template));

				InputStream in = new ByteArrayInputStream(sb.toString()
						.getBytes("UTF-8"));

				byte[] outputByte = new byte[4096];
				// copy binary contect to output stream
				while (in.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
				context.responseComplete();
				in.close();
				out.flush();
				out.close();
			} finally {
				
			}
		} else {
		}
		return;
	}

	// TODO Anstelle file auslesen direkt senden
//	private StringBuffer generateCsvFileBuffer(String template) {
//		StringBuffer writer = new StringBuffer();
//		writer.append();
//		return writer;
//	}

//	private String getPath() {
//		Properties prop = new Properties();
//		String propFile = "config.properties";
//		InputStream input = Patient.class.getClassLoader().getResourceAsStream(
//				propFile);
//		try {
//			prop.load(input);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		String path = prop.getProperty("PATH");
//		return path;
//	}
}