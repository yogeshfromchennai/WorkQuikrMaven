package com.hexaware.workquikr.console.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.workquikr.console.persistance.base.CatalogDao;
import com.hexaware.workquikr.console.persistance.impl.CatalogDaoImpl;
import com.hexaware.workquikr.console.service.base.CatalogService;
import com.hexaware.workquikr.console.vo.AdapterVo;
import com.hexaware.workquikr.console.vo.ApplicationVO;

public class CatalogServiceImpl implements CatalogService{
	private CatalogDao catalogDao;
	public CatalogServiceImpl()
	{
		catalogDao= new CatalogDaoImpl();
	}
	@Override
	public ArrayList<ApplicationVO> getAllApplications() {
		// TODO Auto-generated method stub
		
		return catalogDao.getAllApplications();
	}

	@Override
	public List<AdapterVo> getAllAdapters() {
		// TODO Auto-generated method stub
		return catalogDao.getAllAdapters();
	}
	@Override
	public CachedRowSet getSourcePathAndwebId(String appName) {
		// TODO Auto-generated method stub
		return catalogDao.getSourcePathAndwebId(appName);
	}
	@Override
	public boolean removeUndeployedApp(Object[] apps) {
		
		return catalogDao.removeUndeployedApp(apps);
	}
	@Override
	public void insertApplication(Object[] apps) {
		catalogDao.insertApplication(apps);
		
	}
	public void insertEnvironmentInfo(Object[] parameters){
		catalogDao.insertEnvironmentInfo(parameters);
	}
	@Override
	public void insertAdapterDeployment(Object[] adapters) {
		catalogDao.insertAdapter(adapters);
		
	}
	@Override
	public boolean removeUndeployedAdapter(Object[] adapters) {
		 return catalogDao.removeUndeployedAdapter(adapters);
	}
	

	
}
