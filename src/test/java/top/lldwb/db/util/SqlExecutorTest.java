package top.lldwb.db.util;

import org.junit.jupiter.api.Test;
import top.lldwb.db.util.entity.User;
import top.lldwb.db.util.handle.BeanHandler;
import top.lldwb.db.util.handle.ColumnHandler;
import top.lldwb.db.util.handle.MapHandler;
import top.lldwb.db.util.handle.ObjectArrayHandler;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

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
//        User user = sqlExecutor.sqlQuery("select * from user", new BeanHandler<>(User.class));
        User user = sqlExecutor.sqlQuery("select * from user", new BeanHandler<>(User.class));
        System.out.println(user);
    }

    @Test
    void test() throws IntrospectionException {
        class Person {
            private String name;
            private int age;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getAge() {
                return age;
            }

            public void setAge(int age) {
                this.age = age;
            }
        }

        BeanInfo info = Introspector.getBeanInfo(Person.class);
        PropertyDescriptor[] propertyDescriptors = info.getPropertyDescriptors();
        for (
                PropertyDescriptor pd : info.getPropertyDescriptors()) {
            System.out.println(pd.getName());
            System.out.println("  " + pd.getReadMethod());
            System.out.println("  " + pd.getWriteMethod());
        }

    }
}
