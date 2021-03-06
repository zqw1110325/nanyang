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
import org.springrain.erp.gz.util.BaseImport;
import org.springrain.erp.hr.entity.UserCertificate;
import org.springrain.erp.hr.service.IUserCertificateService;
import org.springrain.frame.util.DateUtils;
import org.springrain.system.entity.User;
import org.springrain.system.service.IUserService;

@Service("userCertificateImportService")
public class UserCertificateImportService extends BaseImport {
	@Resource
	private IUserService userService;
	@Resource
	private IUserCertificateService userCertificateService;
	public static String[] titles = { "姓名", "证书编号","证书名称","颁证机构", "证书通过时间","证书有效期" };
	List<String> listTitle = Arrays.asList(titles);
	private static final String entityKey = "key";

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	protected String checkData() throws Exception {
		UserCertificate dto = new UserCertificate();
		// 姓名
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
		dto.setUserId(list.get(0).getId());
		//证书编号
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setBianhao(indexvalue);
//		
		//证书名称
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setName(indexvalue);
		//机构
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		dto.setBanzhengjigou(indexvalue);
		//时间
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		Date inDate = DateUtils.convertString2Date(indexvalue);
		dto.setPassDate(inDate);
		//时间
		index += 1;
		indexvalue = rowCells.get(index).trim();
		if (StringUtils.isBlank(indexvalue)) {
			return getCellPosition(index) + listTitle.get(index) + "不能为空";
		}
		Date youxiaoDate = DateUtils.convertString2Date(indexvalue);
		dto.setYouxiaoqi(youxiaoDate);
		Map m = new HashMap();
		m.put(entityKey, dto);
		if (listEntities == null)
			listEntities = new ArrayList<Map>();
		listEntities.add(m);
		return null;
	}

	@Override
	protected String extraCheck() throws Exception {
		// 额外校验
		return null;
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected boolean saveData() throws Exception {
		List<UserCertificate> list = new ArrayList<UserCertificate>();
		for (Map m : listEntities) {
			if (m.get(entityKey) != null) {
				UserCertificate u = (UserCertificate) m.get(entityKey);
				list.add(u);
			}
		}
		userCertificateService.saveOrUpdate(list);
		return true;

	}

	@Override
	protected void initOtherParam() {
		// 初始化参数

	}

}
