BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS messages;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE messages (
  message_id SERIAL,
  message_username varchar(50) NOT NULL,
  location varchar(50) NOT NULL,
  message varchar(500) NOT NULL,
  CONSTRAINT PK_message PRIMARY KEY (message_id),
  CONSTRAINT FK_message_username FOREIGN KEY (message_username) REFERENCES users(username)
);

COMMIT TRANSACTION;
