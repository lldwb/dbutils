package top.lldwb.db.util.type.witch;

import top.lldwb.db.util.type.TypeSwitch;
import top.lldwb.db.util.type.TypeSwitchChain;

import java.sql.Timestamp;

public class TimestampSwitch implements TypeSwitch {
    @Override
    public boolean supports(Class clazz) {
        return clazz == Timestamp.class;
    }

    @Override
    public Object valueOf(Object value) {
        return Timestamp.valueOf(value.toString());
    }
}
