CREATE DATABASE boutique_hotel CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE boutique_hotel;

CREATE TABLE
  extra (
    id SMALLINT unsigned auto_increment PRIMARY key,
    name tinytext NOT NULL,
    description tinytext NOT NULL
  );

CREATE TABLE
  hotel (
    id INT unsigned auto_increment PRIMARY key,
    name tinytext NOT NULL,
    address tinytext NOT NULL,
    latitude FLOAT (10, 6) NOT NULL,
    longitude FLOAT (10, 6) NOT NULL,
    directions text NOT NULL,
    contact_phone_number VARCHAR(20) NOT NULL
  );

CREATE TABLE
  room (
    id INT unsigned auto_increment PRIMARY key,
    hotel_id INT unsigned NOT NULL,
    room_number INT unsigned NOT NULL,
    title tinytext NOT NULL,
    description text NOT NULL,
    bed_amount INT unsigned NOT NULL,
    price INT unsigned NOT NULL,
    CONSTRAINT room_hotel_id_fk FOREIGN key (hotel_id) REFERENCES hotel (id)
  );

CREATE TABLE
  customer (
    id INT unsigned auto_increment PRIMARY key,
    first_name tinytext NOT NULL,
    second_name tinytext NOT NULL,
    email tinytext NOT NULL,
    birth_date DATE NOT NULL
  );

CREATE TABLE
  booking (
    id INT unsigned auto_increment PRIMARY key,
    customer_id INT unsigned NOT NULL,
    breakfast tinyint (1) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    room_id INT unsigned NOT NULL,
    CONSTRAINT booking_room_id_fk FOREIGN key (room_id) REFERENCES room (id),
    CONSTRAINT booking_customer_id_fk FOREIGN key (customer_id) REFERENCES customer (id)
  );

CREATE TABLE
  room_extra (
    room_id INT unsigned NOT NULL,
    extra_id SMALLINT unsigned NOT NULL,
    CONSTRAINT room_extra_extra_id_fk FOREIGN key (extra_id) REFERENCES extra (id) ON DELETE CASCADE,
    CONSTRAINT room_extra_room_id_fk FOREIGN key (room_id) REFERENCES room (id) ON DELETE CASCADE
  );