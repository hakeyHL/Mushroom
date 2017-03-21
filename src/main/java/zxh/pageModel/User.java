package zxh.pageModel;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable{
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String ids;
	private String names;
	private String collectorIds;
	private String collectorNames;
	private String monitorIds;
	private String monitorNames;
	
	
	
	
	public String getCollectorIds() {
		return collectorIds;
	}
	public void setCollectorIds(String collectorIds) {
		this.collectorIds = collectorIds;
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
	public String getMonitorNames() {
		return monitorNames;
	}
	public void setMonitorNames(String monitorNames) {
		this.monitorNames = monitorNames;
	}
	public String getNames() {
		return names;
	}
	public void setNames(String names) {
		this.names = names;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
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
	
	private String id;
	private String name;
	private String pwd;
	private String truename;
	private String usertype;
	private String tel;
	private Date createdatetime;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getTruename() {
		return truename;
	}
	public void setTruename(String truename) {
		this.truename = truename;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getCreatedatetime() {
		return createdatetime;
	}
	public void setCreatedatetime(Date createdatetime) {
		this.createdatetime = createdatetime;
	}
	public Date getModifydatetime() {
		return modifydatetime;
	}
	public void setModifydatetime(Date modifydatetime) {
		this.modifydatetime = modifydatetime;
	}
	private Date modifydatetime;
}
