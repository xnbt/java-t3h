package t3h;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateDataExample {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.prepareStatement("update student set name = ? where id = ?");
            statement.setString(1, "Le Thi Z");
            statement.setInt(2, 2);
            statement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
    }
}
