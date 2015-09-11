package com.hexaware.workquikr.console.persistance.impl;

import com.hexaware.workquikr.console.persistance.base.RemoteDisableDao;
import com.hexaware.workquikr.console.vo.RemoteDisableVO;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.hexaware.framework.dao.QueryProcessor;
import com.hexaware.framework.logger.LogFactory;
import com.hexaware.framework.logger.Logger;
public class RemoteDisableDaoImpl implements RemoteDisableDao{
	private QueryProcessor qp = null;
	private Logger log = LogFactory.getLogger(this.getClass());

	public RemoteDisableDaoImpl() {
		// TODO Auto-generated constructor stub
		qp = new QueryProcessor();
	}

	@Override
	public List<RemoteDisableVO> getUsersList() {
		// TODO Auto-generated method stub

		List<RemoteDisableVO> registedapplication=new ArrayList<RemoteDisableVO>();
		String query="SELECT a.app_name,b.user_id,b.date,b.token,b.enabled FROM app_master a,users b where a.app_id=b.app_id;";
		CachedRowSet cRowSet=qp.executeSelectQuery(query, null);
		RemoteDisableVO regappVo=null;
		try {
			while (cRowSet.next()) {
				regappVo=new RemoteDisableVO();
				regappVo.setAppName(cRowSet.getString(1));
				regappVo.setUserID(cRowSet.getString(2));
				regappVo.setDate(cRowSet.getDate(3));
				regappVo.setToken(cRowSet.getString(4));
				regappVo.setEnabler(cRowSet.getString(5));
				registedapplication.add(regappVo);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("getadapterDetails  : "+registedapplication);
		log.info("getadapterDetails  : "+registedapplication);

		return registedapplication;
	}

}
