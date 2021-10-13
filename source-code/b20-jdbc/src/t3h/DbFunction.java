package t3h;

import com.sun.rowset.JdbcRowSetImpl;

import java.io.File;
import java.sql.*;

public class DbFunction {
    public static void getAge(String name, int[] ages) throws SQLException {
        JdbcRowSetImpl jdbcRowSet = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            jdbcRowSet = new JdbcRowSetImpl();
            jdbcRowSet.setCommand("select max(age) from student where name like '%" +name+"%'");
            jdbcRowSet.setUrl("jdbc:derby:" + file.getAbsolutePath());
            jdbcRowSet.execute();
            ages[0] = jdbcRowSet.next() ? jdbcRowSet.getInt(1) : -1;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (jdbcRowSet != null) jdbcRowSet.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        int[] ages = new int[1];
        getAge("Thi Z", ages);
        System.out.println(ages[0]);
    }
}
