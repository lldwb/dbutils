package top.lldwb.db.util.handle;

import top.lldwb.db.util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MapHandler implements ResultSetHandler<Map<String, Object>> {
    @Override
    public Map<String, Object> handler(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? RowProcessor.toMap(resultSet) : null;
    }
}
