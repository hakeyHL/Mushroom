package zxh.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import zxh.service.RepairServiceI;

@Action(value = "repairAction")
public class RepairAction extends BaseAction {
	private RepairServiceI repairService;
	
	public RepairServiceI getRepairService() {
		return repairService;
	}
	@Autowired
	public void setRepairService(RepairServiceI repairService) {
		this.repairService = repairService;
	}
	public void init(){
		//System.out.println("【系统启动】开始...");    
		this.repairService.repair();
	}
}
