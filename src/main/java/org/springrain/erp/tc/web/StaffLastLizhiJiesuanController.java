package  org.springrain.erp.tc.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import org.springrain.erp.constants.TcCodeTypeEnum;
import org.springrain.erp.gz.entity.Salary;
import org.springrain.erp.gz.entity.Salaryinfo;
import org.springrain.erp.gz.service.ISalaryService;
import org.springrain.erp.gz.service.ISalaryinfoService;
import org.springrain.erp.gz.util.MathUtils;
import org.springrain.erp.gz.util.GlobalStaticVar.StoppayEnum;
import org.springrain.erp.hr.entity.UserInfo;
import org.springrain.erp.hr.service.IUserInfoService;
import org.springrain.erp.tc.dto.DimissionDto;
import org.springrain.erp.tc.entity.TongchouInfo;
import org.springrain.erp.tc.entity.TongchouRecord;
import org.springrain.erp.tc.entity.TongchouZengjian;
import org.springrain.erp.tc.service.ITongchouInfoService;
import org.springrain.erp.tc.service.ITongchouRecordService;
import org.springrain.erp.tc.service.ITongchouZengjianService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.entity.DicData;
import org.springrain.system.entity.User;
import org.springrain.system.service.IDicDataService;
import org.springrain.system.service.IUserService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-03 17:40:07
 * @see org.springrain.erp.tc.web.TongchouInfo
 */
@Controller
@RequestMapping(value="/lastdimission")
public class StaffLastLizhiJiesuanController  extends BaseController {
	@Resource
	private ITongchouInfoService tongchouInfoService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IUserService userService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private ITongchouRecordService tongchouRecordService;
	@Resource
	private ITongchouZengjianService tongchouZengjianService;
	@Resource
	private ISalaryinfoService salaryinfoService;
	@Resource
	private ISalaryService salaryService;
	private String listurl="/erp/gz/dimission/lastmonthdimission";
	
	
	   
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
		String userId = request.getParameter("userId");
		returnObject.setQueryBean(userId);
		List<User> userList = userService.finderUserForlizhi();
		model.addAttribute("userList", userList);
		//统筹标题
		List<DicData> tcList = dicDataService.findListDicData(DicdataTypeEnum.统筹类型.getValue(), null, null);
		model.addAttribute("tcList", tcList);
		//工资增减项
		List<DicData> salaydicList = dicDataService.findListDicData(DicdataTypeEnum.工资增减项类型.getValue(), null, null);
		model.addAttribute("salaydicList", salaydicList);
		
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
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
		returnObject.setPage(page);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,TongchouInfo tongchouInfo) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
	
		File file = tongchouInfoService.findDataExportExcel(null,listurl, page,TongchouInfo.class,tongchouInfo);
		String fileName="tongchouInfo"+GlobalStatic.excelext;
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
		returnObject.setStatus(ReturnDatas.ERROR);
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
		String userId = request.getParameter("userId");
		if(StringUtils.isEmpty(userId)){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("请选择用户");
			return returnObject;
		}
		Calendar calendar = Calendar.getInstance();
		Date date = DateUtils.getBoferBeginDate(calendar);
		try {
			String[] salaryArray = request.getParameterValues("salaryArray");//增减项类型
			String[] money = request.getParameterValues("money");//金额
			String[] numberDay = request.getParameterValues("numberDay");
			List<Salaryinfo> list = new ArrayList<Salaryinfo>();
			User user = userService.findUserById(userId);
			UserInfo info = userInfoService.findUserInfoByUserId(userId);
			for(int i=0;i<salaryArray.length;i++){
				Salaryinfo salaryinfo = new Salaryinfo();
				salaryinfo.setUserId(userId);
				//用户信息
				if(info!=null&&StringUtils.isNotBlank(info.getUserName())){
					salaryinfo.setUserName(info.getUserName());
				}
				if(user!=null&&StringUtils.isNotBlank(user.getAccount())){
					salaryinfo.setUserAccount(user.getAccount());
				}
				//增减项类型
				salaryinfo.setSalaryTypeId(salaryArray[i].toString());
				DicData dicData = dicDataService.findDicDataById(salaryArray[i].toString());
				if(dicData!=null&&StringUtils.isNotBlank(dicData.getName())){
					salaryinfo.setSalaryType(dicData.getName());
				}
				//计算日工资
				BigDecimal rigongzi = salaryService.findUserDayPay(userId, date);
				if(StringUtils.isNotBlank(numberDay[i])){
					String amount = MathUtils.mulScale(String.valueOf(rigongzi), numberDay[i].toString());
					salaryinfo.setAmount(new BigDecimal(amount));
					salaryinfo.setNumberDay(new BigDecimal(numberDay[i]));
				}else{
					salaryinfo.setAmount(new BigDecimal(money[i]));
				}
				//离职结算-上月 默认日期
				salaryinfo.setInDate(date);
				salaryinfo.setActive(ErpStateEnum.gzZjxActiveEnum.否.getValue());
				salaryinfo.setCreateTime(new Date());
				salaryinfo.setCreateUser(SessionUser.getUserId());
				list.add(salaryinfo);
			}
			
			//保存并查询数据
			List<Salaryinfo> salarylist = salaryinfoService.saveSalryForList(list, userId, DateUtils.formatDate(DateUtils.DATE_FORMAT, date));
			returnObject.setData(salarylist);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.out.println(e);
			returnObject.setStatus(ReturnDatas.ERROR);
			return returnObject;
		}
		return returnObject;
	
	}
	
	/**
	 * 生成工资
	 */
	@RequestMapping(value = "/update/pre")
	@ResponseBody
	public ReturnDatas updatepre(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		String userId = request.getParameter("userId");
		String salaryId = request.getParameter("salaryId");
		if(StringUtils.isEmpty(salaryId)){
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage("请生成工资基本信息");
			return returnObject;
		}
		try {
			Calendar calendar = Calendar.getInstance();
			//得到上个月第一天
			Date date = DateUtils.getBoferBeginDate(calendar);
			salaryService.saveGenSalaryForLizhi(userId, DateUtils.formatDate(DateUtils.DATE_FORMAT, date));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			System.out.println(e);
			returnObject.setStatus(ReturnDatas.ERROR);
			return returnObject;
		}
		return returnObject;
	
	
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
			Calendar calendar = Calendar.getInstance();
			DimissionDto dimissionDto = new DimissionDto();
			//得到上个月第一天
			Date date = DateUtils.getBoferBeginDate(calendar);
			
			String dateStr = DateUtils.convertDate2String("yyyy-MM", date);
			returnObject.setQueryBean(dateStr);
			String userId = request.getParameter("userId");
			if(StringUtils.isEmpty(userId)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("没有该用户");
				return returnObject;
			}
			//查找用户基本信息
			UserInfo userdata = userInfoService.finderUserInfo(userInfo);
			if(userdata==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("请完善用户信息");
				return returnObject;
			}
			
			if(StoppayEnum.停发.getValue().equals(userdata.getStoppay())){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("该员工已经停职");
				return returnObject;
			}
			//查找工资主表获取salaryID信息
			Salary salary = salaryService.finderSalaryInfo(userId, DateUtils.formatDate(DateUtils.DATE_FORMAT, date));
			if(salary==null){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("请生成员工工资信息");
				return returnObject;
			}
			//查找日工资
			BigDecimal rigongzi = salaryService.findUserDayPay(userId, date);
			dimissionDto.setPayDay(String.valueOf(rigongzi));
			//基本信息
			dimissionDto.setSalaryId(salary.getId());//得到salaryID
			dimissionDto.setUserId(userdata.getUserId());
			dimissionDto.setJibenpay(userdata.getJibenpay());
			dimissionDto.setKaohepay(userdata.getKaohepay());
			dimissionDto.setGangweipay(userdata.getGangweipay());
			dimissionDto.setCardId(userdata.getIdCard());
			dimissionDto.setJobNumber(userdata.getWorkno());
			
			Map<String, DicData> dicmap = dicDataService.getAlldicData(null);
			if(StringUtils.isNotEmpty(userdata.getCompany())&&dicmap.get(userdata.getCompany())!=null){
				dimissionDto.setInCompany(dicmap.get(userdata.getCompany()).getName());
			}
			if(StringUtils.isNotEmpty(userdata.getGangwei())&&dicmap.get(userdata.getGangwei())!=null){
				dimissionDto.setDutyTypeName(dicmap.get(userdata.getGangwei()).getName());
			}
			//查询用户统筹信息
			DicData dic = new DicData();
			List<DicData> dicList = dicDataService.findListDicData(DicdataTypeEnum.统筹类型.getValue(), null, dic);
			List<TongchouRecord> recordList = tongchouRecordService.finderRecordForList(userId, DateUtils.formatDate(DateUtils.DATE_FORMAT, date));
			dimissionDto.setTcrecordList(assembleTcRecord(dicList,recordList));//根据字典ID得到统筹
			if(CollectionUtils.isEmpty(recordList)){
				returnObject.setStatus(ReturnDatas.ERROR);
				returnObject.setMessage("没有找到本月：统筹月度标准报表,请进行生成");
				return returnObject;
			}
			//统筹信息增减项
			DicData dicData = new DicData();
			dicData.setCode(TcCodeTypeEnum.tcFyTypeEnum.社保.getValue());
			List<DicData> listDic = dicDataService.findDicDataForList(DicdataTypeEnum.费用类型.getValue(), null, dicData);
			List<String> listStr = new ArrayList<String>();
			if(CollectionUtils.isNotEmpty(listDic)){
				for(DicData data:listDic){
					listStr.add(data.getId());
				}
			}
			List<TongchouZengjian> zjList = tongchouZengjianService.finderTczjxForList(userId, DateUtils.formatDate(DateUtils.DATE_FORMAT, date),listStr);
			dimissionDto.setZjList(zjList);			
			//查询用户工资增减项
			List<Salaryinfo> salaryList = salaryinfoService.findSalaryinfoForList(userId,DateUtils.formatDate(DateUtils.DATE_FORMAT, date));
			dimissionDto.setSalarInfoyList(salaryList);
			returnObject.setData(dimissionDto);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			returnObject.setStatus(ReturnDatas.ERROR);
			returnObject.setMessage(MessageUtils.UPDATE_ERROR);
		}
		return returnObject;
	
	}
	
	//根据字典ID得到统筹
	private List<TongchouRecord> assembleTcRecord(List<DicData> dicDatas,List<TongchouRecord> records){
		List<TongchouRecord> list = new ArrayList<TongchouRecord>();
		if(CollectionUtils.isNotEmpty(dicDatas)&&CollectionUtils.isNotEmpty(records)){
			for(DicData data :dicDatas){
				TongchouRecord personal = new TongchouRecord();
				for(TongchouRecord record:records){
					if(data.getId().equals(record.getInsuranceType())){
						personal.setInsurancePersonal(record.getInsurancePersonal());
						personal.setInsuranceCompany(record.getInsuranceCompany());
					}
				}
				if(personal.getInsurancePersonal()==null){
					personal.setInsurancePersonal(new BigDecimal(0));
				}
				if(personal.getInsuranceCompany()==null){
					personal.setInsuranceCompany(new BigDecimal(0));
				}
				list.add(personal);
			}
		}
		return list;
	}
	
		
}
