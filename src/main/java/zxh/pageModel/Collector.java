package zxh.pageModel;

import java.io.Serializable;

public class Collector implements Serializable {
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String ids;
	private String q;
	private String mrHouseIds;
	private String mrHouseNames;
	
	
	public String getMrHouseNames() {
		return mrHouseNames;
	}
	public void setMrHouseNames(String mrHouseNames) {
		this.mrHouseNames = mrHouseNames;
	}
	public String getMrHouseIds() {
		return mrHouseIds;
	}
	public void setMrHouseIds(String mrHouseIds) {
		this.mrHouseIds = mrHouseIds;
	}
	public String getQ() {
		return q;
	}
	public void setQ(String q) {
		this.q = q;
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
	private String collectorName;
	private String collectorIp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCollectorName() {
		return collectorName;
	}
	public void setCollectorName(String collectorName) {
		this.collectorName = collectorName;
	}
	public String getCollectorIp() {
		return collectorIp;
	}
	public void setCollectorIp(String collectorIp) {
		this.collectorIp = collectorIp;
	}

	
}
