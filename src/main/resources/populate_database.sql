-- Bookstore Database Population Script

-- Insert Countries
INSERT INTO Countries (name, code) VALUES 
('United States', 'USA'),
('United Kingdom', 'GBR'),
('Canada', 'CAN');

-- Insert States
INSERT INTO States (name, code, country_id) VALUES 
('New York', 'NY', 1),
('California', 'CA', 1),
('Texas', 'TX', 1),
('England', 'ENG', 2),
('Ontario', 'ON', 3);

-- Insert Cities
INSERT INTO Cities (name, state_id, postal_code) VALUES 
('New York City', 1, '10001'),
('Los Angeles', 2, '90001'),
('Austin', 3, '73301'),
('London', 4, 'SW1A 1AA'),
('Toronto', 5, 'M5V 2A8');

-- Insert Addresses
INSERT INTO Addresses (street_line1, street_line2, city_id) VALUES 
('123 Publishing Ave', 'Suite 101', 1),
('456 Book Street', NULL, 2),
('789 Author Lane', 'Floor 3', 3),
('10 Literary Road', NULL, 4),
('42 Reader Boulevard', 'Unit 5', 5);

-- Insert Publishers
INSERT INTO Publishers (name, address_id, website, email, phone) VALUES 
('Parody Press', 1, 'www.parodypress.com', 'info@parodypress.com', '555-123-4567'),
('Mockingbird Books', 2, 'www.mockingbirdbooks.com', 'contact@mockingbirdbooks.com', '555-234-5678'),
('Satire Publications', 3, 'www.satirepub.com', 'hello@satirepub.com', '555-345-6789'),
('Spoof House', 4, 'www.spoofhouse.co.uk', 'books@spoofhouse.co.uk', '555-456-7890'),
('Parody Pages', 5, 'www.parodypages.ca', 'info@parodypages.ca', '555-567-8901');

-- Insert Authors
INSERT INTO Authors (first_name, last_name, biography, birth_date) VALUES 
('Perry', 'Hotter', CAST('Known for magical parodies' AS CLOB), '1980-07-31'),
('Jane', 'Boston', CAST('Specializes in classic literature parodies' AS CLOB), '1975-12-16'),
('George R.R.', 'Fartin', CAST('Master of fantasy parodies' AS CLOB), '1968-09-20'),
('Stephenie', 'Smeyer', CAST('Queen of supernatural romance parodies' AS CLOB), '1973-12-24'),
('Dan', 'Frown', CAST('Expert in thriller parodies' AS CLOB), '1964-06-22'),
('Suzanne', 'Rollins', CAST('Dystopian parody specialist' AS CLOB), '1982-08-10'),
('J.R.R.', 'Folkien', CAST('Creator of Middle-Girth parodies' AS CLOB), '1970-01-03'),
('Agatha', 'Crispy', CAST('Mystery parody writer extraordinaire' AS CLOB), '1965-09-15'),
('Ernest', 'Hemingaway', CAST('Parodies with short sentences' AS CLOB), '1969-07-21'),
('Mark', 'Twang', CAST('River adventure parodies' AS CLOB), '1972-11-30');

-- Insert Categories
INSERT INTO Categories (name, description, parent_id) VALUES 
('Fiction', CAST('Fictional works' AS CLOB), NULL),
('Fantasy', CAST('Fantasy works' AS CLOB), 1),
('Science Fiction', CAST('Sci-fi works' AS CLOB), 1),
('Romance', CAST('Romantic works' AS CLOB), 1),
('Mystery', CAST('Mystery works' AS CLOB), 1),
('Thriller', CAST('Thriller works' AS CLOB), 1),
('Young Adult', CAST('YA works' AS CLOB), 1),
('Dystopian', CAST('Dystopian works' AS CLOB), 3),
('Paranormal', CAST('Paranormal works' AS CLOB), 2),
('Classic', CAST('Classic literature parodies' AS CLOB), 1);

-- Insert Tags
INSERT INTO Tags (name) VALUES 
('Funny'),
('Bestseller'),
('Award-winning'),
('Series'),
('Illustrated'),
('Magical'),
('Adventure'),
('Dragons'),
('Vampires'),
('Dystopia');

-- Insert Books (Parodies of popular books)
INSERT INTO Books (title, subtitle, isbn13, isbn10, publisher_id, publication_date, edition, page_count, language, description, cover_image_url, price) VALUES
('And Then There Were Nuns', NULL, '9788901234568', '8901234568', 4, '2006-10-24', '1st', 268, 'English', CAST('Ten sisters isolated on an island start disappearing' AS CLOB), 'https://example.com/covers/thentherewere.jpg', 12.99),
('Harry Plotter and the Chamber of Secretions', NULL, '9781234567892', '1234567892', 1, '2002-07-02', '1st', 328, 'English', CAST('Harry deals with magical plumbing issues' AS CLOB), 'https://example.com/covers/harryplotter2.jpg', 15.99),
('Harry Plotter and the Prisoner of Azkabanned', NULL, '9781234567893', '1234567893', 1, '2004-07-27', '1st', 342, 'English', CAST('Harry meets a gardener who was banned from Azkaban' AS CLOB), 'https://example.com/covers/harryplotter3.jpg', 16.99),
('The Hunger Lames', 'A Dystopian Parody', '9782345678901', '2345678901', 3, '2010-09-14', '1st', 378, 'English', CAST('Teens with leg injuries compete in a deadly game show' AS CLOB), 'https://example.com/covers/hungerlames.jpg', 13.99),
('Catching Flies', NULL, '9782345678902', '2345678902', 3, '2011-09-01', '1st', 391, 'English', CAST('The revolution continues with more insect collection' AS CLOB), 'https://example.com/covers/catchingflies.jpg', 14.99),
('Mockingjay-Z', NULL, '9782345678903', '2345678903', 3, '2012-08-24', '1st', 403, 'English', CAST('The revolution meets hip-hop' AS CLOB), 'https://example.com/covers/mockingjayZ.jpg', 15.99),
('Twilit', 'A Vampire Romance Parody', '9783456789012', '3456789012', 2, '2008-10-05', '1st', 498, 'English', CAST('Girl falls in love with a slightly illuminated vampire' AS CLOB), 'https://example.com/covers/twilit.jpg', 12.99),
('New Noon', NULL, '9783456789013', '3456789013', 2, '2009-09-22', '1st', 501, 'English', CAST('The vampire saga continues at lunchtime' AS CLOB), 'https://example.com/covers/newnoon.jpg', 13.99),
('Eclipsed', NULL, '9783456789014', '3456789014', 2, '2010-06-30', '1st', 511, 'English', CAST('Vampires during a solar phenomenon' AS CLOB), 'https://example.com/covers/eclipsed.jpg', 14.99),
('Breaking Down', NULL, '9783456789015', '3456789015', 2, '2011-08-02', '1st', 520, 'English', CAST('The vampire saga concludes with structural failures' AS CLOB), 'https://example.com/covers/breakingdown.jpg', 15.99),
('The Da Vinci Cod', 'A Fishy Mystery', '9784567890123', '4567890123', 4, '2004-04-07', '1st', 454, 'English', CAST('A symbologist discovers secrets hidden in fish' AS CLOB), 'https://example.com/covers/davincicod.jpg', 16.99),
('Angels & Daemons', 'A Hellish Parody', '9784567890124', '4567890124', 4, '2006-05-23', '1st', 441, 'English', CAST('Robert Langdon battles computer processes' AS CLOB), 'https://example.com/covers/angelsdaemons.jpg', 15.99),
('The Lost Cymbal', 'A Musical Mystery', '9784567890125', '4567890125', 4, '2009-09-15', '1st', 463, 'English', CAST('The search for a missing percussion instrument' AS CLOB), 'https://example.com/covers/lostcymbal.jpg', 14.99),
('A Game of Moans', 'A Song of Vice and Ire', '9785678901234', '5678901234', 1, '2011-07-12', '1st', 694, 'English', CAST('Political intrigue in a fantasy world full of complainers' AS CLOB), 'https://example.com/covers/gameofmoans.jpg', 19.99),
('A Clash of Swings', NULL, '9785678901235', '5678901235', 1, '2012-07-31', '1st', 712, 'English', CAST('The war of the playground equipment begins' AS CLOB), 'https://example.com/covers/clashofswings.jpg', 20.99),
('A Storm of Swords', 'But They''re Plastic', '9785678901236', '5678901236', 1, '2013-08-06', '1st', 751, 'English', CAST('The toy war escalates' AS CLOB), 'https://example.com/covers/stormofswords.jpg', 21.99),
('The Lord of the Onion Rings', 'One Ring to Season Them All', '9786789012345', '6789012345', 5, '2002-12-19', '1st', 531, 'English', CAST('A hobbit-like creature must destroy a powerful fast food item' AS CLOB), 'https://example.com/covers/lordonionrings.jpg', 18.99),
('The Two Flowers', NULL, '9786789012346', '6789012346', 5, '2003-12-18', '1st', 543, 'English', CAST('The fellowship is split as they search for rare plants' AS CLOB), 'https://example.com/covers/twoflowers.jpg', 19.99),
('The Return of the Bling', NULL, '9786789012347', '6789012347', 5, '2004-12-14', '1st', 567, 'English', CAST('The epic conclusion where jewelry is reclaimed' AS CLOB), 'https://example.com/covers/returnbling.jpg', 20.99),
('Pride and Prejudice and Zombies', 'The Classic Regency Romance with Undead', '9787890123456', '7890123456', 3, '2009-04-01', '1st', 317, 'English', CAST('Elizabeth Bennet battles the undead while finding love' AS CLOB), 'https://example.com/covers/prideprejudicezombies.jpg', 12.99),
('Sense and Sensibility and Sea Monsters', NULL, '9787890123457', '7890123457', 3, '2010-03-15', '1st', 325, 'English', CAST('The Dashwood sisters face aquatic creatures' AS CLOB), 'https://example.com/covers/senseseasmonsters.jpg', 13.99),
('Murder on the Oriental Excess', 'A Hercule Parrot Mystery', '9788901234567', '8901234567', 4, '2005-11-08', '1st', 274, 'English', CAST('Famous detective solves a murder on a very fancy train' AS CLOB), 'https://example.com/covers/orientalexcess.jpg', 11.99),
('The Old Man and the Sea Bass', 'A Fishing Tale', '9789012345678', '9012345678', 2, '2007-07-17', '1st', 127, 'English', CAST('An elderly fisherman struggles to catch a specific fish' AS CLOB), 'https://example.com/covers/oldmanseabass.jpg', 9.99),
('Moby-Richard', 'The Great White Whale', '9789012345679', '9012345679', 2, '2008-06-10', '1st', 583, 'English', CAST('Captain Ahab''s obsession with a formally-named whale' AS CLOB), 'https://example.com/covers/mobyrichard.jpg', 17.99),
('The Adventures of Huckleberry Fin', 'A Fish Out of Water', '9780123456789', '0123456789', 5, '2006-05-16', '1st', 318, 'English', CAST('A boy and a fish travel down the Mississippi' AS CLOB), 'https://example.com/covers/huckleberryfin.jpg', 13.99),
('The Great Gatsby Parrot', 'A Bird''s Eye View of the Roaring Twenties', '9780123456790', '0123456790', 5, '2007-04-10', '1st', 189, 'English', CAST('A talking parrot observes high society from his cage' AS CLOB), 'https://example.com/covers/gatsbyparrot.jpg', 14.99),
('To Kill a Mockingboard', 'A Tale of Circuit Boards', '9780123456791', '0123456791', 1, '2008-03-11', '1st', 324, 'English', CAST('A lawyer defends an electronic device accused of murder' AS CLOB), 'https://example.com/covers/mockingboard.jpg', 15.99),
('1984 BC', 'Big Brother in Ancient Times', '9780123456792', '0123456792', 3, '2009-02-12', '1st', 297, 'English', CAST('Dystopian society in prehistoric times' AS CLOB), 'https://example.com/covers/1984bc.jpg', 16.99),
('Brave New Word', 'A Linguistic Dystopia', '9780123456793', '0123456793', 3, '2010-01-19', '1st', 311, 'English', CAST('A society where vocabulary is strictly controlled' AS CLOB), 'https://example.com/covers/bravenewword.jpg', 17.99);

-- Insert Book_Author relationships
INSERT INTO Book_Author (book_id, author_id, author_order) VALUES 
(1, 1, 1), -- Harry Plotter books by Perry Hotter
(2, 1, 1),
(3, 1, 1),
(4, 6, 1), -- Hunger Lames books by Suzanne Rollins
(5, 6, 1),
(6, 6, 1),
(7, 4, 1), -- Twilit books by Stephenie Smeyer
(8, 4, 1),
(9, 4, 1),
(10, 4, 1),
(11, 5, 1), -- Da Vinci Cod books by Dan Frown
(12, 5, 1),
(13, 5, 1),
(14, 3, 1), -- Game of Moans books by George R.R. Fartin
(15, 3, 1),
(16, 3, 1),
(17, 7, 1), -- Lord of the Onion Rings books by J.R.R. Folkien
(18, 7, 1),
(19, 7, 1),
(20, 2, 1), -- Pride and Prejudice and Zombies by Jane Boston
(21, 2, 1),
(22, 8, 1), -- Murder on the Oriental Excess by Agatha Crispy
(23, 8, 1),
(24, 9, 1), -- The Old Man and the Sea Bass by Ernest Hemingaway
(25, 9, 1),
(26, 10, 1), -- Huckleberry Fin by Mark Twang
(27, 9, 1), -- Great Gatsby Parrot by Ernest Hemingaway
(28, 2, 1), -- To Kill a Mockingboard by Jane Boston
(29, 5, 1), -- 1984 BC by Dan Frown
(30, 6, 1); -- Brave New Word by Suzanne Rollins

-- Insert Book_Category relationships
INSERT INTO Book_Category (book_id, category_id) VALUES 
(1, 2), (1, 7), -- Harry Plotter: Fantasy, Young Adult
(2, 2), (2, 7),
(3, 2), (3, 7),
(4, 8), (4, 7), -- Hunger Lames: Dystopian, Young Adult
(5, 8), (5, 7),
(6, 8), (6, 7),
(7, 9), (7, 4), (7, 7), -- Twilit: Paranormal, Romance, Young Adult
(8, 9), (8, 4), (8, 7),
(9, 9), (9, 4), (9, 7),
(10, 9), (10, 4), (10, 7),
(11, 5), (11, 6), -- Da Vinci Cod: Mystery, Thriller
(12, 5), (12, 6),
(13, 5), (13, 6),
(14, 2), -- Game of Moans: Fantasy
(15, 2),
(16, 2),
(17, 2), -- Lord of the Onion Rings: Fantasy
(18, 2),
(19, 2),
(20, 10), (20, 9), -- Pride and Prejudice and Zombies: Classic, Paranormal
(21, 10), (21, 9),
(22, 5), -- Murder on the Oriental Excess: Mystery
(23, 5),
(24, 10), -- The Old Man and the Sea Bass: Classic
(25, 10),
(26, 10), -- Huckleberry Fin: Classic
(27, 10), -- Great Gatsby Parrot: Classic
(28, 10), -- To Kill a Mockingboard: Classic
(29, 8), -- 1984 BC: Dystopian
(30, 8); -- Brave New Word: Dystopian

-- Insert Book_Tag relationships
INSERT INTO Book_Tag (book_id, tag_id) VALUES 
(1, 1), (1, 2), (1, 4), (1, 6), (1, 7), -- Harry Plotter: Funny, Bestseller, Series, Magical, Adventure
(2, 1), (2, 2), (2, 4), (2, 6), (2, 7),
(3, 1), (3, 2), (3, 4), (3, 6), (3, 7),
(4, 1), (4, 2), (4, 4), (4, 7), (4, 10), -- Hunger Lames: Funny, Bestseller, Series, Adventure, Dystopia
(5, 1), (5, 2), (5, 4), (5, 7), (5, 10),
(6, 1), (6, 2), (6, 4), (6, 7), (6, 10),
(7, 1), (7, 2), (7, 4), (7, 9), -- Twilit: Funny, Bestseller, Series, Vampires
(8, 1), (8, 2), (8, 4), (8, 9),
(9, 1), (9, 2), (9, 4), (9, 9),
(10, 1), (10, 2), (10, 4), (10, 9),
(11, 1), (11, 2), (11, 3), -- Da Vinci Cod: Funny, Bestseller, Award-winning
(12, 1), (12, 2),
(13, 1), (13, 2),
(14, 1), (14, 2), (14, 4), (14, 8), -- Game of Moans: Funny, Bestseller, Series, Dragons
(15, 1), (15, 2), (15, 4), (15, 8),
(16, 1), (16, 2), (16, 4), (16, 8),
(17, 1), (17, 2), (17, 4), (17, 7), -- Lord of the Onion Rings: Funny, Bestseller, Series, Adventure
(18, 1), (18, 2), (18, 4), (18, 7),
(19, 1), (19, 2), (19, 4), (19, 7),
(20, 1), (20, 3), -- Pride and Prejudice and Zombies: Funny, Award-winning
(21, 1),
(22, 1), (22, 3), -- Murder on the Oriental Excess: Funny, Award-winning
(23, 1),
(24, 1), (24, 3), -- The Old Man and the Sea Bass: Funny, Award-winning
(25, 1),
(26, 1), (26, 5), -- Huckleberry Fin: Funny, Illustrated
(27, 1), -- Great Gatsby Parrot: Funny
(28, 1), (28, 3), -- To Kill a Mockingboard: Funny, Award-winning
(29, 1), (29, 10), -- 1984 BC: Funny, Dystopia
(30, 1), (30, 10); -- Brave New Word: Funny, Dystopia

-- Insert Inventory
INSERT INTO Inventory (book_id, quantity, location) VALUES 
(1, 50, 'Warehouse A'),
(2, 45, 'Warehouse A'),
(3, 40, 'Warehouse A'),
(4, 55, 'Warehouse B'),
(5, 50, 'Warehouse B'),
(6, 45, 'Warehouse B'),
(7, 60, 'Warehouse C'),
(8, 55, 'Warehouse C'),
(9, 50, 'Warehouse C'),
(10, 45, 'Warehouse C'),
(11, 40, 'Warehouse D'),
(12, 35, 'Warehouse D'),
(13, 30, 'Warehouse D'),
(14, 65, 'Warehouse A'),
(15, 60, 'Warehouse A'),
(16, 55, 'Warehouse A'),
(17, 50, 'Warehouse B'),
(18, 45, 'Warehouse B'),
(19, 40, 'Warehouse B'),
(20, 35, 'Warehouse C'),
(21, 30, 'Warehouse C'),
(22, 25, 'Warehouse D'),
(23, 20, 'Warehouse D'),
(24, 15, 'Warehouse A'),
(25, 10, 'Warehouse A'),
(26, 20, 'Warehouse B'),
(27, 25, 'Warehouse B'),
(28, 30, 'Warehouse C'),
(29, 35, 'Warehouse C'),
(30, 40, 'Warehouse D');

-- Insert a few customers for demonstration
INSERT INTO Customers (first_name, last_name, email, phone, address_id) VALUES 
('John', 'Doe', 'john.doe@example.com', '555-111-2222', 1),
('Jane', 'Smith', 'jane.smith@example.com', '555-333-4444', 2),
('Bob', 'Johnson', 'bob.johnson@example.com', '555-555-6666', 3);

-- Insert a few orders for demonstration
INSERT INTO Orders (customer_id, status, shipping_address_id, billing_address_id, shipping_method, tracking_number, total_amount, payment_method, payment_status) VALUES 
(1, 'Shipped', 1, 1, 'Standard', 'TRK123456789', 45.97, 'Credit Card', 'Paid'),
(2, 'Processing', 2, 2, 'Express', NULL, 63.96, 'PayPal', 'Paid'),
(3, 'Delivered', 3, 3, 'Standard', 'TRK987654321', 29.97, 'Credit Card', 'Paid');

-- Insert order items
INSERT INTO OrderItems (order_id, book_id, quantity, unit_price, discount, subtotal) VALUES 
(1, 1, 1, 14.99, 0, 14.99),
(1, 2, 1, 15.99, 0, 15.99),
(1, 3, 1, 16.99, 2.00, 14.99),
(2, 7, 1, 12.99, 0, 12.99),
(2, 8, 1, 13.99, 0, 13.99),
(2, 9, 1, 14.99, 0, 14.99),
(2, 10, 1, 15.99, 0, 15.99),
(3, 24, 1, 9.99, 0, 9.99),
(3, 26, 1, 13.99, 0, 13.99),
(3, 11, 1, 16.99, 11.00, 5.99);

-- Insert reviews
INSERT INTO Reviews (book_id, customer_id, rating, review_text) VALUES 
(1, 1, 5, CAST('Hilarious take on the original! Loved the gardening spells.' AS CLOB)),
(2, 1, 4, CAST('Not as good as the first one, but still funny.' AS CLOB)),
(3, 1, 5, CAST('The best in the series so far!' AS CLOB)),
(7, 2, 3, CAST('Somewhat amusing but the jokes get old fast.' AS CLOB)),
(8, 2, 4, CAST('Better than the first one, loved the lunch scene.' AS CLOB)),
(9, 2, 4, CAST('The solar eclipse vampire party was hilarious!' AS CLOB)),
(10, 2, 5, CAST('Perfect ending to the series!' AS CLOB)),
(24, 3, 5, CAST('A masterpiece of parody literature.' AS CLOB)),
(26, 3, 4, CAST('The fish perspective was unexpectedly deep.' AS CLOB)),
(11, 3, 5, CAST('The cod conspiracy had me in stitches!' AS CLOB));

-- Insert sample PageViews data
INSERT INTO PageViews (ip_address, country_id, visit_timestamp, user_agent, session_id, referrer_url, page_url) VALUES
('192.168.1.1', 1, TIMESTAMP('2025-04-20 10:15:32'), 'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36', 'session1234567890', 'https://www.google.com/search?q=best+books', 'http://localhost:8080/'),
