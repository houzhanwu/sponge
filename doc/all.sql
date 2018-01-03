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

/*Table structure for table `T_COMPANY_REALTIME_DATA` */

DROP TABLE IF EXISTS `T_COMPANY_REALTIME_DATA`;

CREATE TABLE `T_COMPANY_REALTIME_DATA` (
  `FCOMPANY_ID` int(10) unsigned NOT NULL,
  `FPH` float DEFAULT NULL COMMENT 'PH值',
  `FEMISSION_LOAD` float DEFAULT NULL COMMENT '排放量',
  `FLMODIFY` datetime NOT NULL,
  PRIMARY KEY (`FCOMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_COMPANY_REALTIME_DATA` */

insert  into `T_COMPANY_REALTIME_DATA`(`FCOMPANY_ID`,`FPH`,`FEMISSION_LOAD`,`FLMODIFY`) values 
(1,7,109.9,'2018-01-03 21:54:15');

/*Table structure for table `T_DEVICE` */

DROP TABLE IF EXISTS `T_DEVICE`;

CREATE TABLE `T_DEVICE` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `FMN` char(24) NOT NULL,
  `FCOMPANY_ID` int(10) unsigned NOT NULL,
  PRIMARY KEY (`FID`),
  UNIQUE KEY `UDX_MN` (`FMN`),
  KEY `IDX_COMPANYID` (`FCOMPANY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COMMENT='现场机';

/*Data for the table `T_DEVICE` */

insert  into `T_DEVICE`(`FID`,`FMN`,`FCOMPANY_ID`) values 
(1,'66666660000111',1);

/*Table structure for table `T_USER` */

DROP TABLE IF EXISTS `T_USER`;

CREATE TABLE `T_USER` (
  `FID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`FID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `T_USER` */

insert  into `T_USER`(`FID`) values 
(1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
