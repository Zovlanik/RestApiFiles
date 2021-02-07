CREATE TABLE `restapi`.`accounts` (
                                      `id` INT NOT NULL AUTO_INCREMENT,
                                      `name` VARCHAR(245) NULL,
                                      `account_status` VARCHAR(45) NULL DEFAULT 'ACTIVE',
                                      PRIMARY KEY (`id`));