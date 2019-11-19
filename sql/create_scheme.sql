USE ride_tracker_db;
GO

DROP TABLE IF EXISTS ride;
GO

CREATE TABLE ride (
  id int IDENTITY(1, 1) NOT NULL,
  name varchar(100) NOT NULL,
  duration int NOT NULL,
  ride_date datetime DEFAULT NULL,
  PRIMARY KEY (id));
GO