DROP TABLE IF EXISTS `fato_contratacoes`;
CREATE TABLE `fato_contratacoes` (
  `quantidade` int DEFAULT NULL,
  `processo_seletivo` bigint NOT NULL,
  `tempo` bigint NOT NULL,
  `vaga` bigint NOT NULL,
  `participante_rh` bigint NOT NULL,
  `tempo_medio` bigint DEFAULT NULL,
  PRIMARY KEY (`processo_seletivo`,`tempo`,`vaga`,`participante_rh`),
  KEY `vaga` (`vaga`),
  KEY `participante_rh` (`participante_rh`),
  KEY `tempo` (`tempo`),
  CONSTRAINT `fato_contratacoes_ibfk_1` FOREIGN KEY (`vaga`) REFERENCES `dim_vaga` (`id_vaga`),
  CONSTRAINT `fato_contratacoes_ibfk_2` FOREIGN KEY (`participante_rh`) REFERENCES `dim_participante_rh` (`id_participante_rh`),
  CONSTRAINT `fato_contratacoes_ibfk_3` FOREIGN KEY (`processo_seletivo`) REFERENCES `dim_processo_seletivo` (`id_processo_seletivo`),
  CONSTRAINT `fato_contratacoes_ibfk_4` FOREIGN KEY (`tempo`) REFERENCES `dim_tempo` (`id_tempo`)
);
