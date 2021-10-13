package t3h;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactionExample {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath() + ";create=true");
            statement = connection.createStatement();
            //connection.setAutoCommit(false);
            connection.setAutoCommit(false);
            for (int i = 0; i < 10; i++) {
                String name = "tran van " + i;
                int age = 10 + i;
                String sql = "insert into student (name, age) values ('" + name + "'," + age + ")";
                statement.executeUpdate(sql);
            }
           // connection.rollback();
            ResultSet rs = statement.executeQuery("select count(*) from student");
            if (rs.next()) System.out.println("total records = " + rs.getInt(1));
           // connection.commit();
         // connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) connection.close();
            if (statement != null) statement.close();
        }
    }
}
