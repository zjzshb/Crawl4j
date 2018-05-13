package com.hb.thread;

import java.util.LinkedList;

//两个线程向里放，10个线程向外取
public class ProductorACustomer {
    final private LinkedList<Integer> lists = new LinkedList<>();
    final private int MAX = 10;//最多10个
    private int count = 0;

    public synchronized void put(Integer integer){
        while(lists.size()==MAX){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        lists.add(integer);
        count++;
        this.notifyAll();
    }

    public synchronized Integer get(){
        while(lists.size()==0){
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Integer t = lists.removeFirst();
        count--;
        this.notifyAll();

        return t;
    }

    public static void main(String[] args){
        ProductorACustomer p = new ProductorACustomer();
        for(int i=0; i<10; i++){
            new Thread(()->{
                for(int j=0; j<5;j++)
                    System.out.println(p.get());
            },"c"+i).start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i=0; i<2; i++){
            new Thread(()->{
                for(int j=0; j<25; j++){
                    p.put(5+j);
                }
            },"p"+i).start();
        }
    }
}
