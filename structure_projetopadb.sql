CREATE DATABASE  IF NOT EXISTS `projetopadb` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;
USE `projetopadb`;
-- MariaDB dump 10.17  Distrib 10.4.11-MariaDB, for Win64 (AMD64)
--
-- Host: 127.0.0.1    Database: projetopadb
-- ------------------------------------------------------
-- Server version	10.4.11-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `dono_carro`
--

DROP TABLE IF EXISTS `dono_carro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dono_carro` (
  `cpf` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `agente`
--

DROP TABLE IF EXISTS `agente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `agente` (
  `cpf` bigint(20) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `endereco` varchar(255) NOT NULL,
  PRIMARY KEY (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `carros`
--

DROP TABLE IF EXISTS `carros`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `carros` (
  `placa` varchar(7) NOT NULL,
  `cor` varchar(100) NOT NULL,
  `tipo` varchar(100) NOT NULL,
  `marca` varchar(100) NOT NULL,
  `cpf_dono` bigint(20) NOT NULL,
  PRIMARY KEY (`placa`),
  KEY `cpf_dono` (`cpf_dono`),
  CONSTRAINT `FK_dono_carroCarros` FOREIGN KEY (`cpf_dono`) REFERENCES `dono_carro` (`cpf`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `carros_ibfk_1` FOREIGN KEY (`cpf_dono`) REFERENCES `dono_carro` (`cpf`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `multa`
--

DROP TABLE IF EXISTS `multa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `multa` (
  `codigo` bigint(20) NOT NULL AUTO_INCREMENT,
  `valor` float NOT NULL,
  `placa_carro` varchar(7) NOT NULL,
  `cpf_agente` bigint(20) NOT NULL,
  `descricao` text NOT NULL,
  PRIMARY KEY (`codigo`),
  KEY `FK_CarrosMulta` (`placa_carro`),
  KEY `FK_AgenteMulta` (`cpf_agente`),
  CONSTRAINT `FK_AgenteMulta` FOREIGN KEY (`cpf_agente`) REFERENCES `agente` (`cpf`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `FK_CarrosMulta` FOREIGN KEY (`placa_carro`) REFERENCES `carros` (`placa`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-22  1:04:48
