MaxiPago Challenge

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

MySQL Server, 8.0.21 version

SCRIPT:

drop database IF EXISTS MAXIPAGO;

CREATE SCHEMA MAXIPAGO;

USE MAXIPAGO;

CREATE TABLE IF NOT EXISTS CITY (
    ID INT NOT null COMMENT 'Unique ID for City',
    NAME VARCHAR(50) not null COMMENT 'Name for City',
    LATITUDE DOUBLE not null COMMENT 'Latitude for City',
    LONGITUDE DOUBLE NOT null COMMENT 'Longitude for City',
    TIMEZONE INT NOT null COMMENT 'Standard time zone GMT for City',
    PRIMARY KEY (id)
) comment='Table of cities';

INSERT INTO MAXIPAGO.CITY VALUES (1,'Brasília',-15.7801,-47.9292, -3);
INSERT INTO MAXIPAGO.CITY VALUES (2,'New York',-2.92887,-44.9357, -5);
INSERT INTO MAXIPAGO.CITY VALUES (3,'Xangai',31.224361,121.469170, 8);


/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
In order to run the project, it may be necessary to change the connection settings found in application.properties
Configurations : 

database.url=jdbc:mysql://localhost:3306/maxipago?useTimezone=true&serverTimezone=America/Sao_Paulo
database.user=root
database.password=admin

For a interactive documentation run the project and access:
http://localhost:8080/swagger-ui.html#/

/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
Justification for choosing the distance method (CityService Class):

Function chosen through the website https://dzone.com/, which is a site with "open source" content
which has an intended area only for Java, ranging from Tech News to practical functions like this one,
where I can compare latitude,longitude, and use measure as parameter.

K - Kilometers
M - Miles
N - Nautical Miles (did not use)

https://dzone.com/articles/distance-calculation-using-3 (specific url function with more than 30k view, bringing reliability)




Stenio Ramalho Vilar


