package top.lldwb.db.util.type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ServiceLoader;

public class TypeSwitchChain {
    private Iterator<TypeSwitch> iterator = ServiceLoader.load(TypeSwitch.class).iterator();

    public Object doTypeSwitch(Class<?> clazz, Object value) {
        if (iterator.hasNext()) {
            TypeSwitch typeSwitch = iterator.next();
            return typeSwitch.valueOf(this, clazz, value);
        } else {
            throw new RuntimeException("没有对应的链");
        }
    }
}