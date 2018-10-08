package org.springrain.erp.hr.entity;

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
 * @version  2017-07-25 13:38:29
 * @see org.springrain.erp.hr.entity.UserEducational
 */
@Table(name="t_user_educational")
public class UserEducational  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "用户教育经历表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_USERID = "用户ID";
	public static final String ALIAS_STARTDATE = "开始时间";
	public static final String ALIAS_ENDDATE = "结束时间";
	public static final String ALIAS_XUEXIAO = "学校名称";
	public static final String ALIAS_XUELI = "学历";
	public static final String ALIAS_ZHAOSHENGFANGSHI = "招生方式";
    */
	//date formats
	//public static final String FORMAT_STARTDATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ENDDATE = DateUtils.DATETIME_FORMAT;
	
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
	 * 开始时间
	 */
	private java.util.Date startDate;
	/**
	 * 结束时间
	 */
	private java.util.Date endDate;
	/**
	 * 学校名称
	 */
	private java.lang.String xuexiao;
	/**
	 * 学历
	 */
	private java.lang.String xueli;
	/**
	 * 招生方式
	 */
	private java.lang.String zhaoshengfangshi;
	/**
	 * 排序
	 */
	private java.lang.Integer sort;
	private String xuewei;
	private String zhuanye;
	//columns END 数据库字段结束
	private String xueliName;
	private String zhaoshengfangshiName;
	private String xueweiName;
	//concstructor

	public UserEducational(){
	}

	public UserEducational(
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
     @WhereSQL(sql="id=:UserEducational_id")
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
	
	
	
	/**
	 * 用户ID
	 */
     @WhereSQL(sql="userId=:UserEducational_userId")
	public java.lang.String getUserId() {
		return this.userId;
	}
		/*
	public String getstartDateString() {
		return DateUtils.convertDate2String(FORMAT_STARTDATE, getstartDate());
	}
	public void setstartDateString(String value) throws ParseException{
		setstartDate(DateUtils.convertString2Date(FORMAT_STARTDATE,value));
	}*/
	
		/**
		 * 开始时间
		 */
	public void setStartDate(java.util.Date value) {
		this.startDate = value;
	}
	
	
	
	/**
	 * 开始时间
	 */
     @WhereSQL(sql="startDate=:UserEducational_startDate")
	public java.util.Date getStartDate() {
		return this.startDate;
	}
		/*
	public String getendDateString() {
		return DateUtils.convertDate2String(FORMAT_ENDDATE, getendDate());
	}
	public void setendDateString(String value) throws ParseException{
		setendDate(DateUtils.convertString2Date(FORMAT_ENDDATE,value));
	}*/
	
		/**
		 * 结束时间
		 */
	public void setEndDate(java.util.Date value) {
		this.endDate = value;
	}
	
	
	
	/**
	 * 结束时间
	 */
     @WhereSQL(sql="endDate=:UserEducational_endDate")
	public java.util.Date getEndDate() {
		return this.endDate;
	}
		/**
		 * 学校名称
		 */
	public void setXuexiao(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.xuexiao = value;
	}
	
	
	
	/**
	 * 学校名称
	 */
     @WhereSQL(sql="xuexiao=:UserEducational_xuexiao")
	public java.lang.String getXuexiao() {
		return this.xuexiao;
	}
		/**
		 * 学历
		 */
	public void setXueli(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.xueli = value;
	}
	
	
	
	/**
	 * 学历
	 */
     @WhereSQL(sql="xueli=:UserEducational_xueli")
	public java.lang.String getXueli() {
		return this.xueli;
	}
		/**
		 * 招生方式
		 */
	public void setZhaoshengfangshi(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zhaoshengfangshi = value;
	}
	
	
	
	/**
	 * 招生方式
	 */
     @WhereSQL(sql="zhaoshengfangshi=:UserEducational_zhaoshengfangshi")
	public java.lang.String getZhaoshengfangshi() {
		return this.zhaoshengfangshi;
	}
     /**
      * 排序
      */
     @WhereSQL(sql="sort=:UserEducational_sort")
	public java.lang.Integer getSort() {
		return sort;
	}
     /**
      * 排序
      */
	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}

	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("用户ID[").append(getUserId()).append("],")
			.append("开始时间[").append(getStartDate()).append("],")
			.append("结束时间[").append(getEndDate()).append("],")
			.append("学校名称[").append(getXuexiao()).append("],")
			.append("学历[").append(getXueli()).append("],")
			.append("招生方式[").append(getZhaoshengfangshi()).append("],")
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
		if(obj instanceof UserEducational == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		UserEducational other = (UserEducational)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}

	public String getXuewei() {
		return xuewei;
	}

	public void setXuewei(String xuewei) {
		this.xuewei = xuewei;
	}

	public String getZhuanye() {
		return zhuanye;
	}

	public void setZhuanye(String zhuanye) {
		this.zhuanye = zhuanye;
	}
	@Transient
	public String getXueliName() {
		return xueliName;
	}

	public void setXueliName(String xueliName) {
		this.xueliName = xueliName;
	}
	@Transient
	public String getZhaoshengfangshiName() {
		return zhaoshengfangshiName;
	}

	public void setZhaoshengfangshiName(String zhaoshengfangshiName) {
		this.zhaoshengfangshiName = zhaoshengfangshiName;
	}
	@Transient
	public String getXueweiName() {
		return xueweiName;
	}

	public void setXueweiName(String xueweiName) {
		this.xueweiName = xueweiName;
	}
	
}

	
