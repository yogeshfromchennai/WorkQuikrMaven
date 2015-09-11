package com.hexaware.workquikr.console;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.util.adapter.jaxb.xml.Adapter;
import com.hexaware.workquikr.console.util.adapter.jaxb.xml.XmlAdapterHelper;
/**
 * Servlet implementation class XMLAdapter
 */
public class XMLAdapter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	FileWriter fWriter = null;
	BufferedWriter writer = null;
	PrintWriter out = null;
	private Logger log = LogFactory.getLogger(this.getClass());

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public XMLAdapter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		log.info("*****************************XML Adapter Starts*********************");
		log.info("inside Xml adapter Servlet");
		out = response.getWriter();
	//	response.setContentType("text/xml");
		// out.println("inside Xml adapter Servlet");
		//String adapterName = request.getParameter("adapterName");
		String AdapterName = "XmlAdapter";

		log.info("info " + request.getSession().getServletContext());
		InputStream in = request.getSession().getServletContext()
				.getResourceAsStream("/Adapters/" + AdapterName + ".xml");
		if(in!=null){
			Adapter adapter=new XmlAdapterHelper().getFromXML(in);
			System.out.println("adapter is "+adapter+"Adapter Type: "+adapter.getType());
			List<Adapter.Operations> operations=adapter.getOperations();
			System.out.println("Operation type "+operations.get(0).getOperation().get(0).getOperationType());
			System.out.println("Operation type "+operations.get(0).getOperation().get(0).getFilePath());
			String filePath=operations.get(0).getOperation().get(0).getFilePath();
			System.out.println("filePath String"+filePath);
			in.close();
			
			InputStream inp = request.getSession().getServletContext()
					.getResourceAsStream(filePath);
			int b;
			while ((b = inp.read()) != -1) {
				// writer.write(""+(char)b);
				out.print("" + (char) b);
			}
			inp.close();
			
		}else{
			log.info("Adapter Not Available");
		}
		
		
		
//		int b;
//		while ((b = in.read()) != -1) {
//			// writer.write(""+(char)b);
//			out.print("" + (char) b);
//		}
//		in.close();
		//getXsl(request, response);

		// String
		// text="<?xml-stylesheet type='text/xsl' href='mystyle.xsl'?>\n";
		// fWriter = new
		// FileWriter("D:/apache-tomcat-6.0.9/webapps/WorkQuikr-console/Adapters/Xml/"+AdapterName+".xml");
		// writer = new BufferedWriter(fWriter);
		// writer.write(text);
		// writer.newLine();
		// while ( ( b = in.read() ) != -1 )
		// {
		// writer.write(""+(char)b);
		// }
		// writer.close();

		// out.println("File writed successfully");
		log.info("********************************XML Adapter Ends***********************************");
	}

	private void getXsl(HttpServletRequest request, HttpServletResponse response) {
		InputStream in = request.getSession().getServletContext()
				.getResourceAsStream("/Adapters/Xml/mystyle.xsl");
		int b;
		try {
			while ((b = in.read()) != -1) {
				// writer.write(""+(char)b);
				out.print("" + (char) b);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
