package org.springrain.erp.tc.entity;

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
 * @version  2017-07-03 17:42:56
 * @see org.springrain.erp.tc.entity.TongchouZengjian
 */
@Table(name="z_tongchou_zengjian")
public class TongchouZengjian  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "TongchouZengjian";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_USERNAME = "用户姓名";
	public static final String ALIAS_MONTH = "月份";
	public static final String ALIAS_INSURANCECOMPANY = "公司缴纳部分";
	public static final String ALIAS_INSURANCEPERSONAL = "个人缴纳部分";
	public static final String ALIAS_INSURANCETYPE = "统筹类型";
	public static final String ALIAS_FEIYONGTYPE = "费用类型";
	public static final String ALIAS_REMARK = "remark";
	public static final String ALIAS_CREATOR = "creator";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_ACTIVE = " 0 否 1 是";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
	public static final String ALIAS_BAK4 = "bak4";
    */
	//date formats
	//public static final String FORMAT_MONTH = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 用户ID
	 */
	private java.lang.String userId;
	/**
	 * 用户姓名
	 */
	private java.lang.String userName;
	/**
	 * 月份
	 */
	private java.util.Date month;
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
	 * 费用类型
	 */
	private java.lang.String feiyongtype;
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
	 *  0 否 1 是
	 */
	private java.lang.String active;
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
	/**
	 * 是否使用 0 否 1 是
	 */
	private String isused;
	/**
	 * 工资ID
	 */
	
	private String salayid;
	private String company;
	private String tcjnd;
	//columns END 数据库字段结束
	private String tcname;//统筹名称
	private String fyname;//费用名称
	private String monthStr;
	private String createName;
	//concstructor

	public TongchouZengjian(){
	}

	public TongchouZengjian(
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
     @WhereSQL(sql="id=:TongchouZengjian_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 用户ID
		 */
	public void setUserId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userId = value;
	}
	
	@Transient
	public String getMonthStr() {
			return monthStr;
		}

		public void setMonthStr(String monthStr) {
			this.monthStr = monthStr;
		}

	public String getIsused() {
			return isused;
		}

		public void setIsused(String isused) {
			this.isused = isused;
		}

		public String getSalayid() {
			return salayid;
		}

		public void setSalayid(String salayid) {
			this.salayid = salayid;
		}

	/**
	 * 用户ID
	 */
     @WhereSQL(sql="userId=:TongchouZengjian_userId")
	public java.lang.String getUserId() {
		return this.userId;
	}
		/**
		 * 用户姓名
		 */
	public void setUserName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.userName = value;
	}
	
	
	@Transient
	public String getTcname() {
			return tcname;
		}

		public void setTcname(String tcname) {
			this.tcname = tcname;
		}
		@Transient
		public String getFyname() {
			return fyname;
		}

		public void setFyname(String fyname) {
			this.fyname = fyname;
		}

	/**
	 * 用户姓名
	 */
     @WhereSQL(sql="userName=:TongchouZengjian_userName")
	public java.lang.String getUserName() {
		return this.userName;
	}
		/*
	public String getmonthString() {
		return DateUtils.convertDate2String(FORMAT_MONTH, getmonth());
	}
	public void setmonthString(String value) throws ParseException{
		setmonth(DateUtils.convertString2Date(FORMAT_MONTH,value));
	}*/
	
		/**
		 * 月份
		 */
	public void setMonth(java.util.Date value) {
		this.month = value;
	}
	
	
	
	/**
	 * 月份
	 */
     @WhereSQL(sql="month=:TongchouZengjian_month")
	public java.util.Date getMonth() {
		return this.month;
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
     @WhereSQL(sql="insuranceCompany=:TongchouZengjian_insuranceCompany")
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
     @WhereSQL(sql="insurancePersonal=:TongchouZengjian_insurancePersonal")
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
     @WhereSQL(sql="insuranceType=:TongchouZengjian_insuranceType")
	public java.lang.String getInsuranceType() {
		return this.insuranceType;
	}
		/**
		 * 费用类型
		 */
	public void setFeiyongtype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.feiyongtype = value;
	}
	
	
	
	/**
	 * 费用类型
	 */
     @WhereSQL(sql="feiyongtype=:TongchouZengjian_feiyongtype")
	public java.lang.String getFeiyongtype() {
		return this.feiyongtype;
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
     @WhereSQL(sql="remark=:TongchouZengjian_remark")
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
     @WhereSQL(sql="creator=:TongchouZengjian_creator")
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
     @WhereSQL(sql="createTime=:TongchouZengjian_createTime")
	public java.util.Date getCreateTime() {
		return this.createTime;
	}
		/**
		 *  0 否 1 是
		 */
	public void setActive(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.active = value;
	}
	
	
	
	/**
	 *  0 否 1 是
	 */
     @WhereSQL(sql="active=:TongchouZengjian_active")
	public java.lang.String getActive() {
		return this.active;
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
     @WhereSQL(sql="bak1=:TongchouZengjian_bak1")
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
     @WhereSQL(sql="bak2=:TongchouZengjian_bak2")
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
     @WhereSQL(sql="bak3=:TongchouZengjian_bak3")
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
	
	
	@Transient
	public String getCreateName() {
			return createName;
		}

		public void setCreateName(String createName) {
			this.createName = createName;
		}

	/**
	 * bak4
	 */
     @WhereSQL(sql="bak4=:TongchouZengjian_bak4")
	public java.lang.String getBak4() {
		return this.bak4;
	}
     
	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTcjnd() {
		return tcjnd;
	}

	public void setTcjnd(String tcjnd) {
		this.tcjnd = tcjnd;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("用户ID[").append(getUserId()).append("],")
			.append("用户姓名[").append(getUserName()).append("],")
			.append("月份[").append(getMonth()).append("],")
			.append("公司缴纳部分[").append(getInsuranceCompany()).append("],")
			.append("个人缴纳部分[").append(getInsurancePersonal()).append("],")
			.append("统筹类型[").append(getInsuranceType()).append("],")
			.append("费用类型[").append(getFeiyongtype()).append("],")
			.append("remark[").append(getRemark()).append("],")
			.append("creator[").append(getCreator()).append("],")
			.append("createTime[").append(getCreateTime()).append("],")
			.append(" 0 否 1 是[").append(getActive()).append("],")
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
		if(obj instanceof TongchouZengjian == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		TongchouZengjian other = (TongchouZengjian)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
