package com.ChangWork.DynamicProgramming;
/*
跳槽换工作的时候leetcode题目刷了共170道，但是当时很执拗于多刷一道是一道，没有进行分类，对于题目也没有系统的分析
现在打算把这些题目再学习一次，根据自己的理解分个类，希望这次就不仅停留于形象地记忆，而是有较为清晰地解题思路
*/
/*乡亲们这几期视频都是整理了几个我自己比较混淆的几个问题，希望以后能够清晰地辨别出这几个题目的类型和解决问题的方法
 *
 * 本期题目：最大连续子序列和，又名最大和的连续子数组（子数组最少包含一个元素），返回其最大和
 * 注意是【连续】，上一期题目是递增不需要连续的子序列，这一期是连续的子序列【和】
 * 方法一：纯数学方法
 * 方法二：动态规划找最优解
 * 方法三：数学应用题解法，画线段
 * */
public class MaximumSubarray {

    //方法一:纯数学方法
    public int maxSubArray(int[] nums) {
        int tmp = nums[0];
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            // nums[i]可能大于0也可以小于0，当目前的tmp是负的，最大的就会变成 nums[i]
            if (tmp < 0) {
                res = Math.max(res, nums[i]);
                tmp = nums[i];
            }
            // nums[i]可能大于0也可以小于0，当目前的tmp是和正的，最大的就会变成 tmp + nums[i]
            else {
                res = Math.max(res, tmp + nums[i]);
                tmp = tmp + nums[i];
            }
        }
        return res;
    }

    //方法二：动态规划方法
    //nums[i] 是所考虑的连续数组的结尾元素，因此 dp(i) 必然包含 nums[i] 作为成分
    //假设已知 dp(i-1)，考虑其递推到 dp(i)：
    int maxSubArray2(int nums[], int length) {
        if (length <= 0) return 0;

        int max = 0;
        int[] dp = new int[length];
        dp[0] = nums[0];

        for (int i = 1; i < length; i++) {
            //如果 dp(i-1) 是正数，考虑到要求最大和，因此将它吸收，即 dp(i) = dp(i-1) + nums[i]
            if (dp[i - 1] > 0)
                dp[i] = dp[i - 1] + nums[i];
                //否则，不吸收 dp(i-1) ，即 dp(i) = nums[i]
            else
                dp[i] = nums[i];
            //对所有 0..n-1 的 i 求解其 dp(i) ，找出其中最大值即结果
            if (max < dp[i]) max = dp[i];
        }
        return max;
    }

    //方法三：当成应用题：画线段
    //任意一个连续数组 [i..j] 之和可以表达为 sum(j) - sum(i-1)
    //要使右侧区间内数字之和最大， 势必要求左侧前缀和 sum(i-1) 最小
    //也就是，要找出 j 左边的前缀和的最小值，它可以在计算前缀和的过程中记录下来
    //最后，对所有 j 找出其右侧区间的最大和 ，记录其中最大的即结果。
    public int maxSubArray3(int[] nums) {
        int sum = 0;
        // Min value of sum(i), where i < j.
        int min = 0;
        // Max value of delta, where delta = sum(j) - min.
        int max = nums[0];

        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            int delta = sum - min;
            if (delta > max) max = delta;
            if (sum < min) min = sum;
        }
        return max;
    }


    public static void main(String[] args) {

        // write your code here
        MaximumSubarray maxSubArray = new MaximumSubarray();
        int[] tokens = new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray.maxSubArray(tokens));
        System.out.println(maxSubArray.maxSubArray2(tokens, tokens.length));
        System.out.println(maxSubArray.maxSubArray3(tokens));
    }
}
