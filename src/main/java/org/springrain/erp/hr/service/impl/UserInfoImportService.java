package org.springrain.erp.hr.service.impl;

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
import org.springrain.erp.constants.DicdataTypeEnum;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.gz.util.BaseImport;
import org.springrain.erp.gz.util.GlobalStaticVar;
import org.springrain.erp.hr.entity.UserInfo;
import org.springrain.erp.hr.service.IUserInfoService;
import org.springrain.frame.util.DateUtils;
import org.springrain.system.entity.DicData;
import org.springrain.system.entity.User;
import org.springrain.system.service.IDicDataService;
import org.springrain.system.service.IUserService;

@Service("userInfoImportService")
public class UserInfoImportService extends BaseImport {
	@Resource
	private IUserService userService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IDicDataService dicDataService;
	public static String[] titles = { "员工姓名","员工代码","性别","出生日期","工号","身份证号","公司","统筹缴纳地","岗位","级别","首次参加工作时间","入职日期","转正日期","离职日期","手机号","备用手机号","固定电话","电子邮箱","婚否","健康状态","入职城市","入职方式","介绍人","民族","政治面貌","最高学历","籍贯","户籍地址","现居住详细地址","紧急联系人","紧急联系电话","与本人关系","兴趣爱好","备注" };
	List<String> listTitle = Arrays.asList(titles);
	private static final String entityKey = "key";
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected String checkData() throws Exception {
		try {
			
		
		UserInfo dto = new UserInfo();
		//员工姓名
		int index = 0;
		String indexvalue = rowCells.get(index).trim();
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		String name2 = indexvalue.replaceAll(" ", "");
		List<User> list = userService.finderUserByUserName(indexvalue,name2);
		if(CollectionUtils.isEmpty(list)){
			return "系统中不存在"+indexvalue+"用户";
		}
		dto.setId(list.get(0).getId());
		dto.setUserId(list.get(0).getId());
		//员工代码
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setUserCode(indexvalue);
		//性别
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setSex(indexvalue);
		//出生日期
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		try {
			Date birthday = DateUtils.convertString2Date(indexvalue);
			dto.setBirthday(birthday);
		} catch (Exception e) {
			return getCellPosition(index) + listTitle.get(index) + "格式错误";
		}
		//工号
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setWorkno(indexvalue);
		//身份证号
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		String message = GlobalStaticVar.checkIDCard(indexvalue.replace("X", "x"));
		if(StringUtils.isNotBlank(message)){
			return getCellPosition(index) + listTitle.get(index) + "身份证号不合法";
		}
		dto.setIdCard(indexvalue);
		//公司
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		DicData qbxl=new DicData();
		qbxl.setName(indexvalue);
		qbxl.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		List<DicData> lsxl= dicDataService.findListDicData(DicdataTypeEnum.公司.getValue(), null, qbxl);
		if(CollectionUtils.isEmpty(lsxl)){
			return getCellPosition(index)+ "公司【"+ indexvalue + "】系统中不存在";
		};
		DicData da= lsxl.get(0);
		if(da!=null) {
			dto.setCompany(da.getId());
		}
		//统筹缴纳地
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		qbxl=new DicData();
		qbxl.setName(indexvalue);
		qbxl.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		lsxl= dicDataService.findListDicData(DicdataTypeEnum.统筹缴纳地.getValue(), null, qbxl);
		if(CollectionUtils.isEmpty(lsxl)){
			return getCellPosition(index)+ "统筹缴纳地【"+ indexvalue + "】系统中不存在";
		};
		da= lsxl.get(0);
		if(da!=null) {
			dto.setTongchou(da.getId());
		}
		//岗位
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		qbxl=new DicData();
		qbxl.setName(indexvalue);
		qbxl.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		lsxl= dicDataService.findListDicData(DicdataTypeEnum.岗位.getValue(), null, qbxl);
		if(CollectionUtils.isEmpty(lsxl)){
			return getCellPosition(index)+ "岗位【"+ indexvalue + "】系统中不存在";
		};
		da= lsxl.get(0);
		if(da!=null) {
			dto.setGangwei(da.getId());
		}
		//级别
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		qbxl=new DicData();
		qbxl.setName(indexvalue);
		qbxl.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		lsxl= dicDataService.findListDicData(DicdataTypeEnum.级别.getValue(), null, qbxl);
		if(CollectionUtils.isEmpty(lsxl)){
			return getCellPosition(index)+ "级别【"+ indexvalue + "】系统中不存在";
		};
		da= lsxl.get(0);
		if(da!=null) {
			dto.setGrade(da.getId());
		}
		//首次参加工作时间
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		try {
			Date firstTime = DateUtils.convertString2Date(indexvalue);
			dto.setFirstTime(firstTime);
		} catch (Exception e) {
			return getCellPosition(index) + listTitle.get(index) + "格式错误";
		}
		//入职日期
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		try {
			Date entryDate = DateUtils.convertString2Date(indexvalue);
			dto.setEntryDate(entryDate);
		} catch (Exception e) {
			return getCellPosition(index) + listTitle.get(index) + "格式错误";
		}
		//转正日期	
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isNotBlank(indexvalue)) {
			try {
				Date startDate = DateUtils.convertString2Date(indexvalue);
				dto.setStartDate(startDate);
			} catch (Exception e) {
				return getCellPosition(index) + listTitle.get(index) + "格式错误";
			}
		}
		//离职日期
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isNotBlank(indexvalue)) {
			try {
				Date endDate = DateUtils.convertString2Date(indexvalue);
				dto.setEndDate(endDate);
			} catch (Exception e) {
				return getCellPosition(index) + listTitle.get(index) + "格式错误";
			}
		}
		//手机号
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setMobile1(indexvalue);
		//备用手机号
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isNotBlank(indexvalue)) {
			dto.setMobile2(indexvalue);
		}
		//固定电话
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isNotBlank(indexvalue)) {
			dto.setTelephone(indexvalue);
		}
		//电子邮箱
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isNotBlank(indexvalue)) {
			dto.setEmail(indexvalue);
		}
		//婚否
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setMarryState(indexvalue);
		//健康状态
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setHealth(indexvalue);
		//入职城市
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		qbxl=new DicData();
		qbxl.setName(indexvalue);
		qbxl.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		lsxl= dicDataService.findListDicData(DicdataTypeEnum.入职城市.getValue(), null, qbxl);
		if(CollectionUtils.isEmpty(lsxl)){
			return getCellPosition(index)+ "入职城市【"+ indexvalue + "】系统中不存在";
		};
		da= lsxl.get(0);
		if(da!=null) {
			dto.setEntryCity(da.getId());
		}
		//入职方式
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setEntryType(indexvalue);
		//介绍人
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isNotBlank(indexvalue)) {
			dto.setIntroducer(indexvalue);
		}
		//民族
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		qbxl=new DicData();
		qbxl.setName(indexvalue);
		qbxl.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		lsxl= dicDataService.findListDicData(DicdataTypeEnum.民族类型.getValue(), null, qbxl);
		if(CollectionUtils.isEmpty(lsxl)){
			return getCellPosition(index)+ "民族【"+ indexvalue + "】系统中不存在";
		};
		da= lsxl.get(0);
		if(da!=null) {
			dto.setMinzu(da.getId());
		}
		//政治面貌
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		qbxl=new DicData();
		qbxl.setName(indexvalue);
		qbxl.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		lsxl= dicDataService.findListDicData(DicdataTypeEnum.政治面貌.getValue(), null, qbxl);
		if(CollectionUtils.isEmpty(lsxl)){
			return getCellPosition(index)+ "政治面貌【"+ indexvalue + "】系统中不存在";
		};
		da= lsxl.get(0);
		if(da!=null) {
			dto.setPoliticsStatus(da.getId());
		}
		//最高学历
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		qbxl=new DicData();
		qbxl.setName(indexvalue);
		qbxl.setActive(Integer.parseInt(ErpStateEnum.stateEnum.是.getValue()));
		lsxl= dicDataService.findListDicData(DicdataTypeEnum.学历类型.getValue(), null, qbxl);
		if(CollectionUtils.isEmpty(lsxl)){
			return getCellPosition(index)+ "学历类型【"+ indexvalue + "】系统中不存在";
		};
		da= lsxl.get(0);
		if(da!=null) {
			dto.setZuigaoxueli(da.getId());
		}
		//籍贯
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setJiguan(indexvalue);
		//户籍地址
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setHujidizhi(indexvalue);
		//现居住详细地址
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setAddress(indexvalue);
		//紧急联系人
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setFireName(indexvalue);
		//紧急联系电话
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setFirePhone(indexvalue);
		//与本人关系
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setGuanxi(indexvalue);
		//兴趣爱好
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setXingqu(indexvalue);
		
		//备注
		index += 1;
		indexvalue = rowCells.get(index).trim();
		dto.setBak(indexvalue);
		Map m = new HashMap();
		m.put(entityKey, dto);
		if (listEntities == null)
			listEntities = new ArrayList<Map>();
		listEntities.add(m);
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		List<UserInfo> list = new ArrayList<UserInfo>();
		for (Map m : listEntities) {
			if (m.get(entityKey) != null) {
				UserInfo u = (UserInfo) m.get(entityKey);
				list.add(u);
			}
		}
		userInfoService.saveorupdateUserBaseInfo(list);
		return true;
	}

	@Override
	protected void initOtherParam() {
		// TODO Auto-generated method stub

	}

}
