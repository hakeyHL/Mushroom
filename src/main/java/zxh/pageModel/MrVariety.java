package zxh.pageModel;

import java.io.Serializable;

public class MrVariety implements Serializable {
	private int page;
	private int rows;
	private String sort;
	private String order;
	private String ids;

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
	private String mushroomname;
	private Integer culduration;

	public Integer getCulduration() {
		return culduration;
	}
	public void setCulduration(Integer culduration) {
		this.culduration = culduration;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMushroomname() {
		return mushroomname;
	}
	public void setMushroomname(String mushroomname) {
		this.mushroomname = mushroomname;
	}
	


	
}
