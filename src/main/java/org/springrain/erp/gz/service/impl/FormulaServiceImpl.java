package org.springrain.erp.gz.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springrain.erp.gz.entity.Formula;
import org.springrain.erp.gz.entity.FormulaData;
import org.springrain.erp.gz.service.IFormulaService;
import org.springrain.erp.gz.util.RoundFunction;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.GlobalStatic;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;

import com.googlecode.aviator.AviatorEvaluator;

/**
 * 公式
 * 
 * @copyright
 * @author wupeilei
 * @version 2017-07-03 18:06:17
 */
@Service("formulaService")
public class FormulaServiceImpl extends BaseSpringrainServiceImpl implements IFormulaService {
    static{
		  //添加保留小数位的函数
        AviatorEvaluator.addFunction(new RoundFunction());
    }
    
    
    String updateAllExpression(String projectName)throws Exception{
		//查询所有的变量和公式 形成id/expression的map
		Map<String,Formula> map_id_fm=new HashMap<String, Formula>();
		Map<String,String> map_id_expcode=new HashMap<String, String>();
		Map<String,String> map_id_exp_new=new HashMap<String, String>();
		List<Formula> list=findAllFormulaList(projectName);
		if(list!=null){
			for(Formula f:list){
				map_id_fm.put(f.getId(), f);
				map_id_expcode.put(f.getId(), f.getExpressionCode());
			}
		}
		//遍历map
		if(map_id_fm!=null){
			for(String id:map_id_fm.keySet()){
				Formula fm=map_id_fm.get(id);
				//将中文表达转为id代码表达
				String expressionCode = cvtChineseExpress2code(fm.getChineseExpression());
				//拆解expressionCode 返回expresstion
				String exp_new=fenjie(map_id_expcode, expressionCode);
				map_id_exp_new.put(id, exp_new);
			}
		}
		if(map_id_exp_new!=null){
			for(String id:map_id_exp_new.keySet()){
				Formula f=super.findById(id, Formula.class);
				f.setExpression(map_id_exp_new.get(id));
				super.update(f);
			}
		}
		return null;
	}
	
	
    
    String fenjie(Map<String,String> map_id_expcode,String expressionCode)throws Exception{
		Set<String> findFormulaSet = findFormulaSetName(expressionCode);
		boolean b=false;
		if(findFormulaSet!=null){
			for(String fn_id:findFormulaSet){
				String fn_ex=map_id_expcode.get(fn_id);
				if(StringUtils.isNotBlank(fn_ex)){
					 expressionCode= expressionCode.replaceAll(fn_id, "(" + fn_ex + ")");
					 b=true;
				}else{
					//如果是变量不再替换id
				}
	
			}
		}
		if(b==false){ 
			return expressionCode;
		}else{
			return fenjie(map_id_expcode, expressionCode); 
		}
	}
    

    
   
    
	@Override
	public String saveFormula(Formula entity) throws Exception {
		entity.setId(entity.getName());
		String id = (String) super.save(entity);
		// 更新公式
		updateExpressionByProjectName(entity.getProjectName(),entity.getId());
		return id;
	}
	
	
	@Override
	public Integer updateFormula(Formula entity) throws Exception {
		Integer update = super.update(entity, true); 
		// 更新公式
		//updateExpressionByProjectName(entity.getProjectName(),null);
		updateAllExpression(entity.getProjectName());
		return update;
	}

	private String updateExpressionByProjectName(String projectName,String fid) throws Exception {
		/***查询所有的公式***/
		Finder selectFinder = new Finder("SELECT id,chineseExpression,expressionCode,projectName FROM "
				+ Finder.getTableName(Formula.class) + " WHERE sysExpression>0  ");
		if(StringUtils.isNotBlank(fid)){
			selectFinder.append(" and id=:fid ").setParam("fid", fid);
		}
		if (StringUtils.isNotBlank(projectName)) {
			selectFinder.append(" and projectName=:projectName ").setParam("projectName", projectName);
		}
		selectFinder.append(" order by id  ");
		List<Formula> list = super.queryForList(selectFinder, Formula.class);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		/****/

		Finder update_finder = new Finder("UPDATE " + Finder.getTableName(Formula.class)
				+ " SET expression=:expression,expressionCode=:expressionCode WHERE id=:id  ");
		for (Formula f : list) {
			// String expression = getFormulaExpressionByChineseExpression(
			// f.getChineseExpression(), f.getProjectName());
			/****将中文表达转为id代码表达***/
			String expressionCode = cvtChineseExpress2code(f.getChineseExpression());
			/***解析id表达式    如果表达式中还有公式则递归查询直到变量**/
			String expression = getFormulaExpressionByChineseExpression(expressionCode, f.getProjectName());
			if (StringUtils.isBlank(expression)) {
				continue;
			}
			update_finder.setParam("id", f.getId()).setParam("expression", expression).setParam("expressionCode", expressionCode);
//			System.out.println("id:"+f.getId()+"  express:"+expression);
			super.update(update_finder);
		}
		return null;
	}

	@Override
	public String saveorupdateFormula(Formula entity) throws Exception {
		return (String) super.saveorupdate(entity);
	}



	@Override
	public Formula findFormulaById(Object id) throws Exception {
		return super.findById(id, Formula.class);
	}

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * 
	 * @param finder
	 * @param page
	 * @param clazz
	 * @param queryBean
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz, Object queryBean)
			throws Exception {
		return super.findListDataByFinder(finder, page, clazz, queryBean);
	}

	/**
	 * 根据查询列表的宏,导出Excel
	 * 
	 * @param finder
	 *            为空则只查询 clazz表
	 * @param ftlurl
	 *            类表的模版宏
	 * @param page
	 *            分页对象
	 * @param clazz
	 *            要查询的对象
	 * @param baseService
	 *            service 调用
	 * @param queryBean
	 *            querybean
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> File findDataExportExcel(Finder finder, String ftlurl, Page page, Class<T> clazz, Object queryBean)
			throws Exception {
		return super.findDataExportExcel(finder, ftlurl, page, clazz, queryBean);
	}

	@Override
	public Map<String, BigDecimal> getRandomSysFormulaMap() throws Exception {
		Finder finder = getSysFormulaFinder();
		List<String> list = super.queryForList(finder, String.class);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		for (String s : list) {
			Random r = new Random(new Date().getTime());
			Double nextDouble = r.nextDouble();
			BigDecimal b = BigDecimal.valueOf(nextDouble);
			map.put(s, b);
		}
		return map;
	}

	@Override
	public Map<String, BigDecimal> getInitSysFormulaMap() throws Exception {
		Finder finder = getSysFormulaFinder();
		List<String> list = super.queryForList(finder, String.class);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
		for (String s : list) {
			map.put(s, BigDecimal.ZERO);
		}
		return map;
	}

	private Finder getSysFormulaFinder() throws Exception {
		Finder finder = new Finder(
				// "SELECT name FROM "+Finder.getTableName(Formula.class)+" WHERE
				// inEffectiveDate<=:inEffectiveDate and active=0 and sysExpression=0 group by
				// name,inEffectiveDate order by inEffectiveDate desc ");
				"SELECT id FROM " + Finder.getTableName(Formula.class)
						+ " WHERE inEffectiveDate<=:inEffectiveDate and active=0 and sysExpression=0 group by name,inEffectiveDate order by inEffectiveDate desc  ");
		finder.setParam("inEffectiveDate", new Date());
		return finder;
	}

	@Override
	@Cacheable(value = GlobalStatic.cacheKey, key = "'getFormulaNameById_'+#id ")
	public String getNameById(String id) throws Exception {
		if (StringUtils.isBlank(id)) {
			return null;
		}
		Finder finder = new Finder(
				"SELECT name FROM " + Finder.getTableName(Formula.class) + " WHERE id=:id  and active=0 ");
		finder.setParam("id", id);
		return super.queryForObject(finder, String.class);
	}

@Override
public List<Formula> findUserFormulaList(String formula, String projectName) throws Exception {
		Set<String> findFormulaSet = findFormulaSetName(formula);
		if (CollectionUtils.isEmpty(findFormulaSet)) {
			return null;
		}
		Finder finder = new Finder("select * from "
		//Finder finder = new Finder("SELECT id,name,chineseExpression,expression,expressionCode from "
				+ Finder.getTableName(Formula.class) + " WHERE id in(:set) and sysExpression>0  ");
		finder.setParam("set", findFormulaSet);

		if (StringUtils.isNotBlank(projectName)) {
			finder.append(" and projectName=:projectName ").setParam("projectName", projectName);
		}
		finder.append(" order by id desc ");
		List<Formula> list = super.queryForList(finder, Formula.class);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list;
	}

@Override
public List<Formula> findAllFormulaList(String projectName) throws Exception {
		Finder finder = new Finder("select * from "+ Finder.getTableName(Formula.class) );
		finder.append(" where active=0 ");
		if (StringUtils.isNotBlank(projectName)) {
			finder.append(" and projectName=:projectName ").setParam("projectName", projectName);
		}
		finder.append(" order by id desc ");
		List<Formula> list = super.queryForList(finder, Formula.class);
		return list;
	}

	@Override
	public Set<String> findFormulaSetName(String formula) {
		if (StringUtils.isBlank(formula)) {
			return null;
		}
		String strinfo = new String(formula);
		strinfo = strinfo.replaceAll("\\+", " ");
		strinfo = strinfo.replaceAll("-", " ");
		strinfo = strinfo.replaceAll("\\*", " ");
		strinfo = strinfo.replaceAll("\\/", " ");
		strinfo = strinfo.replaceAll("\\(", " ");
		strinfo = strinfo.replaceAll("\\)", " ");
		String[] split = strinfo.split(" ");

		if (split == null || split.length < 1) {
			return null;
		}

		Set<String> set = new HashSet<String>();
		for (String s : split) {
			if (StringUtils.isNotBlank(s)) {
				set.add(s);
			}
		}

		return set;
	}

	@Override
	public String getFormulaExpressionByChineseExpression(String expressionCode, String projectName) throws Exception {
		List<Formula> list = findUserFormulaList(expressionCode, projectName);
		if (CollectionUtils.isEmpty(list)) {
			return expressionCode;
		}
		for (Formula f : list) {
			// String name = f.getName();
			String id = f.getId();
			// String expression = f.getChineseExpression();
			String expression = f.getExpressionCode();

			if (StringUtils.isNotBlank(expression)) {
				expressionCode = expressionCode.replaceAll(id, "(" + expression + ")");
			}else{
				expressionCode = expressionCode.replaceAll(id, "0");
			}
		}
		return getFormulaExpressionByChineseExpression(expressionCode, projectName);
	}
	// @Override
	// public String getFormulaExpressionByChineseExpression(
	// String chineseExpression, String projectName) throws Exception {
	// List<Formula> list = findUserFormulaList(chineseExpression, projectName);
	// if (CollectionUtils.isEmpty(list)) {
	// return chineseExpression;
	// }
	// for (Formula f : list) {
	// String name = f.getName();
	// String expression = f.getChineseExpression();
	//
	// if (StringUtils.isNotBlank(expression)) {
	// chineseExpression = chineseExpression.replaceAll(name, "("
	// + expression + ")");
	// }
	// }
	// return getFormulaExpressionByChineseExpression(chineseExpression,
	// projectName);
	// }

	@Override
	public BigDecimal getResultByMap(String id, Map<String, BigDecimal> map) throws Exception {
		if (StringUtils.isBlank(id)) {
			return null;
		}
//		ExpressionFactory factory = ExpressionFactoryImpl.getInstance();

		Finder finder = new Finder(
				"SELECT expression from " + Finder.getTableName(Formula.class) + " WHERE id=:id and active=0 ");
		finder.setParam("id", id);
		String expression = super.queryForObject(finder, String.class);
		BigDecimal b = BigDecimal.ZERO;
		if(expression!=null){
//			Expression el = factory.create(expression);
//			 b = new BigDecimal(el.evaluate(map).toString());
			
			   Map<String, Object> env = new HashMap<String, Object>();
			  if(map!=null&&map.size()>0){
				  for(String key:map.keySet()){
					  env.put(key, map.get(key));
				  }
			  }
	          Object obj=AviatorEvaluator.execute(expression,env);  
	          if(obj!=null){
	        	  b=new BigDecimal(obj.toString()); 
	          }
		}
		return b;
	}

	@Override
	public List<String> findUserFormulaIdByProjectName(String projectName) throws Exception {
		Finder finder = new Finder("SELECT id from " + Finder.getTableName(Formula.class)
				+ " WHERE inEffectiveDate<=:inEffectiveDate and active=0 and sysExpression>0 and sysExpression<100    ");
		finder.setParam("inEffectiveDate", new Date());
		if (StringUtils.isNotBlank(projectName)) {
			finder.append(" and projectName=:projectName ");
			finder.setParam("projectName", projectName);
		}
		finder.append(" order by inEffectiveDate desc ");
		return super.queryForList(finder, String.class);
	}

	@Override
	public List<String> findFormulaNameByProjectName(String projectName) throws Exception {
		Finder finder = new Finder(" SELECT name from " + Finder.getTableName(Formula.class)
				+ " WHERE inEffectiveDate<=:inEffectiveDate and active=0  ");
		finder.setParam("inEffectiveDate", new Date());
		if (StringUtils.isNotBlank(projectName)) {
			finder.append(" and projectName=:projectName ");
			finder.setParam("projectName", projectName);
		}
		finder.append(" order by inEffectiveDate desc ");
		return super.queryForList(finder, String.class);
	}

	@Override
	public Map<String, BigDecimal> getFormulaMap(String tableExt, String businessId, String projectName)
			throws Exception {
		Finder finder = new Finder("SELECT formulaId ,account from " + Finder.getTableName(FormulaData.class));
		finder.append(tableExt).append("   WHERE businessId=:businessId and projectName=:projectName  ");
		finder.setParam("businessId", businessId);
		finder.setParam("projectName", projectName);
		List<Map<String, Object>> queryForList = super.queryForList(finder);

		Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();

		if (CollectionUtils.isEmpty(queryForList)) {
			return map;
		}

		for (Map<String, Object> m : queryForList) {
			String name = m.get("formulaId").toString();
			Object account = m.get("account");
			map.put(name, new BigDecimal(account.toString()));
		}

		return map;

	}

	@Override
	public void deleteReDataByBusinessId(String businessId) throws Exception {
//		String ext = businessId.substring(0, 4);
		Finder finder = new Finder("DELETE FROM ").append(Finder.getTableName(FormulaData.class))
				.append(" WHERE businessId=:businessId");
		finder.setParam("businessId", businessId);
		super.update(finder);
	}

	@Override
	public BigDecimal getValueByIdAndFormulaId(String businessId, String formulaId, String projectName)
			throws Exception {
//		String ext = businessId.substring(0, 4);
		Finder finder = new Finder("SELECT account  FROM ").append(Finder.getTableName(FormulaData.class))
				.append(" WHERE businessId=:businessId and formulaId=:formulaId and projectName=:projectName");
		finder.setParam("businessId", businessId);
		finder.setParam("formulaId", formulaId);
		finder.setParam("projectName", projectName);
		return queryForObject(finder, BigDecimal.class);
	}



	@Override
	public String cvtChineseExpress2code(String expression) throws Exception {
		Set<String> set = findFormulaSetName(expression);
		Map<String, String> map = findMapNameId();
		if (set != null) {
			for (String name : set) {
				String vv = map.get(name);
				if(StringUtils.isBlank(vv)) {
					continue;
				}
				expression = expression.replaceAll(name, vv);
			}
		}
		return expression;
	}

	@Override
	public Map<String, String> findMapNameId() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		Finder f = Finder.getSelectFinder(Formula.class);
		f.append("  order by  LENGTH(name) desc ");
		List<Formula> list = super.queryForList(f, Formula.class);
		for (Formula la : list) {
			map.put(la.getName(), la.getId());
		}
		return map;
	}

}
