package top.lldwb.db.util.handle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MapListHandler extends AbstractListHandler<Map<String,Object>>{
    @Override
    public Map<String, Object> handleRow(ResultSet resultSet) throws SQLException {
        MapHandler mapHandler = new MapHandler();
        return mapHandler.handler(resultSet);
    }
}
