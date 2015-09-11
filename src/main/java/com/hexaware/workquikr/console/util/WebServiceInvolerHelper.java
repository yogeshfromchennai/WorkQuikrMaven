package com.hexaware.workquikr.console.util;

import com.google.gson.Gson;
import com.hexaware.framework.service.ServiceInvoker;

public class WebServiceInvolerHelper {
	


	public Object invokeServiceAdapter(String json, String wsdlURL,String jaxbPackageName, String portName, String targetNamespace,String serviceName,String inputClass )
	{
		  Gson gson = new Gson();
	        Object result=null;
	        try {
	              Class clazz = Class.forName(jaxbPackageName+"."+inputClass);
	              Object obj = clazz.newInstance();
	              Object input = gson.fromJson(json, obj.getClass());              
	              result = ServiceInvoker.invokeBusinessService(input, wsdlURL,targetNamespace, serviceName,portName, jaxbPackageName); 
	        
	        } catch (ClassNotFoundException e) {

	              // TODO Auto-generated catch block
	              e.printStackTrace();

	        } catch (InstantiationException e) {

	              // TODO Auto-generated catch block

	              e.printStackTrace();

	        } catch (IllegalAccessException e) {

	              // TODO Auto-generated catch block

	              e.printStackTrace();

	        } catch (Exception e) {

	              // TODO Auto-generated catch block

	              e.printStackTrace();

	        }

	        return result;
	}


}
