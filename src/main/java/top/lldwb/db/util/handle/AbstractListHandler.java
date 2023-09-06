package top.lldwb.db.util.handle;

import top.lldwb.db.util.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 模板方法模式，把要变化的放到子类中实现，把固定重复的放到父类中反复调用
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
            // 调用子类实现的单行查询的抽象方法,将数据传入集合中
            list.add(handleRow(resultSet));
        }
        return list;
    }

    /**
     * 单行查询的抽象方法提供给子类实现
     * @param resultSet
     * @return
     * @throws SQLException
     */
    public abstract T handleRow(ResultSet resultSet) throws SQLException;
}
