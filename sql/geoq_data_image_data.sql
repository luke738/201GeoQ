-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: geoq_data
-- ------------------------------------------------------
-- Server version	5.7.21-log

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
-- Table structure for table `image_data`
--
USE geoq_data;
DROP TABLE IF EXISTS `image_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `image_data` (
  `image_id` int(11) NOT NULL,
  `latitude` varchar(45) DEFAULT NULL,
  `longitude` varchar(45) DEFAULT NULL,
  `heading` int(11) DEFAULT NULL,
  `pitch` int(11) DEFAULT NULL,
  `answer_a` varchar(45) DEFAULT NULL,
  `answer_b` varchar(45) DEFAULT NULL,
  `answer_c` varchar(45) DEFAULT NULL,
  `answer_d` varchar(45) DEFAULT NULL,
  `correct_answer` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  UNIQUE KEY `image_id_UNIQUE` (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='table that stores all data related to images and questions';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `image_data`
--

LOCK TABLES `image_data` WRITE;
/*!40000 ALTER TABLE `image_data` DISABLE KEYS */;
INSERT INTO `image_data` VALUES (1,'40.6887149','-74.043863',313,30,'Buenos Aires','New York City','Shanghai','Johannesburg','New York City'),(2,'34.009564','118.498611',170,6,'Miami','San Diego','San Francisco','Santa Monica','Santa Monica'),(3,'37.8275033','122.4815992',140,5,'San Francisco','Pattaya','Warsaw','Asheville','San Francisco'),(4,'51.5036739','-0.1230261',94,14,'Warsaw','Berlin','London','Moscow','London'),(5,'25.1368232','55.1851849',354,20,'Abu Dhabi','Dubai','Maldives','Bora Bora','Dubai'),(6,'45.4362339','12.3321429',253,5,'St. Petersburg','Amsterdam','Venice','Bangkok','Venice'),(7,'29.9746896','31.1379663',331,10,'Las Vegas','Istanbul','Faiyum','Giza','Giza'),(8,'39.9157232','116.3970676',348,8,'Shanghai','Beijing','Kyoto','Taipei','Beijing'),(9,'27.1733721','78.0421168',1,10,'Hyderabad','Delhi','Agra','Amritsar','Agra'),(10,'13.4111139','103.8669672',346,8,'Siem Reap','Jakarta','Visakhapatnam','Phuket','Siem Reap');
/*!40000 ALTER TABLE `image_data` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-04-15 17:59:50
