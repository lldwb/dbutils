package top.lldwb.db.util.type.witch;

import top.lldwb.db.util.type.TypeSwitch;
import top.lldwb.db.util.type.TypeSwitchChain;

import java.sql.Timestamp;

public class TimestampSwitch implements TypeSwitch {
    @Override
    public Object valueOf(TypeSwitchChain typeSwitchChain, Class<?> clazz, Object value) {
        if (clazz == Timestamp.class) {
            return Timestamp.valueOf(value.toString());
        } else {
            return typeSwitchChain.doTypeSwitch(clazz,value);
        }
    }
}
