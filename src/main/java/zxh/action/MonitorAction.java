package zxh.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.pageModel.Monitor;
import zxh.pageModel.DataGrid;
import zxh.pageModel.Json;
import zxh.service.MonitorServiceI;

import com.opensymphony.xwork2.ModelDriven;

//@ParentPackage(value = "basePackage")
@Namespace("/")
@Action(value = "monitorAction")
public class MonitorAction<T> extends BaseAction implements ModelDriven<Monitor>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MonitorAction.class);
	
	Monitor monitor =new Monitor();
	private MonitorServiceI monitorService;
	@Autowired
	public MonitorServiceI getMonitorService() {
		return monitorService;
	}
	public void setMonitorService(MonitorServiceI monitorService) {
		this.monitorService = monitorService;
	}
	public void add(){
		Json j=new Json();
		try {
			Monitor u=monitorService.save(monitor);
			j.setSuccess(true);
			j.setMsg("增加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	@Override
	public Monitor getModel() {
		return monitor;
	}
	public void datagrid(){
		super.writeJson(monitorService.datagrid(monitor));
	}
	public void remove(){
		Json j=new Json();
		try{
			monitorService.remove(monitor.getIds());
			j.setSuccess(true);
			j.setMsg("删除成功！");
		}catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	public void edit(){
		Monitor u=monitorService.edit(monitor);
		Json j=new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(u);
		super.writeJson(j);
	}
	public void combogrid() {
		super.writeJson(monitorService.datagrid(monitor));
	}
}
