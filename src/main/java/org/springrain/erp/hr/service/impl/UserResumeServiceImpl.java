package org.springrain.erp.hr.service.impl;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.erp.constants.ErpStateEnum;
import org.springrain.erp.hr.entity.UserInfo;
import org.springrain.erp.hr.entity.UserResume;
import org.springrain.erp.hr.service.IUserResumeService;
import org.springrain.frame.common.SessionUser;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.DicData;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.User;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.IDicDataService;
import org.springrain.system.service.IOrgService;
import org.springrain.system.service.IUserOrgService;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-08-04 09:20:14
 * @see org.springrain.erp.hr.service.impl.UserResume
 */
@Service("userResumeService")
public class UserResumeServiceImpl extends BaseSpringrainServiceImpl implements IUserResumeService {
	@Resource
	IUserOrgService userOrgService;
	@Resource
	IDicDataService dicDataService;
	@Resource
	IOrgService orgService;
   
    @Override
	public String  save(Object entity ) throws Exception{
	      UserResume userResume=(UserResume) entity;
	       return super.save(userResume).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      UserResume userResume=(UserResume) entity;
		 return super.saveorupdate(userResume).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 UserResume userResume=(UserResume) entity;
	return super.update(userResume);
    }
    @Override
	public UserResume findUserResumeById(Object id) throws Exception{
	 return super.findById(id,UserResume.class);
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
			 return ((List<T>) findUserResumeByQuery((UserResume) o, page));
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
	public void saveUserOrgResume(String[] newOrgIds, String userId)
			throws Exception {
		StringBuilder sb = new StringBuilder();
		if(newOrgIds != null && newOrgIds.length > 0){
			for(String newOrgId : newOrgIds){
				sb.append(newOrgId).append(",");
			}
		}
		String newOrgIdStr = sb.toString();
		StringBuilder oldSb = new StringBuilder();
		List<Org> orgList = userOrgService.findOrgByUserId(userId);
		if(CollectionUtils.isNotEmpty(orgList)){
			//判断老的所属部门和新的是否相同，如果相同则记录不同项，如果不同则记录全部
			if(newOrgIds != null && orgList.size() == newOrgIds.length){
				for(Org o : orgList){
					if(!newOrgIdStr.contains(o.getId()+",")){
						oldSb.append(o.getId()).append(",");
					}
				}
			}else{
				for(Org o : orgList){
					oldSb.append(o.getId()).append(",");
				}
			}
		}
		if(oldSb.toString().length() > 0){
			UserResume ur = getUserResume(userId);
			ur.setOldOrg(oldSb.toString());
			ur.setNewOrg(newOrgIdStr);
			ur.setType(ErpStateEnum.resumeTypeEnum.部门.getValue());
			super.save(ur);
		}
	}

	@Override
	public void saveUserResume(UserInfo oinfo, UserInfo ninfo) throws Exception {
		if(StringUtils.isNotBlank(oinfo.getGrade()) && StringUtils.isNotBlank(ninfo.getGrade()) && !oinfo.getGrade().equals(ninfo.getGrade())){
			UserResume ur = getUserResume(oinfo.getUserId());
			ur.setOldGrade(oinfo.getGrade());
			ur.setNewGrade(ninfo.getGrade());
			ur.setType(ErpStateEnum.resumeTypeEnum.级别.getValue());
			super.save(ur);
		}
		if(StringUtils.isNotBlank(oinfo.getGangwei()) && StringUtils.isNotBlank(ninfo.getGangwei()) &&!oinfo.getGangwei().equals(ninfo.getGangwei())){
			UserResume ur = getUserResume(oinfo.getUserId());
			ur.setOldGangwei(oinfo.getGangwei());
			ur.setNewGangwei(ninfo.getGangwei());
			ur.setType(ErpStateEnum.resumeTypeEnum.岗位.getValue());
			super.save(ur);
		}
		if(oinfo.getJibenpay() != null &&  oinfo.getGangweipay() != null & oinfo.getKaohepay() != null){
			if(ninfo.getJibenpay() != null && ninfo.getGangweipay() != null & ninfo.getKaohepay() != null){
				if(!(oinfo.getJibenpay().compareTo(ninfo.getJibenpay())==0 && oinfo.getGangweipay().compareTo(ninfo.getGangweipay()) == 0 &&oinfo.getKaohepay().compareTo(ninfo.getKaohepay())==0)){
					UserResume ur = getUserResume(oinfo.getUserId());
					ur.setOldJibenpay(oinfo.getJibenpay());
					ur.setNewJibenpay(ninfo.getJibenpay());
					ur.setOldGangweipay(oinfo.getGangweipay());
					ur.setNewGangweipay(ninfo.getGangweipay());
					ur.setOldKaohepay(oinfo.getKaohepay());
					ur.setNewKaohepay(ninfo.getKaohepay());
					ur.setType(ErpStateEnum.resumeTypeEnum.薪资.getValue());
					super.save(ur);
				}
			}
		}
		
	}
	
	public UserResume getUserResume(String userId){
		UserResume ur = new UserResume();
		ur.setId(null);
		ur.setUserId(userId);
		ur.setCreateDate(new Date());
		ur.setCreatePerson(SessionUser.getUserId());
		return ur;
	}

	@Override
	public List<UserResume> findUserResumeByQuery(UserResume query, Page page)
			throws Exception {
		page.setOrder("createDate");
		page.setSort("desc");
		Finder finder = Finder.getSelectFinder(UserResume.class, "ur.* , u.name userName ");
		finder.append(" ur left join ").append(Finder.getTableName(User.class)).append(" u on ur.userId = u.id ");
		finder.append(" where 1 = 1 ");
		if(StringUtils.isNotBlank(query.getUserName())){
			finder.append(" and u.name like :userName ").setParam("userName", "%"+query.getUserName()+"%");
		}
		if(StringUtils.isNotBlank(query.getSdate())){
			finder.append(" and ur.createDate >= :sdate ").setParam("sdate", "%"+query.getSdate()+"%");
		}
		if(StringUtils.isNotBlank(query.getEdate())){
			finder.append(" and ur.createDate <= :edate ").setParam("edate", "%"+query.getEdate()+"%");
		}
		if(query.getType() != null ){
			finder.append("and ur.type = :type ").setParam("type", query.getType());
		}
		List<UserResume> datas = super.queryForList(finder, UserResume.class, page);
		if(CollectionUtils.isNotEmpty(datas)){
			for(UserResume ur : datas){
				User createPerson = super.findById(ur.getCreatePerson(), User.class);
				ur.setCreatePersonName(createPerson.getName());
				if(StringUtils.isNotBlank(ur.getOldOrg())){
					List<Org> orgList = orgService.findOrgByOrgIds(ur.getOldOrg());
					if(CollectionUtils.isNotEmpty(orgList)){
						StringBuilder sb = new StringBuilder();
						for(Org o : orgList){
							sb.append(o.getName()).append(",");
						}
						ur.setOldOrgName(sb.toString());
					}
				}
				if(StringUtils.isNotBlank(ur.getNewOrg())){
					List<Org> orgList = orgService.findOrgByOrgIds(ur.getNewOrg());
					if(CollectionUtils.isNotEmpty(orgList)){
						StringBuilder sb = new StringBuilder();
						for(Org o : orgList){
							sb.append(o.getName()).append(",");
						}
						ur.setNewOrgName(sb.toString());
					}
				}
				if(StringUtils.isNotBlank(ur.getOldGangwei())){
					DicData data = dicDataService.findDicDataById(ur.getOldGangwei());
					ur.setOldGangweiName(data.getName());
				}
				if(StringUtils.isNotBlank(ur.getNewGangwei())){
					DicData data = dicDataService.findDicDataById(ur.getNewGangwei());
					ur.setNewGangweiName(data.getName());
				}
				if(StringUtils.isNotBlank(ur.getOldGrade())){
					DicData data = dicDataService.findDicDataById(ur.getOldGrade());
						ur.setOldGradeName(data.getName());
				}
				if(StringUtils.isNotBlank(ur.getNewGrade())){
					DicData data = dicDataService.findDicDataById(ur.getNewGrade());
					ur.setNewGradeName(data.getName());
				}
				
			}
		}
		return datas;
	}

	@Override
	public List<UserResume> findUserResumeByUserId(String userId) throws Exception {
		if(StringUtils.isBlank(userId)){
			return null;
		}
		Finder finder = Finder.getSelectFinder(UserResume.class);
		finder.append(" where 1 = 1 ");
		finder.append(" and userId = :userId ").setParam("userId", userId);
		finder.append(" order by createDate desc ");
		List<UserResume> datas = super.queryForList(finder, UserResume.class);
		return datas;
	}
	
	

}
