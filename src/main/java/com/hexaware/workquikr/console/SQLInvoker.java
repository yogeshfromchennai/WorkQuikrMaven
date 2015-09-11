package com.hexaware.workquikr.console;

import com.hexaware.framework.dao.QueryProcessor;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;



import com.hexaware.workquikr.console.delegate.DeviceEntryDelagate;
import com.hexaware.workquikr.console.util.DataAdapter;
import com.hexaware.workquikr.console.util.adapter.jaxb.sql.Adapter;
import com.hexaware.workquikr.console.util.adapter.jaxb.sql.Adapter.Operations.Operation;
import com.hexaware.workquikr.console.util.adapter.jaxb.sql.XMLHelper;





/**
 * Servlet implementation class SQLInvoker
 */
public class SQLInvoker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private Logger log=LogFactory.getLogger(this.getClass());
	private DeviceEntryDelagate deviceEntryDelagate;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SQLInvoker() {
		super();
		deviceEntryDelagate = new DeviceEntryDelagate();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*  
		 * Database invoker parameters from request
		 * 
		 */
		log.info("********************************SQLInvoker Starts***********************************");
		PrintWriter out= response.getWriter();
		String adapterName=request.getParameter("name");
		String reqProcedure=request.getParameter("operation");
		String reqParameter=request.getParameter("param");
		String reqFormat=request.getParameter("format");
		//String reqFormat="html";
		String reqDisplayFormat=request.getParameter("displayFormat");
		String reqData=request.getParameter("data");
		String reqEvent=request.getParameter("event");
		String reqJavascriptName=request.getParameter("javascriptName");
		String reqfunctionParameter=request.getParameter("functionParameter");
		String token=request.getParameter("token");
		log.info(adapterName);
		log.info(reqProcedure);
		log.info(reqParameter);
		log.info(reqFormat);
		log.info(reqDisplayFormat);
		log.info(reqData);
		log.info(reqEvent);
		log.info(reqJavascriptName);
		log.info(reqfunctionParameter);		
		log.info(token);	
		log.info("SQLInvoker class called");
		String performOnDemandCheck="OFF";
		String authorisedUser=null;
		if(performOnDemandCheck.equalsIgnoreCase("ON")){
			 authorisedUser = deviceEntryDelagate.checkDisable(token);
		}		
		else{
			authorisedUser="true";
		}
		if(authorisedUser.equalsIgnoreCase("true")){
			String sqlQuery=null;
			String[] sqlParams=null;
			InputStream in = getServletContext().getResourceAsStream("/Adapters/"+adapterName+".xml");
			if(in!=null)
			{
				Adapter adapter=new XMLHelper().getFromXML(in);
				System.out.println("adapter is "+adapter);
				Map<String,Object> map=new HashMap<String, Object>();
				System.out.println("adapter type "+adapter.getType());
				if(adapter.getType()!=null && adapter.getType().equalsIgnoreCase("Sql"))
				{
					String datasource=adapter.getDatabaseJndi();
					System.out.println("jndi datasource is "+datasource);

					if(adapter.getDatabaseJndi()!=null && !adapter.getDatabaseJndi().equals("")){

						Map<String, Adapter.Operations.Operation> procedureMap=adapter.getProcedureAsMap();
						System.out.println("procedureMap is jndi "+procedureMap);
						Operation procedure=procedureMap.get(reqProcedure);

						if(procedure!=null){

							String execute=procedure.getOperationType();
							System.out.println("Sql Execute : "+execute);
							map.put("execute",execute);

							sqlQuery=procedure.getQuery();

							if(reqParameter!=null && !reqParameter.equals(""))
							{
								sqlParams=reqParameter.split(",");
							}
							map.put("param", sqlParams);
							System.out.println("String [] param"+sqlParams);
							System.out.println("Sql Query : "+sqlQuery);
							map.put("sqlQuery",sqlQuery);

							DataAdapter da=new DataAdapter(adapter.getDatabaseJndi());
							JSONObject json=null;
							String result="";
							if(reqFormat.equalsIgnoreCase("json")){
								json= da.queryInvoker(map);
								out.write(json+"");
							}

							else if(reqFormat.equalsIgnoreCase("html")){
								map.put("format",reqFormat);
								map.put("displayFormat",reqDisplayFormat);
								map.put("data",reqData);
								map.put("event",reqEvent);
								map.put("javascriptName",reqJavascriptName);
								map.put("functionParameter",reqfunctionParameter);
								result=da.queryInvokerForHtml(map);
								log.info("sql invoker result"+result);
								out.write(result);
							}

							else{
								log.info("Format not Specified");
							}
						}
						else
						{
							log.info("Operation is not available");
						}	

					}
					else{

						Map<String, Adapter.Operations.Operation> procedureMap=adapter.getProcedureAsMap();
						System.out.println("procedureMap is "+procedureMap);
						Operation procedure=procedureMap.get(reqProcedure);

						if(procedure!=null){

							String execute=procedure.getOperationType();
							System.out.println("Sql Execute : "+execute);
							map.put("execute",execute);

							sqlQuery=procedure.getQuery();

							if(reqParameter!=null && !reqParameter.equals(""))
							{
								sqlParams=reqParameter.split(",");
							}
							map.put("param", sqlParams);
							System.out.println("String [] param"+sqlParams);
							System.out.println("Sql Query : "+sqlQuery);
							map.put("sqlQuery",sqlQuery);
							DataAdapter da=new DataAdapter();
							JSONObject json=null;
							String result="";
							if(reqFormat.equalsIgnoreCase("json")){
								json= da.queryInvoker(map);
								out.write(json+"");
							}

							else if(reqFormat.equalsIgnoreCase("html")){
								map.put("format",reqFormat);
								map.put("displayFormat",reqDisplayFormat);
								map.put("data",reqData);
								map.put("event",reqEvent);
								map.put("javascriptName",reqJavascriptName);
								map.put("functionParameter",reqfunctionParameter);
								result=da.queryInvokerForHtml(map);
								System.out.println("sql invoker result"+result);
								out.write(result);
							}

							else{
								log.info("Format not Specified");
							}


						}
						else
						{
							log.info("Operation is not available");
						}	
					}

				}
				else{
					log.info("Adapter Type  is not available");
				}
			}
			else
			{
				log.info("Adapter is not available");
			}
		}
		else{
			log.info("Not A Authorised User");
			out.write("unauthorised");
			response.setStatus(500);
		}

		log.info("********************************SQLInvoker Ends***********************************");


	}




	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}
}
