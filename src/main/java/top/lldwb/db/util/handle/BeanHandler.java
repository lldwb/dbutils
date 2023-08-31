package top.lldwb.db.util.handle;

import top.lldwb.db.util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanHandler<T> implements ResultSetHandler<T> {
    private Class<T> clazz;

    public BeanHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T handler(ResultSet resultSet) throws SQLException {
        return resultSet.next() ? RowProcessor.toBean(resultSet,clazz) : null;
    }
}
