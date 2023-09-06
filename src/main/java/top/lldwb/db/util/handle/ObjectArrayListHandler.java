package top.lldwb.db.util.handle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectArrayListHandler extends AbstractListHandler<Object[]>{
    @Override
    public Object[] handleRow(ResultSet resultSet) throws SQLException {
        ObjectArrayHandler handler = new ObjectArrayHandler();
        return handler.handler(resultSet);
    }
}
