package com.hexaware.workquikr.console;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.action.PushNotificationService;
import com.hexaware.workquikr.console.delegate.CatalogDelegate;
import com.hexaware.workquikr.console.delegate.DeviceEntryDelagate;
import com.hexaware.workquikr.console.delegate.NotificationDelegate;


/**
 * Servlet implementation class RegisterAndPull
 */
public class RegisterAndPull extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String ADD_DEVICE = "2";
	private static final String GET_VERSION = "3";
	private static final String ADD_DEVICE_IOS = "4";
	private static final String DEVICE_TOKEN = "deviceToken";
	private static final String ACT_PARAM = "act";
	private DeviceEntryDelagate deviceEntryDelagate;
	public static Logger log = LogFactory.getLogger(PushNotificationService.class);
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterAndPull() {
        super();
        // TODO Auto-generated constructor stub
        deviceEntryDelagate=new DeviceEntryDelagate();
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
	protected void doGet(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log.info("******************* Registration and Get Version Starts*************************");
		if (ADD_DEVICE.equals(req.getParameter(ACT_PARAM))) {

			String deviceToken = req.getParameter(DEVICE_TOKEN);
			if (deviceToken!= null && !deviceToken.equalsIgnoreCase("null")) {
				
				log.info("Device Token : "+deviceToken);
						 
				String appName = req.getParameter("appname");
				String version = req.getParameter("version");
				String userId = req.getParameter("userid");
				 
				log.info("appName : "+appName +"\nversion : "+version+"\nuserId : "+userId);
				Date date = new Date();
				
				
				try {
					if (deviceEntryDelagate.isAppExist(appName)) {
					boolean flag = false;
					//CachedRowSet count = db.isRegistedDeviceToken(deviceToken);
					CachedRowSet count = deviceEntryDelagate.isRegistedDeviceToken(deviceToken);
					while (count.next()) {
						Object[] parameters = { deviceToken, version, date, userId, deviceToken };
						deviceEntryDelagate.updateRegisteredapplication(parameters);
						flag = true;
					}
					if (flag == false) {
						String app_name=appName+"-web";
						int app_id=deviceEntryDelagate.getApplicationId(app_name);
						String deviceType="android";
						Object[] parameters = { app_id, deviceToken, userId, version, date, deviceType };
						deviceEntryDelagate.insertRegistedAppInfo(parameters);
						log.info("The Application has been Registered Succesfully");
						
					}
					
						
					} else {
						log.info("The registered application is not available.");
					}

				} catch (SQLException e) {
					
					log.error("Sql Exception "+e.getMessage());
				}

				

			}
			else{
				log.info("Device Token Received As Null");
			}

		}
		if (ADD_DEVICE_IOS.equals(req.getParameter(ACT_PARAM))) {

			String deviceToken = req.getParameter(DEVICE_TOKEN);
			if (deviceToken!= null && !deviceToken.equalsIgnoreCase("null")) {
				
				log.info("Device Token : "+deviceToken);
						 
				String appName = req.getParameter("appName");
				String version = "1";
				String userId = req.getParameter("userid");
				 
				log.info("appName : "+appName +"\nversion : "+version+"\nuserId : "+userId);
				Date date = new Date();
				try {
					if (deviceEntryDelagate.isAppExist(appName)) {
					boolean flag = false;
					CachedRowSet count = deviceEntryDelagate.isRegistedDeviceToken(deviceToken);
					while (count.next()) {
						Object[] parameters = { deviceToken, version, date, userId, deviceToken };
						deviceEntryDelagate.updateRegisteredapplication(parameters);
						flag = true;
					}
					if (flag == false) {
						String app_name=appName+"-web";
						int app_id=deviceEntryDelagate.getApplicationId(app_name);
						String deviceType="ios";
						Object[] parameters = { app_id, deviceToken, userId, version, date, deviceType };
						deviceEntryDelagate.insertRegistedAppInfo(parameters);
						log.info("The Application has been Registered Succesfully");
						
					}
					
						
					} else {
						log.info("The registered application is not available.");
					}

				} catch (SQLException e) {
					
					log.error("Sql Exception "+e.getMessage());
				}
			}
			else{
				log.info("Device Token Received As Null");
			}

		}
		if (GET_VERSION.equals(req.getParameter(ACT_PARAM))) {
			log.info("Get Version Starts");
			String appName=req.getParameter("appName");
			PrintWriter out=response.getWriter();
			String deviceType="";
			log.info("User Agent: "+req.getHeader("User-Agent"));
			if(req.getHeader("User-Agent").contains("Android")) {
				log.info("Device Type=Android");
				deviceType="android";
			}
			else if(req.getHeader("User-Agent").contains("Darwin")) {
				log.info("Device Type=Ios");
				deviceType="ios";
			}
			else if(req.getHeader("User-Agent").contains("BlackBerry")) {
				log.info("Device Type=blackberry");
				deviceType="blackberry";
			}
			
			if(appName!=null && !appName.equals(""))
			{
								
				String version=deviceEntryDelagate.getVersion(appName+"-web",deviceType);
				
				if(version!=null)
				{
					if(deviceType.equalsIgnoreCase("android")){
						out.print("android.version.code = "+version);
						log.info("android.version.code = "+version);
					}
					else if(deviceType.equalsIgnoreCase("ios")){
						out.print("ios.version.code = "+version);
						log.info("ios.version.code = "+version);						
					}
					else {
						out.print("ios.version.code = "+version);
						log.info("ios.version.code = "+version);						
					}
				
				}
				else
				{
					out.print("android.version.code = 0.0");	
					log.info("android.version.code = 0.0");
				}	
			}
			else
			{
				out.print("android.version.code = 0.0");
			}
			log.info("Get Version Ends");
			
		}
		
		log.info("******************* Registration and Get Version Ends*************************");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
