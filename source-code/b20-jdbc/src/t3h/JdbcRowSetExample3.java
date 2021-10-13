package t3h;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class JdbcRowSetExample3 {
    public static void main(String[] args) throws SQLException {
        JdbcRowSet jdbcRs = null;
        Connection connection = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            jdbcRs = new JdbcRowSetImpl(connection);
            jdbcRs.setUrl("jdbc:derby:" + file.getAbsolutePath());
            jdbcRs.setCommand("select * from student");
            jdbcRs.execute();
            jdbcRs.first();
            jdbcRs.updateString("name", "Hoang Van X");
            System.out.println(jdbcRs.getInt("id") + "\t" + jdbcRs.getString(2));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (jdbcRs != null) jdbcRs.close();
        }
    }
}
