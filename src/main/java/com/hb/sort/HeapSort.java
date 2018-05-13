package com.hb.sort;

import org.junit.Test;

import javax.measure.unit.SystemOfUnits;

public class HeapSort {

    public void createHeap(int[] arr) {
        int n = arr.length;
        for (int i = n / 2; i >= 0; i--) {
            changeHeap(arr,i);
        }



    }

    public void changeHeap(int[] arr, int s) {
        int n = arr.length;
        int max = s;

        if (s * 2 + 1 < n && arr[s * 2 + 1] > arr[max]) {
            max = s * 2 + 1;
        }
        if (s * 2 + 2 < n && arr[s * 2 + 2] > arr[max]) {
            max = s * 2 + 2;
        }
        if (max != s) {
            exchange(arr,s, max);
        }

    }

    public void exchange(int[] arr, int s, int i) {
        int step = arr[s];
        arr[s] = arr[i];
        arr[i] = step;
    }
    public void heapSort(int[] arr){


    }
    public static void main(String[] args){
        int[] arr=new int[]{1,2,3,4,5,6,7,8,9,10};

        new HeapSort().heapSort(arr);
        for(int i=0;i<arr.length;i++){
            System.out.println(arr[i]);
        }
    }
    
    @Test
    public void test(){
        int n = 9;
        for(int i=0; i<n; i++){
            int nn = n/2-i;
            if(nn<0){
                nn = -nn;
            }
            for(int j=0; j<nn; j++){
                System.out.print(' ');
            }
            if(nn==n/2){
                System.out.print('*');
            }else{
                System.out.print('*');
                for(int k=0;k<n-nn*2-2;k++){
                    System.out.print(' ');
                }
                System.out.print('*');
            }

            for(int j=0; j<nn;j++){
                System.out.print(' ');
            }
            System.out.println();
        }
    }

}
