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
    daily_calories DOUBLE PRECISION,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

CREATE TABLE meals (
    id BIGSERIAL,
    name VARCHAR(255) NOT NULL,
    proteins DOUBLE PRECISION,
    fats DOUBLE PRECISION,
    carbohydrates DOUBLE PRECISION,
    calories_per_serving DOUBLE PRECISION,
    CONSTRAINT pk_meals PRIMARY KEY (id)
);

CREATE TABLE meal_entries (
    id BIGSERIAL,
    user_id BIGINT NOT NULL,
    meal_id BIGINT NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    quantity INT NOT NULL ,
    CONSTRAINT pk_meal_entries PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES  users(id),
    FOREIGN KEY (meal_id) REFERENCES meals(id)
)