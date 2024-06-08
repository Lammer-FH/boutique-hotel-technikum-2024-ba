USE boutique_hotel

INSERT INTO hotel (name, address, latitude, longitude, directions, contact_phone_number) VALUES
('Technikum Boutique Hotel', 'Platz 6, 1200 Wien, Austria', 48.2391664, 16.3774409, 'The hotel is located in the heart of Vienna, easily accessible by public transport. The nearest metro station is Dresdnerstrasse on line U6.', '+43 1 1234567');

INSERT INTO room (hotel_id, room_number, title, description, bed_amount, price) VALUES
(1, 201, 'Family Deluxe', 'A spacious and modern room with child-friendly amenities and two queen beds.', 2, 150),
(1, 202, 'Business Single', 'A sleek room with a work desk, ergonomic chair, and a single king bed for the traveling professional.', 1, 130),
(1, 203, 'Romantic Escape', 'Perfect for couples, this room features a heart-shaped king bed, a jacuzzi, and a stunning city view.', 1, 180),
(1, 204, 'Executive Suite', 'An elegant suite with a separate living area, king bed, and modern amenities for business and leisure.', 1, 220),
(1, 205, 'Family Suite', 'A luxurious suite with multiple bedrooms, a play area, and cribs available upon request.', 4, 300),
(1, 206, 'Presidential Suite', 'The epitome of luxury, offering expansive space, refined decor, and top-notch amenities.', 3, 500);

INSERT INTO extra (name, description) VALUES
('BATHROOM', 'Badezimmer'),
('MINIBAR', 'Minibar'),
('TV', 'Fernseher'),
('HVAC', 'Klimaanlage'),
('WIFI', 'Wlan'),
('BATHTUB', 'Badewanne'),
('VIEW', 'Aussicht');

INSERT INTO room_extra (room_id, extra_id) VALUES
(1, 1), (1, 3), (1, 4), (1, 5), (1, 6),
(2, 1), (2, 2), (2, 3), (2, 4), (2, 5), (2, 7),
(3, 1), (3, 2), (3, 4), (3, 5), (3, 6), (3, 7),
(4, 1), (4, 2), (4, 3), (4, 5), (4, 6), (4, 7),
(5, 1), (5, 3), (5, 4), (5, 5), (5, 6), (5, 7),
(6, 1), (6, 2), (6, 3), (6, 4), (6, 5), (6, 6), (6, 7);

INSERT INTO customer (first_name, second_name, email, birth_date) VALUES
    ('John', 'Doe', 'john.doe@example.com', '1985-03-15'),
    ('Jane', 'Smith', 'jane.smith@example.com', '1990-07-22'),
    ('Michael', 'Johnson', 'michael.j@example.com', '1978-11-10');

INSERT INTO booking (customer_id, breakfast, start_date, end_date, room_id) VALUES
    (1, 1, '2024-06-10', '2024-06-15', 1),
    (2, 0, '2024-06-08', '2024-06-14', 2),
    (3, 1, '2024-08-05', '2024-08-10', 1);