package com.icyfate.interview.test.core.singleton;

/**
 * 枚举模式：最佳单例模式，通过枚举实例（static final只会实例化一次,实例化时调用构造器）获取单例
 *  优点：不仅保证了线程安全、延时加载，并且具备前面4中模式都不没有的优势
 *      1.public abstract class Enum<E extends Enum<E>> implements Comparable<E>, Serializable
 *        已经实现序列化，其他的模式需要额外的序列化工作（否则反序列化时会创建一个新的对象）
 *      2.其他四种模式可用使用反射强行调用私有构造器。枚举不会
 * @author sunbing
 * @version 1.0
 * @since 2021/6/4 10:43
 */
public class EnumSingleton {

    private static enum Singleton{
        INSTANCE;
        private EnumSingleton enumSingleton = null;
        //每个实例static final，故此构造器只会调用一次.
        private Singleton() {
            enumSingleton = new EnumSingleton();
        }

        private EnumSingleton getInstance(){
            return enumSingleton;
        }
    }

    private EnumSingleton() {
    }

    private static EnumSingleton getInstance(){
        return Singleton.INSTANCE.enumSingleton;
    }
}
