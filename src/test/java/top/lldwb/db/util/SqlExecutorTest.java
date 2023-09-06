package top.lldwb.db.util;

import org.junit.jupiter.api.Test;
import top.lldwb.db.util.entity.TestList;
import top.lldwb.db.util.entity.User;
import top.lldwb.db.util.handle.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class SqlExecutorTest {

    @Test
    void sqlQuery() throws SQLException {
        SqlExecutor sqlExecutor;

//        sqlExecutor = new SqlExecutor(ConnectionUtils.getConnection());
//        Object[] objects = sqlExecutor.sqlQuery("select * from user",new ArrayHandler());
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

//        sqlExecutor = new SqlExecutor(ConnectionUtils.getConnection());
//        List<User> list = sqlExecutor.sqlQuery("select * from user", new BeanListHandler<>(User.class));
//        list.forEach(k -> System.out.println(k));

        sqlExecutor = new SqlExecutor(ConnectionUtils.getConnection());
        TestList testList = sqlExecutor.sqlQuery("select * from test", new BeanHandler<>(TestList.class));
        System.out.println(testList.getList());
    }

    @Test
    void test() throws IntrospectionException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

//        User user = new User();
//        Method method = User.class.getMethod("set" + "UserName", String.class);
//        method.invoke(user, "反射");
//        System.out.println(user);

        List<String> list = new ArrayList<>();
        String[] strings = new String[1];
        System.out.println(list.getClass().isAssignableFrom(ArrayList.class));
        System.out.println(list.getClass().isAssignableFrom(List.class));
        System.out.println(strings.getClass().isAssignableFrom(ArrayList.class));
        System.out.println(strings.getClass().isAssignableFrom(String[].class));
        System.out.println(new String[1].getClass().getName());
        System.out.println(Integer[].class.getName());

    }
}
