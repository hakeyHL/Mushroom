package zxh.service;

import zxh.pageModel.DataGrid;
import zxh.pageModel.Monitor;

public interface MonitorServiceI {
	public Monitor save(Monitor monitor);
	public DataGrid datagrid(Monitor monitor);
	public void remove(String ids);
	public Monitor edit(Monitor monitor);
}
