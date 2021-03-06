package org.springrain.erp.hr.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springrain.erp.hr.entity.OaUserOrg;
import org.springrain.erp.hr.service.IOaUserOrgService;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.entity.Org;
import org.springrain.system.entity.User;
import org.springrain.system.entity.UserOrg;
import org.springrain.system.service.BaseSpringrainServiceImpl;


/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-31 10:02:21
 * @see org.springrain.erp.gz.service.impl.OaUserOrg
 */
@Service("oaUserOrgService")
public class OaUserOrgServiceImpl extends BaseSpringrainServiceImpl implements IOaUserOrgService {

   
    @Override
	public String  save(Object entity ) throws Exception{
	      OaUserOrg oaUserOrg=(OaUserOrg) entity;
	       return super.save(oaUserOrg).toString();
	}

    @Override
	public String  saveorupdate(Object entity ) throws Exception{
	      OaUserOrg oaUserOrg=(OaUserOrg) entity;
		 return super.saveorupdate(oaUserOrg).toString();
	}
	
	@Override
    public Integer update(IBaseEntity entity ) throws Exception{
	 OaUserOrg oaUserOrg=(OaUserOrg) entity;
	return super.update(oaUserOrg);
    }
    @Override
	public OaUserOrg findOaUserOrgById(Object id) throws Exception{
	 return super.findById(id,OaUserOrg.class);
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
        @Override
    public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz,
			Object o) throws Exception{
			 return super.findListDataByFinder(finder,page,clazz,o);
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
	public void saveOaUserOrg() throws Exception {

			Finder finder = Finder.getSelectFinder(OaUserOrg.class);
			List<OaUserOrg> list = super.queryForList(finder, OaUserOrg.class);
			if(CollectionUtils.isEmpty(list)){
				return ;
			}
			//查找user
			Finder fuser = new Finder();
			fuser.append(" select * from " + Finder.getTableName(User.class)
					+ " where oauserId =:oauserId");
			Finder forg = new Finder();
			forg.append(" select * from " + Finder.getTableName(Org.class)
					+ " where oaorgId =:oaorgId");
			List<UserOrg> listUserOrg = new ArrayList<UserOrg>();
			//判断是否存在
			Finder fuserorg = new Finder();
			fuserorg.append(" select uo.* from t_user u ,t_org o ,t_user_org uo where u.id= uo.userId and o.id = uo.orgId");
			fuserorg.append(" and u.oauserId =:oauserId and  o.oaorgId =:oaorgId");
			for(OaUserOrg oaUserOrg:list){
				String oauserId = oaUserOrg.getUserId();
				String oaorgId = oaUserOrg.getOrgId();
				fuserorg.setParam("oauserId", oauserId);
				fuserorg.setParam("oaorgId", oaorgId);
				UserOrg userOrg2 = super.queryForObject(fuserorg, UserOrg.class);
				if(userOrg2!=null){
					continue;
				}
				fuser.setParam("oauserId", oauserId);
				forg.setParam("oaorgId", oaorgId);
				
				UserOrg userOrg = new UserOrg();
				User user = super.queryForObject(fuser, User.class);
				Org org = super.queryForObject(forg, Org.class);
				if(user==null||org==null){
					continue;
				}
				userOrg.setUserId(user.getId());
				userOrg.setOrgId(org.getId());
				userOrg.setOrgName(org.getName());
				if(oaUserOrg.getManager()==1){
					userOrg.setManagerType(11);
				}else{
					userOrg.setManagerType(10);
				}
				userOrg.setHasleaf(1);
				listUserOrg.add(userOrg);
			}
			
			super.save(listUserOrg);
	}

}
