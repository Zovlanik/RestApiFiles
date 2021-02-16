CREATE TABLE IF NOT EXISTS `restapi`.`events` (
                                    `id` INT NOT NULL AUTO_INCREMENT,
                                    `user_id` INT,
                                    `date` TIMESTAMP NULL,
                                    `result` VARCHAR(250) NULL,
                                    PRIMARY KEY (`id`));
