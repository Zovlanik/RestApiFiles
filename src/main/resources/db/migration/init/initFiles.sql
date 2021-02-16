CREATE TABLE IF NOT EXISTS `restapi`.`files` (
                                   `id` INT NOT NULL AUTO_INCREMENT,
                                   `filename` VARCHAR(250) NULL,
                                   `creationDate` TIMESTAMP NULL,
                                   `deletionDate` TIMESTAMP NULL,
                                   `status` VARCHAR(45) NULL DEFAULT 'ACTIVE',
                                   PRIMARY KEY (`id`));
