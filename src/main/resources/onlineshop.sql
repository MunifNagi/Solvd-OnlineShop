-- MySQL Script generated by MySQL Workbench
-- Mon Sep 19 18:57:51 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema OnlineShop
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `OnlineShop` ;

-- -----------------------------------------------------
-- Schema OnlineShop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `OnlineShop` DEFAULT CHARACTER SET utf8 ;
USE `OnlineShop` ;

-- -----------------------------------------------------
-- Table `OnlineShop`.`User`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`User` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`User` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `middle_name` VARCHAR(20) NULL,
  `phone` VARCHAR(20) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Category` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Category` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Discount`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Discount` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Discount` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `percentage` DECIMAL(2) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Manufacturer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Manufacturer` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Manufacturer` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Product` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `description` VARCHAR(90) NULL,
  `category_id` INT NULL,
  `weight` DECIMAL(5,2) NULL,
  `in_stock` INT ZEROFILL NOT NULL,
  `discount_id` INT NULL,
  `manufacturer_id` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Product_Category_idx` (`category_id` ASC) VISIBLE,
  INDEX `fk_Product_Discount1_idx` (`discount_id` ASC) VISIBLE,
  INDEX `fk_Product_Manufacturer1_idx` (`manufacturer_id` ASC) VISIBLE,
  CONSTRAINT `fk_Product_Category`
    FOREIGN KEY (`category_id`)
    REFERENCES `OnlineShop`.`Category` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Product_Discount1`
    FOREIGN KEY (`discount_id`)
    REFERENCES `OnlineShop`.`Discount` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Product_Manufacturer1`
    FOREIGN KEY (`manufacturer_id`)
    REFERENCES `OnlineShop`.`Manufacturer` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Address` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `country` VARCHAR(45) NOT NULL,
  `state` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  `zipcode` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Order_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Order_status` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Order_status` (
  `id` INT NOT NULL,
  `value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Payment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Payment` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `type` VARCHAR(45) NOT NULL,
  `amount` DECIMAL(20,2) NOT NULL,
  `date` DATETIME NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Payment_Users1_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_Payment_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `OnlineShop`.`User` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Shipper`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Shipper` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Shipper` (
  `id` INT NOT NULL,
  `company_name` VARCHAR(45) NOT NULL,
  `internationalShipping` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Shipment`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Shipment` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Shipment` (
  `id` INT NOT NULL,
  `tracking_number` VARCHAR(45) NOT NULL,
  `date` DATETIME NOT NULL,
  `shipper_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Shipment_Shipper1_idx` (`shipper_id` ASC) VISIBLE,
  CONSTRAINT `fk_Shipment_Shipper1`
    FOREIGN KEY (`shipper_id`)
    REFERENCES `OnlineShop`.`Shipper` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Order`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Order` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `total_price` DECIMAL(10,2) NOT NULL,
  `products_quantity` INT ZEROFILL NOT NULL,
  `date` DATETIME NOT NULL,
  `shipping_address_id` INT NOT NULL,
  `order_status_id` INT NOT NULL,
  `payment_id` INT NOT NULL,
  `shipment_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Order_Address1_idx` (`shipping_address_id` ASC) VISIBLE,
  INDEX `fk_Order_Order_status1_idx` (`order_status_id` ASC) VISIBLE,
  INDEX `fk_Order_Payment1_idx` (`payment_id` ASC) VISIBLE,
  INDEX `fk_Order_Shipment1_idx` (`shipment_id` ASC) VISIBLE,
  CONSTRAINT `fk_Order_Address1`
    FOREIGN KEY (`shipping_address_id`)
    REFERENCES `OnlineShop`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Order_status1`
    FOREIGN KEY (`order_status_id`)
    REFERENCES `OnlineShop`.`Order_status` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Payment1`
    FOREIGN KEY (`payment_id`)
    REFERENCES `OnlineShop`.`Payment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_Shipment1`
    FOREIGN KEY (`shipment_id`)
    REFERENCES `OnlineShop`.`Shipment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Report`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Report` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Report` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `order_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Report_Users1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Report_Product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_Report_Orders1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_Report_Users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `OnlineShop`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Report_Product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `OnlineShop`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Report_Orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `OnlineShop`.`Order` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Cart`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Cart` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Cart` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `product_id` INT NOT NULL,
  `quantity` INT ZEROFILL NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `total` DECIMAL(12,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Orders_has_Product_Product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_Orders_has_Product_Orders1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_Orders_has_Product_Orders1`
    FOREIGN KEY (`order_id`)
    REFERENCES `OnlineShop`.`Order` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Orders_has_Product_Product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `OnlineShop`.`Product` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Order_status`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Order_status` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Order_status` (
  `id` INT NOT NULL,
  `value` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`UserAddress`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`UserAddress` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`UserAddress` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `user_id` INT NOT NULL,
  `address_id` INT NOT NULL,
  INDEX `fk_Address_has_User_User1_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_Address_has_User_Address1_idx` (`address_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_Address_has_User_Address1`
    FOREIGN KEY (`address_id`)
    REFERENCES `OnlineShop`.`Address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Address_has_User_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `OnlineShop`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Return`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Return` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Return` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `reason` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_Return_Order1_idx` (`order_id` ASC) VISIBLE,
  CONSTRAINT `fk_Return_Order1`
    FOREIGN KEY (`order_id`)
    REFERENCES `OnlineShop`.`Order` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `OnlineShop`.`Rating`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `OnlineShop`.`Rating` ;

CREATE TABLE IF NOT EXISTS `OnlineShop`.`Rating` (
  `id` VARCHAR(45) NOT NULL,
  `product_id` INT NOT NULL,
  `user_id` INT NULL,
  `rating` INT NOT NULL,
  `review` VARCHAR(200) NULL,
  INDEX `fk_User_has_Product_Product1_idx` (`product_id` ASC) VISIBLE,
  INDEX `fk_User_has_Product_User1_idx` (`user_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_User_has_Product_User1`
    FOREIGN KEY (`user_id`)
    REFERENCES `OnlineShop`.`User` (`id`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Product_Product1`
    FOREIGN KEY (`product_id`)
    REFERENCES `OnlineShop`.`Product` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
