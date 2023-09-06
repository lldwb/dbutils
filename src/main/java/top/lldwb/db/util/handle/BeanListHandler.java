package top.lldwb.db.util.handle;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanListHandler<T> extends AbstractListHandler<T>{
    private Class<T> clazz;

    public BeanListHandler(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T handleRow(ResultSet resultSet) throws SQLException {
        return RowProcessor.toBean(resultSet,clazz);
    }
}
