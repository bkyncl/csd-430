/* shop_populate.sql
 * Module 6 Assignment
 * Name: Brittany Kyncl
 * Date: 6.19.23
 * Course: CSD430
 * shop database categories and books table population
 * code exampled from source: "Beginning Jarkarta EE Web Development: 3rd Edition" by Manelli, Zambon
 */

INSERT INTO categories (category_id, category_name) VALUES
    (1, 'Web Development'),
    (2, 'Science Fiction'),
    (3, 'Historical Mysteries');

INSERT INTO books (book_id, title, author, price, category_id) VALUES
    (1, 'MYSQL 8 Query Performance Tuning', 'Jesper Wisborg Krogh', 34.31, 1),
    (2, 'JavaScript Next', 'Raju Ghandi', 36.70, 1),
    (3, 'The Complete Robot', 'Isaac Asimov', 12.13, 2),
    (4, 'Foundation and Earth', 'Isaac Asimov', 11.07, 2),
    (5, 'The Da Vinci Code', 'Dan Brown', 7.99, 3),
    (6, 'A Column of Fire', 'Ken Follett', 6.99, 3);