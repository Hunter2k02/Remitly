CREATE TABLE IF NOT EXISTS bank (
                                           id BIGSERIAL PRIMARY KEY,
                                           country_iso2 VARCHAR(2) NOT NULL,
                                           swift_code VARCHAR(11) NOT NULL,
                                           code_type VARCHAR(255) NOT NULL,
                                           bank_name VARCHAR(255) NOT NULL,
                                           address VARCHAR(1000) NOT NULL,
                                           town_name VARCHAR(255) NOT NULL,
                                           country_name VARCHAR(255) NOT NULL,
                                           time_zone VARCHAR(255) NOT NULL,
                                           is_headquarter BOOLEAN NOT NULL
);


