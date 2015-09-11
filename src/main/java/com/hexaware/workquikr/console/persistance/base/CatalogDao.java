package com.hexaware.workquikr.console.persistance.base;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.workquikr.console.vo.AdapterVo;
import com.hexaware.workquikr.console.vo.ApplicationVO;

public interface CatalogDao {
	public ArrayList<ApplicationVO> getAllApplications();
	public List<AdapterVo> getAllAdapters();
	public CachedRowSet getSourcePathAndwebId(String appname);
	public boolean removeUndeployedApp(Object[] parameters);
	public  void insertApplication(Object[] parameters);
	public void insertEnvironmentInfo(Object[] parameters);
	public void insertAdapter(Object[] parameters);
	public boolean removeUndeployedAdapter(Object[] parameters);
	
}
