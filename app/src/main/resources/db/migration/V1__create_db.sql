CREATE TABLE IF NOT EXISTS client (
   id IDENTITY PRIMARY KEY,
   name VARCHAR(200) CHECK (LENGTH(name) >= 3) NOT NULL
   );

CREATE TABLE IF NOT EXISTS planet (
   id VARCHAR(10) PRIMARY KEY,
   name VARCHAR(500) CHECK (LENGTH(name) >=1) NOT NULL
   );

CREATE TABLE IF NOT EXISTS ticket (
   id IDENTITY PRIMARY KEY,
   created_at TIMESTAMP,
   client_id BIGINT NOT NULL,
   from_planet_id VARCHAR(10) NOT NULL,
   to_planet_id VARCHAR(10) NOT NULL,
   FOREIGN KEY (client_id) REFERENCES client(id),
   FOREIGN KEY (from_planet_id) REFERENCES planet(id),
   FOREIGN KEY (to_planet_id) REFERENCES planet(id)
   );