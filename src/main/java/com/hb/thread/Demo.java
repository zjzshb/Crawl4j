package com.hb.thread;

import org.junit.Test;

public class Demo {


    /*
    wait    让线程进入等待释放cpu并释放锁
    sleep   让线程进入等待释放cpu

    yield   释放资源，从运行-->可运行

    notify
    notifyall

    interrupt() 中断，标记为中断状态，（
            Thread.interrupt()
            Thread.interrupted()
            Thread.currentThread().interrupt()）

    setDaemon(true) 设置为守护线程（主线程退出时，自动停


    */


    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() ->
                System.out.println("t1")
        );
        Thread t2 = new Thread(() ->
                System.out.println("t2")
        );
        Thread t3 = new Thread(() ->
                System.out.println("t3")
        );


        t1.start();
        t1.join();//当前线程被阻塞，只有t1运行完后，其他线程才能运行（才能抢cpu）

        t2.start();
        t3.start();

    }


    //    在多线程的情况下循环
//    按顺序打印字符串    a,b,c
    @Test
    public void printABC() {
        Thread a = new Thread(() -> System.out.println("a"));
        Thread b = new Thread(() -> System.out.println("b"));
        Thread c = new Thread(() -> System.out.println("c"));

        a.start();


        c.start();
    }

    @Test
    public void synchronizedTest() {
        int a = 0;
        synchronized (this) {
            a++;
        }
    }


}


//实现同步
// 1.synchronized解决同一数据修改问题

// 2.只需改为    private volatile int account = 100;    并删除锁

// 3. ReentrantLock() : 创建一个ReentrantLock实例
//          lock() : 获得锁
//        unlock() : 释放锁
/*
只需改为
private Lock lock = new ReentrantLock();

//这里不再需要synchronized
    public void save(int money) {
        lock.lock();
        try{
            account += money;
        }finally{
            lock.unlock();
        }

   }    并删除锁
        */
// 4.
class SynchronizedThread {

    class Bank {

        private int account = 100;

        public int getAccount() {
            return account;
        }

        /**
         * 用同步方法实现
         *
         * @param money
         */
        public synchronized void save(int money) {
            account += money;
        }

        /**
         * 用同步代码块实现
         *
         * @param money
         */
        public void save1(int money) {
            synchronized (this) {
                account += money;
            }
        }
    }

    class NewThread implements Runnable {
        private Bank bank;

        public NewThread(Bank bank) {
            this.bank = bank;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                // bank.save1(10);
                bank.save(10);
                System.out.println(i + "账户余额为：" + bank.getAccount());
            }
        }

    }

    /**
     * 建立线程，调用内部类
     */
    public void useThread() {
        Bank bank = new Bank();
        NewThread new_thread = new NewThread(bank);
        System.out.println("线程1");
        Thread thread1 = new Thread(new_thread);
        thread1.start();
        System.out.println("线程2");
        Thread thread2 = new Thread(new_thread);
        thread2.start();
    }

    public static void main(String[] args) {
        SynchronizedThread st = new SynchronizedThread();
        st.useThread();
    }
}