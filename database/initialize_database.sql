-- Bookstore Database Initialization Script

-- Location Tables

-- Countries
CREATE TABLE Countries (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(100) NOT NULL UNIQUE,
    code VARCHAR(3) NOT NULL UNIQUE
);

-- States
CREATE TABLE States (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(100) NOT NULL,
    code VARCHAR(10) NOT NULL,
    country_id INT NOT NULL,
    CONSTRAINT fk_states_country FOREIGN KEY (country_id) REFERENCES Countries(id),
    CONSTRAINT uk_states_name_country UNIQUE (name, country_id)
);

-- Cities
CREATE TABLE Cities (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(100) NOT NULL,
    state_id INT NOT NULL,
    postal_code VARCHAR(20),
    CONSTRAINT fk_cities_state FOREIGN KEY (state_id) REFERENCES States(id),
    CONSTRAINT uk_cities_name_state_postal UNIQUE (name, state_id, postal_code)
);

-- Addresses
CREATE TABLE Addresses (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    street_line1 VARCHAR(255) NOT NULL,
    street_line2 VARCHAR(255),
    city_id INT NOT NULL,
    CONSTRAINT fk_addresses_city FOREIGN KEY (city_id) REFERENCES Cities(id)
);

-- Core Entities

-- Authors
CREATE TABLE Authors (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    biography TEXT,
    birth_date DATE
);

-- Publishers
CREATE TABLE Publishers (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(255) NOT NULL UNIQUE,
    address_id INT,
    website VARCHAR(255),
    email VARCHAR(100),
    phone VARCHAR(20),
    CONSTRAINT fk_publishers_address FOREIGN KEY (address_id) REFERENCES Addresses(id)
);

-- Categories
CREATE TABLE Categories (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT,
    parent_id INT,
    CONSTRAINT fk_categories_parent FOREIGN KEY (parent_id) REFERENCES Categories(id)
);

-- Tags
CREATE TABLE Tags (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    name VARCHAR(50) NOT NULL UNIQUE
);

-- Books
CREATE TABLE Books (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    title VARCHAR(255) NOT NULL,
    subtitle VARCHAR(255),
    isbn13 VARCHAR(13) UNIQUE,
    isbn10 VARCHAR(10) UNIQUE,
    publisher_id INT NOT NULL,
    publication_date DATE,
    edition VARCHAR(50),
    page_count INT,
    language VARCHAR(50),
    description TEXT,
    cover_image_url VARCHAR(255),
    price DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_books_publisher FOREIGN KEY (publisher_id) REFERENCES Publishers(id)
);

-- Junction Tables

-- Book_Author
CREATE TABLE Book_Author (
    book_id INT,
    author_id INT,
    author_order INT NOT NULL,
    PRIMARY KEY (book_id, author_id),
    CONSTRAINT fk_book_author_book FOREIGN KEY (book_id) REFERENCES Books(id),
    CONSTRAINT fk_book_author_author FOREIGN KEY (author_id) REFERENCES Authors(id)
);

-- Book_Category
CREATE TABLE Book_Category (
    book_id INT,
    category_id INT,
    PRIMARY KEY (book_id, category_id),
    CONSTRAINT fk_book_category_book FOREIGN KEY (book_id) REFERENCES Books(id),
    CONSTRAINT fk_book_category_category FOREIGN KEY (category_id) REFERENCES Categories(id)
);

-- Book_Tag
CREATE TABLE Book_Tag (
    book_id INT,
    tag_id INT,
    PRIMARY KEY (book_id, tag_id),
    CONSTRAINT fk_book_tag_book FOREIGN KEY (book_id) REFERENCES Books(id),
    CONSTRAINT fk_book_tag_tag FOREIGN KEY (tag_id) REFERENCES Tags(id)
);

-- Inventory and Sales

-- Inventory
CREATE TABLE Inventory (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    book_id INT NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    location VARCHAR(100),
    last_updated TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_inventory_book FOREIGN KEY (book_id) REFERENCES Books(id)
);

-- Customers
CREATE TABLE Customers (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone VARCHAR(20),
    address_id INT,
    registration_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_customers_address FOREIGN KEY (address_id) REFERENCES Addresses(id)
);

-- Orders
CREATE TABLE Orders (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    customer_id INT NOT NULL,
    order_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(20) NOT NULL,
    shipping_address_id INT NOT NULL,
    billing_address_id INT NOT NULL,
    shipping_method VARCHAR(50),
    tracking_number VARCHAR(100),
    total_amount DECIMAL(10,2) NOT NULL,
    payment_method VARCHAR(50) NOT NULL,
    payment_status VARCHAR(20) NOT NULL,
    CONSTRAINT fk_orders_customer FOREIGN KEY (customer_id) REFERENCES Customers(id),
    CONSTRAINT fk_orders_shipping_address FOREIGN KEY (shipping_address_id) REFERENCES Addresses(id),
    CONSTRAINT fk_orders_billing_address FOREIGN KEY (billing_address_id) REFERENCES Addresses(id)
);

-- OrderItems
CREATE TABLE OrderItems (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    order_id INT NOT NULL,
    book_id INT NOT NULL,
    quantity INT NOT NULL,
    unit_price DECIMAL(10,2) NOT NULL,
    discount DECIMAL(10,2) DEFAULT 0,
    subtotal DECIMAL(10,2) NOT NULL,
    CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES Orders(id),
    CONSTRAINT fk_order_items_book FOREIGN KEY (book_id) REFERENCES Books(id)
);

-- User Engagement

-- Reviews
CREATE TABLE Reviews (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    book_id INT NOT NULL,
    customer_id INT NOT NULL,
    rating INT NOT NULL,
    review_text TEXT,
    review_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_reviews_book FOREIGN KEY (book_id) REFERENCES Books(id),
    CONSTRAINT fk_reviews_customer FOREIGN KEY (customer_id) REFERENCES Customers(id),
    CONSTRAINT uk_reviews_book_customer UNIQUE (book_id, customer_id),
    CONSTRAINT chk_reviews_rating CHECK (rating BETWEEN 1 AND 5)
);

-- Create Indexes
CREATE INDEX idx_books_title ON Books(title);
CREATE INDEX idx_books_publication_date ON Books(publication_date);
CREATE INDEX idx_authors_last_name ON Authors(last_name);
CREATE INDEX idx_orders_customer_id ON Orders(customer_id);
CREATE INDEX idx_orders_order_date ON Orders(order_date);
CREATE INDEX idx_order_items_order_id ON OrderItems(order_id);
CREATE INDEX idx_order_items_book_id ON OrderItems(book_id);
CREATE INDEX idx_reviews_book_id ON Reviews(book_id);
