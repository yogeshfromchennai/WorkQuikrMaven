package com.hexaware.workquikr.console;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.delegate.DeviceEntryDelagate;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RemoteDisableCheck
 */
public class RemoteDisableCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ACT_PARAM = "act";
	private static final String GET_TOKEN = "1";
	private static final String CHECK_DISABLE = "2";
	private static final String UPDATE_USERS = "3";
	private static final String App_DISABLED = "appDisabled";
	private DeviceEntryDelagate deviceEntryDelagate;
	public static Logger log = LogFactory
			.getLogger(RemoteDisableCheck.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoteDisableCheck() {
        super();
        // TODO Auto-generated constructor stub
        deviceEntryDelagate=new DeviceEntryDelagate();
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
		log.info("******************* Do Post Starts*************************");
		PrintWriter out=response.getWriter();
		 if (GET_TOKEN.equals(request.getParameter(ACT_PARAM))) {
			 String versionId=request.getParameter("VersionID");
			 String deviceId=request.getParameter("deviceId");
			 String appNameTemp=request.getParameter("appName");
			 String appName=appNameTemp+"-web";
			 String userName=request.getParameter("username");
			 UUID generateToken=java.util.UUID.randomUUID();
			 String token=generateToken.toString();
			 log.info("versionId:"+versionId);
			 log.info("deviceId: "+deviceId);
			 log.info("appName: "+appName);
			 log.info("userName: "+userName);
			 log.info("token: "+token);
			
			 String tokenReceived=deviceEntryDelagate.checkUser(deviceId);
			 log.info("tokenReceived from db"+tokenReceived);
			 if(tokenReceived==null){
				 log.info("New Registration");
				 int appId=deviceEntryDelagate.getApplicationId(appName);
				 Date deployedDate = new Date();
				 Object[] parameters = { appId,deviceId,userName,token,deployedDate,versionId };
				 deviceEntryDelagate.sendToken(parameters);
				 out.print(token);
			 }
			 else if(tokenReceived.equalsIgnoreCase(App_DISABLED)){
				 out.write(""+null);
			 }
			 else{
				 out.write(tokenReceived);
			 }
			 
			 
		 }
		 
		 else if (CHECK_DISABLE.equals(request.getParameter(ACT_PARAM))) {
			 String token=request.getParameter("token");
			 log.info("token: "+token); 			
			 String disabler=deviceEntryDelagate.checkDisable(token);
			 log.info("disabler: "+disabler);
			 out.write(disabler);
		 }
		 
		 else if (UPDATE_USERS.equals(request.getParameter(ACT_PARAM))) {
			 log.info("Updating User table Starts");
			 String enabler=request.getParameter("enabler");
			 String token=request.getParameter("token");
			 log.info("enabler: "+enabler); 
			 log.info("token: "+token);
			 deviceEntryDelagate.updateUsersList(enabler,token);
			 response.sendRedirect("Pages/remotedisable.jsp");
			
		 }
		
		log.info("******************* Do Post Ends*************************");
	}

}
