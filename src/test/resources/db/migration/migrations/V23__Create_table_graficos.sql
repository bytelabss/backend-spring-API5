CREATE TABLE `graficos` (
  `id` bigint NOT NULL,
  `ignore_until` datetime DEFAULT NULL,
  `max_limit` float DEFAULT NULL,
  `min_limit` float DEFAULT NULL,
  `has_alarm` bit(1) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
);