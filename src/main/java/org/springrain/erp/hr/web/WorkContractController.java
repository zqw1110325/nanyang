package  org.springrain.erp.hr.web;

import java.io.File;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.erp.constants.DicdataTypeEnum;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.hr.entity.WorkContract;
import org.springrain.erp.hr.service.IWorkContractService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.entity.DicData;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.User;
import org.springrain.system.service.IDicDataService;
import org.springrain.system.service.IOrgService;
import org.springrain.system.service.IUserOrgService;
import org.springrain.system.service.IUserService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-03 17:48:09
 * @see org.springrain.erp.hr.web.WorkContract
 */
@Controller
@RequestMapping(value="/workcontract")
public class WorkContractController  extends BaseController {
	@Resource
	private IWorkContractService workContractService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IOrgService orgService;
	@Resource
	private IUserOrgService userOrgService;
	@Resource
	private IUserService userService;
	private String listurl="/erp/hr/workcontract/workcontractList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param workContract
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,WorkContract workContract) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, workContract);
		User user = new User();
		user.setUserType(ErpStateEnum.userTypeEnum.员工.getValue());
		List<User> userList = userService.finderUserForList(user,null);
		model.addAttribute("userList", userList);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param workContract
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,WorkContract workContract) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<WorkContract> datas=workContractService.findWorkContractByQuery(workContract,page);
		
		returnObject.setQueryBean(workContract);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,WorkContract workContract) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = workContractService.findDataExportExcel(null,listurl, page,WorkContract.class,workContract);
		String fileName="workContract"+GlobalStatic.excelext;
		downFile(response, file, fileName,true);
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/erp/hr/workcontract/workcontractLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  WorkContract workContract = workContractService.findWorkContractById(id);
		  if(workContract != null){
			List<Org> orgList = orgService.findOrgByOrgIds(workContract.getOrgId());
			if(CollectionUtils.isNotEmpty(orgList)){
				StringBuilder orgNames = new StringBuilder();
				for(Org o : orgList){
					orgNames.append(o.getName()).append(",");
				}
				workContract.setOrgName(orgNames.toString());
			}
		  }
		  returnObject.setData(workContract);
		}else{
		returnObject.setStatus(ReturnDatas.ERROR);
		}
		return returnObject;
		
	}
	
	
	/**
	 * 新增/修改 操作吗,返回json格式数据
	 * 
	 */
	@RequestMapping("/update")
	@ResponseBody      
	public ReturnDatas saveorupdate(Model model,WorkContract workContract,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			java.lang.String id =workContract.getId();
			if(StringUtils.isBlank(id)){
			  workContract.setId(null);
			}
			workContract.setEditPerson(SessionUser.getUserId());
			workContract.setEditDate(new Date());
			if(workContract.getTerm() != null && workContract.getStartDate() != null){
				Calendar c = Calendar.getInstance();
				c.setTime(workContract.getStartDate());
				c.add(Calendar.YEAR, workContract.getTerm());
				workContract.setEndDate(c.getTime());
			}
			workContractService.saveorupdate(workContract);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	/**
	 * 进入修改页面,APP端可以调用 lookjson 获取json格式数据
	 */
	@RequestMapping(value = "/update/pre")
	public String updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/erp/hr/workcontract/workcontractCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@ResponseBody      
	public  ReturnDatas delete(HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
				workContractService.deleteById(id,WorkContract.class);
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return new ReturnDatas(ReturnDatas.WARNING, MessageUtils.DELETE_WARNING);
	}
	
	/**
	 * 删除多条记录
	 * 
	 */
	@RequestMapping("/delete/more")
	@ResponseBody      
	public ReturnDatas deleteMore(HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			workContractService.deleteByIds(ids,WorkContract.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

	@RequestMapping("/find/userOrg")
	@ResponseBody 
	public ReturnDatas findUserOrg(HttpServletRequest request, Model model) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String userId = request.getParameter("userId");
		Map<String,String> map = new HashMap<String, String>();
		List<Org> orgList = userOrgService.findOrgByUserId(userId);
		if(CollectionUtils.isNotEmpty(orgList)){
			StringBuilder orgIds = new StringBuilder();
			StringBuilder orgNames = new StringBuilder();
			for(Org o : orgList){
				orgIds.append(o.getId()).append(",");
				orgNames.append(o.getName()).append(",");
			}
			map.put("orgId", orgIds.toString());
			map.put("orgName", orgNames.toString());
		}
		returnObject.setData(map);
		return returnObject;
	}
	
	@RequestMapping("/configure")
	public String configureUpdatePre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookConfigureJson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/erp/hr/workcontract/configureCru";
	}
	
	@RequestMapping("/look/configure/json")
	public @ResponseBody ReturnDatas lookConfigureJson(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Map<String,DicData> map = dicDataService.findHetongConfigure();
		returnObject.setMap(map);
		return returnObject;
	}
	
	@RequestMapping("/configure/update")
	public @ResponseBody ReturnDatas configureUpdate(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		String sendDate = request.getParameter("sendDate");
		String receiveEmail = request.getParameter("receiveEmail");
		
		if(StringUtils.isBlank(sendDate)){
			return new ReturnDatas(ReturnDatas.ERROR,"请填写发送邮件天数");
		}
		if(StringUtils.isBlank(receiveEmail)){
			return new ReturnDatas(ReturnDatas.ERROR,"请填写接接收邮箱");
		}
		receiveEmail = receiveEmail.trim().replaceAll("，", ",");
		
		Map<String,DicData> map = dicDataService.findHetongConfigure();
		DicData sendDateData = map.get("chakanData");
		DicData receiveEmailData = map.get("receiveEmail");
		if(sendDateData == null){
			sendDateData = new DicData();
			sendDateData.setId(null);
			sendDateData.setName(DicdataTypeEnum.发送邮件时间.getName());
			sendDateData.setTypekey(DicdataTypeEnum.发送邮件时间.getValue());
			sendDateData.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		}
		if(receiveEmailData == null){
			receiveEmailData = new DicData();
			receiveEmailData.setId(null);
			receiveEmailData.setName(DicdataTypeEnum.接收邮箱.getName());
			receiveEmailData.setTypekey(DicdataTypeEnum.接收邮箱.getValue());
			receiveEmailData.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		}
		sendDateData.setCode(sendDate);
		receiveEmailData.setRemark(receiveEmail);
		dicDataService.saveorupdate(sendDateData);
		dicDataService.saveorupdate(receiveEmailData);
		return returnObject;
	}
}
