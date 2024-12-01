DROP TABLE IF EXISTS `dim_participante_rh`;
CREATE TABLE `dim_participante_rh` (
  `id_participante_rh` bigint NOT NULL AUTO_INCREMENT,
  `cargo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_participante_rh`)
);
