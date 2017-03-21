package zxh.pageModel;

import java.io.Serializable;
import java.sql.Timestamp;

public class Culinfo implements Serializable {
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String ids;
	private String states;
	private String mrHouseIds;
	private String mrHouseNames;
	private String mrvarietyIds;
	private String mrvarietyNames;

	
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}
	public String getMrvarietyIds() {
		return mrvarietyIds;
	}
	public void setMrvarietyIds(String mrvarietyIds) {
		this.mrvarietyIds = mrvarietyIds;
	}
	public String getMrvarietyNames() {
		return mrvarietyNames;
	}
	public void setMrvarietyNames(String mrvarietyNames) {
		this.mrvarietyNames = mrvarietyNames;
	}
	public String getMrHouseIds() {
		return mrHouseIds;
	}
	public void setMrHouseIds(String mrHouseIds) {
		this.mrHouseIds = mrHouseIds;
	}
	public String getMrHouseNames() {
		return mrHouseNames;
	}
	public void setMrHouseNames(String mrHouseNames) {
		this.mrHouseNames = mrHouseNames;
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
	private Timestamp culstarttime;
	private String chargeman;
	private String culstate;
	private Timestamp culendtime;

	public Timestamp getCulendtime() {
		return culendtime;
	}
	public void setCulendtime(Timestamp culendtime) {
		this.culendtime = culendtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Timestamp getCulstarttime() {
		return culstarttime;
	}
	public void setCulstarttime(Timestamp culstarttime) {
		this.culstarttime = culstarttime;
	}
	public String getChargeman() {
		return chargeman;
	}
	public void setChargeman(String chargeman) {
		this.chargeman = chargeman;
	}
	public String getCulstate() {
		return culstate;
	}
	public void setCulstate(String culstate) {
		this.culstate = culstate;
	}
	
}
