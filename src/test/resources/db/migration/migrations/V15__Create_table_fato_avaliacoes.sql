DROP TABLE IF EXISTS `fato_avaliacoes`;
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
);
