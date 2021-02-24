create schema db_easyr;
use db_easyr;

create table usr(
	id INTEGER UNSIGNED AUTO_INCREMENT,
    username VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(45) NOT NULL,  
    banned TINYINT(1) DEFAULT 0,
    totalPoints INTEGER DEFAULT 0, 
    PRIMARY KEY (id)
);


create table admn(
	id INTEGER UNSIGNED AUTO_INCREMENT,
    username VARCHAR(20) UNIQUE NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(45) NOT NULL,  
    PRIMARY KEY (id)
);


create table offensive_word(
	id INTEGER UNSIGNED AUTO_INCREMENT,
    word VARCHAR(20) UNIQUE NOT NULL, 
    PRIMARY KEY (id)
);


create table log(
	id INTEGER UNSIGNED AUTO_INCREMENT,
    user_id INTEGER UNSIGNED,
	ts timestamp NOT NULL,
	PRIMARY KEY (id),
	FOREIGN KEY (user_id) REFERENCES usr(id) ON DELETE CASCADE
);


create table product (
	id INTEGER UNSIGNED AUTO_INCREMENT,
    prod_name VARCHAR(30) NOT NULL,
	photoimage blob,
    PRIMARY KEY (id)
);
  
  
create table review (
	id INTEGER UNSIGNED AUTO_INCREMENT,
	product_id INTEGER UNSIGNED,
    review_text VARCHAR(400) NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);


create table questionnaire (
	id INTEGER UNSIGNED AUTO_INCREMENT,
    date_questionnaire date UNIQUE NOT NULL,
    product_id INTEGER UNSIGNED,
    PRIMARY KEY (id),
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE
);


create table filled_form (
	id INTEGER UNSIGNED AUTO_INCREMENT,
    user_id INTEGER UNSIGNED, 
    questionnaire_id INTEGER UNSIGNED,
    dateForm date UNIQUE NOT NULL,
    age INTEGER UNSIGNED DEFAULT NULL,
    sex VARCHAR(10) DEFAULT NULL,
    expertice VARCHAR(150) DEFAULT NULL,
    score INTEGER DEFAULT 0, 
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES usr(id) ON DELETE CASCADE,
	FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id) ON DELETE CASCADE
);


create table question (
	id INTEGER UNSIGNED AUTO_INCREMENT,
	question_text  VARCHAR(150) NOT NULL,
    questionnaire_id INTEGER UNSIGNED,
    PRIMARY KEY(id),
  	FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id) ON DELETE CASCADE
);


create table answer (
	id INTEGER UNSIGNED AUTO_INCREMENT,
	question_id INTEGER UNSIGNED,
    response VARCHAR(150) DEFAULT NULL,
    PRIMARY KEY(id),
	FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE
);


create table answer_form (
	answer_id INTEGER UNSIGNED,
	form_id INTEGER UNSIGNED,
    PRIMARY KEY( answer_id, form_id),   
	FOREIGN KEY (answer_id) REFERENCES answer(id) ON DELETE CASCADE,
	FOREIGN KEY (form_id) REFERENCES filled_form(id) ON DELETE CASCADE
);




INSERT INTO usr (username, email, password, banned, totalPoints) VALUES ('ludorighi', 'ludo.righi@hotmail.it', 'a', 0, 0);

INSERT INTO product (prod_name, photoimage) VALUES ('Playstation 5',_binary'abc');

INSERT INTO review (product_id, review_text) VALUES (1, 'Amazing product!!!!!');
INSERT INTO review (product_id, review_text) VALUES (1, 'Increadibile quality!!!!!');
INSERT INTO review (product_id, review_text) VALUES (1, 'I recommend it to everyone!');

INSERT INTO questionnaire (date_questionnaire, product_id) VALUES ( date(now()) ,1);

INSERT INTO question (question_text, questionnaire_id) VALUES ('Do you like the product?',1);
INSERT INTO question (question_text, questionnaire_id) VALUES ('Do you play FIFA21?',1);
INSERT INTO question (question_text, questionnaire_id) VALUES ('Have you ever used this product?',1);


