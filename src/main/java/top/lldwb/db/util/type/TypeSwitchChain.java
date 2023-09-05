package top.lldwb.db.util.type;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TypeSwitchChain {
    private static ServiceLoader<TypeSwitch> switches = ServiceLoader.load(TypeSwitch.class);
    private Iterator<TypeSwitch> iterator = switches.iterator();

    public Object doTypeSwitch(Class<?> clazz, Object value) {
        if (iterator.hasNext()) {
            TypeSwitch typeSwitch = iterator.next();
            return typeSwitch.valueOf(this, clazz, value);
        } else {
            throw new RuntimeException("没有对应的链");
        }
    }
}