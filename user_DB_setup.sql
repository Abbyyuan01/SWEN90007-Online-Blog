CREATE TABLE users (
	id serial PRIMARY KEY,
	first_name VARCHAR(50),
	last_name VARCHAR(50),
	email VARCHAR(50),
	password VARCHAR(50)
);

INSERT INTO users values (1, 'John', 'Doe', 'jon@gmail.com', '1234');