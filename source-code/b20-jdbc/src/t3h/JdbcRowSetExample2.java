package t3h;

import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.JdbcRowSet;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcRowSetExample2 {
    public static void main(String[] args) throws SQLException {
        JdbcRowSet jdbcRs = null;
        Connection connection = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            jdbcRs = new JdbcRowSetImpl(connection);
            jdbcRs.setUrl("jdbc:derby:" + file.getAbsolutePath() );
            jdbcRs.setCommand("select * from student");
            jdbcRs.execute();
            while (jdbcRs.next()){
                System.out.println(jdbcRs.getInt(1)+"\t" +jdbcRs.getString("name"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (jdbcRs != null) jdbcRs.close();
            if (connection != null) jdbcRs.close();
        }
    }
}
