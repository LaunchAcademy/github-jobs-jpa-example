CREATE TABLE jobs (
  id SERIAL PRIMARY KEY,
  github_id VARCHAR(255) NOT NULL,
  position_type VARCHAR(255),
  location VARCHAR(255),
  company_url VARCHAR(255),
  title VARCHAR(255)
)