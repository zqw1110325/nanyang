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
import org.springrain.erp.tc.entity.TongchouRecord;
import org.springrain.erp.tc.entity.TongchouShow;
import org.springrain.erp.tc.service.ITongchouInfoService;
import org.springrain.erp.tc.service.ITongchouRecordService;
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
 * @version  2017-07-03 17:42:27
 * @see org.springrain.erp.tc.web.TongchouRecord
 */
@Controller
@RequestMapping(value="/tongchourecord")
public class TongchouRecordController  extends BaseController {
	@Resource
	private ITongchouRecordService tongchouRecordService;
	@Resource
	private ITongchouInfoService tongchouInfoService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IUserService userService;
	@Resource
	private ITongchouShowService tongchouShowService;
	private String listurl="/erp/tc/tongchourecord/tongchourecordList";
	
	
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param tongchouRecord
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,TongchouRecord tongchouRecord) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, tongchouRecord);
		//公司名称
		DicData dicData = new DicData();
		dicData.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		List<DicData> gsList = dicDataService.findListDicData(DicdataTypeEnum.公司.getValue(), null, dicData);
		model.addAttribute("gsList", gsList);
		User user = new User();
		user.setUserType(ErpStateEnum.userTypeEnum.员工.getValue());
		List<User> listUser = userService.finderUserForList(user,null);
		model.addAttribute("listUser", listUser);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param tongchouRecord
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,TongchouRecord tongchouRecord) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		Date firstD = new Date();
		try {
			if(tongchouRecord.getMonth()==null){
				 firstD = DateUtils.convertString2Date(DateUtils.convertDate2String("yyyy-MM",new Date())+"-01");
				
			}else{
				 firstD = DateUtils.convertString2Date(DateUtils.convertDate2String("yyyy-MM",tongchouRecord.getMonth())+"-01");
			}
			tongchouRecord.setMonth(firstD);
			// ==构造分页请求
			Page page = newPage(request);
			//得到标题
			TongchouShow show = new TongchouShow();
			show.setDicttypeId(DicdataTypeEnum.统筹类型.getValue());
			show.setType(TcshowEnum.tcDeskShowEnum.统筹月度数据.getValue());
			List<TongchouShow> listshow = tongchouShowService.findListByTypekey(show);
			model.addAttribute("listshow", listshow);
			tongchouRecord.setListShow(listshow);
			//查找元数据
			returnObject.setQueryBean(tongchouRecord);
			returnObject.setPage(page);
			//转化成为页面显示信息
			List<TongchouInfoDto> datas=tongchouRecordService.finderInfoForList(tongchouRecord,page);
			returnObject.setData(datas);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,TongchouRecord tongchouRecord) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		Date firstD = new Date();
		try{
			if(tongchouRecord.getMonth()==null){
				firstD = DateUtils.convertString2Date(DateUtils.convertDate2String("yyyy-MM",new Date())+"-01");
				
			}else{
				firstD = DateUtils.convertString2Date(DateUtils.convertDate2String("yyyy-MM",tongchouRecord.getMonth())+"-01");
			}
			tongchouRecord.setMonth(firstD);
			TongchouShow show = new TongchouShow();
			show.setDicttypeId(DicdataTypeEnum.统筹类型.getValue());
			show.setType(TcshowEnum.tcDeskShowEnum.统筹月度数据.getValue());
			List<TongchouShow> listshow = tongchouShowService.findListByTypekey(show);
			tongchouRecord.setListShow(listshow);
			File file = tongchouRecordService.findDataExportExcel(null,"/erp/tc/tongchourecord/tongchourecordList", page,TongchouRecord.class,tongchouRecord);
			String typeName = TcshowEnum.tcDeskShowEnum.统筹月度数据.getName();
			String fileName=typeName+"记录"+GlobalStatic.excelext;
			downFile(response, file, fileName,true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return;
	}
	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		ReturnDatas returnObject = lookjson(model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/tc/tongchourecord/tongchourecordLook";
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
		  TongchouRecord tongchouRecord = tongchouRecordService.findTongchouRecordById(id);
		   returnObject.setData(tongchouRecord);
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
	public ReturnDatas saveorupdate(Model model,TongchouRecord tongchouRecord,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage("数据生成成功");
		try {
		
			java.lang.String id =tongchouRecord.getId();
			
			if(StringUtils.isBlank(id)){
			  tongchouRecord.setId(null);
			}
		
			tongchouRecordService.saveTcRecord(DateUtils.convertDate2String("yyyy-MM",tongchouRecord.getMonth()));
			
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
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/tc/tongchourecord/tongchourecordCru";
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
				tongchouRecordService.deleteById(id,TongchouRecord.class);
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
			tongchouRecordService.deleteByIds(ids,TongchouRecord.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

}
