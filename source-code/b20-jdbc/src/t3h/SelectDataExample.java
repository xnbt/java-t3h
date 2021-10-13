package t3h;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectDataExample {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from student");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt(3);
                System.out.println(id + "\t" + name + "\t" + age);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
    }
}
