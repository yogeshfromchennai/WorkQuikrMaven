package com.hexaware.workquikr.console.action;


import java.io.File;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hexaware.framework.config.ConfigHandler;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.delegate.CatalogDelegate;
import com.hexaware.workquikr.console.util.UndeployApplication;

/**
 * Servlet implementation class Undeploy
 */
public class Undeploy extends HttpServlet {
	private static final long serialVersionUID = 1L;
	   private Logger log=LogFactory.getLogger(this.getClass());
    private String deployDir=null; 
    private static final String undeployRegisteredApplication="undeployRegisteredApplication";
    private static final String undeployAdapter="undeployAdapter";
    public UndeployApplication delete=null;
    private String adapterPath=null;
   
    /**
     * @see HttpServlet#HttpServlet()
     *
     */
    
    @Override
    public void init() throws ServletException {
    
    	super.init(); 
    	
    	deployDir=ConfigHandler.getInstance().getSystemProperty("server.deploy.location");
    	adapterPath=ConfigHandler.getInstance().getSystemProperty("server.deploy.adapter.location");
    }
    public Undeploy() {
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
		   log.info("******************* Do post Starts*************************");

		if(undeployRegisteredApplication.equalsIgnoreCase(request.getParameter("type"))){
			
			System.out.println("inside registered application");
			String source=request.getParameter("source");
			String webId = request.getParameter("webId");
			System.out.println("appname"+source);
			System.out.println("webid"+webId);
			delete=new UndeployApplication();
			String fileName=delete.deleteAll(source,webId,deployDir);	
			System.out.println(deployDir);
			RequestDispatcher rD=request.getRequestDispatcher("/Pages/Home");
			if(fileName!=null)
			{
			fileName=fileName.replaceAll("-web", "");
			request.setAttribute("msg", fileName+" is undeployed successfuly");
	
		    rD.forward(request, response);
			// response.sendRedirect("/WorkQuikr-console/Pages/index.jsp");
			}
			else
			{
	 
				 response.sendRedirect("/WorkQuikr-console/Pages/Home");
			}
		   
			
		}
		if(undeployAdapter.equalsIgnoreCase(request.getParameter("type"))){
			System.out.println("inside adapter");
			String source=request.getParameter("source");
			File file=new File(adapterPath+"/"+source+".xml");
			//out.println(source);
			file.delete();
			CatalogDelegate catlog=new CatalogDelegate();
			Object[] parameters={source};
			catlog.removeUndeployedAdapter(parameters);
			String fileName=file.getName().replaceAll(".xml", "");
			request.setAttribute("message", fileName+ " is undeployed successfuly");
			RequestDispatcher rD=request.getRequestDispatcher("/Pages/Home");
		    rD.forward(request, response);
		}
	      log.info("******************* Do post Ends*************************");

	}

}
