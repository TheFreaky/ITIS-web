package ru.itis.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.itis.entity.Training;
import ru.itis.entity.User;
import ru.itis.entity.UserTraining;
import ru.itis.utils.DBWrapper;

import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 04.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserTrainingDaoJdbcImplTest {
    private static UserTrainingDao userTrainingDao;
    private static User user;
    private static UserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {
        Connection conn = DBWrapper.getConnection();
        userDao = new UserDaoJdbcImpl(conn);
        userTrainingDao = new UserTrainingDaoJdbcImpl(conn, new TrainingDaoJdbcImpl(conn), userDao);
        user = User.builder()
                .name("Test")
                .login("UtTest")
                .password("qwerty")
                .build();
        userDao.save(user);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        userTrainingDao.findByUser(user.getId())
                .forEach(userTraining ->
                        userTrainingDao.delete(userTraining.getId())
                );
        userDao.delete(user.getId());
    }

    @Test
    public void save() throws Exception {
        UserTraining userTraining = UserTraining.builder()
                .date(LocalDate.now())
                .calories(10f)
                .user(user)
                .training(Training.builder()
                        .id(1)
                        .build())
                .build();
        userTrainingDao.save(userTraining);
        assertNotNull(userTraining);
        assertNotNull(userTraining.getId());
    }

    @Test
    public void find() throws Exception {
        UserTraining userTraining = userTrainingDao.find(1);
        assertNotNull(userTraining);
        assertNotNull(userTraining.getId());
        assertNotNull(userTraining.getCalories());
        assertNotNull(userTraining.getDate());
        assertNotNull(userTraining.getTraining());
        assertNotNull(userTraining.getTraining().getId());
        assertNotNull(userTraining.getUser());
        assertNotNull(userTraining.getUser().getId());
        assertNotNull(userTraining.getTraining().getName());
        assertTrue(userTraining.getTraining().getName().equals("Ez"));
    }

    @Test
    public void update() throws Exception {
        UserTraining userTraining = UserTraining.builder()
                .date(LocalDate.now())
                .calories(10f)
                .user(user)
                .training(Training.builder()
                        .id(2)
                        .build())
                .build();
        userTrainingDao.save(userTraining);
        userTraining.setCalories(11f);
        userTrainingDao.update(userTraining);
        assertNotNull(userTraining);
        assertNotNull(userTraining.getId());
        assertNotNull(userTraining.getCalories());
        assertTrue(userTraining.getCalories() == 11f);
    }

    @Test
    public void delete() throws Exception {
        UserTraining userTraining = UserTraining.builder()
                .date(LocalDate.now())
                .calories(100f)
                .user(user)
                .training(Training.builder()
                        .id(3)
                        .build())
                .build();
        userTrainingDao.save(userTraining);
        userTrainingDao.delete(userTraining.getId());
        UserTraining deletedUserTraining = userTrainingDao.find(userTraining.getId());
        assertNull(deletedUserTraining);
    }

    @Test
    public void findAll() throws Exception {
        List<UserTraining> trainings = userTrainingDao.findAll();
        assertNotNull(trainings);
        assertTrue(trainings.size() > 0);
        assertNotNull(trainings.get(0));
    }

    @Test
    public void findByUser() throws Exception {
        UserTraining userTraining = UserTraining.builder()
                .date(LocalDate.now())
                .calories(105f)
                .user(user)
                .training(Training.builder()
                        .id(4)
                        .build())
                .build();
        userTrainingDao.save(userTraining);

        List<UserTraining> trainings = userTrainingDao.findByUser(user.getId());
        assertNotNull(trainings);
        assertTrue(trainings.size() > 0);
        assertNotNull(trainings.get(0));
    }

}