package top.lldwb.db.util.handle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnListHandler<T> extends AbstractListHandler<T>{
    private int columnIndex;

    public ColumnListHandler(int columnIndex) {
        this.columnIndex = columnIndex;
    }
    @Override
    public T handleRow(ResultSet resultSet) throws SQLException {
        ColumnHandler<T> handler = new ColumnHandler(columnIndex);
        return handler.handler(resultSet);
    }
}
