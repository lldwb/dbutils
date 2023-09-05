package top.lldwb.db.util.type.witch;

import top.lldwb.db.util.type.TypeSwitch;

public class IntegerSwitch implements TypeSwitch<Integer> {
    @Override
    public boolean supports(Class clazz) {
        return clazz == Integer.class || clazz == Integer.TYPE;
    }

    @Override
    public Integer valueOf(Object value) {
        return Integer.valueOf(value.toString());
    }
}
