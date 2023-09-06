package top.lldwb.db.util.type.witch;

import top.lldwb.db.util.type.TypeSwitch;

public class StringSwitch implements TypeSwitch<String> {
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == String.class;
    }

    @Override
    public String valueOf(Object value) {
        return value.toString();
    }
}