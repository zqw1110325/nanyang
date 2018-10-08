package  org.springrain.erp.tc.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springrain.erp.constants.DicdataTypeEnum;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.constants.ErpStateEnum.userTypeEnum;
import org.springrain.erp.constants.TcshowEnum;
import org.springrain.erp.gz.service.impl.TongchouinfoImportService;
import org.springrain.erp.hr.entity.UserInfo;
import org.springrain.erp.hr.service.IUserInfoService;
import org.springrain.erp.tc.dto.TongchouInfoDto;
import org.springrain.erp.tc.entity.TongchouInfo;
import org.springrain.erp.tc.entity.TongchouShow;
import org.springrain.erp.tc.service.ITongchouInfoService;
import org.springrain.erp.tc.service.ITongchouShowService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.entity.DicData;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.User;
import org.springrain.system.service.IDicDataService;
import org.springrain.system.service.IUserOrgService;
import org.springrain.system.service.IUserService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-03 17:40:07
 * @see org.springrain.erp.tc.web.TongchouInfo
 */
@Controller
@RequestMapping(value="/tongchouinfo")
public class TongchouInfoController  extends BaseController {
	@Resource
	private ITongchouInfoService tongchouInfoService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IUserService userService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private ITongchouShowService tongchouShowService;
	@Resource
	private TongchouinfoImportService tcInfoImportService;
	@Resource
	private IUserOrgService userOrgService;
	
	private String listurl="/erp/tc/tongchouinfo/tongchouinfoList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model 
	 * @param tongchouInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,TongchouInfo tongchouInfo) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, tongchouInfo);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		//公司名称
		List<DicData> gsList = dicDataService.findListDicData(DicdataTypeEnum.公司.getValue(), null, null);
		model.addAttribute("gsList", gsList);
		User user = new User();
		user.setUserType(ErpStateEnum.userTypeEnum.员工.getValue());
		List<User> listUser = userService.finderUserForList(user,null);
		model.addAttribute("listUser", listUser);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param tongchouInfo
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,TongchouInfo tongchouInfo) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		//得到标题
		try {
			if(StringUtils.isEmpty(tongchouInfo.getState())){
				tongchouInfo.setState(ErpStateEnum.tcstateEnum.是.getValue());
			}
			TongchouShow show = new TongchouShow();
			show.setDicttypeId(DicdataTypeEnum.统筹类型.getValue());
			show.setType(TcshowEnum.tcDeskShowEnum.员工统筹公积金.getValue());
			List<TongchouShow> listShow = tongchouShowService.findListByTypekey(show);
			tongchouInfo.setListShow(listShow);
			returnObject.setQueryBean(tongchouInfo);
			returnObject.setPage(page);
			//转化成为页面显示信息
			List<TongchouInfoDto> datas=tongchouInfoService.finderListInfo(tongchouInfo,page);
			returnObject.setData(datas);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,TongchouInfo tongchouInfo) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		TongchouShow show = new TongchouShow();
		show.setDicttypeId(DicdataTypeEnum.统筹类型.getValue());
		show.setType(TcshowEnum.tcDeskShowEnum.员工统筹公积金.getValue());
		List<TongchouShow> listshow = tongchouShowService.findListByTypekey(show);
		tongchouInfo.setListShow(listshow);
		File file = tongchouInfoService.findDataExportExcel(null,"/erp/tc/tongchouinfo/tongchouinfoList", page,TongchouInfo.class,tongchouInfo);
		String typeName = TcshowEnum.tcDeskShowEnum.员工统筹公积金.getName();
		String fileName=typeName+"记录"+GlobalStatic.excelext;
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
		return "/tc/tongchouinfo/tongchouinfoLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody      
	public ReturnDatas lookjson(Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String userId=request.getParameter("userId");
		if(StringUtils.isNotBlank(userId)){
			TongchouInfoDto dto = new TongchouInfoDto();
			try {
			//根据userid查找可以用户的统筹信息
			List<TongchouInfo> tcList = tongchouInfoService.finderInfoByUserId(userId);
			if(CollectionUtils.isEmpty(tcList)){
				return null;
			}	
			//得到公积金和保险账号
			for(TongchouInfo info:tcList){
				String tctype = info.getInsurgongjijinType();
				if(ErpStateEnum.tcAccountTypeEnum.公积金.getValue().equals(tctype)){
					dto.setGongjijinaccount(info.getInsuranceorgongjijinAccount());
				}else{
					dto.setBaoxianaccount(info.getInsuranceorgongjijinAccount());
				}
			}
			
			dto.setStats(tcList);//统筹信息
			TongchouInfo temp = tcList.get(0);
			dto.setUsername(temp.getUserName());
			dto.setCompany(temp.getCompany());//公司id
			UserInfo userInfo =userInfoService.findUserInfoByUserId(userId);
			dto.setCardId(userInfo.getIdCard());//身份证
			dto.setJobNumber(userInfo.getWorkno());//工号
			dto.setTcjiaonadi(temp.getTcjiaonadi());//统筹缴纳地
			DicData dicData = dicDataService.findDicDataById(userInfo.getGangwei());
			if(dicData!=null){
				dto.setDutyTypeName(dicData.getName());//岗位
			}
			dto.setStopProtectMonth(temp.getStopProtectMonth());//停保月份
			dto.setUserId(userId);
			model.addAttribute("stapcount", tcList.size());//list大小，前台添加统筹类型是参数。
			returnObject.setData(dto);
			} catch (Exception e) {
				logger.error(e.getMessage(),e);
			}
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
	public ReturnDatas saveorupdate(Model model,TongchouInfo tongchouInfo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
 			java.lang.String id =tongchouInfo.getId();
			if(StringUtils.isBlank(id)){
			  tongchouInfo.setId(null);
			}
			String userId = request.getParameter("userId");
			if(StringUtils.isBlank(userId)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("请添加用户信息");
				return returnObject;
			}
			String company = request.getParameter("company");//公司编号
			String cardId = request.getParameter("cardId");//身份证号
			String tcjiaonadi = request.getParameter("tcjiaonadi");
//			String stopProtectMonth = request.getParameter("stopProtectMonth");//停保月份
			Map<String, DicData> stats = getAlldicData(DicdataTypeEnum.统筹类型.getValue());
			String baoxianaccount = request.getParameter("baoxianaccount");//保险公积金账号
			String gongjijinaccount = request.getParameter("gongjijinaccount");//保险公积金账号
			String[] InsurancePaymentDates = request.getParameterValues("DeliverDate");//交费开始时间
			String[] efficientDate = request.getParameterValues("ChargedDate");//实际交费月份
			String[] radices = request.getParameterValues("base");//基数
			String[] insuranceCompany = request.getParameterValues("InsuranceCompany");//公司交纳部分
			String[] insurancePersonal = request.getParameterValues("InsurancePersonal");//个人交纳部分
			String[] insuranceType = request.getParameterValues("statType");//统筹类型
			User user = userService.findUserById(userId);
			String nowUser = SessionUser.getUserId();//当前登录人
			Date nowDate = new Date();//当前时间
			List<TongchouInfo> list = new ArrayList<TongchouInfo>();
			//遍历循环组装tongchouInfo的List集合
			for(int i=0;i<radices.length;i++){
				TongchouInfo tInfo = new TongchouInfo();
				tInfo.setState(ErpStateEnum.tcstateEnum.是.getValue());
				tInfo.setUserId(userId);
				tInfo.setUserName(user.getName());
				tInfo.setTcjiaonadi(tcjiaonadi);
				//查询用户部门
				List<Org> listOrg = userOrgService.findOrgByUserId(userId);
				if(CollectionUtils.isNotEmpty(listOrg)){
					tInfo.setDepartment(listOrg.get(0).getId());
				}
				if(cardId!=null){
					tInfo.setCardId(cardId);
				}
				if(company!=null)
					tInfo.setCompany(company);
//				if(StringUtils.isNotBlank(stopProtectMonth))
//					tInfo.setStopProtectMonth(DateUtils.convertString2Date(stopProtectMonth+"-01"));
				if(StringUtils.isNotBlank(InsurancePaymentDates[i]))
					tInfo.setInsurancePaymentDate(DateUtils.convertString2Date(InsurancePaymentDates[i]+"-01"));
				if(StringUtils.isNotBlank(efficientDate[i]))
					tInfo.setEfficientDate(DateUtils.convertString2Date(efficientDate[i]+"-01"));
				if(StringUtils.isNotBlank(radices[i]))
					tInfo.setRadices(new BigDecimal(radices[i]));
				if(StringUtils.isNotBlank(insuranceCompany[i]))
					tInfo.setInsuranceCompany(new BigDecimal(insuranceCompany[i]));
				if(StringUtils.isNotBlank(insurancePersonal[i]))
					tInfo.setInsurancePersonal(new BigDecimal(insurancePersonal[i]));
				//判断账号公积金类型;	
				if(StringUtils.isNotBlank(insuranceType[i])){
					if(insuranceType[i].toString().equals(ErpStateEnum.gongjijinId.公积金ID.getValue())){
						tInfo.setInsurgongjijinType(ErpStateEnum.tcAccountTypeEnum.公积金.getValue());
						tInfo.setInsuranceorgongjijinAccount(gongjijinaccount);
					}else{
						tInfo.setInsurgongjijinType(ErpStateEnum.tcAccountTypeEnum.社保.getValue());
						tInfo.setInsuranceorgongjijinAccount(baoxianaccount);
					}
					if(stats.get(insuranceType[i])!=null){
						
						tInfo.setInsuranceType(stats.get(insuranceType[i]).getId());
					}
				}
				tInfo.setCreator(nowUser);
				tInfo.setCreateTime(nowDate);
				list.add(tInfo);
			}
			//进行保存
			tongchouInfoService.saveOrupdateListInfo(list,userId);
			
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			System.out.println(e);
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
		//统筹类型
		List<DicData> tcList = dicDataService.findListDicData(DicdataTypeEnum.统筹类型.getValue(), null, null);
		//公司名称
		List<DicData> gsList = dicDataService.findListDicData(DicdataTypeEnum.公司.getValue(), null, null);
		//岗位
		List<DicData> gwList = dicDataService.findListDicData(DicdataTypeEnum.岗位.getValue(), null, null);
		//岗位
		List<DicData> addressList = dicDataService.findListDicData(DicdataTypeEnum.统筹缴纳地.getValue(), null, null);
		//用户
		User user = new User();
		user.setUserType(userTypeEnum.员工.getValue());
		List<User> userList = userService.finderUserForList(user,null);
		model.addAttribute("tcList", tcList);	
		model.addAttribute("gsList", gsList);
		model.addAttribute("gwList", gwList);
		model.addAttribute("userList", userList);
		model.addAttribute("addressList", addressList);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		String userId = request.getParameter("userId");
		if(StringUtils.isNotBlank(userId)){
			return "/erp/tc/tongchouinfo/tongchouinfoEdit";	
		}else{
			
			return "/erp/tc/tongchouinfo/tongchouinfoCru";
		}
		
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
				tongchouInfoService.delete(id);
				return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,MessageUtils.DELETE_WARNING);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.out.println(e);
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
			tongchouInfoService.deleteByIds(ids,TongchouInfo.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
	}
	
	//得到字典类型下所有直属类型
	private Map<String, DicData> getAlldicData(String dictType)throws Exception{
			Map<String, DicData> map = new HashMap<String, DicData>();
			List<DicData> list = dicDataService.findListDicData(dictType, null, null);
			if(CollectionUtils.isEmpty(list))
				return null;
			for(DicData dict : list)
				map.put(dict.getId().toString(), dict);
			return map;
	}
	
	/**
	 * 通过USERID查找基本信息
	 * 
	 */
	@RequestMapping("/userInfoMsg")
	@ResponseBody      
	public ReturnDatas finderUserInfoMsg(Model model,UserInfo userInfo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String userId = request.getParameter("userId");
			List<TongchouInfo> tcList = tongchouInfoService.finderInfoByUserId(userId);
			if(CollectionUtils.isNotEmpty(tcList)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("用户统筹已经存在");
				return returnObject;
			}
			userInfo.setUserId(userId);
			UserInfo userdata = userInfoService.finderUserInfo(userInfo);
			if(userdata==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("请完善用户信息");
				return returnObject;
			}
			//岗位
			List<DicData> gwList = dicDataService.findListDicData(DicdataTypeEnum.岗位.getValue(), null, null);
			if(CollectionUtils.isNotEmpty(gwList)&&userdata.getGangwei()!=null){
				for(DicData dicData :gwList){
					if(dicData.getId().equals(userdata.getGangwei())){
						userdata.setGangweiName(dicData.getName());
					}
				}
			}
			returnObject.setData(userdata);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	@RequestMapping("/tingbao/pre")
	public String tcTingbaopre(Model model,HttpServletRequest request,HttpServletResponse response,TongchouInfo info)throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String userId=request.getParameter("userId");
		User user = userService.findById(userId, User.class);
		returnObject.setData(user);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/erp/tc/tongchouinfo/tctingbao";
	}
	
	@RequestMapping("/tingbao")
	@ResponseBody      
	public ReturnDatas tcTingbao(Model model,TongchouInfo tongchouInfo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			if(tongchouInfo.getUserId()==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);	
				return returnObject;
			}
//			tongchouInfoService.saveTcTingbao(tongchouInfo);
			tongchouInfoService.saveOrUpdateTcTingbao(tongchouInfo,ErpStateEnum.tcstateEnum.否.getValue());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			System.out.println(e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	/**
	 * 恢复停保
	 * @param model
	 * @param tongchouInfo
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tingbao/more")
	@ResponseBody      
	public ReturnDatas tchuifu(Model model,TongchouInfo tongchouInfo,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
			String userId = request.getParameter("userId");
			if(StringUtils.isEmpty(userId)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage(MessageUtils.UPDATE_ERROR);	
				return returnObject;
			}
			tongchouInfoService.saveOrUpdateTcTingbao(tongchouInfo,ErpStateEnum.tcstateEnum.是.getValue());
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			System.out.println(e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	/**
	 * 下载模板
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @throws Exception
	 */
	@RequestMapping("/ajax/downMoban")
	public void downloadmoban(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
		String userId = SessionUser.getUserId();
		if (StringUtils.isBlank(userId)) {
			return;
		}
		String path = "/WEB-INF/tmpl/tcmouban.xls";
		String filePath = request.getSession().getServletContext().getRealPath("/");
		File file = new File(filePath + path);
		super.downFile(response, file, "统筹导入模板.xls", false);
	}

	/**
	 * 导入统筹信息
	 * @param uploadfile
	 * @param request
	 * @param model
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/import")
	public @ResponseBody ReturnDatas upload(@RequestParam("uploadfile") MultipartFile uploadfile,
			HttpServletRequest request, Model model, HttpServletResponse response) throws Exception {
		ReturnDatas rd = ReturnDatas.getSuccessReturnDatas();
		String msg = tcInfoImportService.importExcel(uploadfile, TongchouinfoImportService.titles, request);
		if (StringUtils.isNotBlank(msg)) {
			rd.setStatus(ReturnDatas.ERROR);
			rd.setMessage(msg);
			return rd;
		} else {
			rd.setMessage("导入成功!");
		}
		return rd;
	}
}
