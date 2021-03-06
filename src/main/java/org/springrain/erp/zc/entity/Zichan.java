package org.springrain.erp.zc.entity;

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
 * @version  2017-08-14 09:44:45
 * @see org.springrain.erp.zc.entity.Zichan
 */
@Table(name="z_zichan")
public class Zichan  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "资产表";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_ZCCODE = "资产编号";
	public static final String ALIAS_ZCNAME = "资产名称";
	public static final String ALIAS_ZCTYPE = "资产类型";
	public static final String ALIAS_GUIGE = "规格型号";
	public static final String ALIAS_DANWEI = "单位";
	public static final String ALIAS_GOUMAIDATE = "购买日期";
	public static final String ALIAS_RUKUDATE = "入库日期";
	public static final String ALIAS_ZCNUMBER = "资产数量";
	public static final String ALIAS_KUCUN = "库存量";
	public static final String ALIAS_ZCPRICE = "资产单价";
	public static final String ALIAS_ZCMONEY = "资产金额";
	public static final String ALIAS_NIANXIAN = "使用年限";
	public static final String ALIAS_ZHIBAODATE = "质保到期日";
	public static final String ALIAS_JINGXIAOSHANG = "经销商";
	public static final String ALIAS_JINGXIAOSHANGTEL = "经销商联系方式";
	public static final String ALIAS_ZCCONFIG = "资产配置情况";
	public static final String ALIAS_JSUSER = "经手人";
	public static final String ALIAS_REMARK = "备注";
	public static final String ALIAS_CREATEUSER = "创建人";
	public static final String ALIAS_CREATETIME = "创建日期";
	public static final String ALIAS_ACTIVE = "状态";
	public static final String ALIAS_BAK1 = "bak1";
	public static final String ALIAS_BAK2 = "bak2";
	public static final String ALIAS_BAK3 = "bak3";
    */
	//date formats
	//public static final String FORMAT_GOUMAIDATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_RUKUDATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_ZHIBAODATE = DateUtils.DATETIME_FORMAT;
	//public static final String FORMAT_CREATETIME = DateUtils.DATETIME_FORMAT;
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 资产编号
	 */
	private java.lang.String zccode;
	/**
	 * 资产名称
	 */
	private java.lang.String zcname;
	/**
	 * 资产类型
	 */
	private java.lang.String zctype;
	/**
	 * 规格型号
	 */
	private java.lang.String guige;
	/**
	 * 单位
	 */
	private java.lang.String danwei;
	/**
	 * 购买日期
	 */
	private java.util.Date goumaidate;
	/**
	 * 入库日期
	 */
	private java.util.Date rukudate;
	/**
	 * 资产数量
	 */
	private java.lang.Integer zcnumber;
	/**
	 * 库存量
	 */
	private java.lang.Integer kucun;
	/**
	 * 资产单价
	 */
	private java.math.BigDecimal zcprice;
	/**
	 * 资产金额
	 */
	private java.math.BigDecimal zcmoney;
	/**
	 * 使用年限
	 */
	private java.math.BigDecimal nianxian;
	/**
	 * 质保到期日
	 */
	private java.util.Date zhibaodate;
	/**
	 * 经销商
	 */
	private java.lang.String jingxiaoshang;
	/**
	 * 经销商联系方式
	 */
	private java.lang.String jingxiaoshangtel;
	/**
	 * 资产配置情况
	 */
	private java.lang.String zcconfig;
	/**
	 * 经手人
	 */
	private java.lang.String jsuser;
	/**
	 * 备注
	 */
	private java.lang.String remark;
	/**
	 * 创建人
	 */
	private java.lang.String createuser;
	/**
	 * 创建日期
	 */
	private java.util.Date createtime;
	/**
	 * 状态
	 */
	private java.lang.Integer active;
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
	 * 资产类别
	 */
	private String zcleibie;
	/**
	 * 资产存放地
	 */
	private String zccfd;
	//columns END 数据库字段结束
	private String createuserName;
	private String sdate;
	private String edate;
	private String zctypeName;
	private String srukudate;
	private String erukudate;
	private String zcleibieName;
	//concstructor

	public Zichan(){
	}

	public Zichan(
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
     @WhereSQL(sql="id=:Zichan_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 资产编号
		 */
	public void setZccode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zccode = value;
	}
	
	
	
	/**
	 * 资产编号
	 */
     @WhereSQL(sql="zccode=:Zichan_zccode")
	public java.lang.String getZccode() {
		return this.zccode;
	}
		/**
		 * 资产名称
		 */
	public void setZcname(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zcname = value;
	}
	
	
	
	/**
	 * 资产名称
	 */
     @WhereSQL(sql="zcname=:Zichan_zcname")
	public java.lang.String getZcname() {
		return this.zcname;
	}
		/**
		 * 资产类型
		 */
	public void setZctype(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zctype = value;
	}
	
	
	
	/**
	 * 资产类型
	 */
     @WhereSQL(sql="zctype=:Zichan_zctype")
	public java.lang.String getZctype() {
		return this.zctype;
	}
		/**
		 * 规格型号
		 */
	public void setGuige(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.guige = value;
	}
	
	
	
	/**
	 * 规格型号
	 */
     @WhereSQL(sql="guige=:Zichan_guige")
	public java.lang.String getGuige() {
		return this.guige;
	}
		/**
		 * 单位
		 */
	public void setDanwei(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.danwei = value;
	}
	
	
	
	/**
	 * 单位
	 */
     @WhereSQL(sql="danwei=:Zichan_danwei")
	public java.lang.String getDanwei() {
		return this.danwei;
	}
		/*
	public String getgoumaidateString() {
		return DateUtils.convertDate2String(FORMAT_GOUMAIDATE, getgoumaidate());
	}
	public void setgoumaidateString(String value) throws ParseException{
		setgoumaidate(DateUtils.convertString2Date(FORMAT_GOUMAIDATE,value));
	}*/
	
		/**
		 * 购买日期
		 */
	public void setGoumaidate(java.util.Date value) {
		this.goumaidate = value;
	}
	
	
	
	/**
	 * 购买日期
	 */
     @WhereSQL(sql="goumaidate=:Zichan_goumaidate")
	public java.util.Date getGoumaidate() {
		return this.goumaidate;
	}
		/*
	public String getrukudateString() {
		return DateUtils.convertDate2String(FORMAT_RUKUDATE, getrukudate());
	}
	public void setrukudateString(String value) throws ParseException{
		setrukudate(DateUtils.convertString2Date(FORMAT_RUKUDATE,value));
	}*/
	
		/**
		 * 入库日期
		 */
	public void setRukudate(java.util.Date value) {
		this.rukudate = value;
	}
	
	
	
	/**
	 * 入库日期
	 */
     @WhereSQL(sql="rukudate=:Zichan_rukudate")
	public java.util.Date getRukudate() {
		return this.rukudate;
	}
		/**
		 * 资产数量
		 */
	public void setZcnumber(java.lang.Integer value) {
		this.zcnumber = value;
	}
	
	
	
	/**
	 * 资产数量
	 */
     @WhereSQL(sql="zcnumber=:Zichan_zcnumber")
	public java.lang.Integer getZcnumber() {
		return this.zcnumber;
	}
		/**
		 * 库存量
		 */
	public void setKucun(java.lang.Integer value) {
		this.kucun = value;
	}
	
	
	
	/**
	 * 库存量
	 */
     @WhereSQL(sql="kucun=:Zichan_kucun")
	public java.lang.Integer getKucun() {
		return this.kucun;
	}
		/**
		 * 资产单价
		 */
	public void setZcprice(java.math.BigDecimal value) {
		this.zcprice = value;
	}
	
	
	
	/**
	 * 资产单价
	 */
     @WhereSQL(sql="zcprice=:Zichan_zcprice")
	public java.math.BigDecimal getZcprice() {
		return this.zcprice;
	}
		/**
		 * 资产金额
		 */
	public void setZcmoney(java.math.BigDecimal value) {
		this.zcmoney = value;
	}
	
	
	
	/**
	 * 资产金额
	 */
     @WhereSQL(sql="zcmoney=:Zichan_zcmoney")
	public java.math.BigDecimal getZcmoney() {
		return this.zcmoney;
	}
		/**
		 * 使用年限
		 */
	public void setNianxian(java.math.BigDecimal value) {
		this.nianxian = value;
	}
	
	
	
	/**
	 * 使用年限
	 */
     @WhereSQL(sql="nianxian=:Zichan_nianxian")
	public java.math.BigDecimal getNianxian() {
		return this.nianxian;
	}
		/*
	public String getzhibaodateString() {
		return DateUtils.convertDate2String(FORMAT_ZHIBAODATE, getzhibaodate());
	}
	public void setzhibaodateString(String value) throws ParseException{
		setzhibaodate(DateUtils.convertString2Date(FORMAT_ZHIBAODATE,value));
	}*/
	
		/**
		 * 质保到期日
		 */
	public void setZhibaodate(java.util.Date value) {
		this.zhibaodate = value;
	}
	
	
	
	/**
	 * 质保到期日
	 */
     @WhereSQL(sql="zhibaodate=:Zichan_zhibaodate")
	public java.util.Date getZhibaodate() {
		return this.zhibaodate;
	}
		/**
		 * 经销商
		 */
	public void setJingxiaoshang(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.jingxiaoshang = value;
	}
	
	
	
	/**
	 * 经销商
	 */
     @WhereSQL(sql="jingxiaoshang=:Zichan_jingxiaoshang")
	public java.lang.String getJingxiaoshang() {
		return this.jingxiaoshang;
	}
		/**
		 * 经销商联系方式
		 */
	public void setJingxiaoshangtel(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.jingxiaoshangtel = value;
	}
	
	
	
	/**
	 * 经销商联系方式
	 */
     @WhereSQL(sql="jingxiaoshangtel=:Zichan_jingxiaoshangtel")
	public java.lang.String getJingxiaoshangtel() {
		return this.jingxiaoshangtel;
	}
		/**
		 * 资产配置情况
		 */
	public void setZcconfig(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.zcconfig = value;
	}
	
	
	
	/**
	 * 资产配置情况
	 */
     @WhereSQL(sql="zcconfig=:Zichan_zcconfig")
	public java.lang.String getZcconfig() {
		return this.zcconfig;
	}
		/**
		 * 经手人
		 */
	public void setJsuser(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.jsuser = value;
	}
	
	
	
	/**
	 * 经手人
	 */
     @WhereSQL(sql="jsuser=:Zichan_jsuser")
	public java.lang.String getJsuser() {
		return this.jsuser;
	}
		/**
		 * 备注
		 */
	public void setRemark(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.remark = value;
	}
	
	
	
	/**
	 * 备注
	 */
     @WhereSQL(sql="remark=:Zichan_remark")
	public java.lang.String getRemark() {
		return this.remark;
	}
		/**
		 * 创建人
		 */
	public void setCreateuser(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.createuser = value;
	}
	
	
	
	/**
	 * 创建人
	 */
     @WhereSQL(sql="createuser=:Zichan_createuser")
	public java.lang.String getCreateuser() {
		return this.createuser;
	}
		/*
	public String getcreatetimeString() {
		return DateUtils.convertDate2String(FORMAT_CREATETIME, getcreatetime());
	}
	public void setcreatetimeString(String value) throws ParseException{
		setcreatetime(DateUtils.convertString2Date(FORMAT_CREATETIME,value));
	}*/
	
		/**
		 * 创建日期
		 */
	public void setCreatetime(java.util.Date value) {
		this.createtime = value;
	}
	
	
	
	/**
	 * 创建日期
	 */
     @WhereSQL(sql="createtime=:Zichan_createtime")
	public java.util.Date getCreatetime() {
		return this.createtime;
	}
		/**
		 * 状态
		 */
	public void setActive(java.lang.Integer value) {
		this.active = value;
	}
	
	
	
	/**
	 * 状态
	 */
     @WhereSQL(sql="active=:Zichan_active")
	public java.lang.Integer getActive() {
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
     @WhereSQL(sql="bak1=:Zichan_bak1")
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
     @WhereSQL(sql="bak2=:Zichan_bak2")
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
     @WhereSQL(sql="bak3=:Zichan_bak3")
	public java.lang.String getBak3() {
		return this.bak3;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("资产编号[").append(getZccode()).append("],")
			.append("资产名称[").append(getZcname()).append("],")
			.append("资产类型[").append(getZctype()).append("],")
			.append("规格型号[").append(getGuige()).append("],")
			.append("单位[").append(getDanwei()).append("],")
			.append("购买日期[").append(getGoumaidate()).append("],")
			.append("入库日期[").append(getRukudate()).append("],")
			.append("资产数量[").append(getZcnumber()).append("],")
			.append("库存量[").append(getKucun()).append("],")
			.append("资产单价[").append(getZcprice()).append("],")
			.append("资产金额[").append(getZcmoney()).append("],")
			.append("使用年限[").append(getNianxian()).append("],")
			.append("质保到期日[").append(getZhibaodate()).append("],")
			.append("经销商[").append(getJingxiaoshang()).append("],")
			.append("经销商联系方式[").append(getJingxiaoshangtel()).append("],")
			.append("资产配置情况[").append(getZcconfig()).append("],")
			.append("经手人[").append(getJsuser()).append("],")
			.append("备注[").append(getRemark()).append("],")
			.append("创建人[").append(getCreateuser()).append("],")
			.append("创建日期[").append(getCreatetime()).append("],")
			.append("状态[").append(getActive()).append("],")
			.append("bak1[").append(getBak1()).append("],")
			.append("bak2[").append(getBak2()).append("],")
			.append("bak3[").append(getBak3()).append("],")
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
		if(obj instanceof Zichan == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		Zichan other = (Zichan)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Transient
	public String getCreateuserName() {
		return createuserName;
	}

	public void setCreateuserName(String createuserName) {
		this.createuserName = createuserName;
	}
	@Transient
	public String getSdate() {
		return sdate;
	}

	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	@Transient
	public String getEdate() {
		return edate;
	}

	public void setEdate(String edate) {
		this.edate = edate;
	}
	@Transient
	public String getZctypeName() {
		return zctypeName;
	}

	public void setZctypeName(String zctypeName) {
		this.zctypeName = zctypeName;
	}
	@Transient
	public String getSrukudate() {
		return srukudate;
	}

	public void setSrukudate(String srukudate) {
		this.srukudate = srukudate;
	}
	@Transient
	public String getErukudate() {
		return erukudate;
	}

	public void setErukudate(String erukudate) {
		this.erukudate = erukudate;
	}

	public String getZcleibie() {
		return zcleibie;
	}

	public void setZcleibie(String zcleibie) {
		this.zcleibie = zcleibie;
	}
	@Transient
	public String getZcleibieName() {
		return zcleibieName;
	}

	public void setZcleibieName(String zcleibieName) {
		this.zcleibieName = zcleibieName;
	}

	public String getZccfd() {
		return zccfd;
	}

	public void setZccfd(String zccfd) {
		this.zccfd = zccfd;
	}
	
}

	
