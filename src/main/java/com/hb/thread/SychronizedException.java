package com.hb.thread;


//出现异常，会释放锁，但可能数据没处理完


//volatile使一个变量在多个线程中可见
//1.volatile保证可见性（修改数值后其他线程之后读会读到
//2.synchronized保证可见性和原子性（同时一个线程修改
//        锁的参数是堆内存，记录在堆对象上
//                String s1 = "hello";String s2 = "hello" 为同一堆内存
//3.AtomicInteger 可以保证单个值的 可见性和原子性
//        AtomicInteger i = new AtomicInteger();
//        i.incrementAndGet();//i++
//4.countdownlatch  门闩

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SychronizedException {
    public static void main(String[] args) {

        T t = new T();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                t.m();
            }
        };
        new Thread(r, "t1").start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {

        }
        new Thread(r, "r2").start();
    }
}

class T {
    int count = 0;

    synchronized void m() {
//        t1执行到count=5时,
//                抛异常，释放锁
//        t2从5开始执行

        System.out.println(Thread.currentThread().getName() + "start");
        while (true) {
            count++;
            System.out.println(Thread.currentThread().getName() + "count = " + count);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (count == 5) {
                int i = 1 / 0;
            }
        }
    }

    //    5.用这个替代synchronize=======================================
    Lock lock = new ReentrantLock();//该处参数为true时为公平锁

    void m1() {
        lock.lock();
        try {
//            lock.lockInterruptibly();//设置这个锁可以被打断（拿不着锁时，其他线程打断该线程（thread1.interrupt），让她放弃锁）
            System.out.println("一顿操作");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();//需要手动释放
        }
    }

    //尝试拿锁，拿到返回true，记得拿到后要释放
    void m2() {
        boolean isNotLock = lock.tryLock();//可设置超时时间
        if (isNotLock) {//拿到锁是一种操作
            System.out.println("一顿操作");
            lock.unlock();
        }else{
            System.out.println("又一顿操作");
        }
    }
}
