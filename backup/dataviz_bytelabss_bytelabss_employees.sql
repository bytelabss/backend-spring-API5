-- MySQL dump 10.13  Distrib 8.0.38, for Win64 (x86_64)
--
-- Host: localhost    Database: dataviz_bytelabss
-- ------------------------------------------------------
-- Server version	8.0.39

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
-- Table structure for table `bytelabss_employees`
--

DROP TABLE IF EXISTS `bytelabss_employees`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bytelabss_employees` (
  `employee_id` bigint NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `salary` int DEFAULT NULL,
  `department` varchar(255) DEFAULT NULL,
  `hiring_date` date DEFAULT NULL,
  `salary_with_bonus` int DEFAULT NULL,
  `department_upper_case` varchar(255) DEFAULT NULL,
  `experience` double DEFAULT NULL,
  PRIMARY KEY (`employee_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bytelabss_employees`
--

LOCK TABLES `bytelabss_employees` WRITE;
/*!40000 ALTER TABLE `bytelabss_employees` DISABLE KEYS */;
INSERT INTO `bytelabss_employees` VALUES (6,'antonio','zago',1500,'backend','2024-08-07',1231,'BACKEND',21.1),(7,'alec','rondel',1500,'frontend','2024-08-07',1800,'FRONTEND',21.1),(8,'cauana','dias',1500,'backend','2024-08-07',1800,'BACKEND',21.1),(9,'jhonny','dutra',1900,'techlead','2024-08-07',2500,'TECHLEAD',21.1),(10,'larissa','reis',2100,'sm','2024-08-07',2700,'SM',3213.1),(11,'nicolas','cursino',1500,'backend','2024-08-07',1800,'BACKEND',21.1),(12,'willian','caboski',2100,'po','2024-08-07',2700,'PO',3213.1);
/*!40000 ALTER TABLE `bytelabss_employees` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-08 19:13:35
