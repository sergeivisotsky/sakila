USE sakila;
CREATE TABLE md_frm
(
  id             NUMERIC(19)          NOT NULL PRIMARY KEY,
  ui_description VARCHAR(200)         NOT NULL,
  elem_number    SMALLINT(5) UNSIGNED NOT NULL,
  field_type     VARCHAR(20) CHECK (field_type IN ('INTEGER', 'NUMERIC', 'DATE'))
);

CREATE TABLE md_frm_type
(
  id          NUMERIC(19)  NOT NULL PRIMARY KEY,
  num_of_elem INTEGER(20)  NOT NULL,
  frm_descr   VARCHAR(100) NOT NULL,
  md_frm_id   NUMERIC(19)  NOT NULL,
  FOREIGN KEY (md_frm_id)
    REFERENCES sakila.md_frm (id)
);

CREATE TABLE md_translation
(
  id        NUMERIC(19) NOT NULL PRIMARY KEY,
  text      VARCHAR(200),
  lang_type VARCHAR(20) CHECK (lang_type IN ('LV', 'EN')),
  md_frm_id NUMERIC(19) NOT NULL,
  FOREIGN KEY (md_frm_id)
    REFERENCES sakila.md_frm (id)
);

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

INSERT INTO sakila.md_frm(id, ui_description, elem_number, field_type)
VALUES (1, 'This is test UI description', 5, 'NUMERIC');
INSERT INTO sakila.md_frm_type(id, num_of_elem, frm_descr, md_frm_id)
VALUES (1, 5, 'This is test form description one', 1);
INSERT INTO sakila.md_frm_type(id, num_of_elem, frm_descr, md_frm_id)
VALUES (2, 2, 'This is test form description two', 1);
INSERT INTO sakila.md_frm_type(id, num_of_elem, frm_descr, md_frm_id)
VALUES (3, 3, 'This is test form description three', 1);
INSERT INTO sakila.md_frm_type(id, num_of_elem, frm_descr, md_frm_id)
VALUES (4, 1, 'This is test form description four', 1);
INSERT INTO sakila.md_translation(id, ui_description, lang_type, md_frm_id)
VALUES (1, 'This is test ui description', 'EN', 1);
INSERT INTO sakila.md_translation(id, ui_description, lang_type, md_frm_id)
VALUES (2, 'Tas ir testa formas apraksts', 'LV', 1);