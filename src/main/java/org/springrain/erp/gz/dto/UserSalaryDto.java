package org.springrain.erp.gz.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

public class UserSalaryDto implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 工资增减项
	 */
	private Map<String,BigDecimal> salaryinfoMap;
	/**
	 * 统筹
	 */
	private Map<String,BigDecimal> tongchouRecordMap;
	/**
	 * 统筹增减项
	 */
	private Map<String,BigDecimal> tongchouZengjianMap;
	/**
	 * 用户名称
	 */
	private String userName;
	/**
	 * 归属月份
	 */
	private Date inDate;
	/**
	 * 基本工资
	 */
	private BigDecimal jibenpay;
	/**
	 * 考核工资
	 */
	private BigDecimal kaohepay;
	/**
	 * 岗位工资
	 */
	private BigDecimal gangweipay;
	/**
	 * 统筹公积金金额
	 */
	private BigDecimal tongchoupay;
	/**
	 * 统筹公积金增减金额
	 */
	private BigDecimal tongchouPlusPay;
	/**
	 * 工资增减金额
	 */
	private BigDecimal gongziPlusPay;
	/**
	 * 日工资
	 */
	private BigDecimal ripay;
	/**
	 * 个税
	 */
	private BigDecimal geshui;
	/**
	 * 应付工资
	 */
	private BigDecimal yingfupay;
	/**
	 * 实付工资
	 */
	private BigDecimal shifupay;
	/**
	 * 公司名称
	 */
	private String companyName;
	/**
	 * 部门名称
	 */
	private String unitName;
	/**
	 * 工资表Id
	 */
	private String salaryId;
	/**
	 * 支付状态
	 */
	private String payStateStr;
	/**
	 * 在职天数
	 */
	private Integer ondutydays;
	
	private BigDecimal mobilePay;
	
	public Map<String, BigDecimal> getSalaryinfoMap() {
		return salaryinfoMap;
	}
	public void setSalaryinfoMap(Map<String, BigDecimal> salaryinfoMap) {
		this.salaryinfoMap = salaryinfoMap;
	}
	public Map<String, BigDecimal> getTongchouRecordMap() {
		return tongchouRecordMap;
	}
	public void setTongchouRecordMap(Map<String, BigDecimal> tongchouRecordMap) {
		this.tongchouRecordMap = tongchouRecordMap;
	}
	public Map<String, BigDecimal> getTongchouZengjianMap() {
		return tongchouZengjianMap;
	}
	public void setTongchouZengjianMap(Map<String, BigDecimal> tongchouZengjianMap) {
		this.tongchouZengjianMap = tongchouZengjianMap;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getInDate() {
		return inDate;
	}
	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}
	public BigDecimal getJibenpay() {
		return jibenpay;
	}
	public void setJibenpay(BigDecimal jibenpay) {
		this.jibenpay = jibenpay;
	}
	public BigDecimal getKaohepay() {
		return kaohepay;
	}
	public void setKaohepay(BigDecimal kaohepay) {
		this.kaohepay = kaohepay;
	}
	public BigDecimal getGangweipay() {
		return gangweipay;
	}
	public void setGangweipay(BigDecimal gangweipay) {
		this.gangweipay = gangweipay;
	}
	public BigDecimal getTongchoupay() {
		return tongchoupay;
	}
	public void setTongchoupay(BigDecimal tongchoupay) {
		this.tongchoupay = tongchoupay;
	}
	public BigDecimal getTongchouPlusPay() {
		return tongchouPlusPay;
	}
	public void setTongchouPlusPay(BigDecimal tongchouPlusPay) {
		this.tongchouPlusPay = tongchouPlusPay;
	}
	public BigDecimal getGongziPlusPay() {
		return gongziPlusPay;
	}
	public void setGongziPlusPay(BigDecimal gongziPlusPay) {
		this.gongziPlusPay = gongziPlusPay;
	}
	public BigDecimal getRipay() {
		return ripay;
	}
	public void setRipay(BigDecimal ripay) {
		this.ripay = ripay;
	}
	public BigDecimal getGeshui() {
		return geshui;
	}
	public void setGeshui(BigDecimal geshui) {
		this.geshui = geshui;
	}
	public BigDecimal getYingfupay() {
		return yingfupay;
	}
	public void setYingfupay(BigDecimal yingfupay) {
		this.yingfupay = yingfupay;
	}
	public BigDecimal getShifupay() {
		return shifupay;
	}
	public void setShifupay(BigDecimal shifupay) {
		this.shifupay = shifupay;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getSalaryId() {
		return salaryId;
	}
	public void setSalaryId(String salaryId) {
		this.salaryId = salaryId;
	}
	public String getPayStateStr() {
		return payStateStr;
	}
	public void setPayStateStr(String payStateStr) {
		this.payStateStr = payStateStr;
	}
	public Integer getOndutydays() {
		return ondutydays;
	}
	public void setOndutydays(Integer ondutydays) {
		this.ondutydays = ondutydays;
	}
	public BigDecimal getMobilePay() {
		return mobilePay;
	}
	public void setMobilePay(BigDecimal mobilePay) {
		this.mobilePay = mobilePay;
	}
	
	
}
