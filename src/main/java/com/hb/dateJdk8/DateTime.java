package com.hb.dateJdk8;

import net.sf.ehcache.search.expression.Between;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;

public class DateTime {
    public static void main(String[] args) {
//        oldDate();
//        newDate();
        newDateP();
    }

    private static void newDateP() {
//        java.time.* 包含LocalDate/LocalTime/LocalDateTime,instant()
        timeTest();

//        java.time.format.*  格式化日期

//        java.time.chrono.*  增加如：永历xx的日期系统

//        java.time.temporal.*    对时间进行运算

//        java.time.zone.*    时区方面的类

    }


    private static void timeTest() {
        LocalDateTime ldt = LocalDateTime.now();//获取当前时间

        LocalDateTime localDateTimeI = LocalDateTime.
                of(2018, 1, 2, 20, 21);//创建所需的时间


        //取代calendar对时间进行修改
        LocalDateTime localDateTime1 = ldt.plusYears(2);
        LocalDateTime localDateTime2 = ldt.minusYears(2);


        //获取时间的部分
        System.out.println(ldt.getYear());
        System.out.println(ldt.getMonth());
        System.out.println(ldt.getMonth().getValue());


        /*-------instant时间戳：给电脑使用---------------------------*/
        Instant instant = Instant.now();//Unix元年1970年01.01 00:00:00所经历的毫秒值
        System.out.println(instant);//为现在的本初子午线时间

        //2018-02-28T08:27:21.940Z增加8小时为    东八区时间
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);


        /*-------对时间计算---------------------------*/
        //Durarion计算时间间隔
        //Period计算日期间隔

        Instant instant1 = Instant.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant instant2 = Instant.now();
        Duration between = Duration.between(instant1, instant2);
        System.out.println(between);
        System.out.println(between.getSeconds());
        System.out.println(between.toMinutes());

    }

    //解决线程安全
    private static void newDate() {

        //当前日期
        LocalDate localDate = LocalDate.now();

        //转换格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String format = formatter.format(localDate);
        TemporalAccessor parse = formatter.parse("2018年02月02日");

        System.out.println("日期转为字符：" + format);
        System.out.println("字符转为日期：" + parse);
    }


    /*-------旧日期的缺点：1.不在同一包
                         2.SimpleDateFormat线程不安全---------------------------*/
    private static void oldDate() {
        /*-------date---------------------------*/
        Date date = new Date();


        /*-------date---------------------------*/

        /*-------calender---------------------------*/

        Calendar calender = Calendar.getInstance();
        //对日期进行计算
//        增加/减少 2个月
        calender.add(Calendar.MONTH, 2);
        calender.add(Calendar.MONTH, -2);

        /*-------calender---------------------------*/



        /*-------dateFormat---------------------------*/

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

        //无线程版
        for (int i = 0; i < 10; i++) {
            try {
                Date parse = df.parse("2018-01-01");
                System.out.println(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        //有线程版
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (df) {//必须加上锁才能运行，线程安全
                        try {
                            Date parse = df.parse("2018-01-01");
                            System.out.println(parse);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }

        //lambda
        Runnable runnable = () -> {
            try {
                Date parse = df.parse("2018-01-01");
                System.out.println(parse);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        };
        new Thread(runnable).start();
    }
}
