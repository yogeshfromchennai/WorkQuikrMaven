package com.hexaware.workquikr.console;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.util.JavaResultHelper;
import com.hexaware.workquikr.console.util.adapter.jaxb.java.Adapter;
import com.hexaware.workquikr.console.util.adapter.jaxb.java.Adapter.Operations.Operation;
import com.hexaware.workquikr.console.util.adapter.jaxb.java.Adapter.Operations.Operation.InputDatatype;
import com.hexaware.workquikr.console.util.adapter.jaxb.java.Adapter.Operations.Operation.InputDatatype.Parameter;
import com.hexaware.workquikr.console.util.adapter.jaxb.java.XmlJavaHelper;


/**
 * Servlet implementation class JavaInvoker
 */
public class JavaInvoker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Logger logger = LogFactory.getLogger(this.getClass());
	JavaResultHelper resultHelper = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JavaInvoker() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		logger.info("*************************JAVA INVOKER STARTS*************************");
		PrintWriter out = response.getWriter();
		String jsonObject = null;
		Gson gson = new Gson();
		String adapterName = request.getParameter("name");
		String reqClass = "";	//request.getParameter("className");
		String param = request.getParameter("param");
		String paramKey = request.getParameter("paramKey");
		String adapterType = "";
		String methodName = request.getParameter("method");
		InputDatatype inputDataType;
		String returnType = "";
		String returnDataType = "";
		
		String reqFormat=request.getParameter("format");
		String reqDisplayFormat=request.getParameter("displayFormat");
		String reqData=request.getParameter("data");
		String reqEvent=request.getParameter("event");
		String reqJavascriptName=request.getParameter("javascriptName");
		String reqfunctionParameter=request.getParameter("functionParameter");
		JSONObject jsonResult = null;
		Object paramObj[] = null;
		Class[] paramType = null;
		
		InputStream in = getServletContext().getResourceAsStream("/Adapters/"+adapterName+".xml");
		resultHelper = new JavaResultHelper();
		if(in != null){
			Adapter adapter = new XmlJavaHelper().getFromXml(in);
			
			
			Map<String, Object> map = new HashMap<String, Object>();
			adapterType = adapter.getType();
			logger.info("Adapter is "+adapter+" Parameter is "+param+" Adapter Type is "+adapterType);
			
			if(adapter.getType() != null && adapter.getType().equalsIgnoreCase("Java")){
				reqClass = adapter.getClazz();
				logger.info("Adapter Class is "+reqClass);
				if(adapter.getClazz() != null && !adapter.getClazz().equals("")){
					Map<String, Adapter.Operations.Operation> procedureMap = adapter.getProcedureAsMap();
					//logger.info("Procedure Map "+procedureMap);
					
					Operation procedure = procedureMap.get(methodName);
					//for(Entry<String, Operation> entry:procedureMap.entrySet()){
						//procedure = entry.getValue();
						if(procedure != null){
							inputDataType = procedure.getInputDatatype();
							returnDataType = procedure.getReturnDatatype();
							returnType = procedure.getReturnType();
							String[] params = null;
							List<Parameter> input = inputDataType.getParameter();
							Object returnValue = null;
							logger.info("Method "+methodName+" Procedure "+procedure.getMethodName());
							
							if(param!=null && !param.equals("")){
								params = param.split(",");
								paramObj = new Object[params.length];
								
								paramType = new Class[input.size()];
								String[] keyParams = null;
								if(paramKey!=null && !paramKey.equals("")){
									 keyParams = paramKey.split(",");
								}
								for(int i = 0;i< input.size();i++){
									Parameter pro = input.get(i);
									if(pro.getValue().equalsIgnoreCase("String")){
										paramType[i] = String.class; 
										paramObj[i] = params[i];
									} else if(pro.getValue().equalsIgnoreCase("int")){
										paramType[i] = Integer.class; 
										paramObj[i] = new Integer(params[i]);
									} else if(pro.getValue().equalsIgnoreCase("float")){
										paramType[i] = Float.TYPE; 
										paramObj[i] = new Float(params[i]);
									} else if(pro.getValue().equalsIgnoreCase("boolean")){
										paramType[i] = Boolean.TYPE; 
										paramObj[i] = new Boolean(params[i]);
									} else if(pro.getValue().equalsIgnoreCase("date")){
										paramType[i] = Date.class; 
										paramObj[i] = params[i];
									} else if(pro.getValue().equalsIgnoreCase("list")){
										paramType[i] = List.class; 
										List list = new ArrayList();
										for(String element : params)
										list.add(element);
										paramObj = new Object[1];
										paramObj[i] = list;
									} else if(pro.getValue().equalsIgnoreCase("map")){
										paramType[i] = Map.class; 
										Map mapValue = new HashMap();
										for(int j=0;j<keyParams.length;j++){
											mapValue.put(keyParams[i], params[i]);
										}
										paramObj = new Object[1];
										paramObj[i] = map;
									} else{ 	//if(pro.getValue().equalsIgnoreCase("vo")){
										
										Object[] paramVOObj = null;
										paramVOObj = new Object[params.length];
										try {
											Class thisClass = Class.forName(pro.getValue());
											Object thisObject = thisClass.newInstance();
											Method[] methods = thisObject.getClass().getMethods();
											
											for(int j = 0; j< keyParams.length;j++){
												for(Method method : methods){
													if(("set"+keyParams[j]).equalsIgnoreCase(method.getName())){
														
														System.out.println("Method name "+method.getName());
														System.out.println("Method params "+" "+method.getParameterTypes()[0]);
														
														Method thisMethod = thisClass.getDeclaredMethod(method.getName(), method.getParameterTypes());
														thisMethod.setAccessible(true);
														if(method.getParameterTypes()[0].toString().contains("Integer"))
															paramVOObj[j] = new Integer(params[j]);
														else if(method.getParameterTypes()[0].toString().contains("Float"))
															paramVOObj[j] = new Float(params[j]);
														else if(method.getParameterTypes()[0].toString().contains("Boolean"))
															paramVOObj[j] = new Boolean(params[j]);
														else
															paramVOObj[j] = params[j];
														returnValue = thisMethod.invoke(thisObject, paramVOObj[j]);
														System.out.println("Values set successfully");
														//Object[] p=(Object[]) paramObj[j];
														
														//returnValue = classCallByReflection(pro.getValue(), method.getName(), method.getParameterTypes(), paramObj);
													}
													
												}
											}
											paramType[i] = thisClass; 
											paramObj = new Object[paramType.length];
											
											paramObj[i] = thisObject;
										} catch (ClassNotFoundException e) {
											// TODO Auto-generated catch block
											logger.error("ClassNotFoundException "+e.getMessage());
										} catch (InstantiationException e) {
											// TODO Auto-generated catch block
											logger.error("InstantiationException "+e.getMessage());
										} catch (IllegalAccessException e) {
											// TODO Auto-generated catch block
											logger.error("IllegalAccessException "+e.getMessage());
										} catch (SecurityException e) {
											// TODO Auto-generated catch block
											logger.error("SecurityException "+e.getMessage());
										} catch (NoSuchMethodException e) {
											// TODO Auto-generated catch block
											logger.error("NoSuchMethodException "+e.getMessage());
										} catch (IllegalArgumentException e) {
											// TODO Auto-generated catch block
											logger.error("IllegalArgumentException "+e.getMessage());
										} catch (InvocationTargetException e) {
											// TODO Auto-generated catch block
											logger.error("InvocationTargetException "+e.getMessage());
										}

									}
									logger.info("Parameter Type ---> "+paramType[i].toString());
									logger.info("Parameter ---> "+paramObj[i].toString());
									
								}
							}
							
							returnValue = classCallByReflection(reqClass, methodName, paramType, paramObj);
							
							map.put("result", returnValue);
							map.put("returnType", returnType);
							map.put("returnDataType", returnDataType);
							
							if(reqFormat.equalsIgnoreCase("json")){
								logger.info("Format is JSON "+returnValue);
								//jsonObject = gson.toJson(returnValue);
								jsonResult = new JSONObject();
								jsonResult.put("returnValue", returnValue);
								logger.info("JSON Format "+jsonResult);
								out.write(jsonResult+"");
							} else if(reqFormat.equalsIgnoreCase("html")){
								logger.info("Format is HTML");
								map.put("format", reqFormat);
								map.put("displayFormat",reqDisplayFormat);
								map.put("data",reqData);
								map.put("event",reqEvent);
								map.put("javascriptName",reqJavascriptName);
								map.put("functionParameter",reqfunctionParameter);
								//logger.info("HTML Format "+map.get("data"));
								String result = resultHelper.getResultAsHtml(map);
								out.write(result);
							}

					}
				}
			}
		}
		
		logger.info("*************************JAVA INVOKER ENDS*************************");
	}
	
	public Object classCallByReflection(String reqClass, String methodName, Class[] paramType, Object[] paramObj){
		
		Object returnValue = null;
		logger.info("Class Name "+reqClass+" Method Name "+methodName);
		/*for(int i=0;i<paramObj.length;i++)
			System.out.println("Param Types "+paramType[0]+" Parameters "+paramObj[i]);*/
		try { 
			Class thisClass = Class.forName(reqClass);

			Object thisObject = thisClass.newInstance();
			
			Method thisMethod = thisClass.getDeclaredMethod(methodName, paramType);
			thisMethod.setAccessible(true);
			returnValue = thisMethod.invoke(thisObject, paramObj);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			logger.error("ClassNotFoundException "+e.getMessage());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			logger.error("InstantiationException "+e.getMessage());
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalAccessException "+e.getMessage());
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			logger.error("SecurityException "+e.getMessage());
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			logger.error("NoSuchMethodException "+e.getMessage());
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			logger.error("IllegalArgumentException "+e.getMessage());
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			logger.error("InvocationTargetException "+e.getMessage());
		}
		logger.info("Class called using reflection and the return Object is "+returnValue);
		return returnValue;
	}

}
