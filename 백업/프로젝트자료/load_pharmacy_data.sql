use barodocq;
LOAD DATA LOCAL INFILE 'C:/Users/hi6/Desktop/pharmacy_info.csv'
INTO TABLE pharmacy_info
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(ph_num, ph_name, ph_phone, ph_addr, ph_lat, ph_lng, ph_direction, ph_photo, ph_created_at, ph_updated_at, ph_night_yn, ph_holiday_yn);

-- pharmacy_hours (table name you wrote: hourspharmacy_info; adjust if needed)
LOAD DATA LOCAL INFILE 'C:/Users/hi6/Desktop/pharmacy_hours.csv'
INTO TABLE pharmacy_hours
CHARACTER SET utf8mb4
FIELDS TERMINATED BY ',' ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS
(phh_day_of_week, phh_open_time, phh_close_time, phh_open_yn, phh_created_at, phh_updated_at, ph_num);
