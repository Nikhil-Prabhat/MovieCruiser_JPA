CREATE SCHEMA IF NOT EXISTS `moviecrusier` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `moviecrusier` ;
CREATE TABLE IF NOT EXISTS `moviecrusier`.`user` (
  `us_id` INT NOT NULL AUTO_INCREMENT,
  `us_name` VARCHAR(60) NULL,
  PRIMARY KEY (`us_id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `moviecrusier`.`movie` (
  `movie_id` INT NOT NULL AUTO_INCREMENT,
  `movie_title` VARCHAR(100) NULL,
  `movie_boxOffice` VARCHAR(20) NULL,
  `movie_active` BOOLEAN NULL,
  `movie_date_of_launch` DATE NULL,
  `movie_genre` VARCHAR(45) NULL,
  `movie_has_teaser` BOOLEAN NULL,
  PRIMARY KEY (`movie_id`))
ENGINE = InnoDB;
CREATE TABLE IF NOT EXISTS `moviecrusier`.`favourites` (
  `fav_id` INT NOT NULL AUTO_INCREMENT,
  `fav_us_id` INT NULL,
  `fav_movie_id` INT NULL,
  PRIMARY KEY (`fav_id`),
  INDEX `fav_us_fk_idx` (`fav_us_id` ASC),
  INDEX `fav_movie_fk_idx` (`fav_movie_id` ASC),
  CONSTRAINT `fav_us_fk`
    FOREIGN KEY (`fav_us_id`)
    REFERENCES `moviecrusier`.`user` (`us_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fav_movie_fk`
    FOREIGN KEY (`fav_movie_id`)
    REFERENCES `moviecrusier`.`movie` (`movie_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;