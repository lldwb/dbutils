package top.lldwb.db.util.type;

import top.lldwb.db.util.util.ScanUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TypeSwitchChain {
    private Iterator<TypeSwitch> iterator;
    private static List<Class<?>> classList;

    static {
        classList = ScanUtils.scanImpl(TypeSwitch.class, "top.lldwb.db.util.type.typeswitch");
    }

    public TypeSwitchChain() {
        List<TypeSwitch> list = new ArrayList<>();
        classList.forEach(clazz -> {
            try {
                list.add((TypeSwitch) clazz.newInstance());
            } catch (InstantiationException e) {
                throw new RuntimeException(e);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        iterator = list.listIterator();
    }

    public Object doTypeSwitch(PropertyDescriptor propertyDescriptor, Object value) {
        if (iterator.hasNext()) {
            TypeSwitch typeSwitch = iterator.next();
            return typeSwitch.valueOf(this, propertyDescriptor, value);
        } else {
            throw new RuntimeException("没有对应的链");
        }
    }
}