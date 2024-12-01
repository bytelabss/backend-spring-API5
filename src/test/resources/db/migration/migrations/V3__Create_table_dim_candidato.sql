DROP TABLE IF EXISTS `dim_candidato`;
CREATE TABLE `dim_candidato` (
  `id_candidato` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_candidato`)
);
