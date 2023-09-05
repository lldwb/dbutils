package top.lldwb.db.util.type.witch;

import top.lldwb.db.util.type.TypeSwitch;
import top.lldwb.db.util.type.TypeSwitchChain;

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
