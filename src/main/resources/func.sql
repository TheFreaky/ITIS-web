CREATE VIEW trainings_with_exercises AS
  SELECT
    t.training_id,
    t.training_name,
    t.training_description,
    t.training_xp,
    t.training_min_lvl,
    t.training_type,
    t.training_complexity,
    e.exercise_id,
    e.exercise_name,
    e.exercise_complexity,
    e.exercise_type
  FROM app.trainings t
    LEFT JOIN app.trainings_exercises te ON (t.training_id = te.training_id)
    LEFT JOIN app.exercises e ON (e.exercise_id = te.exercise_id);


CREATE FUNCTION user_trainings_percents(id INT)
  RETURNS TABLE(training_id INTEGER, percents BIGINT) AS $$
BEGIN
  RETURN QUERY SELECT
                 users_trainings.training_id,
                 sum(complete_percent) AS percents
               FROM users_trainings
               WHERE users_trainings.user_id = $1
                     AND users_trainings.date >= date_trunc('month', current_date)
                     AND users_trainings.date < date_trunc('month', current_date + INTERVAL '1' MONTH)
               GROUP BY users_trainings.training_id;
END
$$ LANGUAGE plpgsql;

CREATE FUNCTION user_total_xp_last_month(id INTEGER)
  RETURNS TABLE(xp NUMERIC)
AS $$
BEGIN
  RETURN QUERY SELECT COALESCE(sum(p.percents * t.training_xp / 100), 0) AS xp
               FROM user_trainings_percents($1) AS p
                 INNER JOIN trainings AS t ON t.training_id = p.training_id;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION get_training_xp(id INT)
  RETURNS TABLE(xp INT) AS $$
BEGIN
  RETURN QUERY SELECT trainings.training_xp AS xp
               FROM trainings
               WHERE trainings.training_id = $1;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION app.update_xp_on_user_trainings_deleting()
  RETURNS TRIGGER AS $$
BEGIN
  UPDATE app.users
  SET (user_xp) = (user_xp - old.complete_percent * app.get_training_xp(old.training_id) / 100)
  WHERE users.user_id = old.user_id;
  RETURN old;
END;
$$ LANGUAGE plpgsql;

CREATE FUNCTION app.update_xp_on_user_trainings_addition()
  RETURNS TRIGGER
AS $$
BEGIN
  UPDATE app.users
  SET (user_xp) = (user_xp + new.complete_percent * app.get_training_xp(new.training_id) / 100)
  WHERE users.user_id = new.user_id;
  RETURN new;
END;
$$ LANGUAGE plpgsql;

CREATE VIEW trainings_with_exercises AS
  SELECT
    t.*,
    e.*
  FROM app.trainings AS t
    LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id
    LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id;


CREATE TRIGGER trig_xp_del
  BEFORE DELETE
  ON app.users_trainings
  FOR EACH ROW
EXECUTE PROCEDURE app.update_xp_on_user_trainings_deleting();

CREATE TRIGGER trig_xp_add
  BEFORE INSERT OR UPDATE
  ON app.users_trainings
  FOR EACH ROW
EXECUTE PROCEDURE app.update_xp_on_user_trainings_addition();


GRANT SELECT ON ALL TABLES IN SCHEMA app TO gym_app_user;
GRANT DELETE ON ALL TABLES IN SCHEMA app TO gym_app_user;
GRANT UPDATE ON ALL TABLES IN SCHEMA app TO gym_app_user;
GRANT INSERT ON ALL TABLES IN SCHEMA app TO gym_app_user;
REVOKE TRIGGER ON ALL TABLES IN SCHEMA app FROM gym_app_user;