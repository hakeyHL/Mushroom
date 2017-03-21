package zxh.service;

import zxh.pageModel.MrHouse;
import zxh.pageModel.DataGrid;

public interface MrHouseServiceI {
	public MrHouse save(MrHouse mrHouse);
	public DataGrid datagrid(MrHouse mrHouse);
	public void remove(String ids);
	public MrHouse edit(MrHouse mrHouse);
}
