CREATE TABLE users (
    user_id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    fb_user_id INT,
    fb_access_token VARCHAR(255),
    PRIMARY KEY(user_id)
);