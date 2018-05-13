package com.hb.sort;

import org.apache.pdfbox.util.QuickSort;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestSort {

    @Test
    public void testSort() {
        int c = 10;

        Random r = new Random();
        int[] arr = new int[c];
        List<Integer> list = new ArrayList<>();

        StringBuilder str = new StringBuilder();
        for (int i = 0; i < c; i++) {
            arr[i] = r.nextInt(101);
            str.append(arr[i]).append(" ");
            list.add(arr[i]);
        }
        System.out.println(str);

        //1.冒泡排序
//        int[] arrN10 = SortUtils.bubbleSort(arr);
//
//        StringBuilder str10 = new StringBuilder();
//
//        for (int s : arrN10) {
//            str10.append(s).append(" ");
//        }
//        System.out.println(str10);


        //2.插入
//        int[] arrN = SortUtils.insertSort(arr);

        //3.快排
//        SortUtils.quickSort(arr,0,arr.length-1);

//        SortUtils.quickSort(list);
//        Object[] arrN = list.toArray();

        //4.希尔
//        int[] arrN = SortUtils.shellSort(arr);

        //5.堆
        SortUtils.HeapSort(list);
        Object[] arrN = list.toArray();


        StringBuilder str2 = new StringBuilder();

        for (Object s : arrN) {
            str2.append(s).append(" ");
        }
        System.out.println(str2);
    }
}
