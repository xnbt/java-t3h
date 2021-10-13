package t3h;

import com.sun.rowset.JdbcRowSetImpl;

import java.io.File;
import java.sql.*;

public class CreateStoredProdureExample {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.createStatement();
            statement.executeUpdate("Create procedure GETAGE(STREAM_NAME varchar(255), out age int)" +
                    " parameter style java reads" +
                    " sql data language java external name 'jdbc.DbFunction.getAge'");
            System.out.println("done");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
    }
}
