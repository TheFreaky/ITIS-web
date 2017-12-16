CREATE TABLE app.exercises
(
  exercise_id         SERIAL PRIMARY KEY,
  exercise_name       VARCHAR(50) UNIQUE NOT NULL,
  exercise_complexity app.COMPLEXITY     NOT NULL,
  exercise_type       app.SPECIALIZATION NOT NULL
);

CREATE TABLE app.trainings
(
  training_id          SERIAL PRIMARY KEY,
  training_name        VARCHAR(50) UNIQUE                              NOT NULL,
  training_description TEXT                                            NOT NULL,
  training_xp          INTEGER                                         NOT NULL,
  training_min_lvl     SMALLINT                                        NOT NULL,
  training_type        app.SPECIALIZATION DEFAULT 'Common' :: app.SPECIALIZATION,
  training_complexity  app.COMPLEXITY DEFAULT 'Beginner' :: app.COMPLEXITY NOT NULL
);

CREATE TABLE app.trainings_exercises
(
  id          SERIAL PRIMARY KEY,
  training_id INTEGER,
  exercise_id INTEGER,
  CONSTRAINT trainings_exercises_trainings_training_id_fk FOREIGN KEY (training_id) REFERENCES app.trainings (training_id),
  CONSTRAINT trainings_exercises_exercises_id_fk FOREIGN KEY (exercise_id) REFERENCES app.exercises (exercise_id)
);

CREATE TABLE app.users
(
  user_id             SERIAL PRIMARY KEY,
  user_login          VARCHAR(50) UNIQUE NOT NULL,
  user_password       VARCHAR(256)       NOT NULL,
  user_name           VARCHAR(50)        NOT NULL,
  user_weight         REAL,
  user_height         SMALLINT,
  user_specialization app.SPECIALIZATION DEFAULT 'Common' :: app.SPECIALIZATION,
  user_xp             INTEGER            DEFAULT 0 CHECK (user_xp > 0),
  user_strength       SMALLINT           DEFAULT 0,
  user_stamina        SMALLINT           DEFAULT 0,
  user_flexibility    SMALLINT           DEFAULT 0,
  user_gender         BOOLEAN
);
CREATE UNIQUE INDEX users_login_uindex
  ON app.users (user_login);

CREATE TABLE app.users_trainings
(
  id               SERIAL PRIMARY KEY,
  user_id          INTEGER,
  training_id      INTEGER,
  date             TIMESTAMP         NOT NULL,
  complete_percent INTEGER DEFAULT 0 NOT NULL CHECK (complete_percent > 0 AND complete_percent <= 100),
  CONSTRAINT users_trainings_users_id_fk FOREIGN KEY (user_id) REFERENCES app.users (user_id) ON DELETE CASCADE,
  CONSTRAINT users_trainings_trainings_training_id_fk FOREIGN KEY (training_id) REFERENCES app.trainings (training_id)
);

CREATE TYPE COMPLEXITY AS ENUM ('Beginner', 'Medium', 'Advanced');
CREATE TYPE SPECIALIZATION AS ENUM ('Agility', 'Strength', 'Flexibility', 'Common');