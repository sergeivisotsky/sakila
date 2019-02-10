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
    REFERENCES md_frm (id)
);

CREATE TABLE md_translation
(
  id        NUMERIC(19) NOT NULL PRIMARY KEY,
  text      VARCHAR(200),
  lang_type VARCHAR(20) CHECK (lang_type IN ('LV', 'EN')),
  md_frm_id NUMERIC(19) NOT NULL,
  FOREIGN KEY (md_frm_id)
    REFERENCES md_frm (id)
);

CREATE TABLE md_addr
(
  id             NUMERIC(19)          NOT NULL PRIMARY KEY,
  ui_description VARCHAR(200)         NOT NULL,
  city_id        SMALLINT(5) UNSIGNED NOT NULL,
  field_type     VARCHAR(20) CHECK (field_type IN ('INTEGER', 'NUMERIC', 'DATE')),
  FOREIGN KEY (city_id)
    REFERENCES address (city_id)
);

CREATE TABLE md_payment
(
  id             NUMERIC(19)          NOT NULL PRIMARY KEY,
  customer_id    SMALLINT(5) UNSIGNED NOT NULL,
  ui_description VARCHAR(200),
  field_type     VARCHAR(20) CHECK (field_type IN ('INTEGER', 'NUMERIC', 'DATE')),
  lang_type      VARCHAR(20) CHECK (lang_type IN ('LV', 'EN')),
  FOREIGN KEY (customer_id)
    REFERENCES payment (customer_id)
);

INSERT INTO md_frm(id, ui_description, elem_number, field_type)
VALUES (1, 'This is test UI description', 5, 'NUMERIC');
INSERT INTO md_frm_type(id, num_of_elem, frm_descr, md_frm_id)
VALUES (1, 5, 'This is test form description one', 1);
INSERT INTO md_frm_type(id, num_of_elem, frm_descr, md_frm_id)
VALUES (2, 2, 'This is test form description two', 1);
INSERT INTO md_frm_type(id, num_of_elem, frm_descr, md_frm_id)
VALUES (3, 3, 'This is test form description three', 1);
INSERT INTO md_frm_type(id, num_of_elem, frm_descr, md_frm_id)
VALUES (4, 1, 'This is test form description four', 1);
INSERT INTO md_translation(id, text, lang_type, md_frm_id)
VALUES (1, 'This is test ui description', 'EN', 1);
INSERT INTO md_translation(id, text, lang_type, md_frm_id)
VALUES (2, 'Tas ir testa formas apraksts', 'LV', 1);

-- Stored procedures
DELIMITER $$

CREATE PROCEDURE get_form_type(IN form_id NUMERIC(19))
  READS SQL DATA
BEGIN
  SELECT
    ft.num_of_elem,
    ft.frm_descr
  FROM
    md_frm_type ft
  WHERE
      ft.md_frm_id = form_id;
END $$

CREATE PROCEDURE get_form_metadata(IN form_id NUMERIC(19), IN lang_type VARCHAR(20))
  READS SQL DATA
BEGIN
  SELECT
    f.ui_description,
    f.elem_number,
    f.field_type,
    t.lang_type,
    t.text
  FROM
    md_frm f
      JOIN
    md_translation t ON t.md_frm_id = f.id
  WHERE
      f.id = form_id
    AND t.lang_type = lang_type;
END $$

CREATE PROCEDURE get_addresses_of_all_customers()
  READS SQL DATA
BEGIN
  SELECT
    c.first_name,
    c.last_name,
    c.email,
    a.address,
    a.district,
    ci.city,
    co.country
  FROM
    customer c
      LEFT OUTER JOIN
    address a ON c.address_id = a.address_id
      LEFT OUTER JOIN
    city ci ON a.city_id = ci.city_id
      LEFT OUTER JOIN
    country co ON ci.country_id = co.country_id;
END $$