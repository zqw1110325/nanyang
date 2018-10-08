package org.springrain.erp.tc.service.impl;

import java.io.File;
import java.math.BigDecimal;
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
import org.springrain.erp.hr.entity.UserInfo;
import org.springrain.erp.hr.service.IUserInfoService;
import org.springrain.erp.tc.dto.TongchouInfoDto;
import org.springrain.erp.tc.entity.TongchouInfo;
import org.springrain.erp.tc.entity.TongchouShow;
import org.springrain.erp.tc.service.ITongchouInfoService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.DateUtils;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.DicData;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IDicDataService;



/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-03 17:40:07
 * @see org.springrain.erp.tc.service.impl.TongchouInfo
 */
@Service("ITongchouInfoService")
public class TongchouInfoServiceImpl extends BaseSpringrainServiceImpl implements ITongchouInfoService {

	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IDicDataService dicDataService;
	
    @Override
	public String  save(Object entity ) throws Exception{
	      TongchouInfo tongchouInfo=(TongchouInfo) entity;
	       return super.save(tongchouInfo).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      TongchouInfo tongchouInfo=(TongchouInfo) entity;
		 return super.saveorupdate(tongchouInfo).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 TongchouInfo tongchouInfo=(TongchouInfo) entity;
	return super.update(tongchouInfo);
    }
    @Override
	public TongchouInfo findTongchouInfoById(Object id) throws Exception{
	 return super.findById(id,TongchouInfo.class);
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
        	return (List<T>) finderListInfo((TongchouInfo) o, page);
			 
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
	public void saveOrupdateListInfo(List<TongchouInfo> list,String userId) throws Exception {
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		if(!delete(userId))
			return;
		super.save(list);
	}

	@Override
	public List<TongchouInfo> finderInfoByUserId(String userId)
			throws Exception {
		if(StringUtils.isEmpty(userId)){
			return null;
		}
		Finder f = new Finder();
		f.append("select * from "+Finder.getTableName(TongchouInfo.class)+" where state =:state and userId=:userId")
			.setParam("userId", userId)
			.setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
		return super.queryForList(f, TongchouInfo.class);
	}

	@Override
	public List<TongchouInfoDto> finderListInfo(TongchouInfo info, Page page)
			throws Exception {
		List<TongchouInfo> list = new ArrayList<TongchouInfo>();
		//首先查找用userId 主要是分页数据显示正确。
		Finder f = new Finder();
//		f.append(" select DISTINCT userId from "+Finder.getTableName(TongchouInfo.class)+" where  state=:state ");
		f.append(" select DISTINCT userId from "+Finder.getTableName(TongchouInfo.class)+" where  1=1 ");
//		f.setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
		if(StringUtils.isNotEmpty(info.getUserName())){
			f.append(" and userName =:userName");
		}
		if(StringUtils.isNotEmpty(info.getCompany())){
			f.append(" and company=:company");
		}
		if(StringUtils.isNoneEmpty(info.getState())){
			f.append(" and state=:state");
		}
		if (StringUtils.isNoneEmpty(info.getStartDate())){
			f.append(" and createTime >=:startDate").setParam("startDate",
					info.getStartDate());
		}
		if (StringUtils.isNoneEmpty(info.getEndDate())){
			f.append(" and createTime<=:endDate").setParam("endDate",
					info.getEndDate());
		}
		f.setParam("company", info.getCompany());
		f.setParam("state", info.getState());
		f.setParam("userName", info.getUserName());
		List<String> listUserIdStr = super.queryForList(f, String.class, page);
		if(CollectionUtils.isEmpty(listUserIdStr)){
			return null;
		}
		//根据查询的userid查找统筹信息 
		Finder finder = new  Finder();
//		finder.append(" select * from "+Finder.getTableName(TongchouInfo.class)+" where  state=:state ").setParam("state", ErpStateEnum.tcstateEnum.是.getValue());
		finder.append(" select * from "+Finder.getTableName(TongchouInfo.class)+" where 1=1 ");
		finder.append(" and userId in(:userId)");
		if(StringUtils.isNotEmpty(info.getUserName())){
			finder.append(" and userName =:userName");
		}
		if(StringUtils.isNotEmpty(info.getCompany())){
			finder.append(" and company=:company");
		}
		if(StringUtils.isNoneEmpty(info.getState())){
			finder.append(" and state=:state");
		}
		if (StringUtils.isNoneEmpty(info.getStartDate())){
			finder.append(" and createTime >=:startDate").setParam("startDate",
					info.getStartDate());
		}
		if (StringUtils.isNoneEmpty(info.getEndDate())){
			finder.append(" and createTime<=:endDate").setParam("endDate",
					info.getEndDate());
		}
		finder.setParam("company", info.getCompany());
		finder.setParam("state", info.getState());
		finder.setParam("userName", info.getUserName());
		finder.setParam("userId", listUserIdStr);
		list=super.queryForList(finder, TongchouInfo.class);
		
		return convertShowInfo(info.getListShow(), list);
	
	}
	/**
	 * 转换为页面显示的信息
	 * */
	private List<TongchouInfoDto> convertShowInfo(
			List<TongchouShow> showTitles, List<TongchouInfo> statinfos)
			throws Exception {

		List<TongchouInfoDto> list = new ArrayList<TongchouInfoDto>();
		if(CollectionUtils.isEmpty(showTitles)||CollectionUtils.isEmpty(statinfos)){
			return list;
		}
			Map<String, TongchouInfoDto> map = new LinkedHashMap<String, TongchouInfoDto>();
			for(TongchouInfo info : statinfos){
				String userId = info.getUserId();
				TongchouInfoDto dto = map.get(userId);
				if(dto==null){
					dto = new TongchouInfoDto();
					dto.setShowList(beanValuesCopy(showTitles));//显示的信息
					dto.setUsername(info.getUserName());//用户名
					dto.setUserId(info.getUserId());
					dto.setState(info.getState());
					dto.setStopProtectMonth(info.getStopProtectMonth());//停保月份
					UserInfo userInfo = userInfoService.findUserInfoByUserId(info.getUserId());
					dto.setJobNumber(userInfo.getWorkno());//工号
					dto.setCardId(userInfo.getIdCard());//用户的身份证号
					dto.setCreatDateStr(DateUtils.convertDate2String(DateUtils.DATETIME_FORMAT, info.getCreateTime()));
					DicData dicData1 = dicDataService.findDicDataById(userInfo.getGangwei());
					DicData dicData2 = dicDataService.findDicDataById(info.getCompany());
					DicData dicData3 = dicDataService.findDicDataById(info.getTcjiaonadi());
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
					if(!temp.getDicttypeId().equals(info.getInsuranceType())){
						continue;	
					}
					String property = temp.getProperty();
					if("insuranceAccount".equals(property))
						temp.setPropertyValue(info.getInsuranceorgongjijinAccount());
					else if("insurancePersonal".equals(property))
						temp.setPropertyValue((info.getInsurancePersonal()==null)?BigDecimal.ZERO.toString():info.getInsurancePersonal().toString());
					else if("insuranceCompany".equals(property))
						temp.setPropertyValue((info.getInsuranceCompany()==null)?BigDecimal.ZERO.toString():info.getInsuranceCompany().toString());
					else if("insurancePaymentDate".equals(property))
						temp.setPropertyValue(DateUtils.convertDate2String("yyyy-MM", info.getInsurancePaymentDate()));
					else if("efficientDate".equals(property))
						if( info.getEfficientDate()!=null){
							temp.setPropertyValue(DateUtils.convertDate2String("yyyy-MM", info.getEfficientDate()));
						}
					else if("radices".equals(property)){
						temp.setPropertyValue(info.getRadices().toString());
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
//		Collections.reverse(list); // 倒序排列 
		return list;
	}

	@Override
	public boolean delete(String userId) throws Exception {
		if(StringUtils.isBlank(userId))
			return false;
		Finder finder = new Finder();
		finder.append(" delete from "+Finder.getTableName(TongchouInfo.class)+" where  userId =:userId");
		finder.setParam("userId", userId);
		super.update(finder);
		return true;
	}

	@Override
	public void saveOrUpdateTcTingbao(TongchouInfo info,String state) throws Exception {
		String userId = info.getUserId();
		if(StringUtils.isEmpty(userId)){
			return ;
		}
		Finder f = new Finder();
		f.append("select * from "+Finder.getTableName(TongchouInfo.class)+" where  userId=:userId")
			.setParam("userId", userId);
		List<TongchouInfo> list = super.queryForList(f, TongchouInfo.class);
		if(CollectionUtils.isEmpty(list)){
			return;
		}
		for(TongchouInfo tongchouInfo:list){
			if(ErpStateEnum.tcstateEnum.否.getValue().equals(state)){
				tongchouInfo.setStopProtectMonth(info.getStopProtectMonth());
			}else{
				tongchouInfo.setStopProtectMonth(null);
			}
			tongchouInfo.setState(state);
			tongchouInfo.setOperationUser(SessionUser.getUserId());
			tongchouInfo.setOperationDate(new Date());
		}
		super.update(list);
	}

	@Override
	public TongchouInfo validateMsg(String userName, String tcType)
			throws Exception {
		if(StringUtils.isEmpty(tcType)||StringUtils.isEmpty(userName)){
			return null;
		}
		Finder f = new Finder();
		f.append(" select * from "+Finder.getTableName(TongchouInfo.class)+"  where userName =:userName and insuranceType=:insuranceType");
		f.setParam("userName", userName).setParam("insuranceType", tcType);
		return super.queryForObject(f, TongchouInfo.class);
	}

	@Override
	public void saveOrUpdate(List<TongchouInfo> list) throws Exception {
		if(CollectionUtils.isEmpty(list)){
			return ;
		}
		List<TongchouInfo> lsSave = new ArrayList<TongchouInfo>();
		List<TongchouInfo> lsUpdate = new ArrayList<TongchouInfo>();
		for(TongchouInfo info:list){
			if(StringUtils.isNotEmpty(info.getId())){
				lsUpdate.add(info);
			}else{
				lsSave.add(info);
			}
		}
		super.save(lsSave);
		super.update(lsUpdate);
	}

}
