/* shop_create.sql
 * Module 6 Assignment
 * Name: Brittany Kyncl
 * Date: 6.19.23
 * Course: CSD430
 * shop database and table creation
 * code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
 */

DROP DATABASE IF EXISTS `shop`;
CREATE DATABASE `shop`;

CREATE TABLE IF NOT EXISTS shop.categories (
    category_id int NOT NULL AUTO_INCREMENT,
    category_name varchar(70) NOT NULL,
    PRIMARY KEY (category_id),
    KEY category_id_key (category_id)
);

CREATE TABLE IF NOT EXISTS shop.books (
    book_id int NOT NULL AUTO_INCREMENT,
    title varchar(70) NOT NULL,
    author varchar(70) DEFAULT NULL,
    price double NOT NULL,
    category_id int NOT NULL,
    PRIMARY KEY (book_id),
    KEY book_id_key (book_id),
    CONSTRAINT category_id FOREIGN KEY (category_id) REFERENCES categories (category_id)
);

CREATE TABLE IF NOT EXISTS shop.orders (
    order_id bigint NOT NULL AUTO_INCREMENT,
    delivery_name varchar(70) NOT NULL,
    delivery_address varchar(70) NOT NULL,
    cc_name varchar(70) NOT NULL,
    cc_number varchar(32) NOT NULL,
    cc_expiry varchar(20) NOT NULL,
    PRIMARY KEY (order_id),
    KEY order_id_key (order_id)
);

CREATE TABLE IF NOT EXISTS shop.order_details (
    id bigint NOT NULL AUTO_INCREMENT,
    book_id int NOT NULL,
    title varchar(70) NOT NULL,
    author varchar(70) DEFAULT NULL,
    quantity int NOT NULL,
    price double NOT NULL,
    order_id bigint NOT NULL,
    PRIMARY KEY (id),
    KEY order_details_id_key (id),
    CONSTRAINT book_id FOREIGN KEY (book_id) REFERENCES books (book_id),
    CONSTRAINT order_id FOREIGN KEY (order_id) REFERENCES orders (order_id)
);