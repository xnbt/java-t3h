package t3h;

import com.sun.rowset.JdbcRowSetImpl;

import java.io.File;
import java.sql.SQLException;

public class JdbcRowSetExample {
    public static void main(String[] args) throws SQLException {
        JdbcRowSetImpl jdbcRowSet = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            jdbcRowSet = new JdbcRowSetImpl();
            jdbcRowSet.setCommand("select * from student");
            jdbcRowSet.setUrl("jdbc:derby:" + file.getAbsolutePath());
            jdbcRowSet.execute();
            while (jdbcRowSet.next()) {
                System.out.println(jdbcRowSet.getInt("ID") + "\t" + jdbcRowSet.getString("name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (jdbcRowSet != null) jdbcRowSet.close();
        }
    }
}
