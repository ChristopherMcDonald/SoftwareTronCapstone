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
  `angle` double(30,5) DEFAULT NULL,
  `velocity` double(30,5) DEFAULT NULL,
  PRIMARY KEY (`omega_id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `omega`
--

LOCK TABLES `omega` WRITE;
/*!40000 ALTER TABLE `omega` DISABLE KEYS */;
INSERT INTO `omega` VALUES (1,1.00000,1.00000),(2,0.00000,10.00000),(3,0.00000,10.50000),(4,0.00000,11.00000),(5,0.00000,11.50000),(6,0.00000,12.00000),(7,0.00000,12.50000),(8,0.00000,13.00000),(9,0.00000,13.50000),(10,0.00000,14.00000),(11,0.00000,14.50000),(12,0.00000,15.00000),(13,45.00000,10.00000),(14,45.00000,10.50000),(15,45.00000,11.00000),(16,45.00000,11.50000),(17,45.00000,12.00000),(18,45.00000,12.50000),(19,45.00000,13.00000),(20,45.00000,13.50000),(21,45.00000,14.00000),(22,45.00000,14.50000),(23,45.00000,15.00000),(24,90.00000,10.00000),(25,90.00000,10.50000),(26,90.00000,11.00000),(27,90.00000,11.50000),(28,90.00000,12.00000),(29,90.00000,12.50000),(30,90.00000,13.00000),(31,90.00000,13.50000),(32,90.00000,14.00000),(33,90.00000,14.50000),(34,90.00000,15.00000),(35,135.00000,10.00000),(36,135.00000,10.50000),(37,135.00000,11.00000),(38,135.00000,11.50000),(39,135.00000,12.00000),(40,135.00000,12.50000),(41,135.00000,13.00000),(42,135.00000,13.50000),(43,135.00000,14.00000),(44,135.00000,14.50000),(45,135.00000,15.00000),(46,180.00000,10.00000),(47,180.00000,10.50000),(48,180.00000,11.00000),(49,180.00000,11.50000),(50,180.00000,12.00000),(51,180.00000,12.50000),(52,180.00000,13.00000),(53,180.00000,13.50000),(54,180.00000,14.00000),(55,180.00000,14.50000),(56,180.00000,15.00000),(57,225.00000,10.00000),(58,225.00000,10.50000),(59,225.00000,11.00000),(60,225.00000,11.50000),(61,225.00000,12.00000),(62,225.00000,12.50000),(63,225.00000,13.00000),(64,225.00000,13.50000),(65,225.00000,14.00000),(66,225.00000,14.50000),(67,225.00000,15.00000),(68,270.00000,10.00000),(69,270.00000,10.50000),(70,270.00000,11.00000),(71,270.00000,11.50000),(72,270.00000,12.00000),(73,270.00000,12.50000),(74,270.00000,13.00000),(75,270.00000,13.50000),(76,270.00000,14.00000),(77,270.00000,14.50000),(78,270.00000,15.00000),(79,315.00000,10.00000),(80,315.00000,10.50000),(81,315.00000,11.00000),(82,315.00000,11.50000),(83,315.00000,12.00000),(84,315.00000,12.50000),(85,315.00000,13.00000),(86,315.00000,13.50000),(87,315.00000,14.00000),(88,315.00000,14.50000),(89,315.00000,15.00000),(90,360.00000,10.00000),(91,360.00000,10.50000),(92,360.00000,11.00000),(93,360.00000,11.50000),(94,360.00000,12.00000),(95,360.00000,12.50000),(96,360.00000,13.00000),(97,360.00000,13.50000),(98,360.00000,14.00000),(99,360.00000,14.50000),(100,360.00000,15.00000);
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
) ENGINE=InnoDB AUTO_INCREMENT=1588 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shot_type`
--

LOCK TABLES `shot_type` WRITE;
/*!40000 ALTER TABLE `shot_type` DISABLE KEYS */;
INSERT INTO `shot_type` VALUES (1,1,1),(2,2,2),(3,2,3),(4,2,4),(5,2,5),(6,2,6),(7,2,7),(8,2,8),(9,2,9),(10,2,10),(11,2,11),(12,2,12),(13,2,13),(14,2,14),(15,2,15),(16,2,16),(17,2,17),(18,3,2),(19,3,3),(20,3,4),(21,3,5),(22,3,6),(23,3,7),(24,3,8),(25,3,9),(26,3,10),(27,3,11),(28,3,12),(29,3,13),(30,3,14),(31,3,15),(32,3,16),(33,3,17),(34,4,2),(35,4,3),(36,4,4),(37,4,5),(38,4,6),(39,4,7),(40,4,8),(41,4,9),(42,4,10),(43,4,11),(44,4,12),(45,4,13),(46,4,14),(47,4,15),(48,4,16),(49,4,17),(50,5,2),(51,5,3),(52,5,4),(53,5,5),(54,5,6),(55,5,7),(56,5,8),(57,5,9),(58,5,10),(59,5,11),(60,5,12),(61,5,13),(62,5,14),(63,5,15),(64,5,16),(65,5,17),(66,6,2),(67,6,3),(68,6,4),(69,6,5),(70,6,6),(71,6,7),(72,6,8),(73,6,9),(74,6,10),(75,6,11),(76,6,12),(77,6,13),(78,6,14),(79,6,15),(80,6,16),(81,6,17),(82,7,2),(83,7,3),(84,7,4),(85,7,5),(86,7,6),(87,7,7),(88,7,8),(89,7,9),(90,7,10),(91,7,11),(92,7,12),(93,7,13),(94,7,14),(95,7,15),(96,7,16),(97,7,17),(98,8,2),(99,8,3),(100,8,4),(101,8,5),(102,8,6),(103,8,7),(104,8,8),(105,8,9),(106,8,10),(107,8,11),(108,8,12),(109,8,13),(110,8,14),(111,8,15),(112,8,16),(113,8,17),(114,9,2),(115,9,3),(116,9,4),(117,9,5),(118,9,6),(119,9,7),(120,9,8),(121,9,9),(122,9,10),(123,9,11),(124,9,12),(125,9,13),(126,9,14),(127,9,15),(128,9,16),(129,9,17),(130,10,2),(131,10,3),(132,10,4),(133,10,5),(134,10,6),(135,10,7),(136,10,8),(137,10,9),(138,10,10),(139,10,11),(140,10,12),(141,10,13),(142,10,14),(143,10,15),(144,10,16),(145,10,17),(146,11,2),(147,11,3),(148,11,4),(149,11,5),(150,11,6),(151,11,7),(152,11,8),(153,11,9),(154,11,10),(155,11,11),(156,11,12),(157,11,13),(158,11,14),(159,11,15),(160,11,16),(161,11,17),(162,12,2),(163,12,3),(164,12,4),(165,12,5),(166,12,6),(167,12,7),(168,12,8),(169,12,9),(170,12,10),(171,12,11),(172,12,12),(173,12,13),(174,12,14),(175,12,15),(176,12,16),(177,12,17),(178,13,2),(179,13,3),(180,13,4),(181,13,5),(182,13,6),(183,13,7),(184,13,8),(185,13,9),(186,13,10),(187,13,11),(188,13,12),(189,13,13),(190,13,14),(191,13,15),(192,13,16),(193,13,17),(194,14,2),(195,14,3),(196,14,4),(197,14,5),(198,14,6),(199,14,7),(200,14,8),(201,14,9),(202,14,10),(203,14,11),(204,14,12),(205,14,13),(206,14,14),(207,14,15),(208,14,16),(209,14,17),(210,15,2),(211,15,3),(212,15,4),(213,15,5),(214,15,6),(215,15,7),(216,15,8),(217,15,9),(218,15,10),(219,15,11),(220,15,12),(221,15,13),(222,15,14),(223,15,15),(224,15,16),(225,15,17),(226,16,2),(227,16,3),(228,16,4),(229,16,5),(230,16,6),(231,16,7),(232,16,8),(233,16,9),(234,16,10),(235,16,11),(236,16,12),(237,16,13),(238,16,14),(239,16,15),(240,16,16),(241,16,17),(242,17,2),(243,17,3),(244,17,4),(245,17,5),(246,17,6),(247,17,7),(248,17,8),(249,17,9),(250,17,10),(251,17,11),(252,17,12),(253,17,13),(254,17,14),(255,17,15),(256,17,16),(257,17,17),(260,2,18),(261,3,18),(262,4,18),(263,5,18),(264,6,18),(265,7,18),(266,8,18),(267,9,18),(268,10,18),(269,11,18),(270,12,18),(271,13,18),(272,14,18),(273,15,18),(274,16,18),(275,17,18),(276,2,19),(277,3,19),(278,4,19),(279,5,19),(280,6,19),(281,7,19),(282,8,19),(283,9,19),(284,10,19),(285,11,19),(286,12,19),(287,13,19),(288,14,19),(289,15,19),(290,16,19),(291,17,19),(292,2,20),(293,3,20),(294,4,20),(295,5,20),(296,6,20),(297,7,20),(298,8,20),(299,9,20),(300,10,20),(301,11,20),(302,12,20),(303,13,20),(304,14,20),(305,15,20),(306,16,20),(307,17,20),(308,2,21),(309,3,21),(310,4,21),(311,5,21),(312,6,21),(313,7,21),(314,8,21),(315,9,21),(316,10,21),(317,11,21),(318,12,21),(319,13,21),(320,14,21),(321,15,21),(322,16,21),(323,17,21),(324,2,22),(325,3,22),(326,4,22),(327,5,22),(328,6,22),(329,7,22),(330,8,22),(331,9,22),(332,10,22),(333,11,22),(334,12,22),(335,13,22),(336,14,22),(337,15,22),(338,16,22),(339,17,22),(340,2,23),(341,3,23),(342,4,23),(343,5,23),(344,6,23),(345,7,23),(346,8,23),(347,9,23),(348,10,23),(349,11,23),(350,12,23),(351,13,23),(352,14,23),(353,15,23),(354,16,23),(355,17,23),(356,2,24),(357,3,24),(358,4,24),(359,5,24),(360,6,24),(361,7,24),(362,8,24),(363,9,24),(364,10,24),(365,11,24),(366,12,24),(367,13,24),(368,14,24),(369,15,24),(370,16,24),(371,17,24),(372,2,25),(373,3,25),(374,4,25),(375,5,25),(376,6,25),(377,7,25),(378,8,25),(379,9,25),(380,10,25),(381,11,25),(382,12,25),(383,13,25),(384,14,25),(385,15,25),(386,16,25),(387,17,25),(388,2,26),(389,3,26),(390,4,26),(391,5,26),(392,6,26),(393,7,26),(394,8,26),(395,9,26),(396,10,26),(397,11,26),(398,12,26),(399,13,26),(400,14,26),(401,15,26),(402,16,26),(403,17,26),(404,2,27),(405,3,27),(406,4,27),(407,5,27),(408,6,27),(409,7,27),(410,8,27),(411,9,27),(412,10,27),(413,11,27),(414,12,27),(415,13,27),(416,14,27),(417,15,27),(418,16,27),(419,17,27),(420,2,28),(421,3,28),(422,4,28),(423,5,28),(424,6,28),(425,7,28),(426,8,28),(427,9,28),(428,10,28),(429,11,28),(430,12,28),(431,13,28),(432,14,28),(433,15,28),(434,16,28),(435,17,28),(436,2,29),(437,3,29),(438,4,29),(439,5,29),(440,6,29),(441,7,29),(442,8,29),(443,9,29),(444,10,29),(445,11,29),(446,12,29),(447,13,29),(448,14,29),(449,15,29),(450,16,29),(451,17,29),(452,2,30),(453,3,30),(454,4,30),(455,5,30),(456,6,30),(457,7,30),(458,8,30),(459,9,30),(460,10,30),(461,11,30),(462,12,30),(463,13,30),(464,14,30),(465,15,30),(466,16,30),(467,17,30),(468,2,31),(469,3,31),(470,4,31),(471,5,31),(472,6,31),(473,7,31),(474,8,31),(475,9,31),(476,10,31),(477,11,31),(478,12,31),(479,13,31),(480,14,31),(481,15,31),(482,16,31),(483,17,31),(484,2,32),(485,3,32),(486,4,32),(487,5,32),(488,6,32),(489,7,32),(490,8,32),(491,9,32),(492,10,32),(493,11,32),(494,12,32),(495,13,32),(496,14,32),(497,15,32),(498,16,32),(499,17,32),(500,2,33),(501,3,33),(502,4,33),(503,5,33),(504,6,33),(505,7,33),(506,8,33),(507,9,33),(508,10,33),(509,11,33),(510,12,33),(511,13,33),(512,14,33),(513,15,33),(514,16,33),(515,17,33),(516,2,34),(517,3,34),(518,4,34),(519,5,34),(520,6,34),(521,7,34),(522,8,34),(523,9,34),(524,10,34),(525,11,34),(526,12,34),(527,13,34),(528,14,34),(529,15,34),(530,16,34),(531,17,34),(532,2,35),(533,3,35),(534,4,35),(535,5,35),(536,6,35),(537,7,35),(538,8,35),(539,9,35),(540,10,35),(541,11,35),(542,12,35),(543,13,35),(544,14,35),(545,15,35),(546,16,35),(547,17,35),(548,2,36),(549,3,36),(550,4,36),(551,5,36),(552,6,36),(553,7,36),(554,8,36),(555,9,36),(556,10,36),(557,11,36),(558,12,36),(559,13,36),(560,14,36),(561,15,36),(562,16,36),(563,17,36),(564,2,37),(565,3,37),(566,4,37),(567,5,37),(568,6,37),(569,7,37),(570,8,37),(571,9,37),(572,10,37),(573,11,37),(574,12,37),(575,13,37),(576,14,37),(577,15,37),(578,16,37),(579,17,37),(580,2,38),(581,3,38),(582,4,38),(583,5,38),(584,6,38),(585,7,38),(586,8,38),(587,9,38),(588,10,38),(589,11,38),(590,12,38),(591,13,38),(592,14,38),(593,15,38),(594,16,38),(595,17,38),(596,2,39),(597,3,39),(598,4,39),(599,5,39),(600,6,39),(601,7,39),(602,8,39),(603,9,39),(604,10,39),(605,11,39),(606,12,39),(607,13,39),(608,14,39),(609,15,39),(610,16,39),(611,17,39),(612,2,40),(613,3,40),(614,4,40),(615,5,40),(616,6,40),(617,7,40),(618,8,40),(619,9,40),(620,10,40),(621,11,40),(622,12,40),(623,13,40),(624,14,40),(625,15,40),(626,16,40),(627,17,40),(628,2,41),(629,3,41),(630,4,41),(631,5,41),(632,6,41),(633,7,41),(634,8,41),(635,9,41),(636,10,41),(637,11,41),(638,12,41),(639,13,41),(640,14,41),(641,15,41),(642,16,41),(643,17,41),(644,2,42),(645,3,42),(646,4,42),(647,5,42),(648,6,42),(649,7,42),(650,8,42),(651,9,42),(652,10,42),(653,11,42),(654,12,42),(655,13,42),(656,14,42),(657,15,42),(658,16,42),(659,17,42),(660,2,43),(661,3,43),(662,4,43),(663,5,43),(664,6,43),(665,7,43),(666,8,43),(667,9,43),(668,10,43),(669,11,43),(670,12,43),(671,13,43),(672,14,43),(673,15,43),(674,16,43),(675,17,43),(676,2,44),(677,3,44),(678,4,44),(679,5,44),(680,6,44),(681,7,44),(682,8,44),(683,9,44),(684,10,44),(685,11,44),(686,12,44),(687,13,44),(688,14,44),(689,15,44),(690,16,44),(691,17,44),(692,2,45),(693,3,45),(694,4,45),(695,5,45),(696,6,45),(697,7,45),(698,8,45),(699,9,45),(700,10,45),(701,11,45),(702,12,45),(703,13,45),(704,14,45),(705,15,45),(706,16,45),(707,17,45),(708,2,46),(709,3,46),(710,4,46),(711,5,46),(712,6,46),(713,7,46),(714,8,46),(715,9,46),(716,10,46),(717,11,46),(718,12,46),(719,13,46),(720,14,46),(721,15,46),(722,16,46),(723,17,46),(724,2,47),(725,3,47),(726,4,47),(727,5,47),(728,6,47),(729,7,47),(730,8,47),(731,9,47),(732,10,47),(733,11,47),(734,12,47),(735,13,47),(736,14,47),(737,15,47),(738,16,47),(739,17,47),(740,2,48),(741,3,48),(742,4,48),(743,5,48),(744,6,48),(745,7,48),(746,8,48),(747,9,48),(748,10,48),(749,11,48),(750,12,48),(751,13,48),(752,14,48),(753,15,48),(754,16,48),(755,17,48),(756,2,49),(757,3,49),(758,4,49),(759,5,49),(760,6,49),(761,7,49),(762,8,49),(763,9,49),(764,10,49),(765,11,49),(766,12,49),(767,13,49),(768,14,49),(769,15,49),(770,16,49),(771,17,49),(772,2,50),(773,3,50),(774,4,50),(775,5,50),(776,6,50),(777,7,50),(778,8,50),(779,9,50),(780,10,50),(781,11,50),(782,12,50),(783,13,50),(784,14,50),(785,15,50),(786,16,50),(787,17,50),(788,2,51),(789,3,51),(790,4,51),(791,5,51),(792,6,51),(793,7,51),(794,8,51),(795,9,51),(796,10,51),(797,11,51),(798,12,51),(799,13,51),(800,14,51),(801,15,51),(802,16,51),(803,17,51),(804,2,52),(805,3,52),(806,4,52),(807,5,52),(808,6,52),(809,7,52),(810,8,52),(811,9,52),(812,10,52),(813,11,52),(814,12,52),(815,13,52),(816,14,52),(817,15,52),(818,16,52),(819,17,52),(820,2,53),(821,3,53),(822,4,53),(823,5,53),(824,6,53),(825,7,53),(826,8,53),(827,9,53),(828,10,53),(829,11,53),(830,12,53),(831,13,53),(832,14,53),(833,15,53),(834,16,53),(835,17,53),(836,2,54),(837,3,54),(838,4,54),(839,5,54),(840,6,54),(841,7,54),(842,8,54),(843,9,54),(844,10,54),(845,11,54),(846,12,54),(847,13,54),(848,14,54),(849,15,54),(850,16,54),(851,17,54),(852,2,55),(853,3,55),(854,4,55),(855,5,55),(856,6,55),(857,7,55),(858,8,55),(859,9,55),(860,10,55),(861,11,55),(862,12,55),(863,13,55),(864,14,55),(865,15,55),(866,16,55),(867,17,55),(868,2,56),(869,3,56),(870,4,56),(871,5,56),(872,6,56),(873,7,56),(874,8,56),(875,9,56),(876,10,56),(877,11,56),(878,12,56),(879,13,56),(880,14,56),(881,15,56),(882,16,56),(883,17,56),(884,2,57),(885,3,57),(886,4,57),(887,5,57),(888,6,57),(889,7,57),(890,8,57),(891,9,57),(892,10,57),(893,11,57),(894,12,57),(895,13,57),(896,14,57),(897,15,57),(898,16,57),(899,17,57),(900,2,58),(901,3,58),(902,4,58),(903,5,58),(904,6,58),(905,7,58),(906,8,58),(907,9,58),(908,10,58),(909,11,58),(910,12,58),(911,13,58),(912,14,58),(913,15,58),(914,16,58),(915,17,58),(916,2,59),(917,3,59),(918,4,59),(919,5,59),(920,6,59),(921,7,59),(922,8,59),(923,9,59),(924,10,59),(925,11,59),(926,12,59),(927,13,59),(928,14,59),(929,15,59),(930,16,59),(931,17,59),(932,2,60),(933,3,60),(934,4,60),(935,5,60),(936,6,60),(937,7,60),(938,8,60),(939,9,60),(940,10,60),(941,11,60),(942,12,60),(943,13,60),(944,14,60),(945,15,60),(946,16,60),(947,17,60),(948,2,61),(949,3,61),(950,4,61),(951,5,61),(952,6,61),(953,7,61),(954,8,61),(955,9,61),(956,10,61),(957,11,61),(958,12,61),(959,13,61),(960,14,61),(961,15,61),(962,16,61),(963,17,61),(964,2,62),(965,3,62),(966,4,62),(967,5,62),(968,6,62),(969,7,62),(970,8,62),(971,9,62),(972,10,62),(973,11,62),(974,12,62),(975,13,62),(976,14,62),(977,15,62),(978,16,62),(979,17,62),(980,2,63),(981,3,63),(982,4,63),(983,5,63),(984,6,63),(985,7,63),(986,8,63),(987,9,63),(988,10,63),(989,11,63),(990,12,63),(991,13,63),(992,14,63),(993,15,63),(994,16,63),(995,17,63),(996,2,64),(997,3,64),(998,4,64),(999,5,64),(1000,6,64),(1001,7,64),(1002,8,64),(1003,9,64),(1004,10,64),(1005,11,64),(1006,12,64),(1007,13,64),(1008,14,64),(1009,15,64),(1010,16,64),(1011,17,64),(1012,2,65),(1013,3,65),(1014,4,65),(1015,5,65),(1016,6,65),(1017,7,65),(1018,8,65),(1019,9,65),(1020,10,65),(1021,11,65),(1022,12,65),(1023,13,65),(1024,14,65),(1025,15,65),(1026,16,65),(1027,17,65),(1028,2,66),(1029,3,66),(1030,4,66),(1031,5,66),(1032,6,66),(1033,7,66),(1034,8,66),(1035,9,66),(1036,10,66),(1037,11,66),(1038,12,66),(1039,13,66),(1040,14,66),(1041,15,66),(1042,16,66),(1043,17,66),(1044,2,67),(1045,3,67),(1046,4,67),(1047,5,67),(1048,6,67),(1049,7,67),(1050,8,67),(1051,9,67),(1052,10,67),(1053,11,67),(1054,12,67),(1055,13,67),(1056,14,67),(1057,15,67),(1058,16,67),(1059,17,67),(1060,2,68),(1061,3,68),(1062,4,68),(1063,5,68),(1064,6,68),(1065,7,68),(1066,8,68),(1067,9,68),(1068,10,68),(1069,11,68),(1070,12,68),(1071,13,68),(1072,14,68),(1073,15,68),(1074,16,68),(1075,17,68),(1076,2,69),(1077,3,69),(1078,4,69),(1079,5,69),(1080,6,69),(1081,7,69),(1082,8,69),(1083,9,69),(1084,10,69),(1085,11,69),(1086,12,69),(1087,13,69),(1088,14,69),(1089,15,69),(1090,16,69),(1091,17,69),(1092,2,70),(1093,3,70),(1094,4,70),(1095,5,70),(1096,6,70),(1097,7,70),(1098,8,70),(1099,9,70),(1100,10,70),(1101,11,70),(1102,12,70),(1103,13,70),(1104,14,70),(1105,15,70),(1106,16,70),(1107,17,70),(1108,2,71),(1109,3,71),(1110,4,71),(1111,5,71),(1112,6,71),(1113,7,71),(1114,8,71),(1115,9,71),(1116,10,71),(1117,11,71),(1118,12,71),(1119,13,71),(1120,14,71),(1121,15,71),(1122,16,71),(1123,17,71),(1124,2,72),(1125,3,72),(1126,4,72),(1127,5,72),(1128,6,72),(1129,7,72),(1130,8,72),(1131,9,72),(1132,10,72),(1133,11,72),(1134,12,72),(1135,13,72),(1136,14,72),(1137,15,72),(1138,16,72),(1139,17,72),(1140,2,73),(1141,3,73),(1142,4,73),(1143,5,73),(1144,6,73),(1145,7,73),(1146,8,73),(1147,9,73),(1148,10,73),(1149,11,73),(1150,12,73),(1151,13,73),(1152,14,73),(1153,15,73),(1154,16,73),(1155,17,73),(1156,2,74),(1157,3,74),(1158,4,74),(1159,5,74),(1160,6,74),(1161,7,74),(1162,8,74),(1163,9,74),(1164,10,74),(1165,11,74),(1166,12,74),(1167,13,74),(1168,14,74),(1169,15,74),(1170,16,74),(1171,17,74),(1172,2,75),(1173,3,75),(1174,4,75),(1175,5,75),(1176,6,75),(1177,7,75),(1178,8,75),(1179,9,75),(1180,10,75),(1181,11,75),(1182,12,75),(1183,13,75),(1184,14,75),(1185,15,75),(1186,16,75),(1187,17,75),(1188,2,76),(1189,3,76),(1190,4,76),(1191,5,76),(1192,6,76),(1193,7,76),(1194,8,76),(1195,9,76),(1196,10,76),(1197,11,76),(1198,12,76),(1199,13,76),(1200,14,76),(1201,15,76),(1202,16,76),(1203,17,76),(1204,2,77),(1205,3,77),(1206,4,77),(1207,5,77),(1208,6,77),(1209,7,77),(1210,8,77),(1211,9,77),(1212,10,77),(1213,11,77),(1214,12,77),(1215,13,77),(1216,14,77),(1217,15,77),(1218,16,77),(1219,17,77),(1220,2,78),(1221,3,78),(1222,4,78),(1223,5,78),(1224,6,78),(1225,7,78),(1226,8,78),(1227,9,78),(1228,10,78),(1229,11,78),(1230,12,78),(1231,13,78),(1232,14,78),(1233,15,78),(1234,16,78),(1235,17,78),(1236,2,79),(1237,3,79),(1238,4,79),(1239,5,79),(1240,6,79),(1241,7,79),(1242,8,79),(1243,9,79),(1244,10,79),(1245,11,79),(1246,12,79),(1247,13,79),(1248,14,79),(1249,15,79),(1250,16,79),(1251,17,79),(1252,2,80),(1253,3,80),(1254,4,80),(1255,5,80),(1256,6,80),(1257,7,80),(1258,8,80),(1259,9,80),(1260,10,80),(1261,11,80),(1262,12,80),(1263,13,80),(1264,14,80),(1265,15,80),(1266,16,80),(1267,17,80),(1268,2,81),(1269,3,81),(1270,4,81),(1271,5,81),(1272,6,81),(1273,7,81),(1274,8,81),(1275,9,81),(1276,10,81),(1277,11,81),(1278,12,81),(1279,13,81),(1280,14,81),(1281,15,81),(1282,16,81),(1283,17,81),(1284,2,82),(1285,3,82),(1286,4,82),(1287,5,82),(1288,6,82),(1289,7,82),(1290,8,82),(1291,9,82),(1292,10,82),(1293,11,82),(1294,12,82),(1295,13,82),(1296,14,82),(1297,15,82),(1298,16,82),(1299,17,82),(1300,2,83),(1301,3,83),(1302,4,83),(1303,5,83),(1304,6,83),(1305,7,83),(1306,8,83),(1307,9,83),(1308,10,83),(1309,11,83),(1310,12,83),(1311,13,83),(1312,14,83),(1313,15,83),(1314,16,83),(1315,17,83),(1316,2,84),(1317,3,84),(1318,4,84),(1319,5,84),(1320,6,84),(1321,7,84),(1322,8,84),(1323,9,84),(1324,10,84),(1325,11,84),(1326,12,84),(1327,13,84),(1328,14,84),(1329,15,84),(1330,16,84),(1331,17,84),(1332,2,85),(1333,3,85),(1334,4,85),(1335,5,85),(1336,6,85),(1337,7,85),(1338,8,85),(1339,9,85),(1340,10,85),(1341,11,85),(1342,12,85),(1343,13,85),(1344,14,85),(1345,15,85),(1346,16,85),(1347,17,85),(1348,2,86),(1349,3,86),(1350,4,86),(1351,5,86),(1352,6,86),(1353,7,86),(1354,8,86),(1355,9,86),(1356,10,86),(1357,11,86),(1358,12,86),(1359,13,86),(1360,14,86),(1361,15,86),(1362,16,86),(1363,17,86),(1364,2,87),(1365,3,87),(1366,4,87),(1367,5,87),(1368,6,87),(1369,7,87),(1370,8,87),(1371,9,87),(1372,10,87),(1373,11,87),(1374,12,87),(1375,13,87),(1376,14,87),(1377,15,87),(1378,16,87),(1379,17,87),(1380,2,88),(1381,3,88),(1382,4,88),(1383,5,88),(1384,6,88),(1385,7,88),(1386,8,88),(1387,9,88),(1388,10,88),(1389,11,88),(1390,12,88),(1391,13,88),(1392,14,88),(1393,15,88),(1394,16,88),(1395,17,88),(1396,2,89),(1397,3,89),(1398,4,89),(1399,5,89),(1400,6,89),(1401,7,89),(1402,8,89),(1403,9,89),(1404,10,89),(1405,11,89),(1406,12,89),(1407,13,89),(1408,14,89),(1409,15,89),(1410,16,89),(1411,17,89),(1412,2,90),(1413,3,90),(1414,4,90),(1415,5,90),(1416,6,90),(1417,7,90),(1418,8,90),(1419,9,90),(1420,10,90),(1421,11,90),(1422,12,90),(1423,13,90),(1424,14,90),(1425,15,90),(1426,16,90),(1427,17,90),(1428,2,91),(1429,3,91),(1430,4,91),(1431,5,91),(1432,6,91),(1433,7,91),(1434,8,91),(1435,9,91),(1436,10,91),(1437,11,91),(1438,12,91),(1439,13,91),(1440,14,91),(1441,15,91),(1442,16,91),(1443,17,91),(1444,2,92),(1445,3,92),(1446,4,92),(1447,5,92),(1448,6,92),(1449,7,92),(1450,8,92),(1451,9,92),(1452,10,92),(1453,11,92),(1454,12,92),(1455,13,92),(1456,14,92),(1457,15,92),(1458,16,92),(1459,17,92),(1460,2,93),(1461,3,93),(1462,4,93),(1463,5,93),(1464,6,93),(1465,7,93),(1466,8,93),(1467,9,93),(1468,10,93),(1469,11,93),(1470,12,93),(1471,13,93),(1472,14,93),(1473,15,93),(1474,16,93),(1475,17,93),(1476,2,94),(1477,3,94),(1478,4,94),(1479,5,94),(1480,6,94),(1481,7,94),(1482,8,94),(1483,9,94),(1484,10,94),(1485,11,94),(1486,12,94),(1487,13,94),(1488,14,94),(1489,15,94),(1490,16,94),(1491,17,94),(1492,2,95),(1493,3,95),(1494,4,95),(1495,5,95),(1496,6,95),(1497,7,95),(1498,8,95),(1499,9,95),(1500,10,95),(1501,11,95),(1502,12,95),(1503,13,95),(1504,14,95),(1505,15,95),(1506,16,95),(1507,17,95),(1508,2,96),(1509,3,96),(1510,4,96),(1511,5,96),(1512,6,96),(1513,7,96),(1514,8,96),(1515,9,96),(1516,10,96),(1517,11,96),(1518,12,96),(1519,13,96),(1520,14,96),(1521,15,96),(1522,16,96),(1523,17,96),(1524,2,97),(1525,3,97),(1526,4,97),(1527,5,97),(1528,6,97),(1529,7,97),(1530,8,97),(1531,9,97),(1532,10,97),(1533,11,97),(1534,12,97),(1535,13,97),(1536,14,97),(1537,15,97),(1538,16,97),(1539,17,97),(1540,2,98),(1541,3,98),(1542,4,98),(1543,5,98),(1544,6,98),(1545,7,98),(1546,8,98),(1547,9,98),(1548,10,98),(1549,11,98),(1550,12,98),(1551,13,98),(1552,14,98),(1553,15,98),(1554,16,98),(1555,17,98),(1556,2,99),(1557,3,99),(1558,4,99),(1559,5,99),(1560,6,99),(1561,7,99),(1562,8,99),(1563,9,99),(1564,10,99),(1565,11,99),(1566,12,99),(1567,13,99),(1568,14,99),(1569,15,99),(1570,16,99),(1571,17,99),(1572,2,100),(1573,3,100),(1574,4,100),(1575,5,100),(1576,6,100),(1577,7,100),(1578,8,100),(1579,9,100),(1580,10,100),(1581,11,100),(1582,12,100),(1583,13,100),(1584,14,100),(1585,15,100),(1586,16,100),(1587,17,100);
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
  `xloc` double(30,5) DEFAULT NULL,
  `yloc` double(30,5) DEFAULT NULL,
  PRIMARY KEY (`zone_id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zone`
--

LOCK TABLES `zone` WRITE;
/*!40000 ALTER TABLE `zone` DISABLE KEYS */;
INSERT INTO `zone` VALUES (1,1.00000,1.00000),(2,0.19100,0.17100),(3,0.19100,0.51400),(4,0.19100,0.85600),(5,0.19100,1.19900),(6,0.57200,0.17100),(7,0.57200,0.51400),(8,0.57200,0.85600),(9,0.57200,1.19900),(10,0.95300,0.17100),(11,0.95300,0.51400),(12,0.95300,0.85600),(13,0.95300,1.19900),(14,1.33400,0.17100),(15,1.33400,0.51400),(16,1.33400,0.85600),(17,1.33400,1.19900);
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
    OUT w double,
    OUT v double
)
BEGIN

SELECT zone.xloc,zone.yloc, omega.angle, omega.velocity INTO x,y,w,v FROM shot_type
JOIN zone ON shot_type.zone_id = zone.zone_id
JOIN omega ON shot_type.omega_id = omega.omega_id
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

-- Dump completed on 2018-01-29 10:56:09
