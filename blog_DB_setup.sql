CREATE TABLE blogs (
	id serial PRIMARY KEY,
	author_id INT,
	updated_user INT,
	title VARCHAR(50),
	content TEXT,
	created_at TIMESTAMP,
	updated_at TIMESTAMP
);

INSERT INTO blogs values (1, 1, 'Hello', 'jon');