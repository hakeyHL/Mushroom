package zxh.service;

import zxh.pageModel.DataGrid;
import zxh.pageModel.Variety;

public interface MGConditionServiceI {
	public Variety save(Variety variety);
	public DataGrid datagrid(Variety variety);
	public void remove(String ids);
	public Variety edit(Variety variety);
	
}
