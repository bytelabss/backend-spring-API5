DROP TABLE IF EXISTS `dim_vaga`;
CREATE TABLE `dim_vaga` (
  `id_vaga` bigint NOT NULL AUTO_INCREMENT,
  `titulo_vaga` varchar(255) DEFAULT NULL,
  `numero_posicoes` int DEFAULT NULL,
  `requisitos_vagas` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `data_criacao` datetime DEFAULT NULL,
  PRIMARY KEY (`id_vaga`)
);
