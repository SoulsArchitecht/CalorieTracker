-- liquibase formatted sql

-- changeset serge_sh:22032025-1
CREATE TABLE users (
    id BIGSERIAL,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    age INT NOT NULL,
    gender VARCHAR(64),
    weight DOUBLE PRECISION NOT NULL,
    height DOUBLE PRECISION NOT NULL,
    goal VARCHAR(64),
    daily_calories INT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE dishes (
    id BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    proteins DOUBLE PRECISION,
    fats DOUBLE PRECISION,
    carbohydrates DOUBLE PRECISION,
    calories_per_serving INT,
    CONSTRAINT pk_dishes PRIMARY KEY (id)
);

CREATE TABLE meals (
    id BIGSERIAL,
    user_id BIGINT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    quantity INT NOT NULL ,
    CONSTRAINT pk_meals PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES  users(id)
);

CREATE TABLE meal_dish (
    meal_id BIGINT NOT NULL,
    dish_id BIGINT NOT NULL,
    FOREIGN KEY (meal_id) REFERENCES meals(id),
    FOREIGN KEY (dish_id) REFERENCES dishes(id)
)

