CREATE TABLE `magicpark_orders` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `number` TEXT NULL,
  `total_amount` INT NULL,
  `payment_method` INT NULL,
  `payed_at` DATE NULL,
  `created_at` DATE NULL,
  `user_id` INT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
);
CREATE TABLE `magicpark_shop` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(250) NULL,
  `description` VARCHAR(250) NULL,
  `price` FLOAT NULL,
  `quantity` INT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
);
CREATE TABLE `magicpark_shop_category` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(250) NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
);
CREATE TABLE `magicpark_users` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `fullname` VARCHAR(250) NULL,
  `mail` VARCHAR(250) NULL,
  `token` TEXT NULL,
  `ip` TEXT NULL,
  `verified_at` DATE NULL,
  `created_at` DATE NULL,
  `deleted_at` DATE NULL,
  `country` VARCHAR(250) NULL,
  `rank` INT NULL,
  `phone_number` VARCHAR(250) NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
);
CREATE TABLE `magicpark_voucher` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `voucher_code` VARCHAR(250) NULL,
  `expired_at` DATE NULL,
  `shop_item` INT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
);
CREATE TABLE `magicpark_wallets` (
  `id` INT AUTO_INCREMENT NOT NULL,
  `name` VARCHAR(250) NULL,
  `image_url` TEXT NULL,
  `background_color` VARCHAR(250) NULL,
  `shop_item` INT NULL,
  `created_at` DATE NULL,
  `expired_at` DATE NULL,
  `user_id` INT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`id`)
);
