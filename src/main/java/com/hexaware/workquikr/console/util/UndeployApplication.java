package com.hexaware.workquikr.console.util;

import java.io.File;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.delegate.CatalogDelegate;

public class UndeployApplication {
	
	public Logger log = LogFactory.getLogger(this.getClass());
	
	public String deleteAll(String source, String webId,String deployDir) {
		System.out.println("**********************inside delete all method****************");		
		System.out.println(deployDir);
		String delimeters = "[,]";
		String[] ids = webId.split(delimeters);
		log.info("appp name got from query string  " + source + "\n length :"
				+ ids.length);
		
		int WebId;
		String sourcepath = null;
		CatalogDelegate catalog=new CatalogDelegate();
		CachedRowSet count = catalog.getSourcePathAndwebId(source);
		System.out.println("**************count**********"+count);
		Map<Integer, String> map = new HashMap<Integer, String>();
		try {
			while (count.next()) {
				WebId = count.getInt(1);
				sourcepath = count.getString(2);
				map.put(WebId, sourcepath);
			}

		} catch (SQLException e) {			
			e.printStackTrace();
		}
		System.out.println("map is :" +map);
		
		for (int i = 0; i < ids.length; i++) {
			System.out.println("Primary id" + ids[i]);
			String sourcePath = map.get(Integer.parseInt(ids [i]));
			System.out.println("Source Path : "+sourcePath);
			if(sourcePath!=null)
			{

			File file=new File(deployDir+"/"+sourcePath+".war");
			String trim=sourcePath;
			String trimk=sourcePath;
			String temp=trim.replaceAll(".war", "");
  	        String inter=temp.replaceAll("-web","");
  	        String result=inter.replaceAll("[0-9]", ""); 
  	        String trimmed=result.replaceAll("[.]", "");       
  	        System.out.println("Folder Name is "+trimmed);
	        String secureDownloadDeletePath=deployDir+"WorkQuikr-Download/"+trimmed+"/";
	        String tempk=trimk.replaceAll(".war", "");
	        String interk=tempk.replaceAll("web","debug");
	        System.out.println("file name "+interk);
	        System.out.println("Delete Path"+secureDownloadDeletePath);	            
			File fi=new File(secureDownloadDeletePath+interk+".apk");
			log.info("File existing "+file.exists());
			System.out.println("File Name is "+file.toString());
			log.info(file.toString());
			//deleting the file
			file.delete();
			fi.delete();
			Object[] parameters={sourcePath};
			catalog.removeUndeployedApp(parameters);
			}
			else
			{
				source=null;
			}
		
		}
		return source;
		
	}
	
}
