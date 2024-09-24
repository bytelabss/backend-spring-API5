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
-- Table structure for table `fato_contratacoes`
--

DROP TABLE IF EXISTS `fato_contratacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fato_contratacoes` (
  `quantidade` int DEFAULT NULL,
  `processo_seletivo` bigint NOT NULL,
  `tempo` bigint NOT NULL,
  `vaga` bigint NOT NULL,
  `participante_rh` bigint NOT NULL,
  `tempo_medio` bigint DEFAULT NULL,
  PRIMARY KEY (`processo_seletivo`,`tempo`,`vaga`,`participante_rh`),
  KEY `vaga` (`vaga`),
  KEY `participante_rh` (`participante_rh`),
  KEY `tempo` (`tempo`),
  CONSTRAINT `fato_contratacoes_ibfk_1` FOREIGN KEY (`vaga`) REFERENCES `dim_vaga` (`id_vaga`),
  CONSTRAINT `fato_contratacoes_ibfk_2` FOREIGN KEY (`participante_rh`) REFERENCES `dim_participante_rh` (`id_participante_rh`),
  CONSTRAINT `fato_contratacoes_ibfk_3` FOREIGN KEY (`processo_seletivo`) REFERENCES `dim_processo_seletivo` (`id_processo_seletivo`),
  CONSTRAINT `fato_contratacoes_ibfk_4` FOREIGN KEY (`tempo`) REFERENCES `dim_tempo` (`id_tempo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fato_contratacoes`
--

LOCK TABLES `fato_contratacoes` WRITE;
/*!40000 ALTER TABLE `fato_contratacoes` DISABLE KEYS */;
INSERT INTO `fato_contratacoes` VALUES (7,1,4768,1,1,43),(5,1,4768,2,1,49),(9,1,4768,2,2,56),(22,2,4768,3,2,18),(10,2,4768,4,3,1),(16,2,4768,5,3,4),(4,3,4837,6,3,35),(6,3,4841,6,3,64),(12,3,4847,6,3,95);
/*!40000 ALTER TABLE `fato_contratacoes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-23 20:14:00
