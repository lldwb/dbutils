package top.lldwb.db.util.handle;

import top.lldwb.db.util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 实现ResultSetHandler<List<T>>,这样handler返回类型为List
 * 同时handler负责接收数据
 * 创建一个抽象方法handleRow负责让子类重写并调用子类对应的单行处理器
 *
 * @param <T>
 */
public abstract class AbstractListHandler<T> implements ResultSetHandler<List<T>> {
    @Override
    public List<T> handler(ResultSet resultSet) throws SQLException {
        // 创建集合用于接收数据
        List<T> list = new ArrayList<>();
        // 遍历结果集
        while (resultSet.next()) {
            // 将数据传入集合中
            list.add(this.handleRow(resultSet));
        }
        return list;
    }

    public abstract T handleRow(ResultSet resultSet) throws SQLException;
}
