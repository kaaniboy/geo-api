CREATE TABLE invites (
    invite_id INT NOT NULL AUTO_INCREMENT,
    event_id INT NOT NULL,
    inviter_id INT NOT NULL,
    guest_id INT NOT NULL,
    state VARCHAR(10) NOT NULL,
    PRIMARY KEY(invite_id),
    FOREIGN KEY(event_id) REFERENCES events(event_id),
    FOREIGN KEY(inviter_id) REFERENCES users(user_id),
    FOREIGN KEY(guest_id) REFERENCES users(user_id)
);