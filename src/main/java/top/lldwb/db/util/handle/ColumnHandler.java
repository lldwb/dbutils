package top.lldwb.db.util.handle;

import top.lldwb.db.util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnHandler implements ResultSetHandler {
    private int columnIndex;

    public ColumnHandler(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public Object handler(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? RowProcessor.toColumn(resultSet, columnIndex) : null;
    }
}
