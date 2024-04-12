BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users_favorites;

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
  message text NOT NULL,
  CONSTRAINT PK_message PRIMARY KEY (message_id),
  CONSTRAINT FK_message_username FOREIGN KEY (message_username) REFERENCES users(username)
);

CREATE TABLE users_favorites (
    users_favorites_id SERIAL,
    user_id integer,
    message_id integer,
    CONSTRAINT PK_users_favorites PRIMARY KEY (users_favorites_id),
    CONSTRAINT FK_user_id FOREIGN KEY (user_id) REFERENCES users(user_id),
    CONSTRAINT FK_message_id FOREIGN KEY (message_id) REFERENCES messages(message_id)
);

COMMIT TRANSACTION;
