-- Insert sample books
INSERT INTO books (title, subtitle, isbn13, isbn10, publisher_id, publication_date, edition, page_count, language, description, cover_image_url, price)
VALUES 
('Spring Boot in Action', 'A comprehensive guide to Spring Boot', '9781617292545', '1617292540', 1, '2016-01-30', '1st Edition', 264, 'English', 'A practical guide to Spring Boot', 'https://example.com/spring-boot.jpg', 49.99),
('Java Persistence with Hibernate', 'Second Edition of a bestselling book', '9781617290459', '1617290459', 1, '2015-12-31', '2nd Edition', 608, 'English', 'The definitive guide to Hibernate and JPA', 'https://example.com/hibernate.jpg', 59.99),
('Clean Code', 'A Handbook of Agile Software Craftsmanship', '9780132350884', '0132350882', 2, '2008-08-11', '1st Edition', 464, 'English', 'A book on writing clean, maintainable code', 'https://example.com/clean-code.jpg', 44.99),
('Effective Java', 'Best practices for the Java platform', '9780134685991', '0134685997', 2, '2018-01-06', '3rd Edition', 416, 'English', 'The definitive guide to Java platform best practices', 'https://example.com/effective-java.jpg', 54.99),
('Head First Design Patterns', 'A Brain-Friendly Guide', '9780596007126', '0596007124', 3, '2004-10-25', '1st Edition', 694, 'English', 'A guide to design patterns with a unique approach', 'https://example.com/head-first.jpg', 49.99),
('Domain-Driven Design', 'Tackling Complexity in the Heart of Software', '9780321125217', '0321125215', 3, '2003-08-30', '1st Edition', 560, 'English', 'A book on domain-driven design methodology', 'https://example.com/ddd.jpg', 64.99),
('Refactoring', 'Improving the Design of Existing Code', '9780134757599', '0134757599', 2, '2018-11-30', '2nd Edition', 448, 'English', 'A guide to refactoring code for better design', 'https://example.com/refactoring.jpg', 49.99),
('The Pragmatic Programmer', 'Your Journey to Mastery', '9780135957059', '0135957052', 4, '2019-09-13', '20th Anniversary Edition', 352, 'English', 'A guide to becoming a better programmer', 'https://example.com/pragmatic.jpg', 44.99),
('Test Driven Development', 'By Example', '9780321146533', '0321146530', 2, '2002-11-18', '1st Edition', 240, 'English', 'A guide to test-driven development', 'https://example.com/tdd.jpg', 39.99),
('Working Effectively with Legacy Code', 'Making existing code easier to change', '9780131177055', '0131177052', 2, '2004-09-22', '1st Edition', 464, 'English', 'Techniques for dealing with legacy code', 'https://example.com/legacy-code.jpg', 54.99);
