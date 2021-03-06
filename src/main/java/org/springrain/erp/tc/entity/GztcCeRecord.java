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
 * @version  2017-08-11 10:29:49
 * @see org.springrain.erp.gz.entity.TongchouCerepord
 */
@Table(name="z_gztc_cerecord")
public class GztcCeRecord  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "TongchouCerepord";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_COMPANY = "公司编号";
	public static final String ALIAS_INSURANCEORGONGJIJINACCOUNT = "保险公积金账号";
	public static final String ALIAS_INSURGONGJIJINTYPE = "insurgongjijinType";
	public static final String ALIAS_TCJIAONADI = "统筹缴纳地";
	public static final String ALIAS_DEPARTMENT = "部门";
	public static final String ALIAS_COMPANYDK = "代扣代缴公司部分";
	public static final String ALIAS_INSURANCEPERSONALDK = "代扣代缴个人部分";
	public static final String ALIAS_COMPANYJN = "社保缴纳公司部分";
	public static final String ALIAS_INSURANCEPERSONALJN = "缴纳个人部分";
	public static final String ALIAS_INSURANCEPERSONALCE = "缴纳部分差额";
	public static final String ALIAS_COMPANYCE = "companyCe";
	public static final String ALIAS_CREATEUSER = "createUser";
	public static final String ALIAS_CREATETIME = "createTime";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
	public static final String ALIAS_BAK4 = "bak4";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 公司编号
	 */
	private java.lang.String company;
	/**
	 * 保险公积金账号
	 */
	private java.lang.String insuranceorgongjijinAccount;
	/**
	 * insurgongjijinType
	 */
	private java.lang.String insurgongjijinType;
	/**
	 * 统筹缴纳地
	 */
	private java.lang.String tcjiaonadi;
	/**
	 * 部门
	 */
	private java.lang.String department;
	/**
	 * 代扣代缴公司部分
	 */
	private java.math.BigDecimal companyDK;
	/**
	 * 代扣代缴个人部分
	 */
	private java.math.BigDecimal insurancePersonalDk;
	/**
	 * 社保缴纳公司部分
	 */
	private java.math.BigDecimal companyJn;
	/**
	 * 缴纳个人部分
	 */
	private java.math.BigDecimal insurancePersonalJn;
	/**
	 * 缴纳部分差额
	 */
	private java.math.BigDecimal insurancePersonalCe;
	/**
	 * companyCe
	 */
	private java.math.BigDecimal companyCe;
	/**
	 * createUser
	 */
	private java.lang.String createUser;
	/**
	 * createTime
	 */
	private Date createTime;
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
	
	private Date month;
	
	private String insuranceType;
	private String userId ; 
	private String userName;
	//columns END 数据库字段结束
	private List<TongchouShow> listShow;
	//concstructor

	public GztcCeRecord(){
	}

	public GztcCeRecord(
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
     @WhereSQL(sql="id=:TongchouCerepord_id")
	public java.lang.String getId() {
		return this.id;
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
     @WhereSQL(sql="company=:TongchouCerepord_company")
	public java.lang.String getCompany() {
		return this.company;
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
     @WhereSQL(sql="insuranceorgongjijinAccount=:TongchouCerepord_insuranceorgongjijinAccount")
	public java.lang.String getInsuranceorgongjijinAccount() {
		return this.insuranceorgongjijinAccount;
	}
		/**
		 * insurgongjijinType
		 */
	public void setInsurgongjijinType(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.insurgongjijinType = value;
	}
	
	
	
	/**
	 * insurgongjijinType
	 */
     @WhereSQL(sql="insurgongjijinType=:TongchouCerepord_insurgongjijinType")
	public java.lang.String getInsurgongjijinType() {
		return this.insurgongjijinType;
	}
		/**
		 * 统筹缴纳地
		 */
	public void setTcjiaonadi(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.tcjiaonadi = value;
	}
	
	
	
	/**
	 * 统筹缴纳地
	 */
     @WhereSQL(sql="tcjiaonadi=:TongchouCerepord_tcjiaonadi")
	public java.lang.String getTcjiaonadi() {
		return this.tcjiaonadi;
	}
		/**
		 * 部门
		 */
	public void setDepartment(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.department = value;
	}
	
	
	
	/**
	 * 部门
	 */
     @WhereSQL(sql="department=:TongchouCerepord_department")
	public java.lang.String getDepartment() {
		return this.department;
	}
		/**
		 * 代扣代缴公司部分
		 */
	public void setCompanyDK(java.math.BigDecimal value) {
		this.companyDK = value;
	}
	
	
	
	/**
	 * 代扣代缴公司部分
	 */
     @WhereSQL(sql="companyDK=:TongchouCerepord_companyDK")
	public java.math.BigDecimal getCompanyDK() {
		return this.companyDK;
	}
		/**
		 * 代扣代缴个人部分
		 */
	public void setInsurancePersonalDk(java.math.BigDecimal value) {
		this.insurancePersonalDk = value;
	}
	
	
	
	/**
	 * 代扣代缴个人部分
	 */
     @WhereSQL(sql="insurancePersonalDk=:TongchouCerepord_insurancePersonalDk")
	public java.math.BigDecimal getInsurancePersonalDk() {
		return this.insurancePersonalDk;
	}
		/**
		 * 社保缴纳公司部分
		 */
	public void setCompanyJn(java.math.BigDecimal value) {
		this.companyJn = value;
	}
	
	
	
	/**
	 * 社保缴纳公司部分
	 */
     @WhereSQL(sql="companyJn=:TongchouCerepord_companyJn")
	public java.math.BigDecimal getCompanyJn() {
		return this.companyJn;
	}
		/**
		 * 缴纳个人部分
		 */
	public void setInsurancePersonalJn(java.math.BigDecimal value) {
		this.insurancePersonalJn = value;
	}
	
	
	
	/**
	 * 缴纳个人部分
	 */
     @WhereSQL(sql="insurancePersonalJn=:TongchouCerepord_insurancePersonalJn")
	public java.math.BigDecimal getInsurancePersonalJn() {
		return this.insurancePersonalJn;
	}
		/**
		 * 缴纳部分差额
		 */
	public void setInsurancePersonalCe(java.math.BigDecimal value) {
		this.insurancePersonalCe = value;
	}
	
	
	
	/**
	 * 缴纳部分差额
	 */
     @WhereSQL(sql="insurancePersonalCe=:TongchouCerepord_insurancePersonalCe")
	public java.math.BigDecimal getInsurancePersonalCe() {
		return this.insurancePersonalCe;
	}
		/**
		 * companyCe
		 */
	public void setCompanyCe(java.math.BigDecimal value) {
		this.companyCe = value;
	}
	
	
	
	/**
	 * companyCe
	 */
     @WhereSQL(sql="companyCe=:TongchouCerepord_companyCe")
	public java.math.BigDecimal getCompanyCe() {
		return this.companyCe;
	}
		/**
		 * createUser
		 */
	public void setCreateUser(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.createUser = value;
	}
	
	
	
	/**
	 * createUser
	 */
     @WhereSQL(sql="createUser=:TongchouCerepord_createUser")
	public java.lang.String getCreateUser() {
		return this.createUser;
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
     @WhereSQL(sql="bak1=:TongchouCerepord_bak1")
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
     @WhereSQL(sql="bak2=:TongchouCerepord_bak2")
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
     @WhereSQL(sql="bak3=:TongchouCerepord_bak3")
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
     @WhereSQL(sql="bak4=:TongchouCerepord_bak4")
	public java.lang.String getBak4() {
		return this.bak4;
	}
     public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}

	public Date getMonth() {
			return month;
		}

		public void setMonth(Date month) {
			this.month = month;
		}
		
	public String getInsuranceType() {
			return insuranceType;
		}

		public void setInsuranceType(String insuranceType) {
			this.insuranceType = insuranceType;
		}
		
		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

		@Transient
	public List<TongchouShow> getListShow() {
			return listShow;
		}

		public void setListShow(List<TongchouShow> listShow) {
			this.listShow = listShow;
		}

	public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("公司编号[").append(getCompany()).append("],")
			.append("保险公积金账号[").append(getInsuranceorgongjijinAccount()).append("],")
			.append("insurgongjijinType[").append(getInsurgongjijinType()).append("],")
			.append("统筹缴纳地[").append(getTcjiaonadi()).append("],")
			.append("部门[").append(getDepartment()).append("],")
			.append("代扣代缴公司部分[").append(getCompanyDK()).append("],")
			.append("代扣代缴个人部分[").append(getInsurancePersonalDk()).append("],")
			.append("社保缴纳公司部分[").append(getCompanyJn()).append("],")
			.append("缴纳个人部分[").append(getInsurancePersonalJn()).append("],")
			.append("缴纳部分差额[").append(getInsurancePersonalCe()).append("],")
			.append("companyCe[").append(getCompanyCe()).append("],")
			.append("createUser[").append(getCreateUser()).append("],")
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
		if(obj instanceof GztcCeRecord == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		GztcCeRecord other = (GztcCeRecord)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
}

	
