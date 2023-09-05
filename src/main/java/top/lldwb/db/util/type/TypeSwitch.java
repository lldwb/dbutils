package top.lldwb.db.util.type;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public interface TypeSwitch<T> {
    /**
     * 进行判断,看实现类是否可以执行
     *
     * @param clazz 字段类型
     * @return
     */
    boolean supports(Class<?> clazz);

    /**
     * 处理值
     *
     * @param value 需要转换的值
     * @return
     */
    T valueOf(Object value);
}