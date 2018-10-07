CREATE TABLE Car (
	car_id int NOT NULL AUTO_INCREMENT,
    stateNumber varchar(255) NOT NULL,
    motorNumber varchar(255) NOT NULL,
    color varchar(255),
    model varchar(255),
    techPassportNumber varchar(255),
    PRIMARY KEY (car_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS Inspector (
            inspector_id int NOT NULL AUTO_INCREMENT,
            name varchar(255),
            surname varchar(255) NOT NULL,
            secondName varchar(255),
            job varchar(255),
            rank varchar(255),
            PRIMARY KEY (inspector_id)
            );