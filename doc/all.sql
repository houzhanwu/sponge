/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.6.32 : Database - SPONGE
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`SPONGE` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `SPONGE`;

/*Table structure for table `T_AREA` */

DROP TABLE IF EXISTS `T_AREA`;

CREATE TABLE `T_AREA` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='区域';

/*Data for the table `T_AREA` */

insert  into `T_AREA`(`FID`,`FNAME`) values 
(1,'禅城区'),
(2,'南海区');

/*Table structure for table `T_AUTH_MODULE` */

DROP TABLE IF EXISTS `T_AUTH_MODULE`;

CREATE TABLE `T_AUTH_MODULE` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL,
  `FKEY` varchar(64) DEFAULT NULL,
  `FGROUP` int(10) unsigned NOT NULL,
  `FORDER` int(11) NOT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_MODULE` */

insert  into `T_AUTH_MODULE`(`FID`,`FNAME`,`FKEY`,`FGROUP`,`FORDER`) values 
(1,'查看实时数据','realtime',1,1),
(2,'查看历史数据','history',2,1),
(3,'查看污染物配置','pollutant',3,1),
(4,'修改污染物配置','edit_pollutant',3,2),
(5,'查看角色管理','role',4,1),
(6,'修改角色配置','edit_role',4,2);

/*Table structure for table `T_AUTH_MODULE_GROUP` */

DROP TABLE IF EXISTS `T_AUTH_MODULE_GROUP`;

CREATE TABLE `T_AUTH_MODULE_GROUP` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL,
  `FORDER` int(11) NOT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_MODULE_GROUP` */

insert  into `T_AUTH_MODULE_GROUP`(`FID`,`FNAME`,`FORDER`) values 
(1,'实时数据',1),
(2,'历史数据',2),
(3,'污染物配置',3),
(4,'权限管理',4);

/*Table structure for table `T_AUTH_MODULE_RESOURCE_MAPPING` */

DROP TABLE IF EXISTS `T_AUTH_MODULE_RESOURCE_MAPPING`;

CREATE TABLE `T_AUTH_MODULE_RESOURCE_MAPPING` (
  `FMODULE_ID` int(10) unsigned NOT NULL,
  `FRESOURCE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FMODULE_ID`,`FRESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_MODULE_RESOURCE_MAPPING` */

insert  into `T_AUTH_MODULE_RESOURCE_MAPPING`(`FMODULE_ID`,`FRESOURCE_ID`) values 
(1,1),
(1,2),
(1,3),
(2,4),
(2,5),
(2,6),
(2,7),
(3,8),
(4,9),
(4,10),
(4,11),
(4,12),
(5,13),
(5,17),
(5,18),
(5,20),
(6,14),
(6,15),
(6,16),
(6,19),
(6,21),
(6,22);

/*Table structure for table `T_AUTH_RESOURCE` */

DROP TABLE IF EXISTS `T_AUTH_RESOURCE`;

CREATE TABLE `T_AUTH_RESOURCE` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL,
  `FPATH` varchar(128) NOT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_RESOURCE` */

insert  into `T_AUTH_RESOURCE`(`FID`,`FNAME`,`FPATH`) values 
(1,'查看区域列表','/area/listall'),
(2,'获取所有监控状态菜单','/company/status/listall'),
(3,'查看实时数据','/data/realtime'),
(4,'查看企业列表','/company/listall'),
(5,'获取监控中的污染物列表','/pollutant/listshow'),
(6,'查看历史数据','/data/history'),
(7,'查看企业车间列表','/company/workshop/list'),
(8,'查看污染物配置','/pollutant/listall'),
(9,'新增污染物配置','/pollutant/add'),
(10,'修改污染物配置','/pollutant/update'),
(11,'删除污染物配置','/pollutant/delete'),
(12,'排序污染物','/pollutant/reorder'),
(13,'查看角色列表','/role/listall'),
(14,'新增角色','/role/add'),
(15,'修改角色信息','/role/update'),
(16,'删除角色','/role/delete'),
(17,'查询角色权限','/role/listModule'),
(18,'查询所有权限','/module/listByGroup'),
(19,'修改角色权限','/role/updateModule'),
(20,'查看角色用户','/role/listUser'),
(21,'新增角色用户','/role/addUser'),
(22,'移除角色用户','/role/removeUser');

/*Table structure for table `T_AUTH_ROLE` */

DROP TABLE IF EXISTS `T_AUTH_ROLE`;

CREATE TABLE `T_AUTH_ROLE` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL,
  `FREMARKS` text,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_ROLE` */

insert  into `T_AUTH_ROLE`(`FID`,`FNAME`,`FREMARKS`) values 
(1,'超级管理员',NULL),
(3,'实时数据运营',NULL);

/*Table structure for table `T_AUTH_ROLE_MODULE_MAPPING` */

DROP TABLE IF EXISTS `T_AUTH_ROLE_MODULE_MAPPING`;

CREATE TABLE `T_AUTH_ROLE_MODULE_MAPPING` (
  `FROLE_ID` int(10) unsigned NOT NULL,
  `FMODULE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FROLE_ID`,`FMODULE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_ROLE_MODULE_MAPPING` */

insert  into `T_AUTH_ROLE_MODULE_MAPPING`(`FROLE_ID`,`FMODULE_ID`) values 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(3,1),
(3,2),
(3,3),
(3,5);

/*Table structure for table `T_AUTH_ROLE_RESOURCE_MAPPING` */

DROP TABLE IF EXISTS `T_AUTH_ROLE_RESOURCE_MAPPING`;

CREATE TABLE `T_AUTH_ROLE_RESOURCE_MAPPING` (
  `FROLE_ID` int(10) unsigned NOT NULL,
  `FRESOURCE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FROLE_ID`,`FRESOURCE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_ROLE_RESOURCE_MAPPING` */

insert  into `T_AUTH_ROLE_RESOURCE_MAPPING`(`FROLE_ID`,`FRESOURCE_ID`) values 
(1,1),
(1,2),
(1,3),
(1,4),
(1,5),
(1,6),
(1,7),
(1,8);

/*Table structure for table `T_AUTH_USER` */

DROP TABLE IF EXISTS `T_AUTH_USER`;

CREATE TABLE `T_AUTH_USER` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FUSERNAME` varchar(32) NOT NULL,
  `FPASSWORD` char(32) NOT NULL,
  `FNICKNAME` varchar(32) NOT NULL,
  `FTOKEN` char(36) NOT NULL,
  `FTOKEN_EXPIRE` datetime NOT NULL DEFAULT '1970-01-01 08:00:00',
  `FCREATE_TIME` datetime NOT NULL DEFAULT '1970-01-01 08:00:00',
  PRIMARY KEY (`FID`),
  UNIQUE KEY `UDX_USERNAME` (`FUSERNAME`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_USER` */

insert  into `T_AUTH_USER`(`FID`,`FUSERNAME`,`FPASSWORD`,`FNICKNAME`,`FTOKEN`,`FTOKEN_EXPIRE`,`FCREATE_TIME`) values 
(1,'admin','21232f297a57a5a743894a0e4a801fc3','admin','bb1b0569-7563-4383-8ce8-cc67a65ba6f6','2018-02-08 15:41:02','1970-01-01 08:00:00'),
(2,'create','81dc9bdb52d04dc20036dbd8313ed055','create','','1970-01-01 08:00:00','1970-01-01 08:00:00'),
(3,'user1','81dc9bdb52d04dc20036dbd8313ed055','user1','','1970-01-01 08:00:00','1970-01-01 08:00:00'),
(4,'user2','e10adc3949ba59abbe56e057f20f883e','','','1970-01-01 08:00:00','2018-02-05 17:03:57'),
(5,'user3','e10adc3949ba59abbe56e057f20f883e','','','1970-01-01 08:00:00','2018-02-05 17:05:48'),
(6,'user4','e10adc3949ba59abbe56e057f20f883e','','','1970-01-01 08:00:00','2018-02-05 17:09:31'),
(7,'user5','e10adc3949ba59abbe56e057f20f883e','','5f68a834-d9ea-42d4-a1ca-c745a5a6d28a','2018-02-06 18:32:52','2018-02-05 17:10:25');

/*Table structure for table `T_AUTH_USER_ROLE_MAPPING` */

DROP TABLE IF EXISTS `T_AUTH_USER_ROLE_MAPPING`;

CREATE TABLE `T_AUTH_USER_ROLE_MAPPING` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FUSER_ID` int(10) unsigned NOT NULL,
  `FROLE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FID`),
  UNIQUE KEY `UDX_USERID_ROLEID` (`FUSER_ID`,`FROLE_ID`),
  UNIQUE KEY `UDX_ROLEID_USERID` (`FROLE_ID`,`FUSER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_USER_ROLE_MAPPING` */

insert  into `T_AUTH_USER_ROLE_MAPPING`(`FID`,`FUSER_ID`,`FROLE_ID`) values 
(1,1,1),
(2,2,3),
(5,3,3),
(6,7,3);

/*Table structure for table `T_COMPANY` */

DROP TABLE IF EXISTS `T_COMPANY`;

CREATE TABLE `T_COMPANY` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL COMMENT '名称',
  `FAREA_ID` int(10) unsigned NOT NULL COMMENT '区域id',
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='公司';

/*Data for the table `T_COMPANY` */

insert  into `T_COMPANY`(`FID`,`FNAME`,`FAREA_ID`) values 
(1,'工厂1',1),
(2,'工厂2',1),
(3,'工厂3',2),
(4,'工厂4',2);

/*Table structure for table `T_DEVICE` */

DROP TABLE IF EXISTS `T_DEVICE`;

CREATE TABLE `T_DEVICE` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FMN` char(24) NOT NULL,
  `FWORKSHOP_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FID`),
  UNIQUE KEY `UDX_MN` (`FMN`),
  KEY `IDX_WORKSHOPID` (`FWORKSHOP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='现场机';

/*Data for the table `T_DEVICE` */

insert  into `T_DEVICE`(`FID`,`FMN`,`FWORKSHOP_ID`) values 
(1,'66666660000111',1),
(2,'66666660000112',2);

/*Table structure for table `T_HISTORY_DATA` */

DROP TABLE IF EXISTS `T_HISTORY_DATA`;

CREATE TABLE `T_HISTORY_DATA` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FWORKSHOP_ID` int(10) unsigned NOT NULL COMMENT '车间ID',
  `FDATETIME` datetime NOT NULL COMMENT '记录时间',
  `FCOMPANY_ID` int(10) unsigned NOT NULL COMMENT '企业ID',
  `FRTD_DATA` text NOT NULL COMMENT '数值型数据',
  `FSTATUS_DATA` text NOT NULL COMMENT '状态型数据',
  `FDATA_PROTOCOL` smallint(5) unsigned NOT NULL COMMENT '数据协议版本',
  PRIMARY KEY (`FID`),
  UNIQUE KEY `UDX_DATETIME_COMPANYID_WORKSHOPID` (`FDATETIME`,`FCOMPANY_ID`,`FWORKSHOP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_HISTORY_DATA` */

insert  into `T_HISTORY_DATA`(`FID`,`FWORKSHOP_ID`,`FDATETIME`,`FCOMPANY_ID`,`FRTD_DATA`,`FSTATUS_DATA`,`FDATA_PROTOCOL`) values 
(1,1,'2018-01-07 01:40:00',1,'{\"ph\":7,\"emissionLoad\":100.99}','',1),
(2,1,'2018-01-07 01:45:00',1,'{\"ph\":7.1,\"emissionLoad\":100.1}','',1),
(3,2,'2018-01-14 01:50:00',1,'{\"ph\":6.9,\"emissionLoad\":109.1}','',1),
(4,3,'2018-01-14 02:05:00',2,'{\"ph\":7,\"emissionLoad\":100}','',1);

/*Table structure for table `T_POLLUTANT` */

DROP TABLE IF EXISTS `T_POLLUTANT`;

CREATE TABLE `T_POLLUTANT` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL,
  `FTYPE` enum('RTD','STATUS') NOT NULL,
  `FSHOW` tinyint(4) NOT NULL,
  `FORDER` smallint(6) NOT NULL,
  PRIMARY KEY (`FID`),
  UNIQUE KEY `UDX_ORDER` (`FORDER`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='污染物配置';

/*Data for the table `T_POLLUTANT` */

insert  into `T_POLLUTANT`(`FID`,`FNAME`,`FTYPE`,`FSHOW`,`FORDER`) values 
(1,'pH','RTD',1,1),
(2,'排放量(m³/h)','RTD',1,2),
(3,'生产用水量(m³/h)','RTD',0,4);

/*Table structure for table `T_POLLUTANT_MAPPING` */

DROP TABLE IF EXISTS `T_POLLUTANT_MAPPING`;

CREATE TABLE `T_POLLUTANT_MAPPING` (
  `FPOLLUTANT_ID` int(10) unsigned NOT NULL,
  `FFIELD_KEY_HJT212` varchar(32) DEFAULT NULL,
  `FFIELD_KEY_KNT2014` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`FPOLLUTANT_ID`),
  UNIQUE KEY `UDX_FIELDKEYHJT212` (`FFIELD_KEY_HJT212`),
  UNIQUE KEY `UDX_FIELDKEYKNT2014` (`FFIELD_KEY_KNT2014`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='污染物字段映射';

/*Data for the table `T_POLLUTANT_MAPPING` */

insert  into `T_POLLUTANT_MAPPING`(`FPOLLUTANT_ID`,`FFIELD_KEY_HJT212`,`FFIELD_KEY_KNT2014`) values 
(1,'ph','ph'),
(2,'emissionLoad','emissionLoad'),
(3,NULL,NULL);

/*Table structure for table `T_REALTIME_DATA` */

DROP TABLE IF EXISTS `T_REALTIME_DATA`;

CREATE TABLE `T_REALTIME_DATA` (
  `FWORKSHOP_ID` int(10) unsigned NOT NULL,
  `FRTD_DATA` text NOT NULL COMMENT '数值型数据',
  `FSTATUS_DATA` text NOT NULL COMMENT '状态型数据',
  `FDATA_PROTOCOL` smallint(5) unsigned NOT NULL COMMENT '数据协议版本',
  `FLMODIFY` datetime NOT NULL,
  PRIMARY KEY (`FWORKSHOP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_REALTIME_DATA` */

insert  into `T_REALTIME_DATA`(`FWORKSHOP_ID`,`FRTD_DATA`,`FSTATUS_DATA`,`FDATA_PROTOCOL`,`FLMODIFY`) values 
(1,'{\"ph\":7,\"emissionLoad\":100.99}','',1,'2018-01-03 21:54:15'),
(2,'{\"ph\":7.1,\"emissionLoad\":100.1}','',1,'2018-01-13 02:57:05');

/*Table structure for table `T_WORKSHOP` */

DROP TABLE IF EXISTS `T_WORKSHOP`;

CREATE TABLE `T_WORKSHOP` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL COMMENT '名称',
  `FCOMPANY_ID` int(10) unsigned NOT NULL COMMENT '企业ID',
  `FREMARK` varchar(50) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`FID`),
  KEY `IDX_COMPANYID` (`FCOMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_WORKSHOP` */

insert  into `T_WORKSHOP`(`FID`,`FNAME`,`FCOMPANY_ID`,`FREMARK`) values 
(1,'车间101',1,NULL),
(2,'车间102',1,NULL),
(3,'车间103',1,NULL),
(4,'车间104',1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
