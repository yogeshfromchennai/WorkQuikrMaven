package com.hexaware.workquikr.console.service.impl;

import com.hexaware.workquikr.console.persistance.base.RemoteDisableDao;
import com.hexaware.workquikr.console.persistance.impl.RemoteDisableDaoImpl;
import com.hexaware.workquikr.console.service.base.RemoteDisableService;
import com.hexaware.workquikr.console.vo.RemoteDisableVO;

import java.util.List;

public class RemoteDisableServiceImpl implements RemoteDisableService {
	
	private RemoteDisableDao remoteDisableDaoImpl;
	public RemoteDisableServiceImpl() {
		// TODO Auto-generated constructor stub
		remoteDisableDaoImpl=new RemoteDisableDaoImpl();
	}

	@Override
	public List<RemoteDisableVO> getUsersList() {
		// TODO Auto-generated method stub
		return remoteDisableDaoImpl.getUsersList();
	}

}
