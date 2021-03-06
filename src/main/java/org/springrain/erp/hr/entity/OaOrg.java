package org.springrain.erp.hr.entity;

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
 * @version  2017-07-31 10:02:48
 * @see org.springrain.erp.gz.entity.OaOrg
 */
@Table(name="z_oa_org")
public class OaOrg  extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	//alias
	/*
	public static final String TABLE_ALIAS = "移动办公部门";
	public static final String ALIAS_ID = "id";
	public static final String ALIAS_COMCODE = "代码";
	public static final String ALIAS_PID = "上级部门ID";
	public static final String ALIAS_NAME = "组织名称";
	public static final String ALIAS_SYSID = "子系统ID";
	public static final String ALIAS_TYPE = "0,组织机构 1.部门";
	public static final String ALIAS_LEAF = "叶子节点(0:树枝节点;1:叶子节点)";
	public static final String ALIAS_SORTNO = "排序号";
	public static final String ALIAS_DESCRIPTION = "描述";
	public static final String ALIAS_STATE = "是否有效(否/是)";
	public static final String ALIAS_DELUID = "删除人Id";
	public static final String ALIAS_DELTIME = "删除时间";
	public static final String ALIAS_MANAGERMOBILE = "managerMobile";
	public static final String ALIAS_MANAGERNAME = "managerName";
	public static final String ALIAS_JINGDU = "jingdu";
	public static final String ALIAS_WEIDU = "weidu";
	public static final String ALIAS_JULI = "juli";
	public static final String ALIAS_NUMBER = "number";
	public static final String ALIAS_RANK = "rank";
	public static final String ALIAS_ICONURL = "部门图标";
	public static final String ALIAS_ISCHILDORG = "是否分公司(0:不是;1:是)";
	public static final String ALIAS_COMPANYTYPEID = "部门类型Id";
    */
	//date formats
	
	//columns START
	/**
	 * id
	 */
	private java.lang.String id;
	/**
	 * 代码
	 */
	private java.lang.String comcode;
	/**
	 * 上级部门ID
	 */
	private java.lang.String pid;
	/**
	 * 组织名称
	 */
	private java.lang.String name;
	/**
	 * 子系统ID
	 */
	private java.lang.String sysid;
	/**
	 * 0,组织机构 1.部门
	 */
	private java.lang.Integer type;
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
	private java.lang.Integer leaf;
	/**
	 * 排序号
	 */
	private java.lang.Integer sortno;
	/**
	 * 描述
	 */
	private java.lang.String description;
	/**
	 * 是否有效(否/是)
	 */
	private java.lang.String state;
	/**
	 * 删除人Id
	 */
	private java.lang.String delUid;
	/**
	 * 删除时间
	 */
	private java.lang.Long delTime;
	/**
	 * managerMobile
	 */
	private java.lang.String managerMobile;
	/**
	 * managerName
	 */
	private java.lang.String managerName;
	/**
	 * jingdu
	 */
	private java.lang.String jingdu;
	/**
	 * weidu
	 */
	private java.lang.String weidu;
	/**
	 * juli
	 */
	private java.lang.Integer juli;
	/**
	 * number
	 */
	private java.lang.String number;
	/**
	 * rank
	 */
	private java.lang.Integer rank;
	/**
	 * 部门图标
	 */
	private java.lang.String iconUrl;
	/**
	 * 是否分公司(0:不是;1:是)
	 */
	private java.lang.Integer isChildOrg;
	/**
	 * 部门类型Id
	 */
	private java.lang.String companyTypeId;
	//columns END 数据库字段结束
	private List<OaOrg> leafOrg;
	//concstructor

	public OaOrg(){
	}

	public OaOrg(
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
     @WhereSQL(sql="id=:OaOrg_id")
	public java.lang.String getId() {
		return this.id;
	}
		/**
		 * 代码
		 */
	public void setComcode(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.comcode = value;
	}
	
	
	
	/**
	 * 代码
	 */
     @WhereSQL(sql="comcode=:OaOrg_comcode")
	public java.lang.String getComcode() {
		return this.comcode;
	}
		/**
		 * 上级部门ID
		 */
	public void setPid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.pid = value;
	}
	
	
	
	/**
	 * 上级部门ID
	 */
     @WhereSQL(sql="pid=:OaOrg_pid")
	public java.lang.String getPid() {
		return this.pid;
	}
		/**
		 * 组织名称
		 */
	public void setName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.name = value;
	}
	
	
	
	/**
	 * 组织名称
	 */
     @WhereSQL(sql="name=:OaOrg_name")
	public java.lang.String getName() {
		return this.name;
	}
		/**
		 * 子系统ID
		 */
	public void setSysid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.sysid = value;
	}
	
	
	
	/**
	 * 子系统ID
	 */
     @WhereSQL(sql="sysid=:OaOrg_sysid")
	public java.lang.String getSysid() {
		return this.sysid;
	}
		/**
		 * 0,组织机构 1.部门
		 */
	public void setType(java.lang.Integer value) {
		this.type = value;
	}
	
	
	
	/**
	 * 0,组织机构 1.部门
	 */
     @WhereSQL(sql="type=:OaOrg_type")
	public java.lang.Integer getType() {
		return this.type;
	}
		/**
		 * 叶子节点(0:树枝节点;1:叶子节点)
		 */
	public void setLeaf(java.lang.Integer value) {
		this.leaf = value;
	}
	
	
	
	/**
	 * 叶子节点(0:树枝节点;1:叶子节点)
	 */
     @WhereSQL(sql="leaf=:OaOrg_leaf")
	public java.lang.Integer getLeaf() {
		return this.leaf;
	}
		/**
		 * 排序号
		 */
	public void setSortno(java.lang.Integer value) {
		this.sortno = value;
	}
	
	
	
	/**
	 * 排序号
	 */
     @WhereSQL(sql="sortno=:OaOrg_sortno")
	public java.lang.Integer getSortno() {
		return this.sortno;
	}
		/**
		 * 描述
		 */
	public void setDescription(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.description = value;
	}
	
	
	
	/**
	 * 描述
	 */
     @WhereSQL(sql="description=:OaOrg_description")
	public java.lang.String getDescription() {
		return this.description;
	}
		/**
		 * 是否有效(否/是)
		 */
	public void setState(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.state = value;
	}
	
	
	
	/**
	 * 是否有效(否/是)
	 */
     @WhereSQL(sql="state=:OaOrg_state")
	public java.lang.String getState() {
		return this.state;
	}
		/**
		 * 删除人Id
		 */
	public void setDelUid(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.delUid = value;
	}
	
	
	
	/**
	 * 删除人Id
	 */
     @WhereSQL(sql="delUid=:OaOrg_delUid")
	public java.lang.String getDelUid() {
		return this.delUid;
	}
		/**
		 * 删除时间
		 */
	public void setDelTime(java.lang.Long value) {
		this.delTime = value;
	}
	
	
	
	/**
	 * 删除时间
	 */
     @WhereSQL(sql="delTime=:OaOrg_delTime")
	public java.lang.Long getDelTime() {
		return this.delTime;
	}
		/**
		 * managerMobile
		 */
	public void setManagerMobile(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.managerMobile = value;
	}
	
	
	
	/**
	 * managerMobile
	 */
     @WhereSQL(sql="managerMobile=:OaOrg_managerMobile")
	public java.lang.String getManagerMobile() {
		return this.managerMobile;
	}
		/**
		 * managerName
		 */
	public void setManagerName(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.managerName = value;
	}
	
	
	
	/**
	 * managerName
	 */
     @WhereSQL(sql="managerName=:OaOrg_managerName")
	public java.lang.String getManagerName() {
		return this.managerName;
	}
		/**
		 * jingdu
		 */
	public void setJingdu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.jingdu = value;
	}
	
	
	
	/**
	 * jingdu
	 */
     @WhereSQL(sql="jingdu=:OaOrg_jingdu")
	public java.lang.String getJingdu() {
		return this.jingdu;
	}
		/**
		 * weidu
		 */
	public void setWeidu(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.weidu = value;
	}
	
	
	
	/**
	 * weidu
	 */
     @WhereSQL(sql="weidu=:OaOrg_weidu")
	public java.lang.String getWeidu() {
		return this.weidu;
	}
		/**
		 * juli
		 */
	public void setJuli(java.lang.Integer value) {
		this.juli = value;
	}
	
	
	
	/**
	 * juli
	 */
     @WhereSQL(sql="juli=:OaOrg_juli")
	public java.lang.Integer getJuli() {
		return this.juli;
	}
		/**
		 * number
		 */
	public void setNumber(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.number = value;
	}
	
	
	
	/**
	 * number
	 */
     @WhereSQL(sql="number=:OaOrg_number")
	public java.lang.String getNumber() {
		return this.number;
	}
		/**
		 * rank
		 */
	public void setRank(java.lang.Integer value) {
		this.rank = value;
	}
	
	
	
	/**
	 * rank
	 */
     @WhereSQL(sql="rank=:OaOrg_rank")
	public java.lang.Integer getRank() {
		return this.rank;
	}
		/**
		 * 部门图标
		 */
	public void setIconUrl(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.iconUrl = value;
	}
	
	
	
	/**
	 * 部门图标
	 */
     @WhereSQL(sql="iconUrl=:OaOrg_iconUrl")
	public java.lang.String getIconUrl() {
		return this.iconUrl;
	}
		/**
		 * 是否分公司(0:不是;1:是)
		 */
	public void setIsChildOrg(java.lang.Integer value) {
		this.isChildOrg = value;
	}
	
	
	
	/**
	 * 是否分公司(0:不是;1:是)
	 */
     @WhereSQL(sql="isChildOrg=:OaOrg_isChildOrg")
	public java.lang.Integer getIsChildOrg() {
		return this.isChildOrg;
	}
		/**
		 * 部门类型Id
		 */
	public void setCompanyTypeId(java.lang.String value) {
		    if(StringUtils.isNotBlank(value)){
			 value=value.trim();
			}
		this.companyTypeId = value;
	}
	
	
	
	/**
	 * 部门类型Id
	 */
     @WhereSQL(sql="companyTypeId=:OaOrg_companyTypeId")
	public java.lang.String getCompanyTypeId() {
		return this.companyTypeId;
	}
	@Override
	public String toString() {
		return new StringBuilder()
			.append("id[").append(getId()).append("],")
			.append("代码[").append(getComcode()).append("],")
			.append("上级部门ID[").append(getPid()).append("],")
			.append("组织名称[").append(getName()).append("],")
			.append("子系统ID[").append(getSysid()).append("],")
			.append("0,组织机构 1.部门[").append(getType()).append("],")
			.append("叶子节点(0:树枝节点;1:叶子节点)[").append(getLeaf()).append("],")
			.append("排序号[").append(getSortno()).append("],")
			.append("描述[").append(getDescription()).append("],")
			.append("是否有效(否/是)[").append(getState()).append("],")
			.append("删除人Id[").append(getDelUid()).append("],")
			.append("删除时间[").append(getDelTime()).append("],")
			.append("managerMobile[").append(getManagerMobile()).append("],")
			.append("managerName[").append(getManagerName()).append("],")
			.append("jingdu[").append(getJingdu()).append("],")
			.append("weidu[").append(getWeidu()).append("],")
			.append("juli[").append(getJuli()).append("],")
			.append("number[").append(getNumber()).append("],")
			.append("rank[").append(getRank()).append("],")
			.append("部门图标[").append(getIconUrl()).append("],")
			.append("是否分公司(0:不是;1:是)[").append(getIsChildOrg()).append("],")
			.append("部门类型Id[").append(getCompanyTypeId()).append("],")
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
		if(obj instanceof OaOrg == false){
			return false;
		}
			
		if(this == obj){
			return true;
		}
		
		OaOrg other = (OaOrg)obj;
		return new EqualsBuilder()
			.append(getId(),other.getId())
			.isEquals();
	}
	@Transient
	public List<OaOrg> getLeafOrg() {
		return leafOrg;
	}

	public void setLeafOrg(List<OaOrg> leafOrg) {
		this.leafOrg = leafOrg;
	}
	
	
}

	
