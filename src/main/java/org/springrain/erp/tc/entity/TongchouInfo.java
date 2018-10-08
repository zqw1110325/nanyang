package org.springrain.erp.tc.entity;

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
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2017-07-03 17:40:07
 * @see org.springrain.erp.tc.entity.TongchouInfo
 */
@Table(name="z_tongchou_info")
public class TongchouInfo  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "TongchouInfo";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "userId";
	public static final String ALIAS_COMPANY = "公司编号";
	public static final String ALIAS_STOPPROTECTMONTH = "停保月份";
	public static final String ALIAS_INSURANCEORGONGJIJINACCOUNT = "保险公积金账号";
	public static final String ALIAS_INSURGONGJIJINTYPE = "类型";
	public static final String ALIAS_INSURANCEPAYMENTDATE = "缴费开始时间";
	public static final String ALIAS_EFFICIENTDATE = "缴费结束时间";
	public static final String ALIAS_RADICES = "基数";
	public static final String ALIAS_INSURANCECOMPANY = "公司缴纳部分";
	public static final String ALIAS_INSURANCEPERSONAL = "个人缴纳部分";
	public static final String ALIAS_INSURANCETYPE = "统筹类型";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_CREATOR = "creator";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_STATE = "状态 0 否 1 是";
	public static final String ALIAS_CARDID = "身份证";
	public static final String ALIAS_USERNAME = "userName";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
	public static final String ALIAS_BAK4 = "bak4";
    */
	//date formats
	//public static final String FORMAT_STOPPROTECTMONTH = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_INSURANCEPAYMENTDATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_EFFICIENTDATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * userId
	 */
	private java.lang.String userId;
	/**
	 * 公司编号
	 */
	private java.lang.String company;
	/**
	 * 停保月份
	 */
	private java.util.Date stopProtectMonth;
	/**
	 * 保险公积金账号
	 */
	private java.lang.String insuranceorgongjijinAccount;
	/**
	 * 类型
	 */
	private java.lang.String insurgongjijinType;
	/**
	 * 缴费开始时间
	 */
	private java.util.Date insurancePaymentDate;
	/**
	 * 缴费结束时间
	 */
	private java.util.Date efficientDate;
	/**
	 * 基数
	 */
	private java.math.BigDecimal radices;
	/**
	 * 公司缴纳部分
	 */
	private java.math.BigDecimal insuranceCompany;
	/**
	 * 个人缴纳部分
	 */
	private java.math.BigDecimal insurancePersonal;
	/**
	 * 统筹类型
	 */
	private java.lang.String insuranceType;
	/**
	 * remark
	 */
	private java.lang.String remark;
	/**
	 * creator
	 */
	private java.lang.String creator;
	/**
	 * createTime
	 */
	private java.util.Date createTime;
	/**
	 * 状态 0 否 1 是
	 */
	private java.lang.String state;
	/**
	 * 身份证
	 */
	private java.lang.String cardId;
	/**
	 * userName
	 */
	private java.lang.String userName;
	/**
	 * bak1
	 */
	private java.lang.String bak1;
	/**
	 * bak2
	 */
	private java.lang.String bak2;
	/**
	 * bak3
	 */
	private java.lang.String bak3;
	/**
	 * bak4
	 */
	private java.lang.String bak4;
	private String operationUser;
	private Date operationDate;
	private String tcjiaonadi;
	private String department;
	//columns END 数据库字段结束
	private  String endDate;
	private String startDate;
	private String insuranceTypeName;
	private List<TongchouShow> listShow;
	//concstructor

	public TongchouInfo(){
	}

	public TongchouInfo(
		java.lang.String id
	){
		this.id = id;
	}

	//get and set
		/**
		 * id
		 */
	public void setId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.id = value;
	}
	
	
	
	/**
	 * id
	 */
	@Id
     @WhereSQL(sql="id=:TongchouInfo_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * userId
		 */
	public void setUserId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userId = value;
	}
	
		public String getOperationUser() {
			return operationUser;
		}

		public void setOperationUser(String operationUser) {
			this.operationUser = operationUser;
		}
		
		public Date getOperationDate() {
			return operationDate;
		}

		public void setOperationDate(Date operationDate) {
			this.operationDate = operationDate;
		}

		@Transient
		public String getEndDate() {
			return endDate;
		}

		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}
		@Transient
		public String getStartDate() {
			return startDate;
		}

		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}

	/**
	 * userId
	 */
     @WhereSQL(sql="userId=:TongchouInfo_userId")
	public java.lang.String getUserId() {
		return this.userId;
	}
		/**
		 * 公司编号
		 */
	public void setCompany(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.company = value;
	}
	
	
	
	/**	
	 * 公司编号
	 */
     @WhereSQL(sql="company=:TongchouInfo_company")
	public java.lang.String getCompany() {
		return this.company;
	}
		/*
	public String getstopProtectMonthString() {
		return DateUtils.convertDate2String(FORMAT_STOPPROTECTMONTH, getstopProtectMonth());
	}
	public void setstopProtectMonthString(String value) throws ParseException{
		setstopProtectMonth(DateUtils.convertString2Date(FORMAT_STOPPROTECTMONTH,value));
	}*/
	
		/**
		 * 停保月份
		 */
	public void setStopProtectMonth(java.util.Date value) {
		this.stopProtectMonth = value;
	}
	
	
	
	/**
	 * 停保月份
	 */
     @WhereSQL(sql="stopProtectMonth=:TongchouInfo_stopProtectMonth")
	public java.util.Date getStopProtectMonth() {
		return this.stopProtectMonth;
	}
		/**
		 * 保险公积金账号
		 */
	public void setInsuranceorgongjijinAccount(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.insuranceorgongjijinAccount = value;
	}
	
	
	
	/**
	 * 保险公积金账号
	 */
     @WhereSQL(sql="insuranceorgongjijinAccount=:TongchouInfo_insuranceorgongjijinAccount")
	public java.lang.String getInsuranceorgongjijinAccount() {
		return this.insuranceorgongjijinAccount;
	}
		/**
		 * 类型
		 */
	public void setInsurgongjijinType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.insurgongjijinType = value;
	}
	
	
	
	/**
	 * 类型
	 */
     @WhereSQL(sql="insurgongjijinType=:TongchouInfo_insurgongjijinType")
	public java.lang.String getInsurgongjijinType() {
		return this.insurgongjijinType;
	}
		/*
	public String getinsurancePaymentDateString() {
		return DateUtils.convertDate2String(FORMAT_INSURANCEPAYMENTDATE, getinsurancePaymentDate());
	}
	public void setinsurancePaymentDateString(String value) throws ParseException{
		setinsurancePaymentDate(DateUtils.convertString2Date(FORMAT_INSURANCEPAYMENTDATE,value));
	}*/
	
		/**
		 * 缴费开始时间
		 */
	public void setInsurancePaymentDate(java.util.Date value) {
		this.insurancePaymentDate = value;
	}
	
	
	
	/**
	 * 缴费开始时间
	 */
     @WhereSQL(sql="insurancePaymentDate=:TongchouInfo_insurancePaymentDate")
	public java.util.Date getInsurancePaymentDate() {
		return this.insurancePaymentDate;
	}
		/*
	public String getefficientDateString() {
		return DateUtils.convertDate2String(FORMAT_EFFICIENTDATE, getefficientDate());
	}
	public void setefficientDateString(String value) throws ParseException{
		setefficientDate(DateUtils.convertString2Date(FORMAT_EFFICIENTDATE,value));
	}*/
	
		/**
		 * 缴费结束时间
		 */
	public void setEfficientDate(java.util.Date value) {
		this.efficientDate = value;
	}
	
	
	
	/**
	 * 缴费结束时间
	 */
     @WhereSQL(sql="efficientDate=:TongchouInfo_efficientDate")
	public java.util.Date getEfficientDate() {
		return this.efficientDate;
	}
		/**
		 * 基数
		 */
	public void setRadices(java.math.BigDecimal value) {
		this.radices = value;
	}
	
	
	
	/**
	 * 基数
	 */
     @WhereSQL(sql="radices=:TongchouInfo_radices")
	public java.math.BigDecimal getRadices() {
		return this.radices;
	}
		/**
		 * 公司缴纳部分
		 */
	public void setInsuranceCompany(java.math.BigDecimal value) {
		this.insuranceCompany = value;
	}
	
	
	
	/**
	 * 公司缴纳部分
	 */
     @WhereSQL(sql="insuranceCompany=:TongchouInfo_insuranceCompany")
	public java.math.BigDecimal getInsuranceCompany() {
		return this.insuranceCompany;
	}
		/**
		 * 个人缴纳部分
		 */
	public void setInsurancePersonal(java.math.BigDecimal value) {
		this.insurancePersonal = value;
	}
	
	
	
	/**
	 * 个人缴纳部分
	 */
     @WhereSQL(sql="insurancePersonal=:TongchouInfo_insurancePersonal")
	public java.math.BigDecimal getInsurancePersonal() {
		return this.insurancePersonal;
	}
		/**
		 * 统筹类型
		 */
	public void setInsuranceType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.insuranceType = value;
	}
	
	
	
	/**
	 * 统筹类型
	 */
     @WhereSQL(sql="insuranceType=:TongchouInfo_insuranceType")
	public java.lang.String getInsuranceType() {
		return this.insuranceType;
	}
		/**
		 * remark
		 */
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
	
	
	/**
	 * remark
	 */
     @WhereSQL(sql="remark=:TongchouInfo_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
		/**
		 * creator
		 */
	public void setCreator(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.creator = value;
	}
	
	
	
	/**
	 * creator
	 */
     @WhereSQL(sql="creator=:TongchouInfo_creator")
	public java.lang.String getCreator() {
		return this.creator;
	}
		/*
	public String getcreateTimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreateTime());
	}
	public void setcreateTimeString(String value) throws ParseException{
		setcreateTime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
		/**
		 * createTime
		 */
	public void setCreateTime(java.util.Date value) {
		this.createTime = value;
	}
	
	
	
	/**
	 * createTime
	 */
     @WhereSQL(sql="createTime=:TongchouInfo_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		/**
		 * 状态 0 否 1 是
		 */
	public void setState(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.state = value;
	}
	
	
	
	/**
	 * 状态 0 否 1 是
	 */
     @WhereSQL(sql="state=:TongchouInfo_state")
	public java.lang.String getState() {
		return this.state;
	}
		/**
		 * 身份证
		 */
	public void setCardId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.cardId = value;
	}
	
	
	
	/**
	 * 身份证
	 */
     @WhereSQL(sql="cardId=:TongchouInfo_cardId")
	public java.lang.String getCardId() {
		return this.cardId;
	}
		/**
		 * userName
		 */
	public void setUserName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userName = value;
	}
	
	
	
	/**
	 * userName
	 */
     @WhereSQL(sql="userName=:TongchouInfo_userName")
	public java.lang.String getUserName() {
		return this.userName;
	}
		/**
		 * bak1
		 */
	public void setBak1(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak1 = value;
	}
	
	
	
	/**
	 * bak1
	 */
     @WhereSQL(sql="bak1=:TongchouInfo_bak1")
	public java.lang.String getBak1() {
		return this.bak1;
	}
		/**
		 * bak2
		 */
	public void setBak2(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak2 = value;
	}
	
	
	
	/**
	 * bak2
	 */
     @WhereSQL(sql="bak2=:TongchouInfo_bak2")
	public java.lang.String getBak2() {
		return this.bak2;
	}
		/**
		 * bak3
		 */
	public void setBak3(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak3 = value;
	}
	
	
	
	/**
	 * bak3
	 */
     @WhereSQL(sql="bak3=:TongchouInfo_bak3")
	public java.lang.String getBak3() {
		return this.bak3;
	}
		/**
		 * bak4
		 */
	public void setBak4(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.bak4 = value;
	}
	
	
	
	/**
	 * bak4
	 */
     @WhereSQL(sql="bak4=:TongchouInfo_bak4")
	public java.lang.String getBak4() {
		return this.bak4;
	}
     
	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getTcjiaonadi() {
		return tcjiaonadi;
	}

	public void setTcjiaonadi(String tcjiaonadi) {
		this.tcjiaonadi = tcjiaonadi;
	}
	@Transient
	public List<TongchouShow> getListShow() {
		return listShow;
	}

	public void setListShow(List<TongchouShow> listShow) {
		this.listShow = listShow;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("userId[").append(getUserId()).append("],")
			.append("公司编号[").append(getCompany()).append("],")
			.append("停保月份[").append(getStopProtectMonth()).append("],")
			.append("保险公积金账号[").append(getInsuranceorgongjijinAccount()).append("],")
			.append("类型[").append(getInsurgongjijinType()).append("],")
			.append("缴费开始时间[").append(getInsurancePaymentDate()).append("],")
			.append("缴费结束时间[").append(getEfficientDate()).append("],")
			.append("基数[").append(getRadices()).append("],")
			.append("公司缴纳部分[").append(getInsuranceCompany()).append("],")
			.append("个人缴纳部分[").append(getInsurancePersonal()).append("],")
			.append("统筹类型[").append(getInsuranceType()).append("],")
			.append("remark[").append(getRemark()).append("],")
			.append("creator[").append(getCreator()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append("状态 0 否 1 是[").append(getState()).append("],")
			.append("身份证[").append(getCardId()).append("],")
			.append("userName[").append(getUserName()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
			.append("bak4[").append(getBak4()).append("],")
			.toString();
	}
	@Override
	public int hashCode() {
		return new HashCodeBuilder()
			.append(getId())
			.toHashCode();
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof TongchouInfo == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		TongchouInfo other = (TongchouInfo)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Transient
	public String getInsuranceTypeName() {
		return insuranceTypeName;
	}

	public void setInsuranceTypeName(String insuranceTypeName) {
		this.insuranceTypeName = insuranceTypeName;
	}
	
}

	
