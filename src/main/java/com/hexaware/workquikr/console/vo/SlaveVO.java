package com.hexaware.workquikr.console.vo;

import java.util.Date;
import java.util.List;

public class SlaveVO {
	private String sourcePath;
	private float version;
	private Date deploymentDate;
	private int webId;
	private List<AppEnvironmentVO> apEnvList;
	
	public List<AppEnvironmentVO> getApEnvList() {
		return apEnvList;
	}
	public void setApEnvList(List<AppEnvironmentVO> apEnvList) {
		this.apEnvList = apEnvList;
	}
	public String getSourcePath() {
		return sourcePath;
	}
	public int getWebId() {
		return webId;
	}
	public void setWebId(int webId) {
		this.webId = webId;
	}
	public void setSourcePath(String sourcePath) {
		this.sourcePath = sourcePath;
	}
	public float getVersion() {
		return version;
	}
	public void setVersion(float version) {
		this.version = version;
	}
	public Date getDeploymentDate() {
		return deploymentDate;
	}
	public void setDeploymentDate(Date deploymentDate) {
		this.deploymentDate = deploymentDate;
	}

	@Override
	public String toString() {
	
		return "Slave :"+webId+","+version+","+apEnvList;
		
	}
}
