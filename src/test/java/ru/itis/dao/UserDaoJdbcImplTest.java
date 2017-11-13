package ru.itis.dao;

import com.google.common.collect.Lists;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.itis.models.Specialization;
import ru.itis.models.User;
import ru.itis.utils.DbWrapper;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 30.10.2017
 *
 * @author Kuznetsov Maxim
 * @version v1.0
 */
public class UserDaoJdbcImplTest {
    private static UserDao userDao;

    @BeforeClass
    public static void setUp() throws Exception {
        userDao = new UserDaoJdbcImpl(DbWrapper.getConnection());
    }

    @AfterClass
    public static void tearDown() throws Exception {
        Lists.newArrayList("Save_test", "Save_test_error", "Update_test", "Save_test_enum", "Update_test_enum")
                .forEach(s -> {
                    User user = userDao.findByLogin(s);
                    if (user != null) {
                        userDao.delete(user.getId());
                    }
                });
    }

    @Test
    public void save() throws Exception {
        User user = User.builder()
                .name("Test")
                .gender(true)
                .login("Save_test")
                .password("qwerty123")
                .build();
        userDao.save(user);
        assertNotNull(user);
        assertNotNull(user.getId());
    }


    @Test(expected = IllegalArgumentException.class)
    public void updateNegativeXp() throws Exception {
        User user = User.builder()
                .name("Test")
                .gender(true)
                .login("Save_test_error")
                .password("qwerty123")
                .build();
        userDao.save(user);
        user.setXp(-1L);
        userDao.update(user);
        assertNotNull(user);
        assertNotNull(user.getId());
    }

    @Test
    public void find() throws Exception {
        User user = userDao.find(1L);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getLogin());
        assertTrue(user.getLogin().equals("the#_freak"));
    }

    @Test
    public void update() throws Exception {
        User user = User.builder()
                .name("Test")
                .gender(true)
                .login("Update_test")
                .password("qwerty")
                .build();
        userDao.save(user);
        user.setGender(false);
        userDao.update(user);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(false, user.getGender());
    }

    @Test
    public void updateWithEnum() throws Exception {
        User user = User.builder()
                .name("Test")
                .gender(true)
                .login("Update_test_enum")
                .password("qwerty")
                .build();
        userDao.save(user);
        user.setSpecialization(Specialization.Agility);
        userDao.update(user);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(Specialization.Agility, user.getSpecialization());
    }

    @Test
    public void delete() throws Exception {
        User user = User.builder()
                .name("Test")
                .gender(true)
                .login("Delete_test")
                .password("qwerty321")
                .build();
        userDao.save(user);
        userDao.delete(user.getId());
        User deletedUser = userDao.findByLogin("Delete_test");
        assertNull(deletedUser);
    }

    @Test
    public void findAll() throws Exception {
        List<User> users = userDao.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
        assertNotNull(users.get(0));
    }

    @Test
    public void findByLogin() throws Exception {
        User user = userDao.findByLogin("the#_freak");
        assertNotNull(user);
        assertNotNull(user.getId());
        assertTrue(user.getId().equals(1L));
    }

}