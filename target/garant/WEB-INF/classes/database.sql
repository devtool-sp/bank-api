/*All User's gets stored in APP_USER table*/
create table APP_USER (
   id BIGINT NOT NULL AUTO_INCREMENT,
   sso_id VARCHAR(30) NOT NULL,
   password VARCHAR(100) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (sso_id)
);
   
/* USER_PROFILE table contains all possible roles */ 
create table USER_PROFILE(
   id BIGINT NOT NULL AUTO_INCREMENT,
   type VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   UNIQUE (type)
);
   
/* JOIN TABLE for MANY-TO-MANY relationship*/  
CREATE TABLE APP_USER_USER_PROFILE (
    user_id BIGINT NOT NULL,
    user_profile_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, user_profile_id),
    CONSTRAINT FK_APP_USER FOREIGN KEY (user_id) REFERENCES APP_USER (id),
    CONSTRAINT FK_USER_PROFILE FOREIGN KEY (user_profile_id) REFERENCES USER_PROFILE (id)
);

/* USER_STATUS table contains all possible statuses */ 
create table USER_DEALS(
   id BIGINT NOT NULL AUTO_INCREMENT,
   deal_subject VARCHAR(30) NOT NULL,
   quantity VARCHAR(30) NOT NULL,
   complect VARCHAR(30) NOT NULL,
   weight VARCHAR(30) NOT NULL,
   additionaly VARCHAR(200),
   deal_sum BIGINT NOT NULL,
   terms DATETIME NOT NULL,
   buyer_sso VARCHAR(30) NOT NULL,
   seller_sso VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (buyer_sso) REFERENCES APP_USER (sso_id),
   FOREIGN KEY (seller_sso) REFERENCES APP_USER (sso_id)
);


/* MESSAGES table contains all possible messages */ 
create table MESSAGES(
   id BIGINT NOT NULL AUTO_INCREMENT,
   message TEXT(5000) NOT NULL,
   timestamp DATETIME NOT NULL,
   author_id BIGINT NOT NULL,
   deal_id BIGINT NOT NULL,
   arbitr_id BIGINT,
   customer_service_id BIGINT,
   PRIMARY KEY (id),
   FOREIGN KEY (author_id) REFERENCES APP_USER (id),
   FOREIGN KEY (customer_service_id) REFERENCES APP_USER (id),

);

/* SIMPLE_MESSAGES table contains all possible messages */ 
create table SIMPLE_MESSAGES(
   id BIGINT NOT NULL AUTO_INCREMENT,
   message TEXT(5000) NOT NULL,
   timestamp DATETIME NOT NULL,
   sender_id BIGINT NOT NULL,
   reciever_id BIGINT NOT NULL,
   chat_id BIGINT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (sender_id) REFERENCES APP_USER (id),
   FOREIGN KEY (reciever_id) REFERENCES APP_USER (id)
);

create table CHAT_APP(
   id BIGINT NOT NULL AUTO_INCREMENT,
   timestamp DATETIME NOT NULL,
   sender_app_id BIGINT NOT NULL,
   reciever_app_id BIGINT NOT NULL,
   chat_app_id VARCHAR(30) NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (chat_app_id) REFERENCES SIMPLE_MESSAGES (chat_id),
   FOREIGN KEY (sender_app_id) REFERENCES APP_USER (id),
   FOREIGN KEY (reciever_app_id) REFERENCES APP_USER (id)
);

/* SIMPLE_MESSAGES table contains all possible messages */ 
create table CREDIT_CARD(
   id BIGINT NOT NULL AUTO_INCREMENT,
   merchant_account VARCHAR(30) NOT NULL,
   amount DOUBLE NOT NULL,
   card VARCHAR(30) NOT NULL,
   cardholder_id BIGINT NOT NULL,
   PRIMARY KEY (id),
   FOREIGN KEY (cardholder_id) REFERENCES APP_USER (id),
);

/* Populate USER_PROFILE Table */
INSERT INTO USER_PROFILE(type)
VALUES ('USER');
  
INSERT INTO USER_PROFILE(type)
VALUES ('ADMIN');
  
INSERT INTO USER_PROFILE(type)
VALUES ('DBA');
  
/* Populate one Admin User */
INSERT INTO APP_USER(sso_id, password)
VALUES ('admin','$2a$10$4eqIF5s/ewJwHK1p8lqlFOEm2QIA0S8g6./Lok.pQxqcxaBZYChRm');
    
/* Populate JOIN Table */
INSERT INTO APP_USER_USER_PROFILE (user_id, user_profile_id)
  SELECT user.id, profile.id FROM app_user user, user_profile profile
  where user.sso_id='admin' and profile.type='ADMIN';
  
  
ALTER TABLE `garant1`.`SIMPLE_MESSAGES` 
ADD INDEX `chat_id` (`chat_id` ASC);

ALTER TABLE `garant1`.`CHAT_APP` 
ADD UNIQUE INDEX `chat_app_id_UNIQUE` (`chat_app_id` ASC);


 