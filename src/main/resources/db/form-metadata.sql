CREATE TABLE sakila.md_addr
(
  id             NUMERIC(19)          NOT NULL PRIMARY KEY,
  ui_description VARCHAR(200)         NOT NULL,
  city_id        SMALLINT(5) UNSIGNED NOT NULL,
  field_type     VARCHAR(20) CHECK (field_type IN ('INTEGER', 'NUMERIC', 'DATE')),
  FOREIGN KEY (city_id)
    REFERENCES sakila.address (city_id)
);

CREATE TABLE sakila.md_payment
(
  id             NUMERIC(19)          NOT NULL PRIMARY KEY,
  customer_id    SMALLINT(5) UNSIGNED NOT NULL,
  ui_description VARCHAR(200),
  field_type     VARCHAR(20) CHECK (field_type IN ('INTEGER', 'NUMERIC', 'DATE')),
  lang_type      VARCHAR(20) CHECK (lang_type IN ('LV', 'EN')),
  FOREIGN KEY (customer_id)
    REFERENCES payment (customer_id)
);