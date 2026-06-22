CREATE TABLE menu_category (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    description VARCHAR(255)
);

CREATE TABLE dish (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10, 2),
    is_vegetarian BOOLEAN,
    category_id BIGINT NOT NULL,
    CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES menu_category(id)
);

CREATE TABLE ingredient (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE dish_ingredient (
    dish_id BIGINT NOT NULL,
    ingredient_id BIGINT NOT NULL,
    PRIMARY KEY (dish_id, ingredient_id),
    CONSTRAINT fk_dish FOREIGN KEY (dish_id) REFERENCES dish(id),
    CONSTRAINT fk_ingredient FOREIGN KEY (ingredient_id) REFERENCES ingredient(id)
);