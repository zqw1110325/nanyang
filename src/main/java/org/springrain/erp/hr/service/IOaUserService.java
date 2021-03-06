package org.springrain.erp.hr.service;

import org.springrain.erp.hr.entity.OaUser;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-31 10:02:36
 * @see org.springrain.erp.gz.service.OaUser
 */
public interface IOaUserService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	OaUser findOaUserById(Object id) throws Exception;
	
	void saveOaMsg()throws Exception;
	
	void saveOaUserForsyn()throws Exception;
}
