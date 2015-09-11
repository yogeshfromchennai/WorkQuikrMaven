package com.hexaware.workquikr.console.action;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.delegate.CatalogDelegate;
import com.hexaware.workquikr.console.vo.ApplicationVO;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class AppDownload
 */
public class AppDownload extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static Logger log=LogFactory.getLogger(AppDownload.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AppDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("******************* Do Post  Starts*************************");
		RequestDispatcher req=request.getRequestDispatcher("appdownload.jsp");
		CatalogDelegate catalogDelegate=new CatalogDelegate();
		List<ApplicationVO> deployedDetails = catalogDelegate.getAllApplications();			
		request.setAttribute("DeploymentVoList", deployedDetails);
		req.forward(request, response);
		log.info("******************* Do Post Ends*************************");
		
	}

}
