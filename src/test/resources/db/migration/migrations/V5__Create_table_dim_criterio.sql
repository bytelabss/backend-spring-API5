DROP TABLE IF EXISTS `dim_criterio`;
CREATE TABLE `dim_criterio` (
  `id_criterio` bigint NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_criterio`)
);
