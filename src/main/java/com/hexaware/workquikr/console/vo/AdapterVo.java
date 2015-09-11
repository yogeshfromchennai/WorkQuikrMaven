package com.hexaware.workquikr.console.vo;
import java.util.Date;
public class  AdapterVo {
	private String Name,ResourceLocation,Type;
	private int id_workquikr_adapter;
	private Date DeployedDate;
	private float version;
	
	public int getId_workquikr_adapter() {
		return id_workquikr_adapter;
	}
	public void setId_workquikr_adapter(int id_workquikr_adapter) {
		this.id_workquikr_adapter = id_workquikr_adapter;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		this.Name = name;
	}
	public float getVersion() {
		return version;
	}
	public void setVersion(float version) {
		this.version = version;
	}
	public String getResourceLocation() {
		return ResourceLocation;
	}
	public void setResourceLocation(String resourceLocation) {
		this.ResourceLocation = resourceLocation;
	}
	public String getType() {
		return Type;
	}
	public void setType(String type) {
		this.Type = type;
	}
	public Date getDeployedDate() {
		return DeployedDate;
	}
	public void setDeployedDate(Date deployedDate) {
		this.DeployedDate = deployedDate;
	}
	

}
