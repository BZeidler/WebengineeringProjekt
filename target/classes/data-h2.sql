-- Initialize database on startup. See
-- https://docs.spring.io\spring-boot\docs\current\reference\html\howto-database-initialization.html#howto-initialize-a-database-using-spring-jdbc
-- for explanation. This is a cool spring feature :-).

-- Empty tables
DELETE FROM CHAT_MESSAGES;
DELETE FROM MATCH_STATUS;
DELETE FROM USER_;

-- quotes copied from IMDB pages of each character
-- paswords are first 3 letters of the name, for example "ara" (without the "'s )for aragorn
INSERT INTO USER_ (ID, EMAIL, PASSWORD, NAME, MESSAGE)
VALUES(1,'aragorn@rivendell.net', '277e9a67bc99452594fe8e6593ea86cc94505a8946870ebb8944a2fcf20d4f5f41232f862d43889b40e91de8d8069e78709205afb76d842abe5e75adce35b45c', 'Aragorn', 'I can avoid being seen if I wish, but to disappear entirely, that is a rare gift');
INSERT INTO USER_ (ID, EMAIL, PASSWORD, NAME, MESSAGE)
VALUES(2,'arwen@rivendell.net', '944a0245c2a79c1bca06f30c6294f7a4d0f821ec9ed4f8e3884bda9d29200754aff7d6dec4a7e61975e8a265e407de41345994b1e81406ea0d633c13b23ae772', 'Arwen', 'I choose a mortal life. ');
INSERT INTO USER_ (ID, EMAIL, PASSWORD, NAME, MESSAGE)
VALUES(3,'gimli@bluemountains.net', '4cf76e5dd839ca100ff5bc2a493dd712f41e450df988e499a24cc4f65cf24e5dfe8acdf2c725ecffea074ac984b65b1c86b2c8f38c1f93601a29474e40bc6264', 'Gimli', 'That still only counts as one!');
INSERT INTO USER_ (ID, EMAIL, PASSWORD, NAME, MESSAGE)
VALUES(4,'legolas@mirkwood.net', '6909d26ff8c179c043077cec93668a788b54bf2c43415d04e6d49881ad6e17387d6516860928137da04bd5989debb237b844bedde46b9f93221b4f1f3f434913', 'Legolas', 'They´re taking the hobbits to Isengard');
INSERT INTO USER_ (ID, EMAIL, PASSWORD, NAME, MESSAGE)
VALUES(5,'sauron@mordor.net', 'eb5044d932daffa73c84ca3168aa8c30a4534dd00712662734397f9165552d1cbface6d71285785311377f81b796df8820ee59ef8e80fff290a98113fe1d6b28', 'Sauron', 'Build me an army worthy of Mordor');
INSERT INTO USER_ (ID, EMAIL, PASSWORD, NAME, MESSAGE)
VALUES(6,'saruman@isengard.net', '95f45463a4f1d0930a58dacb7f183af6bfa745fd4ec3f652107898b3f9d8415ffe2f046a44f9b167899f9da80814b9b17da886269f33c9e1be58e8942a6daf7a', 'Saruman', 'There will be no dawn... for men. ');
INSERT INTO USER_ (ID, EMAIL, PASSWORD, NAME, MESSAGE)
VALUES(7,'grima@rohan.net', 'd7e5edf2cfa564b98514692ef18dd78c9265ef29eb077bd6a8997e4b80816f3ab8e853c589cb50321df44367058eb7c0547eb7116209b5afeff3ba33c92815ac', 'Grima', 'But my lord ther is no such force!');
INSERT INTO USER_ (ID, EMAIL, PASSWORD, NAME, MESSAGE)
VALUES(8,'eowyn@rohan.net', '9ecfd5c6cd2d0ed211b378b650bca4eb8b3025d214f7458e8dcd5dad986ab71029a858ddd5a15c42d725a678ad591ebc91840bd9be40443b975d761c21f22b28', 'Eowyn', 'I am no man!');

INSERT INTO MATCH_STATUS(ID, INITIATOR_ID, TARGET_ID, STATE) VALUES(1, 1, 2, 'M');
INSERT INTO MATCH_STATUS(ID, INITIATOR_ID, TARGET_ID, STATE) VALUES(2, 2, 5, 'D');
INSERT INTO MATCH_STATUS(ID, INITIATOR_ID, TARGET_ID, STATE) VALUES(3, 3, 4, 'M');
INSERT INTO MATCH_STATUS(ID, INITIATOR_ID, TARGET_ID, STATE) VALUES(4, 4, 6, 'D');
INSERT INTO MATCH_STATUS(ID, INITIATOR_ID, TARGET_ID, STATE) VALUES(5, 5, 6, 'L');
INSERT INTO MATCH_STATUS(ID, INITIATOR_ID, TARGET_ID, STATE) VALUES(6, 6, 7, 'L');
INSERT INTO MATCH_STATUS(ID, INITIATOR_ID, TARGET_ID, STATE) VALUES(7, 7, 8, 'L');
INSERT INTO MATCH_STATUS(ID, INITIATOR_ID, TARGET_ID, STATE) VALUES(8, 8, 1, 'L');

INSERT INTO CHAT_MESSAGES(ID, AUTHOR_ID, MATCH_ID, CREATED_AT, MESSAGE) VALUES(1, 2, 1, parsedatetime('2017-05-20 05:01', 'yyyy-MM-dd HH:mm'), 'Why do you fear the past? You are Isildurs heir, not Isildur himself. You are not bound to his fate. ');
INSERT INTO CHAT_MESSAGES(ID, AUTHOR_ID, MATCH_ID, CREATED_AT, MESSAGE) VALUES(2, 1, 1, parsedatetime('2017-05-20 05:02', 'yyyy-MM-dd HH:mm'), 'The same blood flows in my veins. The same weakness.');
INSERT INTO CHAT_MESSAGES(ID, AUTHOR_ID, MATCH_ID, CREATED_AT, MESSAGE) VALUES(3, 2, 1, parsedatetime('2017-05-20 05:03', 'yyyy-MM-dd HH:mm'), 'Your time will come. You will face the same evil, and you will defeat it.');

-- Add some posts.
-- Used search terms "h2 timestamp", the second link lead to parsedatetime.
-- Note that JPA transforms your column names from camelCase to camel_case using underscores.
--INSERT INTO POST (ID, TITLE, CREATED_AT, AUTHOR_ID) VALUES
  --(1, 'title-1', parsedatetime('2017-05-20 05:01', 'yyyy-MM-dd HH:mm'), 1),
  --(2, 'title-2', parsedatetime('2017-05-21 12:01', 'yyyy-MM-dd HH:mm'), 2);