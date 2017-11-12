package ru.itis.dao;

import com.google.common.collect.Lists;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.itis.models.Complexity;
import ru.itis.models.Training;
import ru.itis.utils.DbWrapper;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 04.11.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class TrainingDaoJdbcImplTest {
    private static TrainingDao trainingDao;

    @BeforeClass
    public static void setUp() throws Exception {
        trainingDao = new TrainingDaoJdbcImpl(DbWrapper.getConnection());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Lists.newArrayList("TestSave", "TestUpdate")
                .forEach(s -> {
                    Training training = trainingDao.findByName(s);
                    if (training != null) {
                        trainingDao.delete(training.getId());
                    }
                });
    }

    @Test
    public void save() throws Exception {
        Training training = Training.builder()
                .name("TestSave")
                .description("test")
                .xp(0)
                .minLvl((short) 1)
                .complexity(Complexity.Advanced)
                .build();
        trainingDao.save(training);
        assertNotNull(training);
        assertNotNull(training.getId());
    }

    @Test
    public void findWithoutExercises() throws Exception {
        Training training = trainingDao.find(3);
        assertNotNull(training);
        assertNotNull(training.getId());
        assertNotNull(training.getName());
        assertNotNull(training.getDescription());
        assertNotNull(training.getXp());
        assertNotNull(training.getMinLvl());
        assertNotNull(training.getExercises());
        assertTrue(training.getName().equals("Test"));
    }

    @Test
    public void findWithExercises() throws Exception {
        Training training = trainingDao.find(1);
        assertNotNull(training);
        assertNotNull(training.getExercises());
        assertTrue(training.getExercises().size() == 2);
    }

    @Test
    public void update() throws Exception {
        Training training = Training.builder()
                .name("TestUpdate")
                .description("test")
                .xp(1)
                .minLvl((short) 1)
                .complexity(Complexity.Advanced)
                .build();
        trainingDao.save(training);
        training.setXp(0);
        trainingDao.update(training);
        assertNotNull(training);
        assertNotNull(training.getId());
        assertTrue(training.getXp() == 0);
    }

    @Test
    public void delete() throws Exception {
        Training training = Training.builder()
                .name("TestDelete")
                .description("test")
                .xp(0)
                .minLvl((short) 1)
                .complexity(Complexity.Advanced)
                .build();
        trainingDao.save(training);
        trainingDao.delete(training.getId());
        Training deletedTraining = trainingDao.findByName("TestDelete");
        assertNull(deletedTraining);
    }

    @Test
    public void findAll() throws Exception {
        List<Training> trainings = trainingDao.findAll();
        assertNotNull(trainings);
        assertTrue(trainings.size() > 0);
        assertNotNull(trainings.get(0));
    }

    @Test
    public void findByName() throws Exception {
        Training training = trainingDao.findByName("Test");
        assertNotNull(training);
        assertNotNull(training.getId());
        assertTrue(training.getId().equals(3));
    }

}