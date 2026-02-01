CREATE TABLE author (id SERIAL PRIMARY KEY,
                     name VARCHAR(100) NOT NULL);

CREATE TABLE category (id SERIAL PRIMARY KEY,
                       name VARCHAR(100) NOT NULL);

CREATE TABLE books (id SERIAL PRIMARY KEY,
                    name VARCHAR(150) NOT NULL,
                    author_id INT NOT NULL,
                    category_id INT NOT NULL,
                    available BOOLEAN NOT NULL,
                    FOREIGN KEY (author_id) REFERENCES author(id),
                    FOREIGN KEY (category_id) REFERENCES category(id));
