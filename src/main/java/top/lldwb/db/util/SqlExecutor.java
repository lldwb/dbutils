package top.lldwb.db.util;

import java.sql.*;

/**
 *  SQL执行器
 */
public class SqlExecutor {
    private Connection connection;

    public SqlExecutor(Connection connection) {
        this.connection = connection;
    }

    /**
     * 执行增删改操作
     *
     * @param sql     sql语句
     * @param objects 参数
     * @return
     */
    public int sqlUpdate(String sql, Object... objects) {
        if (connection == null) {
            throw new RuntimeException("connection Null");
        }
        if (sql == null) {
            throw new RuntimeException("sql Null");
        }

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(sql);
            addParam(preparedStatement, objects);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
            close();
        }
    }

    /**
     * 执行查询使用结果集对象处理结果集并返回结果
     *
     * @param sql     sql语句
     * @param handler 处理器
     * @param objects 参数
     * @param <T>
     * @return
     */
    public <T> T sqlQuery(String sql, ResultSetHandler<T> handler, Object... objects) {
        if (connection == null) {
            throw new RuntimeException("connection Null");
        }
        if (sql == null) {
            throw new RuntimeException("sql Null");
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        // O/R 相互转换:对象/关系 映射
        try {
            preparedStatement = connection.prepareStatement(sql);
            addParam(preparedStatement, objects);
            return handler.handler(preparedStatement.executeQuery());

//            // 关系转换成对象就叫对象关系映射
//            List<Object> list = new ArrayList<>();
//            while(resultSet.next()){
//                // 结果集映射成实体,不适合多表查询
//                Object object = null;
//
//                // 结果集映射成Object[]
//                Object[] objects1 = new Object[1];
//                objects1[0] = resultSet.getObject(1);
//
//                // 结果集映射成Map<String,Object>
//                Map<String,Object> map= new HashMap<>();
//                map.put(resultSet.getCursorName(),resultSet.getObject(1));
//            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(resultSet);
            close(preparedStatement);
            close();
        }
    }

    /**
     * 将参数插入到SQL语句的对象
     *
     * @param preparedStatement SQL语句的对象
     * @param objects           参数
     * @throws SQLException
     */
    private void addParam(PreparedStatement preparedStatement, Object... objects) throws SQLException {
        int count = 1;
        for (Object object : objects) {
            preparedStatement.setObject(count, object);
            count++;
        }
    }

    /**
     * 关闭结果集
     *
     * @param resultSet 结果集
     */
    private void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 关闭SQL语句的对象
     *
     * @param preparedStatement SQL语句的对象
     */
    private void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 关闭连接对象
     */
    private void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
