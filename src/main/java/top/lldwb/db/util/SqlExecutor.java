package top.lldwb.db.util;

import java.sql.*;

public class SqlExecutor {
    private Connection connection;

    public SqlExecutor(Connection connection) {
        this.connection = connection;
    }

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
            addParam(preparedStatement,objects);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(preparedStatement);
            close();
        }
    }

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
            addParam(preparedStatement,objects);
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

    private void addParam(PreparedStatement preparedStatement, Object... objects) throws SQLException {
        int count = 1;
        for (Object object : objects) {
            preparedStatement.setObject(count, object);
            count++;
        }
    }

    private void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void close(PreparedStatement preparedStatement) {
        if (preparedStatement != null) {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

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
