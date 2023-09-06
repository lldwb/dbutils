package top.lldwb.db.util.handle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ColumnListHandler extends AbstractListHandler {
    private int columnIndex;

    public ColumnListHandler(int columnIndex) {
        this.columnIndex = columnIndex;
    }

    @Override
    public Object handleRow(ResultSet resultSet) throws SQLException {
        return RowProcessor.toColumn(resultSet, columnIndex);
    }
}
