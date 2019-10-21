CREATE TABLE FILES(
ID NUMBER,
CONSTRAINT FILE_PK PRIMARY KEY(ID),
FILE_NAME NVARCHAR2(50) NOT NULL,
FILE_FORMAT NVARCHAR2(50) NOT NULL,
FILE_SIZE NUMBER NOT NULL
);

CREATE SEQUENCE FILE_SEQ INCREMENT BY 1 MAXVALUE 1000 CYCLE;

DROP SEQUENCE ITEM_SEQ;

-- INSERT INTO item VALUES(101, 'Alex', to_date('2019/05/30:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'), to_date('2019/05/31:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'),'aa bb cc dd');
-- INSERT INTO item VALUES(102, 'Bob', to_date('2019/05/30:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'), to_date('2019/05/31:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'),'aa bb cc dd');
-- INSERT INTO item VALUES(103, 'Caddy', to_date('2019/05/30:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'), to_date('2019/05/31:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'),'aa bb cc dd');
-- INSERT INTO item VALUES(104, 'Dimentor', to_date('2019/05/30:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'), to_date('2019/05/31:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'),'aa bb cc dd');
-- INSERT INTO item VALUES(105, 'Evgen', to_date('2019/05/30:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'), to_date('2019/05/31:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'),'aa bb cc dd');
-- INSERT INTO item VALUES(106, 'ForEven', to_date('2019/05/30:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'), to_date('2019/05/31:12:00:00AM', 'yyyy/mm/dd:hh:mi:ssam'),'aa bb cc dd');