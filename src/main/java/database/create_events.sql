CREATE TABLE events (
    event_id INT NOT NULL AUTO_INCREMENT,
    host_user_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    description VARCHAR(1000) NOT NULL,
    start_time DATETIME NOT NULL,
    latitude FLOAT NOT NULL,
    longitude FLOAT NOT NULL,
    PRIMARY KEY(event_id),
    FOREIGN KEY(host_user_id) REFERENCES users(user_id)
);