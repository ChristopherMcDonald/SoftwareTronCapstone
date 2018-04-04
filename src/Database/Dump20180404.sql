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
-- Table structure for table `orientation`
--

DROP TABLE IF EXISTS `orientation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `orientation` (
  `orient_id` int(11) NOT NULL AUTO_INCREMENT,
  `roll` int(11) NOT NULL,
  `pitch` int(11) NOT NULL,
  PRIMARY KEY (`orient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orientation`
--

LOCK TABLES `orientation` WRITE;
/*!40000 ALTER TABLE `orientation` DISABLE KEYS */;
INSERT INTO `orientation` VALUES (1,0,0),(2,0,10),(3,0,20),(4,0,30),(5,90,0),(6,90,10),(7,90,20),(8,90,30),(9,180,0),(10,180,10),(11,180,20),(12,180,30),(13,270,0),(14,270,10),(15,270,20),(16,270,30);
/*!40000 ALTER TABLE `orientation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shot_taken`
--

DROP TABLE IF EXISTS `shot_taken`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shot_taken` (
  `user_id` int(11) NOT NULL,
  `shot_id` int(11) NOT NULL,
  `returned` tinyint(1) NOT NULL,
  `time_stamp` datetime NOT NULL,
  KEY `user_id_idx` (`user_id`),
  KEY `shot_id_idx` (`shot_id`),
  CONSTRAINT `shot_id` FOREIGN KEY (`shot_id`) REFERENCES `shot_type` (`shot_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shot_taken`
--

LOCK TABLES `shot_taken` WRITE;
/*!40000 ALTER TABLE `shot_taken` DISABLE KEYS */;
/*!40000 ALTER TABLE `shot_taken` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shot_type`
--

DROP TABLE IF EXISTS `shot_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `shot_type` (
  `shot_id` int(11) NOT NULL AUTO_INCREMENT,
  `orient_id` int(11) NOT NULL,
  `zone_id` int(11) NOT NULL,
  PRIMARY KEY (`shot_id`),
  KEY `orient_id_idx` (`orient_id`),
  KEY `zone_id_idx` (`zone_id`),
  CONSTRAINT `orient_id` FOREIGN KEY (`orient_id`) REFERENCES `orientation` (`orient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `zone_id` FOREIGN KEY (`zone_id`) REFERENCES `zone` (`zone_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=257 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shot_type`
--

LOCK TABLES `shot_type` WRITE;
/*!40000 ALTER TABLE `shot_type` DISABLE KEYS */;
INSERT INTO `shot_type` VALUES (1,1,2),(2,1,3),(3,1,4),(4,1,5),(5,1,6),(6,1,7),(7,1,8),(8,1,9),(9,1,10),(10,1,11),(11,1,12),(12,1,13),(13,1,14),(14,1,15),(15,1,16),(16,1,17),(17,2,2),(18,2,3),(19,2,4),(20,2,5),(21,2,6),(22,2,7),(23,2,8),(24,2,9),(25,2,10),(26,2,11),(27,2,12),(28,2,13),(29,2,14),(30,2,15),(31,2,16),(32,2,17),(33,3,2),(34,3,3),(35,3,4),(36,3,5),(37,3,6),(38,3,7),(39,3,8),(40,3,9),(41,3,10),(42,3,11),(43,3,12),(44,3,13),(45,3,14),(46,3,15),(47,3,16),(48,3,17),(49,4,2),(50,4,3),(51,4,4),(52,4,5),(53,4,6),(54,4,7),(55,4,8),(56,4,9),(57,4,10),(58,4,11),(59,4,12),(60,4,13),(61,4,14),(62,4,15),(63,4,16),(64,4,17),(65,5,2),(66,5,3),(67,5,4),(68,5,5),(69,5,6),(70,5,7),(71,5,8),(72,5,9),(73,5,10),(74,5,11),(75,5,12),(76,5,13),(77,5,14),(78,5,15),(79,5,16),(80,5,17),(81,6,2),(82,6,3),(83,6,4),(84,6,5),(85,6,6),(86,6,7),(87,6,8),(88,6,9),(89,6,10),(90,6,11),(91,6,12),(92,6,13),(93,6,14),(94,6,15),(95,6,16),(96,6,17),(97,7,2),(98,7,3),(99,7,4),(100,7,5),(101,7,6),(102,7,7),(103,7,8),(104,7,9),(105,7,10),(106,7,11),(107,7,12),(108,7,13),(109,7,14),(110,7,15),(111,7,16),(112,7,17),(113,8,2),(114,8,3),(115,8,4),(116,8,5),(117,8,6),(118,8,7),(119,8,8),(120,8,9),(121,8,10),(122,8,11),(123,8,12),(124,8,13),(125,8,14),(126,8,15),(127,8,16),(128,8,17),(129,9,2),(130,9,3),(131,9,4),(132,9,5),(133,9,6),(134,9,7),(135,9,8),(136,9,9),(137,9,10),(138,9,11),(139,9,12),(140,9,13),(141,9,14),(142,9,15),(143,9,16),(144,9,17),(145,10,2),(146,10,3),(147,10,4),(148,10,5),(149,10,6),(150,10,7),(151,10,8),(152,10,9),(153,10,10),(154,10,11),(155,10,12),(156,10,13),(157,10,14),(158,10,15),(159,10,16),(160,10,17),(161,11,2),(162,11,3),(163,11,4),(164,11,5),(165,11,6),(166,11,7),(167,11,8),(168,11,9),(169,11,10),(170,11,11),(171,11,12),(172,11,13),(173,11,14),(174,11,15),(175,11,16),(176,11,17),(177,12,2),(178,12,3),(179,12,4),(180,12,5),(181,12,6),(182,12,7),(183,12,8),(184,12,9),(185,12,10),(186,12,11),(187,12,12),(188,12,13),(189,12,14),(190,12,15),(191,12,16),(192,12,17),(193,13,2),(194,13,3),(195,13,4),(196,13,5),(197,13,6),(198,13,7),(199,13,8),(200,13,9),(201,13,10),(202,13,11),(203,13,12),(204,13,13),(205,13,14),(206,13,15),(207,13,16),(208,13,17),(209,14,2),(210,14,3),(211,14,4),(212,14,5),(213,14,6),(214,14,7),(215,14,8),(216,14,9),(217,14,10),(218,14,11),(219,14,12),(220,14,13),(221,14,14),(222,14,15),(223,14,16),(224,14,17),(225,15,2),(226,15,3),(227,15,4),(228,15,5),(229,15,6),(230,15,7),(231,15,8),(232,15,9),(233,15,10),(234,15,11),(235,15,12),(236,15,13),(237,15,14),(238,15,15),(239,15,16),(240,15,17),(241,16,2),(242,16,3),(243,16,4),(244,16,5),(245,16,6),(246,16,7),(247,16,8),(248,16,9),(249,16,10),(250,16,11),(251,16,12),(252,16,13),(253,16,14),(254,16,15),(255,16,16),(256,16,17);
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
  `password` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (35,'SharonPassword','sharon@email.ca'),(36,'HaritPassword','harit@email.ca'),(37,'NishPassword','nish@email.ca'),(38,'SamPassword','sam@email.ca'),(39,'JaredPassword','jared@email.ca'),(40,'ChrisPassword','chris@email.ca'),(41,'JanakPassword','janak@email.ca');
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
  `xloc` double NOT NULL,
  `yloc` double NOT NULL,
  PRIMARY KEY (`zone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zone`
--

LOCK TABLES `zone` WRITE;
/*!40000 ALTER TABLE `zone` DISABLE KEYS */;
INSERT INTO `zone` VALUES (2,0.191,0.171),(3,0.191,0.514),(4,0.191,0.856),(5,0.191,1.199),(6,0.572,0.171),(7,0.572,0.514),(8,0.572,0.856),(9,0.572,1.199),(10,0.953,0.171),(11,0.953,0.514),(12,0.953,0.856),(13,0.953,1.199),(14,1.334,0.171),(15,1.334,0.514),(16,1.334,0.856),(17,1.334,1.199);
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
    WHERE uname = email and pass = password;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `next_shot` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `next_shot`(
	IN shot int,
    OUT x double,
    OUT y double,
    OUT r double,
    OUT p double
)
BEGIN

SELECT zone.xloc,zone.yloc, orientation.roll, orientation.pitch INTO x,y,r,p FROM shot_type
JOIN zone ON shot_type.zone_id = zone.zone_id
JOIN orientation ON shot_type.orient_id = orientation.orient_id
WHERE shot = shot_id;


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
	INSERT INTO shot_taken(user_id, shot_id, returned, time_stamp)
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
	INSERT INTO user(email,password)
	VALUES (u_name,pass);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `statistics` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `statistics`(
    IN `user_id_input` INT,
    IN `zones_input` VARCHAR(64),
    IN `roll_initial` DOUBLE,
    IN `roll_final` DOUBLE,
    IN `pitch_initial` DOUBLE,
    IN `pitch_final` DOUBLE,
    IN `date_initial` DATETIME,
    IN `date_final` DATETIME
)
BEGIN
    set @base = 'SELECT zone.zone_id, orientation.roll, orientation.pitch, shot_taken.returned, shot_taken.time_stamp
    FROM shot_type
    JOIN orientation ON shot_type.orient_id = orientation.orient_id
    JOIN zone ON shot_type.zone_id = zone.zone_id
    JOIN shot_taken ON shot_type.shot_id = shot_taken.shot_id
    WHERE 1 = 1';

    IF !(user_id_input IS NULL) THEN
        set @base = concat(@base, ' AND (shot_taken.user_id = ',user_id_input,')');
    END IF;

    IF !(zones_input IS NULL) THEN
        set @base = concat(@base, ' AND (shot_type.zone_id IN (' , zones_input , '-1))');
    END IF;

    IF !(roll_initial IS NULL OR roll_final IS NULL) THEN
        set @base = concat(@base, ' AND (orientation.roll BETWEEN ',roll_initial,' AND ',roll_final,')');
    END IF;

    IF !(pitch_initial IS NULL OR pitch_final IS NULL) THEN
        set @base = concat(@base, ' AND (orientation.pitch BETWEEN ',pitch_initial,' AND ',pitch_final,')');
    END IF;

    IF !(date_initial IS NULL OR date_final IS NULL) THEN
        set @base = concat(@base, ' AND (shot_taken.time_stamp BETWEEN "',date_initial,'" AND "',date_final,'")');
    END IF;

    PREPARE stmt FROM @base;
    EXECUTE stmt;

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

-- Dump completed on 2018-04-04 12:55:09
