CREATE DATABASE boutique_hotel;

USE boutique_hotel;

CREATE TABLE
  extra (
    id SMALLINT unsigned auto_increment PRIMARY key,
    type tinytext NOT NULL
  );

CREATE TABLE
  room (
    id INT unsigned auto_increment PRIMARY key,
    title tinytext NOT NULL,
    description text NOT NULL,
    address tinytext NOT NULL,
    latitude FLOAT (10, 6) NOT NULL,
    longitude FLOAT (10, 6) NOT NULL,
    directions text NOT NULL,
    contact_phone_number VARCHAR(20) NOT NULL
  );

CREATE TABLE
  booking (
    id INT unsigned auto_increment PRIMARY key,
    customer_name tinytext NOT NULL,
    customer_surname tinytext NOT NULL,
    customer_email tinytext NOT NULL,
    breakfast tinyint (1) NOT NULL,
    START DATE NOT NULL,
    END DATE NOT NULL,
    room_id INT unsigned NOT NULL,
    CONSTRAINT booking_room_id_fk FOREIGN key (room_id) REFERENCES room (id)
  );

CREATE TABLE
  room_extra (
    room_id INT unsigned NOT NULL,
    extra_id SMALLINT unsigned NOT NULL,
    CONSTRAINT room_extra_extra_id_fk FOREIGN key (extra_id) REFERENCES extra (id) ON DELETE CASCADE,
    CONSTRAINT room_extra_room_id_fk FOREIGN key (room_id) REFERENCES room (id) ON DELETE CASCADE
  );