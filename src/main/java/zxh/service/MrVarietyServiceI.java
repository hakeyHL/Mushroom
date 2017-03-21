package zxh.service;

import zxh.pageModel.MrVariety;
import zxh.pageModel.DataGrid;

public interface MrVarietyServiceI {
	public MrVariety save(MrVariety mrVariety);
	public DataGrid datagrid(MrVariety mrVariety);
	public void remove(String ids);
	public MrVariety edit(MrVariety mrVariety);
}
