package com.hb.sort;

import java.util.ArrayList;
import java.util.List;

class SortUtils {

    //---冒泡--------------------------------------------------------------------

    static int[] bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    exchangeVal(arr, i, j);
                }
            }
        }

        return arr;
    }
    //----插入-------------------------------------------------------------------

    public static int[] insertSort(int[] arr) {

//        for (int i = 1; i < arr.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if (arr[i] < arr[j]) {
//                    int t = arr[i];
//                    for (int k = i; k > j; k--) {
//                        arr[k] = arr[k - 1];
//                        arr[j] = t;
//                    }
//                    break;
//                }
//            }
//        }


        for (int j = 1; j < arr.length; j++) {
            for (int i = j; i > 0 && arr[i - 1] > arr[i]; i--) {
                exchangeVal(arr, i, i - 1);
            }
        }


        return arr;
    }
    //-----快排------------------------------------------------------------------

    public static void quickSort(List<Integer> list) {
        List<Integer> smaller = new ArrayList<>();
        List<Integer> seem = new ArrayList<>();
        List<Integer> bigger = new ArrayList<>();
        if (list.size() > 1) {
            Integer midd = list.get(list.size() / 2);
            for (int i = 0; i < list.size(); i++) {
                Integer li = list.get(i);
                if (li < midd) {
                    smaller.add(li);
                } else if (li == midd) {
                    seem.add(li);
                } else if (li > midd) {
                    bigger.add(li);
                }
            }

            quickSort(smaller);
            quickSort(bigger);

            list.clear();
            list.addAll(smaller);
            list.addAll(seem);
            list.addAll(bigger);
        }
    }

    public static int partition(int[] array, int lo, int hi) {
        //固定的切分方式
        int key = array[lo];
        while (lo < hi) {
            while (array[hi] >= key && hi > lo) {//从后半部分向前扫描
                hi--;
            }
            array[lo] = array[hi];
            while (array[lo] <= key && hi > lo) {//从前半部分向后扫描
                lo++;
            }
            array[hi] = array[lo];
        }
        array[hi] = key;
        return hi;
    }

    public static void quickSort(int[] array, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int index = partition(array, lo, hi);
        quickSort(array, lo, index - 1);
        quickSort(array, index + 1, hi);
    }

    //--希尔---------------------------------------------------------------------
    public static int[] shellSort(int[] arr) {
        for (int k = (arr.length) / 2; k > 0; k = k / 2) {
            for (int i = k; i < arr.length; i++) {
                ifEx(arr, k, i);
            }
        }
        return arr;
    }

    private static void ifEx(int[] arr, int k, int i) {
        if (i - k >= 0 && arr[i - k] > arr[i]) {
            exchangeVal(arr, i, i - k);
            ifEx(arr, k, i - k);
        } else {
            return;
        }
    }
    //--堆---------------------------------------------------------------------

    public static void HeapSort(List<Integer> list) {
        for (int j = list.size() - 1; j > 0; j--) {
            for (int i = j; i >= 1; i = i - 2) {
                if (i % 2 == 0) {
                    if (list.get(i) > list.get(i / 2 - 1)) {
                        exchangeVal(list, i, i / 2 - 1);
                    }
                    if (list.get(i - 1) > list.get(i / 2 - 1)) {
                        exchangeVal(list, i - 1, i / 2 - 1);
                    }
                } else {
                    if (list.get(i) > list.get((i - 1) / 2)) {
                        exchangeVal(list, i, (i - 1) / 2);
                    }
                    if(i<j&&list.get(i+1) > list.get((i - 1) / 2)){
                        exchangeVal(list, i+1, (i - 1) / 2);
                    }
                }
            }
            exchangeVal(list, 0, j);
        }
    }

    //--工具---------------------------------------------------------------------

    private static void exchangeVal(List<Integer> list, int i, int j) {
        Integer t = list.get(i);
        list.set(i, list.get(j));
        list.set(j, t);
    }

    private static void exchangeVal(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }


}
