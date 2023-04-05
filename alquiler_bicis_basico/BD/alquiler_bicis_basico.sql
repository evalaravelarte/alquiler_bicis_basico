-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema alquiler_bicis_basico
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema alquiler_bicis_basico
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `alquiler_bicis_basico` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci ;
USE `alquiler_bicis_basico` ;

-- -----------------------------------------------------
-- Table `alquiler_bicis_basico`.`bici`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alquiler_bicis_basico`.`bici` (
  `cod_bici` INT NOT NULL,
  `libre` TINYINT NOT NULL DEFAULT '0',
  PRIMARY KEY (`cod_bici`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


-- -----------------------------------------------------
-- Table `alquiler_bicis_basico`.`usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `alquiler_bicis_basico`.`usuario` (
  `cod_usuario` INT NOT NULL,
  `nombre` VARCHAR(45) NOT NULL,
  `cod_bici` INT NULL DEFAULT NULL,
  PRIMARY KEY (`cod_usuario`),
  INDEX `fk_bici` (`cod_bici` ASC) VISIBLE,
  CONSTRAINT `fk_bici`
    FOREIGN KEY (`cod_bici`)
    REFERENCES `alquiler_bicis_basico`.`bici` (`cod_bici`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_unicode_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `alquiler_bicis_basico`.`bici`
-- -----------------------------------------------------
START TRANSACTION;
USE `alquiler_bicis_basico`;
INSERT INTO `alquiler_bicis_basico`.`bici` (`cod_bici`, `libre`) VALUES (1, 0);
INSERT INTO `alquiler_bicis_basico`.`bici` (`cod_bici`, `libre`) VALUES (2, DEFAULT);

COMMIT;


-- -----------------------------------------------------
-- Data for table `alquiler_bicis_basico`.`usuario`
-- -----------------------------------------------------
START TRANSACTION;
USE `alquiler_bicis_basico`;
INSERT INTO `alquiler_bicis_basico`.`usuario` (`cod_usuario`, `nombre`, `cod_bici`) VALUES (1, 'Eva', NULL);
INSERT INTO `alquiler_bicis_basico`.`usuario` (`cod_usuario`, `nombre`, `cod_bici`) VALUES (2, 'Juan', NULL);

COMMIT;

