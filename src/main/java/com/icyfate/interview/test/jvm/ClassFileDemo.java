package com.icyfate.interview.test.jvm;

/**
 * class文件
 *
 *
 * @author sunbing
 * @version 1.0
 * @since 2021/7/15 14:20
 */
public class ClassFileDemo {

    /**
     * 魔数 Magic Number
     * #CAFEBABE  作用：标识文件是一个JVM可处理的.class文件
     */

    /**
     * Minor Version    次版本号
     * Major Version    主版本号
     */

    /**
     * 常量池 Constant Pool
     * u2             constant_pool_count;//常量池的数量
     * cp_info        constant_pool[constant_pool_count-1];//常量池
     * 常量池中包括2中类型
     *      字面量：    字符串字面量，整型字面量，浮点型字面量
     *      符号引用：
     *          类和接口的全限定名
     *          字段的名称和描述符
     *          方法的名称和描述符
     *      类型              tag     描述
     *      CONSTANT_utf8_info	1	UTF-8 编码的字符串
             CONSTANT_Integer_info	3	整形字面量
             CONSTANT_Float_info	4	浮点型字面量
             CONSTANT_Long_info	５	长整型字面量
             CONSTANT_Double_info	６	双精度浮点型字面量
             CONSTANT_Class_info	７	类或接口的符号引用
             CONSTANT_String_info	８	字符串类型字面量
             CONSTANT_Fieldref_info	９	字段的符号引用
             CONSTANT_Methodref_info	10	类中方法的符号引用
             CONSTANT_InterfaceMethodref_info	11	接口中方法的符号引用
             CONSTANT_NameAndType_info	12	字段或方法的符号引用
             CONSTANT_MothodType_info	16	标志方法类型
             CONSTANT_MethodHandle_info	15	表示方法句柄
             CONSTANT_InvokeDynamic_info	18	表示一个动态方法调用点
     *
     *
     */

    /**
     *  访问标识 Access Flags ：标识此类或者接口的访问信息
     *      ACC_PUBLIC
     *      ACC_FINAL
     *      ACC_INTERFACE
     *      ACC_SUPER
     *      ACC_ABSTRACT
     *      ACC_SYNTHETIC
     *      ACC_ANNOTATION
     *      ACC_ENUM
     */

    /**
     *  当前类、父类、接口数量、接口集合
     *   u2             this_class;//当前类
     *   u2             super_class;//父类
     *   u2             interfaces_count;//接口
     *   u2             interfaces[interfaces_count];//一个类可以实现多个接口
     */

    /**
     *  字段表集合 Fields
     *   u2             fields_count;//Class 文件的字段的个数
     *   field_info     fields[fields_count];//一个类会可以有个字段
     *   字段表（field_info）结构
     *      field_info{
     *          u2      access_flags;       //字段访问标识        和上面访问标识不一样几个例子：ACC_STATIC ACC_VOLATILE ACC_TRANSIENT
     *          u2      name_index;         //字段名在常量池的符号引用
     *          u2      descriptor_index;   //描述在常量池的符号引用
     *          u2      attributes_count;   //额外属性数量
     *          attributes_info     attributes[attributes_count];   //额外属性
     *      }
     *
     */

    /**
     *  方法表集合 Methods
     *  u2             methods_count;//Class 文件的方法的数量
     *  method_info    methods[methods_count];//一个类可以有个多个方法
     *  方法表(method_info)结构
     *      method_info{
     *          u2      access_flags;       //方法访问标识        和上面访问标识不一样几个例子：ACC_SYCHRONIZED ACC_BRIDGE ACC_NATIVE
     *          u2      name_index;         //方法名在常量池的符号引用
     *          u2      descriptor_index;   //描述在常量池的符号引用
     *          u2      attributes_count;   //额外属性数量
     *          attributes_info     attributes[attributes_count];   //额外属性
     *      }
     *
     */

    /**
     *  属性表集合
     *  u2             attributes_count;//此类的属性表中的属性数
     *  attribute_info attributes[attributes_count];//属性表集合
     */

}
