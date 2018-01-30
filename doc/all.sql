/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.20 : Database - SPONGE
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

/*Table structure for table `T_AUTH_RESOURCE` */

DROP TABLE IF EXISTS `T_AUTH_RESOURCE`;

CREATE TABLE `T_AUTH_RESOURCE` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL,
  `FPATH` varchar(128) NOT NULL,
  `FTYPE` enum('NAV','ENTER','API') NOT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_RESOURCE` */

insert  into `T_AUTH_RESOURCE`(`FID`,`FNAME`,`FPATH`,`FTYPE`) values 
(1,'查看区域列表','/area/listall','API'),
(2,'获取所有监控状态菜单','/company/status/listall','API'),
(3,'查看实时数据','/data/realtime','API'),
(4,'查看企业列表','/company/listall','API'),
(5,'获取监控中的污染物列表','/pollutant/listshow','API'),
(6,'查看历史数据','/data/history','API'),
(7,'查看企业车间列表','/company/workshop/list','API'),
(8,'查看污染物配置','/pollutant/listall','API');

/*Table structure for table `T_AUTH_ROLE` */

DROP TABLE IF EXISTS `T_AUTH_ROLE`;

CREATE TABLE `T_AUTH_ROLE` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FNAME` varchar(32) NOT NULL,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_ROLE` */

insert  into `T_AUTH_ROLE`(`FID`,`FNAME`) values 
(1,'超级管理员');

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_USER` */

insert  into `T_AUTH_USER`(`FID`,`FUSERNAME`,`FPASSWORD`,`FNICKNAME`,`FTOKEN`,`FTOKEN_EXPIRE`,`FCREATE_TIME`) values 
(1,'admin','21232f297a57a5a743894a0e4a801fc3','admin','6714ae50-facc-4b79-ba59-8dd408efff8c','2018-01-31 01:11:46','1970-01-01 08:00:00');

/*Table structure for table `T_AUTH_USER_ROLE_MAPPING` */

DROP TABLE IF EXISTS `T_AUTH_USER_ROLE_MAPPING`;

CREATE TABLE `T_AUTH_USER_ROLE_MAPPING` (
  `FUSER_ID` int(10) unsigned NOT NULL,
  `FROLE_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FUSER_ID`,`FROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_AUTH_USER_ROLE_MAPPING` */

insert  into `T_AUTH_USER_ROLE_MAPPING`(`FUSER_ID`,`FROLE_ID`) values 
(1,1);

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
  `FSHOW` tinyint(4) NOT NULL,
  `FORDER` smallint(6) NOT NULL,
  PRIMARY KEY (`FID`),
  UNIQUE KEY `UDX_ORDER` (`FORDER`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COMMENT='污染物配置';

/*Data for the table `T_POLLUTANT` */

insert  into `T_POLLUTANT`(`FID`,`FNAME`,`FSHOW`,`FORDER`) values 
(1,'pH',1,1),
(2,'排放量(m³/h)',1,2),
(3,'生产用水量(m³/h)',0,4);

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
  `FSTATUS` tinyint(4) NOT NULL COMMENT '状态',
  `FPH` float DEFAULT NULL COMMENT 'PH值',
  `FEMISSION_LOAD` float DEFAULT NULL COMMENT '排放量',
  `FRTD_DATA` text NOT NULL COMMENT '数值型数据',
  `FSTATUS_DATA` text NOT NULL COMMENT '状态型数据',
  `FDATA_PROTOCOL` smallint(5) unsigned NOT NULL COMMENT '数据协议版本',
  `FLMODIFY` datetime NOT NULL,
  PRIMARY KEY (`FWORKSHOP_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_REALTIME_DATA` */

insert  into `T_REALTIME_DATA`(`FWORKSHOP_ID`,`FSTATUS`,`FPH`,`FEMISSION_LOAD`,`FRTD_DATA`,`FSTATUS_DATA`,`FDATA_PROTOCOL`,`FLMODIFY`) values 
(1,1,7.1,109.9,'','',0,'2018-01-03 21:54:15'),
(2,1,6,108.8,'','',0,'2018-01-13 02:57:05');

/*Table structure for table `T_USER` */

DROP TABLE IF EXISTS `T_USER`;

CREATE TABLE `T_USER` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_USER` */

insert  into `T_USER`(`FID`) values 
(1);

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
