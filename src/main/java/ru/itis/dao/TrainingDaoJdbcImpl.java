package ru.itis.dao;

import ru.itis.models.Complexity;
import ru.itis.models.Exercise;
import ru.itis.models.Specialization;
import ru.itis.models.Training;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 01.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class TrainingDaoJdbcImpl implements TrainingDao {
    private Connection connection;

    public TrainingDaoJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    private final static String SQL_INSERT = "INSERT INTO trainings (training_name, training_description, " +
            "training_xp, training_min_lvl, training_complexity, training_type) VALUES (?, ?, ?, ?, ?::COMPLEXITY, ?)";
    private final static String SQL_SELECT_ALL = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id;";
    private final static String SQL_SELECT_BY_ID = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id WHERE t.training_id = ?;";
    private final static String SQL_UPDATE = "UPDATE trainings SET (training_name, training_description, " +
            "training_xp, training_min_lvl, training_complexity, training_type) = (?, ?, ?, ?, ?::COMPLEXITY, ?) " +
            "WHERE training_id = ?";
    private final static String SQL_DELETE = "DELETE FROM trainings WHERE training_id = ?";
    private final static String SQL_SELECT_BY_NAME = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id WHERE training_name = ?;";
    private final static String SQL_SELECT_BY_LVL = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id WHERE training_min_lvl <= ?;";
    private final static String SQL_SELECT_ALL_COMPLEXITY_ASC = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id " +
            "ORDER BY training_complexity;";
    private final static String SQL_SELECT_ALL_COMPLEXITY_DESC = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id " +
            "ORDER BY training_complexity DESC;";
    private final static String SQL_SELECT_ALL_TYPE_ASC = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id " +
            "ORDER BY training_type;";
    private final static String SQL_SELECT_ALL_TYPE_DESC = "SELECT t.*, e.* FROM trainings AS t " +
            "LEFT JOIN trainings_exercises AS te ON t.training_id = te.training_id " +
            "LEFT JOIN exercises AS e ON e.exercise_id = te.exercise_id " +
            "ORDER BY training_type DESC;";


    @Override
    public void save(Training model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_INSERT,
                            Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, model.getName());
            stmt.setObject(2, model.getDescription());
            stmt.setObject(3, model.getXp());
            stmt.setObject(4, model.getMinLvl());
            if (model.getComplexity() != null) {
                stmt.setObject(5, model.getComplexity().toString());
            } else {
                stmt.setObject(5, model.getComplexity());
            }
            if (model.getType() != null) {
                stmt.setObject(6, model.getType().toString());
            } else {
                stmt.setObject(6, model.getType());
            }
            stmt.executeUpdate();

            ResultSet resultSet = stmt.getGeneratedKeys();
            if (resultSet.next()) {
                Integer id = resultSet.getInt("training_id");
                model.setId(id);
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Training find(Integer id) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, id);
            return find(stmt.executeQuery());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void update(Training model) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_UPDATE);
            stmt.setString(1, model.getName());
            stmt.setObject(2, model.getDescription());
            stmt.setObject(3, model.getXp());
            stmt.setObject(4, model.getMinLvl());
            if (model.getComplexity() != null) {
                stmt.setObject(5, model.getComplexity().toString());
            } else {
                stmt.setObject(5, model.getComplexity());
            }
            if (model.getType() != null) {
                stmt.setObject(6, model.getType().toString());
            } else {
                stmt.setObject(6, model.getType());
            }
            stmt.setLong(7, model.getId());
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            PreparedStatement stmt =
                    connection.prepareStatement(SQL_DELETE);
            stmt.setLong(1, id);
            stmt.execute();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Training> findAll() {
        try {
            return findAll(connection.createStatement().executeQuery(SQL_SELECT_ALL));
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Training findByName(String name) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_NAME);
            stmt.setString(1, name);
            return find(stmt.executeQuery());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Training> findAllByMinLvlLessThan(Integer lvl) {
        try {
            PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_BY_LVL);
            stmt.setInt(1, lvl);
            return findAll(stmt.executeQuery());

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Training> findAllByOrderByComplexityAsc() {
        try {
            return findAll(connection.createStatement().executeQuery(SQL_SELECT_ALL_COMPLEXITY_ASC));

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Training> findAllByOrderByComplexityDesc() {
        try {
            return findAll(connection.createStatement().executeQuery(SQL_SELECT_ALL_COMPLEXITY_DESC));

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Training> findAllByOrderByTypeAsc() {
        try {
            return findAll(connection.createStatement().executeQuery(SQL_SELECT_ALL_TYPE_ASC));

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Training> findAllByOrderByTypeDesc() {
        try {
            return findAll(connection.createStatement().executeQuery(SQL_SELECT_ALL_TYPE_DESC));

        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private List<Training> findAll(ResultSet rs) {
        try {
            List<Training> trainings = new ArrayList<>();
            int lastId = -1;
            while (rs.next()) {
                if (lastId != rs.getInt("training_id")) {
                    lastId = rs.getInt("training_id");

                    Specialization specialization = null;
                    if (rs.getString("training_type") != null) {
                        specialization = Specialization.valueOf(rs.getString("training_type"));
                    }

                    Complexity complexity = null;
                    if (rs.getString("training_complexity") != null) {
                        complexity = Complexity.valueOf(rs.getString("training_complexity"));
                    }

                    trainings.add(
                            Training.builder()
                                    .id(lastId)
                                    .name(rs.getString("training_name"))
                                    .description(rs.getString("training_description"))
                                    .xp(rs.getInt("training_xp"))
                                    .minLvl(rs.getShort("training_min_lvl"))
                                    .exercises(new HashSet<>())
                                    .complexity(complexity)
                                    .type(specialization)
                                    .build());
                }

                if (rs.getInt("exercise_id") == 0) continue;

                trainings.get(trainings.size() - 1).getExercises().add(Exercise.builder()
                        .id(rs.getInt("exercise_id"))
                        .name(rs.getString("exercise_name"))
                        .build());
            }
            return trainings;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private Training find(ResultSet rs) {
        try {

            Set<Exercise> exercises = new HashSet<>();
            Training training = null;

            while (rs.next()) {
                if (training == null) {
                    Specialization specialization = null;
                    if (rs.getString("training_type") != null) {
                        specialization = Specialization.valueOf(rs.getString("training_type"));
                    }

                    Complexity complexity = null;
                    if (rs.getString("training_complexity") != null) {
                        complexity = Complexity.valueOf(rs.getString("training_complexity"));
                    }

                    training = Training.builder()
                            .id(rs.getInt("training_id"))
                            .name(rs.getString("training_name"))
                            .description(rs.getString("training_description"))
                            .xp(rs.getInt("training_xp"))
                            .minLvl(rs.getShort("training_min_lvl"))
                            .exercises(exercises)
                            .type(specialization)
                            .complexity(complexity)
                            .build();
                }

                exercises.add(Exercise.builder()
                        .id(rs.getInt("exercise_id"))
                        .name(rs.getString("exercise_name"))
                        .build());
            }
            return training;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
