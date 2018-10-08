package org.springrain.erp.tc.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.constants.TcshowEnum.tcShowPropetryEnum;
import org.springrain.erp.gz.util.MathUtils;
import org.springrain.erp.hr.entity.UserInfo;
import org.springrain.erp.hr.service.IUserInfoService;
import org.springrain.erp.tc.dto.DkdjRecordDto;
import org.springrain.erp.tc.dto.TongchouInfoDto;
import org.springrain.erp.tc.entity.TongchouInfo;
import org.springrain.erp.tc.entity.TongchouRecord;
import org.springrain.erp.tc.entity.TongchouShow;
import org.springrain.erp.tc.service.ITongchouRecordService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.frame.util.ReturnDatas;
import org.springrain.system.entity.DicData;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IDicDataService;



/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-03 17:42:27
 * @see org.springrain.erp.tc.service.impl.TongchouRecord
 */
@Service("tongchouRecordService")
public class TongchouRecordServiceImpl extends BaseSpringrainServiceImpl implements ITongchouRecordService {

	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IDicDataService dicDataService;
    @Override
	public String  save(Object entity ) throws Exception{
	      TongchouRecord tongchouRecord=(TongchouRecord) entity;
	       return super.save(tongchouRecord).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      TongchouRecord tongchouRecord=(TongchouRecord) entity;
		 return super.saveorupdate(tongchouRecord).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 TongchouRecord tongchouRecord=(TongchouRecord) entity;
	return super.update(tongchouRecord);
    }
    @Override
	public TongchouRecord findTongchouRecordById(Object id) throws Exception{
	 return super.findById(id,TongchouRecord.class);
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
        	return (List<T>) finderInfoForList((TongchouRecord) o, page);
        	
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
	public void saveTcRecord(String month) throws Exception {
		if(StringUtils.isBlank(month)){
			return;
		}
		Date startDate = DateUtils.getFirstDayOfMonth(month);//获取当前时间第一天
		Date endDate = DateUtils.getNextDay(startDate);//获取下月第一人天
		//查找统筹信息 
		//条件 小于缴费结束时间且大于停保时间或者停保时间是null且状态是正常的
		List<TongchouInfo> tcInfoList = findTongchouInfoList(startDate);
		List<TongchouRecord> list = new ArrayList<TongchouRecord>();
		if(CollectionUtils.isNotEmpty(tcInfoList)){
			//查询数据记录表中是否存在当前对应月份的数据,全部删除
			Finder f = Finder.getDeleteFinder(TongchouRecord.class);
//			f.append(" where month >= :startDate and month < :endDate and state =:state and salayid is null ");
			f.append(" where month >= :startDate and month < :endDate and state =:state ");
			f.setParam("startDate", startDate);
			f.setParam("endDate", endDate);
			f.setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
			super.update(f);
			
			//循环遍历赋值
			for(TongchouInfo info:tcInfoList){
				TongchouRecord infoReport = new TongchouRecord();
				infoReport.setDepartment(info.getDepartment());
				infoReport.setCompany(info.getCompany());
				infoReport.setTcjiaonadi(info.getTcjiaonadi());
				infoReport.setUserId(info.getUserId());
				infoReport.setStopProtectMonth(info.getStopProtectMonth());
				infoReport.setInsuranceorgongjijinAccount(info.getInsuranceorgongjijinAccount());
				infoReport.setInsurgongjijinType(info.getInsurgongjijinType());
				infoReport.setInsurancePaymentDate(startDate);// 当前查询月
				infoReport.setEfficientDate(info.getEfficientDate());
				infoReport.setRadices(info.getRadices());
				infoReport.setInsuranceCompany(info.getInsuranceCompany());
				infoReport.setInsurancePersonal(info.getInsurancePersonal());
				infoReport.setInsuranceType(info.getInsuranceType());
				infoReport.setRemark(info.getRemark());
				infoReport.setState(info.getState());
				infoReport.setCreator(SessionUser.getUserId());
				infoReport.setCreateTime(new Date());
				infoReport.setCardId(info.getCardId());
				infoReport.setUserName(info.getUserName());
				infoReport.setMonth(startDate);
				infoReport.setSalayid(null);
				list.add(infoReport);
			}
			super.save(list);
		}
	}
	/**
	 * 查TongchouInfo表中对应月份的数据
	 * @param month 月份
	 * @return
	 * @throws Exception
	 */
	private List<TongchouInfo> findTongchouInfoList(Date date)
			throws Exception {
		Finder finder = new Finder();
		finder.append(" select * from "+Finder.getTableName(TongchouInfo.class)+"");
		finder.append(" where (efficientDate IS NULL or efficientDate>= :date)  and (stopProtectMonth is null or stopProtectMonth >= :date) and state =:state");
		finder.setParam("date", date);
		finder.setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
		return queryForList(finder, TongchouInfo.class);
	}

	@Override
	public List<TongchouInfoDto> finderInfoForList(
			TongchouRecord tongchouRecord, Page page) throws Exception {
		//首先查找用userId 主要是分页数据显示正确。
		List<TongchouRecord> list = new ArrayList<TongchouRecord>();
				Finder f = new Finder();
				f.append(" select DISTINCT userId from "+Finder.getTableName(TongchouRecord.class)+" where  state=:state ");
				f.setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
				if(StringUtils.isNotEmpty(tongchouRecord.getUserName())){
					f.append(" and userName =:userName");
				}
				if(StringUtils.isNotEmpty(tongchouRecord.getCompany())){
					f.append(" and company=:company");
				}
				if(tongchouRecord.getMonth()!=null){
					f.append(" and month=:month");
				}
				f.setParam("company", tongchouRecord.getCompany());
				f.setParam("month", tongchouRecord.getMonth());
				f.setParam("userName", tongchouRecord.getUserName());
				List<String> listUserIdStr = super.queryForList(f, String.class, page);
				if(CollectionUtils.isEmpty(listUserIdStr)){
					return null;
				}
				//根据查询的userid查找统筹信息 
				Finder finder = new  Finder();
				finder.append(" select * from "+Finder.getTableName(TongchouRecord.class)+" where  state=:state ").setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
				finder.append(" and userId in(:userId)");
				if(StringUtils.isNotEmpty(tongchouRecord.getUserName())){
					finder.append(" and userName =:userName");
				}
				if(StringUtils.isNotEmpty(tongchouRecord.getCompany())){
					finder.append(" and company=:company");
				}
				if(tongchouRecord.getMonth()!=null){
					finder.append(" and month=:month");
				}
				finder.setParam("company", tongchouRecord.getCompany());
				finder.setParam("month", tongchouRecord.getMonth());
				finder.setParam("userName", tongchouRecord.getUserName());
				finder.setParam("userId", listUserIdStr);
				list=super.queryForList(finder, TongchouRecord.class);
				return convertShowInfo(tongchouRecord.getListShow(), list);
	}

	@Override
	public List<TongchouRecord> finderRecordForList(String userId, Date month)
			throws Exception {
		if(StringUtils.isBlank(userId)&&month== null){
			return null;
		}
		Finder f = new Finder();
		f.append("select * from "+Finder.getTableName(TongchouRecord.class)+" where state=:state").setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
		if(StringUtils.isNotBlank(userId)){
			f.append(" and userId =:userId");
		}
		if(month!=null){
			f.append(" and month=:month");
		}
		f.setParam("userId", userId);
		f.setParam("month", month);
		return queryForList(f, TongchouRecord.class);
	}

	private List<TongchouInfoDto> convertShowInfo(List<TongchouShow> showTitles,
			List<TongchouRecord> recordList) throws Exception {


		if(CollectionUtils.isEmpty(showTitles)||CollectionUtils.isEmpty(recordList)){
			return null;
		}
		List<TongchouInfoDto> list = new ArrayList<TongchouInfoDto>();
		Map<String, TongchouInfoDto> map = new LinkedHashMap<String, TongchouInfoDto>();
		for(TongchouRecord record : recordList){
			String userId = record.getUserId();
			TongchouInfoDto dto = map.get(userId);
			if(dto==null){
				dto = new TongchouInfoDto();
				dto.setShowList(beanValuesCopy(showTitles));//显示的信息
				dto.setUsername(record.getUserName());//用户名
				dto.setUserId(record.getUserId());
				dto.setStopProtectMonth(record.getStopProtectMonth());//停保月份
				UserInfo userInfo = userInfoService.findUserInfoByUserId(record.getUserId());
				dto.setJobNumber(userInfo.getWorkno());//工号
				dto.setCardId(userInfo.getIdCard());//用户的身份证号
				dto.setMonth(record.getMonth());
				dto.setSalayId(record.getSalayid());
				DicData dicData1 = dicDataService.findDicDataById(userInfo.getGangwei());
				DicData dicData2 = dicDataService.findDicDataById(record.getCompany());
				DicData dicData3 = dicDataService.findDicDataById(record.getTcjiaonadi());
				if(dicData1!=null){
					dto.setDutyTypeName(dicData1.getName());
				}
				if(dicData2!=null){
					dto.setInCompany(dicData2.getName());;//所在公司
				}
				if(dicData3!=null){
					dto.setTcjiaonadi(dicData3.getName());;//所在公司
				}
				dto.setCardId(userInfo.getIdCard());
				map.put(userId, dto);
				list.add(dto);
			}
			//统筹信息设置
			List<TongchouShow> tempShows = dto.getShowList();
			for(TongchouShow temp : tempShows){
				if(!temp.getDicttypeId().equals(record.getInsuranceType()))
					continue;
				String property = temp.getProperty();
				if(tcShowPropetryEnum.账号.getValue().equals(property))
					temp.setPropertyValue(record.getInsuranceorgongjijinAccount());
				else if(tcShowPropetryEnum.个人缴费.getValue().equals(property))
					temp.setPropertyValue((record.getInsurancePersonal()==null)?BigDecimal.ZERO.toString():record.getInsurancePersonal().toString());
				else if(tcShowPropetryEnum.单位缴费.getValue().equals(property))
					temp.setPropertyValue((record.getInsuranceCompany()==null)?BigDecimal.ZERO.toString():record.getInsuranceCompany().toString());
				else if(tcShowPropetryEnum.缴费开始时间.getValue().equals(property))
					temp.setPropertyValue(DateUtils.convertDate2String("yyyy-MM", record.getInsurancePaymentDate()));
				else if(tcShowPropetryEnum.缴费结束时间.getValue().equals(property))
						if( record.getEfficientDate()!=null){
							temp.setPropertyValue(DateUtils.convertDate2String("yyyy-MM", record.getEfficientDate()));
						}
				else if(tcShowPropetryEnum.缴费基数.getValue().equals(property)){
					temp.setPropertyValue(record.getRadices().toString());
				}
			}
		}
		
		return list;
	
	}
	private List<TongchouShow> beanValuesCopy(List<TongchouShow> showTitles){
		List<TongchouShow> list = new ArrayList<TongchouShow>();
		for(TongchouShow show : showTitles){
			TongchouShow temp = new TongchouShow();
			temp.setDeskShowName(show.getDeskShowName());
			temp.setDicttypeId(show.getDicttypeId());
			temp.setShowOrhidden(show.getShowOrhidden());
			String property = show.getProperty();
			temp.setProperty(property);
			list.add(temp);
		}
		return list;
	}

	@Override
	public BigDecimal findRecordToal(String userId, Date month)
			throws Exception {
		if(StringUtils.isBlank(userId)&&month== null){
			return null;
		}
		Finder f = new Finder();
		f.append("select * from "+Finder.getTableName(TongchouRecord.class)+" where state=:state").setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
		if(StringUtils.isNotBlank(userId)){
			f.append(" and userId =:userId");
		}
		if(month!=null){
			f.append(" and month=:month");
		}
		f.setParam("userId", userId);
		f.setParam("month", month);
		List<TongchouRecord> list =  queryForList(f, TongchouRecord.class);
		BigDecimal bigDecimal =new BigDecimal(BigInteger.ZERO);
		if(CollectionUtils.isEmpty(list)){
			return bigDecimal;
		}
		for(TongchouRecord record:list){
			bigDecimal=bigDecimal.add(record.getInsurancePersonal());
		}
		return bigDecimal;
	}

	@Override
	public ReturnDatas saveTongchouRecord(String userId,
			String salayId, Date month) throws Exception {
		List<TongchouRecord> list = new ArrayList<TongchouRecord>();
		ReturnDatas returnDatas = ReturnDatas.getSuccessReturnDatas();
		//		if(StringUtils.isEmpty(userId)&&StringUtils.isEmpty(salayId)&&month==null){
		if(StringUtils.isEmpty(userId)){
			returnDatas.setStatus(ReturnDatas.ERROR);
			returnDatas.setMessage("请选择用户");
			return returnDatas;
		}
		//根据userId 查询员工统筹标准
		Finder f = new Finder();
		f.append("select * from "+Finder.getTableName(TongchouInfo.class)+" where state =:state and userId=:userId")
			.setParam("userId", userId)
			.setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
		List<TongchouInfo> tcInfoList = super.queryForList(f, TongchouInfo.class);
		if(CollectionUtils.isEmpty(tcInfoList)){
			returnDatas.setStatus(ReturnDatas.ERROR);
			returnDatas.setMessage("该用户已经停保");
			return returnDatas;
		}
		//循环遍历赋值 保存
		for(TongchouInfo info:tcInfoList){
			TongchouRecord infoReport = new TongchouRecord();
			infoReport.setCompany(info.getCompany());
			infoReport.setUserId(info.getUserId());
			infoReport.setStopProtectMonth(info.getStopProtectMonth());
			infoReport.setInsuranceorgongjijinAccount(info.getInsuranceorgongjijinAccount());
			infoReport.setInsurgongjijinType(info.getInsurgongjijinType());
			infoReport.setInsurancePaymentDate(info.getInsurancePaymentDate());// 当前查询月
			infoReport.setEfficientDate(info.getEfficientDate());
			infoReport.setRadices(info.getRadices());
			infoReport.setInsuranceCompany(info.getInsuranceCompany());
			infoReport.setInsurancePersonal(info.getInsurancePersonal());
			infoReport.setInsuranceType(info.getInsuranceType());
			infoReport.setRemark(info.getRemark());
			infoReport.setState(info.getState());
			infoReport.setCreator(SessionUser.getUserId());
			infoReport.setCreateTime(new Date());
			infoReport.setCardId(info.getCardId());
			infoReport.setUserName(info.getUserName());
			infoReport.setMonth(month);
			infoReport.setSalayid(salayId);
			infoReport.setTcjiaonadi(info.getTcjiaonadi());
			list.add(infoReport);
		}
		super.save(list);
		//取值返回
		List<TongchouRecord> listrecord = finderRecordForList(userId,month);
		returnDatas.setData(listrecord);
		return returnDatas;
	}

	private List<DkdjRecordDto> convertTcDkdjShowInfo(List<TongchouShow> listShow,
			List<TongchouRecord> datas) throws Exception {

		if(CollectionUtils.isEmpty(listShow)||CollectionUtils.isEmpty(datas)){
			return null;
		}
		List<DkdjRecordDto> list = new ArrayList<DkdjRecordDto>();
		Map<String, DkdjRecordDto> map = new LinkedHashMap<String, DkdjRecordDto>();
		for(TongchouRecord record : datas){
			String userId = record.getUserId();
			DkdjRecordDto dto = map.get(userId);
			if(dto==null){
				dto = new DkdjRecordDto();
				dto.setShowList(beanValuesCopy(listShow));//显示的信息
				dto.setUsername(record.getUserName());//用户名
				dto.setUserId(record.getUserId());
				dto.setMonth(record.getMonth());
				map.put(userId, dto);
				list.add(dto);
			}
			//统筹信息设置
			List<TongchouShow> tempShows = dto.getShowList();
			for(TongchouShow temp : tempShows){
				if(temp.getDicttypeId().equals(record.getInsuranceType())){
					temp.setPropertyValue(record.getInsurancePersonal().toString());
				}
				
			}
			
			//合计
			String totalcount="0";
			for(TongchouShow temp : tempShows){
				String propertyValue = temp.getPropertyValue();
				if(StringUtils.isNotEmpty(propertyValue)){
					 totalcount = MathUtils.addScale(totalcount, propertyValue, 2);
				}
			}
			dto.setTotalcount(totalcount);
		}
		
		return list;
	
	}

	@Override
	public List<DkdjRecordDto> finderTcDkdjList(TongchouRecord tongchouRecord, Page page) throws Exception {

		//首先查找用userId 主要是分页数据显示正确。
				Finder f = new Finder();
				f.append(" select DISTINCT userId from "+Finder.getTableName(TongchouRecord.class)+" where  state=:state and insurgongjijinType=:insurgongjijinType ");
				f.setParam("insurgongjijinType", ErpStateEnum.tcAccountTypeEnum.社保.getValue());
				f.setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
				if(StringUtils.isNotEmpty(tongchouRecord.getUserName())){
					f.append(" and userName =:userName");
				}
				if(StringUtils.isNotBlank(tongchouRecord.getMonthStr())){
					f.append(" and month=:month");
				}
				f.setParam("company", tongchouRecord.getCompany());
				f.setParam("month", DateUtils.convertString2Date("yyyy-MM-dd",tongchouRecord.getMonthStr()+"-01"));
				f.setParam("userName", tongchouRecord.getUserName());
				List<String> listUserIdStr = super.queryForList(f, String.class, page);
				if(CollectionUtils.isEmpty(listUserIdStr)){
					return null;
				}
				//根据查询的userid查找统筹信息 
				Finder finder = new  Finder();
				finder.append(" select * from "+Finder.getTableName(TongchouRecord.class)+" where  state=:state and insurgongjijinType=:insurgongjijinType ").setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
				finder.append(" and userId in(:userId)");
				finder.setParam("insurgongjijinType", ErpStateEnum.tcAccountTypeEnum.社保.getValue());
				if(StringUtils.isNotEmpty(tongchouRecord.getCompany())){
					finder.append(" and userName =:userName");
				}
				if(StringUtils.isNotBlank(tongchouRecord.getMonthStr())){
					finder.append(" and month=:month");
				}
				finder.setParam("company", tongchouRecord.getCompany());
				finder.setParam("month", DateUtils.convertString2Date("yyyy-MM-dd",tongchouRecord.getMonthStr()+"-01"));
				finder.setParam("userName", tongchouRecord.getUserName());
				finder.setParam("userId", listUserIdStr);
				
				List<TongchouRecord> list =  super.queryForList(finder, TongchouRecord.class);
				return convertTcDkdjShowInfo(tongchouRecord.getListShow(), list);
	
	}
}
