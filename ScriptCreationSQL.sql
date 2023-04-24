set @@global.time_zone = '+00:00' ;
set @@session.time_zone = '+00:00' ;

-- tool table
CREATE TABLE tool (
    tool_name VARCHAR(32),
    CONSTRAINT tool_name_pk PRIMARY KEY (tool_name)
);

-- recipe table
CREATE TABLE recipe (
    recipe_id NUMERIC(4) PRIMARY KEY,
    recipe_name VARCHAR(64) NOT NULL,
    steps VARCHAR(2048) NOT NULL,
    description VARCHAR(1024),
    creation_date DATE NOT NULL,
    is_favorite BIT DEFAULT 0 NOT NULL
);

-- utensil table
CREATE TABLE utensil (
    tool_id VARCHAR(32) NOT NULL,
    recipe_id NUMERIC(4) NOT NULL,
    CONSTRAINT tool_id_fk FOREIGN KEY (tool_id) REFERENCES tool(tool_name),
    CONSTRAINT utensil_pk PRIMARY KEY (tool_id, recipe_id),
    CONSTRAINT recipe_id_fk FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id) ON DELETE CASCADE
);

-- unit table
CREATE TABLE unit (unit_name VARCHAR(32) PRIMARY KEY);

-- consumable_type table
CREATE TABLE consumable_type (
    consumable_type_name VARCHAR(32) PRIMARY KEY
);

-- consumable table
CREATE TABLE consumable (
    consumable_name VARCHAR(32) PRIMARY KEY,
    is_vegan BIT DEFAULT 0 NOT NULL,
    unit_id VARCHAR(32) NOT NULL,
    description VARCHAR(1024),
    kcal NUMERIC(4) CONSTRAINT kcal_ck CHECK (
        kcal >= 0
        AND kcal <= 9999
    ),
    creation_date DATE NOT NULL,
    consumable_type_id VARCHAR(32) NOT NULL,
    CONSTRAINT unit_id_fk FOREIGN KEY (unit_id) REFERENCES unit(unit_name),
    CONSTRAINT consumable_type_id_fk FOREIGN KEY (consumable_type_id) REFERENCES consumable_type(consumable_type_name)
);

-- content table
CREATE TABLE content (
    content_id NUMERIC(4) PRIMARY KEY,
    consumable_id VARCHAR(32) NOT NULL,
    quantity NUMERIC(8, 2) DEFAULT 0 CONSTRAINT quantity_ck CHECK (
        quantity >= 0
        AND quantity <= 99999999
    ) NOT NULL,
    expiration_date DATE,
    CONSTRAINT consumable_id_fk FOREIGN KEY (consumable_id) REFERENCES consumable(consumable_name) ON DELETE CASCADE
);

-- alcohol_type table
CREATE TABLE alcohol_type (
    alcohol_type_name VARCHAR(32) PRIMARY KEY
);

-- drink table
CREATE TABLE drink (
    drink_name VARCHAR(32) NOT NULL,
    alcohol_type_id VARCHAR(32) NOT NULL,
    is_sugar_free BIT DEFAULT 0 NOT NULL,
    is_sparkling BIT DEFAULT 0 NOT NULL,
    alcohol_level NUMERIC(4, 2) DEFAULT 0 CONSTRAINT alcohol_level_ck CHECK (
        alcohol_level >= 0
        AND alcohol_level <= 100
    ) NOT NULL,
    CONSTRAINT drink_name_fk FOREIGN KEY (drink_name) REFERENCES consumable(consumable_name) ON DELETE CASCADE,
    CONSTRAINT alcohol_type_fk FOREIGN KEY (alcohol_type_id) REFERENCES alcohol_type(alcohol_type_name)
);

-- ingredient table
CREATE TABLE ingredient (
    required_quantity NUMERIC(8, 2) DEFAULT 0 CONSTRAINT required_quantity_ck CHECK (
        required_quantity >= 0
        AND required_quantity <= 999999.99
    ) NOT NULL,
    recipe_id NUMERIC(4) NOT NULL,
    consumable_id VARCHAR(32) NOT NULL,
    CONSTRAINT ingredient_consumable_id_fk FOREIGN KEY (consumable_id) REFERENCES consumable(consumable_name),
    CONSTRAINT ingredient_recipe_id_fk FOREIGN KEY (recipe_id) REFERENCES recipe(recipe_id) ON DELETE CASCADE
);