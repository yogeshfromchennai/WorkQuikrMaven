package com.hexaware.workquikr.console.service.base;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.workquikr.console.vo.AdapterVo;
import com.hexaware.workquikr.console.vo.ApplicationVO;

public interface CatalogService {

	public ArrayList<ApplicationVO> getAllApplications();
	public List<AdapterVo> getAllAdapters();
	public CachedRowSet getSourcePathAndwebId(String appname);
	public boolean removeUndeployedApp(Object[] parameters);
	public  void insertApplication(Object[] parameters);
	public void insertEnvironmentInfo(Object[] parameters);
	public void insertAdapterDeployment(Object[] parameters);
	public boolean removeUndeployedAdapter(Object[] parameters);

	}
