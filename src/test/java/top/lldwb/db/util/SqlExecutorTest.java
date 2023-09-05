package top.lldwb.db.util;

import org.junit.jupiter.api.Test;
import top.lldwb.db.util.entity.User;
import top.lldwb.db.util.handle.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.List;

class SqlExecutorTest {

    @Test
    void sqlQuery() throws SQLException {
        SqlExecutor sqlExecutor;

//        sqlExecutor = new SqlExecutor(ConnectionUtils.getConnection());
//        Object[] objects = sqlExecutor.sqlQuery("select * from user",new ObjectArrayHandler());
//        for (Object object:objects){
//            System.out.println(object);
//        }
//
//        sqlExecutor = new SqlExecutor(ConnectionUtils.getConnection());
//        Map<String, Object> map = sqlExecutor.sqlQuery("select * from user", new MapHandler());
//        map.forEach((k, v) -> {
//            System.out.println(k + ":" + v);
//        });
//
//        sqlExecutor = new SqlExecutor(ConnectionUtils.getConnection());
//        Long count = sqlExecutor.sqlQuery("select count(*) from user",new ColumnHandler<>(1));
//        System.out.println(count);

        sqlExecutor = new SqlExecutor(ConnectionUtils.getConnection());
////        User user = sqlExecutor.sqlQuery("select * from user", new BeanHandler<>(User.class));
//        User user = sqlExecutor.sqlQuery("select * from user", new BeanHandler<>(User.class));
//        System.out.println(user);

        sqlExecutor = new SqlExecutor(ConnectionUtils.getConnection());
        List<User> list = sqlExecutor.sqlQuery("select * from user", new BeanListHandler<>(User.class));
        list.forEach(k -> System.out.println(k));
    }

    @Test
    void test() throws IntrospectionException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        User user = new User();
        Method method = User.class.getMethod("set" + "UserName", String.class);
        method.invoke(user, "反射");
        System.out.println(user);
    }
}
