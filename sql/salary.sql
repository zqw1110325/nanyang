-- ----------------------------
-- 工资表
-- ----------------------------
DROP TABLE IF EXISTS `z_salary`;
CREATE TABLE `z_salary` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) NOT NULL COMMENT '用户Id',
  `unitId` varchar(50) NOT NULL COMMENT '部门Id',
  `inDate` datetime DEFAULT NULL COMMENT '归属月份',
  `startDate` datetime NOT NULL COMMENT '开始时间',
  `endDate` datetime NOT NULL COMMENT '结束时间',
  `jibenpay` decimal(18,5) NOT NULL COMMENT '基本工资',
  `kaohepay` decimal(18,5) NOT NULL COMMENT '考核工资',
  `gangweipay` decimal(18,5) NOT NULL COMMENT '岗位工资',
  `ripay` decimal(18,5) NOT NULL COMMENT '日工资',
  `geshui` decimal(18,2) NOT NULL COMMENT '基本工资',
  `shifupay` decimal(18,2) NOT NULL COMMENT '考核工资',
  `yingfupay` decimal(18,2) NOT NULL COMMENT '岗位工资',
  `state` int(11) NOT NULL DEFAULT '1' COMMENT '状态 1是未生成 2生成 3已发放',
  `sendDate` datetime DEFAULT NULL COMMENT '发放日期',
  `sendTime` datetime DEFAULT NULL COMMENT '发放时间,系统时间',
  `sendPerson` varchar(50) DEFAULT NULL COMMENT '发放人',
  `remarker` varchar(3000) DEFAULT NULL COMMENT '说明',
  
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资主表';



-- ----------------------------
-- 工资子表
-- ----------------------------
DROP TABLE IF EXISTS `z_salaryinfo`;
CREATE TABLE `z_salaryinfo` (
  `id` varchar(50) NOT NULL,
  `salaryId` varchar(50) DEFAULT NULL COMMENT '主表ID',
  `businessId` varchar(50) DEFAULT NULL COMMENT '业务ID',
  `salaryType` varchar(200) DEFAULT NULL COMMENT '工资类型(包括社保、公积金和自定义的工资类型)',
  `amount` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  `remarker` varchar(3000) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='工资子表';

 alter table z_salaryinfo add foreign key(salaryId) references z_salary(id);
 -- alter table salaryinfo add foreign key(salaryTypeId) references dic_salary_type(id);
 -- alter table z_salary add foreign key(userId) references t_user(id);



-- ----------------------------
-- 公式表
-- ----------------------------
DROP TABLE IF EXISTS `z_formula`;
CREATE TABLE `z_formula` (
  `id` varchar(50) NOT NULL,
  `name` varchar(200) NOT NULL COMMENT '名称',
  `chineseName` varchar(500) DEFAULT NULL COMMENT '表达式的中文名称',
  `expression` varchar(5000) DEFAULT NULL COMMENT '表达式',
  `chineseExpression` varchar(5000) DEFAULT NULL COMMENT '表达式的中文显示',
  `sysExpression` int(11) NOT NULL DEFAULT '0' COMMENT '是否是系统变量,0是 1否',
  `projectName` varchar(500) NOT NULL COMMENT '项目名称,用于区分不同项目的公式',
  `inEffectiveDate` datetime NOT NULL DEFAULT '2012-01-01 00:00:00' COMMENT '起效日期',
  `active` int(11) NOT NULL DEFAULT '0' COMMENT '是否可用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公式';


-- ----------------------------
-- 业务公式数据
-- ----------------------------
DROP TABLE IF EXISTS `z_formula_data_2017`;
CREATE TABLE `z_formula_data_2017` (
  `id` varchar(50) NOT NULL,
  `businessId` varchar(50) NOT NULL COMMENT '业务ID',
  `formulaId` varchar(50) NOT NULL COMMENT '公式ID',
  `projectName` varchar(100) NOT NULL COMMENT '项目ID',
  `amount` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务公式数据2017';

DROP TABLE IF EXISTS `z_formula_data_2018`;
CREATE TABLE `z_formula_data_2018` (
  `id` varchar(50) NOT NULL,
  `businessId` varchar(50) NOT NULL COMMENT '业务ID',
  `formulaId` varchar(50) NOT NULL COMMENT '公式ID',
  `projectName` varchar(100) NOT NULL COMMENT '项目ID',
  `amount` decimal(18,2) NOT NULL DEFAULT '0.00' COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='业务公式数据2018';

-- 初始化需要执行的系统变量
INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salay_jibenpay','基本工资','基本工资',0,'salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salay_kaohepay','考核工资','考核工资',0,'salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salay_gonglingpay','工龄工资','工龄工资',0,'salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salary_monthdays','本月总天数','本月总天数',0,'salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salary_ondutydays','本月在职天数','本月在职天数',0,'salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salary_taxrate','个税税率','个税税率',0,'salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salary_taxshu','个税速算扣除数','个税速算扣除数',0,'salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salary_rfygzhj','日费用工资合计','日费用工资合计',0,'salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,projectName,inEffectiveDate)
values('salary_rfygzhjkk','日费用工资合计扣款','日费用工资合计扣款',0,'salary','2017-07-03');

-- 初始化需要执行的公式
INSERT z_formula(id,name,chineseName,sysExpression,chineseExpression,projectName,inEffectiveDate)
VALUES('salary_baseSalary','工资项合计','工资项合计',1,'(基本工资+考核工资+工龄工资)*本月在职天数/本月总天数','salary','2017-07-03');


INSERT z_formula(id,name,chineseName,sysExpression,chineseExpression,projectName,inEffectiveDate)
VALUES('salary_yingfupay','应付工资','应付工资',1,'工资项合计+日费用工资合计-日费用工资合计扣款','salary','2017-07-03');


INSERT z_formula(id,name,chineseName,sysExpression,chineseExpression,projectName,inEffectiveDate)
VALUES('salary_taxbase','个税基数','个税基数',1,'应付工资','salary','2017-07-03');

INSERT z_formula(id,name,chineseName,sysExpression,chineseExpression,projectName,inEffectiveDate)
values('salary_geshui','个税','个税',1,'(个税基数-3500)*个税税率-个税速算扣除数','salary','2017-07-03');


INSERT z_formula(id,name,chineseName,sysExpression,chineseExpression,projectName,inEffectiveDate)
VALUES('salary_shifupay','实付工资','实付工资',1,'应付工资-个税','salary','2017-07-03');



-- ----------------------------
-- 保险公积金增减项
-- ----------------------------
DROP TABLE IF EXISTS `z_tongchou_zengjian`;
CREATE TABLE `z_tongchou_zengjian` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `userName` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `month` date DEFAULT NULL COMMENT '月份',
  `insuranceCompany` decimal(18,2) DEFAULT NULL COMMENT '公司缴纳部分',
  `insurancePersonal` decimal(18,2) DEFAULT NULL COMMENT '个人缴纳部分',
  `insuranceType` varchar(50) DEFAULT NULL COMMENT '统筹类型',
  `feiyongtype` varchar(50) DEFAULT NULL COMMENT '费用类型',
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `active` varchar(2) DEFAULT NULL COMMENT ' 0 否 1 是',
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='保险公积金增减项';

-- ----------------------------
-- 保险公积金信息表
-- ----------------------------
DROP TABLE IF EXISTS `z_tongchou_info`;
CREATE TABLE `z_tongchou_info` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL COMMENT '公司编号',
  `stopProtectMonth` datetime DEFAULT NULL COMMENT '停保月份',
  `insuranceorgongjijinAccount` varchar(255) DEFAULT NULL COMMENT '保险公积金账号',
  `insurgongjijinType` varchar(2) DEFAULT NULL COMMENT '类型',
  `insurancePaymentDate` datetime DEFAULT NULL COMMENT '缴费开始时间',
  `efficientDate` datetime DEFAULT NULL COMMENT '缴费结束时间',
  `radices` decimal(18,2) DEFAULT NULL COMMENT '基数',
  `insuranceCompany` decimal(18,2) DEFAULT NULL COMMENT '公司缴纳部分',
  `insurancePersonal` decimal(18,2) DEFAULT NULL COMMENT '个人缴纳部分',
  `insuranceType` varchar(50) DEFAULT NULL COMMENT '统筹类型',
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `state` varchar(2) DEFAULT NULL COMMENT '状态 0 否 1 是',
  `cardId` varchar(50) DEFAULT NULL COMMENT '身份证',
  `userName` varchar(50) DEFAULT NULL,
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='保险公积金信息表';


-- ----------------------------
--	保险公积金月度数据表
-- ----------------------------
DROP TABLE IF EXISTS `z_tongchou_record`;
CREATE TABLE `z_tongchou_record` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL COMMENT '公司编号',
  `stopProtectMonth` datetime DEFAULT NULL COMMENT '停保月份',
  `insuranceorgongjijinAccount` varchar(255) DEFAULT NULL COMMENT '保险公积金账号',
  `insurgongjijinType` varchar(2) DEFAULT NULL COMMENT '类型 1 保险账号 0 公积金账号',
  `insurancePaymentDate` datetime DEFAULT NULL COMMENT '缴费开始时间',
  `efficientDate` date DEFAULT NULL COMMENT '缴费结束时间',
  `radices` decimal(18,2) DEFAULT NULL COMMENT '基数',
  `insuranceCompany` decimal(18,2) DEFAULT NULL COMMENT '公司缴纳部分',
  `insurancePersonal` decimal(18,2) DEFAULT NULL COMMENT '个人缴纳部分',
  `insuranceType` varchar(255) DEFAULT NULL COMMENT '统筹类型 ',
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '建立时间',
  `state` varchar(2) DEFAULT NULL COMMENT '状态 0 否 1 是',
  `cardId` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `month` datetime DEFAULT NULL COMMENT '所属月份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='保险公积金月度数据表';

-- ----------------------------
-- 保险公积桌面显示表
-- ----------------------------
DROP TABLE IF EXISTS `z_tongchou_show`;
CREATE TABLE `z_tongchou_show` (
  `id` varchar(50) NOT NULL COMMENT '字典id',
  `dicttypeId` varchar(50) DEFAULT NULL,
  `property` varchar(50) DEFAULT NULL COMMENT '属性',
  `propertyValue` varchar(50) DEFAULT NULL COMMENT '属性值',
  `deskShowName` varchar(255) DEFAULT NULL COMMENT '桌面显示值',
  `showOrhidden` varchar(50) DEFAULT NULL COMMENT '是否隐藏',
  `active` varchar(2) DEFAULT NULL COMMENT ' 0 否 1 是',
  `type` varchar(50) DEFAULT NULL COMMENT '区分基础信息和月度数据',
  `reportType` varchar(255) DEFAULT NULL COMMENT '主要记录缴纳类型',
  `createUser` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='保险公积桌面显示表';


-- ----------------------------
-- 劳务合同
-- ----------------------------
DROP TABLE IF EXISTS `z_work_contract`;
CREATE TABLE `z_work_contract` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL COMMENT '归属公司',
  `orgId` text COMMENT '部门',
  `startDate` datetime DEFAULT NULL COMMENT '签订开始日期',
  `endDate` datetime DEFAULT NULL COMMENT '签订结束日期',
  `contractType` varchar(50) DEFAULT NULL COMMENT '合同类型',
  `workType` varchar(50) DEFAULT NULL COMMENT '用工方式',
  `bak` varchar(255) DEFAULT NULL COMMENT '备注',
  `state` varchar(10) DEFAULT NULL COMMENT '是否有效',
  `editPerson` varchar(50) DEFAULT NULL COMMENT '编辑人',
  `editDate` datetime DEFAULT NULL COMMENT '编辑时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='劳务合同';


-- ----------------------------
-- 用户信息表
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `userCode` varchar(50) DEFAULT NULL COMMENT '员工代码',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `workno` varchar(50) DEFAULT NULL COMMENT '工号',
  `idCard` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `company` varchar(50) DEFAULT NULL COMMENT '公司',
  `tongchou` varchar(50) DEFAULT NULL COMMENT '统筹缴纳地',
  `gangwei` varchar(50) DEFAULT NULL COMMENT '岗位',
  `entryDate` datetime DEFAULT NULL COMMENT '入职日期',
  `startDate` datetime DEFAULT NULL COMMENT '转正日期',
  `endDate` datetime DEFAULT NULL COMMENT '离职日期',
  `mobile1` varchar(50) DEFAULT NULL COMMENT '移动电话1',
  `mobile2` varchar(50) DEFAULT NULL COMMENT '移动电话2',
  `telephone` varchar(50) DEFAULT NULL COMMENT '固定电话',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `marryState` varchar(2) DEFAULT NULL COMMENT '婚否',
  `health` varchar(50) DEFAULT NULL COMMENT '健康状态',
  `entryCity` varchar(50) DEFAULT NULL COMMENT '入职城市',
  `entryType` varchar(50) DEFAULT NULL COMMENT '入职方式',
  `introducer` varchar(50) DEFAULT NULL COMMENT '介绍人',
  `minzu` varchar(50) DEFAULT NULL COMMENT '民族',
  `politicsStatus` varchar(50) DEFAULT NULL COMMENT '政治面貌',
  `zuigaoxueli` varchar(50) DEFAULT NULL COMMENT '最高学历',
  `jiaoyujingli` text COMMENT '教育经历',
  `fireName` varchar(50) DEFAULT NULL COMMENT '紧急联系人',
  `firePhone` varchar(50) DEFAULT NULL COMMENT '紧急联系电话',
  `bak` varchar(255) DEFAULT NULL COMMENT '备注',
  `bankName` varchar(50) DEFAULT NULL COMMENT '银行名称',
  `bankAccountName` varchar(50) DEFAULT NULL COMMENT '银行帐号',
  `bankId` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `bankState` varchar(10) DEFAULT NULL COMMENT '卡号状态',
  `jibenpay` decimal(18,2) DEFAULT NULL COMMENT '基本工资',
  `kaohepay` decimal(18,2) DEFAULT NULL COMMENT '考核工资',
  `gangweipay` decimal(18,2) DEFAULT NULL COMMENT '岗位工资',
  `stoppay` varchar(5) DEFAULT NULL COMMENT '是否停发工资',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4  COMMENT='用户信息表';

-- ----------------------------
-- 用户信息备份表
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info_bak`;
CREATE TABLE `t_user_info_bak` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `userCode` varchar(50) DEFAULT NULL COMMENT '员工代码',
  `birthday` datetime DEFAULT NULL COMMENT '出生日期',
  `workno` varchar(50) DEFAULT NULL COMMENT '工号',
  `idCard` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `company` varchar(50) DEFAULT NULL COMMENT '公司',
  `tongchou` varchar(50) DEFAULT NULL COMMENT '统筹缴纳地',
  `gangwei` varchar(50) DEFAULT NULL COMMENT '岗位',
  `entryDate` datetime DEFAULT NULL COMMENT '入职日期',
  `startDate` datetime DEFAULT NULL COMMENT '转正日期',
  `endDate` datetime DEFAULT NULL COMMENT '离职日期',
  `mobile1` varchar(50) DEFAULT NULL COMMENT '移动电话1',
  `mobile2` varchar(50) DEFAULT NULL COMMENT '移动电话2',
  `telephone` varchar(50) DEFAULT NULL COMMENT '固定电话',
  `email` varchar(255) DEFAULT NULL COMMENT '电子邮箱',
  `marryState` varchar(2) DEFAULT NULL COMMENT '婚否',
  `health` varchar(50) DEFAULT NULL COMMENT '健康状态',
  `entryCity` varchar(50) DEFAULT NULL COMMENT '入职城市',
  `entryType` varchar(50) DEFAULT NULL COMMENT '入职方式',
  `introducer` varchar(50) DEFAULT NULL COMMENT '介绍人',
  `minzu` varchar(50) DEFAULT NULL COMMENT '民族',
  `politicsStatus` varchar(50) DEFAULT NULL COMMENT '政治面貌',
  `zuigaoxueli` varchar(50) DEFAULT NULL COMMENT '最高学历',
  `jiaoyujingli` text COMMENT '教育经历',
  `fireName` varchar(50) DEFAULT NULL COMMENT '紧急联系人',
  `firePhone` varchar(50) DEFAULT NULL COMMENT '紧急联系电话',
  `bak` varchar(255) DEFAULT NULL COMMENT '备注',
  `bankName` varchar(50) DEFAULT NULL COMMENT '银行名称',
  `bankAccountName` varchar(50) DEFAULT NULL COMMENT '银行帐号',
  `bankId` varchar(50) DEFAULT NULL COMMENT '银行卡号',
  `bankState` varchar(10) DEFAULT NULL COMMENT '卡号状态',
  `jibenpay` decimal(18,2) DEFAULT NULL COMMENT '基本工资',
  `kaohepay` decimal(18,2) DEFAULT NULL COMMENT '考核工资',
  `gangweipay` decimal(18,2) DEFAULT NULL COMMENT '岗位工资',
  `stoppay` varchar(5) DEFAULT NULL COMMENT '是否停发工资',
  `month` datetime DEFAULT NULL COMMENT '所属月份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';


-- 统筹增减项新增字段

alter table z_tongchou_zengjian add  isused VARCHAR(20) COMMENT '0 否 1 是';

alter table z_tongchou_zengjian add  salayid VARCHAR(50) COMMENT '工资id';

alter table z_tongchou_show add  sortno int(20) COMMENT '排序号';

alter table z_tongchou_record add  salayid VARCHAR(50) COMMENT '工资id';

alter table z_salaryinfo add  active VARCHAR(50) COMMENT '增减项状态 0未使用 1 使用 ';

alter table t_user_info add  lizhiremark VARCHAR(200) COMMENT '离职原因';

alter table t_user add  editDate datetime COMMENT '修改时间-员工离职';

alter table t_user add  editUser VARCHAR(50) COMMENT '修改人-员工离职';
-- 停保
ALTER table z_tongchou_info add operationUser VARCHAR(50) COMMENT '停保操作人';

ALTER table z_tongchou_info add operationDate datetime COMMENT '停保操作时间';
-- 天数
alter table z_salaryinfo add decimal(18,2) COMMENT '天数';

ALTER table z_tongchou_info add operationDate datetime COMMENT '停保操作时间';

-- 2017-07-25 合同表新增字段
alter table z_work_contract add  term int(11) DEFAULT NULL COMMENT '录入期限';
alter table z_work_contract add  times int(11) DEFAULT NULL COMMENT '签订次数';
alter table z_work_contract add  contractNo varchar(50) DEFAULT NULL COMMENT '合同编号';

-- 2017-07-25 用户基本信息表增加字段
alter table t_user_info add  firstTime datetime DEFAULT NULL COMMENT '首次参加工作时间';
alter table t_user_info add  grade varchar(50) DEFAULT NULL COMMENT '级别';
alter table t_user_info_bak add  grade varchar(50) DEFAULT NULL COMMENT '级别';

-- ----------------------------
-- 用户教育经历表
-- ----------------------------
DROP TABLE IF EXISTS `t_user_educational`;
CREATE TABLE `t_user_educational` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL COMMENT '用户ID',
  `startDate` datetime DEFAULT NULL COMMENT '开始时间',
  `endDate` datetime DEFAULT NULL COMMENT '结束时间',
  `xuexiao` varchar(255) DEFAULT NULL COMMENT '学校名称',
  `xueli` varchar(255) DEFAULT NULL COMMENT '学历',
  `zhaoshengfangshi` varchar(255) DEFAULT NULL COMMENT '招生方式',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户教育经历表';

-- ----------------------------
-- 用户证书表
-- ----------------------------
DROP TABLE IF EXISTS `t_user_certificate`;
CREATE TABLE `t_user_certificate` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL COMMENT '用户名称',
  `bianhao` varchar(255) DEFAULT NULL COMMENT '编号',
  `name` varchar(255) DEFAULT NULL COMMENT '证书名称',
  `banzhengjigou` varchar(255) DEFAULT NULL COMMENT '颁证机构',
  `passDate` datetime DEFAULT NULL COMMENT '通过日期',
  `youxiaoqi` datetime DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户证书表';
--  oa user_org
DROP TABLE IF EXISTS `z_oa_user_org`;
CREATE TABLE `z_oa_user_org` (
  `id` varchar(40) NOT NULL COMMENT '编号',
  `userId` varchar(40) NOT NULL COMMENT '用户编号',
  `orgId` varchar(40) NOT NULL COMMENT '机构编号',
  `active` int(2) DEFAULT '1' COMMENT '是否可用',
  `delUid` varchar(50) DEFAULT NULL COMMENT '删除人Id',
  `delTime` bigint(20) DEFAULT NULL COMMENT '删除时间',
  `manager` int(2) DEFAULT '0' COMMENT '是否是主管(0：否，1：是)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户部门中间表';
-- oa user
-- ----------------------------
-- Table structure for z_oa_user
-- ----------------------------
DROP TABLE IF EXISTS `z_oa_user`;
CREATE TABLE `z_oa_user` (
  `id` varchar(40) NOT NULL DEFAULT '' COMMENT '编号',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `workno` varchar(40) DEFAULT NULL COMMENT '工号',
  `account` varchar(40) DEFAULT NULL COMMENT '账号',
  `password` varchar(40) DEFAULT NULL COMMENT '密码',
  `cardno` varchar(40) DEFAULT NULL COMMENT '身份证',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `sex` varchar(2) DEFAULT '男' COMMENT '性别',
  `phone` varchar(30) DEFAULT NULL COMMENT '电话号码',
  `mobile` varchar(16) DEFAULT NULL COMMENT '手机号码',
  `email` varchar(60) DEFAULT NULL COMMENT '邮箱',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `gradeId` varchar(40) DEFAULT NULL COMMENT '级别',
  `eduName` varchar(40) DEFAULT NULL COMMENT '学历',
  `fireName` varchar(30) DEFAULT NULL COMMENT '紧急联系人',
  `firePhone` varchar(30) DEFAULT NULL COMMENT '紧急联系电话',
  `description` varchar(2000) DEFAULT NULL COMMENT '备注',
  `state` varchar(10) DEFAULT '是' COMMENT '是否有效,是/否/离职',
  `delUid` varchar(50) DEFAULT NULL COMMENT '删除人Id',
  `delTime` bigint(20) DEFAULT NULL COMMENT '删除时间',
  `imei` varchar(50) DEFAULT NULL,
  `imsi` varchar(50) DEFAULT NULL,
  `headimg` varchar(1000) DEFAULT NULL,
  `collegeName` varchar(1000) DEFAULT NULL COMMENT '院系名称',
  `specileName` varchar(1000) DEFAULT NULL COMMENT '专业名称',
  `className` varchar(1000) DEFAULT NULL COMMENT '班级名称',
  `yearName` varchar(50) DEFAULT NULL COMMENT '年级',
  `studyLength` varchar(50) DEFAULT NULL COMMENT '学制',
  `overtime` datetime DEFAULT NULL COMMENT '毕业日期',
  `qqnum` varchar(30) DEFAULT NULL COMMENT 'qq号',
  `familyUserName` varchar(20) DEFAULT NULL COMMENT '家庭联系人',
  `familyPhone` varchar(20) DEFAULT NULL COMMENT '家庭固话',
  `familyMobile` varchar(20) DEFAULT NULL COMMENT '家庭手机',
  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
  `modifytime` datetime DEFAULT NULL COMMENT '修改日期',
  `token` varchar(200) DEFAULT NULL COMMENT '融云token',
  `qrcode` varchar(100) DEFAULT NULL COMMENT '二维码名片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户';
-- oa org

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for z_oa_org
-- ----------------------------
DROP TABLE IF EXISTS `z_oa_org`;
CREATE TABLE `z_oa_org` (
  `id` varchar(40) NOT NULL,
  `comcode` varchar(40) DEFAULT NULL COMMENT '代码',
  `pid` varchar(40) DEFAULT NULL COMMENT '上级部门ID',
  `name` varchar(100) DEFAULT NULL COMMENT '组织名称',
  `sysid` varchar(40) DEFAULT NULL COMMENT '子系统ID',
  `type` int(11) DEFAULT NULL COMMENT '0,组织机构 1.部门',
  `leaf` int(11) DEFAULT NULL COMMENT '叶子节点(0:树枝节点;1:叶子节点)',
  `sortno` int(11) DEFAULT NULL COMMENT '排序号',
  `description` varchar(2000) DEFAULT NULL COMMENT '描述',
  `state` varchar(2) DEFAULT '是' COMMENT '是否有效(否/是)',
  `delUid` varchar(50) DEFAULT NULL COMMENT '删除人Id',
  `delTime` bigint(20) DEFAULT NULL COMMENT '删除时间',
  `managerMobile` varchar(16) DEFAULT NULL,
  `managerName` varchar(20) DEFAULT NULL,
  `jingdu` varchar(50) DEFAULT NULL,
  `weidu` varchar(50) DEFAULT NULL,
  `juli` int(11) DEFAULT NULL,
  `number` varchar(50) DEFAULT NULL,
  `rank` int(3) DEFAULT NULL,
  `iconUrl` varchar(1000) DEFAULT NULL COMMENT '部门图标',
  `isChildOrg` int(2) DEFAULT NULL COMMENT '是否分公司(0:不是;1:是)',
  `companyTypeId` varchar(50) DEFAULT NULL COMMENT '部门类型Id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='移动办公部门';
-- 话费补助
ALTER table t_user_info add  mobilePay  decimal(18,2) COMMENT '电话费补助';

ALTER table z_salary add  mobilePay  decimal(18,2) COMMENT '电话费补助';

ALTER table t_user_info_bak add  mobilePay  decimal(18,2) COMMENT '电话费补助';

ALTER table t_user_info_bak add  grade  varchar(50) COMMENT '级别';

alter table t_user_info add sex VARCHAR(50) COMMENT '性别';
alter table t_user_info add mobile VARCHAR(50) COMMENT '电话';
alter table t_user_info_bak add mobile VARCHAR(50) COMMENT '电话';
-- oaUserId
alter table  t_user   add oauserId  VARCHAR(50) COMMENT 'oaUserId';

-- 2017-08-01 oaOrgId
alter table t_org add oaorgId varchar(50)  DEFAULT NULL COMMENT 'oaorgId';
-- 2017-08-02
alter  table  z_tongchou_info add tcjiaonadi VARCHAR(50) COMMENT '统筹缴纳地';
alter  table  z_tongchou_record add tcjiaonadi VARCHAR(50) COMMENT '统筹缴纳地';

-- 2017-08-03 增加劳动合同导出菜单
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('2e7b3eb6579b47d1bf764914b5dfc084', '劳动合同导出', 'b7234b6e44a542baa47b5a6eb50d1ae0', '', '/workcontract/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
-- 2017-08-03 基本信息表增加字段
ALTER table t_user_info add  jiguan  varchar(50) COMMENT '籍贯';
ALTER table t_user_info add  hujidizhi  varchar(255) COMMENT '户籍地址';
ALTER table t_user_info add  address  varchar(255) COMMENT '现居住详细地址';
ALTER table t_user_info add  guanxi  varchar(50) COMMENT '与本人关系';
ALTER table t_user_info add  xingqu  varchar(500) COMMENT '兴趣爱好';
-- 2017-08-03 教育经理表增加字段
ALTER table t_user_educational add  zhuanye  varchar(50) COMMENT '专业';
ALTER table t_user_educational add  xuewei  varchar(50) COMMENT '学位';
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('fedf919bb0d242f2b0a283748a273d84', '学位管理', 'dic_manager', '', '/system/dicdata/xuewei/list', '1', '1', '3', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('0a6d65f4e28540b895caed0eab25126d', '学位管理更新', 'fedf919bb0d242f2b0a283748a273d84', '', '/system/dicdata/xuewei/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('14a544e375144ab8810341ee3c2cf752', '学位管理批量删除', 'fedf919bb0d242f2b0a283748a273d84', '', '/system/dicdata/xuewei/delete/more', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('22cb5c85ee1a426c8e27f5ea122c02d7', '学位管理删除', 'fedf919bb0d242f2b0a283748a273d84', '', '/system/dicdata/xuewei/delete', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('5a0d4b338da045e6b89f0148fa74c748', '学位管理查看', 'fedf919bb0d242f2b0a283748a273d84', '', '/system/dicdata/xuewei/look', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('5d906247941845e5922565ff4a8eb985', '学位管理树', 'fedf919bb0d242f2b0a283748a273d84', '', '/system/dicdata/xuewei/tree', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);

-- 2017-08-04 员工履历表
DROP TABLE IF EXISTS `t_user_resume`;
CREATE TABLE `t_user_resume` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL COMMENT '员工Id',
  `type` int(11) DEFAULT NULL COMMENT '类别',
  `createPerson` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createDate` datetime DEFAULT NULL COMMENT '创建时间',
  `oldOrg` varchar(500) DEFAULT NULL COMMENT '原部门',
  `newOrg` varchar(500) DEFAULT NULL COMMENT '新部门',
  `oldGrade` varchar(50) DEFAULT NULL COMMENT '原级别',
  `newGrade` varchar(50) DEFAULT NULL COMMENT '新级别',
  `oldGangwei` varchar(50) DEFAULT NULL COMMENT '原岗位',
  `newGangwei` varchar(50) DEFAULT NULL COMMENT '新岗位',
  `oldJibenpay` decimal(18,2) DEFAULT NULL COMMENT '原基本工资',
  `newJibenpay` decimal(18,2) DEFAULT NULL COMMENT '新基本工资',
  `oldKaohepay` decimal(18,2) DEFAULT NULL COMMENT '原考核工资',
  `newKaohepay` decimal(18,2) DEFAULT NULL COMMENT '新考核工资',
  `oldGangweipay` decimal(18,2) DEFAULT NULL COMMENT '原岗位工资',
  `newGangweipay` decimal(18,2) DEFAULT NULL COMMENT '新岗位工资',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工履历表';

INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('dcd1b51f9430492e8664fdb5ef5883c0', '记录统计', NULL, '', NULL, '1', '1', NULL, '&#xe63c;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('981f0bc085224ab383324dd00c1da115', '员工履历', 'dcd1b51f9430492e8664fdb5ef5883c0', '', NULL, '1', '1', '5', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('2b73ab9040554492801750fdaf91709a', '员工薪资变更记录', '981f0bc085224ab383324dd00c1da115', '', '/userresume/3/list', '1', '1', '3', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('5cb78c49d9d045598c080f0b33eb6c72', '员工级别变更记录', '981f0bc085224ab383324dd00c1da115', '', '/userresume/2/list', '1', '1', '2', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('5f57c085a9464a4fa784aa2076ebf51f', '员工薪资变更导出', '2b73ab9040554492801750fdaf91709a', '', '/userresume/3/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('68047b7d18024428a2328c3d0704a6d8', '员工部门变更记录', '981f0bc085224ab383324dd00c1da115', '', '/userresume/0/list', '1', '1', '0', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('6967653a223c481dae512befc0cd2721', '员工岗位变更导出', 'e6c9eff2e57b45cc89b5e66f995cd7ff', '', '/userresume/1/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('84b8291262d14460a34e1360ef29686b', '员工部门变更导出', '68047b7d18024428a2328c3d0704a6d8', '', '/userresume/0/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('865e1fb757b144abb4d89aebc2d25acb', '员工级别变更导出', '5cb78c49d9d045598c080f0b33eb6c72', '', '/userresume/2/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('e6c9eff2e57b45cc89b5e66f995cd7ff', '员工岗位变更记录', '981f0bc085224ab383324dd00c1da115', '', '/userresume/1/list', '1', '1', '1', '&#xe623;', NULL, NULL, NULL, NULL, NULL);

ALTER table t_org add  center  varchar(50) COMMENT '归属中心';

-- 2017-08-08 员工综合信息菜单
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('16c8b6130a114b92ae94c3e7b05bd90f', '员工综合信息导出', 'd6399dea3e6b4ad0b402447e6abff159', '', '/userbaseinfo/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('d6399dea3e6b4ad0b402447e6abff159', '员工综合信息', 'dcd1b51f9430492e8664fdb5ef5883c0', '', '/userbaseinfo/list', '1', '1', NULL, '&#xe624;', NULL, NULL, NULL, NULL, NULL);
-- 统筹信息导入导出菜单
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('0d70474b77ef469dad5760d5ae1b18c7', '统筹信息导入', '698290a3a3784275a958f705239d6037', '', '/tongchouinfo/import', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('79e017d5570448b9987fcf4ddd742b30', '员工统筹公积金导出', '698290a3a3784275a958f705239d6037', '', '/tongchouinfo/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('cbb63f374dd04d059444e7a8d96812fe', '统筹信息模板下载', '698290a3a3784275a958f705239d6037', '', '/tongchouinfo/ajax/downMoban', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
-- 2017-8-10
-- z_tongchou_info z_tongchou_record新增部门
alter  table  z_tongchou_info add department VARCHAR(50) COMMENT '部门';
alter  table  z_tongchou_record add department VARCHAR(50) COMMENT '部门';
-- 2017-08-11
alter  table  z_tongchou_zengjian add company VARCHAR(50) COMMENT '公司';
alter  table  z_tongchou_zengjian add tcjnd VARCHAR(50) COMMENT '统筹缴纳地';

--统筹记录表

-- ----------------------------
-- Table structure for z_gzdkdj_record
-- ----------------------------
DROP TABLE IF EXISTS `z_gzdkdj_record`;
CREATE TABLE `z_gzdkdj_record` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL COMMENT '公司编号',
  `tcjnd` varchar(255) DEFAULT NULL COMMENT '统筹缴纳地',
  `department` varchar(50) DEFAULT NULL COMMENT '部门',
  `stopProtectMonth` datetime DEFAULT NULL COMMENT '停保月份',
  `insuranceorgongjijinAccount` varchar(255) DEFAULT NULL COMMENT '保险公积金账号',
  `insurgongjijinType` varchar(2) DEFAULT NULL COMMENT '类型 1 保险账号 0 公积金账号',
  `radices` decimal(18,2) DEFAULT NULL COMMENT '基数',
  `insuranceCompany` decimal(18,2) DEFAULT NULL COMMENT '公司缴纳部分',
  `insurancePersonal` decimal(18,2) DEFAULT NULL COMMENT '个人缴纳部分',
  `insuranceType` varchar(255) DEFAULT NULL COMMENT '统筹类型 ',
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `month` datetime DEFAULT NULL COMMENT '所属月份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='工资代扣代缴表';

-- ----------------------------
-- Table structure for z_tcjn_record
-- ----------------------------
DROP TABLE IF EXISTS `z_tcjn_record`;
CREATE TABLE `z_tcjn_record` (
  `id` varchar(50) NOT NULL,
  `userId` varchar(50) DEFAULT NULL,
  `company` varchar(50) DEFAULT NULL COMMENT '公司编号',
  `tcjnd` varchar(255) DEFAULT NULL COMMENT '统筹缴纳地',
  `department` varchar(50) DEFAULT NULL COMMENT '部门',
  `stopProtectMonth` datetime DEFAULT NULL COMMENT '停保月份',
  `insuranceorgongjijinAccount` varchar(255) DEFAULT NULL COMMENT '保险公积金账号',
  `insurgongjijinType` varchar(2) DEFAULT NULL COMMENT '类型 1 保险账号 0 公积金账号',
  `radices` decimal(18,2) DEFAULT NULL COMMENT '基数',
  `insuranceCompany` decimal(18,2) DEFAULT NULL COMMENT '公司缴纳部分',
  `insurancePersonal` decimal(18,2) DEFAULT NULL COMMENT '个人缴纳部分',
  `insuranceType` varchar(255) DEFAULT NULL COMMENT '统筹类型 ',
  `remark` varchar(255) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createTime` datetime DEFAULT NULL COMMENT '创建时间',
  `userName` varchar(255) DEFAULT NULL COMMENT '用户名',
  `month` datetime DEFAULT NULL COMMENT '所属月份',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='社保公积金缴纳报表';

-- ----------------------------
-- Table structure for z_tongchou_cerepord
-- ----------------------------
DROP TABLE IF EXISTS `z_gztc_cerecord`;
CREATE TABLE `z_gztc_cerecord` (
  `id` varchar(50) NOT NULL,
  `company` varchar(255) DEFAULT NULL COMMENT '公司编号',
  `userId` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `insuranceorgongjijinAccount` varchar(255) DEFAULT NULL COMMENT '保险公积金账号',
  `insurgongjijinType` varchar(255) DEFAULT NULL,
  `tcjiaonadi` varchar(255) DEFAULT NULL COMMENT '统筹缴纳地',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `companyDK` decimal(18,2) DEFAULT NULL COMMENT '代扣代缴公司部分',
  `insurancePersonalDk` decimal(18,2) DEFAULT NULL COMMENT '代扣代缴个人部分',
  `companyJn` decimal(18,2) DEFAULT NULL COMMENT '社保缴纳公司部分',
  `insurancePersonalJn` decimal(18,2) DEFAULT NULL COMMENT '缴纳个人部分',
  `insurancePersonalCe` decimal(18,2) DEFAULT NULL COMMENT '缴纳部分差额',
  `companyCe` decimal(18,2) DEFAULT NULL,
  `createUser` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `insuranceType` varchar(255) DEFAULT NULL,
  `month` datetime DEFAULT NULL,
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Table structure for z_tongchou_kkze
-- ----------------------------
DROP TABLE IF EXISTS `z_tongchou_kkze`;
CREATE TABLE `z_tongchou_kkze` (
  `id` varchar(50) NOT NULL,
  `company` varchar(255) DEFAULT NULL COMMENT '公司编号',
  `userId` varchar(255) DEFAULT NULL,
  `userName` varchar(255) DEFAULT NULL,
  `tcjiaonadi` varchar(255) DEFAULT NULL COMMENT '统筹缴纳地',
  `department` varchar(255) DEFAULT NULL COMMENT '部门',
  `insuranceCompany` decimal(18,2) DEFAULT NULL COMMENT '公司部分',
  `insurancePersonal` decimal(18,2) DEFAULT NULL COMMENT '个人部分',
  `createUser` varchar(255) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `insuranceType` varchar(255) DEFAULT NULL,
  `month` datetime DEFAULT NULL,
  `sourceType` varchar(255) DEFAULT NULL COMMENT '0 统筹记录1 增减项',
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  `bak4` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
-- 统筹记录菜单
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('06f3d8604c574ef580948c3deed4f74b', '统筹实际扣款总额', '3cf7cf615f49401f997277c62e267a26', '', '/tongchoukkze/list', '1', '1', '4', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('22039f3d4e184c9abe63473c640d476d', '统筹实际扣款导出', '06f3d8604c574ef580948c3deed4f74b', '', '/tongchoukkze/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('e63c7afe8abc402fbdc65cf701238c5d', '统筹实际扣款生成', '06f3d8604c574ef580948c3deed4f74b', '', '/tongchoukkze/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('10e4bcfa2b2f4d91a05b9a5583e5f327', '代扣代缴与缴纳差异保存', '1793edcf1067458d8262e753e3f6a5f3', '', '/gztccerecord/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('1793edcf1067458d8262e753e3f6a5f3', '代扣代缴缴纳差异', '3cf7cf615f49401f997277c62e267a26', '', '/gztccerecord/list', '1', '1', '3', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('98ff8eed9a1d446a87b56de98e7a958a', '代扣代缴与缴纳差异导出', '1793edcf1067458d8262e753e3f6a5f3', '', '/gztccerecord/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('55cf3524648d42338433dad0a930a381', '工资代扣代缴报表生成', 'a6c79054f004491e8dbd0dbdf84d91be', '', '/gzdkdjrecord/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('979a1ec353d44249b42c6da0b8dffb8f', '工资代扣代缴报表导出', 'a6c79054f004491e8dbd0dbdf84d91be', '', '/gzdkdjrecord/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('a6c79054f004491e8dbd0dbdf84d91be', '工资代扣代缴报表', '3cf7cf615f49401f997277c62e267a26', '', '/gzdkdjrecord/list', '1', '1', NULL, '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('35eb1141d8e14d878d297bba017e1e57', '社保公积金缴纳导出', 'bbaba48e1c004f1eab11ab98946c7817', '', '/tcjnrecord/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('5eab825592dc429bba4cbbe1acbfaa18', '社保公积金缴纳生成', 'bbaba48e1c004f1eab11ab98946c7817', '', '/tcjnrecord/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('bbaba48e1c004f1eab11ab98946c7817', '社保公积金缴纳表', '3cf7cf615f49401f997277c62e267a26', '', '/tcjnrecord/list', '1', '1', NULL, '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('3cf7cf615f49401f997277c62e267a26', '统筹记录', 'dcd1b51f9430492e8664fdb5ef5883c0', '', NULL, '1', '1', NULL, '&#xe624;', NULL, NULL, NULL, NULL, NULL);

-- 资产菜单
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('0c8d2eb6b5544284812403b6144abf58', '资产报损', 'ae5ea5c36a6042f28b593b776c9c4782', '', '/zichan/baosun', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('0e0bbf1c784f45d78028c0dcdcee2b9a', '资产变动记录-超级', '63bdde3efa1943869dabdee5d4df1484', '', '/zichanrecord/list/super', '1', '1', '3', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('1c519d26b1d24e759bba76d67a9cbfbb', '资产类型查看', '2aa8f7be5181464ab2e0e56e8a9b932a', '', '/system/dicdata/zichan/look', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('2aa8f7be5181464ab2e0e56e8a9b932a', '资产类型管理', '63bdde3efa1943869dabdee5d4df1484', '', '/system/dicdata/zichan/list', '1', '1', '2', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('3485def2ab7945c090af655abb403475', '资产类型树形结构', '2aa8f7be5181464ab2e0e56e8a9b932a', '', '/system/dicdata/zichan/tree', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('35504514d627483bb8cc745d46eca595', '资产变动记录导出-个人', '6e16f58d36e3402ab1312945f2e6ef00', '', '/zichanrecord/list/me/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('63bdde3efa1943869dabdee5d4df1484', '资产管理', 'business_manager', '', NULL, '1', '1', '70', '&#xe624;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('6e16f58d36e3402ab1312945f2e6ef00', '资产变动记录-个人', '63bdde3efa1943869dabdee5d4df1484', '', '/zichanrecord/list/me', '1', '1', '4', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('70e645d8434e448e8cad480b88923d37', '资产类型删除', '2aa8f7be5181464ab2e0e56e8a9b932a', '', '/system/dicdata/zichan/delete', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('7be928681bfe43e8a10404bd812dfb71', '资产管理管理页面', 'ae5ea5c36a6042f28b593b776c9c4782', '', '/zichan/manage', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('87da10d6d5f04aaaadb47ffaff6ec973', '资产出售', 'ae5ea5c36a6042f28b593b776c9c4782', '', '/zichan/chushou', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('8d1b2628a3df47e5913bd70ad123a211', '资产类型批量删除', '2aa8f7be5181464ab2e0e56e8a9b932a', '', '/system/dicdata/zichan/delete/more', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('992cbb401a604086b46b791c848183de', '资产变动记录导出-超级', '0e0bbf1c784f45d78028c0dcdcee2b9a', '', '/zichanrecord/list/super/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('adda380a334a4b48a074ec612223202c', '资产管理导出', 'ae5ea5c36a6042f28b593b776c9c4782', '', '/zichan/list/export', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('ae5ea5c36a6042f28b593b776c9c4782', '资产管理', '63bdde3efa1943869dabdee5d4df1484', '', '/zichan/list', '1', '1', '1', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('b535beb0a6464671b86ccd1dd1383889', '资产领用', 'ae5ea5c36a6042f28b593b776c9c4782', '', '/zichan/lingyong', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('b7ab61e5482e4e22828cabe35bbb9398', '资产类型更新', '2aa8f7be5181464ab2e0e56e8a9b932a', '', '/system/dicdata/zichan/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('dbd3e1072a9140e89c2445b55f757de4', '资产管理导入', 'ae5ea5c36a6042f28b593b776c9c4782', '', '/zichan/import', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('e33a69e9176b46d382208c4097400daf', '资产归还', 'ae5ea5c36a6042f28b593b776c9c4782', '', '/zichan/guihuan', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('e9ddbc7d55ae451ca6739487c15394df', '资产管理修改', 'ae5ea5c36a6042f28b593b776c9c4782', '', '/zichan/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for zichan
-- ----------------------------
DROP TABLE IF EXISTS `z_zichan`;
CREATE TABLE `z_zichan` (
  `id` varchar(50) NOT NULL,
  `zccode` varchar(50) NOT NULL COMMENT '资产编号',
  `zcname` varchar(50) NOT NULL COMMENT '资产名称',
  `zctype` varchar(50) NOT NULL COMMENT '资产类型',
  `guige` text DEFAULT NULL COMMENT '规格型号',
  `danwei` varchar(50) DEFAULT NULL COMMENT '单位',
  `goumaidate` datetime DEFAULT NULL COMMENT '购买日期',
  `rukudate` datetime NOT NULL COMMENT '入库日期',
  `zcnumber` int(11) DEFAULT NULL COMMENT '资产数量',
  `kucun` int(11) DEFAULT NULL COMMENT '库存量',
  `zcprice` decimal(18,2) DEFAULT NULL COMMENT '资产单价',
  `zcmoney` decimal(18,2) DEFAULT NULL COMMENT '资产金额',
  `nianxian` decimal(18,2) DEFAULT NULL COMMENT '使用年限',
  `zhibaodate` datetime DEFAULT NULL COMMENT '质保到期日',
  `jingxiaoshang` varchar(50) DEFAULT NULL COMMENT '经销商',
  `jingxiaoshangtel` varchar(50) DEFAULT NULL COMMENT '经销商联系方式',
  `zcconfig` varchar(500) DEFAULT NULL COMMENT '资产配置情况',
  `jsuser` varchar(50) DEFAULT NULL COMMENT '经手人',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `createuser` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
  `active` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `zccode` (`zccode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '资产表';

-- ----------------------------
-- Table structure for zichan_ling_detail
-- ----------------------------
DROP TABLE IF EXISTS `z_zichan_ling_detail`;
CREATE TABLE `z_zichan_ling_detail` (
  `id` varchar(50) NOT NULL,
  `zcId` varchar(50) NOT NULL,
  `linguser` varchar(50) NOT NULL COMMENT '领用人',
  `linguserid` varchar(50) DEFAULT NULL COMMENT '领用人id',
  `lingunit` varchar(50) DEFAULT NULL COMMENT '领用部门',
  `lingunitid` varchar(500) DEFAULT NULL COMMENT '领用部门id',
  `lingnum` decimal(18,2) DEFAULT NULL COMMENT '领用数量',
  `active` int(11) NOT NULL DEFAULT '0',
  `bak1` varchar(50) DEFAULT NULL,
  `bak2` varchar(50) DEFAULT NULL,
  `bak3` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '资产领用表';

-- ----------------------------
-- Table structure for zichan_record
-- ----------------------------
DROP TABLE IF EXISTS `z_zichan_record`;
CREATE TABLE `z_zichan_record` (
  `id` varchar(50) NOT NULL,
  `zcid` varchar(50) NOT NULL,
  `lingid` varchar(50) DEFAULT NULL,
  `retype` varchar(50) DEFAULT NULL COMMENT '发生类型',
  `reuser` varchar(50) DEFAULT NULL COMMENT '发生人',
  `reuserid` varchar(50) DEFAULT NULL COMMENT '发生人id',
  `reunit` varchar(50) DEFAULT NULL COMMENT '发生部门',
  `reunitid` varchar(500) DEFAULT NULL COMMENT '发生部门id',
  `redate` datetime DEFAULT NULL COMMENT '发生日期',
  `renum` int(11) DEFAULT NULL COMMENT '变动数量',
  `remoney` decimal(18,2) DEFAULT NULL COMMENT '变动金额',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `createuser` varchar(50) DEFAULT NULL COMMENT '创建人',
  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
  `active` int(11) NOT NULL DEFAULT '0',
  `bak1` varchar(255) DEFAULT NULL COMMENT '审核人',
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '资产领用记录表';

DROP TABLE IF EXISTS `z_zichan_properties`;
CREATE TABLE `z_zichan_properties` (
  `id` varchar(50) NOT NULL,
  `zcId` varchar(50) DEFAULT NULL,
  `proName` varchar(255) DEFAULT NULL COMMENT '名称',
  `proValue` varchar(255) DEFAULT NULL COMMENT '值',
  `bak1` varchar(255) DEFAULT NULL,
  `bak2` varchar(255) DEFAULT NULL,
  `bak3` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT '资产属性表';


-- 2017-08-17 增加资产类别
alter  table  z_zichan add zcleibie VARCHAR(50) COMMENT '资产类别';
alter  table  z_zichan add zccfd VARCHAR(50) COMMENT '存放地点';
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('017b71ff0a6d48988329feefbea118d5', '资产类别修改', '381e71b7aa7d408a98c943b05aba2ccf', '', '/system/dicdata/zcleibie/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('35237902530f4fa08bd8cf7590f015c8', '资产类别批量删除', '381e71b7aa7d408a98c943b05aba2ccf', '', '/system/dicdata/zcleibie/delete/more', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('381e71b7aa7d408a98c943b05aba2ccf', '资产类别管理', '63bdde3efa1943869dabdee5d4df1484', '', '/system/dicdata/zcleibie/list', '1', '1', '5', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('3dfb647e0c7c4745954d7f9f5116edf7', '资产类别树', '381e71b7aa7d408a98c943b05aba2ccf', '', '/system/dicdata/zcleibie/tree', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('94b6fbd833ba4878ba12e5c58d0c1eb5', '资产类别删除', '381e71b7aa7d408a98c943b05aba2ccf', '', '/system/dicdata/zcleibie/delete', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('f0aaed01111d4f83b75a1225bfd11fcc', '资产类别查看', '381e71b7aa7d408a98c943b05aba2ccf', '', '/system/dicdata/zcleibie/look', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('695b640701ec45dd88d766305e0647b8', '员工基本信息导入', '977cc95d9edc4c9eb06675921870d5ec', '', '/userinfo/import/baseinfo', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
-- 修改统筹记录菜单

UPDATE `t_menu` SET `id`='06f3d8604c574ef580948c3deed4f74b', `name`='统筹实际扣款总额', `pid`='3cf7cf615f49401f997277c62e267a26', `description`='', `pageurl`='/tongchoukkze/list', `menuType`='1', `active`='1', `sortno`='4', `menuIcon`='&#xe623;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='06f3d8604c574ef580948c3deed4f74b');
UPDATE `t_menu` SET `id`='1793edcf1067458d8262e753e3f6a5f3', `name`='代扣代缴缴纳差异', `pid`='3cf7cf615f49401f997277c62e267a26', `description`='', `pageurl`='/gztccerecord/list', `menuType`='1', `active`='1', `sortno`='3', `menuIcon`='&#xe623;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='1793edcf1067458d8262e753e3f6a5f3');
UPDATE `t_menu` SET `id`='a6c79054f004491e8dbd0dbdf84d91be', `name`='工资代扣代缴报表', `pid`='3cf7cf615f49401f997277c62e267a26', `description`='', `pageurl`='/gzdkdjrecord/list', `menuType`='1', `active`='1', `sortno`='1', `menuIcon`='&#xe623;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='a6c79054f004491e8dbd0dbdf84d91be');
UPDATE `t_menu` SET `id`='bbaba48e1c004f1eab11ab98946c7817', `name`='社保公积金缴纳表', `pid`='3cf7cf615f49401f997277c62e267a26', `description`='', `pageurl`='/tcjnrecord/list', `menuType`='1', `active`='1', `sortno`='2', `menuIcon`='&#xe623;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='bbaba48e1c004f1eab11ab98946c7817');
UPDATE `t_menu` SET `id`='ea7615a6fb5b47698df4597510b05763', `name`='统筹月度标准报表', `pid`='3cf7cf615f49401f997277c62e267a26', `description`='', `pageurl`='/tongchourecord/list', `menuType`='1', `active`='1', `sortno`='0', `menuIcon`='&#xe623;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='ea7615a6fb5b47698df4597510b05763');
-- 2017-08-22 添加资产领用导入菜单
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('902fc6a244a5448f9d209f141dd00238', '资产领用记录导入', '0e0bbf1c784f45d78028c0dcdcee2b9a', '', '/zichanrecord/import', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
-- 新增字段
 alter table t_user add  state VARCHAR(20) COMMENT '0 办理离职 1 生成工资 3 停保';
 -- 更新菜单
UPDATE `t_menu` SET `id`='t_menu_list', `name`='菜单管理', `pid`='system_manager', `description`='', `pageurl`='/system/menu/list/all', `menuType`='1', `active`='1', `sortno`='4', `menuIcon`='&#xe624;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='t_menu_list');
UPDATE `t_menu` SET `id`='t_org_list', `name`='部门管理', `pid`='system_manager', `description`='', `pageurl`='/system/org/list', `menuType`='1', `active`='1', `sortno`='2', `menuIcon`='&#xe624;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='t_org_list');
UPDATE `t_menu` SET `id`='t_role_list', `name`='角色管理', `pid`='system_manager', `description`='', `pageurl`='/system/role/list/all', `menuType`='1', `active`='1', `sortno`='3', `menuIcon`='&#xe624;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='t_role_list');
UPDATE `t_menu` SET `id`='t_user_list', `name`='用户管理', `pid`='system_manager', `description`='', `pageurl`='/system/user/list', `menuType`='1', `active`='1', `sortno`='1', `menuIcon`='&#xe624;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='t_user_list');
-- 离职流程
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('684a1f4179fb4407af441456217c63e5', '员工离职-办理', '676880ed3efc4fd4944339fad8f751d1', '', '/userleave/list', '1', '1', '99', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('02d7b8af5ea24aff84e83a33a3188794', '员工离职查看', '684a1f4179fb4407af441456217c63e5', '', '/userleave/look', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('0b636065b2a64d5fa9b795260b7301de', '查找离职员工信息', '684a1f4179fb4407af441456217c63e5', '', '/userleave/userInfo', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('4d894f9ac5a347c4aefde385a1e05c84', '员工离职办理', '684a1f4179fb4407af441456217c63e5', '', '/userleave/update', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('5091afc4f07549beb2d7d3fd6dc67538', '员工办理离职', '684a1f4179fb4407af441456217c63e5', '', '/userleave/lizhi', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);

-- menu菜单
INSERT INTO `t_role_menu` (`id`, `roleId`, `menuId`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('ea3a0e7234e445e2ab4dcfbf30cb60a7', 'r_10001', '02d7b8af5ea24aff84e83a33a3188794', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` (`id`, `roleId`, `menuId`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('bd584eec4b1640e5b3c684079c3ef3a6', 'r_10001', '0b636065b2a64d5fa9b795260b7301de', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` (`id`, `roleId`, `menuId`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('d0e662cc5d2a4c7698f01d75a9ab1ffd', 'r_10001', '4d894f9ac5a347c4aefde385a1e05c84', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` (`id`, `roleId`, `menuId`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('61c6bfa98daa44668cf679da64791b02', 'r_10001', '5091afc4f07549beb2d7d3fd6dc67538', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_role_menu` (`id`, `roleId`, `menuId`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('f090090ea939480cbcb341828222f3d8', 'r_10001', '684a1f4179fb4407af441456217c63e5', NULL, NULL, NULL, NULL, NULL);

-- 2017-08-28 个人工资条
UPDATE `t_menu` SET `id`='c4e907f423f94a1d83830a48c385f20d', `name`='个人工资条', `pid`='d96b10327c4c4d6692ecb3b5432de2dd', `description`='', `pageurl`='/user/salary/list/me', `menuType`='1', `active`='1', `sortno`='80', `menuIcon`='&#xe623;', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='c4e907f423f94a1d83830a48c385f20d');
UPDATE `t_menu` SET `id`='03fe96e7d8cb46f6adb089472cce52a3', `name`='个人工资条导出', `pid`='c4e907f423f94a1d83830a48c385f20d', `description`='', `pageurl`='/user/salary/list/export/me', `menuType`='0', `active`='1', `sortno`='50', `menuIcon`='', `bak1`=NULL, `bak2`=NULL, `bak3`=NULL, `bak4`=NULL, `bak5`=NULL WHERE (`id`='03fe96e7d8cb46f6adb089472cce52a3');
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('21f697c21d094668b8cd98ecd5421594', '员工工资导出', '46dfefd4089f4f66811238d1289d87dc', '', '/user/salary/list/export/me', '0', '1', NULL, '', NULL, NULL, NULL, NULL, NULL);
INSERT INTO `t_menu` (`id`, `name`, `pid`, `description`, `pageurl`, `menuType`, `active`, `sortno`, `menuIcon`, `bak1`, `bak2`, `bak3`, `bak4`, `bak5`) VALUES ('46dfefd4089f4f66811238d1289d87dc', '员工工资查询', 'd96b10327c4c4d6692ecb3b5432de2dd', '', '/user/salary/list/yuangong', '1', '1', '80', '&#xe623;', NULL, NULL, NULL, NULL, NULL);
