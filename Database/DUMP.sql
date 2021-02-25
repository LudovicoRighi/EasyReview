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
    age INTEGER UNSIGNED DEFAULT NULL,
    sex VARCHAR(10) DEFAULT NULL,
    expertice VARCHAR(150) DEFAULT NULL,
    score INTEGER DEFAULT 0, 
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES usr(id) ON DELETE CASCADE,
	FOREIGN KEY (questionnaire_id) REFERENCES questionnaire(id) ON DELETE CASCADE,
	CONSTRAINT only_one_form UNIQUE(user_id, questionnaire_id)
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
    form_id INTEGER UNSIGNED,
    response VARCHAR(150) DEFAULT NULL,
    PRIMARY KEY(id),
	FOREIGN KEY (question_id) REFERENCES question(id) ON DELETE CASCADE,
    FOREIGN KEY (form_id) REFERENCES filled_form(id) ON DELETE CASCADE

);

 -- ------------------------------------------------------------------------------------------------------------------------------


DELIMITER $$

CREATE TRIGGER computePoints
AFTER INSERT ON filled_form
FOR EACH ROW
BEGIN
DECLARE num_mark_quest INT;
DECLARE age_info, sex_info, exper_info INT;

	SELECT COUNT(*) INTO num_mark_quest FROM question
    WHERE questionnaire_id=new.questionnaire_id;
	
    IF (new.sex <> null)
		THEN 
			SET sex_info=1;
		ELSE 
			SET sex_info=0;
    END IF;
    
     IF (new.age <> null)
		THEN 
			SET age_info=1;
		ELSE 
			SET age_info=0;
    END IF;
    
       IF (new.expertice <> null)
		THEN 
			SET exper_info=1;
		ELSE 
			SET exper_info=0;
    END IF;
    
    
    UPDATE filled_form
    SET score = num_mark_quest + 2 * (age_info+ sex_info + exper_info)
    WHERE id = new.id;

END $$

DELIMITER ;

-- ------------------------------------------------------------------------------------------------------------------------------


DELIMITER $$

CREATE TRIGGER prevent_offensive_words
AFTER INSERT ON answer
FOR EACH ROW
BEGIN
		DECLARE banned_user INT;
        
        
		IF ( new.response IN ( SELECT word FROM offensive_word) )
        THEN
			SELECT  user_id INTO banned_user FROM filled_form 
						 WHERE id = new.form_id;
			UPDATE usr
			SET banned = 1
			WHERE id = banned_user;
			
            -- Deleting the Filled Form causes also the deletion of al the Answers that are linked to it because of the Cascading policy.
			DELETE FROM filled_form
            WHERE id = new.form_id;
            
		END IF;
END $$

DELIMITER ;

-- ------------------------------------------------------------------------------------------------------------------------------
 
-- SIGNAL sqlstate '45001' set message_text = "No way ! You cannot do this !";
 
drop trigger prevent_offensive_words  ;





INSERT INTO usr (username, email, password, banned, totalPoints) VALUES ('ludorighi', 'ludo.righi@hotmail.it', 'a', 0, 0);

INSERT INTO product (prod_name, photoimage) VALUES ('Playstation 5',_binary'abc');

INSERT INTO review (product_id, review_text) VALUES (1, 'Amazing product!!!!!');
INSERT INTO review (product_id, review_text) VALUES (1, 'Increadibile quality!!!!!');
INSERT INTO review (product_id, review_text) VALUES (1, 'I recommend it to everyone!');

INSERT INTO questionnaire (date_questionnaire, product_id) VALUES (date(now()) ,1);

INSERT INTO question (question_text, questionnaire_id) VALUES ('Do you like the product?',1);
INSERT INTO question (question_text, questionnaire_id) VALUES ('Do you play FIFA21?',1);
INSERT INTO question (question_text, questionnaire_id) VALUES ('Have you ever used this product?',1);


INSERT INTO product (prod_name, photoimage) VALUES ('XBox360',_binary'fgh');

INSERT INTO review (product_id, review_text) VALUES (2, 'I love it!');
INSERT INTO review (product_id, review_text) VALUES (2, 'I prefer Playstation honestly..');
INSERT INTO review (product_id, review_text) VALUES (2, 'I cant stop playing!');
INSERT INTO review (product_id, review_text) VALUES (2, 'Too expensive!');


INSERT INTO questionnaire (date_questionnaire, product_id) VALUES (date(now()) + 1,2);

INSERT INTO question (question_text, questionnaire_id) VALUES ('Do you like it?',2);
INSERT INTO question (question_text, questionnaire_id) VALUES ('Where did you buy it?',2);
INSERT INTO question (question_text, questionnaire_id) VALUES ('Did you try the product before buying it?',2);

INSERT INTO admn (username, email, password) VALUES ('paolo', 'paolo@mail.com', 'a');

INSERT INTO offensive_word (word) VALUES ('cappero');
INSERT INTO offensive_word (word) VALUES ('cavolo');
INSERT INTO offensive_word (word) VALUES ('accipicchia');