package top.lldwb.db.util.type.typeswitch;

import top.lldwb.db.util.type.TypeSwitch;
import top.lldwb.db.util.type.TypeSwitchChain;

import java.beans.PropertyDescriptor;

public class StringSwitch implements TypeSwitch {
    @Override
    public Object valueOf(TypeSwitchChain typeSwitchChain, Class<?> clazz, Object value) {
        if (clazz == String.class) {
            return value;
        } else {
            return typeSwitchChain.doTypeSwitch(clazz,value);
        }
    }
}