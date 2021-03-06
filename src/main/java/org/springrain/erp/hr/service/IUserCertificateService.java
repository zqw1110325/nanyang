package org.springrain.erp.hr.service;

import java.util.List;

import org.springrain.erp.hr.entity.UserCertificate;
import org.springrain.system.service.IBaseSpringrainService;
/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-26 09:16:21
 * @see org.springrain.erp.hr.service.UserCertificate
 */
public interface IUserCertificateService extends IBaseSpringrainService {
	
	/**
	 * 根据ID查找
	 * @param id
	 * @return
	 * @throws Exception
	 */
	UserCertificate findUserCertificateById(Object id) throws Exception;
	
	
	void deleteNotInIdsAndUserId(String userId, List<String> certificatelIds) throws Exception;

	List<UserCertificate> findByUserId(String userId) throws Exception;
	
	void saveOrUpdate(List<UserCertificate> list) throws Exception;
}
