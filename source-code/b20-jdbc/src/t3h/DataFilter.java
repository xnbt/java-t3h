package t3h;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.Predicate;
import java.sql.SQLException;

public class DataFilter implements Predicate {

    @Override
    public boolean evaluate(RowSet rs) {
        CachedRowSet crs = (CachedRowSet) rs;
        try {
            return crs.getString("name").indexOf("Le") > -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean evaluate(Object value, int column) throws SQLException {
        return false;
    }

    @Override
    public boolean evaluate(Object value, String columnName) throws SQLException {
        return false;
    }
}
