package top.lldwb.db.util.handle;

import top.lldwb.db.util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListHandler<T> implements ResultSetHandler<List<T>> {
    @Override
    public List<T> handler(ResultSet resultSet) throws SQLException {
        List<T> list = new ArrayList<>();
        while (resultSet.next()){
            list.add(this.handleRow(resultSet));
        }
        return list;
    }
    public abstract T handleRow(ResultSet resultSet) throws SQLException;
}
