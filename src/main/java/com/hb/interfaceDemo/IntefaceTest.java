package com.hb.interfaceDemo;

//可以写实例方法的接口
//支持default，static
@FunctionalInterface
public interface IntefaceTest {
    public void printMethod();

    default void someMethod(){
        System.out.println("someMethod");
    }

    static void staticMethod(){
        System.out.println("staticMethod");
    }

}
