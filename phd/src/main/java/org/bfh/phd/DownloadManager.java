package org.bfh.phd;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

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

	/** This method send the csv stream to the client as a file
	 * @param template the name of the csv they needed
	 * Code dieser Methode durch MKYONG entwickelt, durch uns auf unsere Bedürfnisse angepasst
	 */ 
	public void export(String template){
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
				ServletOutputStream out;
				out = response.getOutputStream();
				StringBuffer sb = new StringBuffer(em.getCSV(template));
				InputStream in = new ByteArrayInputStream(sb.toString()
						.getBytes("UTF-16"));

				byte[] outputByte = new byte[4096];
				// copy binary contect to output stream
				while (in.read(outputByte, 0, 4096) != -1) {
					out.write(outputByte, 0, 4096);
				}
				context.responseComplete();
				in.close();
				out.flush();
				out.close();
				} catch (IOException e) {
				e.printStackTrace();
			}finally {
				
			}
		} else {
		}
		return;
	}
}