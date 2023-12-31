package top.lldwb.db.util.handle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArrayListHandler extends AbstractListHandler<Object[]>{
    @Override
    public Object[] handleRow(ResultSet resultSet) throws SQLException {
        return RowProcessor.taArray(resultSet);
    }
}
