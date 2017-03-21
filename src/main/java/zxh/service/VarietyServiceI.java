package zxh.service;

import zxh.pageModel.Variety;
import zxh.pageModel.DataGrid;

public interface VarietyServiceI {
	public Variety save(Variety variety);
	public DataGrid datagrid(Variety variety);
	public void remove(String ids);
	public Variety edit(Variety variety);
}
