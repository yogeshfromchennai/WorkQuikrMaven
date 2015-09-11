package com.hexaware.workquikr.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.util.JavaResultHelper;
import com.hexaware.workquikr.console.util.WebServiceInvolerHelper;
import com.hexaware.workquikr.console.util.adapter.jaxb.ws.Adapter;
import com.hexaware.workquikr.console.util.adapter.jaxb.ws.Adapter.Operations.Operation;
import com.hexaware.workquikr.console.util.adapter.jaxb.ws.XmlWsHelper;

/**
 * Servlet implementation class WsInvoker
 */
public class WsInvoker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger log = LogFactory.getLogger(this.getClass());
	PrintWriter out = null;
	WebServiceInvolerHelper wsinvokerhelper=null;
	JavaResultHelper javahelper=null;	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WsInvoker() {
        super();
        // TODO Auto-generated constructor stub
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
		log.info("*****************************Ws Adapter Starts*********************");
		out = response.getWriter();
		String adapterName=request.getParameter("name");
		String reqProcedure=request.getParameter("operation");
		String json = request.getParameter("param");
		String reqFormat=request.getParameter("format");
		String reqDisplayFormat=request.getParameter("displayFormat");
		String reqData=request.getParameter("data");
		String reqEvent=request.getParameter("event");
		String reqJavascriptName=request.getParameter("javascriptName");
		String reqfunctionParameter=request.getParameter("functionParameter");
		log.info(reqProcedure);
		log.info(json);
		log.info(reqFormat);
		log.info(reqDisplayFormat);
		log.info(reqData);
		log.info(reqEvent);
		log.info(reqJavascriptName);
		log.info(reqfunctionParameter);	
		log.info("info " + request.getSession().getServletContext());
		InputStream in = request.getSession().getServletContext()
				.getResourceAsStream("/Adapters/" + adapterName + ".xml");
		if(in!=null){
		Adapter adapter=new XmlWsHelper().getFromXML(in);
		log.info("adapter type: "+adapter.getType());
			if(adapter.getType()!=null && adapter.getType().equalsIgnoreCase("webservice")){
				 Map<String, Adapter.Operations.Operation> procedureMap=adapter.getProcedureAsMap();
				 log.info("procedureMap is "+procedureMap);
				 Operation procedure=procedureMap.get(reqProcedure);
				 if(procedure!=null){
					 log.info("Ws adpter InputClass:"+procedure.getInputClass());
					 log.info("WS portName: "+procedure.getPortName());
					 wsinvokerhelper=new WebServiceInvolerHelper();
					 Object result=wsinvokerhelper.invokeServiceAdapter(json,procedure.getWsdlURL(),procedure.getJaxbPackageName(),procedure.getPortName(),procedure.getTargetNamespace(),procedure.getServiceName(),procedure.getInputClass());
					 log.info("result after webservice hit before converting: "+result);
					 Map<String, Object> map=new HashMap<String, Object>();
					 if(result instanceof java.lang.String){
						 System.out.println("it s a string");
					 }
					 
					 else{
						 System.out.println("type not known");
					 }
					 if(reqFormat.equalsIgnoreCase("json")){						
						 map.put("returnType", "webservice");
						 map.put("result",result);
						 javahelper=new JavaResultHelper();
						 JSONObject jsonObj=javahelper.getResultAsJson(map);
						 System.out.println("result after webservice hit"+jsonObj);
						 out.write(jsonObj+""); 
					 }
					 else if(reqFormat.equalsIgnoreCase("Html")){
						 map.put("returnType", procedure.getReturnType());
						 map.put("returnDataType", procedure.getReturnDatatype());
						 map.put("result",result);
						 map.put("displayFormat",reqDisplayFormat);
						 map.put("data",reqData);
						 map.put("event",reqEvent);
						 map.put("javascriptName",reqJavascriptName);
						 map.put("functionParameter",reqfunctionParameter);
						 String output = javahelper.getResultAsHtml(map);
						 out.write(output); 
					 }
					 else{
						 log.info("Format Not Specified");
					 }
					 
				 }
				 else{
					 log.info("Operation is not available");
				 }
				
			}
			else{
				log.info("Adapter is not of WebService Type");
			}
			
		}
		else{
			log.info("Adapter Not Avalable");
		}
		
		log.info("*****************************Ws Adapter Ends*********************");
	}

}
