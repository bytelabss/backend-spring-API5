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
-- Table structure for table `dim_processo_seletivo`
--

DROP TABLE IF EXISTS `dim_processo_seletivo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dim_processo_seletivo` (
  `id_processo_seletivo` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `criado_por` varchar(255) DEFAULT NULL,
  `inicio_processo_seletivo` datetime DEFAULT NULL,
  `fim_processo_seletivo` datetime DEFAULT NULL,
  PRIMARY KEY (`id_processo_seletivo`)
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_processo_seletivo`
--

LOCK TABLES `dim_processo_seletivo` WRITE;
/*!40000 ALTER TABLE `dim_processo_seletivo` DISABLE KEYS */;
INSERT INTO `dim_processo_seletivo` VALUES (1,'Processo seletivo 1','concluido','descri��o do processo seletivo 1','antonio','2023-12-01 00:00:00','2024-01-30 00:00:00'),(2,'Processo seletivo 2','Em andamento','descri��o do processo seletivo 2','Rodrigo','2024-01-01 00:00:00','2024-01-05 00:00:00'),(3,'Processo seletivo 3','Concluido','descri��o do processo seletivo 3','Amanda','2024-01-01 00:00:00','2024-04-05 00:00:00');
/*!40000 ALTER TABLE `dim_processo_seletivo` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-23 20:14:01
