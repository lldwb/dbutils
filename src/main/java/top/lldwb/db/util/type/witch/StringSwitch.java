package top.lldwb.db.util.type.witch;

import top.lldwb.db.util.type.TypeSwitch;
import top.lldwb.db.util.type.TypeSwitchChain;

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