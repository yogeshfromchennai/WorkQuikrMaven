package com.hexaware.workquikr.console.util.adapter.jaxb.sql;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;


public class XMLHelper {

	
public Adapter getFromXML(InputStream in) {
	 

	String packageName = "com.hexaware.workquikr.console.util.adapter.jaxb.sql";
	JAXBContext context;
	try {
		context = JAXBContext.newInstance(packageName, this.getClass()
				.getClassLoader());
		Unmarshaller unmarshaller = context.createUnmarshaller();
		

		Adapter adapter = (Adapter) unmarshaller.unmarshal(in);
		
		
		 return adapter;
	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}

}
