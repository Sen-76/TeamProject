CREATE DATABASE  IF NOT EXISTS `swp391-project` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `swp391-project`;
-- MySQL dump 10.13  Distrib 8.0.29, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: swp391-project
-- ------------------------------------------------------
-- Server version	8.0.29

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `class_id` int NOT NULL AUTO_INCREMENT,
  `class_code` varchar(45) DEFAULT NULL,
  `trainer_id` int DEFAULT NULL,
  `subject_id` int DEFAULT NULL,
  `class_year` int DEFAULT NULL,
  `class_term` varchar(145) DEFAULT NULL,
  `block5_class` varchar(145) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `notes` varchar(445) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  KEY `user_id_idx` (`trainer_id`),
  KEY `subject_id_idx` (`subject_id`),
  CONSTRAINT `subject_id` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `trainer_id` FOREIGN KEY (`trainer_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=165 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES (1,'SWP391_SE1619',4,1,2004,'5','1',1,NULL),(2,'SE8127',2,1,2001,'4','0',1,NULL),(3,'SE1323',2,1,1,'4','0',1,NULL),(4,'AI3612',3,3,3,'4','0',0,NULL),(5,'AI326',2,2,2,'4','0',1,NULL),(6,'MKT216',4,1,2019,'4','0',1,NULL),(7,'SE1232',2,2,2019,'4','0',1,NULL),(8,'SE1232',4,1,2019,'4','0',0,NULL),(9,'SE1232',2,2,2019,'4','0',1,NULL),(10,'AI1232',6,3,2019,'4','0',1,NULL),(11,'AI1232',2,2,2019,'4','0',1,NULL),(12,'AI1232',2,2,2019,'4','0',1,NULL),(13,'AI1232',6,2,2019,'4','0',0,NULL),(14,'AI1232',2,2,2019,'4','0',1,NULL),(15,'MKT2103',3,2,2019,'4','0',1,NULL),(84,'thanh_1',2,1,2022,'1','1',1,NULL),(85,'ddddddd',10,3,2020,'5','1',0,NULL),(86,'MKT1234',2,3,2022,'7','0',1,NULL),(87,'SE162T',4,4,2022,'4','0',1,NULL),(88,'JHY3432',4,2,2022,'3','0',1,NULL),(89,'GHR3245',10,3,2021,'6','1',0,NULL),(90,'ewfwr',3,2,2002,'5','0',1,NULL),(91,'cai nay moi',10,1,2022,'7','0',1,NULL),(113,'dadasd',2,2,2022,'1','1',1,NULL),(114,'tj',2,3,2022,'9','1',1,NULL),(115,'TTTT',10,3,2022,'4','1',1,NULL),(116,'SE1623',6,2,2022,'3','1',1,NULL),(144,'ddddddd',2,2,2022,'4','1',1,NULL),(145,'thanh_aygs',4,2,2005,'8','1',1,NULL),(146,'amen_iosd',3,1,2022,'3','1',1,NULL),(147,'dadasd',2,10,2022,'5','0',1,NULL),(148,'dadasd',3,4,2022,'2','1',1,NULL),(149,'SE1620',6,2,254,'5','1',1,NULL),(150,'se',6,4,2022,'1','1',1,NULL),(151,'se',6,9,2021,'2','1',1,NULL),(152,'se',6,9,1234,'3','1',0,NULL),(153,'se',6,9,2000,'3','1',0,NULL),(154,'se',6,9,2001,'4','1',1,NULL),(155,'se',6,9,2003,'2','1',1,NULL),(156,'se',6,9,2032,'3','1',1,NULL),(157,'se',6,9,2033,'6','1',1,NULL),(158,'se',6,9,2022,'3','1',1,NULL),(159,'se',6,9,2023,'6','1',1,NULL),(160,'se',6,9,2023,'6','1',1,NULL),(161,'se',6,9,2022,'4','1',0,NULL),(162,'ae',6,9,2021,'3','1',0,NULL),(163,'SE2421',10,13,2022,'6','0',1,NULL),(164,'SWP391_SE1620',10,18,2022,'6','0',1,NULL);
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classuser`
--

DROP TABLE IF EXISTS `classuser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classuser` (
  `idclassuser` int NOT NULL AUTO_INCREMENT,
  `class_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `team_leader` varchar(145) DEFAULT NULL,
  `dropout_date` date DEFAULT NULL,
  `user_notes` varchar(545) DEFAULT NULL,
  `ongoing_eval` varchar(145) DEFAULT NULL,
  `final_pres_eval` varchar(145) DEFAULT NULL,
  `final_topic_eval` varchar(145) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`idclassuser`),
  UNIQUE KEY `idclassuser_UNIQUE` (`idclassuser`),
  KEY `class_id0_idx` (`class_id`),
  KEY `team_id0_idx` (`team_id`),
  KEY `user_id0_idx` (`user_id`),
  CONSTRAINT `class_id0` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_id0` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id0` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classuser`
--

LOCK TABLES `classuser` WRITE;
/*!40000 ALTER TABLE `classuser` DISABLE KEYS */;
INSERT INTO `classuser` VALUES (26,1,1,2027,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(28,1,1,2029,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(29,1,1,2030,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(30,1,1,2031,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(32,1,1,2033,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(34,1,1,2035,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(35,1,1,2036,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(40,1,1,2041,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(41,1,1,2042,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(42,1,1,2043,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(44,1,1,2045,'0',NULL,'Searching the other day, I entered the \ngame by phone and found this strange email linked to my account. \nThis version comes into the game and I need a code but I don',NULL,NULL,'10',1),(45,1,1,2,'0',NULL,'Searching the other day, I entered the ',NULL,NULL,'10',1),(46,2,1,2,'0',NULL,'Searching the other day, I entered the ',NULL,NULL,'10',1),(47,2,2,1,'0',NULL,'Searching the other day, I entered the ',NULL,NULL,NULL,NULL),(48,1,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(55,1,NULL,2018,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(64,1,NULL,2019,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(65,1,NULL,2023,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(68,1,NULL,2021,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(74,1,NULL,2022,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(75,1,NULL,2046,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(76,1,NULL,2056,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(77,1,NULL,2057,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(78,1,NULL,2047,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(79,1,NULL,2059,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(80,1,NULL,2055,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(81,1,NULL,2048,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(82,1,NULL,2063,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(83,1,NULL,2049,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(84,1,NULL,2052,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(85,1,NULL,2065,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(86,1,NULL,2058,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(87,1,NULL,2050,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `classuser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `evaluation_criteria`
--

DROP TABLE IF EXISTS `evaluation_criteria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evaluation_criteria` (
  `criteria_id` int NOT NULL AUTO_INCREMENT,
  `iteration_id` int DEFAULT NULL,
  `evaluation_weight` double DEFAULT NULL,
  `team_evaluation` int DEFAULT NULL,
  `criteria_order` varchar(145) DEFAULT NULL,
  `max_loc` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `evaluation_title` varchar(45) DEFAULT NULL,
  `description` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`criteria_id`),
  UNIQUE KEY `criteria_id_UNIQUE` (`criteria_id`),
  KEY `interation_id_idx` (`iteration_id`),
  CONSTRAINT `iteration_id` FOREIGN KEY (`iteration_id`) REFERENCES `iteration` (`iteration_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `evaluation_criteria`
--

LOCK TABLES `evaluation_criteria` WRITE;
/*!40000 ALTER TABLE `evaluation_criteria` DISABLE KEYS */;
INSERT INTO `evaluation_criteria` VALUES (1,1,1,1,'1',1,1,NULL,NULL),(2,2,2,2,'2',2,2,NULL,NULL),(3,2,1,1,'2',2,1,NULL,NULL),(4,1,2,1,'2',1,2,NULL,NULL);
/*!40000 ALTER TABLE `evaluation_criteria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feature`
--

DROP TABLE IF EXISTS `feature`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feature` (
  `feature_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int DEFAULT NULL,
  `feature_name` varchar(145) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `description` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`feature_id`),
  UNIQUE KEY `feature_ID_UNIQUE` (`feature_id`),
  KEY `team_id3_idx` (`team_id`),
  CONSTRAINT `team_id3` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feature`
--

LOCK TABLES `feature` WRITE;
/*!40000 ALTER TABLE `feature` DISABLE KEYS */;
INSERT INTO `feature` VALUES (1,1,'2',1,NULL),(2,2,'1',2,NULL),(3,1,'1',1,NULL),(4,2,'2',2,NULL);
/*!40000 ALTER TABLE `feature` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `function`
--

DROP TABLE IF EXISTS `function`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `function` (
  `function_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int DEFAULT NULL,
  `function_name` varchar(145) DEFAULT NULL,
  `feature_id` int DEFAULT NULL,
  `access_roles` varchar(45) DEFAULT NULL,
  `description` varchar(545) DEFAULT NULL,
  `complexity_id` int DEFAULT NULL,
  `owner_id` int DEFAULT NULL,
  `priority` varchar(145) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `func_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`function_id`),
  UNIQUE KEY `function_id_UNIQUE` (`function_id`),
  KEY `team_id_idx` (`team_id`),
  KEY `feature_id_idx` (`feature_id`),
  CONSTRAINT `feature_id` FOREIGN KEY (`feature_id`) REFERENCES `feature` (`feature_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_id2` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `function`
--

LOCK TABLES `function` WRITE;
/*!40000 ALTER TABLE `function` DISABLE KEYS */;
INSERT INTO `function` VALUES (1,1,'1',1,'1','1',1,1,'1',1,NULL),(2,2,'2',2,'2','2',2,2,'2',2,NULL),(3,3,'3',3,'3','3',3,3,'3',3,NULL);
/*!40000 ALTER TABLE `function` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `issue` (
  `issue_id` int NOT NULL AUTO_INCREMENT,
  `assignee_id` int DEFAULT NULL,
  `issue_title` varchar(145) DEFAULT NULL,
  `description` varchar(545) DEFAULT NULL,
  `gitlab_id` int DEFAULT NULL,
  `gitlab_url` varchar(545) DEFAULT NULL,
  `created_at` varchar(145) DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `milestone_id` int DEFAULT NULL,
  `function_ids` int DEFAULT NULL,
  `lables` varchar(145) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`issue_id`),
  UNIQUE KEY `issue_id_UNIQUE` (`issue_id`),
  KEY `assignee_id_idx` (`assignee_id`),
  KEY `team_id_idx` (`team_id`),
  CONSTRAINT `assignee_id` FOREIGN KEY (`assignee_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_id` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='		';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (1,2,'1','1',1,'1','1','2022-12-12',2,1,1,'asdawd',1),(2,1,'2','2',2,'2','2','2022-12-12',1,2,2,'sjdan',2),(3,1,'2','2',1,'2','1','2000-12-12',2,1,1,'aknwdo',1),(4,2,'1','1',2,'1','1','2000-02-02',1,2,1,'jbksf',2);
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iteration`
--

DROP TABLE IF EXISTS `iteration`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iteration` (
  `iteration_id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int DEFAULT NULL,
  `iteration_name` varchar(145) DEFAULT NULL,
  `duration` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `notes` varchar(445) DEFAULT NULL,
  PRIMARY KEY (`iteration_id`),
  UNIQUE KEY `iteration_id_UNIQUE` (`iteration_id`),
  KEY `subject_id_idx` (`subject_id`),
  CONSTRAINT `subject_id2` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iteration`
--

LOCK TABLES `iteration` WRITE;
/*!40000 ALTER TABLE `iteration` DISABLE KEYS */;
INSERT INTO `iteration` VALUES (1,1,'nawound','oanwon',1,NULL),(2,2,'ianwo','foqno',1,NULL),(3,1,'aowndoa','onfouq',2,NULL),(4,2,'oawo','owi',2,NULL),(5,5,'Linemen','Sports',2,NULL),(6,8,'Welder','Movies',2,NULL),(7,11,'HVAC','Garden',1,NULL),(8,13,'Boilermaker','Automotive',2,NULL),(9,18,'Equipment Operator','Jewelry',1,NULL),(10,19,'Boilermaker','Health',2,NULL),(11,6,'Tile Setter','Music',2,NULL),(12,7,'Landscaper','Kids',1,NULL),(13,14,'Laborer','Grocery',1,NULL),(14,14,'Refridgeration','Electronics',1,NULL),(15,14,'HVAC','Kids',1,NULL),(16,13,'Plasterers','Baby',2,NULL),(17,2,'Ironworker','Grocery',1,NULL),(18,15,'HVAC','Music',2,NULL),(20,5,'Linemen','Sports',2,NULL),(21,8,'Welder','Movies',2,NULL),(22,11,'HVAC','Garden',1,NULL),(23,13,'Boilermaker','Automotive',2,NULL),(24,18,'Equipment Operator','Jewelry',1,NULL),(25,19,'Boilermaker','Health',2,NULL),(26,6,'Tile Setter','Music',2,NULL),(27,7,'Landscaper','Kids',1,NULL),(28,14,'Laborer','Grocery',1,NULL),(29,14,'Refridgeration','Electronics',1,NULL),(30,14,'HVAC','Kids',1,NULL),(31,13,'Plasterers','Baby',2,NULL),(32,2,'Ironworker','Grocery',1,NULL),(33,15,'HVAC','Music',2,NULL),(35,5,'Linemen','Sports',2,NULL),(36,8,'Welder','Movies',2,NULL),(37,11,'HVAC','Garden',1,NULL),(38,13,'Boilermaker','Automotive',2,NULL),(39,18,'Equipment Operator','Jewelry',1,NULL),(40,19,'Boilermaker','Health',2,NULL),(41,6,'Tile Setter','Music',2,NULL),(42,7,'Landscaper','Kids',1,NULL),(43,14,'Laborer','Grocery',1,NULL),(44,14,'Refridgeration','Electronics',1,NULL),(45,14,'HVAC','Kids',1,NULL),(46,13,'Plasterers','Baby',2,NULL),(47,2,'Ironworker','Grocery',1,NULL),(48,15,'HVAC','Music',2,NULL),(50,5,'Linemen','Sports',2,NULL),(51,8,'Welder','Movies',2,NULL),(52,11,'HVAC','Garden',1,NULL),(53,13,'Boilermaker','Automotive',2,NULL),(54,18,'Equipment Operator','Jewelry',1,NULL),(55,19,'Boilermaker','Health',2,NULL),(56,6,'Tile Setter','Music',2,NULL),(57,7,'Landscaper','Kids',1,NULL),(58,14,'Laborer','Grocery',1,NULL),(59,14,'Refridgeration','Electronics',1,NULL),(60,14,'HVAC','Kids',1,NULL),(61,13,'Plasterers','Baby',2,NULL),(62,2,'Ironworker','Grocery',1,NULL),(63,15,'HVAC','Music',2,NULL);
/*!40000 ALTER TABLE `iteration` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `iteration_evaluation`
--

DROP TABLE IF EXISTS `iteration_evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `iteration_evaluation` (
  `evaluation_id` int NOT NULL AUTO_INCREMENT,
  `interation_id` int DEFAULT NULL,
  `class_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  `bonus` double DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `note` varchar(545) DEFAULT NULL,
  PRIMARY KEY (`evaluation_id`),
  UNIQUE KEY `evaluation_id_UNIQUE` (`evaluation_id`),
  KEY `class_id7_idx` (`class_id`),
  KEY `iteration_id4_idx` (`interation_id`),
  CONSTRAINT `class_id7` FOREIGN KEY (`class_id`) REFERENCES `classuser` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `iteration_id4` FOREIGN KEY (`interation_id`) REFERENCES `iteration` (`iteration_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `iteration_evaluation`
--

LOCK TABLES `iteration_evaluation` WRITE;
/*!40000 ALTER TABLE `iteration_evaluation` DISABLE KEYS */;
INSERT INTO `iteration_evaluation` VALUES (7,4,NULL,1,2,4,5,'ngu'),(8,4,NULL,1,3,6,34,'ga`');
/*!40000 ALTER TABLE `iteration_evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `loc_evaluation`
--

DROP TABLE IF EXISTS `loc_evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loc_evaluation` (
  `evaluation_id` int NOT NULL AUTO_INCREMENT,
  `evaluation_time` date DEFAULT NULL,
  `evaluation_note` varchar(545) DEFAULT NULL,
  `complexity_id` int DEFAULT NULL,
  `quality_id` int DEFAULT NULL,
  `tracking_id` int DEFAULT NULL,
  PRIMARY KEY (`evaluation_id`),
  UNIQUE KEY `evaluation_id_UNIQUE` (`evaluation_id`),
  KEY `tracking_id_idx` (`tracking_id`),
  CONSTRAINT `tracking_id` FOREIGN KEY (`tracking_id`) REFERENCES `tracking` (`tracking_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loc_evaluation`
--

LOCK TABLES `loc_evaluation` WRITE;
/*!40000 ALTER TABLE `loc_evaluation` DISABLE KEYS */;
INSERT INTO `loc_evaluation` VALUES (1,'2022-02-02','aoiwj02',1,1,1),(2,'2022-02-02','2',2,2,2),(3,'2022-02-02','3',3,3,3),(4,'2022-02-02','4',4,4,4);
/*!40000 ALTER TABLE `loc_evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member_evaluation`
--

DROP TABLE IF EXISTS `member_evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member_evaluation` (
  `member_eval_id` int NOT NULL AUTO_INCREMENT,
  `evaluation_id` int DEFAULT NULL,
  `criteria_id` int DEFAULT NULL,
  `converted_log` varchar(145) DEFAULT NULL,
  `grade` double DEFAULT NULL,
  `note` varchar(545) DEFAULT NULL,
  PRIMARY KEY (`member_eval_id`),
  UNIQUE KEY `member_eval_id_UNIQUE` (`member_eval_id`),
  KEY `evaluation_id_idx` (`evaluation_id`),
  KEY `criteria_id_idx` (`criteria_id`),
  CONSTRAINT `criteria_id1` FOREIGN KEY (`criteria_id`) REFERENCES `evaluation_criteria` (`criteria_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `evaluation_id` FOREIGN KEY (`evaluation_id`) REFERENCES `iteration_evaluation` (`evaluation_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member_evaluation`
--

LOCK TABLES `member_evaluation` WRITE;
/*!40000 ALTER TABLE `member_evaluation` DISABLE KEYS */;
INSERT INTO `member_evaluation` VALUES (7,7,1,'fgdfgdfg',435,'ngu'),(8,8,2,NULL,NULL,NULL),(9,8,4,'5',NULL,NULL);
/*!40000 ALTER TABLE `member_evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `milestone`
--

DROP TABLE IF EXISTS `milestone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `milestone` (
  `milestone_id` int NOT NULL AUTO_INCREMENT,
  `interation_id` int DEFAULT NULL,
  `class_id` int DEFAULT NULL,
  `from_date` date DEFAULT NULL,
  `to_date` date DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`milestone_id`),
  UNIQUE KEY `milestone_id_UNIQUE` (`milestone_id`),
  KEY `class_id2_idx` (`class_id`),
  KEY `interation_id_idx` (`interation_id`),
  CONSTRAINT `class_id2` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`),
  CONSTRAINT `interation_id` FOREIGN KEY (`interation_id`) REFERENCES `iteration` (`iteration_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `milestone`
--

LOCK TABLES `milestone` WRITE;
/*!40000 ALTER TABLE `milestone` DISABLE KEYS */;
INSERT INTO `milestone` VALUES (1,1,1,'2022-02-02','2022-03-03',1),(2,2,2,'2022-01-01','2022-02-02',1),(3,1,1,'2020-02-02','2020-12-12',2),(4,2,1,'2000-02-02','2000-12-12',2),(7,22,10,'2021-11-08','2022-02-21',2),(9,42,10,'2021-09-10','2022-05-05',2),(10,42,10,'2021-09-10','2022-05-05',2),(11,43,9,'2021-06-22','2022-03-15',1),(12,62,4,'2021-06-21','2022-02-25',1),(13,44,2,'2021-07-04','2022-04-20',2),(14,13,6,'2021-11-08','2022-03-07',2),(15,42,11,'2021-11-27','2022-04-09',2),(16,38,7,'2021-07-29','2022-02-10',1),(17,32,3,'2021-07-11','2022-02-28',1),(18,45,15,'2021-10-20','2022-05-19',2),(19,59,15,'2021-11-13','2022-04-30',2),(20,29,6,'2021-09-13','2022-04-29',2),(21,35,11,'2021-08-21','2022-05-08',1),(24,42,10,'2021-09-10','2022-05-05',2),(25,43,9,'2021-06-22','2022-03-15',1),(26,62,4,'2021-06-21','2022-02-25',1),(27,44,2,'2021-07-04','2022-04-20',2),(28,13,6,'2021-11-08','2022-03-07',2),(29,42,11,'2021-11-27','2022-04-09',2),(30,38,7,'2021-07-29','2022-02-10',1),(31,32,3,'2021-07-11','2022-02-28',1),(32,45,15,'2021-10-20','2022-05-19',2),(33,59,15,'2021-11-13','2022-04-30',2),(34,29,6,'2021-09-13','2022-04-29',2),(35,35,11,'2021-08-21','2022-05-08',1),(37,42,10,'2021-09-10','2022-05-05',2),(38,43,9,'2021-06-22','2022-03-15',1),(39,62,4,'2021-06-21','2022-02-25',1),(40,44,2,'2021-07-04','2022-04-20',2),(41,13,6,'2021-11-08','2022-03-07',2),(42,42,11,'2021-11-27','2022-04-09',2),(43,38,7,'2021-07-29','2022-02-10',1),(44,32,3,'2021-07-11','2022-02-28',1),(45,45,15,'2021-10-20','2022-05-19',2),(46,59,15,'2021-11-13','2022-04-30',2),(47,29,6,'2021-09-13','2022-04-29',2),(48,35,11,'2021-08-21','2022-05-08',1),(50,55,156,'2021-07-01','2022-02-03',2),(51,48,162,'2021-07-11','2022-04-05',1),(52,27,150,'2021-07-04','2022-04-22',1),(53,29,160,'2021-10-09','2022-05-10',2),(54,53,150,'2021-06-20','2022-03-05',2),(55,2,160,'2021-08-02','2022-03-08',2),(56,5,155,'2021-10-06','2022-03-22',1),(57,26,155,'2021-07-17','2022-03-14',2),(58,39,151,'2021-11-30','2022-02-11',2),(59,58,150,'2021-10-22','2022-04-10',1),(60,32,160,'2021-10-31','2022-05-18',1),(61,22,153,'2021-10-30','2022-05-17',1),(62,41,152,'2021-12-05','2022-03-08',2),(63,47,154,'2021-10-12','2022-03-09',2),(64,14,151,'2021-12-22','2022-04-21',1),(65,40,158,'2021-12-31','2022-02-14',1),(66,27,158,'2021-06-10','2022-05-28',2),(67,25,160,'2021-12-20','2022-03-01',2),(68,27,156,'2021-10-20','2022-02-28',2),(69,35,159,'2021-07-26','2022-05-07',1),(70,39,162,'2021-09-20','2022-02-24',1),(71,25,156,'2021-12-19','2022-03-31',2),(72,48,164,'2021-08-01','2022-04-13',2),(73,29,154,'2021-12-21','2022-05-04',1),(74,35,151,'2021-12-30','2022-05-03',1),(75,14,155,'2021-08-25','2022-04-08',2),(76,18,155,'2021-09-12','2022-02-02',2),(77,37,157,'2021-07-28','2022-04-21',2),(78,22,159,'2021-07-23','2022-02-06',1),(79,53,164,'2021-10-07','2022-05-02',1);
/*!40000 ALTER TABLE `milestone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `setting`
--

DROP TABLE IF EXISTS `setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `setting` (
  `setting_id` int NOT NULL AUTO_INCREMENT,
  `type_id` int DEFAULT NULL,
  `setting_title` varchar(145) DEFAULT NULL,
  `setting_value` varchar(145) DEFAULT NULL,
  `display_order` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `note` varchar(545) DEFAULT NULL,
  PRIMARY KEY (`setting_id`),
  UNIQUE KEY `setting_id_UNIQUE` (`setting_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `setting`
--

LOCK TABLES `setting` WRITE;
/*!40000 ALTER TABLE `setting` DISABLE KEYS */;
INSERT INTO `setting` VALUES (1,2,'','','',1,''),(2,2,'thanh','amen','this is new one',2,'                            '),(3,1,'nfain','b ifdi','dbivksb',1,'e diarrhoeas. He was first diagnosed on pancreatitis after the first blood test on May 13. This morning, his symptoms were getting worst, so we took him to emergency room at City university VMC. Did an 2nd blood test and X-rays, the blood test result are bet'),(4,5,'ksjdnv','isndsl','wnoj',1,'e diarrhoeas. He was first diagnosed on pancreatitis after the first blood test on May 13. This morning, his symptoms were getting worst, so we took him to emergency room at City university VMC. Did an 2nd blood test and X-rays, the blood test result are bet'),(5,3,'aaaasdasda','lisgdfuashdf','sayonara',1,'e diarrhoeas. He was first diagnosed on pancreatitis after the first blood test on May 13. This morning, his symptoms were getting worst, so we took him to emergency room at City university VMC. Did an 2nd blood test and X-rays, the blood test result are bet'),(6,4,'rasds','sdftyss','dsdasd',2,'The retail gasoline price on the Singapore market updated to May 17 increased sharply compared to the previous price calculation period.'),(7,3,'Dang Tat Thanh','this is new one','asg sdfs',2,NULL),(8,3,'Dang Tat Thanh','this is new one','new value',1,NULL),(9,1,'validate setting, detail','this is new one','1',1,NULL);
/*!40000 ALTER TABLE `setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `subject_id` int NOT NULL AUTO_INCREMENT,
  `subject_code` varchar(45) DEFAULT NULL,
  `subject_name` varchar(145) DEFAULT NULL,
  `author_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `description` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`subject_id`),
  UNIQUE KEY `subject_id_UNIQUE` (`subject_id`),
  KEY `author_id_idx` (`author_id`),
  CONSTRAINT `author_id` FOREIGN KEY (`author_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
INSERT INTO `subject` VALUES (1,'SWP','project',2,1,NULL),(2,'PRN','hoc c#',3,1,NULL),(3,'MAS','xac suat thong ke',3,2,NULL),(4,'MAD','toan roi rac',1,2,NULL),(5,'PRO','JAVA OOP',1,1,NULL),(6,'PRF','C CORE',2,1,NULL),(7,'CSI','ly thuyet d',3,1,NULL),(8,'CEA','ly thuyet y',3,2,NULL),(9,'AAA','ly thuyet e',10,1,NULL),(10,'BBB','ly thuyet yh',1,1,NULL),(11,'CCC','ly thuyet re',2,1,NULL),(12,'DDD','ly thuyet yt',1,2,NULL),(13,'EEE','ly thuyet we',1,2,NULL),(14,'ERE','ly thuyet fr',2,1,NULL),(15,'TTG','ly thuyet fe',3,1,NULL),(16,'GRF','ly thuyet fq',3,1,NULL),(17,'ASD','ly thuyet af',2,1,NULL),(18,'SER','ly thuyet ft',1,2,NULL),(19,'mlk','Ly thuyet sp',9,1,NULL),(20,'AAAA','AAAA',10,1,NULL);
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_setting`
--

DROP TABLE IF EXISTS `subject_setting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_setting` (
  `setting_id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  `setting_title` varchar(145) DEFAULT NULL,
  `setting_value` varchar(145) DEFAULT NULL,
  `display_order` varchar(145) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`setting_id`),
  UNIQUE KEY `setting_id_UNIQUE` (`setting_id`),
  KEY `subject_id_idx` (`subject_id`),
  CONSTRAINT `subject_id1` FOREIGN KEY (`subject_id`) REFERENCES `subject` (`subject_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_setting`
--

LOCK TABLES `subject_setting` WRITE;
/*!40000 ALTER TABLE `subject_setting` DISABLE KEYS */;
INSERT INTO `subject_setting` VALUES (1,1,2,'fvuou','0j20ij','0qij30q',1),(2,2,1,'w0830','0jw30','3r22',2),(3,1,1,'oo9o3rw','392r90ij','32j9t2',1),(4,2,2,'9q83','398j29','239t823',2),(5,12,1,NULL,NULL,NULL,1),(6,11,3,NULL,NULL,NULL,1),(7,14,5,NULL,NULL,NULL,2),(8,10,2,NULL,NULL,NULL,2),(9,9,1,NULL,NULL,NULL,1),(10,8,2,NULL,NULL,NULL,2),(11,3,2,NULL,NULL,NULL,1);
/*!40000 ALTER TABLE `subject_setting` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `team_id` int NOT NULL AUTO_INCREMENT,
  `class_id` int DEFAULT NULL,
  `topic_code` varchar(145) DEFAULT NULL,
  `topic_name` varchar(145) DEFAULT NULL,
  `gitlab_url` varchar(545) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `team_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`team_id`),
  UNIQUE KEY `team_id_UNIQUE` (`team_id`),
  KEY `class_id_idx` (`class_id`),
  CONSTRAINT `class_id` FOREIGN KEY (`class_id`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (1,1,'TOPIC 1','MAE','wqqrww',1,NULL),(2,1,'TOPIC 2','MAD','fasfq3',1,NULL),(3,1,'TOPIC 3','MAS','bfsn54',0,NULL),(13,1,'TOPIC 4','PRF',NULL,1,NULL);
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team-evaluation`
--

DROP TABLE IF EXISTS `team-evaluation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team-evaluation` (
  `team_eval_id` int NOT NULL AUTO_INCREMENT,
  `evaluation_id` int DEFAULT NULL,
  `criteria_id` int DEFAULT NULL,
  `team_id` int DEFAULT NULL,
  `grade` varchar(45) DEFAULT NULL,
  `note` varchar(545) DEFAULT NULL,
  PRIMARY KEY (`team_eval_id`),
  UNIQUE KEY `team_eval_id_UNIQUE` (`team_eval_id`),
  KEY `team_id_idx` (`team_id`),
  KEY `criteria_id_idx` (`criteria_id`),
  KEY `evaluation_id_idx` (`evaluation_id`),
  CONSTRAINT `criteria_id` FOREIGN KEY (`criteria_id`) REFERENCES `evaluation_criteria` (`criteria_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `evaluation_id5` FOREIGN KEY (`evaluation_id`) REFERENCES `iteration_evaluation` (`evaluation_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_id6` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team-evaluation`
--

LOCK TABLES `team-evaluation` WRITE;
/*!40000 ALTER TABLE `team-evaluation` DISABLE KEYS */;
INSERT INTO `team-evaluation` VALUES (8,7,1,1,'3','auhsdbfuhasd'),(9,7,1,1,'4','aaa'),(10,8,2,2,'4',NULL),(11,8,3,1,'4','55');
/*!40000 ALTER TABLE `team-evaluation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracking`
--

DROP TABLE IF EXISTS `tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tracking` (
  `tracking_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int DEFAULT NULL,
  `milestone_id` int DEFAULT NULL,
  `function_id` int DEFAULT NULL,
  `assigner_id` int DEFAULT NULL,
  `assignee_id` int DEFAULT NULL,
  `tracking_note` varchar(545) DEFAULT NULL,
  `updates` varchar(145) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`tracking_id`),
  UNIQUE KEY `tracking_id_UNIQUE` (`tracking_id`),
  KEY `team_id5_idx` (`team_id`),
  KEY `function_id1_idx` (`function_id`),
  KEY `milestone_id_idx` (`milestone_id`),
  CONSTRAINT `function_id1` FOREIGN KEY (`function_id`) REFERENCES `function` (`function_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `milestone_id` FOREIGN KEY (`milestone_id`) REFERENCES `milestone` (`milestone_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `team_id5` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracking`
--

LOCK TABLES `tracking` WRITE;
/*!40000 ALTER TABLE `tracking` DISABLE KEYS */;
INSERT INTO `tracking` VALUES (1,1,1,1,1,1,'asd2wfqwf','afw2wq',1),(2,2,2,2,2,2,'asf12f','fasf2q',2),(3,1,2,2,1,1,'as32','vsdv23',1),(4,2,1,2,1,2,'asf3w3','vq2w',2);
/*!40000 ALTER TABLE `tracking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `roll_number` varchar(50) DEFAULT NULL,
  `fullname` varchar(145) DEFAULT NULL,
  `gender` int DEFAULT NULL,
  `date_of__birth` date DEFAULT NULL,
  `email` varchar(145) DEFAULT NULL,
  `mobile` varchar(10) DEFAULT NULL,
  `avatar_link` varchar(545) DEFAULT NULL,
  `facebook_link` varchar(545) DEFAULT NULL,
  `role_id` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `pass` varchar(45) DEFAULT NULL,
  `note` varchar(245) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=2517 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'e2704','Dang Thanh',1,'2001-01-11','erogura@gmail.com','0287626234','373690cai nay hoi la.png','https://www.facebook.com/Siscon1456/',4,0,'aaa','tuIT5P7gQCgTH5JwGt7o2Q==',NULL),(2,'jshdf23','Nguyen Hoa',1,'2022-01-01','abc@gmail.com','24124134','asfwfaw','b223',3,0,'bbb','yiP2Mk+l7LW95kP6zTeNAA==',NULL),(3,'23fdd32','Van Nam',2,'2020-02-02','bacs@gmail.com','2114242','naosndoand','12rjut0p3',2,1,'ccc','tuIT5P7gQCgTH5JwGt7o2Q==',NULL),(4,'af32fsdf','Mi Xao',1,'2000-03-03','ias@gmail.com','248124','salndalknd','28u1jip0jf',3,1,'ddd','tuIT5P7gQCgTH5JwGt7o2Q==',NULL),(6,'323r233','Sen',1,'2022-05-19','sen76201@gmail.com','344382294','none','none',3,0,'sen','tuIT5P7gQCgTH5JwGt7o2Q==',NULL),(9,'rer23','hoa',2,NULL,'asdd@gmail.com',NULL,NULL,NULL,2,1,'eee','tuIT5P7gQCgTH5JwGt7o2Q==',NULL),(10,'rer4gw3','NonSon',1,NULL,NULL,NULL,NULL,NULL,3,1,'ddd','tuIT5P7gQCgTH5JwGt7o2Q==',NULL),(2017,'hQr2gCOpf','Courtney Waterhouse',2,'1996-12-26','cwaterhouse0@businessinsider.com',NULL,NULL,NULL,1,1,'cwaterhouse0','tuIT5P7gQCgTH5JwGt7o2Q==',NULL),(2018,'asunmM','Liv Gaspard',1,'1997-03-05','lgaspard1@google.com.au','9516010176',NULL,NULL,1,1,'lgaspard1',NULL,NULL),(2019,'mvggaN','Land Tranckle',2,'2006-09-09','ltranckle2@chron.com','6257663626',NULL,NULL,1,1,'ltranckle2',NULL,NULL),(2020,'4WU5TbJ','Barr Barchrameev',1,'2003-05-26','bbarchrameev3@psu.edu',NULL,NULL,NULL,1,0,'bbarchrameev3',NULL,NULL),(2021,'aOWX9TGQW7k','Darci Orsay',2,'2005-10-09','dorsay4@indiatimes.com','9047539922',NULL,NULL,1,1,'dorsay4',NULL,NULL),(2022,'1TaROjfaiqB','Letty Illingsworth',2,'2002-08-28','lillingsworth5@who.int','8769205732',NULL,NULL,1,0,'lillingsworth5',NULL,NULL),(2023,'jg6APUtR6Gqs','Hillyer O\' Lone',1,'1999-08-20','ho6@vimeo.com','7444706391',NULL,NULL,1,1,'ho6',NULL,NULL),(2024,'sdhvTN','Mayne Mossdale',2,'1992-01-05','mmossdale7@quantcast.com',NULL,NULL,NULL,1,1,'mmossdale7',NULL,NULL),(2025,'WgCqW6nc4rD','Morie Lease',2,'1998-09-30','mlease8@cnbc.com','8578087444',NULL,NULL,1,0,'mlease8','tuIT5P7gQCgTH5JwGt7o2Q==',NULL),(2026,'HJmYOxMc0q77','Kaye Edginton',2,'2004-04-12','kedginton9@shinystat.com',NULL,NULL,NULL,1,1,'kedginton9',NULL,NULL),(2027,'3TiCp0C7','Jaquelin Maplethorp',2,'1992-09-12','jmaplethorpa@taobao.com','3884605420',NULL,NULL,1,1,'jmaplethorpa',NULL,NULL),(2028,'9LoC1tsrX3V','Farica Chetwin',1,'1995-01-14','fchetwinb@tripadvisor.com',NULL,NULL,NULL,1,0,'fchetwinb',NULL,NULL),(2029,'hTlhtP77r1','Conant Lyes',1,'2003-03-08','clyesc@yandex.ru','9443793662',NULL,NULL,1,1,'clyesc',NULL,NULL),(2030,'pSXfim','Carr Canter',2,'1990-06-19','ccanterd@google.com.br','1915323286',NULL,NULL,1,0,'ccanterd',NULL,NULL),(2031,'zkZa08','Blondelle Beuscher',1,'1991-10-13','bbeuschere@storify.com','8154683298',NULL,NULL,1,0,'bbeuschere',NULL,NULL),(2032,'UbEPCMWpf','Miguela Pladen',2,'2003-11-13','mpladenf@e-recht24.de',NULL,NULL,NULL,1,1,'mpladenf',NULL,NULL),(2033,'YvcWhv','Birk Millions',2,'1991-07-01','bmillionsg@craigslist.org','4896941175',NULL,NULL,1,1,'bmillionsg',NULL,NULL),(2034,'13qXNNYis','Archie McIlwreath',1,'1999-12-10','amcilwreathh@prweb.com',NULL,NULL,NULL,1,0,'amcilwreathh',NULL,NULL),(2035,'A67Y4Mb','Pattie Somerset',2,'1993-08-15','psomerseti@digg.com','3022573905',NULL,NULL,1,1,'psomerseti',NULL,NULL),(2036,'ZLQX18BvLgW','Linus Trippett',2,'2006-04-26','ltrippettj@meetup.com','4374040842',NULL,NULL,1,0,'ltrippettj',NULL,NULL),(2037,'uYtKfz6','Arvie Lemar',1,'2003-06-19','alemark@senate.gov',NULL,NULL,NULL,1,0,'alemark',NULL,NULL),(2038,'FADlWf','Ignacio Stuehmeyer',1,'1999-06-15','istuehmeyerl@webeden.co.uk',NULL,NULL,NULL,1,1,'istuehmeyerl',NULL,NULL),(2039,'WhO1MT','Meir Hayley',2,'1994-09-18','mhayleym@woothemes.com',NULL,NULL,NULL,1,1,'mhayleym',NULL,NULL),(2040,'G2NG4jCf','Ruby Buttfield',1,'1997-04-28','rbuttfieldn@rambler.ru',NULL,NULL,NULL,1,1,'rbuttfieldn',NULL,NULL),(2041,'r4PNmzDjsTX','Reese Boreham',2,'1998-12-23','rborehamo@wordpress.com','7009728741',NULL,NULL,1,0,'rborehamo',NULL,NULL),(2042,'5vzIhfzA7VJQ','Zack Windram',2,'2003-05-07','zwindramp@sohu.com','3454135947',NULL,NULL,1,1,'zwindramp',NULL,NULL),(2043,'SnaKjo','Murdoch Grisbrook',1,'2005-10-03','mgrisbrookq@livejournal.com','5636651602',NULL,NULL,1,0,'mgrisbrookq',NULL,NULL),(2044,'d7gu6NGq4','Guntar McLise',1,'2002-08-04','gmcliser@fotki.com',NULL,NULL,NULL,1,0,'gmcliser',NULL,NULL),(2045,'8HBreqO','Menard Berndsen',2,'1992-03-19','mberndsens@census.gov','6351646707',NULL,NULL,1,0,'mberndsens',NULL,NULL),(2046,'SdnRqr','Paulette Matfin',2,'2001-07-12','pmatfint@spotify.com','1545987524',NULL,NULL,1,1,'pmatfint',NULL,NULL),(2047,'x7xmRT','Bartlett Bevan',1,'2003-05-28','bbevanu@exblog.jp','9705461098',NULL,NULL,1,0,'bbevanu',NULL,NULL),(2048,'fnkJsk1MdxAk','Manon Danilevich',2,'1996-11-12','mdanilevichv@dedecms.com','8219299192',NULL,NULL,1,1,'mdanilevichv',NULL,NULL),(2049,'jcsce5HQcdRO','Darcie Wasbey',1,'1996-08-15','dwasbeyw@nydailynews.com','1252209486',NULL,NULL,1,0,'dwasbeyw',NULL,NULL),(2050,'Qp9jezohZR4','Jolene Larking',1,'1993-08-29','jlarkingx@dmoz.org','4281044557',NULL,NULL,1,0,'jlarkingx',NULL,NULL),(2051,'5I5hlTywa','Robinett Phelan',2,'1998-08-13','rphelany@dedecms.com','3141123588',NULL,NULL,1,1,'rphelany',NULL,NULL),(2052,'M7tRBXq','Rudd Voas',1,'2000-02-18','rvoasz@google.pl','5586155090',NULL,NULL,1,1,'rvoasz',NULL,NULL),(2053,'InKL1thPR','Kathleen Merkle',1,'1994-09-10','kmerkle10@slideshare.net',NULL,NULL,NULL,1,1,'kmerkle10',NULL,NULL),(2054,'Ilck3NUoF','Larry Roddie',1,'1990-12-19','lroddie11@imageshack.us',NULL,NULL,NULL,1,0,'lroddie11',NULL,NULL),(2055,'Weehm4BnWOTJ','Prudence Coneybeer',2,'1998-02-12','pconeybeer12@dropbox.com','3368161789',NULL,NULL,1,0,'pconeybeer12',NULL,NULL),(2056,'UC2j9Q','Madelin Badland',1,'1994-01-01','mbadland13@xinhuanet.com','3952240955',NULL,NULL,1,0,'mbadland13',NULL,NULL),(2057,'2aPyW08Zry','Cyndia Wheelband',2,'2000-09-09','cwheelband14@prnewswire.com','8008759905',NULL,NULL,1,0,'cwheelband14',NULL,NULL),(2058,'ZiciHFB33z','Herold MacFadin',1,'2006-04-03','hmacfadin15@ning.com','4552352126',NULL,NULL,1,1,'hmacfadin15',NULL,NULL),(2059,'7u81dNaBrygg','Caesar Huddlestone',2,'2003-03-19','chuddlestone16@parallels.com','2714677099',NULL,NULL,1,1,'chuddlestone16',NULL,NULL),(2060,'DyeWxioRY7','Jewel Zorzetti',1,'1990-05-20','jzorzetti17@webs.com',NULL,NULL,NULL,1,0,'jzorzetti17',NULL,NULL),(2061,'oOpFjzBqJ','Claudelle Bignal',2,'2002-04-24','cbignal18@washington.edu',NULL,NULL,NULL,1,0,'cbignal18',NULL,NULL),(2062,'SflGBO0kP0iT','Hanni Darkins',2,'1995-03-26','hdarkins19@ustream.tv',NULL,NULL,NULL,1,0,'hdarkins19',NULL,NULL),(2063,'fDglVGd3o','Brynn Jaspar',2,'1998-08-28','bjaspar1a@omniture.com','9873077378',NULL,NULL,1,1,'bjaspar1a',NULL,NULL),(2064,'mtmBeFA','Beverie Manilove',2,'1991-12-28','bmanilove1b@list-manage.com','9187055960',NULL,NULL,1,1,'bmanilove1b',NULL,NULL),(2065,'qcMXbYuA80L','De witt Thackeray',1,'1997-06-29','dwitt1c@jiathis.com','8859052688',NULL,NULL,1,0,'dwitt1c',NULL,NULL),(2066,'E1Jktn','Willi Dymott',2,'1990-11-05','wdymott1d@deliciousdays.com',NULL,NULL,NULL,1,0,'wdymott1d',NULL,NULL),(2067,'cewS0W5L','Palmer Hardi',1,'1993-03-30','phardi1e@t-online.de',NULL,NULL,NULL,1,0,'phardi1e',NULL,NULL),(2068,'zZjHP7H','Duffy Naisey',1,'2006-01-04','dnaisey1f@yolasite.com','5036269801',NULL,NULL,1,0,'dnaisey1f',NULL,NULL),(2069,'jVIpPk9k5XXE','Sax Grigson',2,'1998-12-25','sgrigson1g@nydailynews.com','8752525340',NULL,NULL,1,1,'sgrigson1g',NULL,NULL),(2070,'HKPp5arkPFg','Klarika Pawlata',1,'1991-09-24','kpawlata1h@oaic.gov.au',NULL,NULL,NULL,1,1,'kpawlata1h',NULL,NULL),(2071,'RY6E9MXIh','Meghan Belsham',2,'1998-08-31','mbelsham1i@dailymotion.com','2037473554',NULL,NULL,1,0,'mbelsham1i',NULL,NULL),(2072,'NPhhByA1y1x','Nickolaus Braikenridge',2,'1991-12-31','nbraikenridge1j@google.com.hk','7915195717',NULL,NULL,1,0,'nbraikenridge1j',NULL,NULL),(2073,'r1GkmZD3T','Mariel Harvey',1,'2006-12-06','mharvey1k@goo.gl','5723018924',NULL,NULL,1,1,'mharvey1k',NULL,NULL),(2074,'nsxMOtIQ','Tabbie Cosyns',1,'2001-08-18','tcosyns1l@oracle.com','6413991336',NULL,NULL,1,1,'tcosyns1l',NULL,NULL),(2075,'LmdAI70','Obadiah Fell',2,'1993-05-27','ofell1m@columbia.edu','2844915118',NULL,NULL,1,1,'ofell1m',NULL,NULL),(2076,'BO4tOyJihV','Laurene Enstone',2,'1997-11-29','lenstone1n@thetimes.co.uk','6156512375',NULL,NULL,1,0,'lenstone1n',NULL,NULL),(2077,'84HRVFK','Berti Elan',1,'1993-09-05','belan1o@blogtalkradio.com',NULL,NULL,NULL,1,1,'belan1o',NULL,NULL),(2078,'tchTmiq9609b','Cristin Shermar',2,'1996-11-24','cshermar1p@surveymonkey.com','4098854206',NULL,NULL,1,1,'cshermar1p',NULL,NULL),(2079,'Q3AgPV6f9yD','George De Biaggi',2,'2001-09-24','gde1q@discuz.net','3082299056',NULL,NULL,1,0,'gde1q',NULL,NULL),(2080,'hkvJJrN2O','Kellby Sturdy',2,'1991-06-15','ksturdy1r@yelp.com','3632708861',NULL,NULL,1,1,'ksturdy1r',NULL,NULL),(2081,'NRzHzkGVb','Lonna Fairfull',1,'1999-02-07','lfairfull1s@elegantthemes.com','9891421844',NULL,NULL,1,1,'lfairfull1s',NULL,NULL),(2082,'PzG8s0EfHWm','Alissa Huxstep',2,'1996-06-09','ahuxstep1t@webeden.co.uk',NULL,NULL,NULL,1,1,'ahuxstep1t',NULL,NULL),(2083,'9V3HeSYg','Leif Malacrida',2,'2004-01-12','lmalacrida1u@surveymonkey.com','8323843377',NULL,NULL,1,1,'lmalacrida1u',NULL,NULL),(2084,'k2eUkdKRym','Pammie Ruthen',1,'1996-05-26','pruthen1v@wunderground.com','4766846936',NULL,NULL,1,1,'pruthen1v',NULL,NULL),(2085,'jqKNbz84aaTj','Katti Iaduccelli',1,'2001-03-25','kiaduccelli1w@accuweather.com',NULL,NULL,NULL,1,0,'kiaduccelli1w',NULL,NULL),(2086,'ZiQiCD','Ansell Skevington',1,'2003-08-30','askevington1x@fda.gov','1974422416',NULL,NULL,1,0,'askevington1x',NULL,NULL),(2087,'k7HONsJ','Arden Halliburton',1,'1998-02-12','ahalliburton1y@google.com.hk','1506312483',NULL,NULL,1,0,'ahalliburton1y',NULL,NULL),(2088,'hB3NmAprmHUQ','Grange Benitez',2,'2000-06-22','gbenitez1z@dropbox.com','2754843289',NULL,NULL,1,0,'gbenitez1z',NULL,NULL),(2089,'my5osuk','Christoforo Dragonette',1,'1999-02-16','cdragonette20@instagram.com','7428438699',NULL,NULL,1,1,'cdragonette20',NULL,NULL),(2090,'4BlO1J','Dyna Ladbury',1,'2000-08-21','dladbury21@rakuten.co.jp','9622743041',NULL,NULL,1,1,'dladbury21',NULL,NULL),(2091,'QtWxQ49v','Georges Kohnen',2,'1992-12-24','gkohnen22@newsvine.com','8556701904',NULL,NULL,1,1,'gkohnen22',NULL,NULL),(2092,'LviOgQjJV2p','Ellswerth Blague',1,'2006-01-31','eblague23@canalblog.com',NULL,NULL,NULL,1,0,'eblague23',NULL,NULL),(2093,'tLy97LVk','Dyann Dugall',2,'1995-03-29','ddugall24@hexun.com','6296008470',NULL,NULL,1,0,'ddugall24',NULL,NULL),(2094,'bwfCgiEuX','Mikey Dumsday',2,'2004-12-22','mdumsday25@tmall.com','1639675673',NULL,NULL,1,0,'mdumsday25',NULL,NULL),(2095,'uagRGfF','Julian Ayrs',2,'2001-05-21','jayrs26@exblog.jp','7759972069',NULL,NULL,1,0,'jayrs26',NULL,NULL),(2096,'np5PtX','Fritz McGibbon',2,'1992-03-04','fmcgibbon27@google.it','2612510331',NULL,NULL,1,0,'fmcgibbon27',NULL,NULL),(2097,'y2R2bXvQJF','Salem Fido',1,'2004-02-07','sfido28@godaddy.com','2689265482',NULL,NULL,1,1,'sfido28',NULL,NULL),(2098,'M97Xp0bpDAk','Dona Seldner',1,'1992-06-03','dseldner29@msu.edu','2796725263',NULL,NULL,1,1,'dseldner29',NULL,NULL),(2099,'Fw3FPGD28','Haily Bwy',1,'2004-04-02','hbwy2a@godaddy.com','6419482213',NULL,NULL,1,0,'hbwy2a',NULL,NULL),(2100,'Cdfz2XJ8EglL','Ellissa Jojic',2,'2003-01-04','ejojic2b@un.org','2109467713',NULL,NULL,1,0,'ejojic2b',NULL,NULL),(2101,'GcPEw7','Lexis Stanistrete',1,'1995-08-08','lstanistrete2c@cbc.ca','7812196044',NULL,NULL,1,0,'lstanistrete2c',NULL,NULL),(2102,'xnjEN3Hvq','Stanislas Weadick',1,'2005-10-10','sweadick2d@geocities.com','8303714664',NULL,NULL,1,1,'sweadick2d',NULL,NULL),(2103,'XX2Mq9szL','Anni Soot',1,'1999-09-26','asoot2e@pen.io','3472239279',NULL,NULL,1,1,'asoot2e',NULL,NULL),(2104,'XfnZLSZt6YxU','Georgie Quodling',1,'1992-02-08','gquodling2f@yahoo.co.jp','8464614416',NULL,NULL,1,1,'gquodling2f',NULL,NULL),(2105,'GL0q1I1ep1pD','Ernesto Starkey',2,'2004-03-08','estarkey2g@shutterfly.com','4368299055',NULL,NULL,1,1,'estarkey2g',NULL,NULL),(2106,'yQxtqEFAtUt','Jdavie Hatherleigh',1,'1993-04-28','jhatherleigh2h@t-online.de',NULL,NULL,NULL,1,0,'jhatherleigh2h',NULL,NULL),(2107,'KpvF7ohBtOD','Regen Acres',2,'1999-01-15','racres2i@rediff.com','6225481499',NULL,NULL,1,0,'racres2i',NULL,NULL),(2108,'bYIhra77Hw','Angie Truce',1,'1996-03-12','atruce2j@webeden.co.uk','2456618104',NULL,NULL,1,0,'atruce2j',NULL,NULL),(2109,'Rhs3DzjA6bqp','Bobbi Hurdis',2,'2006-08-05','bhurdis2k@flavors.me','6545702405',NULL,NULL,1,0,'bhurdis2k',NULL,NULL),(2110,'gei2k66ct7l','Burl Goullee',2,'2006-08-18','bgoullee2l@instagram.com','8854933192',NULL,NULL,1,1,'bgoullee2l',NULL,NULL),(2111,'pgVGYq','Karilynn Vasovic',2,'2000-05-31','kvasovic2m@indiegogo.com','4922109924',NULL,NULL,1,0,'kvasovic2m',NULL,NULL),(2112,'9HRvm94nEnFS','Giorgi Derington',1,'1995-06-17','gderington2n@vistaprint.com','7903794503',NULL,NULL,1,1,'gderington2n',NULL,NULL),(2113,'LAick7zZPhW','Bord Doole',2,'1996-03-27','bdoole2o@cmu.edu','4389456855',NULL,NULL,1,1,'bdoole2o',NULL,NULL),(2114,'YOHdLcyqcui','Gaye Cheese',1,'1999-02-19','gcheese2p@pbs.org',NULL,NULL,NULL,1,1,'gcheese2p',NULL,NULL),(2115,'6HOXfUPk','Chrisy Towsie',1,'2000-06-24','ctowsie2q@dion.ne.jp',NULL,NULL,NULL,1,1,'ctowsie2q',NULL,NULL),(2116,'S6BLD5qjnlqP','Berti Riggey',2,'2000-05-04','briggey2r@rakuten.co.jp','1251821086',NULL,NULL,1,1,'briggey2r',NULL,NULL),(2117,'vWIqCFdL17','Hillary Norheny',2,'2004-12-07','hnorheny2s@examiner.com','7363027978',NULL,NULL,1,0,'hnorheny2s',NULL,NULL),(2118,'XUII9wB4','Brander Mazzilli',1,'2003-06-24','bmazzilli2t@skype.com','8995931210',NULL,NULL,1,1,'bmazzilli2t',NULL,NULL),(2119,'xkbVwEXSBUET','Randie Eveling',1,'2006-08-04','reveling2u@t-online.de',NULL,NULL,NULL,1,1,'reveling2u',NULL,NULL),(2120,'Rd1vsmG','Bettine Sikora',1,'2002-09-19','bsikora2v@nih.gov','1902692487',NULL,NULL,1,1,'bsikora2v',NULL,NULL),(2121,'AshdDzfh','Nehemiah Coulton',1,'2003-09-19','ncoulton2w@google.co.jp',NULL,NULL,NULL,1,1,'ncoulton2w',NULL,NULL),(2122,'dJnl9k5grA7a','Shelley Aspall',1,'1991-10-27','saspall2x@mysql.com',NULL,NULL,NULL,1,1,'saspall2x',NULL,NULL),(2123,'n4Es4u','Giustina Labden',2,'1995-02-04','glabden2y@wiley.com',NULL,NULL,NULL,1,0,'glabden2y',NULL,NULL),(2124,'escoZmK','Chen Mounce',1,'1999-05-08','cmounce2z@forbes.com','8155381803',NULL,NULL,1,0,'cmounce2z',NULL,NULL),(2125,'1ewsHC','Kelley Lamlin',2,'2001-02-08','klamlin30@paypal.com',NULL,NULL,NULL,1,0,'klamlin30',NULL,NULL),(2126,'wlFJVaMB','Bellanca Collings',2,'2004-05-14','bcollings31@kickstarter.com','2715549120',NULL,NULL,1,1,'bcollings31',NULL,NULL),(2127,'BzIDrkY','Lianne Stallwood',1,'2006-06-08','lstallwood32@jigsy.com','6714909584',NULL,NULL,1,1,'lstallwood32',NULL,NULL),(2128,'QcnsRB','Ester Matyushenko',2,'1999-03-02','ematyushenko33@meetup.com',NULL,NULL,NULL,1,1,'ematyushenko33',NULL,NULL),(2129,'b2k5hWdN6','Patrizio Foxten',1,'1999-07-05','pfoxten34@blog.com','9271996193',NULL,NULL,1,0,'pfoxten34',NULL,NULL),(2130,'hHmHwkr','Neilla Turton',1,'2002-06-09','nturton35@networksolutions.com',NULL,NULL,NULL,1,0,'nturton35',NULL,NULL),(2131,'DZ2DqF','Roch Vearncombe',1,'2004-02-01','rvearncombe36@intel.com','9874266483',NULL,NULL,1,0,'rvearncombe36',NULL,NULL),(2132,'xJsJZP','Walther Craker',2,'1994-10-07','wcraker37@prlog.org','6982101111',NULL,NULL,1,0,'wcraker37',NULL,NULL),(2133,'3Z7cBqqw','Jazmin Basler',1,'1998-02-21','jbasler38@cornell.edu','2459366833',NULL,NULL,1,1,'jbasler38',NULL,NULL),(2134,'93HRntwf','Anny Hollyar',1,'2005-12-05','ahollyar39@blogs.com','1157578026',NULL,NULL,1,1,'ahollyar39',NULL,NULL),(2135,'MWzFhrc','Richmond Try',1,'1991-11-19','rtry3a@psu.edu','2611357494',NULL,NULL,1,1,'rtry3a',NULL,NULL),(2136,'GTEWsNzm5','Dottie Braunthal',2,'1996-08-13','dbraunthal3b@youku.com','9347654943',NULL,NULL,1,1,'dbraunthal3b',NULL,NULL),(2137,'GII0peK5seLB','Nikki St. Louis',2,'1996-11-06','nst3c@house.gov','1651793723',NULL,NULL,1,0,'nst3c',NULL,NULL),(2138,'5L1cderEHy','Baillie Stenbridge',2,'2000-01-13','bstenbridge3d@twitpic.com',NULL,NULL,NULL,1,1,'bstenbridge3d',NULL,NULL),(2139,'QJBq5rP77qMO','Lanette Dripp',2,'2001-08-09','ldripp3e@nytimes.com','5495445237',NULL,NULL,1,1,'ldripp3e',NULL,NULL),(2140,'KZ8pUgrFdM','Mirna Reubbens',1,'2001-11-08','mreubbens3f@economist.com','1485449144',NULL,NULL,1,0,'mreubbens3f',NULL,NULL),(2141,'iNutAS','Conney Clemmens',1,'2001-10-01','cclemmens3g@java.com',NULL,NULL,NULL,1,0,'cclemmens3g',NULL,NULL),(2142,'wGMclesV','Terence Chritchley',2,'2000-07-13','tchritchley3h@posterous.com',NULL,NULL,NULL,1,1,'tchritchley3h',NULL,NULL),(2143,'zlOaJ1CcdNr','Regine Murthwaite',1,'2003-02-01','rmurthwaite3i@nyu.edu',NULL,NULL,NULL,1,0,'rmurthwaite3i',NULL,NULL),(2144,'kfjrXER','Min Padefield',2,'2003-10-20','mpadefield3j@elpais.com',NULL,NULL,NULL,1,0,'mpadefield3j',NULL,NULL),(2145,'1GYpttXY','Peri Brito',2,'1999-12-27','pbrito3k@discuz.net',NULL,NULL,NULL,1,0,'pbrito3k',NULL,NULL),(2146,'kY41jxY10Ho2','Antonio LAbbet',1,'1992-09-15','alabbet3l@marriott.com','8785158569',NULL,NULL,1,1,'alabbet3l',NULL,NULL),(2147,'hMO5LaZ','Ajay Master',2,'1998-12-14','amaster3m@time.com','9147281284',NULL,NULL,1,0,'amaster3m',NULL,NULL),(2148,'JuiMWu62','Pasquale Bittleson',2,'2002-07-30','pbittleson3n@sitemeter.com',NULL,NULL,NULL,1,1,'pbittleson3n',NULL,NULL),(2149,'sTztmlSnLl','Tara Wavell',1,'1994-12-20','twavell3o@microsoft.com',NULL,NULL,NULL,1,1,'twavell3o',NULL,NULL),(2150,'jSXDmTlz','King Jacquemy',1,'2002-02-23','kjacquemy3p@marketwatch.com',NULL,NULL,NULL,1,0,'kjacquemy3p',NULL,NULL),(2151,'Ea51vJtP5','Kala Shutler',2,'2003-02-13','kshutler3q@google.fr',NULL,NULL,NULL,1,0,'kshutler3q',NULL,NULL),(2152,'xlZLMAYkhp1','Rooney Gumbley',1,'1995-09-25','rgumbley3r@surveymonkey.com','4224197431',NULL,NULL,1,0,'rgumbley3r',NULL,NULL),(2153,'wmfRHt3NCag','Danya Kew',1,'1990-06-17','dkew3s@dell.com',NULL,NULL,NULL,1,1,'dkew3s',NULL,NULL),(2154,'pvWJBIil','Tiffy Steventon',2,'2005-07-26','tsteventon3t@cocolog-nifty.com',NULL,NULL,NULL,1,1,'tsteventon3t',NULL,NULL),(2155,'0zUMY5BIL8H','Maren Genery',2,'2003-12-07','mgenery3u@blinklist.com','9493770108',NULL,NULL,1,0,'mgenery3u',NULL,NULL),(2156,'0IuOE8Vb','Luisa Fairnington',2,'2005-09-08','lfairnington3v@economist.com',NULL,NULL,NULL,1,0,'lfairnington3v',NULL,NULL),(2157,'bIUzI4c','Jewell Wagstaff',2,'1995-08-20','jwagstaff3w@nhs.uk',NULL,NULL,NULL,1,0,'jwagstaff3w',NULL,NULL),(2158,'rXe63Oad2q','Ethel Grise',1,'1991-09-09','egrise3x@slashdot.org','9798090928',NULL,NULL,1,1,'egrise3x',NULL,NULL),(2159,'AAI7YJrvN','Cherise Bartolomeotti',2,'2005-02-20','cbartolomeotti3y@biglobe.ne.jp','8846042063',NULL,NULL,1,1,'cbartolomeotti3y',NULL,NULL),(2160,'8YvnhitrCjN','Kingston Harron',1,'1995-02-12','kharron3z@1und1.de',NULL,NULL,NULL,1,0,'kharron3z',NULL,NULL),(2161,'idYd8Kctm3F','Rufe Allitt',1,'1990-04-02','rallitt40@drupal.org','4606522158',NULL,NULL,1,1,'rallitt40',NULL,NULL),(2162,'GDmBaDapJVzJ','Margaretta Borwick',1,'2003-03-29','mborwick41@paginegialle.it','5743190682',NULL,NULL,1,0,'mborwick41',NULL,NULL),(2163,'p3tnbOkQTB','Javier Attoc',1,'2005-09-02','jattoc42@ow.ly','7378862831',NULL,NULL,1,0,'jattoc42',NULL,NULL),(2164,'OpThPRH8NRG','Paula Wetherick',2,'2005-02-24','pwetherick43@independent.co.uk','4624013549',NULL,NULL,1,0,'pwetherick43',NULL,NULL),(2165,'qh33nv','Roberta Lampett',2,'1992-06-06','rlampett44@sbwire.com','7905714448',NULL,NULL,1,1,'rlampett44',NULL,NULL),(2166,'z61kDWX','Rolph Attwooll',2,'1990-03-12','rattwooll45@infoseek.co.jp','6915174380',NULL,NULL,1,1,'rattwooll45',NULL,NULL),(2167,'K2wE3OtWWOAE','Leo Mc Elory',2,'2003-07-29','lmc46@hatena.ne.jp',NULL,NULL,NULL,1,0,'lmc46',NULL,NULL),(2168,'mWVGrrJNC','Rheba Sandison',1,'2003-06-14','rsandison47@wunderground.com',NULL,NULL,NULL,1,1,'rsandison47',NULL,NULL),(2169,'LwHsMP0','Mayor Pigeram',1,'2001-02-21','mpigeram48@npr.org',NULL,NULL,NULL,1,1,'mpigeram48',NULL,NULL),(2170,'rBhWUaj','Estel Kopecka',1,'2000-05-30','ekopecka49@deviantart.com','8645798681',NULL,NULL,1,1,'ekopecka49',NULL,NULL),(2171,'nOjCvnSXSZ','Zelig Hanshaw',2,'1994-08-05','zhanshaw4a@mit.edu','6122874916',NULL,NULL,1,0,'zhanshaw4a',NULL,NULL),(2172,'Z3BUm9xh','Wait Westover',1,'1993-04-27','wwestover4b@mtv.com','5574470342',NULL,NULL,1,1,'wwestover4b',NULL,NULL),(2173,'A1ux202','Ileana Sowersby',2,'2005-10-31','isowersby4c@zimbio.com','7569716837',NULL,NULL,1,0,'isowersby4c',NULL,NULL),(2174,'MnYWqdzs','Salem Langworthy',2,'2004-08-05','slangworthy4d@answers.com','4837055962',NULL,NULL,1,1,'slangworthy4d',NULL,NULL),(2175,'61qAAajJ','Mallory Dowling',1,'1995-09-17','mdowling4e@who.int','7317337547',NULL,NULL,1,1,'mdowling4e',NULL,NULL),(2176,'VDplvfx','Gale Delong',2,'1994-04-17','gdelong4f@dailymotion.com','2592344904',NULL,NULL,1,0,'gdelong4f',NULL,NULL),(2177,'twhTRWNNDQQ','Angelika Scotfurth',2,'1997-01-08','ascotfurth4g@nydailynews.com',NULL,NULL,NULL,1,0,'ascotfurth4g',NULL,NULL),(2178,'wBmtnXJQI5','Bette Fance',2,'2005-07-10','bfance4h@bigcartel.com',NULL,NULL,NULL,1,0,'bfance4h',NULL,NULL),(2179,'4GwRpoUEU5G','Shantee Disbrey',2,'2002-10-26','sdisbrey4i@bloglovin.com','2154109177',NULL,NULL,1,0,'sdisbrey4i',NULL,NULL),(2180,'gN2ajkQ4Jt','Lewes McCaskill',1,'1995-10-04','lmccaskill4j@discovery.com',NULL,NULL,NULL,1,0,'lmccaskill4j',NULL,NULL),(2181,'vNJ8m2Oa42vL','Alida Aikenhead',1,'2004-05-25','aaikenhead4k@paginegialle.it',NULL,NULL,NULL,1,1,'aaikenhead4k',NULL,NULL),(2182,'pFCLK46mMwo','Forrest Kuhlmey',1,'2001-08-28','fkuhlmey4l@reddit.com','3707598016',NULL,NULL,1,1,'fkuhlmey4l',NULL,NULL),(2183,'3HP3uJh78Tb','Sybil Tearney',1,'2002-01-29','stearney4m@cisco.com','1127331971',NULL,NULL,1,1,'stearney4m',NULL,NULL),(2184,'xEUvmwtofb','Cart Bulgen',1,'1999-01-01','cbulgen4n@ebay.co.uk','9079914651',NULL,NULL,1,0,'cbulgen4n',NULL,NULL),(2185,'mFWEHzQSfUL','Garret Crosston',2,'1994-09-22','gcrosston4o@oracle.com',NULL,NULL,NULL,1,0,'gcrosston4o',NULL,NULL),(2186,'s9EefDT19yM8','Daisey Klimczak',2,'2003-09-30','dklimczak4p@mashable.com','3147903466',NULL,NULL,1,0,'dklimczak4p',NULL,NULL),(2187,'ZbIV6l','Franni Flemmich',1,'2002-12-04','fflemmich4q@yandex.ru','3683151754',NULL,NULL,1,1,'fflemmich4q',NULL,NULL),(2188,'ifZ16p','Cortie Cobbold',2,'1991-09-03','ccobbold4r@google.nl',NULL,NULL,NULL,1,1,'ccobbold4r',NULL,NULL),(2189,'jFJtHkxeUNp','Consuela Camellini',2,'2002-08-08','ccamellini4s@php.net',NULL,NULL,NULL,1,1,'ccamellini4s',NULL,NULL),(2190,'ZkL8moQ','Sibyl Gurner',1,'1998-01-02','sgurner4t@phoca.cz',NULL,NULL,NULL,1,0,'sgurner4t',NULL,NULL),(2191,'baKMy89','Alic Rosenblatt',1,'2006-05-11','arosenblatt4u@drupal.org','2739994935',NULL,NULL,1,1,'arosenblatt4u',NULL,NULL),(2192,'C9VDA7nNuFz','Martina Craven',2,'2003-11-09','mcraven4v@eventbrite.com','7942710326',NULL,NULL,1,0,'mcraven4v',NULL,NULL),(2193,'dRoHFu','Alaric Burtt',1,'2000-06-19','aburtt4w@networkadvertising.org','8522236097',NULL,NULL,1,1,'aburtt4w',NULL,NULL),(2194,'so5ReFAC5hC','Minni Kenryd',1,'1992-07-02','mkenryd4x@hc360.com',NULL,NULL,NULL,1,0,'mkenryd4x',NULL,NULL),(2195,'zc6gwc5uwPZ','Pearl Niesel',1,'2004-08-24','pniesel4y@naver.com','5442590917',NULL,NULL,1,0,'pniesel4y',NULL,NULL),(2196,'OwWiM1','Rafaelita Bagot',1,'1995-03-10','rbagot4z@e-recht24.de',NULL,NULL,NULL,1,1,'rbagot4z',NULL,NULL),(2197,'OXV9zvr7G','Alena Dannell',1,'1998-06-05','adannell50@nba.com',NULL,NULL,NULL,1,1,'adannell50',NULL,NULL),(2198,'BUOXn35zl','Antoinette Jenkins',1,'2005-10-13','ajenkins51@twitter.com','8965527912',NULL,NULL,1,1,'ajenkins51',NULL,NULL),(2199,'D5sStXbX','Merill Disley',2,'1996-11-28','mdisley52@harvard.edu',NULL,NULL,NULL,1,1,'mdisley52',NULL,NULL),(2200,'AjdgXlDB','Upton Carrol',2,'1990-12-05','ucarrol53@nymag.com','6973373866',NULL,NULL,1,0,'ucarrol53',NULL,NULL),(2201,'FUtFSAewlBKN','Iormina Dickenson',1,'2003-01-21','idickenson54@mayoclinic.com','4949276873',NULL,NULL,1,1,'idickenson54',NULL,NULL),(2202,'DnYRqZvN','Cirstoforo Knevet',0,'1994-12-16','cknevet55@bloglovin.com',NULL,NULL,NULL,1,1,'cknevet55',NULL,NULL),(2203,'E9Moll8fin75','Gustav Relph',1,'2002-05-27','grelph56@wiley.com',NULL,NULL,NULL,1,1,'grelph56',NULL,NULL),(2204,'ALmQjWQHi3C','Darius Cornforth',2,'2003-09-04','dcornforth57@shareasale.com',NULL,NULL,NULL,1,1,'dcornforth57',NULL,NULL),(2205,'PBTdfk2K','Dolph Perfitt',1,'2003-04-29','dperfitt58@bloomberg.com','4566143713',NULL,NULL,1,1,'dperfitt58',NULL,NULL),(2206,'8gCctQ2chB3','Lindsey Bantick',1,'1999-12-17','lbantick59@reddit.com','9819732159',NULL,NULL,1,1,'lbantick59',NULL,NULL),(2207,'5cXCOVmpstNF','Evangelin Getcliffe',2,'2001-03-21','egetcliffe5a@altervista.org','1446591294',NULL,NULL,1,1,'egetcliffe5a',NULL,NULL),(2208,'qjO8tsAutE','Marcia Pett',2,'1998-11-10','mpett5b@sun.com','6401350778',NULL,NULL,1,1,'mpett5b',NULL,NULL),(2209,'aAE5UoqSvw','Evita Paddemore',2,'2005-07-14','epaddemore5c@japanpost.jp','1659357961',NULL,NULL,1,1,'epaddemore5c',NULL,NULL),(2210,'ZLalJVGh1Q','Phaidra Sedgeworth',2,'1996-08-15','psedgeworth5d@flickr.com',NULL,NULL,NULL,1,1,'psedgeworth5d',NULL,NULL),(2211,'uazubX1X0Y','Stuart Ladson',2,'1996-07-05','sladson5e@deliciousdays.com',NULL,NULL,NULL,1,1,'sladson5e',NULL,NULL),(2212,'U4WNAlv5T','Fara Liddle',2,'1990-04-19','fliddle5f@timesonline.co.uk','1853723186',NULL,NULL,1,0,'fliddle5f',NULL,NULL),(2213,'DsBHu4IRDn','Vladamir Stadden',2,'1998-03-19','vstadden5g@infoseek.co.jp','9639047569',NULL,NULL,1,1,'vstadden5g',NULL,NULL),(2214,'9FhzkWG','Herschel Rispine',2,'1993-12-09','hrispine5h@usgs.gov','2566098181',NULL,NULL,1,1,'hrispine5h',NULL,NULL),(2215,'HDUQtagMjFA','Gerardo Remirez',1,'1992-03-19','gremirez5i@nytimes.com',NULL,NULL,NULL,1,0,'gremirez5i',NULL,NULL),(2216,'4KyvNA','Shaylah Heintsch',2,'2002-02-21','sheintsch5j@usda.gov',NULL,NULL,NULL,1,0,'sheintsch5j',NULL,NULL),(2217,'EI5nBR3oGm','Agnella Ciubutaro',1,'2003-09-26','aciubutaro5k@webmd.com','7157431356',NULL,NULL,1,1,'aciubutaro5k',NULL,NULL),(2218,'gUDttwxtE','Sofie Vasiliu',2,'1991-05-13','svasiliu5l@reverbnation.com',NULL,NULL,NULL,1,1,'svasiliu5l',NULL,NULL),(2219,'UZy0PdPIAA','Connie Horrod',2,'1998-03-13','chorrod5m@tamu.edu','1579364741',NULL,NULL,1,1,'chorrod5m',NULL,NULL),(2220,'8jmH9c3kmqPv','Rozalin Lamberth',2,'2005-07-09','rlamberth5n@discuz.net','2806083634',NULL,NULL,1,0,'rlamberth5n',NULL,NULL),(2221,'6s9YIH0lz','Blair Shambroke',1,'1994-10-18','bshambroke5o@nih.gov','7927825936',NULL,NULL,1,1,'bshambroke5o',NULL,NULL),(2222,'WWyCemy7l0w','Murvyn Caillou',0,'2002-04-11','mcaillou5p@soundcloud.com','6714322648',NULL,NULL,1,0,'mcaillou5p',NULL,NULL),(2223,'w3rd5bu6l0','Yankee Sawers',0,'2001-09-17','ysawers5q@t.co',NULL,NULL,NULL,1,0,'ysawers5q',NULL,NULL),(2224,'SEUwxhL3Y','Lenka O\'Currigan',1,'2003-02-13','locurrigan5r@123-reg.co.uk','9549083300',NULL,NULL,1,0,'locurrigan5r',NULL,NULL),(2225,'Kt3LkHUACiyv','Ysabel Rivers',0,'2000-11-15','yrivers5s@skyrock.com','8755956136',NULL,NULL,1,1,'yrivers5s',NULL,NULL),(2226,'KBWgc4o5B','Rube Worgen',0,'1999-12-01','rworgen5t@boston.com','9119471538',NULL,NULL,1,1,'rworgen5t',NULL,NULL),(2227,'FwrzrgOJ2rNM','Elnar Glen',0,'1994-06-05','eglen5u@aboutads.info',NULL,NULL,NULL,1,1,'eglen5u',NULL,NULL),(2228,'bOS2l46q4XS','Gill Maharey',1,'2000-10-01','gmaharey5v@yellowpages.com',NULL,NULL,NULL,1,1,'gmaharey5v',NULL,NULL),(2229,'i28SUyVJCc','Brianne Blackstone',1,'1990-07-30','bblackstone5w@tinypic.com',NULL,NULL,NULL,1,1,'bblackstone5w',NULL,NULL),(2230,'z9zHCcIhlJ','Maureen Monni',0,'1996-10-26','mmonni5x@mysql.com',NULL,NULL,NULL,1,0,'mmonni5x',NULL,NULL),(2231,'tcLUZvX','Don Hastler',1,'1992-12-24','dhastler5y@blog.com',NULL,NULL,NULL,1,0,'dhastler5y',NULL,NULL),(2232,'NcfbWKGXfn','Sidonnie Sends',1,'1998-07-05','ssends5z@ftc.gov',NULL,NULL,NULL,1,0,'ssends5z',NULL,NULL),(2233,'XrtL8KTqAqoJ','Codi Nutman',1,'1994-02-10','cnutman60@cbc.ca','1074152235',NULL,NULL,1,1,'cnutman60',NULL,NULL),(2234,'MlkRojq2gpEU','Annemarie Leversuch',0,'1999-11-17','aleversuch61@fastcompany.com',NULL,NULL,NULL,1,0,'aleversuch61',NULL,NULL),(2235,'sal6nJx8jRVs','Thaddus Barnsdall',1,'1991-02-14','tbarnsdall62@sitemeter.com',NULL,NULL,NULL,1,1,'tbarnsdall62',NULL,NULL),(2236,'PLzXOPJ7ZQY','Emmet Quarrington',0,'1999-11-19','equarrington63@senate.gov',NULL,NULL,NULL,1,1,'equarrington63',NULL,NULL),(2237,'ZCRnbUYLD','Padget Wakeman',1,'2004-01-29','pwakeman64@epa.gov','7158882930',NULL,NULL,1,0,'pwakeman64',NULL,NULL),(2238,'i8n9mMLl','Valenka Topes',1,'1997-06-07','vtopes65@google.ca',NULL,NULL,NULL,1,1,'vtopes65',NULL,NULL),(2239,'QA7dJzeoVv','Eveleen Kesper',1,'2005-09-27','ekesper66@aol.com',NULL,NULL,NULL,1,0,'ekesper66',NULL,NULL),(2240,'1Za2qx','Jena Perel',0,'2005-07-22','jperel67@java.com',NULL,NULL,NULL,1,0,'jperel67',NULL,NULL),(2241,'KzLsvzPqXZG','Trenton Maseyk',0,'1998-12-28','tmaseyk68@jiathis.com','2626267542',NULL,NULL,1,0,'tmaseyk68',NULL,NULL),(2242,'ZPjPM4bUYj','Zandra Pisculli',1,'2002-05-24','zpisculli69@google.ru','8919397850',NULL,NULL,1,0,'zpisculli69',NULL,NULL),(2243,'boXAdBoXkBf','Lloyd Elsmore',1,'1994-08-19','lelsmore6a@umn.edu',NULL,NULL,NULL,1,0,'lelsmore6a',NULL,NULL),(2244,'0Xx3U9nNLvm','Adriaens Ceillier',1,'1993-07-31','aceillier6b@craigslist.org','1216939376',NULL,NULL,1,0,'aceillier6b',NULL,NULL),(2245,'yLvYJXE','Adah Beinisch',1,'1996-09-11','abeinisch6c@reddit.com',NULL,NULL,NULL,1,1,'abeinisch6c',NULL,NULL),(2246,'my0qOf8ZIS','Avis Kimblin',1,'2005-03-01','akimblin6d@umich.edu',NULL,NULL,NULL,1,0,'akimblin6d',NULL,NULL),(2247,'8x4X3fK28','Anderson Giovani',1,'1996-10-05','agiovani6e@ebay.com','3749084831',NULL,NULL,1,0,'agiovani6e',NULL,NULL),(2248,'LmiWtvU8dNs','Keefer Ewbach',0,'2000-08-19','kewbach6f@bloomberg.com','6957878953',NULL,NULL,1,0,'kewbach6f',NULL,NULL),(2249,'PyGSlsO41O','Kellina Hawick',0,'1993-05-14','khawick6g@biblegateway.com',NULL,NULL,NULL,1,1,'khawick6g',NULL,NULL),(2250,'9QkfUg','Aldis Eplate',0,'1995-04-24','aeplate6h@redcross.org',NULL,NULL,NULL,1,0,'aeplate6h',NULL,NULL),(2251,'yzqZ8HSBaV5S','Hagan Posthill',1,'1990-07-02','hposthill6i@dagondesign.com','1771386950',NULL,NULL,1,0,'hposthill6i',NULL,NULL),(2252,'RqyzalO1gu73','Tracey Jerrard',1,'1997-04-29','tjerrard6j@instagram.com','3654168166',NULL,NULL,1,0,'tjerrard6j',NULL,NULL),(2253,'AWzPPp5O','Tory Eastop',1,'2002-11-21','teastop6k@prweb.com','3628851527',NULL,NULL,1,1,'teastop6k',NULL,NULL),(2254,'8yEuFzoS5','Nettle Iacavone',1,'1991-05-26','niacavone6l@topsy.com',NULL,NULL,NULL,1,1,'niacavone6l',NULL,NULL),(2255,'I5OA1MKx','Gaynor Felmingham',1,'2002-04-13','gfelmingham6m@bigcartel.com','6582501340',NULL,NULL,1,0,'gfelmingham6m',NULL,NULL),(2256,'CElUZTfqqumg','Donica Dunniom',0,'2005-11-01','ddunniom6n@blinklist.com',NULL,NULL,NULL,1,1,'ddunniom6n',NULL,NULL),(2257,'zNGbvzXu7w6v','Granny Rymmer',0,'1997-05-07','grymmer6o@youku.com','8676369502',NULL,NULL,1,1,'grymmer6o',NULL,NULL),(2258,'2M4OGwx','Stavros Bretherton',0,'1991-10-05','sbretherton6p@bloglovin.com',NULL,NULL,NULL,1,0,'sbretherton6p',NULL,NULL),(2259,'T0EcNhlCBs','Tamiko Scarre',1,'2003-11-10','tscarre6q@state.gov',NULL,NULL,NULL,1,1,'tscarre6q',NULL,NULL),(2260,'RGloIisL5','Tonnie Muldowney',1,'1991-04-13','tmuldowney6r@cnet.com',NULL,NULL,NULL,1,0,'tmuldowney6r',NULL,NULL),(2261,'BawvV2QqW','Elwin Barg',1,'1992-07-07','ebarg6s@shop-pro.jp',NULL,NULL,NULL,1,1,'ebarg6s',NULL,NULL),(2262,'sSzU051GOy','Daphne Wilce',1,'2006-08-30','dwilce6t@technorati.com','1339284049',NULL,NULL,1,0,'dwilce6t',NULL,NULL),(2263,'ysUIiSPls6','Victoria Houseley',0,'2005-05-21','vhouseley6u@mayoclinic.com','9376130995',NULL,NULL,1,0,'vhouseley6u',NULL,NULL),(2264,'uvs5wQ9vKrY','Roxine Langlois',1,'1993-06-07','rlanglois6v@feedburner.com','4399384601',NULL,NULL,1,0,'rlanglois6v',NULL,NULL),(2265,'ZwLsLLccmsa','Aldous Rouby',0,'1998-06-09','arouby6w@cbsnews.com','1658814509',NULL,NULL,1,0,'arouby6w',NULL,NULL),(2266,'aBpyrW5sn','Blithe Gorges',0,'2003-05-20','bgorges6x@printfriendly.com','9881958391',NULL,NULL,1,0,'bgorges6x',NULL,NULL),(2267,'zsuEUeXNca','Myrta Riggeard',0,'1996-01-25','mriggeard6y@wsj.com','3659087348',NULL,NULL,1,1,'mriggeard6y',NULL,NULL),(2268,'prOqcB50RCY','Leela Cosbee',0,'2005-09-15','lcosbee6z@cyberchimps.com',NULL,NULL,NULL,1,1,'lcosbee6z',NULL,NULL),(2269,'zdNwjdtvVzw','Trudie Dovidian',0,'2006-10-11','tdovidian70@google.com.au',NULL,NULL,NULL,1,0,'tdovidian70',NULL,NULL),(2270,'WMu9sU','Phip McNee',1,'1991-02-27','pmcnee71@free.fr',NULL,NULL,NULL,1,0,'pmcnee71',NULL,NULL),(2271,'XDSeyX','Kayle Rudolph',0,'1992-09-24','krudolph72@princeton.edu',NULL,NULL,NULL,1,0,'krudolph72',NULL,NULL),(2272,'YtLun5','Devon Scurrell',0,'1997-04-13','dscurrell73@wordpress.com','6345495579',NULL,NULL,1,0,'dscurrell73',NULL,NULL),(2273,'oyQDbIt','Hymie Itzak',0,'1997-11-05','hitzak74@virginia.edu',NULL,NULL,NULL,1,0,'hitzak74',NULL,NULL),(2274,'GtUfhPBSHj','Tiffani Fronzek',0,'2004-06-28','tfronzek75@sciencedirect.com',NULL,NULL,NULL,1,0,'tfronzek75',NULL,NULL),(2275,'14LTpLT','Meaghan Aspinal',1,'1997-11-22','maspinal76@census.gov','7532152550',NULL,NULL,1,0,'maspinal76',NULL,NULL),(2276,'kjtYxl3HJ3','Emlynn Wooldridge',0,'1999-09-08','ewooldridge77@hexun.com','5967708950',NULL,NULL,1,0,'ewooldridge77',NULL,NULL),(2277,'17k5Vj','Rivi Derges',0,'1998-11-30','rderges78@angelfire.com','1573908768',NULL,NULL,1,0,'rderges78',NULL,NULL),(2278,'8wu7WfuqV','Hugibert Hackett',0,'1992-11-10','hhackett79@latimes.com','9405252804',NULL,NULL,1,0,'hhackett79',NULL,NULL),(2279,'DPIWm1NR','Gusella Brun',1,'1994-06-08','gbrun7a@slashdot.org',NULL,NULL,NULL,1,0,'gbrun7a',NULL,NULL),(2280,'cuthEJ0','Rodolphe Mouatt',1,'1999-04-20','rmouatt7b@lycos.com',NULL,NULL,NULL,1,1,'rmouatt7b',NULL,NULL),(2281,'zDwJ8AtmnF','Bibby Mashal',1,'1998-03-31','bmashal7c@cpanel.net',NULL,NULL,NULL,1,0,'bmashal7c',NULL,NULL),(2282,'JZSXx8D','Angeli Prothero',1,'1995-01-29','aprothero7d@networksolutions.com','8781094010',NULL,NULL,1,1,'aprothero7d',NULL,NULL),(2283,'OYKv3Npea','Miranda Busse',1,'1996-04-25','mbusse7e@yahoo.co.jp','2068887537',NULL,NULL,1,1,'mbusse7e',NULL,NULL),(2284,'ZneWzs6Jb','Meier Yule',0,'1996-07-23','myule7f@yellowbook.com','1958681858',NULL,NULL,1,0,'myule7f',NULL,NULL),(2285,'jViythiZHgf','Mordy Dixsee',1,'2000-12-26','mdixsee7g@wp.com','8323909492',NULL,NULL,1,0,'mdixsee7g',NULL,NULL),(2286,'W8C3BWLd','Corene Greenwood',0,'2002-08-11','cgreenwood7h@quantcast.com','7186241068',NULL,NULL,1,1,'cgreenwood7h',NULL,NULL),(2287,'u7EEQI68','Erskine Hansie',0,'2000-10-25','ehansie7i@phpbb.com',NULL,NULL,NULL,1,0,'ehansie7i',NULL,NULL),(2288,'Llp9IboN','Sallee Coryndon',0,'2004-11-20','scoryndon7j@google.pl',NULL,NULL,NULL,1,1,'scoryndon7j',NULL,NULL),(2289,'9bKOSd63K','Jerrome McGrorty',1,'1995-06-10','jmcgrorty7k@ycombinator.com',NULL,NULL,NULL,1,1,'jmcgrorty7k',NULL,NULL),(2290,'E2NcLFA','Leticia Haliburton',0,'2000-06-22','lhaliburton7l@businessinsider.com','1542150587',NULL,NULL,1,1,'lhaliburton7l',NULL,NULL),(2291,'DQdh2CFA','Irene Kleynen',1,'2001-04-14','ikleynen7m@chronoengine.com',NULL,NULL,NULL,1,0,'ikleynen7m',NULL,NULL),(2292,'llltvo','Rafaello Hellmore',0,'2003-11-02','rhellmore7n@flickr.com','5011710646',NULL,NULL,1,0,'rhellmore7n',NULL,NULL),(2293,'mAQE9s','Francklyn Svanini',1,'2000-08-28','fsvanini7o@sciencedirect.com','3644730002',NULL,NULL,1,1,'fsvanini7o',NULL,NULL),(2294,'q1e0fcAPB6U','Axe Boatright',1,'1999-02-09','aboatright7p@so-net.ne.jp','2715476903',NULL,NULL,1,1,'aboatright7p',NULL,NULL),(2295,'kIS84SodKb','Charmion Storrock',1,'2005-02-01','cstorrock7q@wordpress.org','4129286993',NULL,NULL,1,1,'cstorrock7q',NULL,NULL),(2296,'2IXt5aXZaTG','Georgeanne Lyon',1,'1992-02-21','glyon7r@blogs.com','1821103520',NULL,NULL,1,0,'glyon7r',NULL,NULL),(2297,'TF0hEFSw1','Lavena Villaret',0,'2004-08-27','lvillaret7s@ycombinator.com','6169207001',NULL,NULL,1,1,'lvillaret7s',NULL,NULL),(2298,'cmATVQ9l8sLR','Iggie Simo',0,'1993-12-28','isimo7t@cafepress.com',NULL,NULL,NULL,1,0,'isimo7t',NULL,NULL),(2299,'dGjlnVaOZOQ','Guillema Lawrinson',1,'2002-08-14','glawrinson7u@marketwatch.com','8308385723',NULL,NULL,1,1,'glawrinson7u',NULL,NULL),(2300,'tTsKiQG','Carley Shoosmith',0,'2004-04-22','cshoosmith7v@g.co','6874072009',NULL,NULL,1,1,'cshoosmith7v',NULL,NULL),(2301,'h3f1M3RP','Katusha Stockey',1,'1997-08-31','kstockey7w@jalbum.net',NULL,NULL,NULL,1,1,'kstockey7w',NULL,NULL),(2302,'6Ox1wkIReus','Glennie Vant',1,'1991-07-29','gvant7x@thetimes.co.uk','8823127650',NULL,NULL,1,1,'gvant7x',NULL,NULL),(2303,'6aBF7tzw','Margareta Griggs',0,'2003-06-18','mgriggs7y@posterous.com','9318272068',NULL,NULL,1,0,'mgriggs7y',NULL,NULL),(2304,'2Ib1tN95Ooi6','Tarrance Targett',1,'1994-06-23','ttargett7z@patch.com','5787484531',NULL,NULL,1,1,'ttargett7z',NULL,NULL),(2305,'cPFfsX5','Stormi Blackey',0,'1991-02-23','sblackey80@wunderground.com','7598312828',NULL,NULL,1,1,'sblackey80',NULL,NULL),(2306,'1VpIacG','Bess Dumphry',1,'1999-06-29','bdumphry81@arstechnica.com','7227523510',NULL,NULL,1,0,'bdumphry81',NULL,NULL),(2307,'AeWSO7MyS','Lisha Jedrzejczyk',1,'1992-07-26','ljedrzejczyk82@unblog.fr',NULL,NULL,NULL,1,1,'ljedrzejczyk82',NULL,NULL),(2308,'9fOp8l7VkfR','Estelle Sima',0,'2001-04-29','esima83@blogs.com',NULL,NULL,NULL,1,0,'esima83',NULL,NULL),(2309,'5tExJ2Rmty','Trefor Stiddard',0,'1997-09-27','tstiddard84@patch.com','9471748311',NULL,NULL,1,1,'tstiddard84',NULL,NULL),(2310,'AynZzA','Bartholomew Shucksmith',1,'2004-10-11','bshucksmith85@disqus.com',NULL,NULL,NULL,1,1,'bshucksmith85',NULL,NULL),(2311,'AtZsxrCCYY','Sumner Sloane',1,'2002-06-08','ssloane86@exblog.jp','1538998648',NULL,NULL,1,0,'ssloane86',NULL,NULL),(2312,'5A9O1Q','Leia Tytterton',0,'1993-10-28','ltytterton87@123-reg.co.uk','7597709295',NULL,NULL,1,1,'ltytterton87',NULL,NULL),(2313,'WY7ZM1AmAlfY','Noelle Casel',0,'2002-09-22','ncasel88@umn.edu',NULL,NULL,NULL,1,1,'ncasel88',NULL,NULL),(2314,'dMmRmISih','Karita O\'Connolly',1,'2004-07-19','koconnolly89@google.fr',NULL,NULL,NULL,1,0,'koconnolly89',NULL,NULL),(2315,'xvQ2ewFnqr','Gery Axten',0,'1995-10-15','gaxten8a@archive.org',NULL,NULL,NULL,1,0,'gaxten8a',NULL,NULL),(2316,'FTNIF8S','Ric Guitte',0,'1994-12-19','rguitte8b@bbc.co.uk','5897710157',NULL,NULL,1,0,'rguitte8b',NULL,NULL),(2317,'pURlMQ','Rinaldo Geillier',0,'1994-04-14','rgeillier8c@intel.com',NULL,NULL,NULL,1,0,'rgeillier8c',NULL,NULL),(2318,'nqy9aA5B','Corly Martinek',0,'2006-08-21','cmartinek8d@chron.com',NULL,NULL,NULL,1,1,'cmartinek8d',NULL,NULL),(2319,'ryp5ulDIpgnp','Tonya Mathie',0,'2005-07-30','tmathie8e@seattletimes.com','6386809776',NULL,NULL,1,1,'tmathie8e',NULL,NULL),(2320,'dL2SXYcIbH','Ezri Beakes',1,'1996-05-09','ebeakes8f@sun.com','5524525530',NULL,NULL,1,1,'ebeakes8f',NULL,NULL),(2321,'LNjJaGzUzPN','Isac Lockley',1,'1996-06-09','ilockley8g@nbcnews.com',NULL,NULL,NULL,1,0,'ilockley8g',NULL,NULL),(2322,'yztXV0O1u','Josias Sidaway',1,'1990-09-17','jsidaway8h@washington.edu',NULL,NULL,NULL,1,1,'jsidaway8h',NULL,NULL),(2323,'HzdxVXLDu6G','Jo-ann Steventon',1,'2000-07-08','jsteventon8i@pbs.org','8966400002',NULL,NULL,1,1,'jsteventon8i',NULL,NULL),(2324,'vNpEmDr3DA','Lezlie Brazel',0,'1995-11-13','lbrazel8j@ycombinator.com','9358158458',NULL,NULL,1,0,'lbrazel8j',NULL,NULL),(2325,'s6J2T9tP','Janetta Palffrey',1,'1991-07-13','jpalffrey8k@wiley.com','6774941152',NULL,NULL,1,0,'jpalffrey8k',NULL,NULL),(2326,'okflpS2Rlfwh','Randolf Asmus',1,'2002-08-17','rasmus8l@bigcartel.com','1584607249',NULL,NULL,1,1,'rasmus8l',NULL,NULL),(2327,'dSdw3URNif','Catlin Boxall',1,'1994-08-16','cboxall8m@springer.com','7014114032',NULL,NULL,1,1,'cboxall8m',NULL,NULL),(2328,'dg6YFJYXc','Lotte Hillborne',1,'1993-12-01','lhillborne8n@army.mil','7418435529',NULL,NULL,1,0,'lhillborne8n',NULL,NULL),(2329,'MF8jaX','Ruprecht Bodman',1,'1995-01-10','rbodman8o@diigo.com',NULL,NULL,NULL,1,1,'rbodman8o',NULL,NULL),(2330,'JmKaEqv','Rozanna Goodchild',0,'2004-11-23','rgoodchild8p@marketwatch.com',NULL,NULL,NULL,1,1,'rgoodchild8p',NULL,NULL),(2331,'xaWpz6Ls5FY','Josefina Carneck',0,'2005-01-25','jcarneck8q@mac.com',NULL,NULL,NULL,1,1,'jcarneck8q',NULL,NULL),(2332,'IXqs2VBPj0PL','Sasha Forsdyke',0,'1995-11-03','sforsdyke8r@ibm.com','5791200209',NULL,NULL,1,1,'sforsdyke8r',NULL,NULL),(2333,'GaDFbz','Cathleen Guyer',0,'2000-04-27','cguyer8s@list-manage.com','1407162306',NULL,NULL,1,1,'cguyer8s',NULL,NULL),(2334,'rkIz2HcVHn','Osbourne Alker',1,'1990-02-08','oalker8t@ifeng.com','2928828374',NULL,NULL,1,1,'oalker8t',NULL,NULL),(2335,'cMHXBPtY','Conroy O\'Grada',1,'1996-11-20','cograda8u@nba.com','5104771313',NULL,NULL,1,0,'cograda8u',NULL,NULL),(2336,'fyYRVVy','Biron Merali',0,'1999-12-22','bmerali8v@csmonitor.com',NULL,NULL,NULL,1,1,'bmerali8v',NULL,NULL),(2337,'FwC81kmYD','Amerigo Shutle',1,'2002-03-14','ashutle8w@google.com.hk',NULL,NULL,NULL,1,1,'ashutle8w',NULL,NULL),(2338,'psod7IwB','Devland Boggas',0,'2004-03-18','dboggas8x@ezinearticles.com','1989965831',NULL,NULL,1,1,'dboggas8x',NULL,NULL),(2339,'FHIz3dpBM7EH','Eulalie Toxell',1,'2006-06-13','etoxell8y@weather.com','7444685628',NULL,NULL,1,1,'etoxell8y',NULL,NULL),(2340,'tE0wSWY','Ianthe Pimer',1,'2005-12-19','ipimer8z@e-recht24.de',NULL,NULL,NULL,1,0,'ipimer8z',NULL,NULL),(2341,'yi0HtjG9QEY','Noelani Wiltshier',0,'2006-04-16','nwiltshier90@senate.gov',NULL,NULL,NULL,1,1,'nwiltshier90',NULL,NULL),(2342,'vi7zhit','Celene Swalough',1,'2004-07-07','cswalough91@domainmarket.com',NULL,NULL,NULL,1,1,'cswalough91',NULL,NULL),(2343,'taW5jDZxet','Valene O\'Finan',1,'2004-03-04','vofinan92@ebay.co.uk','7649093745',NULL,NULL,1,1,'vofinan92',NULL,NULL),(2344,'1suAuny','Aidan Peggrem',0,'2000-04-16','apeggrem93@goodreads.com','4642459513',NULL,NULL,1,0,'apeggrem93',NULL,NULL),(2345,'fXldHnRV','Brew Birchenough',0,'1997-11-12','bbirchenough94@goo.gl','2153370684',NULL,NULL,1,1,'bbirchenough94',NULL,NULL),(2346,'VkaSeFBZsp75','Dewitt Farquharson',0,'2002-11-06','dfarquharson95@geocities.com','6838662881',NULL,NULL,1,0,'dfarquharson95',NULL,NULL),(2347,'f5mEHh','Glen Weetch',1,'1998-02-04','gweetch96@nymag.com',NULL,NULL,NULL,1,1,'gweetch96',NULL,NULL),(2348,'T0hQ9N8L','Davide Barlow',0,'1992-12-19','dbarlow97@phoca.cz',NULL,NULL,NULL,1,1,'dbarlow97',NULL,NULL),(2349,'HLLHPWW','Sharla Meechan',0,'1996-09-19','smeechan98@omniture.com','1583448644',NULL,NULL,1,1,'smeechan98',NULL,NULL),(2350,'FnWqBm','Angela Kleinplatz',0,'1993-10-14','akleinplatz99@ucoz.ru',NULL,NULL,NULL,1,1,'akleinplatz99',NULL,NULL),(2351,'ZMQw8cJQiRc','Giraud MacGahy',0,'1990-02-14','gmacgahy9a@ask.com','4993186088',NULL,NULL,1,1,'gmacgahy9a',NULL,NULL),(2352,'F2SiDRURnTQw','Thayne Delue',0,'1990-01-23','tdelue9b@independent.co.uk',NULL,NULL,NULL,1,0,'tdelue9b',NULL,NULL),(2353,'YX3gUrM1','Angie Marzele',1,'1996-02-01','amarzele9c@hp.com',NULL,NULL,NULL,1,0,'amarzele9c',NULL,NULL),(2354,'cUNyaLq','Marney Colicot',1,'1991-02-08','mcolicot9d@google.de',NULL,NULL,NULL,1,1,'mcolicot9d',NULL,NULL),(2355,'h72zp7','Gasparo Shackesby',0,'2001-06-21','gshackesby9e@narod.ru','8009493090',NULL,NULL,1,1,'gshackesby9e',NULL,NULL),(2356,'FzXmGw','Gawen Ballance',0,'2002-04-28','gballance9f@adobe.com',NULL,NULL,NULL,1,0,'gballance9f',NULL,NULL),(2357,'4vgkTXf','Kain Foale',1,'1992-04-30','kfoale9g@mashable.com',NULL,NULL,NULL,1,1,'kfoale9g',NULL,NULL),(2358,'FbLR29Mi','Lucine Chaves',0,'1990-04-15','lchaves9h@irs.gov','2421617588',NULL,NULL,1,1,'lchaves9h',NULL,NULL),(2359,'AKllB1','Mercedes Akerman',1,'2003-01-28','makerman9i@bigcartel.com','1333463710',NULL,NULL,1,0,'makerman9i',NULL,NULL),(2360,'nqeSruDeTZ1w','Dorothea Lisciandri',0,'1998-10-18','dlisciandri9j@bing.com',NULL,NULL,NULL,1,0,'dlisciandri9j',NULL,NULL),(2361,'lparEHaNd','Carlynne Scanterbury',0,'2004-08-08','cscanterbury9k@fotki.com','7564845828',NULL,NULL,1,1,'cscanterbury9k',NULL,NULL),(2362,'YAWu1D','Editha Barroux',0,'2006-09-21','ebarroux9l@liveinternet.ru',NULL,NULL,NULL,1,0,'ebarroux9l',NULL,NULL),(2363,'WbMTzrUB','Coletta Emerson',1,'1999-09-29','cemerson9m@yale.edu',NULL,NULL,NULL,1,1,'cemerson9m',NULL,NULL),(2364,'ksQy2esyCiO','Abbe Kaes',0,'2002-11-25','akaes9n@dot.gov',NULL,NULL,NULL,1,1,'akaes9n',NULL,NULL),(2365,'he1OzT8B','Errol Fonteyne',1,'2000-09-11','efonteyne9o@friendfeed.com',NULL,NULL,NULL,1,0,'efonteyne9o',NULL,NULL),(2366,'yfJF6ek','Nadean Mc Elory',0,'1992-11-01','nmc9p@xing.com','1997436606',NULL,NULL,1,1,'nmc9p',NULL,NULL),(2367,'8IbRBzTzdlj','Calla Spinley',1,'2006-11-01','cspinley9q@technorati.com','2505655619',NULL,NULL,1,1,'cspinley9q',NULL,NULL),(2368,'tzCUBJ8e7Re','Forest Condell',0,'2000-01-18','fcondell9r@t-online.de','8917585009',NULL,NULL,1,0,'fcondell9r',NULL,NULL),(2369,'VSGPjGiL4o','Marie-ann Molyneaux',1,'1993-08-02','mmolyneaux9s@alibaba.com','2776889955',NULL,NULL,1,0,'mmolyneaux9s',NULL,NULL),(2370,'KanDsIPwDM0','Fernanda Santos',0,'1999-03-05','fsantos9t@about.me',NULL,NULL,NULL,1,1,'fsantos9t',NULL,NULL),(2371,'AX1T1S1hNEYX','Dylan Prothero',0,'2006-10-12','dprothero9u@scientificamerican.com','7406041495',NULL,NULL,1,0,'dprothero9u',NULL,NULL),(2372,'FiNkvyK','Fae Riseley',0,'2005-06-19','friseley9v@mayoclinic.com',NULL,NULL,NULL,1,1,'friseley9v',NULL,NULL),(2373,'7hktVr','Darcie Risdale',1,'1999-12-16','drisdale9w@list-manage.com',NULL,NULL,NULL,1,0,'drisdale9w',NULL,NULL),(2374,'l1xomRmys6','Veda Agar',1,'2001-09-25','vagar9x@csmonitor.com','2434368036',NULL,NULL,1,0,'vagar9x',NULL,NULL),(2375,'LAOycMTsfee','Pauline Vagges',1,'2005-08-19','pvagges9y@paginegialle.it','2898973991',NULL,NULL,1,0,'pvagges9y',NULL,NULL),(2376,'oyfTShOjOh','Norry Dalley',1,'1993-02-06','ndalley9z@jalbum.net','6046162568',NULL,NULL,1,1,'ndalley9z',NULL,NULL),(2377,'8KcGddbk','Geri Paterson',0,'1999-08-29','gpatersona0@ibm.com',NULL,NULL,NULL,1,1,'gpatersona0',NULL,NULL),(2378,'OUFRCoRR2i','Anne Hulke',0,'2006-09-19','ahulkea1@nydailynews.com',NULL,NULL,NULL,1,0,'ahulkea1',NULL,NULL),(2379,'960wyFfrj5IO','Abba Whitloe',0,'2006-01-27','awhitloea2@drupal.org','8702909680',NULL,NULL,1,1,'awhitloea2',NULL,NULL),(2380,'EJtqM5','Harman Spanswick',1,'1992-10-24','hspanswicka3@reddit.com','6865743950',NULL,NULL,1,0,'hspanswicka3',NULL,NULL),(2381,'4NdxIO2u','Hatti Dillingham',0,'1991-02-12','hdillinghama4@blinklist.com',NULL,NULL,NULL,1,0,'hdillinghama4',NULL,NULL),(2382,'HTQOu8qD','Paton Gauford',0,'2004-11-21','pgauforda5@google.ca','6699242777',NULL,NULL,1,1,'pgauforda5',NULL,NULL),(2383,'CmrLUowux','Dilly Rawet',1,'2006-09-16','draweta6@soundcloud.com','2323151907',NULL,NULL,1,1,'draweta6',NULL,NULL),(2384,'P17aCArVlEJ','Jayme Ducker',0,'2005-10-17','jduckera7@cbslocal.com',NULL,NULL,NULL,1,0,'jduckera7',NULL,NULL),(2385,'XmYhaFWn6','Franny Nevison',0,'1999-03-26','fnevisona8@nsw.gov.au',NULL,NULL,NULL,1,1,'fnevisona8',NULL,NULL),(2386,'gjfeopgpcQ','Dru Pentony',0,'2003-07-25','dpentonya9@tinypic.com',NULL,NULL,NULL,1,0,'dpentonya9',NULL,NULL),(2387,'TGHkoXOMFQL','Marc Allott',1,'2005-06-05','mallottaa@prlog.org','4722429642',NULL,NULL,1,1,'mallottaa',NULL,NULL),(2388,'rQ2DRFVePdBc','Noam Rillatt',0,'2000-11-24','nrillattab@feedburner.com','2762125120',NULL,NULL,1,0,'nrillattab',NULL,NULL),(2389,'znbEhz','Quinton Biddlecombe',0,'2005-10-27','qbiddlecombeac@netscape.com','9469401404',NULL,NULL,1,0,'qbiddlecombeac',NULL,NULL),(2390,'YAraHR','Kingsley Zammett',0,'2005-11-05','kzammettad@who.int','4062307084',NULL,NULL,1,1,'kzammettad',NULL,NULL),(2391,'ahPfV3','Patrica Lesek',0,'1995-01-09','plesekae@answers.com','1743641348',NULL,NULL,1,1,'plesekae',NULL,NULL),(2392,'yRASqzx','Wendall Douch',0,'1998-02-20','wdouchaf@over-blog.com','1886219847',NULL,NULL,1,0,'wdouchaf',NULL,NULL),(2393,'1fDmMqFs','Wilmar Meharg',0,'1997-07-31','wmehargag@merriam-webster.com','2186637144',NULL,NULL,1,1,'wmehargag',NULL,NULL),(2394,'sndTXYlI','Sam Banbrigge',0,'2005-12-25','sbanbriggeah@google.com.br',NULL,NULL,NULL,1,1,'sbanbriggeah',NULL,NULL),(2395,'th5gz0uK','Adele Shory',0,'2004-05-28','ashoryai@soup.io','9839629327',NULL,NULL,1,0,'ashoryai',NULL,NULL),(2396,'qXdkYeku','Mary Demeter',1,'2006-02-15','mdemeteraj@weibo.com','5637958592',NULL,NULL,1,1,'mdemeteraj',NULL,NULL),(2397,'89bWZFPYQ','Linet Pickavance',0,'1998-10-16','lpickavanceak@typepad.com','8951557153',NULL,NULL,1,0,'lpickavanceak',NULL,NULL),(2398,'83rT2jAIS46V','Wren Baiyle',1,'1998-11-09','wbaiyleal@berkeley.edu','8144620245',NULL,NULL,1,1,'wbaiyleal',NULL,NULL),(2399,'qdzqFdiSP3','Sherri Begwell',1,'1995-08-03','sbegwellam@latimes.com','3425865049',NULL,NULL,1,1,'sbegwellam',NULL,NULL),(2400,'9x2BdqJ','Clarice McAster',1,'1991-04-30','cmcasteran@histats.com','5829944079',NULL,NULL,1,0,'cmcasteran',NULL,NULL),(2401,'DZvF0CApmEI3','Jenny Groucutt',1,'2000-05-07','jgroucuttao@samsung.com','6158773459',NULL,NULL,1,1,'jgroucuttao',NULL,NULL),(2402,'Iu7brc','Aldon Mazillius',0,'2002-06-20','amazilliusap@noaa.gov','5907858306',NULL,NULL,1,0,'amazilliusap',NULL,NULL),(2403,'5vIgwkmqUr','Donal Siggee',0,'2000-08-11','dsiggeeaq@hostgator.com',NULL,NULL,NULL,1,1,'dsiggeeaq',NULL,NULL),(2404,'jSW4WC4','Jermayne Bahlmann',0,'1990-03-25','jbahlmannar@howstuffworks.com','5382387725',NULL,NULL,1,0,'jbahlmannar',NULL,NULL),(2405,'edqKqp','Jacqueline Cruft',0,'1996-02-10','jcruftas@prnewswire.com','7725238425',NULL,NULL,1,1,'jcruftas',NULL,NULL),(2406,'vYKFFqS','Odell Scotter',1,'1997-01-17','oscotterat@umich.edu',NULL,NULL,NULL,1,0,'oscotterat',NULL,NULL),(2407,'Agzk0f9P','Sebastiano Arnell',1,'2000-04-21','sarnellau@apache.org',NULL,NULL,NULL,1,1,'sarnellau',NULL,NULL),(2408,'0NLxYMOd21','Mile Rymill',1,'2005-03-16','mrymillav@pagesperso-orange.fr',NULL,NULL,NULL,1,0,'mrymillav',NULL,NULL),(2409,'BcM7w1ZD4','Morly Roan',1,'1990-09-16','mroanaw@nsw.gov.au','7709626275',NULL,NULL,1,0,'mroanaw',NULL,NULL),(2410,'afcbfl9SDIh','Blanch Bennitt',1,'2005-11-07','bbennittax@wp.com','5441652022',NULL,NULL,1,1,'bbennittax',NULL,NULL),(2411,'07VcIoi2','Yancy Davet',1,'1992-01-21','ydavetay@sciencedirect.com',NULL,NULL,NULL,1,0,'ydavetay',NULL,NULL),(2412,'26ix8nm','Tobin Turbard',0,'1996-10-16','tturbardaz@dmoz.org','8645776946',NULL,NULL,1,1,'tturbardaz',NULL,NULL),(2413,'IjCTENAVRD','Jud Crosson',1,'1990-04-06','jcrossonb0@exblog.jp','2538489088',NULL,NULL,1,0,'jcrossonb0',NULL,NULL),(2414,'Z95XENX','Cecilia Bompas',1,'2006-05-18','cbompasb1@comsenz.com',NULL,NULL,NULL,1,1,'cbompasb1',NULL,NULL),(2415,'hq6A7N','Ashton Wigginton',0,'1995-05-03','awiggintonb2@multiply.com','6154355433',NULL,NULL,1,0,'awiggintonb2',NULL,NULL),(2416,'gmwGcj','Cyndie Gabbott',0,'2001-03-05','cgabbottb3@paginegialle.it','8646497861',NULL,NULL,1,1,'cgabbottb3',NULL,NULL),(2417,'nSjyfoGg3','Terrill Bunning',0,'1991-09-27','tbunningb4@google.cn',NULL,NULL,NULL,1,1,'tbunningb4',NULL,NULL),(2418,'mO9otA82wuyS','Jaymee Portingale',1,'1994-11-01','jportingaleb5@ebay.com','8627738202',NULL,NULL,1,0,'jportingaleb5',NULL,NULL),(2419,'jhinLSV6fL6t','Danie Petzolt',0,'1997-12-22','dpetzoltb6@samsung.com','5601200282',NULL,NULL,1,0,'dpetzoltb6',NULL,NULL),(2420,'fSc8AcHwrBK','Ewell Lombardo',1,'1995-01-21','elombardob7@goodreads.com','5799647486',NULL,NULL,1,0,'elombardob7',NULL,NULL),(2421,'T4RkNqQ7NhAB','Laurie Smythe',0,'1996-01-27','lsmytheb8@virginia.edu','3011029028',NULL,NULL,1,0,'lsmytheb8',NULL,NULL),(2422,'KfPwvw','Kimberly Yarranton',0,'1992-10-01','kyarrantonb9@people.com.cn','5923514055',NULL,NULL,1,1,'kyarrantonb9',NULL,NULL),(2423,'34h432vE','Jeff Hellin',0,'2005-08-19','jhellinba@ifeng.com','7372148537',NULL,NULL,1,0,'jhellinba',NULL,NULL),(2424,'g0G5plhaxz9V','Cirillo Beades',1,'2002-03-21','cbeadesbb@gov.uk',NULL,NULL,NULL,1,1,'cbeadesbb',NULL,NULL),(2425,'RWCXjax','Hayden Amyes',1,'1990-12-22','hamyesbc@a8.net','5986791022',NULL,NULL,1,0,'hamyesbc',NULL,NULL),(2426,'jFQANE7BdXQL','Shay Gallanders',0,'2001-01-26','sgallandersbd@posterous.com','2765612639',NULL,NULL,1,1,'sgallandersbd',NULL,NULL),(2427,'wqo0gppJYbqY','Lynnette Bulcroft',1,'1998-08-28','lbulcroftbe@dropbox.com','7548051242',NULL,NULL,1,1,'lbulcroftbe',NULL,NULL),(2428,'VannIz','Annabela Rowantree',1,'1994-02-07','arowantreebf@wunderground.com','1424997293',NULL,NULL,1,1,'arowantreebf',NULL,NULL),(2429,'lf99BNIHi9P','Gretchen Bispham',1,'1991-08-05','gbisphambg@ehow.com','8285459922',NULL,NULL,1,1,'gbisphambg',NULL,NULL),(2430,'Dccf2b','Stacee Danielski',0,'2002-01-31','sdanielskibh@privacy.gov.au','2147894397',NULL,NULL,1,0,'sdanielskibh',NULL,NULL),(2431,'ExrD1fpA','Bethany Pawlaczyk',0,'1997-07-01','bpawlaczykbi@joomla.org',NULL,NULL,NULL,1,1,'bpawlaczykbi',NULL,NULL),(2432,'P22u1832','Galven Polendine',0,'1994-08-04','gpolendinebj@ihg.com','9289481877',NULL,NULL,1,1,'gpolendinebj',NULL,NULL),(2433,'uJFvi3GIcBPC','Dorelia Balogh',0,'1996-07-23','dbaloghbk@cyberchimps.com',NULL,NULL,NULL,1,1,'dbaloghbk',NULL,NULL),(2434,'GUCV9x','Trisha Gonet',0,'2000-12-17','tgonetbl@java.com','7492659863',NULL,NULL,1,1,'tgonetbl',NULL,NULL),(2435,'BwkxTISTPo','Melisande Gurton',1,'2005-05-24','mgurtonbm@drupal.org','1341660323',NULL,NULL,1,0,'mgurtonbm',NULL,NULL),(2436,'CWRy0f','Dorthea Sacco',1,'1999-08-12','dsaccobn@kickstarter.com','8691594189',NULL,NULL,1,0,'dsaccobn',NULL,NULL),(2437,'LWWlaI','Ted Wooton',1,'2000-04-13','twootonbo@php.net',NULL,NULL,NULL,1,0,'twootonbo',NULL,NULL),(2438,'g21xqy','Lilith Blinder',1,'1996-07-11','lblinderbp@usnews.com','4131940758',NULL,NULL,1,1,'lblinderbp',NULL,NULL),(2439,'2gQubYB','Murray Mathivon',0,'1998-11-30','mmathivonbq@google.fr',NULL,NULL,NULL,1,0,'mmathivonbq',NULL,NULL),(2440,'TGKAub','Dorelia Stoak',1,'2005-12-10','dstoakbr@engadget.com','6527166688',NULL,NULL,1,0,'dstoakbr',NULL,NULL),(2441,'htEjhG','Megan Dowrey',0,'2004-05-31','mdowreybs@booking.com','4113779820',NULL,NULL,1,0,'mdowreybs',NULL,NULL),(2442,'DEM6saxH','Maurine Thurske',0,'1992-03-14','mthurskebt@sina.com.cn','8391600182',NULL,NULL,1,1,'mthurskebt',NULL,NULL),(2443,'dHZzf3DkobSL','Sanford Possell',0,'1993-06-09','spossellbu@multiply.com','8488926792',NULL,NULL,1,0,'spossellbu',NULL,NULL),(2444,'k6pDpV','Henryetta LAbbet',0,'1994-12-10','hlabbetbv@economist.com',NULL,NULL,NULL,1,0,'hlabbetbv',NULL,NULL),(2445,'iOh4tGj','Davon Chinnick',0,'1991-03-10','dchinnickbw@vimeo.com','1647762059',NULL,NULL,1,1,'dchinnickbw',NULL,NULL),(2446,'WZypjKpqF70d','Shirlene Shevill',1,'1999-03-14','sshevillbx@elpais.com','5682768565',NULL,NULL,1,1,'sshevillbx',NULL,NULL),(2447,'g3WEZccUZC','Fonzie Gedling',1,'1994-04-09','fgedlingby@senate.gov','7693892129',NULL,NULL,1,0,'fgedlingby',NULL,NULL),(2448,'N2fuXFUDaT0j','Giacobo Gorusso',0,'1997-10-06','ggorussobz@sitemeter.com','5825019916',NULL,NULL,1,0,'ggorussobz',NULL,NULL),(2449,'s98aGPn','Harbert Risborough',0,'2005-06-19','hrisboroughc0@live.com',NULL,NULL,NULL,1,0,'hrisboroughc0',NULL,NULL),(2450,'R03dJfs0PO','Harley Fissenden',1,'1997-04-11','hfissendenc1@theglobeandmail.com','5635454368',NULL,NULL,1,0,'hfissendenc1',NULL,NULL),(2451,'YpTehNxKU','Paton Quinet',0,'1991-10-20','pquinetc2@hao123.com','2465169445',NULL,NULL,1,1,'pquinetc2',NULL,NULL),(2452,'a3Z1EBIf','Tori Mews',1,'1999-06-03','tmewsc3@4shared.com','5774077163',NULL,NULL,1,0,'tmewsc3',NULL,NULL),(2453,'3I3PUEx6g','Orton Trangmar',0,'1995-09-14','otrangmarc4@spiegel.de',NULL,NULL,NULL,1,0,'otrangmarc4',NULL,NULL),(2454,'b6SDQluXPA','Verine Strelitzer',1,'2001-08-06','vstrelitzerc5@google.fr',NULL,NULL,NULL,1,0,'vstrelitzerc5',NULL,NULL),(2455,'35Bqzs','Seth Geraldini',1,'1993-09-19','sgeraldinic6@mlb.com','7957253525',NULL,NULL,1,1,'sgeraldinic6',NULL,NULL),(2456,'Nk5Rj4X96nN','Guendolen Rudledge',0,'2004-12-28','grudledgec7@tripadvisor.com','5524999235',NULL,NULL,1,1,'grudledgec7',NULL,NULL),(2457,'3SsORM','Belva Philpott',1,'1991-07-22','bphilpottc8@princeton.edu','8256577318',NULL,NULL,1,1,'bphilpottc8',NULL,NULL),(2458,'hz2XkL4Ku1uw','Roderigo Blowing',0,'2002-03-16','rblowingc9@posterous.com','1994834289',NULL,NULL,1,1,'rblowingc9',NULL,NULL),(2459,'suO4mE','Neilla Janas',1,'2002-02-15','njanasca@alexa.com','7301914540',NULL,NULL,1,1,'njanasca',NULL,NULL),(2460,'iltil5pd','Lon Fairpo',1,'1992-10-06','lfairpocb@google.nl',NULL,NULL,NULL,1,0,'lfairpocb',NULL,NULL),(2461,'emYc4A','Wrennie Lautie',1,'1990-06-17','wlautiecc@illinois.edu','3826758290',NULL,NULL,1,0,'wlautiecc',NULL,NULL),(2462,'agIHyT2oCAhy','Beaufort Lowy',0,'1998-02-28','blowycd@prnewswire.com','2232638472',NULL,NULL,1,0,'blowycd',NULL,NULL),(2463,'M500jt3zj6c','John Coupman',1,'1991-12-10','jcoupmance@fda.gov',NULL,NULL,NULL,1,1,'jcoupmance',NULL,NULL),(2464,'Kpbq2ncLn','Auguste Mandal',1,'1993-06-20','amandalcf@nationalgeographic.com','7803622797',NULL,NULL,1,1,'amandalcf',NULL,NULL),(2465,'IsdKNu','Tommy Pyott',0,'2000-07-10','tpyottcg@cnet.com',NULL,NULL,NULL,1,0,'tpyottcg',NULL,NULL),(2466,'EVRGGNS','Deanna Pegg',0,'2003-08-09','dpeggch@nba.com',NULL,NULL,NULL,1,1,'dpeggch',NULL,NULL),(2467,'2svTsijQ3IL7','Orsola Brient',0,'1995-08-23','obrientci@xrea.com','6998869706',NULL,NULL,1,1,'obrientci',NULL,NULL),(2468,'YuXjzQvIE','Leann Shropshire',1,'1995-08-06','lshropshirecj@google.com',NULL,NULL,NULL,1,0,'lshropshirecj',NULL,NULL),(2469,'JtP3Lfd5OD','Leonardo Pearl',1,'2003-10-10','lpearlck@prweb.com',NULL,NULL,NULL,1,1,'lpearlck',NULL,NULL),(2470,'2APVVXFU','Joleen Bramble',0,'1997-09-13','jbramblecl@addthis.com','1847120055',NULL,NULL,1,1,'jbramblecl',NULL,NULL),(2471,'GtjSlneP0','Winona Fulford',0,'1992-04-25','wfulfordcm@bigcartel.com',NULL,NULL,NULL,1,0,'wfulfordcm',NULL,NULL),(2472,'DqefgtI13j','Claiborne Bierman',0,'1997-08-24','cbiermancn@accuweather.com',NULL,NULL,NULL,1,0,'cbiermancn',NULL,NULL),(2473,'Gs1myr','Tiff Cribbin',0,'1998-07-21','tcribbinco@furl.net',NULL,NULL,NULL,1,0,'tcribbinco',NULL,NULL),(2474,'Z1X3eE','Fitz Trevaskis',1,'1992-05-15','ftrevaskiscp@github.com',NULL,NULL,NULL,1,1,'ftrevaskiscp',NULL,NULL),(2475,'VtYavalh4XwU','Bayard Penswick',1,'1999-01-03','bpenswickcq@weebly.com',NULL,NULL,NULL,1,1,'bpenswickcq',NULL,NULL),(2476,'ugP6ljT53f','Boyce Olcot',1,'1998-06-12','bolcotcr@shutterfly.com','6122556040',NULL,NULL,1,1,'bolcotcr',NULL,NULL),(2477,'vldMMNH97aT','Monique O\'Hagerty',0,'1993-08-24','mohagertycs@fc2.com','3509957570',NULL,NULL,1,0,'mohagertycs',NULL,NULL),(2478,'1iYIAny7','Phillida Duffyn',0,'1992-08-27','pduffynct@infoseek.co.jp',NULL,NULL,NULL,1,1,'pduffynct',NULL,NULL),(2479,'8ux39HnQ8','Sonya McCathay',1,'1990-11-18','smccathaycu@washingtonpost.com','3504138272',NULL,NULL,1,1,'smccathaycu',NULL,NULL),(2480,'MqbLhe','Jewelle Padginton',1,'2002-08-16','jpadgintoncv@imgur.com','8071570766',NULL,NULL,1,0,'jpadgintoncv',NULL,NULL),(2481,'B4aCgpFp3TD','Forest Murr',0,'1995-08-28','fmurrcw@accuweather.com',NULL,NULL,NULL,1,1,'fmurrcw',NULL,NULL),(2482,'LefJgX4HTtk5','Catha Ansett',0,'2003-09-07','cansettcx@cbsnews.com',NULL,NULL,NULL,1,1,'cansettcx',NULL,NULL),(2483,'PmgraIaW5','Petronella Gutch',0,'2004-08-19','pgutchcy@storify.com','9373993191',NULL,NULL,1,1,'pgutchcy',NULL,NULL),(2484,'2TcnqJQ','Lennard Swanger',1,'2000-03-17','lswangercz@marriott.com','9979338302',NULL,NULL,1,0,'lswangercz',NULL,NULL),(2485,'zZ0N7BMeK','Colene Wyche',1,'1993-06-19','cwyched0@engadget.com','5438768521',NULL,NULL,1,0,'cwyched0',NULL,NULL),(2486,'FMdhBJopkf','Harcourt Zoanetti',1,'1995-11-27','hzoanettid1@netscape.com',NULL,NULL,NULL,1,1,'hzoanettid1',NULL,NULL),(2487,'FqCkJVCB','Hugo Shepley',1,'2004-07-31','hshepleyd2@theglobeandmail.com',NULL,NULL,NULL,1,1,'hshepleyd2',NULL,NULL),(2488,'1GLbCZDL7JSi','Kelly Lochet',0,'2004-09-03','klochetd3@webeden.co.uk','7048033001',NULL,NULL,1,1,'klochetd3',NULL,NULL),(2489,'wkyCZu9ise6','Bonnie Fortun',1,'2003-01-28','bfortund4@canalblog.com','5029347157',NULL,NULL,1,1,'bfortund4',NULL,NULL),(2490,'l6otkVPM','Doy Teacy',1,'1997-06-17','dteacyd5@loc.gov','3484668123',NULL,NULL,1,1,'dteacyd5',NULL,NULL),(2491,'9IqjapPZ6oH','Dagny McKie',1,'2000-01-29','dmckied6@theguardian.com','5966031196',NULL,NULL,1,1,'dmckied6',NULL,NULL),(2492,'79up6fgM9','Edvard Tasker',0,'2000-01-19','etaskerd7@moonfruit.com','6402586809',NULL,NULL,1,0,'etaskerd7',NULL,NULL),(2493,'4RnsBkDU','Caritta Betz',1,'1992-07-04','cbetzd8@bbb.org','9832930871',NULL,NULL,1,0,'cbetzd8',NULL,NULL),(2494,'0iRI8I0x0AHb','Alexa De Ambrosis',1,'2001-12-19','aded9@google.com','8434913057',NULL,NULL,1,0,'aded9',NULL,NULL),(2495,'4oh9JyGNzR2','Wallace Pieche',1,'1994-02-24','wpiecheda@sphinn.com',NULL,NULL,NULL,1,0,'wpiecheda',NULL,NULL),(2496,'HVC3Wl9wwth','Filide Swaddle',0,'1994-10-31','fswaddledb@indiatimes.com','9905668152',NULL,NULL,1,0,'fswaddledb',NULL,NULL),(2497,'vakHHE8B0','Berna Rourke',1,'1992-08-26','brourkedc@unblog.fr',NULL,NULL,NULL,1,0,'brourkedc',NULL,NULL),(2498,'ULHldOxy99t','Joseph Mancell',0,'2004-09-24','jmancelldd@tripod.com',NULL,NULL,NULL,1,0,'jmancelldd',NULL,NULL),(2499,'a7b0xmVG','Liesa Millgate',0,'2000-10-04','lmillgatede@geocities.jp','4292506152',NULL,NULL,1,1,'lmillgatede',NULL,NULL),(2500,'QjmBVO','Hobard Marshallsay',1,'1993-12-19','hmarshallsaydf@nature.com',NULL,NULL,NULL,1,0,'hmarshallsaydf',NULL,NULL),(2501,'R1XN1yaJE','Belita Fontin',0,'2005-10-16','bfontindg@nyu.edu','8751131756',NULL,NULL,1,1,'bfontindg',NULL,NULL),(2502,'qAejcpf','Paulette Varne',0,'1997-04-23','pvarnedh@arstechnica.com',NULL,NULL,NULL,1,1,'pvarnedh',NULL,NULL),(2503,'VIHw6MPl87U','Pru Brownell',1,'2000-06-04','pbrownelldi@ted.com','1881293199',NULL,NULL,1,1,'pbrownelldi',NULL,NULL),(2504,'Ed22hvSZKb4G','Teirtza Whiff',0,'1994-07-15','twhiffdj@amazon.com','5007367485',NULL,NULL,1,0,'twhiffdj',NULL,NULL),(2505,'Lk2DyEB','Adriana Songer',0,'1999-10-09','asongerdk@about.com',NULL,NULL,NULL,1,1,'asongerdk',NULL,NULL),(2506,'n7Z72LE','Giffy Ollerhead',0,'1998-03-08','gollerheaddl@cyberchimps.com','3936409671',NULL,NULL,1,0,'gollerheaddl',NULL,NULL),(2507,'cInpRAR','Wit Verryan',1,'1999-10-09','wverryandm@smugmug.com',NULL,NULL,NULL,1,0,'wverryandm',NULL,NULL),(2508,'s6xLIo7B0iPC','Gabby McDermott',1,'2006-09-23','gmcdermottdn@hao123.com',NULL,NULL,NULL,1,1,'gmcdermottdn',NULL,NULL),(2509,'zMh4wY0','Trista Krystek',0,'1994-09-14','tkrystekdo@ucsd.edu',NULL,NULL,NULL,1,0,'tkrystekdo',NULL,NULL),(2510,'RtMSe1TTxp4','Vance Tejero',0,'1990-06-05','vtejerodp@webmd.com',NULL,NULL,NULL,1,1,'vtejerodp',NULL,NULL),(2511,'pvVCKGD','Nickie Screas',0,'1994-08-23','nscreasdq@addthis.com',NULL,NULL,NULL,1,1,'nscreasdq',NULL,NULL),(2512,'CukM2aHjpfq','Angeli Parradine',1,'2002-02-14','aparradinedr@slideshare.net',NULL,NULL,NULL,1,1,'aparradinedr',NULL,NULL),(2513,'VcQ38DyqEm','Saunderson Drayson',1,'1990-01-26','sdraysonds@ebay.com','2915266189',NULL,NULL,1,1,'sdraysonds',NULL,NULL),(2514,'787j7VJ','Terry Selly',0,'1993-05-01','tsellydt@sphinn.com',NULL,NULL,NULL,1,0,'tsellydt',NULL,NULL),(2515,'OobKIfoC','Brewer Sarchwell',1,'1993-03-10','bsarchwelldu@ucoz.ru','4711919273',NULL,NULL,1,0,'bsarchwelldu',NULL,NULL),(2516,'RVMHatkdFVw0','Stevie McQuillin',0,'2001-08-24','smcquillindv@mapquest.com','5063957142',NULL,NULL,1,1,'smcquillindv',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'swp391-project'
--

--
-- Dumping routines for database 'swp391-project'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-15 21:26:19
