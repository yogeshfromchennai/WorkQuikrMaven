package com.hexaware.workquikr.console;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;

/**
 * Servlet implementation class AuthenticationService
 */
public class AuthenticationService extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log=LogFactory.getLogger(this.getClass());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthenticationService() {
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		log.info("***************Authentication Starts***************************");
//		PrintWriter out=response.getWriter();
//		String authenticationType=request.getParameter("authenticationType");
//		String username=request.getParameter("username");
//		String password=request.getParameter("password");	
//		String result="";
//		log.info("authenticationType="+authenticationType);
//		log.info("username="+username);
//		log.info("username="+password);
//		if(authenticationType.equalsIgnoreCase("database")){
//		DatabaseHelper d=new DatabaseHelper();
//		try {
//			 result=d.getAuthenticatedUser(username, password);
//			log.info("result is "+result);
//			out.write(result);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		}
//		else if(authenticationType.equalsIgnoreCase("")||authenticationType.equalsIgnoreCase(null)){
//			 result="Format not Specified";
//			out.write("result is "+result);
//			log.info("Format Not Specified");
//		}
//		
//		log.info("***************Authentication Ends***************************");
 	}

	

}
