package top.lldwb.db.util.type.witch;

import top.lldwb.db.util.type.TypeSwitch;

import java.util.ArrayList;
import java.util.List;

public class SplitTypeHandler implements TypeSwitch {
    private Class<?> clazz;

    @Override
    public boolean supports(Class clazz) {
        this.clazz = clazz;
        return clazz.isAssignableFrom(String[].class) || clazz.isAssignableFrom(ArrayList.class);
    }

    @Override
    public Object valueOf(Object value) {
        String[] strings = value.toString().split(",");
        if (clazz.isAssignableFrom(ArrayList.class)) {
            List<String> list = new ArrayList<>();
            for (String string : strings) {
                list.add(string);
            }
            return list;
        }
        return strings;
    }
}
