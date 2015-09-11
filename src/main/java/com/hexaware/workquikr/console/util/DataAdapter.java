package com.hexaware.workquikr.console.util;

import com.hexaware.framework.dao.QueryProcessor;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;

import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/*
 * DataAdapter class is used to invoke
 *  database through the Framework
 */

public class DataAdapter {

	/* framework configuration details */

	private QueryProcessor qp = null;
//	private String DataSourceJNDI = "";
//	private String driverClass = "com.mysql.jdbc.Driver";
//	private String url = "jdbc:mysql://localhost:3306/workQuickr";
//	private String userName = "root";
//	private String password = "admin";
	   private Logger log=LogFactory.getLogger(this.getClass());
	public DataAdapter(String dataSource) {
		qp = new QueryProcessor(dataSource);
		
	}
	public DataAdapter() {
		qp = new QueryProcessor();
		
	}
	/* Used to process the update query */

	public JSONObject executeUpdateQuery(String sqlQuery, Object[] parameters) {
		qp.executeUpdateQuery(sqlQuery, parameters);
		JSONObject json=new JSONObject();
		json.put("Log : ", "Query executed Succesfully");
		return json;

	}

	/* queryInvoker is called from sqlInvoker class */

	public JSONObject queryInvoker(Map<String, Object> map) {
		//System.out.println("queryInvoker Method called");
		log.info("queryInvoker Method called");
		String sqlQuery = (String) map.get("sqlQuery");
		Object[] parameters = (Object[]) map.get("param");
		JSONObject json=null;
		String execute = (String) map.get("execute");		
		if (execute!=null && execute.equals("update")) {
			json=executeUpdateQuery(sqlQuery, parameters);
		} else if (execute!=null && execute.equals("select")) {
			json=executeSelectQuery(sqlQuery, parameters);
			return json;
		}
		
		return json;
		
	}
	
	/* queryInvoker is called from sqlInvoker class for html output */
	public String queryInvokerForHtml(Map<String, Object> map) {
		log.info("queryInvoker Method called for html");
		String sqlQuery = (String) map.get("sqlQuery");
		Object[] parameters = (Object[]) map.get("param");
		String execute = (String) map.get("execute");
		String data = (String) map.get("data");
		String event = (String) map.get("event");
		String javascriptName = (String) map.get("javascriptName");
		String functionParameter = (String) map.get("functionParameter");
		System.out.println("Inside the query invoker event"+event);
		String result = null;
		if (execute!=null && execute.equals("update")) {
			result=null;
		} else if (execute!=null && execute.equals("select")) {
			result=executeSelectQueryForHtml(sqlQuery, parameters,data,event,javascriptName,functionParameter);
			return result;
		}
		return result;
		
	}
	

	/* Used to process the Select query */

	public JSONObject executeSelectQuery(String sqlQuery, Object[] parameters) {
		
	
		System.out.println("executeSelectQuery Method called");
		log.info("executeSelectQuery Method called");
		CachedRowSet cRowSet = qp.executeSelectQuery(sqlQuery, parameters);

		ArrayList<String> ar = new ArrayList<String>();
		try {
			JSONObject jsonObject = new JSONObject();

			Collection<?> col = cRowSet.toCollection();
			ResultSetMetaData meta = cRowSet.getMetaData();
			int colCnt = cRowSet.getMetaData().getColumnCount();

			JSONArray jsonArray = null;
			jsonArray = new JSONArray();
			while (cRowSet.next()) {
				
				JSONObject jsonTmp = new JSONObject();

				for (int i = 1; i <= colCnt; i++) {
					jsonTmp.put(meta.getColumnName(i), cRowSet.getObject(i));
				}
				jsonArray.add(jsonTmp);
 
			}
			jsonObject.put("Table", jsonArray);

			System.out.println(jsonObject);
				
		return jsonObject;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/* Added for processing select query with html output */
	public String executeSelectQueryForHtml(String sqlQuery, Object[] parameters,String data,String event,String javascriptName,String functionParameter) {
		
		
		System.out.println("executeSelectQuery Method called for html");
		log.info("executeSelectQuery Method called for html");
		CachedRowSet cRowSet = qp.executeSelectQuery(sqlQuery, parameters);
		try {
			JSONObject jsonObject = new JSONObject();
			String result="";
			ResultSetMetaData meta = cRowSet.getMetaData();
			int colCnt = cRowSet.getMetaData().getColumnCount();
			
			String delimeter="[,]";
			String[] ids = data.split(delimeter);			
			String[] id = functionParameter.split(delimeter);	
			JSONArray jsonArray = null;
			jsonArray = new JSONArray();
			while (cRowSet.next()) {
				
				JSONObject jsonTmp = new JSONObject();

				for (int i = 1; i <= colCnt; i++) {
					jsonTmp.put(meta.getColumnName(i), cRowSet.getObject(i));
				}
				jsonArray.add(jsonTmp);				
			}
			jsonObject.put("Table", jsonArray);
			
			JSONArray jArray = jsonObject.getJSONArray("Table");
			System.out.println("array size is "+jArray.size());
			System.out.println("Inside the Selectquery invoker event"+event);
			for(int i = 0;i <jArray.size();i++){
				JSONObject resultObject=(JSONObject) jsonObject.getJSONArray("Table").get(i);
				System.out.println("----->"+event);
				if(!event.equalsIgnoreCase("undefined")){
					result+="<a href=\"#\">";
				}
			 result+="<li id=\""+resultObject.get(ids[i])+"\" value=\""+resultObject.get(ids[i].trim())+"\" ";
				if(!event.equalsIgnoreCase("undefined")){
					result+=event+"=\""+javascriptName+"(";
					 for(int k=0;k<id.length;k++){
						 	if(k==(id.length-1)){
						 		result+="'"+resultObject.get(ids[k])+"'";
						 	}
						 	else{
						 		result+="'"+resultObject.get(ids[k])+"',";
						 	}
							
						}					 
							 result+=")\"";
						
				}
			
			 result+=" >";
					for(int k=0;k<ids.length;k++){
						if(k==(ids.length-1)){
							result+=resultObject.get(ids[k]);
					 	}
					 	else{
					 		result+=resultObject.get(ids[k])+"--";
					 	}						
					}
					 
			 result+="</li>";
			 if(!event.equalsIgnoreCase("undefined")){
					result+="</a>";
				}
			}
			System.out.println(" final result is "+result);
				
		return result;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
