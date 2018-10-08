package  org.springrain.erp.tc.web;

import java.io.File;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.erp.constants.DicdataTypeEnum;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.constants.TcshowEnum;
import org.springrain.erp.tc.dto.TongchouInfoDto;
import org.springrain.erp.tc.entity.GztcCeRecord;
import org.springrain.erp.tc.entity.TongchouShow;
import org.springrain.erp.tc.service.ITongchouCerecordService;
import org.springrain.erp.tc.service.ITongchouShowService;
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
 * @version  2017-08-11 10:29:49
 * @see org.springrain.erp.gz.web.GztcCeRecord
 */
@Controller
@RequestMapping(value="/gztccerecord")
public class TongchouCerecordController  extends BaseController {
	@Resource
	private ITongchouCerecordService tongchouCerepordService;
	@Resource
	private ITongchouShowService tongchouShowService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IUserService userService;
	private String listurl="/erp/tc/record/tcCerecord/tccerecord";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param GztcCeRecord
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,GztcCeRecord gztcCeRecord) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, gztcCeRecord);
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
	 * @param GztcCeRecord
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,GztcCeRecord gztcCeRecord) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		if(gztcCeRecord.getMonth()==null){
			Date date = DateUtils.getMonthFirstDay(new Date());
			gztcCeRecord.setMonth(date);
		}
		TongchouShow show = new TongchouShow();
		show.setDicttypeId(DicdataTypeEnum.统筹类型.getValue());
		show.setType(TcshowEnum.tcDeskShowEnum.代扣代缴与社保差异.getValue());
		List<TongchouShow> listShow = tongchouShowService.findListByTypekey(show);
		gztcCeRecord.setListShow(listShow);
		returnObject.setQueryBean(gztcCeRecord);
		returnObject.setPage(page);
		// ==执行分页查询
		List<TongchouInfoDto> datas=tongchouCerepordService.finderCeRecordList(gztcCeRecord, page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,GztcCeRecord gztcCeRecord) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		TongchouShow show = new TongchouShow();
		show.setDicttypeId(DicdataTypeEnum.统筹类型.getValue());
		show.setType(TcshowEnum.tcDeskShowEnum.代扣代缴与社保差异.getValue());
		List<TongchouShow> listshow = tongchouShowService.findListByTypekey(show);
		gztcCeRecord.setListShow(listshow);
		File file = tongchouCerepordService.findDataExportExcel(null,"/erp/tc/record/tcCerecord/tccerecord", page,GztcCeRecord.class,gztcCeRecord);
		String typeName = TcshowEnum.tcDeskShowEnum.代扣代缴与社保差异.getName();
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
		return "/gz/GztcCeRecord/tongchoucerepordLook";
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
		  GztcCeRecord GztcCeRecord = tongchouCerepordService.findTongchouCerepordById(id);
		   returnObject.setData(GztcCeRecord);
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
	public ReturnDatas saveorupdate(Model model,GztcCeRecord GztcCeRecord,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		

			java.lang.String id =GztcCeRecord.getId();
			String month = request.getParameter("month");
			if(StringUtils.isBlank(id)){
				GztcCeRecord.setId(null);
			}
			returnObject =  tongchouCerepordService.saveCeRecord(month);
			
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
		return "/gz/GztcCeRecord/tongchoucerepordCru";
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
				tongchouCerepordService.deleteById(id,GztcCeRecord.class);
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
			tongchouCerepordService.deleteByIds(ids,GztcCeRecord.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
