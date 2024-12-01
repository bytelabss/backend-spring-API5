DROP TABLE IF EXISTS `dim_tempo`;
CREATE TABLE `dim_tempo` (
  `id_tempo` bigint NOT NULL AUTO_INCREMENT,
  `mes` int DEFAULT NULL,
  `ano` int DEFAULT NULL,
  `semestre` int DEFAULT NULL,
  `trimestre` int DEFAULT NULL,
  PRIMARY KEY (`id_tempo`),
  UNIQUE KEY `UC_DIM_TEMPO` (`mes`,`ano`,`semestre`,`trimestre`)
);
