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
-- Table structure for table `fato_avaliacoes`
--

DROP TABLE IF EXISTS `fato_avaliacoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fato_avaliacoes` (
  `pontuacao` bigint DEFAULT NULL,
  `tempo` bigint NOT NULL,
  `vaga` bigint NOT NULL,
  `criterio` bigint NOT NULL,
  `candidato` bigint NOT NULL,
  PRIMARY KEY (`criterio`,`tempo`,`vaga`,`candidato`),
  KEY `vaga` (`vaga`),
  KEY `tempo` (`tempo`),
  KEY `candidato` (`candidato`),
  CONSTRAINT `fato_avaliacoes_ibfk_1` FOREIGN KEY (`vaga`) REFERENCES `dim_vaga` (`id_vaga`),
  CONSTRAINT `fato_avaliacoes_ibfk_2` FOREIGN KEY (`tempo`) REFERENCES `dim_tempo` (`id_tempo`),
  CONSTRAINT `fato_avaliacoes_ibfk_3` FOREIGN KEY (`criterio`) REFERENCES `dim_criterio` (`id_criterio`),
  CONSTRAINT `fato_avaliacoes_ibfk_4` FOREIGN KEY (`candidato`) REFERENCES `dim_candidato` (`id_candidato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fato_avaliacoes`
--

LOCK TABLES `fato_avaliacoes` WRITE;
/*!40000 ALTER TABLE `fato_avaliacoes` DISABLE KEYS */;
INSERT INTO `fato_avaliacoes` VALUES (3,6315,1,1,1),(7,6315,1,1,2),(1,6315,1,1,3),(6,6315,1,1,4),(9,6315,1,1,5),(4,6315,1,1,6),(10,6315,1,1,7),(2,6315,2,1,8),(5,6315,2,1,9),(8,6315,2,1,10),(3,6315,2,1,11),(2,6315,2,1,12),(7,6315,2,1,13),(6,6315,2,1,14),(4,6315,2,1,15),(10,6315,2,1,16),(1,6315,2,1,17),(9,6315,2,1,18),(5,6315,2,1,19),(8,6315,2,1,20),(6,6315,2,1,21),(2,6315,3,1,22),(4,6315,3,1,23),(7,6315,3,1,24),(1,6315,3,1,25),(10,6315,3,1,26),(3,6315,3,1,27),(5,6315,3,1,28),(9,6315,3,1,29),(8,6315,3,1,30),(2,6315,3,1,31),(6,6315,3,1,32),(4,6315,3,1,33),(7,6315,3,1,34),(1,6315,3,1,35),(10,6315,3,1,36),(5,6315,3,1,37),(3,6315,3,1,38),(9,6315,3,1,39),(8,6315,3,1,40),(4,6315,3,1,41),(2,6315,3,1,42),(6,6315,3,1,43),(7,6315,4,1,44),(1,6315,4,1,45),(10,6315,4,1,46),(3,6315,4,1,47),(5,6315,4,1,48),(9,6315,4,1,49),(8,6315,4,1,50),(2,6315,4,1,51),(6,6315,4,1,52),(4,6315,4,1,53),(7,6315,5,1,54),(10,6315,5,1,55),(1,6315,5,1,56),(3,6315,5,1,57),(5,6315,5,1,58),(8,6315,5,1,59),(9,6315,5,1,60),(2,6315,5,1,61),(6,6315,5,1,62),(4,6315,5,1,63),(7,6315,5,1,64),(10,6315,5,1,65),(1,6315,5,1,66),(3,6315,5,1,67),(5,6315,5,1,68),(9,6315,5,1,69),(8,6453,6,1,70),(2,6453,6,1,71),(6,6453,6,1,72),(4,6453,6,1,73),(7,6457,6,1,74),(10,6457,6,1,75),(1,6457,6,1,76),(3,6457,6,1,77),(5,6457,6,1,78),(9,6457,6,1,79),(2,6463,6,1,80),(6,6463,6,1,81),(4,6463,6,1,82),(8,6463,6,1,83),(10,6463,6,1,84),(1,6463,6,1,85),(3,6463,6,1,86),(5,6463,6,1,87),(7,6463,6,1,88),(9,6463,6,1,89),(2,6463,6,1,90),(6,6463,6,1,91),(4,6315,1,2,1),(8,6315,1,2,2),(10,6315,1,2,3),(1,6315,1,2,4),(3,6315,1,2,5),(5,6315,1,2,6),(7,6315,1,2,7),(2,6315,2,2,8),(6,6315,2,2,9),(4,6315,2,2,10),(9,6315,2,2,11),(8,6315,2,2,12),(10,6315,2,2,13),(1,6315,2,2,14),(3,6315,2,2,15),(5,6315,2,2,16),(7,6315,2,2,17),(2,6315,2,2,18),(6,6315,2,2,19),(4,6315,2,2,20),(9,6315,2,2,21),(10,6315,3,2,22),(1,6315,3,2,23),(3,6315,3,2,24),(5,6315,3,2,25),(8,6315,3,2,26),(7,6315,3,2,27),(2,6315,3,2,28),(6,6315,3,2,29),(4,6315,3,2,30),(9,6315,3,2,31),(10,6315,3,2,32),(1,6315,3,2,33),(3,6315,3,2,34),(5,6315,3,2,35),(8,6315,3,2,36),(7,6315,3,2,37),(2,6315,3,2,38),(6,6315,3,2,39),(4,6315,3,2,40),(10,6315,3,2,41),(1,6315,3,2,42),(3,6315,3,2,43),(5,6315,4,2,44),(8,6315,4,2,45),(9,6315,4,2,46),(2,6315,4,2,47),(6,6315,4,2,48),(4,6315,4,2,49),(7,6315,4,2,50),(10,6315,4,2,51),(1,6315,4,2,52),(3,6315,4,2,53),(5,6315,5,2,54),(9,6315,5,2,55),(2,6315,5,2,56),(6,6315,5,2,57),(4,6315,5,2,58),(8,6315,5,2,59),(10,6315,5,2,60),(1,6315,5,2,61),(3,6315,5,2,62),(5,6315,5,2,63),(9,6315,5,2,64),(7,6315,5,2,65),(2,6315,5,2,66),(6,6315,5,2,67),(4,6315,5,2,68),(8,6315,5,2,69),(10,6453,6,2,70),(1,6453,6,2,71),(3,6453,6,2,72),(5,6453,6,2,73),(8,6457,6,2,74),(7,6457,6,2,75),(2,6457,6,2,76),(6,6457,6,2,77),(4,6457,6,2,78),(9,6457,6,2,79),(10,6463,6,2,80),(1,6463,6,2,81),(3,6463,6,2,82),(5,6463,6,2,83),(7,6463,6,2,84),(2,6463,6,2,85),(6,6463,6,2,86),(4,6463,6,2,87),(9,6463,6,2,88),(8,6463,6,2,89),(9,6463,6,2,90),(9,6463,6,2,91);
/*!40000 ALTER TABLE `fato_avaliacoes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-14 21:39:31
