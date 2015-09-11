package com.hexaware.workquikr.console.persistance.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.CachedRowSet;

import com.hexaware.framework.dao.QueryProcessor;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
import com.hexaware.workquikr.console.persistance.base.CatalogDao;
import com.hexaware.workquikr.console.vo.AdapterVo;
import com.hexaware.workquikr.console.vo.AppEnvironmentVO;
import com.hexaware.workquikr.console.vo.ApplicationVO;
import com.hexaware.workquikr.console.vo.SlaveVO;

public class CatalogDaoImpl implements CatalogDao {

	private QueryProcessor qp = null;
	private Logger log = LogFactory.getLogger(this.getClass());

	public CatalogDaoImpl() {
		qp = new QueryProcessor();
	}

	@Override
	public ArrayList<ApplicationVO> getAllApplications() {

		String query = "select app_master.app_id,app_master.app_name,app_slave.web_id,app_slave.app_source,app_slave.version,app_slave.deployed_date,app_environment.device_type from app_master,app_slave,app_environment where app_master.app_id=app_slave.app_id and app_slave.web_id=app_environment.web_id order by  app_master.app_id,app_slave.web_id,app_environment.device_type ;";

		CachedRowSet cRowSet = qp.executeSelectQuery(query, null);
		ApplicationVO depVo = null;
		SlaveVO slvVO = null;
		AppEnvironmentVO appenvVo = null;
		ArrayList<ApplicationVO> deployedDetails = new ArrayList<ApplicationVO>();
		ArrayList<SlaveVO> slaveDetails = null;
		List<AppEnvironmentVO> appEnvDetails = null;
		int tmpAppid = -1;
		int tmpWebId = -1;
		try {
			while (cRowSet.next()) {
				if (tmpAppid != cRowSet.getInt(1)) {
					tmpAppid = cRowSet.getInt(1);

					if (tmpWebId != cRowSet.getInt(3)) {
						depVo = new ApplicationVO();
						depVo.setId(cRowSet.getInt(1));
						depVo.setContext(cRowSet.getString(2));
						slaveDetails = new ArrayList<SlaveVO>();
						tmpWebId = cRowSet.getInt(3);
						appEnvDetails = new ArrayList<AppEnvironmentVO>();
						slvVO = new SlaveVO();
						slvVO.setWebId(cRowSet.getInt(3));
						slvVO.setSourcePath(cRowSet.getString(4));
						slvVO.setVersion(cRowSet.getFloat(5));
						slvVO.setDeploymentDate(cRowSet.getDate(6));
						appenvVo = new AppEnvironmentVO();
						appenvVo.setDeviceType(cRowSet.getString(7));
						appEnvDetails.add(appenvVo);
						slvVO.setApEnvList(appEnvDetails);
						slaveDetails.add(slvVO);
						depVo.setSlvaeList(slaveDetails);

					} else {
						appenvVo = new AppEnvironmentVO();
						appenvVo.setDeviceType(cRowSet.getString(7));
						appEnvDetails.add(appenvVo);
						slvVO.setApEnvList(appEnvDetails);
					}
					deployedDetails.add(depVo);
				} else {
					if (tmpWebId != cRowSet.getInt(3)) {
						tmpWebId = cRowSet.getInt(3);
						appEnvDetails = new ArrayList<AppEnvironmentVO>();
						slvVO = new SlaveVO();
						slvVO.setWebId(cRowSet.getInt(3));
						slvVO.setSourcePath(cRowSet.getString(4));
						slvVO.setVersion(cRowSet.getFloat(5));
						slvVO.setDeploymentDate(cRowSet.getDate(6));
						appenvVo = new AppEnvironmentVO();
						appenvVo.setDeviceType(cRowSet.getString(7));
						appEnvDetails.add(appenvVo);
						slvVO.setApEnvList(appEnvDetails);
						slaveDetails.add(slvVO);
						depVo.setSlvaeList(slaveDetails);
					} else {
						appenvVo = new AppEnvironmentVO();
						appenvVo.setDeviceType(cRowSet.getString(7));
						appEnvDetails.add(appenvVo);
						slvVO.setApEnvList(appEnvDetails);
					}
				}
			}
			System.out.println(deployedDetails);
		} catch (SQLException ex) {

			ex.printStackTrace();
		}
		log.info("getDeployedAppList method ends");
		return deployedDetails;
	}

	@Override
	public List<AdapterVo> getAllAdapters() {
		List<AdapterVo> adapterDetails=new ArrayList<AdapterVo>();	
		String query="SELECT adapter_id, adapter_name, adapter_loc, deployed_date, adapter_type, max(version) from adapters group by adapter_name order by adapter_type;";
		CachedRowSet cRowSet=qp.executeSelectQuery(query, null);
		AdapterVo adpVo=null;
		try {
			while (cRowSet.next()) {
				adpVo=new AdapterVo();
				
				adpVo.setId_workquikr_adapter(cRowSet.getInt(1));
				adpVo.setName(cRowSet.getString(2));
				adpVo.setResourceLocation(cRowSet.getString(3));
				adpVo.setDeployedDate(cRowSet.getDate(4));
				adpVo.setType(cRowSet.getString(5));
				adpVo.setVersion(cRowSet.getFloat(6));
				adapterDetails.add(adpVo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("getadapterDetails  : "+adapterDetails);
		log.info("getadapterDetails  : "+adapterDetails);
		return adapterDetails;
	}

	@Override
	public CachedRowSet getSourcePathAndwebId(String appname) {
		log.info("inside the database helper method for bulk delete");
		String query="select b.web_id,b.app_source from app_master a,app_slave b where app_name='"+appname+"' and a.app_id=b.app_id;";
		System.out.println(appname);
		CachedRowSet cRowSet=qp.executeSelectQuery(query, null);
		return cRowSet;
	}

	@Override
	public boolean removeUndeployedApp(Object[] parameters) {
		String query="DELETE FROM app_slave WHERE app_source=?";
		int i=qp.executeUpdateQuery(query, parameters);
		log.info("Delete undeployed Apps called "+i);
		String appSource=(String) parameters[0];
		String trim=appSource.replaceAll("[0-9]", "");
		String result=trim.replaceAll("[.]", "");
		String querySelectSlave="Select * from app_slave where app_source like '"+result+"%';";		
		CachedRowSet cRowSet=qp.executeSelectQuery(querySelectSlave, null);
			try {
				if(cRowSet.next()){
					log.info("Other Versions are Available");
				}
				else{
					String queryDelMaster=" Delete from app_master where app_name='"+result+"';";
					int q=qp.executeUpdateQuery(queryDelMaster, null);
					log.info("Deleted from master Table"+q);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return true; 
	}

	@Override
	public void insertApplication(Object[] parameters) {
		int appId = 0;
		Object[] param ={parameters[0]};
		String query = "SELECT app_name FROM app_master WHERE app_name=?";
		CachedRowSet cRowSet=qp.executeSelectQuery(query, param);
		try {
			while(!cRowSet.next()){
				String query1 = "INSERT INTO app_master(app_name) VALUES (?);";
				int i=qp.executeUpdateQuery(query1, param);	
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			log.error(e1.getMessage());
		}
		String query2 = "SELECT app_id FROM app_master WHERE app_name=?";
		try {
			CachedRowSet cRowSets=qp.executeSelectQuery(query2, param);
			while (cRowSets.next()) {
				appId=cRowSets.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		Object[] param1 = {appId, parameters[1], parameters[2],parameters[3]};
		String query3 = "INSERT INTO app_slave (app_id, app_source, version, deployed_date) VALUES (?,?,?,?)";
		int j=qp.executeUpdateQuery(query3, param1);	
		log.info("insertDeploymentInfo method ends");
		
	}

	@Override
	public void insertEnvironmentInfo(Object[] parameters) {
		int appId = 0;
		int webId = 0;
		Object[] param1 ={parameters[0]};
		String query1 = "SELECT app_id FROM app_master WHERE app_name=?";
		try {
			CachedRowSet cRowSet=qp.executeSelectQuery(query1, param1);
			while (cRowSet.next()) {
				appId=cRowSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		Object[] param2 = {appId};
		String query2 = "SELECT web_id FROM app_slave WHERE app_id=?";
		try {
			CachedRowSet cRowSet=qp.executeSelectQuery(query2, param2);
			while (cRowSet.next()) {
				webId=cRowSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}
		
		Object[] param3 = {webId, parameters[1], parameters[2], parameters[3]};
		String query3 = "INSERT INTO app_environment (web_id, device_type, notification_required, notification_type) VALUES (?,?,?,?)";
		int j=qp.executeUpdateQuery(query3, param3);	
		log.info("insertEnvironmentInfo method ends");
		
	}

	@Override
	public void insertAdapter(Object[] parameters) {
		 String query="INSERT INTO adapters (adapter_name, adapter_loc, deployed_date, adapter_type, version) VALUES (?, ?, ?, ?, ?)";
         int i=qp.executeUpdateQuery(query, parameters);         
         log.info("insertAdapterDeployment method ends");
		
	}

	@Override
	public boolean removeUndeployedAdapter(Object[] parameters) {
		String query="DELETE FROM adapters WHERE adapter_loc=?";
		int i=qp.executeUpdateQuery(query, parameters);
		System.out.println("Delete undeployed Apps called "+i);
		log.info("Delete undeployed Apps called "+i);
		return true;
		 
	}



}
