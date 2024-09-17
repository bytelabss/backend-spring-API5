-- Create user
CREATE USER IF NOT EXISTS 'userapp'@'localhost' IDENTIFIED BY 'admin123';

-- Crete schema
CREATE SCHEMA IF NOT EXISTS dataviz_bytelabss;

-- Grant permissions
GRANT SELECT, INSERT, CREATE, UPDATE, DELETE ON dataviz_bytelabss.* TO 'userapp'@'localhost';

USE dataviz_bytelabss;

-- Create example table
CREATE TABLE bytelabss_employees (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    salary INT,
    department VARCHAR(255),
    hiring_date DATE,
    salary_with_bonus INT,
    department_upper_case VARCHAR(255),
    experience DOUBLE
);

-- Create user
CREATE USER IF NOT EXISTS 'userapp'@'localhost' IDENTIFIED BY 'admin123';

-- Crete schema
CREATE SCHEMA IF NOT EXISTS dataviz_bytelabss;

-- Grant permissions
GRANT SELECT, INSERT, CREATE, UPDATE, DELETE ON dataviz_bytelabss.* TO 'userapp'@'localhost';

USE dataviz_bytelabss;

CREATE TABLE bytelabss_employees (
    employee_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    salary INT,
    department VARCHAR(255),
    hiring_date DATE,
    salary_with_bonus INT,
    department_upper_case VARCHAR(255),
    experience DOUBLE
);

CREATE TABLE dim_vaga (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_vaga BIGINT,
    titulo_vaga VARCHAR(255),
    numero_posicoes INT,
    requisitos_vagas TEXT,
    estado VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE dim_participante_rh (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_participante_rh BIGINT,
    cargo VARCHAR(255),
    feedback_dados INT,
    PRIMARY KEY (id)
);
