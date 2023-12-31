package top.lldwb.db.util.util;

import top.lldwb.db.util.type.TypeSwitchChain;

import java.beans.PropertyDescriptor;

public class BeanUtils {
    /**
     *
     * @param propertyDescriptor
     * @param value
     * @return
     */
    public static Object toBean(PropertyDescriptor propertyDescriptor,Object value) {
        try {
            return new TypeSwitchChain().doTypeSwitch(propertyDescriptor.getPropertyType(),value);
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException(e);
        }
    }
}