package top.lldwb.db.util;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandler<T> {
    T handler(ResultSet resultSet) throws SQLException;
}
