package top.lldwb.db.util.type;

import java.util.Iterator;
import java.util.ServiceLoader;

public class TypeSwitchChain<T> {
    // 服务提供者加载工具，通过配置文件找到对应的接口的实现类并返回实现类的集合
    private static ServiceLoader<TypeSwitch> switches = ServiceLoader.load(TypeSwitch.class);
    // 迭代器
    private Iterator<TypeSwitch> iterator = switches.iterator();

    public T doTypeSwitch(Class<?> clazz, Object value) {
        while (iterator.hasNext()){
            TypeSwitch<T> tTypeSwitch = iterator.next();
            if (tTypeSwitch.supports(clazz)){
                return tTypeSwitch.valueOf(value);
            }
        }
        return null;
    }
}