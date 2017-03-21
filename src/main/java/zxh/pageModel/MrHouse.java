package zxh.pageModel;

import java.io.Serializable;

public class MrHouse implements Serializable {
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String ids;
	private String monitorIds;
	private String collectorIds;
	private String monitorNames;
	private String collectorNames;
	
	
	
	public String getMonitorNames() {
		return monitorNames;
	}
	public void setMonitorNames(String monitorNames) {
		this.monitorNames = monitorNames;
	}
	public String getCollectorNames() {
		return collectorNames;
	}
	public void setCollectorNames(String collectorNames) {
		this.collectorNames = collectorNames;
	}
	public String getMonitorIds() {
		return monitorIds;
	}
	public void setMonitorIds(String monitorIds) {
		this.monitorIds = monitorIds;
	}
	public String getCollectorIds() {
		return collectorIds;
	}
	public void setCollectorIds(String collectorIds) {
		this.collectorIds = collectorIds;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	private String id;
	private String mrHouseName;
	private String mrHouseState;



	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMrHouseName() {
		return mrHouseName;
	}
	public void setMrHouseName(String mrHouseName) {
		this.mrHouseName = mrHouseName;
	}
	public String getMrHouseState() {
		return mrHouseState;
	}
	public void setMrHouseState(String mrHouseState) {
		this.mrHouseState = mrHouseState;
	}
	
}
