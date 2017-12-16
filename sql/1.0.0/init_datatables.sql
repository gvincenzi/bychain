-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema byochain
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema byochain
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `byochain` DEFAULT CHARACTER SET utf8 ;
USE `byochain` ;

-- -----------------------------------------------------
-- Table `byochain`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `byochain`.`user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(60) NOT NULL,
  `creation_date` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login` DATETIME NULL,
  `enabled` INT(1) NOT NULL DEFAULT '1',
  PRIMARY KEY (`user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `byochain`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `byochain`.`role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `byochain`.`user_roles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `byochain`.`user_roles` (
  `user_roles_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `role_id` INT NOT NULL,
  PRIMARY KEY (`user_roles_id`),
  INDEX `fk_username_idx` (`user_id` ASC),
  INDEX `fk_role_idx` (`role_id` ASC),
  CONSTRAINT `fk_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `byochain`.`user` (`user_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_role`
    FOREIGN KEY (`role_id`)
    REFERENCES `byochain`.`role` (`role_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
