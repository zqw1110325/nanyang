package  org.springrain.system.web;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springrain.erp.constants.DicdataTypeEnum;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.gz.entity.Formula;
import org.springrain.erp.gz.service.IFormulaService;
import org.springrain.frame.controller.BaseController;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.frame.util.property.MessageUtils;
import org.springrain.system.entity.DicData;
import org.springrain.system.service.IDicDataService;
import org.springrain.system.service.ITableindexService;


/**
 * TODO 在此加入类描述
 * @copyright {@link springrain}
 * @author 9iu.dicData<Auto generate>
 * @version  2013-07-31 15:56:45
 * @see org.springrain.springrain.web.DicData
 */
@Controller
@RequestMapping(value="/system/dicdata/{pathtypekey}")
public class DicDataController  extends BaseController {
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IFormulaService formulaService;
	@Resource
	private ITableindexService tableindexService;
	
	private String listurl="/system/dicdata/dicdataList";
	
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param dicData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@PathVariable String pathtypekey,HttpServletRequest request, Model model,DicData dicData) 
			throws Exception {
		ReturnDatas returnObject = listjson(pathtypekey,request, model, dicData);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		DicData turnDic = dicDataService.findByCodeAndTypeKey(pathtypekey,DicdataTypeEnum.跳转类型.getValue());
		if(turnDic != null){
			return "/erp/dicdata/"+turnDic.getCode()+"/dicdataList";
		}
		return listurl;
	}
	
	/**
	 * json数据,为APP提供数据
	 * 
	 * @param request
	 * @param model
	 * @param dicData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list/json")
	@ResponseBody 
	public ReturnDatas listjson(@PathVariable String pathtypekey,HttpServletRequest request, Model model,DicData dicData) throws Exception{
		dicData.setTypekey(pathtypekey);
		String nopage = request.getParameter("page");//树结构不能分页
		Page page=null;
		if(!"false".equals(nopage)){
			page=newPage(request);
		}
		//List<DicData> datas=dicDataService.findListDicData(pathtypekey,page,dicData);
		dicData.setTypekey(pathtypekey);
//		List<DicData> datas=dicDataService.findListDataByFinder(null, page, DicData.class, dicData);
		String active = request.getParameter("active");
		if(StringUtils.isEmpty(active)){
			dicData.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		}
		List<DicData> datas=dicDataService.findDicDataForList(pathtypekey, page, dicData);
		//boolean hasNext = page.getHasNext();
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setData(datas);
		Map<String,String> map=new HashMap<String,String>();
		map.put("typekey", pathtypekey);
		returnObject.setQueryBean(dicData);//正式如果 ，加了缓存此处删除 
		returnObject.setPage(page);
		returnObject.setMap(map);
		return returnObject;
	}
	/**
	 * 列表数据,调用listjson方法,保证和app端数据统一
	 * 
	 * @param request
	 * @param model
	 * @param dicData
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/tree")
	public String tree(@PathVariable String pathtypekey,HttpServletRequest request, Model model,DicData dicData) 
			throws Exception {
		if(dicData!=null){
			dicData.setTypekey(pathtypekey);
		}
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setData(dicData);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		return "/system/dicdata/tree";
	}
	
	
	
	
	

	
		/**
	 * 查看操作,调用APP端lookjson方法
	 */
	@RequestMapping(value = "/look")
	public String look(@PathVariable String pathtypekey,Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception {
		
		ReturnDatas returnObject = lookjson(pathtypekey,model, request, response);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		DicData turnDic = dicDataService.findByCodeAndTypeKey(pathtypekey,DicdataTypeEnum.跳转类型.getValue());
		if(turnDic != null){
			return "/erp/dicdata/"+turnDic.getCode()+"/dicDataLook";
		}
		return "/system/dicData/dicDataLook";
	}

	
	/**
	 * 查看的Json格式数据,为APP端提供数据
	 */
	@RequestMapping(value = "/look/json")
	@ResponseBody 
	public ReturnDatas lookjson(@PathVariable String pathtypekey,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		java.lang.String id=request.getParameter("id");
		if(StringUtils.isNotBlank(id)){
		  DicData dicData = dicDataService.findDicDataById(id,pathtypekey);
		  returnObject.setData(dicData);
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
	public ReturnDatas saveorupdate(@PathVariable String pathtypekey,Model model,DicData dicData,HttpServletRequest request,HttpServletResponse response) throws Exception{
		ReturnDatas returnObject = ReturnDatas.getSuccessReturnDatas();
		returnObject.setMessage(MessageUtils.UPDATE_SUCCESS);
		
			try {
				String id=dicData.getId();
				String pid=dicData.getPid();
				if(StringUtils.isBlank(id)){
					dicData.setId(null);
				}
				if(StringUtils.isBlank(pid)){
					dicData.setPid(null);
				}
		    dicData.setTypekey(pathtypekey);
			if(DicdataTypeEnum.工资增减项类型.getValue().equals(pathtypekey) ||DicdataTypeEnum.统筹类型.getValue().equals(pathtypekey) ){
				String _id = null ;
				if(StringUtils.isBlank(id)){
					_id = tableindexService.updateNewIdSix(DicData.class);
					dicData.setId(_id);
					dicDataService.save(dicData);
				}else{
					dicDataService.update(dicData);
				}
//				dicDataService.saveorupdateDicData(dicData,pathtypekey);
				_id = dicData.getId();
				Formula formula =formulaService.findFormulaById(_id);
				boolean flag = true;
				if(formula == null){
					flag = false;
					formula = new Formula();
				}
				formula.setName(dicData.getName());
				formula.setChineseName(dicData.getName());
				formula.setProjectName("salary");
				formula.setActive(0);
				formula.setSysExpression(0);
				formula.setInEffectiveDate(new Date());
				if(flag){
					formulaService.update(formula);
				}else{
					formula.setId(_id);
					formulaService.save(formula);
				}
			}else{
				dicDataService.saveorupdateDicData(dicData,pathtypekey);
			}
			
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
	public String edit(@PathVariable String pathtypekey,Model model,HttpServletRequest request,HttpServletResponse response)  throws Exception{
		ReturnDatas returnObject = lookjson(pathtypekey,model, request, response);
		Map<String,String> map=new HashMap<String,String>();
		map.put("typekey", pathtypekey);
		returnObject.setMap(map);
		model.addAttribute(GlobalStatic.returnDatas, returnObject);
		DicData turnDic = dicDataService.findByCodeAndTypeKey(pathtypekey,DicdataTypeEnum.跳转类型.getValue());
		if(turnDic != null){
			return "/erp/dicdata/"+turnDic.getCode()+"/dicdataCru";
		}
		return "/system/dicdata/dicdataCru";
	}
	
	/**
	 * 删除操作
	 */
	@RequestMapping(value="/delete")
	@ResponseBody 
	public  ReturnDatas destroy(@PathVariable String pathtypekey,HttpServletRequest request) throws Exception {

			// 执行删除
		try {
		java.lang.String id=request.getParameter("id");
		String key = request.getParameter("key");
		if(StringUtils.isNotBlank(id)){
			DicData dicDate = dicDataService.findById(id, DicData.class);
			if(dicDate != null){
				dicDate.setActive(Integer.parseInt(key));
				dicDataService.update(dicDate);
			}
//				dicDataService.deleteDicDataById(id,pathtypekey);
				return new ReturnDatas(ReturnDatas.SUCCESS,
						MessageUtils.UPDATE_SUCCESS);
			} else {
				return new ReturnDatas(ReturnDatas.WARNING,
						MessageUtils.DELETE_WARNING);
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
	public ReturnDatas delMultiRecords(@PathVariable String pathtypekey,HttpServletRequest request, Model model) {
		String records = request.getParameter("records");
		if(StringUtils.isBlank(records)){
			 return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		String[] rs = records.split(",");
		if (rs == null || rs.length < 1) {
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_NULL_FAIL);
		}
		try {
			List<String> ids = Arrays.asList(rs);
			dicDataService.deleteDicDataByIds(ids,pathtypekey);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return new ReturnDatas(ReturnDatas.ERROR,
					MessageUtils.DELETE_ALL_FAIL);
		}
		return new ReturnDatas(ReturnDatas.SUCCESS,
				MessageUtils.DELETE_ALL_SUCCESS);
		
		
	}

	
}
