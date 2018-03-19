CREATE DATABASE  IF NOT EXISTS `smartserve` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `smartserve`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: smartserve
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Table structure for table `omega`
--

DROP TABLE IF EXISTS `omega`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `omega` (
  `omega_id` int(11) NOT NULL AUTO_INCREMENT,
  `xloc` double(30,5) DEFAULT NULL,
  `yloc` double(30,5) DEFAULT NULL,
  PRIMARY KEY (`omega_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `omega`
--

LOCK TABLES `omega` WRITE;
/*!40000 ALTER TABLE `omega` DISABLE KEYS */;
INSERT INTO `omega` VALUES (1,1.00000,1.00000);
/*!40000 ALTER TABLE `omega` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `return_rate`
--

DROP TABLE IF EXISTS `return_rate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `return_rate` (
  `user_id` int(11) DEFAULT NULL,
  `shot_id` int(11) DEFAULT NULL,
  `returned` tinyint(1) DEFAULT NULL,
  `time_stamp` datetime DEFAULT NULL,
  KEY `user_id` (`user_id`),
  KEY `shot_id` (`shot_id`),
  CONSTRAINT `return_rate_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `return_rate_ibfk_2` FOREIGN KEY (`shot_id`) REFERENCES `shot_type` (`shot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `return_rate`
--

LOCK TABLES `return_rate` WRITE;
/*!40000 ALTER TABLE `return_rate` DISABLE KEYS */;
INSERT INTO `return_rate` VALUES (25,1,1,'1969-12-31 07:00:00'),(25,1,1,'1969-12-31 07:00:00'),(25,1,1,'1969-12-31 07:00:00'),(25,1,1,'1969-12-31 07:00:00');
/*!40000 ALTER TABLE `return_rate` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shot_type`
--

DROP TABLE IF EXISTS `shot_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shot_type` (
  `shot_id` int(11) NOT NULL AUTO_INCREMENT,
  `zone_id` int(11) DEFAULT NULL,
  `omega_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`shot_id`),
  KEY `zone_id` (`zone_id`),
  KEY `omega_id` (`omega_id`),
  CONSTRAINT `shot_type_ibfk_1` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`),
  CONSTRAINT `shot_type_ibfk_2` FOREIGN KEY (`omega_id`) REFERENCES `omega` (`omega_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shot_type`
--

LOCK TABLES `shot_type` WRITE;
/*!40000 ALTER TABLE `shot_type` DISABLE KEYS */;
INSERT INTO `shot_type` VALUES (1,1,1);
/*!40000 ALTER TABLE `shot_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (11,'user','hello'),(12,'44','Harit'),(13,'44','Harit'),(14,'44','Harit'),(15,'44','Harit'),(16,'44','Harit'),(17,'tseting','Sharon'),(18,'Sharon','plzwork'),(19,'Sharon','plzwork'),(20,'Sharon','plzwork'),(21,'Sharon','plzwork'),(22,'Sharon','plzwork'),(23,'Sharon','plzwork'),(24,'Sharon','plzwork'),(25,'Sharon','plzwork'),(26,'Sharon','plzwork'),(27,'Sharon','plzwork'),(28,'Sharon','plzwork'),(29,'Sharon','apples');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zone`
--

DROP TABLE IF EXISTS `zone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `zone` (
  `zone_id` int(11) NOT NULL AUTO_INCREMENT,
  `omega` double(30,5) DEFAULT NULL,
  `velocity` double(30,5) DEFAULT NULL,
  PRIMARY KEY (`zone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zone`
--

LOCK TABLES `zone` WRITE;
/*!40000 ALTER TABLE `zone` DISABLE KEYS */;
INSERT INTO `zone` VALUES (1,1.00000,1.00000);
/*!40000 ALTER TABLE `zone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'smartserve'
--

--
-- Dumping routines for database 'smartserve'
--
/*!50003 DROP PROCEDURE IF EXISTS `login_proc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `login_proc`(
	uname VARCHAR(225),
    pass VARCHAR(225)
)
BEGIN
	SELECT user_id FROM user
    WHERE uname = user_name and pass = password;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `returned` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `returned`(
    user int, 
    shot int, 
    returned boolean, 
    time_stamp datetime
)
BEGIN
	INSERT INTO return_rate(user_id, shot_id, returned, time_stamp)
	VALUES (user,shot,returned,time_stamp);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `signup_proc` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `signup_proc`(
	u_name VARCHAR(255),
	pass VARCHAR(255)
)
BEGIN
	INSERT INTO user(user_name,password)
	VALUES (u_name,pass);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-01-27 13:33:56
