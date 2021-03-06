package org.springrain.erp.zc.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.NumberUtils;
import org.springrain.erp.constants.DicdataTypeEnum;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.gz.util.BaseImport;
import org.springrain.erp.zc.entity.Zichan;
import org.springrain.erp.zc.service.IZichanService;
import org.springrain.frame.util.DateUtils;
import org.springrain.system.entity.DicData;
import org.springrain.system.service.IDicDataService;
import org.springrain.system.service.IUserService;

@Service("zichanImportService")
public class ZichanImportService extends BaseImport {
	@Resource
	private IUserService userService;
	@Resource
	private IDicDataService dicDataService;
	@Resource
	private IZichanService zichanService;
																		
	public static String[] titles = { "资产编号", "资产名称", "资产类型","资产类别","规格型号", "单位","存放地点", "购买日期","入库日期" ,"资产数量" ,"库存量" ,"资产单价" ,"资产金额" ,"使用年限" ,"质保到期日" ,"购买渠道" ,"购买渠道联系方式" ,"资产配置情况" ,"采购人" ,"备注" };
	List<String> listTitle = Arrays.asList(titles);
	private static final String entityKey = "key";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected String checkData() throws Exception {
		Zichan dto = new Zichan();
		//资产编号
		int index = 0;
		String indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		if (!zichanService.findZcCodeLegal(indexvalue, null)){
			return getCellPosition(index) + listTitle.get(index) + "不能重复";
		}
		dto.setZccode(indexvalue);
		//资产名称
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setZcname(indexvalue);
		//资产类型
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isNotBlank(indexvalue)) {
			//查询字典表对应的id  如果不存在说明资产类型不对
			DicData qb=new DicData();
			qb.setName(indexvalue);
			qb.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
			List<DicData> ls= dicDataService.findListDicData(DicdataTypeEnum.资产类型.getValue(), null, qb);
			if(CollectionUtils.isEmpty(ls)) {
				return getCellPosition(index) + indexvalue + "系统中不存在";
			}
			DicData da= ls.get(0);
			if(da!=null) {
				dto.setZctype(da.getId());
			}
		}
		//资产类别
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isNotBlank(indexvalue)) {
			//查询字典表对应的id  如果不存在说明资产类型不对
			DicData qb=new DicData();
			qb=new DicData();
			qb.setName(indexvalue);
			qb.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
			List<DicData> ls= dicDataService.findListDicData(DicdataTypeEnum.资产类别.getValue(), null, qb);
			if(CollectionUtils.isEmpty(ls)) {
				return getCellPosition(index) + indexvalue + "系统中不存在";
			}
			DicData da= ls.get(0);
			if(da!=null) {
				dto.setZcleibie(da.getId());
			}
		}
		//规格型号
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if(StringUtils.isNotBlank(indexvalue)){
			dto.setGuige(indexvalue.replaceAll("，", ",").replaceAll("：", ":"));
		}
		//单位
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setDanwei(indexvalue);
		//存放地点
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setZccfd(indexvalue);
		//购买日期
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if(StringUtils.isNotBlank(indexvalue)){
			dto.setGoumaidate(DateUtils.convertString2Date(indexvalue));	
		}
		//入库日期
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if(StringUtils.isNotBlank(indexvalue)){
			dto.setRukudate(DateUtils.convertString2Date(indexvalue));	
		}else{
			dto.setRukudate(new Date());
		}
		//资产数量
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		Integer num = 0;
		try {
			indexvalue = indexvalue.substring(0, indexvalue.indexOf("."));
			num = NumberUtils.parseNumber(indexvalue, Integer.class);
		} catch (Exception e) {
			return getCellPosition(index) + listTitle.get(index) + "只能为整数";
		}
		if(num < 0){
			return getCellPosition(index) + listTitle.get(index) + "不能为负数";
		}
		dto.setZcnumber(num);
		//库存量
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		try {
			indexvalue = indexvalue.substring(0, indexvalue.indexOf("."));
			num = NumberUtils.parseNumber(indexvalue, Integer.class);
		} catch (Exception e) {
			return getCellPosition(index) + listTitle.get(index) + "只能为整数";
		}
		if(num < 0){
			return getCellPosition(index) + listTitle.get(index) + "不能为负数";
		}
		dto.setKucun(num);
		//资产单价
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		if(new BigDecimal(indexvalue).signum()<0){
			return getCellPosition(index) + listTitle.get(index) + "不能为负数";
		}
		dto.setZcprice(new BigDecimal(indexvalue));
		//资产金额
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		if(new BigDecimal(indexvalue).signum()<0){
			return getCellPosition(index) + listTitle.get(index) + "不能为负数";
		}
		dto.setZcmoney(new BigDecimal(indexvalue));
		//使用年限
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		if(new BigDecimal(indexvalue).signum()<0){
			return getCellPosition(index) + listTitle.get(index) + "不能为负数";
		}
		dto.setNianxian(new BigDecimal(indexvalue));
		//质保到期日
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if(StringUtils.isNotBlank(indexvalue)){
			dto.setZhibaodate(DateUtils.convertString2Date(indexvalue));	
		}
		//经销商
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setJingxiaoshang(indexvalue);
		//经销商联系方式
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setJingxiaoshangtel(indexvalue);
		//资产配置情况
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setZcconfig(indexvalue);
		//经手人
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setJsuser(indexvalue);
		
		//备注
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setRemark(indexvalue);
		
		Map m = new HashMap();
		m.put(entityKey, dto);
		if (listEntities == null)
			listEntities = new ArrayList<Map>();
		listEntities.add(m);
		return null;
	}

	@Override
	protected String extraCheck() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected boolean saveData() throws Exception {
		List<Zichan> list = new ArrayList<Zichan>();
		for (Map m : listEntities) {
			if (m.get(entityKey) != null) {
				Zichan z = (Zichan) m.get(entityKey);
				list.add(z);
			}
		}
		zichanService.saveZichanList(list);

		return true;
	}

	@Override
	protected void initOtherParam() {
		// TODO Auto-generated method stub

	}

}
