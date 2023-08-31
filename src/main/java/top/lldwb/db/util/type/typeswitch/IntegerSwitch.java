package top.lldwb.db.util.type.typeswitch;

import top.lldwb.db.util.type.TypeSwitch;
import top.lldwb.db.util.type.TypeSwitchChain;

import java.beans.PropertyDescriptor;

public class IntegerSwitch implements TypeSwitch {
    @Override
    public Object valueOf(TypeSwitchChain typeSwitchChain, PropertyDescriptor propertyDescriptor, Object value) {
        if (propertyDescriptor.getPropertyType() == Integer.class || propertyDescriptor.getPropertyType() == Integer.TYPE) {
            return Integer.valueOf(value.toString());
        } else {
            return typeSwitchChain.doTypeSwitch(propertyDescriptor, value);
        }
    }
}
