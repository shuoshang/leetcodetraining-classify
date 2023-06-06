package com.ChangWork.ArrayInplace;
/*
跳槽换工作的时候leetcode题目刷了共170道，但是当时很执拗于多刷一道是一道，没有进行分类，对于题目也没有系统的分析
现在打算把这些题目再学习一次，根据自己的理解分个类，希望这次就不仅停留于形象地记忆，而是有较为清晰地解题思路
*/
import java.util.Arrays;

/*TopK 问题-最大的或最小的K个数

最大的 K 个数

快速选择的方法，类似快速排序的思路：
1、对数组进行原地分割。
   随机选择一个基准元素，分割后使得：大于它的元素都在它左边，小于它的元素都在它右边。
2、根据分割后左边的元素个数和k的大小关系，选择一边递归进行分割。
*/

public class TopKNumber {


    //TopK 会【原地】把 k 大数放到 a 的前 k 个
    void TopK(int a[], int n, int k) {
        //快排
        QuickSelect(a, 0, n - 1, k);
        System.out.println(Arrays.toString(a));
    }

    //递归-快排
    void QuickSelect(int a[], int start, int end, int k) {

        //异常用例
        if (start >= end || k <= 0) return;

        int p = Partition(a, start, end);

        // 整个数组中在基准元素左边的元素个数
        int m = p + 1;
/*      入参的K和找到的基准元素个数比较，分成两部分快排
        因为此时，注意，左边的m个数只保证不大于基准元素，并非排序
        此时，需要对左边的m个数递归分割*/
        if (k < m)
            QuickSelect(a, start, p - 1, k);
        else if (k > m)
            // 注意传 k 而非 k - m ，对齐 m 的意义
            QuickSelect(a, p + 1, end, k);
        //k=m的时候已经找到最大的k个数，终止递归
        else
            return;
    }

     /*数组原地分割，取 v = a[start]
       >v 在左，=v 在中，<v在右
       返回基准元素在整个数组中的【位置】
       和快排完全一样              */
    int Partition(int a[], int start, int end) {
        int v = a[start];
        int left = start;
        int right = end;
        int i = start;
        while (i <= right) {
            if (a[i] > v) {
                swap(a, i, left);
                left++;
                i++;
            } else if (a[i] < v) {
                swap(a, i, right);
                right--;
            } else {
                i++;
            }
        }
        return i - 1;
    }
    //交换两个元素，单独的方法
    public void swap(int[] nums, int i, int index) {
        int k = nums[i];
        nums[i] = nums[index];
        nums[index] = k;
    }

    public static void main(String[] args) {

        // write your code here
        TopKNumber topKNumber =  new TopKNumber();
        int[] nums1 = new int[]{5,3,1,9,7,2,5,8,4};
        topKNumber.TopK(nums1,nums1.length,4);
    }
}
