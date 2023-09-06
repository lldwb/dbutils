package top.lldwb.db.util.handle;

import top.lldwb.db.util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrayHandler implements ResultSetHandler<Object[]> {
    @Override
    public Object[] handler(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? RowProcessor.taArray(resultSet) : null;
    }
}
