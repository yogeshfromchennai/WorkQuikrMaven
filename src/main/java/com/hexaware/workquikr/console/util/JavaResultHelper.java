package com.hexaware.workquikr.console.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;

public class JavaResultHelper {

	String returnType = "";
	String displayFormat = "";
	String data = "";
	String event = "";
	String javascriptName = "";
	String functionParameter = "";
	String returndataType = "";
	String delimeter="[,]";
	
	private Logger logger = LogFactory.getLogger(this.getClass());
	public JSONObject getResultAsJson(Map<String, Object> map){
		returnType = (String) map.get("returnType");
		returndataType = (String) map.get("returnDataType");
		JSONObject jsonObject = new JSONObject();
		//String jsonResult = "";
		//Gson gson = new Gson();
		Object result = null;
		result = map.get("result");
		if(returnType.equalsIgnoreCase("simple")){
			//jsonResult = gson.toJson(result);
			jsonObject.put("resultValue", result);
		} else if(returnType.equalsIgnoreCase("complex")){
			//jsonResult = gson.toJson(result);
			jsonObject.put("resultValue", result);
		} else if(returnType.equalsIgnoreCase("none")){
			jsonObject.put("resultValue", "Classes Executed");
		} else if(returnType.equalsIgnoreCase("webservice")){
			//jsonResult = gson.toJson(result);
			jsonObject.put("resultValue", result);
		}
			
		logger.info("Return json object is "+jsonObject);
		return jsonObject;
	}
	
	public String getResultAsHtml(Map<String, Object> map){
		returnType = (String) map.get("returnType");
		displayFormat = (String) map.get("displayFormat");
		data = (String) map.get("data");
		event = (String) map.get("event");
		javascriptName = (String) map.get("javascriptName");
		functionParameter = (String) map.get("functionParameter");
		returndataType = (String) map.get("returnDataType");
		String[] id = data.split(delimeter);
		String listTag = "";
		String tableTag = "";
		
		logger.info("Return Type "+returnType+" Return Data Type "+returndataType+javascriptName+functionParameter);
		if(returnType.equalsIgnoreCase("simple")){
			Object result = map.get("result");
			if(displayFormat.equalsIgnoreCase("list")){
				listTag = "<li id=\""+result+"\" value=\""+result+"\" >"+result+"</li>";
				return listTag;
			} else if(displayFormat.equalsIgnoreCase("table")){
				tableTag = "<tr id=\""+result+"\" value=\""+result+"\" >"+result+"</tr>";
			}
		}else if(returnType.equalsIgnoreCase("complex")){
			Object result = map.get("result");
			if(displayFormat.equalsIgnoreCase("list")){
				if(returndataType.equalsIgnoreCase("vo")){
					logger.info("Getting inside COMPLEX LIST VO block");
					listTag = getvo(result);
				} else if(returndataType.equalsIgnoreCase("list")){
					logger.info("Getting inside COMPLEX LIST LIST block");
					listTag = getListValue(result);
				} else if(returndataType.equalsIgnoreCase("map")){
					logger.info("Getting inside COMPLEX LIST MAP block");
					listTag = getMapValue(result);
				} else if(returndataType.equalsIgnoreCase("json")){
					logger.info("Getting inside COMPLEX LIST JSON block");
					listTag = getJsonValue(result);
				}
				return listTag;
			} else if(displayFormat.equalsIgnoreCase("table")){
				tableTag = "<tr id=\""+result+"\" value=\""+result+"\" >"+result+"</tr>";
			}
		} else if(returnType.equalsIgnoreCase("none")){
			String result = "Classes executed";
			return result;
		}
		return null;
	}
	
	private String getvo(Object result) {
		// TODO Auto-generated method stub
		String[] id = data.split(delimeter);
		String[] functionParams = functionParameter.split(delimeter);
		String resultTag = "";
		logger.info("------------------------ > Function Parameters "+functionParams);
		
		ClassLoader classLoader = JavaResultHelper.class.getClassLoader();
		try {
	        
	        Object thisObject = result;
	        
	        Field[] fields = null;
	        fields = thisObject.getClass().getDeclaredFields();
	        
	        Method[] methods = null;
	        methods = thisObject.getClass().getDeclaredMethods();
	        
	        Object paramObj[] = null;
			Class[] paramType = null;
			String[] key ;
			String[] value = null;
			resultTag += "<a href=\"#\" "+event+"=\""+javascriptName+"('";
			// Executes method for functional parameters field
			
			for(int k=0;k<functionParams.length;k++){
				if(k==(functionParams.length-1)){
					for(Method method : methods){
						if(method.getName().contains("get") && method.getParameterTypes().length==0 && method.getName().equalsIgnoreCase("get"+functionParams[k])){
							//logger.info("Method Name "+method.getName());
							Method thisMethod = thisObject.getClass().getDeclaredMethod(method.getName(), paramType);
							Object resulte = thisMethod.invoke(thisObject, paramObj);
							resultTag += resulte.toString();
						}	
					}
				} else{
					for(Method method : methods){
						if(method.getName().contains("get") && method.getParameterTypes().length==0 && method.getName().equalsIgnoreCase("get"+functionParams[k])){
							//logger.info("Method Name "+method.getName());
							Method thisMethod = thisObject.getClass().getDeclaredMethod(method.getName(), paramType);
							Object resulte = thisMethod.invoke(thisObject, paramObj);
							resultTag += resulte.toString()+",";
						}	
					}
				}
			}
			
			resultTag += "');\">";
			
			resultTag += "<li id=\""+id[0]+"\" value=\"";
			// Executes method for value field
			for(Method method : methods){
				if(method.getName().contains("get") && method.getParameterTypes().length==0 && method.getName().equalsIgnoreCase("get"+id[0])){
					Method thisMethod = thisObject.getClass().getDeclaredMethod(method.getName(), paramType);
					Object resulte = thisMethod.invoke(thisObject, paramObj);
					resultTag += resulte.toString();
				}
			}
			resultTag += "\" >";
			
			// Executes method for data field
			
			for(int k=0;k<id.length;k++){
				if(k==(id.length-1)){
					for(Method method : methods){
						if(method.getName().contains("get") && method.getParameterTypes().length==0 && method.getName().equalsIgnoreCase("get"+id[k])){
							//logger.info("Method Name "+method.getName());
							Method thisMethod = thisObject.getClass().getDeclaredMethod(method.getName(), paramType);
							Object resulte = thisMethod.invoke(thisObject, paramObj);
							resultTag += resulte.toString();
						}	
					}
				} else{
					for(Method method : methods){
						if(method.getName().contains("get") && method.getParameterTypes().length==0 && method.getName().equalsIgnoreCase("get"+id[k])){
							//logger.info("Method Name "+method.getName());
							Method thisMethod = thisObject.getClass().getDeclaredMethod(method.getName(), paramType);
							Object resulte = thisMethod.invoke(thisObject, paramObj);
							resultTag += resulte.toString()+" - ";
						}	
					}
				}
			}
			
			resultTag += "</li></a>";
			logger.info(resultTag);
			
			
	    } catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
	    	logger.error("IllegalArgumentException "+e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("IllegalAccessException "+e.getMessage());
			
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("InvocationTargetException "+e.getMessage());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error("SecurityException "+e.getMessage());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error("NoSuchMethodException "+e.getMessage());
		}
		return resultTag;
	}

	private String getListValue(Object result) {
		// TODO Auto-generated method stub
		String[] id = data.split(delimeter);
		String resultTag = "";
		try{
			logger.info("Inside getListValue ");
			//JSONObject jsonObj = new JSONObject();
			JsonParser parser = new JsonParser();
			JsonElement j = null;
			try{
			j = parser.parse((String)result);
			} catch (Exception e) {
				// TODO: handle exception
				resultTag = "Empty";
				return resultTag;
			}
			JsonObject jsonObj = j.getAsJsonObject();
			//String jsonObj = null;
			//jsonObj = result.toString();
			Gson gson = new Gson();
			
			JsonElement je = null; 
			for (final Entry<String, JsonElement> entry : jsonObj.entrySet()) {
				   final String key = entry.getKey();
				   final JsonElement value = entry.getValue();
				   je = value;
			}
			Collection c = gson.fromJson(je, Collection.class);
			for (Iterator iterator = (c).iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			
			if(object instanceof String || object instanceof Integer || object instanceof Float || object instanceof Boolean || object instanceof Date){
				resultTag += "<a href=\"#\" "+event+"=\""+javascriptName+"('"+object+"');\"><li id=\""+object+"\" value=\""+object+"\">"+object+"</li></a>";
			} else if(object instanceof List){
				resultTag += getListValue(object);	//resultTag += 
			} else if(object instanceof Map){
				resultTag += getMapValue(object);	//resultTag += 
			} else {
				resultTag += getvo(object);
			}
			
		}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		/*JSONArray jArray = new JSONArray();
		jArray = json.getJSONArray("return");
		for(int i= 0;i<jArray.size();i++){
			list.addAll(jArray);
		}
		for(int i=0;i<list.size();i++)*/
		logger.info("Result in getList "+resultTag);
		return resultTag;
	}

	private String getMapValue(Object result) {
		// TODO Auto-generated method stub
		String resultTag = "";
		String[] id = data.split(delimeter);
		String[] functionParams = functionParameter.split(delimeter);
		
		if(result instanceof Map){
			//System.out.println("Map Instance");
			String functionParamVar = "";
			String dataValue = "";
			Map tempMap = (Map) result;
			for(int k=0;k<functionParams.length;k++){
		    	functionParamVar += tempMap.get(functionParams[k]);
		    	if(k != (functionParams.length-1)){
		    		functionParamVar += ",";
		    	}
		    }
			logger.info("Parameters for function is "+functionParamVar);
		    for(int l=0;l<id.length;l++){
		    	dataValue += tempMap.get(id[l]);
		    	if(l != (id.length-1)){
		    		dataValue += "  --  ";
		    	}
		    }
		    logger.info("Data for the list is "+dataValue);
			/*for (Map.Entry e : ( (Map<String,Object>) result).entrySet()) {  
			    Object key = e.getKey();  
			    Object value = e.getValue();  
			    // now do something with key and value
			    
			    for(int k=0;k<id.length;k++){
			    for(int l = 0;l<functionParams.length;l++)
					if(key.equals(functionParams[l]) || key.equals(id)){	//key.equals(functionParams[l]) || 
					    if(value instanceof String || value instanceof Integer || value instanceof Float || value instanceof Boolean || value instanceof Date){
					    	resultTag += "<a href=\"#\" "+event+"=\""+javascriptName+"('"+functionParamVar+"');\"><li id=\""+id[0]+"\" value=\""+tempMap.get(id[0])+"\">"+dataValue+"</li></a>";
						} else if(value instanceof List){
							resultTag += getListValue(value);	
						} else if(value instanceof Map){
							resultTag += getMapValue(value);	
						} else {
							resultTag += getvo(value);
						}
			    System.out.println("key "+key.toString()+" Value "+value.toString());
					}
					}
			}*/
			resultTag += "<a href=\"#\" "+event+"=\""+javascriptName+"('"+functionParamVar+"');\"><li id=\""+id[0]+"\" value=\""+tempMap.get(id[0])+"\">"+dataValue+"</li></a>";
		} else{
			System.out.println("No instance");
		}
		
		
		/*for (Iterator iterator = ( (Collection) result).iterator(); iterator.hasNext();) {
			Object object = (Object) iterator.next();
			if(object instanceof String || object instanceof Integer || object instanceof Float || object instanceof Boolean || object instanceof Date){
				jArray.add(object);
				json.put("return", jArray);
			} else if(object instanceof List){
				getListValue(object);
			} else if(object instanceof Map){
				getMapValue(object);
			} else {
				json = getvo(object);
			}
			
		}*/
		logger.info("Result in getMap "+resultTag);
		return resultTag;
	}
	
	private String getJsonValue(Object result){
		String resultTag = "";
		
		/*JSONObject jObj = new JSONObject();
		jObj = JSONObject.fromObject(result);
		Map map = (Map) JSONObject.toBean(jObj, Map.class);
		*/
		JSONObject jObj = new JSONObject();
		jObj = JSONObject.fromObject(result);
		Map map = jObj;
		resultTag = getMapValue(map);
		
		return resultTag;
		
	}

}
