-- Create PageViews table
CREATE TABLE PageViews (
    id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
    ip_address VARCHAR(45),
    country_id INT,
    visit_timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    user_agent VARCHAR(255),
    session_id VARCHAR(100),
    referrer_url VARCHAR(255),
    page_url VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (country_id) REFERENCES Countries(id)
);

-- Create index on country_id for faster lookups
CREATE INDEX idx_pageviews_country ON PageViews(country_id);

-- Create index on visit_timestamp for time-based queries
CREATE INDEX idx_pageviews_timestamp ON PageViews(visit_timestamp);
