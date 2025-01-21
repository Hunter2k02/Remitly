CREATE TABLE IF NOT EXISTS bank (
                                           id BIGSERIAL PRIMARY KEY,
                                           address VARCHAR(1000) NOT NULL,
                                           bank_name VARCHAR(255) NOT NULL,
                                           country_iso2 VARCHAR(2) NOT NULL,
                                           country_name VARCHAR(255),
                                           swift_code VARCHAR(11) NOT NULL,
                                           is_headquarter BOOLEAN NOT NULL
);


