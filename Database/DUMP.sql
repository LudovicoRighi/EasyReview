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

create table product (
	id INTEGER UNSIGNED AUTO_INCREMENT,
    pathToImage VARCHAR(60) NOT NULL,
    pName VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);
    
create table review (
	id INTEGER UNSIGNED AUTO_INCREMENT,
	prodId INTEGER UNSIGNED,
    rText VARCHAR(400) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (prodId) REFERENCES product(id) ON DELETE CASCADE
);

INSERT INTO product (pathToImage, pName) VALUES ('path1', 'Playstation 5');

INSERT INTO product (pathToImage, pName) VALUES ('path2', 'Playstation 3');

SELECT * FROM product;

INSERT INTO review (prodId, rText) VALUES (2, 'Questa play è bellissima!!!');

SELECT * FROM review;

create table questionnaire (
	id INTEGER UNSIGNED AUTO_INCREMENT,
    date date UNIQUE NOT NULL,
    prodId INTEGER UNSIGNED,
    nQMark INTEGER UNSIGNED NOT NULL,
    qMark1 VARCHAR(150) DEFAULT NULL,
    qMark2 VARCHAR(150) DEFAULT NULL,
    qMark3 VARCHAR(150) DEFAULT NULL,
    qMark4 VARCHAR(150) DEFAULT NULL,
    qMark5 VARCHAR(150) DEFAULT NULL,
    qMark6 VARCHAR(150) DEFAULT NULL,
    qMark7 VARCHAR(150) DEFAULT NULL,
    qMark8 VARCHAR(150) DEFAULT NULL,
    qMark9 VARCHAR(150) DEFAULT NULL,
    qMark10 VARCHAR(150) DEFAULT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (prodId) REFERENCES product(id) ON DELETE CASCADE
);

INSERT INTO questionnaire (date, prodId, nQMark, qMark1, qMark2, qMark3 ) VALUES ('2020/12/23', 2, 3, 'Do you know the product?', 'Have you purchased the
product before?', 'Would you recommend the product to a friend?');

create table answer (
	id INTEGER UNSIGNED AUTO_INCREMENT,
    userId INTEGER UNSIGNED,
    questId INTEGER UNSIGNED,
    age INTEGER UNSIGNED DEFAULT NULL,
    sex VARCHAR(10) DEFAULT NULL,
    expertice VARCHAR(150) DEFAULT NULL,
    aMark1 VARCHAR(150) DEFAULT NULL,
    aMark2 VARCHAR(150) DEFAULT NULL,
    aMark3 VARCHAR(150) DEFAULT NULL,
    aMark4 VARCHAR(150) DEFAULT NULL,
    aMark5 VARCHAR(150) DEFAULT NULL,
    aMark6 VARCHAR(150) DEFAULT NULL,
    aMark7 VARCHAR(150) DEFAULT NULL,
    aMark8 VARCHAR(150) DEFAULT NULL,
    aMark9 VARCHAR(150) DEFAULT NULL,
    aMark10 VARCHAR(150) DEFAULT NULL,
    PRIMARY KEY(id),
	FOREIGN KEY (userId) REFERENCES usr(id) ON DELETE CASCADE,
    FOREIGN KEY (questId) REFERENCES questionnaire(id) ON DELETE CASCADE
);

INSERT INTO answer (userId, questId, age, sex, aMark1, aMark2, aMark3) VALUES (1, 1, 18, 'm', 'Yes', 'No, never', 'Of course!!!');

SELECT * FROM answer;


