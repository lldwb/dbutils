package top.lldwb.db.util.type;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;

public interface TypeSwitch {
    /**
     * 可以处理返回值
     * 不可以处理返回 null
     *
     * @param propertyDescriptor 字段
     * @param value 需要转换的值
     * @return
     */
    Object valueOf(TypeSwitchChain typeSwitchChain, PropertyDescriptor propertyDescriptor, Object value);
}