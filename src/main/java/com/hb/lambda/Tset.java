package com.hb.lambda;


import org.apache.poi.ss.formula.functions.T;

import java.util.*;
import java.util.function.*;


public class Tset {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        test5();
    }

    private static void test5() {
        //方法引用
        //对象::实例方法
        Consumer<String> consumer = System.out::println;
        //类::静态方法
        //类::实例方法


        //构造器引用
        //调用无参构造
        Supplier<Person> supplier = Person::new;
        //调用需要int数据的构造器
        Function<Integer,Person> function = Person::new;
        //调用多个数据的构造器(TestInterface3中有对应的方法
        TestInterface3 ts = (name,age,score) -> new Person(name,age,score);
        TestInterface3 ts2 = Person::new;


        //数组引用
        Function<Integer,Person[]> function1 = (x)->new Person[x];
        Function<Integer,Person[]> function2 = Person[]::new;
    }

    //常用的内置的函数式接口
    //先定义再使用
    private static void test4() {
        //判断某person年龄是否大于18，返回bool
        Predicate<Person> predicate = person -> person.age >= 18;
        predicate.test(new Person(18));

        //接收一个参数并产生一个结果
        Function<String, Integer> toInteger = (s) -> Integer.parseInt(s);
        Function<String, Integer> toInteger2 = Integer::parseInt;
        toInteger.apply("555");

        //生成一个给定类型结果,不接收
        Supplier<Person> personNew = () -> new Person(66);
        personNew.get();

        //执行带有单个输入参数的操作
        Consumer<Integer> consumer = (i) -> i++;
        consumer.accept(1);

        //输入输出为同种类型
        UnaryOperator<String> unaryOperator = s -> s.toLowerCase();
        unaryOperator.apply("SD");


    }

    private static void test3() {
        TestInterface2<Person> t = new TestInterface2<Person>() {

            @Override
            public int method(Person t1, Person t2) {
                return Integer.compare(t1.age, t2.age);
            }
        };

        t = (t1, t2) -> Integer.compare(t1.age, t2.age);


        //内置方法排序
        ArrayList<Person> l = new ArrayList<Person>();
        Person p = new Person();
        p.age = 1;
        l.add(new Person());
        p.age = 2;
        l.add(new Person());

        Collections.sort(l, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Integer.compare(o1.age, o2.age);
            }
        });

        Comparator<Person> comparator = Comparator.comparingInt(o -> o.age);
    }

    //各种形式
    private static void test2() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println("这是匿名内部类");
            }
        };

        //1.无参，无返回值
        runnable = () -> {
            System.out.println("匿名内部类");
        };

        //2.有参，无返回值
//        TestInterface testInterface = new TestInterface() {
//            @Override
//            public void method(String str) {
//                System.out.println("有参无返");
//            }
//        };
//        testInterface = (String str) -> {
//            System.out.println("有参无返");
//        };

        //3.省略
//        testInterface = str -> System.out.println("有参无返");

        //4.有多个参，并有返回值
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                System.out.println("多参有返");
                return Integer.compare(o1.length(), o2.length());
            }
        };
        comparator = (String o1, String o2) -> {
            System.out.println("多参有返");
            return Integer.compare(o1.length(), o2.length());
        };

        //类型可省略，jvm通过上下文获取
    }

    private static void test1() {
        Runnable runnable = new Runnable() {
            public void run() {
                System.out.println("这是个匿名内部类");
            }
        };


        Runnable runnable2 = () -> {
            System.out.println("这是个匿名内部类");
        };

        //实现比较器，存入的数据按长度排序
        TreeSet<String> treeSet = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(), o2.length());
            }
        });

        treeSet.add("dsd");
        treeSet.add("555d");
        treeSet.add("4");
        System.out.println(treeSet);

        //(参数)->方法体
        Comparator<String> comparator = (str1, str2) -> Integer.compare(str1.length(), str2.length());
        TreeSet<String> treeSet2 = new TreeSet<>(comparator);
        treeSet2.add("dsd");
        treeSet2.add("555d");
        treeSet2.add("4");
        System.out.println(treeSet2);
    }
}

interface TestInterface {
    void method(T t);
}


//函数型接口：只有一个方法的接口
// （@FunctionalInterface可写可不写，但可以有object中继承方法
@FunctionalInterface
interface TestInterface2<T> {
    int method(T t1, T t2);
}
@FunctionalInterface
interface TestInterface3<T> {
    Person myMethod(String name, int age, int score);
}