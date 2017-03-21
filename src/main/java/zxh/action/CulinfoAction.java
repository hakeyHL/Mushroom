package zxh.action;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.pageModel.Culinfo;
import zxh.pageModel.Json;
import zxh.service.CulinfoServiceI;

import com.opensymphony.xwork2.ModelDriven;

//@ParentPackage(value = "basePackage")
@Namespace("/")
@Action(value = "culinfoAction")
public class CulinfoAction<T> extends BaseAction implements ModelDriven<Culinfo>{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CulinfoAction.class);
	
	Culinfo culinfo =new Culinfo();
	private CulinfoServiceI culinfoService;
	@Autowired
	public CulinfoServiceI getCulinfoService() {
		return culinfoService;
	}
	public void setCulinfoService(CulinfoServiceI culinfoService) {
		this.culinfoService = culinfoService;
	}
	public void add(){
		Json j=new Json();
		try {
			Culinfo u=culinfoService.save(culinfo);
			j.setSuccess(true);
			j.setMsg("增加成功！");
			j.setObj(u);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	@Override
	public Culinfo getModel() {
		return culinfo;
	}
	public void datagrid(){
		super.writeJson(culinfoService.datagrid(culinfo));
	}
	public void remove(){
		Json j=new Json();
		String [] culstates=culinfo.getStates().split(",");
		for(int i=0;i<culstates.length;i++){
			if(culstates[i].equals("栽培中")){
//				logger.info(culstates[i]);
				j.setSuccess(false);
				j.setMsg("菇房状态为\"栽培中\"不能删除！");
				super.writeJson(j);
				return;
			}
		}
//		System.out.println("ok");
		try{
//			String [] states=culinfo.getStates().split(",");
//			for(int i=0;i<states.length;i++){
//				if(states[i].equals("zxh")){
//					j.setSuccess(false);
//					j.setMsg("用户\"zxh\"不能删除！");
//					super.writeJson(j);
//					return;
//				}
//			}
//			if(culinfo.getCulstate().equals("栽培中")){
//				j.setSuccess(false);
//				j.setMsg("此菇房状态为\"栽培中\"不能删除！");
//				super.writeJson(j);
//				return;
//			}
			culinfoService.remove(culinfo.getIds());
			j.setSuccess(true);
			j.setMsg("删除成功！");
			
		}catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		super.writeJson(j);
	}
	public void edit(){
		Culinfo u=culinfoService.edit(culinfo);
		Json j=new Json();
		j.setSuccess(true);
		j.setMsg("修改成功！");
		j.setObj(u);
		super.writeJson(j);
	}
	public void combogrid() {
		super.writeJson(culinfoService.datagrid(culinfo));
	}
}
