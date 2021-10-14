package t3h;

import com.sun.rowset.CachedRowSetImpl;

import javax.sql.rowset.CachedRowSet;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;

public class DataAccessLogic {
    private final static Logger logger = Logger.getLogger(DataAccessLogic.class.getName());
    private Connection connection = null;
    private CachedRowSet rowSet;
    private static final int pageSize = 10;
    private Statement statement;

    public DataAccessLogic() throws SQLException {
        File file = new File("./sampledb");
        this.connection = DriverManager.getConnection("jdbc:derby:" + file.getAbsolutePath());

        rowSet = new CachedRowSetImpl();
        rowSet.setCommand("select * from student");
        rowSet.execute(connection);
    }

    public void disconnect() {
        try {
            if (connection != null) connection.close();
        } catch (SQLException e) {
            logger.log(Level.WARNING, e.toString());
        }
    }

    List<String> loadNames(int page, int pageSize) throws SQLException {
        List<String> names = new ArrayList<>();
        rowSet.setPageSize(pageSize);
        int start = (page - 1) * pageSize + 1;
        if (!rowSet.absolute(start)) {
            return names;
        }

        rowSet.previous();
        while (rowSet.next()) {
            names.add(rowSet.getString("name"));
            if (names.size() >= pageSize) break;
        }
        return names;
    }

    public int numberOfPage() throws SQLException {
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select count(*) from student");
            if (!rs.next()) return 0;
            int total = rs.getInt(1);
            int totalPage = total / pageSize;
            if (total % totalPage != 0) totalPage++;
            return totalPage;
        } catch (Exception e) {
            if (statement != null) statement.close();
            throw e;
        }
    }

    public static void main(String[] args) throws SQLException {
        DataAccessLogic data = new DataAccessLogic();
        List<String> names = data.loadNames(2,400);


        //nhung tinh nang moi cua java 8
        // lambda, method reference
        names.forEach(System.out::println);


        System.out.println("total page =" + data.numberOfPage());

        IntStream.range(1, data.numberOfPage() + 1).forEach(pageNumber -> {
            System.out.println("===========" + pageNumber);
            try {
                List<String> names1 = data.loadNames(pageNumber,5);
                System.out.println(names1);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        });
    }
}
