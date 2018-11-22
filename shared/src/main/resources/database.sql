DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  id IDENTITY PRIMARY KEY,
  name VARCHAR(255),
);

INSERT into customers values(5,'Dude');
INSERT into customers values(50, 'Dudette');

DROP TABLE IF EXISTS users;

CREATE TABLE users (
  id IDENTITY PRIMARY KEY,
  username VARCHAR(255),
);

INSERT into users values(6,'Test username');
INSERT into users values(60, 'Otheruser');