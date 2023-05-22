import DatabaseConnection.ConnectionFactory;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.fail;

public class ConnectionFactoryTest {

    @Test
    public void testCreateConnection() {
        Connection conn = ConnectionFactory.getConnection();
        assertNotNull(conn);
    }

    @Test
    public void testCloseConnection() {
        Connection conn = ConnectionFactory.getConnection();
        assertNotNull(conn);

        ConnectionFactory.close(conn);

        try {
            conn.createStatement();
            fail("SQLException expected but not thrown");
        } catch (SQLException e) {
        }
    }

    @Test
    public void testCloseStatement() {
        Connection conn = ConnectionFactory.getConnection();
        assertNotNull(conn);

        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            assertNotNull(stmt);

            ConnectionFactory.close(stmt);

            stmt.executeQuery("SELECT * FROM my_table");
            fail("SQLException expected but not thrown");
        } catch (SQLException e) {
        }
    }

    @Test
    public void testCloseResultSet() {
        Connection conn = ConnectionFactory.getConnection();
        assertNotNull(conn);

        Statement stmt = null;
        ResultSet rs = null;

        try {
            stmt = conn.createStatement();
            assertNotNull(stmt);

            rs = stmt.executeQuery("SELECT * FROM my_table");
            assertNotNull(rs);

            ConnectionFactory.close(rs);

            rs.next();
            fail("SQLException expected but not thrown");
        } catch (SQLException e) {

        } finally {
            ConnectionFactory.close(stmt);
        }
    }
}
