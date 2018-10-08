package  org.springrain.erp.tc.web;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import org.springrain.erp.gz.service.impl.TongchouZjxImportService;
import org.springrain.erp.tc.entity.TongchouZengjian;
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
 * @version  2017-07-03 17:42:56
 * @see org.springrain.erp.tc.web.TongchouZengjian
 */
@Controller
@RequestMapping(value="/tongchouzengjian")
public class TongchouZengjianController  extends BaseController {
	@Resource
	private ITongchouZengjianService tongchouZengjianService;
	@Resource
	private IUserService userService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private TongchouZjxImportService tongchouZjxImportService;
	private String listurl="/erp/tc/tongchouzengjian/tongchouzengjianList";
	
		
	   
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param tongchouZengjian
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model,TongchouZengjian tongchouZengjian) 
			throws Exception {
		ReturnDatas returnObject = listjson(request, model, tongchouZengjian);
		//统筹类型
		List<DicData> tcList = dicDataService.findListDicData(DicdataTypeEnum.统筹类型.getValue(), null, null);
		//公司名称
		List<DicData> fyList = dicDataService.findListDicData(DicdataTypeEnum.费用类型.getValue(), null, null);
		model.addAttribute("tcList", tcList);
		model.addAttribute("fyList", fyList);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param tongchouZengjian
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody   
	public  ReturnDatas listjson(HttpServletRequest request, Model model,TongchouZengjian tongchouZengjian) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		// ==构造分页请求
		Page page = newPage(request);
		// ==执行分页查询
		List<TongchouZengjian> datas=tongchouZengjianService.finderInfoForList(page, tongchouZengjian);
		returnObject.setQueryBean(tongchouZengjian);
		returnObject.setPage(page);
		returnObject.setData(datas);
		return returnObject;
	}
	
	@RequestMapping("/list/export")
	public void listexport(HttpServletRequest request,HttpServletResponse response, Model model,TongchouZengjian tongchouZengjian) throws Exception{
		// ==构造分页请求
		Page page = newPage(request);
		File file = tongchouZengjianService.findDataExportExcel(null,"/erp/tc/tongchouzengjian/tongchouzengjianList", page,TongchouZengjian.class,tongchouZengjian);
		String fileName="统筹增减项记录"+GlobalStatic.excelext;
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
		return "/tc/tongchouzengjian/tongchouzengjianLook";
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
		  TongchouZengjian tongchouZengjian = tongchouZengjianService.findTongchouZengjianById(id);
		   returnObject.setData(tongchouZengjian);
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
	public ReturnDatas saveorupdate(Model model,TongchouZengjian tongchouZengjian,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		try {
		
			java.lang.String id =tongchouZengjian.getId();
			if(StringUtils.isBlank(id)){
			  tongchouZengjian.setId(null);
			}
			String[] mothDate = request.getParameterValues("monthdate");//月份
			String userId = request.getParameter("userId");
			String tcjnd = request.getParameter("tcjnd");
			String company = request.getParameter("company");
			User user = userService.findUserById(userId);
			String[] insuranceTypearray = request.getParameterValues("insuranceTypeid");//统筹类型ID
			String[] feiyongtypearray = request.getParameterValues("feiyongtypeid");//费用类型ID
			String[] companySumarray = request.getParameterValues("companySum");//公司缴纳部分
			String[] personSumarray = request.getParameterValues("personSum");//个人缴纳部分
			List<TongchouZengjian> list = new ArrayList<TongchouZengjian>();
			if (mothDate.length > 0 && insuranceTypearray.length > 0 && feiyongtypearray.length > 0
					&& companySumarray.length > 0 && personSumarray.length > 0) {
				
				for (int i = 0; i < mothDate.length; i++) {
					TongchouZengjian zengjian = new TongchouZengjian();
					zengjian.setUserId(userId);
					if(StringUtils.isNoneEmpty(user.getName())){
						zengjian.setUserName(user.getName());
					}
					Date month = DateUtils.convertString2Date(mothDate[i] + "-01");
					zengjian.setMonthStr(DateUtils.convertDate2String("yyyy-MM-dd", month));
					zengjian.setInsuranceType(insuranceTypearray[i].toString());
					zengjian.setFeiyongtype(feiyongtypearray[i].toString());
					List<TongchouZengjian> listzj = tongchouZengjianService.finderInfoForList(null, zengjian);
					if(CollectionUtils.isNotEmpty(listzj)){
						returnObject.setStatus(ReturnDatas.ERROR);
						returnObject.setMessage("统筹增减项已经存在");
						return returnObject;
					}
					zengjian.setCreator(SessionUser.getUserId());
					zengjian.setCreateTime(new Date());
					zengjian.setInsuranceCompany(new BigDecimal(companySumarray[i]));
					zengjian.setInsurancePersonal(new BigDecimal(personSumarray[i]));
					zengjian.setMonth(month);
					zengjian.setTcjnd(tcjnd);
					zengjian.setCompany(company);
					zengjian.setIsused(ErpStateEnum.tcZjxStateEnum.否.getValue());//标记是否已经被引用
					zengjian.setActive(ErpStateEnum.tcstateEnum.是.getValue());//是否正常
					list.add(zengjian);
					
				}
				
			}
			tongchouZengjianService.saveOrUpdateForList(list);
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
		User user = new User();
		user.setUserType(userTypeEnum.员工.getValue());
		List<User> userList = userService.finderUserForList(user,null);
		model.addAttribute("userList", userList);
		//统筹类型
		List<DicData> tcList = dicDataService.findListDicData(DicdataTypeEnum.统筹类型.getValue(), null, null);
		model.addAttribute("tcList", tcList);	
		List<DicData> fyList = dicDataService.findListDicData(DicdataTypeEnum.费用类型.getValue(), null, null);
				//公司名称
		List<DicData> gsList = dicDataService.findListDicData(DicdataTypeEnum.公司.getValue(), null, null);
				//岗位
		List<DicData> addressList = dicDataService.findListDicData(DicdataTypeEnum.统筹缴纳地.getValue(), null, null);
				//用户
		model.addAttribute("tcList", tcList);	
		model.addAttribute("gsList", gsList);
		model.addAttribute("userList", userList);
		model.addAttribute("addressList", addressList);
		model.addAttribute("fyList", fyList);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/erp/tc/tongchouzengjian/tongchouzengjianCru";
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
				tongchouZengjianService.deletetongchouInfo(id);
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
			tongchouZengjianService.deleteByIds(ids,TongchouZengjian.class);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,MessageUtils.DELETE_ALL_SUCCESS);
		
		
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
		String path = "/WEB-INF/tmpl/tczjxmouban.xls";
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
		String msg = tongchouZjxImportService.importExcel(uploadfile, TongchouZjxImportService.titles, request);
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
