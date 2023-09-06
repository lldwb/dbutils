本人根据源码实现的仓库地址：https://github.com/lldwb/dbutils.git
## 学习总结
### 面向对象oo
#### 封装
`BeanProcessor`：根据结果集创建Bean实例中，通过方法的封装把编译时异常转换成运行时异常。因为编译时异常（又称已检查异常）调用时需要开发者去处理，如果转换成运行时异常调用时不需要抛出异常

### 设计原则(单一职责、开闭原则、依赖倒置、里氏替换、接口隔离)
`ResultSetHandler`：结果集处理器接口以及实现类`xxxHandler`单行处理器
#### 单一职责
实现类`xxxHandler`单行处理器只负责一种数据处理
#### 开闭原则-面向扩展开放，面向修改关闭
每需要一种处理器时，根据需要实现`ResultSetHandler`接口
#### 依赖倒置-面向抽象编程
在`SqlExecutor`：SQL执行器中依赖的是`ResultSetHandler`接口，不是具体的实现类
#### 里氏替换-父类出现的地方一定可以用子类的对象替换，子类不可以改变父类的行为和状态
在`SqlExecutor`：SQL执行器中`ResultSetHandler`父类出现的地方一定可以用实现类的对象替换
#### 接口隔离-不需要强制实现不必要的方法
`ResultSetHandler`接口只关注结果集的处理，而 `AbstractListHandler` 抽象类则扩展了这个接口。
1. **`ResultSetHandler` 接口**：专注于将单行结果集转化为 Java 对象的功能，只有一个方法 `T handle(ResultSet rs)`。
2. **`AbstractListHandler` 类**：扩展了 `ResultSetHandler` 接口，但它是一个抽象类，专注于将多行结果集转化为列表（List）的功能，并在内部实现了 `handle` 方法。如果需要自定义列表的构建逻辑，子类只需重写 `handleRow` 方法。

这两者的分工明确，符合接口隔离原则，客户端可以根据需要选择实现 `ResultSetHandler` 接口或扩展 `AbstractListHandler` 类，而不需要强制实现不必要的方法。

### 设计模式
#### [责任链](https://www.lldwb.top/archives/4932 "责任链")
`TypeSwitchChain`责任链：负责遍历`TypeSwitch`接口的实现类
`TypeSwitch`接口：把判断和转换拆分开成两个方法，先判断通过后执行转换
#### [模板方法模式](https://www.lldwb.top/archives/4512 "模板方法模式")
把要变化的放到子类中实现，把固定重复的放到父类中反复调用
`AbstractListHandler`抽象类：
	`handler`方法(重复)：负责将单行数据转换成多行数据
	`handleRow`抽象方法(变化)：单行查询的抽象方法提供给子类实现

### 泛型
#### [泛型的类型确定](https://www.lldwb.top/archives/4291 "泛型的类型确定")
实现类需要返回`List<T>`，但是接口的`handle`抽象方法的返回类型是接口的泛型类型。
只需要在实现接口时把接口的泛型定义为`List<T>`，那么接口的`handle`抽象方法的返回类型就是`List<T>`

### 反射
#### 内省
`BeanProcessor`：
`propertyDescriptors`：通过内省获取属性描述器数组
`hasColumnLabel`：根据属性描述器获取字段名称或者注解,并且判断是否和表的列名相符(根据属性描述器解析出表的列名)
`callSetter`：获取并调用set方法进行赋值操作

### 自定义注解
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value();
}
```
用于解决字段名和属性名不一致的问题，当不一致时只需要把该注解放到对应的属性时并且注解的值是字段名。
#### 反射中的作用
使用反射判断属性是否有对应注解，来判断是否有不一致的问题

### SPI机制
类型转换责任链`TypeSwitchChain`：
使用 `ServiceLoader` 类加载了服务提供者 `TypeSwitch` 的实现类。`ServiceLoader` 是 Java 标准库中用于加载服务提供者的工具类，它会查找配置文件 `META-INF/services/xxx`，其中 `xxx` 是服务接口的全限定名，找到配置文件后会加载其中的实现类。


## 感谢和总结
只要打好`java`基础，不管是看还是(根据思想)实现都`DButils`不难


