CREATE TABLE todos(
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    summary VARCHAR(255) DEFAULT NULL,
    status ENUM('DONE', 'TO_DO'),
    created_at TIMESTAMP DEFAULT NOW(),
    PRIMARY KEY (id),
    CONSTRAINT todos_users_id_fk FOREIGN KEY (user_id) REFERENCES users (id)
);