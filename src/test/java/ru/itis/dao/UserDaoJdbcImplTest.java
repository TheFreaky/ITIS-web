package ru.itis.dao;

import com.google.common.collect.Lists;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.itis.dao.impl.UserDaoJdbcImpl;
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
    public static void setUp() {
        userDao = new UserDaoJdbcImpl(DbWrapper.getConnection());
    }

    @AfterClass
    public static void tearDown() {
        Lists.newArrayList("Save_test", "Save_test_error", "Update_test",
                "Save_test_enum", "Update_test_enum", "Find_total_month_xp")
                .forEach(s -> {
                    User user = userDao.findByLogin(s);
                    if (user != null) {
                        userDao.delete(user.getId());
                    }
                });
    }

    @Test
    public void testSave() {
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
    public void testUpdateNegativeXp() {
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
    public void testFind() {
        User user = userDao.findById(1L);
        assertNotNull(user);
        assertNotNull(user.getId());
        assertNotNull(user.getLogin());
        assertTrue(user.getLogin().equals("the#_freak"));
    }

    @Test
    public void testUpdate() {
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
    public void testUpdateWithEnum() {
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
    public void testDelete() {
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
    public void testFindAll() {
        List<User> users = userDao.findAll();
        assertNotNull(users);
        assertTrue(users.size() > 0);
        assertNotNull(users.get(0));
    }

    @Test
    public void testFindByLogin() {
        User user = userDao.findByLogin("the#_freak");
        assertNotNull(user);
        assertNotNull(user.getId());
        assertTrue(user.getId().equals(1L));
    }

    @Test
    public void testFindTotalXpLastMonthById() {
        User user = User.builder()
                .name("Test")
                .gender(true)
                .login("Find_total_month_xp")
                .password("qwerty123")
                .build();
        userDao.save(user);
        long totalMonthXp = userDao.findTotalXpLastMonthById(user.getId());
        assertEquals(0L, totalMonthXp);
    }
}