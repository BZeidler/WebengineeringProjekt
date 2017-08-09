-- Initialize database on startup. See
-- https://docs.spring.io/spring-boot/docs/current/reference/html/howto-database-initialization.html#howto-initialize-a-database-using-spring-jdbc
-- for explanation. This is a cool spring feature :-).

-- Empty tables
DELETE FROM MATCH_STATUS;
DELETE FROM USER_;

-- paswords are first 3 letters of the name, for example "ara" (without the "'s )for aragorn


-- Add some posts.
-- Used search terms "h2 timestamp", the second link lead to parsedatetime.
-- Note that JPA transforms your column names from camelCase to camel_case using underscores.
--INSERT INTO POST (ID, TITLE, CREATED_AT, AUTHOR_ID) VALUES
  --(1, 'title-1', parsedatetime('2017-05-20 05:01', 'yyyy-MM-dd HH:mm'), 1),
  --(2, 'title-2', parsedatetime('2017-05-21 12:01', 'yyyy-MM-dd HH:mm'), 2);
