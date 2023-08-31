package top.lldwb.db.util;

import top.lldwb.db.util.type.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BeanProcessor {

    /**
     * 根据结果集创建Bean实例
     */
    public Object createBean(ResultSet resultSet, Class<?> clazz) throws SQLException {
        // 1. 创建对象
        Object object = newInstance(clazz);
        // 2. 通过内省获取属性描述器数组
        PropertyDescriptor[] propertyDescriptors = propertyDescriptors(clazz);
        // 3. 获取结果集元数据
        ResultSetMetaData metaData = resultSet.getMetaData();
        // 4. 遍历结果集列
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            // 5. 获取列名(优先别名)
            String columnLabel = metaData.getColumnLabel(i);
            // 6. 遍历属性描述器数组
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                // 7. 根据属性描述器获取字段名称或者注解,并且判断是否和表的列名相符
                if (hasColumnLabel(columnLabel, propertyDescriptor, clazz)) {
                    // 8. 通过结果集获取数据并且进行类型转换
                    Object value = processColumn(propertyDescriptor, columnLabel, resultSet);
                    // 9. 获取并调用set方法进行赋值操作
                    callSetter(propertyDescriptor, object, value);
                }
            }
        }
        // 10. 返回结果
        return object;
    }

    /**
     * 根据Class对象创建实例
     *
     * @param clazz javaBean类型
     * @return javaBean对象
     */
    private Object newInstance(Class<?> clazz) {
        try {
            // 获取构造方法并且创建对象
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通过内省获取属性描述器数组
     *
     * @param clazz javaBean类型
     * @return 属性描述器数组
     */
    private PropertyDescriptor[] propertyDescriptors(Class<?> clazz) {
        try {
            // Introspector类为工具提供了一种了解目标Java Bean所支持的属性、事件和方法的标准方法。
            // BeanInfo接口创建BeanInfo类，并提供关于bean的方法、属性、事件和其他特性的显式信息
            BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
            // 返回bean的所有属性的描述符
            return beanInfo.getPropertyDescriptors();
        } catch (IntrospectionException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据属性描述器获取字段名称或者注解,并且判断是否和表的列名相符(根据属性描述器解析出表的列名)
     *
     * @param columnLabel        表的列名
     * @param propertyDescriptor 属性描述器:获取字段名称或者注解
     * @param clazz              javaBean类型
     * @return 判断是否和表的列名相符
     */
    private boolean hasColumnLabel(String columnLabel, PropertyDescriptor propertyDescriptor, Class<?> clazz) {
        try {
            // 字段名称
            String fieldName = propertyDescriptor.getName();
            // 通过字段名称获取字段
            Field field = clazz.getDeclaredField(fieldName);
            // 判断是否有对应注解
            if (field.isAnnotationPresent(Column.class)) {
                // 注解的值代替字段名称
                fieldName = field.getAnnotation(Column.class).value();
            }
            // 将此字符串与另一个字符串进行比较，忽略大小写考虑
            return fieldName.equalsIgnoreCase(columnLabel);
        } catch (NoSuchFieldException e) {
//            throw new RuntimeException(e);
            return false;
        }
    }

    /**
     * 通过结果集获取数据并且进行类型转换
     *
     * @param propertyDescriptor 属性描述器:用于获取数据类型进行类型转换
     * @param columnLabel        列名:根据特定的列名获取数据
     * @param resultSet          结果集:用于获取数据
     * @return 返回类型转换后的数据
     */
    private Object processColumn(PropertyDescriptor propertyDescriptor, String columnLabel, ResultSet resultSet) {
        try {
            // 获取数据类型
            Class<?> fieldType = propertyDescriptor.getPropertyType();
            // 根据特定的列名获取数据
            Object value = resultSet.getObject(columnLabel);
            // 如果字段不是基本数据类型,同时数据为空(为什么使用&&不使用||)
            if (!fieldType.isPrimitive() && value == null) {
                return null;
            }
            // 类型转换链
            return BeanUtils.toBean(propertyDescriptor,value);
//            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取并调用set方法进行赋值操作
     *
     * @param propertyDescriptor 属性描述器:用于获取set方法进行赋值操作
     * @param object             需要赋值的对象
     * @param value              赋值的值
     */
    private void callSetter(PropertyDescriptor propertyDescriptor, Object object, Object value) {
        try {
            // 获取并调用set方法进行赋值操作
            propertyDescriptor.getWriteMethod().invoke(object, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
