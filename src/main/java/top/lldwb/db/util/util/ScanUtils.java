package org.nf.db.util.util;

import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import top.lldwb.StrategyPattern.Calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 安然的尾巴
 * @version 1.0
 */
public class ScanUtils {

    /**
     * 扫描指定的包，并返回相关的Class对象
     *
     * @param packages
     * @return
     */
    public static List<Class<?>> scan(String... packages) {
        try (ScanResult scan = new ClassGraph().enableAllInfo().acceptPackages(packages).scan()) {
            return scan.getAllClasses().loadClasses();
        }
    }

    /**
     * 扫描指定的包，并返回相关的实现类对象
     *
     * @param clazz 接口
     * @param packages 包
     * @return
     */
    public static List<Class<?>> scanImpl(Class<?> clazz, String... packages) {
        List<Class<?>> list = new ArrayList<>();
        ScanUtils.scan(packages).forEach(aClass -> {
            if (clazz.isAssignableFrom(aClass) && clazz.get != aClass) {
                list.add(aClass);
            }
        });
        return list;
    }
}