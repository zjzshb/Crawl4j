package com.hb.lambda;


import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.ss.formula.functions.Value;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamApi {
    public static void main(String[] args) {
//        test1();
//        test2();
//        test3();
//        test4();
        try {
            test6_wordcount();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //读取txt中词语出现次数
    public static void test6_wordcount() throws IOException {
        InputStream resourceAsStream = StreamApi.class.getClassLoader().getResourceAsStream("./wc.txt");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));

        //读取成每行，每行用空格切分，再转为stream，排序，通过String.toString分组
        Map<String, List<String>> map = bufferedReader.lines().flatMap(line -> Arrays.stream(line.split(" ")))
                .sorted().collect(Collectors.groupingBy(x -> x));
        map.forEach((key, Value)->System.out.println(key+"->"+Value));

        bufferedReader.close();
    }

    private static void test4() {
//        1. 使用IntStream移除常规的循环
        IntStream.range(0,9).forEach(value->System.out.println(value));

//        2. 计算列表中的元素数
        //2.1.先转为列表
        List<Integer> list = IntStream.range(0, 10).boxed().collect(Collectors.toList());
        //2.2.计算元素数
        System.out.println(list.stream().count());

//        3. 计算列表中元素的平均数
        list.stream().collect(Collectors.averagingInt(item->item));

//        4. 对列表元素进行统计,平均、个数、最大、最小、和
        list.stream().collect(Collectors.summarizingInt(v->v));

//        5. 根据List创建Map 键/值=1/3
        list.stream().collect(Collectors.toMap(p->p,q->q*3));

//        6. 求列表元素的最大数
        list.stream().reduce(Math::max);

//        7. 从一堆姓名列表中找出以字母“C”开头的姓名


    }

    private static void test3() {
        Integer[] art = {1,2,3,4,5,6,7,8,9,10};
        //1.获取最大值
        Integer integer = Arrays.stream(art).collect(Collectors.maxBy((i1, i2) -> Integer.compare(i1, i2))).get();
    }


    //中间操作
    public static void test2() {
        Integer[] ary = {1, 2, 3, 4, 4, 5, 5, 6, 7, 8, 9};

        //取出3-5的元素
        Stream.of(ary).skip(2).limit(3).forEach(System.out::println);

        //取出偶数，去重复
        Arrays.stream(ary).filter(it -> it % 2 == 0).distinct().forEach(System.out::println);

        //二维数组，合成一维数组，并排序
        Integer[][] ary2 = {{1, 5, 6, 7, 3, 4, 4}, {5, 8, 2, 9}};
        Arrays.stream(ary2).flatMap(item -> Arrays.stream(item)).sorted().forEach(System.out::println);
        System.out.println("---------");

        //0.3最终操作

        Integer integer = Arrays.stream(ary2).flatMap(item -> Arrays.stream(item)).sorted().findFirst().get();
        System.out.println(integer);

        List<Person> persons = Arrays.asList(
                new Person("hh", 11, 53),
                new Person("aa", 15, 52),
                new Person("bb", 20, 51)
        );

        //0.3归约
        //将年龄汇总,前一个和后一个相加，从0开始
        persons.stream().map(Person::getAge).reduce(0,(i1,i2)->i1+i2);
    }

    public static void test1() {
        List<Person> persons = Arrays.asList(
                new Person("hh", 11, 53),
                new Person("aa", 15, 52),
                new Person("bb", 20, 51)
        );

        //取出age>12人的姓名， filter((p)->p.getAge()>12).map(Person::getName)
        // 按字典排序，        sorted()
        // 并输出到控制台      forEach(System.out::println)
        persons.stream().filter((p) -> p.getAge() > 12).map(Person::getName).sorted().forEach(System.out::println);
        //0.1.创建流       0.2.操作流                                                 0.3.汇总流
        //1.操作为延迟执行，如无foreach将不执行


        //0.1.创建流
        //0.1.方法一，Collection.stream（）/paralStream
        List<String> list = new ArrayList<>();
        list.stream();

        //0.1.方法二，Arrays.stream（）
        Stream<String> stream = Arrays.stream(new String[]{"aa", "bb"});

        //0.1.方法3，Stream.of
        Stream<String> stream2 = Stream.of("aa", "bb");


    }

}
