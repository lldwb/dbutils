package top.lldwb.db.util.handle;

import top.lldwb.db.util.BeanProcessor;
import top.lldwb.db.util.Column;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 行处理器，处理一行数据
 */
public class RowProcessor {

    /**
     * 转换成Object[]
     *
     * @param resultSet
     * @return
     */
    public static Object[] taArray(ResultSet resultSet) throws SQLException {
        // 先得到表的总列数(通过元数据获取)
        int count = resultSet.getMetaData().getColumnCount();
        // 创建Object[]数组,数组的长度为列的总数
        Object[] objects = new Object[count];
        for (int i = 0; i < count; i++) {
            objects[i] = resultSet.getObject(i + 1);
        }
        return objects;
    }

    /**
     * 转换成Map
     *
     * @param resultSet
     * @return
     */
    public static Map<String, Object> toMap(ResultSet resultSet) throws SQLException {
        // 先得到表的元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        Map<String, Object> map = new HashMap<>();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            map.put(metaData.getColumnLabel(i), resultSet.getObject(i));
        }
        return map;
    }

    public static Object toValue(ResultSet resultSet, int columnIndex) throws SQLException {
        return resultSet.getObject(columnIndex);
    }

    /**
     * 转换成实体类
     *
     * @param resultSet 结果集
     * @param clazz     实体类
     * @param <T>
     * @return
     * @throws SQLException
     */
    public static <T> T toBean(ResultSet resultSet, Class<T> clazz) throws SQLException {
        BeanProcessor beanProcessor = new BeanProcessor();
        return (T) beanProcessor.createBean(resultSet,clazz);

//        // 获取实体类的字段集合<注解名(没有注解为字段名),字段>
//        Map<String, Field> map = new HashMap<>();
//        for (Field field : clazz.getDeclaredFields()) {
//            field.setAccessible(true);
//
////            if (field.isAnnotationPresent(Column.class)){
////                map.put(field.getAnnotation(Column.class).value(),field);
////            }else{
////                map.put(field.getName(), field);
////            }
//
//            map.put(field.isAnnotationPresent(Column.class) ? field.getAnnotation(Column.class).value() : field.getName(), field);
//        }
//
//        // 先得到表的元数据
//        ResultSetMetaData metaData = resultSet.getMetaData();
//        try {
//            T t = clazz.newInstance();
//            for (int i = 1; i <= metaData.getColumnCount(); i++) {
//                String name = metaData.getColumnLabel(i);
//                if (map.containsKey(name)) {
//                    map.get(name).set(t, resultSet.getObject(i));
//                }
//            }
//            return t;
//        } catch (InstantiationException e) {
//            throw new RuntimeException(e);
//        } catch (IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
    }
}
