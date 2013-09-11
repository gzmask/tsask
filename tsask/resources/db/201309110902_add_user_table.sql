CREATE TABLE `user` (
    id  INTEGER PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(30) NOT NULL,
    password VARCHAR(30) NOT NULL
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

insert into Version (timestamp) values ("201309110902");
INSERT INTO user (username, password) VALUES ('isaac', 'F=ma');
INSERT INTO user (username, password) VALUES ('admin', 'admin');
