CREATE TABLE sakila.md_addr
(
  id             NUMERIC(19)          NOT NULL PRIMARY KEY,
  ui_description VARCHAR(200)         NOT NULL,
  city_id        SMALLINT(5) UNSIGNED NOT NULL,
  fld_type       VARCHAR(20) CHECK (fld_type IN ('INTEGER', 'NUMERIC', 'DATE')),
  FOREIGN KEY (city_id)
    REFERENCES sakila.address (city_id)
);