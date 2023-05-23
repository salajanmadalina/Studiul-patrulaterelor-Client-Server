import Service.UserService;
import Model.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.Assert.*;

public class UserTest {
    private UserService userService = new UserService();

    @BeforeAll
    void setUp() {
        userService = new UserService();
    }

    @Test
    void testInsert() {
        User user = new User.UserBuilder("Ana Maria", "1234", "ELEV", 30).build();
        userService.insert(user);
        User insertedUser = userService.findById(user.getId());
        assertNotNull(insertedUser);
        assertEquals(user.getNume(), insertedUser.getNume());
        assertEquals(user.getPassword(), insertedUser.getPassword());
        assertEquals(user.getRol(), insertedUser.getRol());
    }

    @Test
    void testFindByField() {
        User user = userService.findById(3);
        assertNotNull(user);
        assertEquals("manuela", user.getNume());
        assertEquals("1234", user.getPassword());
        assertEquals("ELEV", user.getRol());
    }

    @Test
    void testUpdate() {
        User user = userService.findById(3);
        user.setPassword("1234");
        userService.update("password", "1234", 3);
        User updatedUser = userService.findById(3);
        assertEquals("1234", updatedUser.getPassword());
    }

    @Test
    void testDelete() {
        User user = userService.findById(2);
        userService.delete(2);

        Exception exception = assertThrows(IndexOutOfBoundsException.class, () -> {
            User deletedUser = userService.findById(2);
        });
    }
}
