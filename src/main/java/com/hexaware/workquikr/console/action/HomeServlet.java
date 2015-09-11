
package com.hexaware.workquikr.console.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;

import com.hexaware.workquikr.console.delegate.CatalogDelegate;
import com.hexaware.workquikr.console.vo.AdapterVo;
import com.hexaware.workquikr.console.vo.ApplicationVO;

/**
 * Servlet implementation class HomeServlet
 */
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger log=LogFactory.getLogger(HomeServlet.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("******************* Do Post  Starts*************************");
		if(request.getHeader("User-Agent").indexOf("Mobile") != -1) {
		    //you're in mobile land
		// request.getRequestDispatcher("mindex.html").forward(request, response);
		 response.sendRedirect("/WorkQuikr-console/Pages/mindex.html");
		  }
	 else if (request.getHeader("User-Agent").contains("Android") && !request.getHeader("User-Agent").contains("Mobile")){
		 //request.getRequestDispatcher("mindex.html").forward(request, response);
		 response.sendRedirect("/WorkQuikr-console/Pages/mindex.html");
	 }
	 else if(request.getHeader("User-Agent").contains("BlackBerry")){
		 response.sendRedirect("/WorkQuikr-console/Pages/mindex.html");			 
	 }
	 else{
		 System.out.println("Username " + request.getRemoteUser());
			if(request.isUserInRole("manager")||request.isUserInRole("admin")) {
				System.out.println("User in Manager/Admin role");
				RequestDispatcher req=request.getRequestDispatcher("index.jsp");
				CatalogDelegate catalogDelegate=new CatalogDelegate();
				List<AdapterVo> adapterDetails = catalogDelegate.getAllAdapters();
				List<ApplicationVO> deployedDetails = catalogDelegate.getAllApplications();			
				request.setAttribute("DeploymentVoList", deployedDetails);
				request.setAttribute("AdapterVoList", adapterDetails);
				req.forward(request, response);
			}
			else {
				 
				System.out.println("User in General role");
				response.sendRedirect("appdownload.jsp");
			}
		 
	 }
		
		
		log.info("******************* Do Post Ends*************************");
	}

}
