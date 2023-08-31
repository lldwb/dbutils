package top.lldwb.db.util.type.typeswitch;

import top.lldwb.db.util.type.TypeSwitch;
import top.lldwb.db.util.type.TypeSwitchChain;

import java.beans.PropertyDescriptor;

public class IntegerSwitch implements TypeSwitch {
    @Override
    public Object valueOf(TypeSwitchChain typeSwitchChain, Class<?> clazz, Object value) {
        if (clazz == Integer.class || clazz == Integer.TYPE) {
            return Integer.valueOf(value.toString());
        } else {
            return typeSwitchChain.doTypeSwitch(clazz, value);
        }
    }
}
