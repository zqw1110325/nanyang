package org.springrain.erp.gz.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springrain.erp.gz.entity.Formula;
import org.springrain.system.service.IBaseSpringrainService;

/**
 * 公式
 * 
 */
public interface IFormulaService extends IBaseSpringrainService {
	String saveFormula(Formula entity) throws Exception;

	String saveorupdateFormula(Formula entity) throws Exception;

	Integer updateFormula(Formula entity) throws Exception;

	Formula findFormulaById(Object id) throws Exception;

	/**
	 * 获取系统变量的Map,初始化数据位随机数
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, BigDecimal> getRandomSysFormulaMap() throws Exception;

	/**
	 * 获取系统变量的Map,初始化数据为0
	 * 
	 * @return
	 * @throws Exception
	 */
	Map<String, BigDecimal> getInitSysFormulaMap() throws Exception;

	/**
	 * 根据ID查询公式名称
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	String getNameById(String id) throws Exception;

	/**
	 * 根据参数,指定ID的结果
	 * 
	 * @param id
	 * @param map
	 * @return
	 * @throws Exception
	 */
	BigDecimal getResultByMap(String id, Map<String, BigDecimal> map)
			throws Exception;

	/**
	 * 查询指定项目 自定义级别的所有公式ID
	 * 
	 * @param projectName
	 * @param sysExpression
	 * @return
	 */
	List<String> findUserFormulaIdByProjectName(String projectName)
			throws Exception;

	/**
	 * 解析公式的大项
	 * 
	 * @param formula
	 * @return
	 */
	Set<String> findFormulaSetName(String formula);

	/**
	 * 
	 * @param formula
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	String getFormulaExpressionByChineseExpression(String chineseExpression,
			String projectName) throws Exception;

	/**
	 * 查询中间表的实际值
	 * 
	 * @param tableExt
	 * @param businessId
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	Map<String, BigDecimal> getFormulaMap(String tableExt, String businessId,
			String projectName) throws Exception;

	/**
	 * 根据业务ID 删除中间数据
	 * 
	 * @param businessId
	 * @throws Exception
	 */
	void deleteReDataByBusinessId(String businessId) throws Exception;

	/**
	 * 根据ID查询一个公式的值
	 * 
	 * @param businessId
	 * @param formulaId
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	BigDecimal getValueByIdAndFormulaId(String businessId, String formulaId,
			String projectName) throws Exception;

	/**
	 * 查询项目下的所有公式项和非公式项
	 * 
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	List<String> findFormulaNameByProjectName(String projectName)
			throws Exception;
	/**
	 * 将中文表达式转为code
	 * @param expression
	 * @return
	 * @throws Exception
	 */
	 String cvtChineseExpress2code(String expression)throws Exception;
	 /**
	  * 形成map 按变量长度倒序排列
	  * @return
	  * @throws Exception
	  */
     Map<String,String> findMapNameId()throws Exception;
     /**
      * 查询所有的公式
      * @param formula
      * @param projectName
      * @return
      * @throws Exception
      */
     List<Formula> findUserFormulaList(String formula, String projectName) throws Exception ;
     /**
      * 查询所有的公式带变量
      */
     List<Formula> findAllFormulaList(String projectName) throws Exception ;
}
