package org.springrain.erp.tc.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.tc.entity.TongchouZengjian;
import org.springrain.erp.tc.service.ITongchouZengjianService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.DicData;
import org.springrain.system.entity.User;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IDicDataService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-03 17:42:56
 * @see org.springrain.erp.tc.service.impl.TongchouZengjian
 */
@Service("tongchouZengjianService")
public class TongchouZengjianServiceImpl extends BaseSpringrainServiceImpl implements ITongchouZengjianService {

	@Resource
	private IDicDataService dicDataService;
    @Override
	public String  save(Object entity ) throws Exception{
	      TongchouZengjian tongchouZengjian=(TongchouZengjian) entity;
	       return super.save(tongchouZengjian).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      TongchouZengjian tongchouZengjian=(TongchouZengjian) entity;
		 return super.saveorupdate(tongchouZengjian).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 TongchouZengjian tongchouZengjian=(TongchouZengjian) entity;
	return super.update(tongchouZengjian);
    }
    @Override
	public TongchouZengjian findTongchouZengjianById(Object id) throws Exception{
	 return super.findById(id,TongchouZengjian.class);
	}
	
/**
 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
 * @param finder
 * @param page
 * @param clazz
 * @param o
 * @return
 * @throws Exception
 */
        @SuppressWarnings("unchecked")
		@Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
//			 return super.findListDataByFinder(finder,page,clazz,o);
			 
			 return (List<T>) finderInfoForList(page,(TongchouZengjian) o);
			}
	/**
	 * 根据查询列表的宏,导出Excel
	 * @param finder 为空则只查询 clazz表
	 * @param ftlurl 类表的模版宏
	 * @param page 分页对象
	 * @param clazz 要查询的对象
	 * @param o  querybean
	 * @return
	 * @throws Exception
	 */
		@Override
	public <T> File findDataExportExcel(Finder finder,String ftlurl, Page page,
			Class<T> clazz, Object o)
			throws Exception {
			 return super.findDataExportExcel(finder,ftlurl,page,clazz,o);
		}

	@Override
	public void saveOrUpdateForList(List<TongchouZengjian> list)
			throws Exception {
		if(CollectionUtils.isEmpty(list)){
			return;
		}
	/*	for(TongchouZengjian tongchouZengjian :list){
			tongchouZengjian.setId(null);
		}*/
		super.save(list);
		
	}

	@Override
	public List<TongchouZengjian> finderInfoForList(Page page,
			TongchouZengjian tongchouZengjian) throws Exception {
		Finder f = new Finder();
//		f.append("select * from z_tongchou_zengjian where 1=1 and active=:active").setParam("active", ErpStateEnum.tcstateEnum.是.getValue());
		f.append("select tc.*,u.name createName from "+Finder.getTableName(TongchouZengjian.class)+" as  tc ,"+Finder.getTableName(User.class)+" as u where tc.creator=u.id ");
		f.append(" and tc.active=:active").setParam("active", ErpStateEnum.tcstateEnum.是.getValue());
		if(StringUtils.isNotBlank(tongchouZengjian.getUserName())){
			f.append(" and tc.userName like:userName");
		}
		if(StringUtils.isNotBlank(tongchouZengjian.getInsuranceType())){
			f.append(" and tc.insuranceType =:insuranceType");
		}
		if(StringUtils.isNotBlank(tongchouZengjian.getFeiyongtype())){
			f.append(" and tc.feiyongtype =:feiyongtype");
		}
		if(StringUtils.isNotBlank(tongchouZengjian.getMonthStr())){
			f.append(" and tc.month =:month");
			String monthStr = tongchouZengjian.getMonthStr();
			f.setParam("month",DateUtils.convertString2Date(monthStr+"-01"));
		}
		if(tongchouZengjian.getMonth()!=null){
			f.append(" and tc.month =:month");
			f.setParam("month",tongchouZengjian.getMonth());
		}
		if(StringUtils.isNotBlank(tongchouZengjian.getCompany())){
			f.append(" and tc.company =:company");
			f.setParam("company",tongchouZengjian.getCompany());
		}
//		monthStr
		f.setParam("feiyongtype", tongchouZengjian.getFeiyongtype());
		f.setParam("insuranceType", tongchouZengjian.getInsuranceType());
		f.setParam("userName", "%"+tongchouZengjian.getUserName()+"%");
		f.append(" order by tc.createTime desc ");
		List<TongchouZengjian> datas = super.queryForList(f, TongchouZengjian.class, page);
		Map<String, DicData> tcdata = dicDataService.getAlldicData(null);
		if(CollectionUtils.isNotEmpty(datas)){
			for(TongchouZengjian zengjian:datas){
				if(tcdata.get(zengjian.getInsuranceType())!=null){
					zengjian.setTcname(tcdata.get(zengjian.getInsuranceType()).getName());
				}
				if(tcdata.get(zengjian.getFeiyongtype())!=null){
					zengjian.setFyname(tcdata.get(zengjian.getFeiyongtype()).getName());
				}
			}
		}
		return datas ;
	}

	@Override
	public void deletetongchouInfo(String id) throws Exception {	
		if(StringUtils.isBlank(id))
			return;
		Finder finder = new Finder();
		finder.append(" update  "+Finder.getTableName(TongchouZengjian.class)+" set active=:active where  id =:id");
		finder.setParam("id", id);
		finder.setParam("active", ErpStateEnum.tcstateEnum.否.getValue());
		super.update(finder);
	}

	@Override
	public List<TongchouZengjian> finderTczjxForList(String userId, Date month,List<String> listStr)
			throws Exception {
		if(StringUtils.isBlank(userId)&&month== null){
			return null;
		}
		Finder f = new Finder();
		f.append("select * from "+Finder.getTableName(TongchouZengjian.class)+" where active=:active").setParam("active", ErpStateEnum.tcstateEnum.是.getValue());
		if(StringUtils.isNotBlank(userId)){
			f.append(" and userId =:userId");
		}
		if(month!=null){
			f.append(" and month=:month");
		}
		//计算工资只查询代扣代缴
		if(CollectionUtils.isNotEmpty(listStr)){
			f.append(" and feiyongtype in(:feiyongtype)");
		}
		f.setParam("userId", userId);
		f.setParam("month", month);
		f.setParam("feiyongtype", listStr);
		
		List<TongchouZengjian> zjList	= queryForList(f, TongchouZengjian.class);
		Map<String, DicData> dicmap = dicDataService.getAlldicData(null);
		if(CollectionUtils.isEmpty(zjList)){
			return zjList;
		}
		for(TongchouZengjian tongchouZengjian:zjList){
			tongchouZengjian.setMonthStr(DateUtils.convertDate2String(DateUtils.DATE_FORMAT, tongchouZengjian.getMonth()));
			if(StringUtils.isNotBlank(tongchouZengjian.getInsuranceType())&&dicmap.get(tongchouZengjian.getInsuranceType())!=null){
				tongchouZengjian.setTcname(dicmap.get(tongchouZengjian.getInsuranceType()).getName());
			}
			if(StringUtils.isNotBlank(tongchouZengjian.getFeiyongtype())&&dicmap.get(tongchouZengjian.getFeiyongtype())!=null){
				tongchouZengjian.setFyname(dicmap.get(tongchouZengjian.getFeiyongtype()).getName());
			}
		}
		return zjList;
	}

	@Override
	public BigDecimal findzengjianToal(String userId, Date month)
			throws Exception {
		if(StringUtils.isBlank(userId)&&month== null){
			return null;
		}
		Finder f = new Finder();
		f.append("select * from "+Finder.getTableName(TongchouZengjian.class)+" where active=:active").setParam("active", ErpStateEnum.tcstateEnum.是.getValue());
		if(StringUtils.isNotBlank(userId)){
			f.append(" and userId =:userId");
		}
		if(month!=null){
			f.append(" and month=:month");
		}
		f.setParam("userId", userId);
		f.setParam("month", month);
		List<TongchouZengjian> list =  queryForList(f, TongchouZengjian.class);
		BigDecimal bigDecimal =new BigDecimal(BigInteger.ZERO);
		if(CollectionUtils.isEmpty(list)){
			return bigDecimal;
		}
		for(TongchouZengjian zengjian:list){
			bigDecimal=bigDecimal.add(zengjian.getInsurancePersonal());
		}
		return bigDecimal;
	}

	@Override
	public List<TongchouZengjian> finderTczjxForFenzuList(String userId,
			Date month) throws Exception {
		if(StringUtils.isBlank(userId)&&month== null){
			return null;
		}
		Finder finder = new Finder(
				"select userId,month,sum(insurancePersonal) as insurancePersonal,insuranceType,feiyongtype from "
						+ Finder.getTableName(TongchouZengjian.class)
						+ " where userId=:userId and month=:inDate and active=:active group by userId,insuranceType,feiyongtype,month order by userid ");
		finder.setParam("userId", userId).setParam("inDate", month).setParam("active", ErpStateEnum.tcstateEnum.是.getValue());
		return super.queryForList(finder, TongchouZengjian.class);
	}

}
