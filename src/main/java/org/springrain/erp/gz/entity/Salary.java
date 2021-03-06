package org.springrain.erp.gz.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.springrain.frame.annotation.WhereSQL;
import org.springrain.frame.entity.BaseEntity;

/**
 * 工资表
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2017-07-03 17:28:38
 * @see org.springrain.erp.gz.entity.Salary
 */
@Table(name = "z_salary")
public class Salary extends BaseEntity {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 用户Id
	 */
	private java.lang.String userId;
	/**
	 * 部门Id
	 */
	private java.lang.String unitId;
	/**
	 * 归属月份
	 */
	private java.util.Date inDate;
	/**
	 * 开始时间
	 */
	private java.util.Date startDate;
	/**
	 * 结束时间
	 */
	private java.util.Date endDate;
	/**
	 * 基本工资
	 */
	private java.math.BigDecimal jibenpay;
	/**
	 * 考核工资
	 */
	private java.math.BigDecimal kaohepay;
	/**
	 * 岗位工资
	 */
	private java.math.BigDecimal gangweipay;
	/**
	 * 日工资
	 */
	private java.math.BigDecimal ripay;
	/**
	 * 状态 1是未生成 2生成 3已发放
	 */
	private java.lang.Integer state;
	/**
	 * 发放日期
	 */
	private java.util.Date sendDate;
	/**
	 * 发放时间,系统时间
	 */
	private java.util.Date sendTime;
	/**
	 * 发放人
	 */
	private java.lang.String sendPerson;
	/**
	 * 说明
	 */
	private java.lang.String remarker;
	/**
	 * 实付工资
	 */
	private BigDecimal shifupay;
	/**
	 * 应付工资
	 */
	private BigDecimal yingfupay;
	/**
	 * 个税金额
	 */
	private BigDecimal geshui;
	/**
	 * 统筹公积金金额
	 */
	private BigDecimal tongchoupay;
	/**
	 * 工资增减金额
	 */
	private BigDecimal gongziPlusPay;
	/**在职天数
	 */
	private Integer ondutydays;
	/**
	 * 电话费补助
	 */
	private BigDecimal mobilePay;
	
	// columns END 数据库字段结束
	/**
	 * 工资小项
	 */
	private List<Salaryinfo> listinfo;

	private String userName;
	private String unitName;
	private String gradeName;
	private String dutyName;
	// 工资状态名称
	private String stateName;
	private Date entryDate;
	private Date leaveDate;
	private String stoppay;
	private String bankAccountName;
	private String bankId;
	private String bankState;
	private String companyName;
	private String payStateStr;

	// 查询条件
	private String oper;//
	private String companyId;

	// concstructor

	public Salary() {
	}

	public Salary(java.lang.String id) {
		this.id = id;
	}

	// get and set
	/**
	 * id
	 */
	public void setId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.id = value;
	}

	/**
	 * id
	 */
	@Id
	@WhereSQL(sql = "id=:Salary_id")
	public java.lang.String getId() {
		return this.id;
	}

	/**
	 * 用户Id
	 */
	public void setUserId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.userId = value;
	}

	public BigDecimal getMobilePay() {
		return mobilePay;
	}

	public void setMobilePay(BigDecimal mobilePay) {
		this.mobilePay = mobilePay;
	}

	/**
	 * 用户Id
	 */
	@WhereSQL(sql = "userId=:Salary_userId")
	public java.lang.String getUserId() {
		return this.userId;
	}

	/**
	 * 部门Id
	 */
	public void setUnitId(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.unitId = value;
	}

	/**
	 * 部门Id
	 */
	@WhereSQL(sql = "unitId=:Salary_unitId")
	public java.lang.String getUnitId() {
		return this.unitId;
	}

	/*
	 * public String getinDateString() { return
	 * DateUtils.convertDate2String(FORMAT_INDATE, getinDate()); } public void
	 * setinDateString(String value) throws ParseException{
	 * setinDate(DateUtils.convertString2Date(FORMAT_INDATE,value)); }
	 */

	public Integer getOndutydays() {
		return ondutydays;
	}

	public void setOndutydays(Integer ondutydays) {
		this.ondutydays = ondutydays;
	}

	/**
	 * 归属月份
	 */
	public void setInDate(java.util.Date value) {
		this.inDate = value;
	}

	/**
	 * 归属月份
	 */
	@WhereSQL(sql = "inDate=:Salary_inDate")
	public java.util.Date getInDate() {
		return this.inDate;
	}

	/*
	 * public String getstartDateString() { return
	 * DateUtils.convertDate2String(FORMAT_STARTDATE, getstartDate()); } public void
	 * setstartDateString(String value) throws ParseException{
	 * setstartDate(DateUtils.convertString2Date(FORMAT_STARTDATE,value)); }
	 */

	/**
	 * 开始时间
	 */
	public void setStartDate(java.util.Date value) {
		this.startDate = value;
	}

	/**
	 * 开始时间
	 */
	@WhereSQL(sql = "startDate=:Salary_startDate")
	public java.util.Date getStartDate() {
		return this.startDate;
	}

	/*
	 * public String getendDateString() { return
	 * DateUtils.convertDate2String(FORMAT_ENDDATE, getendDate()); } public void
	 * setendDateString(String value) throws ParseException{
	 * setendDate(DateUtils.convertString2Date(FORMAT_ENDDATE,value)); }
	 */

	/**
	 * 结束时间
	 */
	public void setEndDate(java.util.Date value) {
		this.endDate = value;
	}

	/**
	 * 结束时间
	 */
	@WhereSQL(sql = "endDate=:Salary_endDate")
	public java.util.Date getEndDate() {
		return this.endDate;
	}

	/**
	 * 基本工资
	 */
	public void setJibenpay(java.math.BigDecimal value) {
		this.jibenpay = value;
	}

	/**
	 * 基本工资
	 */
	@WhereSQL(sql = "jibenpay=:Salary_jibenpay")
	public java.math.BigDecimal getJibenpay() {
		return this.jibenpay;
	}

	/**
	 * 考核工资
	 */
	public void setKaohepay(java.math.BigDecimal value) {
		this.kaohepay = value;
	}

	/**
	 * 考核工资
	 */
	@WhereSQL(sql = "kaohepay=:Salary_kaohepay")
	public java.math.BigDecimal getKaohepay() {
		return this.kaohepay;
	}

	/**
	 * 岗位工资
	 */
	public void setGangweipay(java.math.BigDecimal value) {
		this.gangweipay = value;
	}

	/**
	 * 岗位工资
	 */
	@WhereSQL(sql = "gangweipay=:Salary_gangweipay")
	public java.math.BigDecimal getGangweipay() {
		return this.gangweipay;
	}

	/**
	 * 日工资
	 */
	public void setRipay(java.math.BigDecimal value) {
		this.ripay = value;
	}

	/**
	 * 日工资
	 */
	@WhereSQL(sql = "ripay=:Salary_ripay")
	public java.math.BigDecimal getRipay() {
		return this.ripay;
	}

	/**
	 * 状态 1是未生成 2生成 3已发放
	 */
	public void setState(java.lang.Integer value) {
		this.state = value;
	}

	/**
	 * 状态 1是未生成 2生成 3已发放
	 */
	@WhereSQL(sql = "state=:Salary_state")
	public java.lang.Integer getState() {
		return this.state;
	}

	/*
	 * public String getsendDateString() { return
	 * DateUtils.convertDate2String(FORMAT_SENDDATE, getsendDate()); } public void
	 * setsendDateString(String value) throws ParseException{
	 * setsendDate(DateUtils.convertString2Date(FORMAT_SENDDATE,value)); }
	 */

	/**
	 * 发放日期
	 */
	public void setSendDate(java.util.Date value) {
		this.sendDate = value;
	}

	/**
	 * 发放日期
	 */
	@WhereSQL(sql = "sendDate=:Salary_sendDate")
	public java.util.Date getSendDate() {
		return this.sendDate;
	}

	/*
	 * public String getsendTimeString() { return
	 * DateUtils.convertDate2String(FORMAT_SENDTIME, getsendTime()); } public void
	 * setsendTimeString(String value) throws ParseException{
	 * setsendTime(DateUtils.convertString2Date(FORMAT_SENDTIME,value)); }
	 */

	/**
	 * 发放时间,系统时间
	 */
	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}

	/**
	 * 发放时间,系统时间
	 */
	@WhereSQL(sql = "sendTime=:Salary_sendTime")
	public java.util.Date getSendTime() {
		return this.sendTime;
	}

	/**
	 * 发放人
	 */
	public void setSendPerson(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.sendPerson = value;
	}

	/**
	 * 发放人
	 */
	@WhereSQL(sql = "sendPerson=:Salary_sendPerson")
	public java.lang.String getSendPerson() {
		return this.sendPerson;
	}

	/**
	 * 说明
	 */
	public void setRemarker(java.lang.String value) {
		if (StringUtils.isNotBlank(value)) {
			value = value.trim();
		}
		this.remarker = value;
	}

	/**
	 * 说明
	 */
	@WhereSQL(sql = "remarker=:Salary_remarker")
	public java.lang.String getRemarker() {
		return this.remarker;
	}

	@Override
	public String toString() {
		return new StringBuilder().append("id[").append(getId()).append("],").append("用户Id[").append(getUserId())
				.append("],").append("部门Id[").append(getUnitId()).append("],").append("归属月份[").append(getInDate())
				.append("],").append("开始时间[").append(getStartDate()).append("],").append("结束时间[").append(getEndDate())
				.append("],").append("基本工资[").append(getJibenpay()).append("],").append("考核工资[").append(getKaohepay())
				.append("],").append("岗位工资[").append(getGangweipay()).append("],").append("日工资[").append(getRipay())
				.append("],").append("状态 1是未生成 2生成 3已发放[").append(getState()).append("],").append("发放日期[")
				.append(getSendDate()).append("],").append("发放时间,系统时间[").append(getSendTime()).append("],")
				.append("发放人[").append(getSendPerson()).append("],").append("说明[").append(getRemarker()).append("],")
				.toString();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(getId()).toHashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Salary == false) {
			return false;
		}

		if (this == obj) {
			return true;
		}

		Salary other = (Salary) obj;
		return new EqualsBuilder().append(getId(), other.getId()).isEquals();
	}

	@Transient
	public List<Salaryinfo> getListinfo() {
		return listinfo;
	}

	public void setListinfo(List<Salaryinfo> listinfo) {
		this.listinfo = listinfo;
	}

	public BigDecimal getShifupay() {
		return shifupay;
	}

	public void setShifupay(BigDecimal shifupay) {
		this.shifupay = shifupay;
	}

	public BigDecimal getYingfupay() {
		return yingfupay;
	}

	public void setYingfupay(BigDecimal yingfupay) {
		this.yingfupay = yingfupay;
	}

	/*
	 * @Transient public BigDecimal getRfypay() { return rfypay; }
	 * 
	 * public void setRfypay(BigDecimal rfypay) { this.rfypay = rfypay; }
	 * 
	 * @Transient public BigDecimal getRfykkpay() { return rfykkpay; }
	 * 
	 * public void setRfykkpay(BigDecimal rfykkpay) { this.rfykkpay = rfykkpay; }
	 */

	@Transient
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Transient
	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	@Transient
	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	@Transient
	public String getDutyName() {
		return dutyName;
	}

	public void setDutyName(String dutyName) {
		this.dutyName = dutyName;
	}

	@Transient
	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public BigDecimal getGeshui() {
		return geshui;
	}

	public void setGeshui(BigDecimal geshui) {
		this.geshui = geshui;
	}

	@Transient
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	@Transient
	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}

	@Transient
	public String getStoppay() {
		return stoppay;
	}

	public void setStoppay(String stoppay) {
		this.stoppay = stoppay;
	}

	@Transient
	public String getOper() {
		return oper;
	}

	public void setOper(String oper) {
		this.oper = oper;
	}

	public BigDecimal getTongchoupay() {
		return tongchoupay;
	}

	public void setTongchoupay(BigDecimal tongchoupay) {
		this.tongchoupay = tongchoupay;
	}

	public BigDecimal getGongziPlusPay() {
		return gongziPlusPay;
	}

	public void setGongziPlusPay(BigDecimal gongziPlusPay) {
		this.gongziPlusPay = gongziPlusPay;
	}
	@Transient
	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Transient
	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	@Transient
	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}
	@Transient
	public String getBankState() {
		return bankState;
	}

	public void setBankState(String bankState) {
		this.bankState = bankState;
	}
	@Transient
	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Transient
	public String getPayStateStr() {
		return payStateStr;
	}

	public void setPayStateStr(String payStateStr) {
		this.payStateStr = payStateStr;
	}

}
