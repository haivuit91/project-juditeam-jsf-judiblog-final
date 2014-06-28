CREATE DATABASE  IF NOT EXISTS `db_judiblog` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `db_judiblog`;
-- MySQL dump 10.13  Distrib 5.6.17, for Win32 (x86)
--
-- Host: localhost    Database: db_judiblog
-- ------------------------------------------------------
-- Server version	5.6.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `tbl_category`
--

DROP TABLE IF EXISTS `tbl_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_category` (
  `catID` int(11) NOT NULL AUTO_INCREMENT,
  `catName` varchar(45) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`catID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_category`
--

LOCK TABLES `tbl_category` WRITE;
/*!40000 ALTER TABLE `tbl_category` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_post`
--

DROP TABLE IF EXISTS `tbl_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_post` (
  `postID` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `content` varchar(10000) NOT NULL,
  `imagePath` varchar(100) DEFAULT NULL,
  `postDate` date NOT NULL,
  `userID` int(11) NOT NULL,
  `catID` int(11) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`postID`),
  KEY `userID_idx` (`userID`),
  KEY `fk_Cat` (`catID`),
  CONSTRAINT `fk_Cat` FOREIGN KEY (`catID`) REFERENCES `tbl_category` (`catID`),
  CONSTRAINT `fk_User` FOREIGN KEY (`userID`) REFERENCES `tbl_user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_post`
--

LOCK TABLES `tbl_post` WRITE;
/*!40000 ALTER TABLE `tbl_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_project`
--

DROP TABLE IF EXISTS `tbl_project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_project` (
  `projectID` int(11) NOT NULL AUTO_INCREMENT,
  `projectName` varchar(100) NOT NULL,
  `description` varchar(200) NOT NULL,
  `startDate` date NOT NULL,
  `duration` int(11) DEFAULT NULL,
  `typeID` int(11) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`projectID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_project`
--

LOCK TABLES `tbl_project` WRITE;
/*!40000 ALTER TABLE `tbl_project` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_project_user`
--

DROP TABLE IF EXISTS `tbl_project_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_project_user` (
  `project_userID` int(11) NOT NULL AUTO_INCREMENT,
  `userID` int(11) NOT NULL,
  `projectID` int(11) NOT NULL,
  `isCreater` tinyint(1) NOT NULL,
  PRIMARY KEY (`project_userID`),
  KEY `userID_idx` (`userID`),
  KEY `projectID_idx` (`projectID`),
  CONSTRAINT `projectID` FOREIGN KEY (`projectID`) REFERENCES `tbl_project` (`projectID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `tbl_user` (`userID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_project_user`
--

LOCK TABLES `tbl_project_user` WRITE;
/*!40000 ALTER TABLE `tbl_project_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `tbl_project_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_role`
--

DROP TABLE IF EXISTS `tbl_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_role` (
  `roleID` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(45) NOT NULL,
  `imagePath` varchar(105) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`roleID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_role`
--

LOCK TABLES `tbl_role` WRITE;
/*!40000 ALTER TABLE `tbl_role` DISABLE KEYS */;
INSERT INTO `tbl_role` VALUES (1,'Administrator',NULL,1),(2,'Super Mod',NULL,1),(3,'Mod',NULL,1),(4,'Member',NULL,1);
/*!40000 ALTER TABLE `tbl_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_type`
--

DROP TABLE IF EXISTS `tbl_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_type` (
  `typeID` int(11) NOT NULL AUTO_INCREMENT,
  `typeName` varchar(60) NOT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`typeID`),
  UNIQUE KEY `typeName_UNIQUE` (`typeName`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_type`
--

LOCK TABLES `tbl_type` WRITE;
/*!40000 ALTER TABLE `tbl_type` DISABLE KEYS */;
INSERT INTO `tbl_type` VALUES (1,'Java Web Application',1),(2,'J2SE',1),(3,'Winform .NET',1),(4,'ASP.NET',1),(5,'iOS',1),(6,'Android',1),(7,'Windows Phone',1);
/*!40000 ALTER TABLE `tbl_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tbl_user` (
  `userID` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(45) NOT NULL,
  `pwd` varchar(45) NOT NULL,
  `fullName` varchar(45) NOT NULL,
  `birthday` date DEFAULT NULL,
  `gender` tinyint(1) DEFAULT NULL,
  `idCard` varchar(12) DEFAULT NULL,
  `userAddress` varchar(105) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `imagePath` varchar(105) DEFAULT NULL,
  `roleID` int(11) NOT NULL,
  `idActive` varchar(45) DEFAULT NULL,
  `isActive` tinyint(1) NOT NULL,
  PRIMARY KEY (`userID`),
  UNIQUE KEY `userName_UNIQUE` (`userName`),
  KEY `roleID_idx` (`roleID`),
  CONSTRAINT `roleID` FOREIGN KEY (`roleID`) REFERENCES `tbl_role` (`roleID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbl_user`
--

LOCK TABLES `tbl_user` WRITE;
/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
INSERT INTO `tbl_user` VALUES (1,'admin','admin','Phạm Đình Công','1991-11-24',1,'173549176','Dân Quyền - Triệu Sơn - Thanh Hóa','congpd.itedu@gmail.com','0982207386',NULL,1,NULL,1),(2,'duynn','duynn','Nguyễn Ngọc Duy','1990-03-24',1,'201223452','Núi Thành - Quảng Nam','duynn.itedu@gmail.com','0906252112',NULL,1,NULL,1);
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-06-24 20:12:55
