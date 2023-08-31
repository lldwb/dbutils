package top.lldwb.db.util.type.typeswitch;

import top.lldwb.db.util.type.TypeSwitch;
import top.lldwb.db.util.type.TypeSwitchChain;

import java.beans.PropertyDescriptor;

public class StringSwitch implements TypeSwitch {
    @Override
    public Object valueOf(TypeSwitchChain typeSwitchChain, PropertyDescriptor propertyDescriptor, Object value) {
        if (propertyDescriptor.getPropertyType() == String.class) {
            return value;
        } else {
            return typeSwitchChain.doTypeSwitch(propertyDescriptor,value);
        }
    }
}