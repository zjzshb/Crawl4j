package com.hb.thread;

import org.junit.Test;

public class SychronizedTest {
    @Test
    public void test1() {
        Saler s1 = new Saler("s1");
        Saler s2 = new Saler("s2");
        Saler s3 = new Saler("s3");
        s1.start();
        s2.start();
        s3.start();
    }
    @Test
    public void test2() {
        Saler s = new Saler("11");
        Thread s1 = new Thread(s);
        Thread s2 = new Thread(s);
        Thread s3 = new Thread(s);
        s1.start();
        s2.start();
        s3.start();
    }
    @Test
    public void test3() {


    }
    public static void main(String[] args){
//        Saler s = new Saler("11");
//        Thread s1 = new Thread(s);
//        Thread s2 = new Thread(s);
//        Thread s3 = new Thread(s);
//        s1.start();
//        s2.start();
//        s3.start();
    }
    @Test
    public void doubleSynchronized(){
//        sychronized void m1(
    }
}

class Saler extends Thread {
    private String name;
    public static volatile int ticket = 1000;

    public Saler(String name) {
        this.name = name;
    }

    public void run() {
        while (true) {
            int tick = getTicket();
            if (tick > 0) {
                System.out.println(name +"  "+ Thread.currentThread().getName()+" 卖出第 " + tick + " 张票");
            } else {
                System.out.println("卖完了");
                break;
            }
        }
    }

    public int getTicket() {
//        synchronized (this) {
            int current = ticket;
            ticket--;
            return current;
//        }
    }


}
