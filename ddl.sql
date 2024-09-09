-- Create user
CREATE USER IF NOT EXISTS 'userapp'@'localhost' IDENTIFIED BY 'admin123';

-- Crete schema
CREATE SCHEMA IF NOT EXISTS dataviz_bytelabss;

-- Grant permissions
GRANT SELECT, INSERT, CREATE, UPDATE, DELETE ON dataviz_bytelabss.* TO 'userapp'@'localhost';
