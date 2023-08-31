package top.lldwb.db.util.handle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanListHandler<T> extends AbstractListHandler<T>{
    private Class<?> clazz;

    public BeanListHandler(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T handleRow(ResultSet resultSet) throws SQLException {
        BeanHandler<T> beanHandler = new BeanHandler(clazz);
        return beanHandler.handler(resultSet);
    }
}
