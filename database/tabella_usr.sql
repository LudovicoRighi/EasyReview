use db_easyr;

create table usr(
	id INTEGER UNSIGNED AUTO_INCREMENT,
    username VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(45) NOT NULL,  
    banned TINYINT(1) DEFAULT 0,
    hasQOT TINYINT(1) DEFAULT 0, 
    pointOfToday INTEGER DEFAULT 0,
    totalPoints INTEGER DEFAULT 0,
    administrator TINYINT(1) DEFAULT 0,
    PRIMARY KEY (id)
);


INSERT INTO usr (username, email, password, banned, hasQOT) 
VALUES('eghera', 'egherardi@hotmail.it', 'miapass', 0, 1)
