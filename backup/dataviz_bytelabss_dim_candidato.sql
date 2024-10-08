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
-- Table structure for table `dim_candidato`
--

DROP TABLE IF EXISTS `dim_candidato`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dim_candidato` (
  `id_candidato` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_candidato`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dim_candidato`
--

LOCK TABLES `dim_candidato` WRITE;
/*!40000 ALTER TABLE `dim_candidato` DISABLE KEYS */;
INSERT INTO `dim_candidato` VALUES (1,'Ana'),(2,'Bruno'),(3,'Carla'),(4,'Daniel'),(5,'Eduardo'),(6,'Fernanda'),(7,'Gabriel'),(8,'Helena'),(9,'Igor'),(10,'Juliana'),(11,'Kevin'),(12,'Luana'),(13,'Marcelo'),(14,'Nat�lia'),(15,'Ot�vio'),(16,'Patr�cia'),(17,'Rafael'),(18,'Sofia'),(19,'Thiago'),(20,'Vanessa'),(21,'Andr�'),(22,'Beatriz'),(23,'Caio'),(24,'D�bora'),(25,'Elias'),(26,'Fl�via'),(27,'Gustavo'),(28,'Isabela'),(29,'Jorge'),(30,'K�tia'),(31,'Leandro'),(32,'Mariana'),(33,'Nestor'),(34,'Ol�via'),(35,'Paulo'),(36,'Qu�sia'),(37,'Ricardo'),(38,'Samara'),(39,'T�nia'),(40,'Uriel'),(41,'Vitor'),(42,'Wagner'),(43,'Xuxa'),(44,'Yasmin'),(45,'Z�'),(46,'Alice'),(47,'Bruno'),(48,'Cristiane'),(49,'Diego'),(50,'Emanuelle'),(51,'F�bio'),(52,'Geovana'),(53,'Hugo'),(54,'Ivana'),(55,'J�ssica'),(56,'Kleber'),(57,'L�cia'),(58,'M�rio'),(59,'N�dia'),(60,'Orlando'),(61,'Priscila'),(62,'Quirino'),(63,'Renata'),(64,'Sabrina'),(65,'Tom�s'),(66,'Ursula'),(67,'V�nia'),(68,'Wilma'),(69,'Xerxes'),(70,'Yara'),(71,'Zilda'),(72,'Adalberto'),(73,'Bruna'),(74,'C�ssio'),(75,'Denise'),(76,'Everton'),(77,'Fabiana'),(78,'Gilberto'),(79,'Helo�sa'),(80,'Ivo'),(81,'J�nior'),(82,'K�tia'),(83,'Leonardo'),(84,'Manuela'),(85,'Nicolas'),(86,'Olivia'),(87,'Paulo'),(88,'Quinto'),(89,'Raquel'),(90,'Sandro'),(91,'Teodoro');
/*!40000 ALTER TABLE `dim_candidato` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-10-08 19:13:34
