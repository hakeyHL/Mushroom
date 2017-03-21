package zxh.pageModel;

import java.io.Serializable;

public class Variety implements Serializable {
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String ids;
	private String culinfoIds;
	private String mrVarietyIds;
	private String mrVarietyNames;
	
	
	
	
	public String getMrVarietyIds() {
		return mrVarietyIds;
	}
	public void setMrVarietyIds(String mrVarietyIds) {
		this.mrVarietyIds = mrVarietyIds;
	}
	public String getMrVarietyNames() {
		return mrVarietyNames;
	}
	public void setMrVarietyNames(String mrVarietyNames) {
		this.mrVarietyNames = mrVarietyNames;
	}
	public String getCulinfoIds() {
		return culinfoIds;
	}
	public void setCulinfoIds(String culinfoIds) {
		this.culinfoIds = culinfoIds;
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
	private Integer culduration;
	private String culdays;
	private Integer maxtemp;
	private Integer mintemp;
	private Integer maxhumi;
	private Integer minhumi;
	private Integer maxco2;
	private Integer minco2;
	private Integer ligthtime;





	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getCulduration() {
		return culduration;
	}
	public void setCulduration(Integer culduration) {
		this.culduration = culduration;
	}
	public String getCuldays() {
		return culdays;
	}
	public void setCuldays(String culdays) {
		this.culdays = culdays;
	}
	public Integer getMaxtemp() {
		return maxtemp;
	}
	public void setMaxtemp(Integer maxtemp) {
		this.maxtemp = maxtemp;
	}
	public Integer getMintemp() {
		return mintemp;
	}
	public void setMintemp(Integer mintemp) {
		this.mintemp = mintemp;
	}
	public Integer getMaxhumi() {
		return maxhumi;
	}
	public void setMaxhumi(Integer maxhumi) {
		this.maxhumi = maxhumi;
	}
	public Integer getMinhumi() {
		return minhumi;
	}
	public void setMinhumi(Integer minhumi) {
		this.minhumi = minhumi;
	}
	public Integer getMaxco2() {
		return maxco2;
	}
	public void setMaxco2(Integer maxco2) {
		this.maxco2 = maxco2;
	}
	public Integer getMinco2() {
		return minco2;
	}
	public void setMinco2(Integer minco2) {
		this.minco2 = minco2;
	}
	public Integer getLigthtime() {
		return ligthtime;
	}
	public void setLigthtime(Integer ligthtime) {
		this.ligthtime = ligthtime;
	}


	
}
