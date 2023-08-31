package top.lldwb.db.util.handle;

import top.lldwb.db.util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnHandler<T> implements ResultSetHandler<T> {
    private int columnIndex;

    public ColumnHandler(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public T handler(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? (T) RowProcessor.toColumn(resultSet, columnIndex) : null;
    }
}
