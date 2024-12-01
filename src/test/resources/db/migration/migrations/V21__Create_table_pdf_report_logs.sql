DROP TABLE IF EXISTS `pdf_report_logs`;
CREATE TABLE `pdf_report_logs` (
  `id` int NOT NULL AUTO_INCREMENT,
  `report_date` date NOT NULL,
  `generated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);
