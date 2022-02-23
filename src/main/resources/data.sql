DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS ads;
DROP TABLE IF EXISTS cities;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS ad_types;

CREATE TABLE users (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  FIRST_NAME VARCHAR(250) NOT NULL,
  LAST_NAME VARCHAR(250) NOT NULL,
  PASSWORD VARCHAR(250) NOT NULL,
  EMAIL VARCHAR(250) NOT NULL UNIQUE,
  ACTIVE INT DEFAULT 1
);

INSERT INTO users (ID, FIRST_NAME, LAST_NAME, PASSWORD, EMAIL) VALUES
  (1, 'Admin', 'Admin', 'admin123', 'admin@gmail.com');

CREATE TABLE cities (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(250) NOT NULL
);

INSERT INTO cities (ID, NAME) VALUES
  (1, 'Nikšić'),
  (2, 'Podgorica'),
  (3, 'Cetinje'),
  (4, 'Bar'),
  (5, 'Kotor'),
  (6, 'Tivat'),
  (7, 'Budva'),
  (8, 'Kolašin'),
  (9, 'Bijelo Polje'),
  (10, 'Pljevlja'),
  (11, 'Mojkovac'),
  (12, 'Žabljak'),
  (13, 'Ulcinj');

CREATE TABLE categories (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(250) NOT NULL
);

INSERT INTO categories (ID, NAME) VALUES
  (1, 'Kompjuteri'),
  (2, 'Telefoni'),
  (3, 'Auta'),
  (4, 'Nekretnine'),
  (5, 'Knjige'),
  (6, 'Muzika'),
  (7, 'Motori'),
  (8, 'Nakit'),
  (9, 'Sport'),
  (10, 'Ljubimci');

  CREATE TABLE statuses (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(250) NOT NULL
);

INSERT INTO statuses (ID, NAME) VALUES
  (1, 'Aktuelan'),
  (2, 'Završen'),
  (3, 'Istekao'),
  (4, 'Obrisan');

   CREATE TABLE ad_types (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(250) NOT NULL
);

INSERT INTO ad_types (ID, NAME) VALUES
  (1, 'Prodaja'),
  (2, 'Iznajmljivanje'),
  (3, 'Potražnja'),
  (4, 'Servis'),
  (5, 'Usluga');

CREATE TABLE ads (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  NAME VARCHAR(250) NOT NULL,
  DESCRIPTION VARCHAR(250) NOT NULL,
  CATEGORY_ID INT NOT NULL,
  CITY_ID INT NOT NULL,
  OWNER_ID INT NOT NULL,
  STATUS_ID INT NOT NULL,
  TYPE_ID INT NOT NULL,
  PRICE DOUBLE(10) NOT NULL,
  FOREIGN KEY (CATEGORY_ID) REFERENCES categories(ID),
  FOREIGN KEY (CITY_ID) REFERENCES cities(ID),
  FOREIGN KEY (OWNER_ID) REFERENCES users(ID),
  FOREIGN KEY (STATUS_ID) REFERENCES statuses(ID),
  FOREIGN KEY (TYPE_ID) REFERENCES ad_types(ID)
);

INSERT INTO ads (ID, NAME, DESCRIPTION, CATEGORY_ID, CITY_ID, OWNER_ID, STATUS_ID, TYPE_ID, PRICE) VALUES
  (1, 'Prodajem auto', 'Na prodaju GOLF 7 GTI, 2020 godiste, u odličnom stanju. Kao nov!', 3, 1, 1, 1, 1, 20000);

     CREATE TABLE images (
  ID INT PRIMARY KEY AUTO_INCREMENT,
  AD_ID INT NOT NULL,
  IMAGE VARCHAR(250) NOT NULL,
  FOREIGN KEY (AD_ID) REFERENCES ads(ID)
);