package zxh.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.pageModel.MrVariety;
import zxh.pageModel.DataGrid;
import zxh.pageModel.Json;
import zxh.service.MrVarietyServiceI;

import com.opensymphony.xwork2.ModelDriven;

//@ParentPackage(value = "basePackage")
@Namespace("/")
@Action(value = "mrVarietyAction")
public class mrVarietyAction<T> extends BaseAction implements ModelDriven<MrVariety>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(mrVarietyAction.class);
	
	MrVariety mrVariety =new MrVariety();
	private MrVarietyServiceI mrVarietyService;
	@Autowired
	public MrVarietyServiceI getMrVarietyService() {
		return mrVarietyService;
	}
	public void setMrVarietyService(MrVarietyServiceI mrVarietyService) {
		this.mrVarietyService = mrVarietyService;
	}
	public void add(){
		Json j=new Json();
		try {
			MrVariety u=mrVarietyService.save(mrVariety);
			j.setSuccess(true);
			j.setMsg("增加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	@Override
	public MrVariety getModel() {
		return mrVariety;
	}
	public void datagrid(){
		super.writeJson(mrVarietyService.datagrid(mrVariety));
	}
	public void remove(){
		Json j=new Json();
		try {
			mrVarietyService.remove(mrVariety.getIds());
			j.setSuccess(true);
			j.setMsg("删除成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		
		super.writeJson(j);
	}
	public void edit(){
		MrVariety u=mrVarietyService.edit(mrVariety);
		Json j=new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(u);
		super.writeJson(j);
	}
	public void combogrid() {
		super.writeJson(mrVarietyService.datagrid(mrVariety));
	}
}
