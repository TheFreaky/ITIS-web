package ru.itis.dao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.itis.dao.impl.TrainingDaoJdbcImpl;
import ru.itis.dao.impl.UserDaoJdbcImpl;
import ru.itis.dao.impl.UserTrainingDaoJdbcImpl;
import ru.itis.models.Training;
import ru.itis.models.User;
import ru.itis.models.UserTraining;
import ru.itis.utils.DbWrapper;

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
    public static void setUp() {
        Connection conn = DbWrapper.getConnection();
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
    public static void tearDown() {
        userTrainingDao.findByUserId(user.getId())
                .forEach(userTraining ->
                        userTrainingDao.delete(userTraining.getId())
                );
        userDao.delete(user.getId());
    }

    @Test
    public void testSave() {
        UserTraining userTraining = UserTraining.builder()
                .date(LocalDate.now())
                .user(user)
                .training(Training.builder()
                        .id(1)
                        .build())
                .completePercent(100)
                .build();
        userTrainingDao.save(userTraining);
        assertNotNull(userTraining);
        assertNotNull(userTraining.getId());
    }

    @Test
    public void testFind() {
        UserTraining userTraining = userTrainingDao.findById(1);
        assertNotNull(userTraining);
        assertNotNull(userTraining.getId());
        assertNotNull(userTraining.getDate());
        assertNotNull(userTraining.getTraining());
        assertNotNull(userTraining.getTraining().getId());
        assertNotNull(userTraining.getUser());
        assertNotNull(userTraining.getUser().getId());
        assertNotNull(userTraining.getTraining().getName());
        assertTrue(userTraining.getTraining().getName().equals("Ez"));
    }

    @Test
    public void testUpdate() {
        UserTraining userTraining = UserTraining.builder()
                .date(LocalDate.now())
                .user(user)
                .training(Training.builder()
                        .id(1)
                        .build())
                .completePercent(100)
                .build();
        userTrainingDao.save(userTraining);

        LocalDate date = LocalDate.of(2017, 1, 2);
        userTraining.setDate(date);
        userTrainingDao.update(userTraining);

        UserTraining updated = userTrainingDao.findById(userTraining.getId());
        assertNotNull(updated);
        assertNotNull(updated.getId());
        assertNotNull(updated.getDate());
        assertTrue(updated.getDate().equals(date));
    }

    @Test
    public void testDelete() {
        UserTraining userTraining = UserTraining.builder()
                .date(LocalDate.now())
                .user(user)
                .training(Training.builder()
                        .id(1)
                        .build())
                .completePercent(100)
                .build();
        userTrainingDao.save(userTraining);
        userTrainingDao.delete(userTraining.getId());
        UserTraining deletedUserTraining = userTrainingDao.findById(userTraining.getId());
        assertNull(deletedUserTraining);
    }

    @Test
    public void testFindAll() {
        List<UserTraining> trainings = userTrainingDao.findAll();
        assertNotNull(trainings);
        assertTrue(trainings.size() > 0);
        assertNotNull(trainings.get(0));
    }

    @Test
    public void testFindByUser() {
        UserTraining userTraining = UserTraining.builder()
                .date(LocalDate.now())
                .user(user)
                .training(Training.builder()
                        .id(1)
                        .build())
                .completePercent(100)
                .build();
        userTrainingDao.save(userTraining);

        List<UserTraining> trainings = userTrainingDao.findByUserId(user.getId());
        assertNotNull(trainings);
        assertTrue(trainings.size() > 0);
        assertNotNull(trainings.get(0));
    }
}