package zxh.service;

import zxh.pageModel.Culinfo;
import zxh.pageModel.DataGrid;

public interface CulinfoServiceI {
	public Culinfo save(Culinfo culinfo);
	public DataGrid datagrid(Culinfo culinfo);
	public void remove(String ids);
	public Culinfo edit(Culinfo culinfo);
}
