DROP TABLE IF EXISTS `dim_processo_seletivo`;
CREATE TABLE `dim_processo_seletivo` (
  `id_processo_seletivo` bigint NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `criado_por` varchar(255) DEFAULT NULL,
  `inicio_processo_seletivo` datetime DEFAULT NULL,
  `fim_processo_seletivo` datetime DEFAULT NULL,
  PRIMARY KEY (`id_processo_seletivo`)
);
