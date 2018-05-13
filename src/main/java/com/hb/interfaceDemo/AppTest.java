package com.hb.interfaceDemo;

public class AppTest {
    public static void main(String[] args){
        IntefaceTest it = new InterfaceTestImpl();
        it.printMethod();
        it.someMethod();//直接调用接口中的默认方法
        IntefaceTest.staticMethod();//调用static方法
    }
    /*
    一个类实现的两个接口中有同名默认方法   要在类中重写
    一个类的子类与实现接口中有同名默认方法   实际调用子类方法
    * */
}
