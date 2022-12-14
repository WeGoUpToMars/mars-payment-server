create user IF NOT EXISTS 'test'@'%' identified by 'test';
CREATE DATABASE IF NOT EXISTS mars;
grant all privileges on mars.* to 'test'@'%';
flush privileges;


-- MySQL Script generated by MySQL Workbench
-- Thu Nov 17 10:44:48 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mars
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `mars` ;

-- -----------------------------------------------------
-- Schema mars
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mars` DEFAULT CHARACTER SET utf8 ;
SHOW WARNINGS;
USE `mars` ;

-- -----------------------------------------------------
-- Table `mars`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mars`.`user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mars`.`user` (
                                             `id` BIGINT(20) NOT NULL,
                                             `name` VARCHAR(225) NULL,
                                             `email` VARCHAR(225) NULL,
                                             `profile` VARCHAR(225) NULL,
                                             PRIMARY KEY (`id`))
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mars`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mars`.`product` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mars`.`product` (
                                                `id` BIGINT(20) NOT NULL,
                                                `name` VARCHAR(225) NULL,
                                                `price` BIGINT(20) NULL,
                                                `category` VARCHAR(225) NULL,
                                                PRIMARY KEY (`id`))
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mars`.`order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mars`.`order` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mars`.`order` (
                                              `id` BIGINT(20) NOT NULL,
                                              `order_uuid` VARCHAR(225) NULL,
                                              `amount` DOUBLE NULL,
                                              `status` VARCHAR(225) NULL,
                                              `user_id` BIGINT(20) NULL,
                                              PRIMARY KEY (`id`),
                                              CONSTRAINT `fk_order_user_id`
                                                  FOREIGN KEY (user_id)
                                                      REFERENCES `mars`.`user` (id)
                                                      ON DELETE NO ACTION
                                                      ON UPDATE NO ACTION)
    ENGINE = InnoDB;

SHOW WARNINGS;



-- -----------------------------------------------------
-- Table `mars`.`order_product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mars`.`order_product` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mars`.`order_product` (
                                                      `id` BIGINT(20) NOT NULL,
                                                      `order_id` BIGINT(20) NULL,
                                                      `product_id` BIGINT(20) NULL,
                                                      PRIMARY KEY (`id`),
                                                      CONSTRAINT `fk_op_order_id`
                                                          FOREIGN KEY (order_id)
                                                              REFERENCES `mars`.`order` (id)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION,
                                                      CONSTRAINT `fk_op_product_id`
                                                          FOREIGN KEY (product_id)
                                                              REFERENCES `mars`.`product` (id)
                                                              ON DELETE NO ACTION
                                                              ON UPDATE NO ACTION)
    ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `mars`.`payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mars`.`payment` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `mars`.`payment` (
                                                `id` BIGINT(20) NOT NULL,
                                                `order_id` BIGINT(20) NULL,
                                                `payment_gateway` VARCHAR(225) NULL,
                                                `status` VARCHAR(225) NULL,
                                                `pay_type` VARCHAR(225) NULL,
                                                PRIMARY KEY (`id`),
                                                CONSTRAINT `fk_payment_order_id`
                                                    FOREIGN KEY (order_id)
                                                        REFERENCES `mars`.`order` (id)
                                                        ON DELETE NO ACTION
                                                        ON UPDATE NO ACTION)
    ENGINE = InnoDB;

SHOW WARNINGS;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
