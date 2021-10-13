package t3h;

import com.sun.rowset.FilteredRowSetImpl;
import com.sun.rowset.JdbcRowSetImpl;

import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import java.io.File;
import java.sql.*;

public class DataFilterExample {
    public static void main(String[] args) throws SQLException {
        JdbcRowSet jdbcRs = null;
        try {
            File file = new File("./sampledb");
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            FilteredRowSet frs = new FilteredRowSetImpl();
            frs.setUrl("jdbc:derby:" + file.getAbsolutePath());

            frs.setCommand("select * from student");
            frs.setFilter(new DataFilter());
            frs.execute();

            System.out.println("ID\tName\tAge");
            while (frs.next()){
                System.out.println(frs.getInt("id") +"\t" + frs.getString("name")
                        + "\t" + frs.getString("age"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (jdbcRs != null) jdbcRs.close();
        }
    }
}
