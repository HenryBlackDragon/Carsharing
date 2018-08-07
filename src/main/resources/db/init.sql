CREATE TABLE IF NOT EXISTS passport_data (
  id                  BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  surname             VARCHAR(50) NOT NULL,
  name                VARCHAR(50) NOT NULL,
  patronymic          VARCHAR(50) NOT NULL,
  series              VARCHAR(5)  NOT NULL,
  num_passport        INT         NOT NULL,
  date_issued         DATE        NOT NULL,
  organization_issued VARCHAR(50) NOT NULL,
  date_end            DATE        NOT NULL,
  date_born           DATE        NOT NULL,
  place_born          VARCHAR(50) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS driving_license (
  id                  BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  series              VARCHAR(5)  NOT NULL,
  num_license         INT         NOT NULL,
  date_issued         DATE        NOT NULL,
  organization_issued VARCHAR(50) NOT NULL,
  date_end            DATE        NOT NULL,
  category            VARCHAR(50) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS users (
  id                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  email              VARCHAR(50)  NOT NULL,
  password           VARCHAR(50)  NOT NULL,
  passport_id        BIGINT       NOT NULL,
  drivers_license_id BIGINT       NOT NULL,
  photo              VARCHAR(250) NOT NULL,
  phone_number       VARCHAR(50)  NOT NULL,
  FOREIGN KEY (passport_id) REFERENCES passport_data (id),
  FOREIGN KEY (drivers_license_id) REFERENCES driving_license (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS base_latlng (
  id  BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  lat VARCHAR(100) NOT NULL,
  lng VARCHAR(100) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS photos (
  id   BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  path VARCHAR(250) NOT NULL
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE IF NOT EXISTS cars (
  id                    BIGINT      NOT NULL AUTO_INCREMENT PRIMARY KEY,
  mark                  VARCHAR(50) NOT NULL,
  model                 VARCHAR(50) NOT NULL,
  release_year          INT         NOT NULL,
  state_num             INT         NOT NULL,
  mileage               INT         NOT NULL,
  count_seats           INT         NOT NULL,
  latLng_id             BIGINT      NOT NULL,
  box_type              VARCHAR(50) NOT NULL,
  body_type             VARCHAR(50) NOT NULL,
  gear                  VARCHAR(50) NOT NULL,
  engine                VARCHAR(50) NOT NULL,
  fuel_type             VARCHAR(50) NOT NULL,
  fuel_consumption      INT         NOT NULL,
  data_about_car        BLOB        NOT NULL,
  car_damages           BLOB        NOT NULL,
  insurance_information BLOB        NOT NULL,
  price                 INT         NOT NULL,
  text                  BLOB        NOT NULL,
  FOREIGN KEY (latLng_id) REFERENCES base_latlng (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

CREATE TABLE user_cars (
  user_id BIGINT NOT NULL,
  car_id  BIGINT NOT NULL,

  FOREIGN KEY (user_id) REFERENCES users (id),
  FOREIGN KEY (car_id) REFERENCES cars (id),
  UNIQUE (user_id, car_id)
)
  ENGINE = InnoDB;

CREATE TABLE car_photos (
  car_id   BIGINT NOT NULL,
  photo_id BIGINT NOT NULL,

  FOREIGN KEY (car_id) REFERENCES cars (id),
  FOREIGN KEY (photo_id) REFERENCES photos (id),
  UNIQUE (car_id, photo_id)
)
  ENGINE = InnoDB;