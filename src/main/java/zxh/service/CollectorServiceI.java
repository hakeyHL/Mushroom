package zxh.service;

import zxh.pageModel.Collector;
import zxh.pageModel.DataGrid;

public interface CollectorServiceI {
	public Collector save(Collector collector);
	public DataGrid datagrid(Collector collector);
	public void remove(String ids);
	public Collector edit(Collector collector);
}
