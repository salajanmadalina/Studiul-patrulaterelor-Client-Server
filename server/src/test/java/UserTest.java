import Model.Dao.UserDAO;
import Model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;

public class UserTest {
    private UserDAO userDAO = new UserDAO();

    @BeforeAll
    void setUp() {
        userDAO = new UserDAO();
    }

    @Test
    void testInsert() {
        User user = new User("Ana Maria", "1234", "ELEV", 30);
        userDAO.insert(user);
        User insertedUser = userDAO.findById(user.getId());
        assertNotNull(insertedUser);
        assertEquals(user.getNume(), insertedUser.getNume());
        assertEquals(user.getPassword(), insertedUser.getPassword());
        assertEquals(user.getRol(), insertedUser.getRol());
    }

    @Test
    void testFindByField() {
        User user = userDAO.findById(3);
        assertNotNull(user);
        assertEquals("manuela", user.getNume());
        assertEquals("1234", user.getPassword());
        assertEquals("ELEV", user.getRol());
    }

    @Test
    void testUpdate() {
        User user = userDAO.findById(3);
        user.setPassword("1234");
        userDAO.update("password", "1234", 3);
        User updatedUser = userDAO.findById(3);
        assertEquals("1234", updatedUser.getPassword());
    }

    @Test
    void testDelete() {
        User user = userDAO.findById(2);
        userDAO.delete(2);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            User deletedUser = userDAO.findById(2);
        });
    }
}
