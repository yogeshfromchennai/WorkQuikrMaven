package com.hexaware.workquikr.console.delegate;

import java.util.ArrayList;
import java.util.List;
import javax.sql.rowset.CachedRowSet;
import com.hexaware.workquikr.console.service.base.CatalogService;
import com.hexaware.workquikr.console.service.impl.CatalogServiceImpl;
import com.hexaware.workquikr.console.vo.AdapterVo;
import com.hexaware.workquikr.console.vo.ApplicationVO;

public class CatalogDelegate {

	CatalogService catalogService = new CatalogServiceImpl();
	public ArrayList<ApplicationVO> getAllApplications(){
		return catalogService.getAllApplications();
	}
	public List<AdapterVo> getAllAdapters(){
		return catalogService.getAllAdapters();
	}
	public CachedRowSet getSourcePathAndwebId(String appname){
		return catalogService.getSourcePathAndwebId(appname);
	}
	public boolean removeUndeployedApp(Object[] parameters){
		return catalogService.removeUndeployedApp(parameters);
	}
	public  void insertApplication(Object[] parameters){
		catalogService.insertApplication(parameters);
	}
	public void insertEnvironmentInfo(Object[] parameters){
		catalogService.insertEnvironmentInfo(parameters);
	}
	public void insertAdapter(Object[] parameters){
		catalogService.insertAdapterDeployment(parameters);
	}
	public boolean removeUndeployedAdapter(Object[] parameters){
		return catalogService.removeUndeployedAdapter(parameters);
	}
	
}