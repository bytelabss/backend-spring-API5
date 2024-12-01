DROP TABLE IF EXISTS `custom_queries`;
CREATE TABLE `custom_queries` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `query` json NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  CONSTRAINT `custom_queries_chk_1` CHECK (json_valid(`query`))
);
