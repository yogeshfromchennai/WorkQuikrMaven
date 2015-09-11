package com.hexaware.workquikr.console.delegate;

import com.hexaware.workquikr.console.service.base.RemoteDisableService;
import com.hexaware.workquikr.console.service.impl.RemoteDisableServiceImpl;
import com.hexaware.workquikr.console.vo.ApplicationVO;
import com.hexaware.workquikr.console.vo.RemoteDisableVO;

import java.util.ArrayList;
import java.util.List;

public class RemoteDisableDelegate {
	RemoteDisableService remoteDisableServiceImpl=null;
	public RemoteDisableDelegate() {
		// TODO Auto-generated constructor stub
		remoteDisableServiceImpl=new RemoteDisableServiceImpl();
	}
	
	public List<RemoteDisableVO> getUsersList(){
		return remoteDisableServiceImpl.getUsersList();
	}
}
