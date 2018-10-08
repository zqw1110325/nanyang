package org.springrain.erp.tc.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.gz.util.MathUtils;
import org.springrain.erp.hr.service.IUserInfoService;
import org.springrain.erp.tc.dto.DkdjRecordDto;
import org.springrain.erp.tc.entity.TongchouRecord;
import org.springrain.erp.tc.entity.TongchouShow;
import org.springrain.erp.tc.service.DkdjRecordService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IDicDataService;



/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-03 17:42:27
 * @see org.springrain.erp.tc.service.impl.TongchouRecord
 */
@Service("dkdjRecordService")
public class DkdjRecordServiceImpl extends BaseSpringrainServiceImpl implements DkdjRecordService {

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
        	return (List<T>) finderGjjDkdjList((TongchouRecord) o, page);
        	
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
				if(!temp.getDicttypeId().equals(record.getInsuranceType())){
					continue;
				}
				String property = temp.getProperty();
				if("insurancePersonal".equals(property)){
					temp.setPropertyValue((record.getInsurancePersonal()==null)?BigDecimal.ZERO.toString():record.getInsurancePersonal().toString());
				}else if("insuranceCompany".equals(property)){
					temp.setPropertyValue((record.getInsuranceCompany()==null)?BigDecimal.ZERO.toString():record.getInsuranceCompany().toString());
				}else if("radices".equals(property)){
					temp.setPropertyValue(record.getRadices().toString());
				}
			}
			
			//合计
			String totalcount="0";
			for(TongchouShow temp : tempShows){
				String property = temp.getProperty();
				if("radices".equals(property)){
					continue;
				}
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
	public List<DkdjRecordDto> finderGjjDkdjList(TongchouRecord tongchouRecord, Page page) throws Exception {

		//首先查找用userId 主要是分页数据显示正确。
				Finder f = new Finder();
				f.append(" select DISTINCT userId from "+Finder.getTableName(TongchouRecord.class)+" where  state=:state and insurgongjijinType=:insurgongjijinType ");
				f.setParam("insurgongjijinType", ErpStateEnum.tcAccountTypeEnum.公积金.getValue());
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
				finder.append(" select * from "+Finder.getTableName(TongchouRecord.class)+" where  state=:state and insurgongjijinType=:insurgongjijinType  ").setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
				finder.append(" and userId in(:userId)");
				finder.setParam("insurgongjijinType", ErpStateEnum.tcAccountTypeEnum.公积金.getValue());
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
