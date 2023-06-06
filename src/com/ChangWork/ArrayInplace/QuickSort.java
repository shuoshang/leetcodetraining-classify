package com.ChangWork.ArrayInplace;
/*
跳槽换工作的时候leetcode题目刷了共170道，但是当时很执拗于多刷一道是一道，没有进行分类，对于题目也没有系统的分析
现在打算把这些题目再学习一次，根据自己的理解分个类，希望这次就不仅停留于形象地记忆，而是有较为清晰地解题思路
*/
import java.util.Arrays;

/*
乡亲们可以先复习一下上个视频找出最大（或最小）的K个数，这个题目就是非常直接的快速排序，倒序或正序
可以看下上个题目和快排的相同点和异同点
*/

public class QuickSort {

    //直接开启递归快排
    void TopK(int a[], int n, int k) {
        QuickSelect(a, 0, n - 1, k);
        System.out.println(Arrays.toString(a));
    }
    //递归
    void QuickSelect(int a[], int start, int end, int k) {

        if (start >= end || k <= 0) return;
        //依然是要先找到基准位置，注意一下返回的位置是i还是i-1，那我们依然返回i-1
        int m = Partition(a, start, end);
        //快排的比较适合基准位置m和start是>的比较，end的位置是m-1
        if (m > start)
            QuickSelect(a, start, m - 1, k);
        //或者基准位置m和end是<的比较，start的位置是m+1（值得注意的是：这两个条件不是if和else if的关系，是并列或的关系！）
        if (m < end)
            QuickSelect(a, m + 1, end, k);
    }

    /*
        数组是原地分割，取v=a[start]
        倒序：>v 在左，=v在中，<v在右  正序：<v 在左，=v在中，>v在右
        返回基准元素在整个数组中的【位置】
    */
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
        return i-1;
    }

    //交换两个元素的单独方法
    public void swap(int[] nums, int i, int index) {
        int k = nums[i];
        nums[i] = nums[index];
        nums[index] = k;
    }

    public static void main(String[] args) {

        // write your code here
        QuickSort topKNumber =  new QuickSort();
        int[] nums1 = new int[]{5,3,1,9,7,2,5,8,4};
        topKNumber.TopK(nums1,nums1.length,4);
    }
}