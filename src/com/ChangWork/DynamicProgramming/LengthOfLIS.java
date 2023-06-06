package com.ChangWork.DynamicProgramming;
/*
跳槽换工作的时候leetcode题目刷了共170道，但是当时很执拗于多刷一道是一道，没有进行分类，对于题目也没有系统的分析
现在打算把这些题目再学习一次，根据自己的理解分个类，希望这次就不仅停留于形象地记忆，而是有较为清晰地解题思路
*/
import java.util.Arrays;
/*
乡亲们这两期视频整理了几个我自己比较混淆的几个问题，希望以后能够清晰地辨别出这几个题目的类型和解决问题的方法
*/
//最长递增子序列：不要求连续！
//方法一：动态规划算法-求最长严格递增子序列的长度
//方法二：贪心+二分查找-求最长严格递增子序列的长度以及打印满足条件的子序列

public class LengthOfLIS {

        public int lengthOfLIS1(int[] nums) {
            int n = nums.length;
            if (n == 0) return 0;  // 如果数组为空，递增子序列长度为0

            // dp[i] 代表以位置 i 结尾的最长递增子序列的长度
            int[] dp = new int[nums.length];
            dp[0] = 1;  // 以索引0结尾的递增子序列长度始终为1（只包含该元素本身）

            // 记录 dp 中的最大值
            int maxdp = dp[0];

            // 遍历数组，从索引1开始
            for (int i = 1; i < n; i++) {
                dp[i] = 1;  // 至少为1（每个元素本身就是长度为1的有效子序列）

                // 遍历索引0到i-1的元素
                for (int j = 0; j < i; j++) {
                    if (nums[i] > nums[j]) {
                        // 如果索引i处的元素大于索引j处的元素，
                        // 可以通过将索引i处的元素添加到索引j处的递增子序列中来扩展以索引j结尾的递增子序列。
                        // 更新 dp[i] 为当前值和 dp[j] + 1 的较大值。
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                    }
                }

                // 更新 maxdp 为当前值和 dp[i] 的较大值
                maxdp = Math.max(dp[i], maxdp);
            }

            // 返回 maxdp 中存储的最大递增子序列长度
            return maxdp;
        }


    public int lengthOfLIS2(int[] nums) {
        int len = 1, n = nums.length;
        if (n == 0) {
            return 0;
        }
        int[] d = new int[n + 1];
        d[len] = nums[0];
        for (int i = 1; i < n; i++) {
            if (nums[i] > d[len]) {  // 当前元素大于最长递增子序列的最后一个元素
                d[len+1] = nums[i];  // 将当前元素添加到递增子序列中
                len++;  // 更新最长递增子序列的长度
            } else {
                int l = 1, r = len, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;  // 计算中间位置
                    if (d[mid] < nums[i]) {  // 最长递增子序列和原数组进行比较，如果中间位置的值小于当前元素
                        pos = mid;  // 更新位置 pos
                        l = mid + 1;  // 缩小搜索范围，继续向右搜索
                    } else {
                        r = mid - 1;  // 缩小搜索范围，继续向左搜索
                    }
                }
                d[pos + 1] = nums[i];  // 在找到的位置后插入当前元素，更新【递增子序列】的某个元素
            }
        }
        System.out.println(Arrays.toString(d));  // 输出递增子序列数组，以便查看变化过程
        return len;  // 返回最长递增子序列的长度
    }

    public static void main(String[] args) {
        // write your code here
        LengthOfLIS lengthOfLIS =  new LengthOfLIS();
        int[] maxanst = new int[]{10,9,2,5,3,7,101,18};
        System.out.println(lengthOfLIS.lengthOfLIS1(maxanst));
        System.out.println(lengthOfLIS.lengthOfLIS2(maxanst));
    }
}

