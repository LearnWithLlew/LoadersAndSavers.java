-- Bookstore Database Population Script (Modified for single table)

-- Insert Books (Parodies of popular books)
INSERT INTO books (title, subtitle, isbn13, isbn10, publisher_id, publication_date, edition, page_count, language, description, cover_image_url, price) VALUES 
('Harry Plotter and the Sorcerer''s Bone', 'A Magical Parody', '9781234567891', '1234567891', 1, '2001-06-26', '1st', 309, 'English', 'Young wizard learns to garden magically', 'https://example.com/covers/harryplotter1.jpg', 14.99),
('Harry Plotter and the Chamber of Secretions', NULL, '9781234567892', '1234567892', 1, '2002-07-02', '1st', 328, 'English', 'Harry deals with magical plumbing issues', 'https://example.com/covers/harryplotter2.jpg', 15.99),
('Harry Plotter and the Prisoner of Azkabanned', NULL, '9781234567893', '1234567893', 1, '2004-07-27', '1st', 342, 'English', 'Harry meets a gardener who was banned from Azkaban', 'https://example.com/covers/harryplotter3.jpg', 16.99),
('The Hunger Lames', 'A Dystopian Parody', '9782345678901', '2345678901', 3, '2010-09-14', '1st', 378, 'English', 'Teens with leg injuries compete in a deadly game show', 'https://example.com/covers/hungerlames.jpg', 13.99),
('Catching Flies', NULL, '9782345678902', '2345678902', 3, '2011-09-01', '1st', 391, 'English', 'The revolution continues with more insect collection', 'https://example.com/covers/catchingflies.jpg', 14.99),
('Mockingjay-Z', NULL, '9782345678903', '2345678903', 3, '2012-08-24', '1st', 403, 'English', 'The revolution meets hip-hop', 'https://example.com/covers/mockingjayZ.jpg', 15.99),
('Twilit', 'A Vampire Romance Parody', '9783456789012', '3456789012', 2, '2008-10-05', '1st', 498, 'English', 'Girl falls in love with a slightly illuminated vampire', 'https://example.com/covers/twilit.jpg', 12.99),
('New Noon', NULL, '9783456789013', '3456789013', 2, '2009-09-22', '1st', 501, 'English', 'The vampire saga continues at lunchtime', 'https://example.com/covers/newnoon.jpg', 13.99),
('Eclipsed', NULL, '9783456789014', '3456789014', 2, '2010-06-30', '1st', 511, 'English', 'Vampires during a solar phenomenon', 'https://example.com/covers/eclipsed.jpg', 14.99),
('Breaking Down', NULL, '9783456789015', '3456789015', 2, '2011-08-02', '1st', 520, 'English', 'The vampire saga concludes with structural failures', 'https://example.com/covers/breakingdown.jpg', 15.99);
