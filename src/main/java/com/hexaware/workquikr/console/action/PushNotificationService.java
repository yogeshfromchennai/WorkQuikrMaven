package com.hexaware.workquikr.console.action;

//import com.hexaware.c2dm.manager.AndroidPushManager;
//import com.hexaware.c2dm.manager.impl.AndroidPushManagerImpl;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.CachedRowSet;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.hexaware.c2dm.utils.AuthenticationUtils;
import com.hexaware.c2dm.utils.Constants;
import com.hexaware.framework.config.ConfigHandler;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.delegate.NotificationDelegate;
import com.hexaware.workquikr.console.service.notification.NotificationService;
import com.hexaware.workquikr.console.service.notification.Notifier;

public class PushNotificationService extends HttpServlet {

	
	
	private static final String APP_NAME = "appname";
	

	private static final String ACT_PARAM = "act";
	private static final String SEND_NOTIFICATION = "3";
	//private NotificationService notfiyService;

	private static final long serialVersionUID =-5286284539850422211L;
	public static Logger log = LogFactory
			.getLogger(PushNotificationService.class);
	public Notifier notifier = null;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req,resp);
		notifier=new Notifier();				
	}

 

	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		notifier=new Notifier();
		log.info("******************* Do get Starts*************************");
		
 if (SEND_NOTIFICATION.equals(req.getParameter(ACT_PARAM))) {

			if (req.getParameter(APP_NAME) != null) {

				String deviceId = req.getParameter("deviceid");
				String appname = req.getParameter("appname");
				String deviceType = req.getParameter("deviceType");
				String jmsEnabler=ConfigHandler.getInstance().getSystemProperty("jms.enable");
				log.info("JMS Enabler="+jmsEnabler);
				log.info("deviceType="+deviceType);
				if(jmsEnabler !=null){
					if(jmsEnabler.equalsIgnoreCase("true")){
						log.info("Push Using Jms");
					//	notfiyService.send(deviceId, appname);
						req.setAttribute("message","Notification has been sent to selected registered users."); 
						req.getRequestDispatcher("/Pages/notification.jsp").forward(req, resp);
					}
					else{
						log.info("Push Without Jms");
						notifier.sendNotification(deviceId, appname,deviceType);
						req.setAttribute("message","Notification has been sent to selected registered users."); 
						req.getRequestDispatcher("/Pages/notification.jsp").forward(req, resp);
					}
					
				}
				else{
					log.info("Jms Property not set in Property File");
					req.setAttribute("message","Notification Property not set in WorkQuikr Property File"); 
					req.getRequestDispatcher("/Pages/notification.jsp").forward(req, resp);
				}
			
				
			}
		

		} else {
			resp.getWriter().write("checking for notify call");

		}
		// System.out.println("device Toke map"+deviceMap.get("deviceId"));
		log.info("******************* Do get Ends*************************");
	}

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	
				//ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
			//	notfiyService = (NotificationService) ctx.getBean("notificationService");
		
		
		System.out
				.print("******************** Servlet init called ****************");
		NotificationDelegate notificationDelegate=new NotificationDelegate();
		
		try {
			boolean flag = false;
			CachedRowSet count = notificationDelegate.getServerToken();
			while (count.next()) {
				String ServerToken = count.getString(1);
				Constants.AUTH_KEY = ServerToken;
				flag = true;
			}
			if (flag == false) {
				try {

					String serverusername = ConfigHandler.getInstance().getSystemProperty("c2dm.server.username");
					
					String password = ConfigHandler.getInstance()
							.getSystemProperty("c2dm.server.password");
					log.info(serverusername);
					log.info(password);
					String serverToken = AuthenticationUtils.getAuthCode(
							serverusername, password);
					log.info("Server Token from c2dm : " + serverToken);
					Date DeployedDate = new Date();
					Object[] parameters = { serverToken, DeployedDate };
					if (serverToken != null) {
						notificationDelegate.insertServerToken(parameters);
						Constants.AUTH_KEY = serverToken;
						log.info("New Registration completed. Key : "
								+ Constants.AUTH_KEY);
					} else {
						log.info("Registration is not completed.");
					}
				} catch (Exception e) {
					log.warning( "Unable to reach c2dm server : ");
				}
			}

		} catch (SQLException e) {

			log.warning("Database error" + e.getMessage());
		}

	}

}
