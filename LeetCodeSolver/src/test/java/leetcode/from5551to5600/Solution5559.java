package leetcode.from5551to5600;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Author: ZeromaXHe
 * @Time: 2020/11/28 23:01
 * @Description: 5559. 得到山形数组的最少删除次数
 * 我们定义 arr 是 山形数组 当且仅当它满足：
 * <p>
 * arr.length >= 3
 * 存在某个下标 i （从 0 开始） 满足 0 < i < arr.length - 1 且：
 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
 * arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
 * 给你整数数组 nums​ ，请你返回将 nums 变成 山形状数组 的​ 最少 删除次数。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,1]
 * 输出：0
 * 解释：数组本身就是山形数组，所以我们不需要删除任何元素。
 * 示例 2：
 * <p>
 * 输入：nums = [2,1,1,5,6,2,3,1]
 * 输出：3
 * 解释：一种方法是将下标为 0，1 和 5 的元素删除，剩余元素为 [1,5,6,3,1] ，是山形数组。
 * 示例 3：
 * <p>
 * 输入：nums = [4,3,2,1,1,2,3,1]
 * 输出：4
 * 提示：
 * <p>
 * 输入：nums = [1,2,3,4,4,3,2,1]
 * 输出：1
 * <p>
 * <p>
 * 提示：
 * <p>
 * 3 <= nums.length <= 1000
 * 1 <= nums[i] <= 109
 * 题目保证 nums 删除一些元素后一定能得到山形数组。
 * @Modified By: ZeromaXHe
 */
public class Solution5559 {
    /**
     * 82 / 82 个通过测试用例
     * 状态：通过
     * 执行用时: 100 ms
     * 内存消耗: 38.5 MB
     *
     * @param nums
     * @return
     */
    public int minimumMountainRemovals(int[] nums) {
        int[] preSmallerIndex = new int[nums.length];
        int[] preSmallerCount = new int[nums.length];
//        int[] preBiggerIndex = new int[nums.length];
//        int[] preBiggerCount = new int[nums.length];
        int[] nextSmallerIndex = new int[nums.length];
        int[] nextSmallerCount = new int[nums.length];
//        int[] nextBiggerIndex = new int[nums.length];
//        int[] nextBiggerCount = new int[nums.length];
        Arrays.fill(preSmallerIndex, -1);
//        Arrays.fill(preBiggerIndex, -1);
        Arrays.fill(nextSmallerIndex, -1);
//        Arrays.fill(nextBiggerIndex, -1);
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
//                if (nums[j] > nums[i]) {
//                    if (preBiggerCount[i] == 0 || preBiggerCount[j] + 1 > preBiggerCount[i]) {
//                        preBiggerIndex[i] = j;
//                        preBiggerCount[i] = preBiggerCount[j] + 1;
//                    }
//                } else if (nums[j] < nums[i]) {
                if (nums[j] < nums[i]) {
                    if (preSmallerCount[i] == 0 || preSmallerCount[j] + 1 > preSmallerCount[i]) {
                        preSmallerIndex[i] = j;
                        preSmallerCount[i] = preSmallerCount[j] + 1;
                    }
                }
            }
        }
        for (int i = nums.length - 2; i >= 0; i--) {
            for (int j = nums.length - 1; j > i; j--) {
//                if (nums[j] > nums[i]) {
//                    if (nextBiggerCount[i] == 0 || nextBiggerCount[j] + 1 > nextBiggerCount[i]) {
//                        nextBiggerIndex[i] = j;
//                        nextBiggerCount[i] = nextBiggerCount[j] + 1;
//                    }
//                } else if (nums[j] < nums[i]) {
                if (nums[j] < nums[i]) {
                    if (nextSmallerCount[i] == 0 || nextSmallerCount[j] + 1 > nextSmallerCount[i]) {
                        nextSmallerIndex[i] = j;
                        nextSmallerCount[i] = nextSmallerCount[j] + 1;
                    }
                }
            }
        }
//        System.out.println("nextSmallerCount" + Arrays.toString(nextSmallerCount));
//        System.out.println("nextSmallerIndex" + Arrays.toString(nextSmallerIndex));
//        System.out.println("preSmallerCount" + Arrays.toString(preSmallerCount));
//        System.out.println("preSmallerIndex" + Arrays.toString(preSmallerIndex));
        int max = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            int mountCount = nextSmallerCount[i] + preSmallerCount[i] + 1;
            if (mountCount > max) {
                max = mountCount;
            }
        }
        return nums.length - max;
    }

    // case 1:
    // [9,8,1,7,6,5,4,3,2,1]
    // 预期 2

    public static void main(String[] args) {
        Solution5559 solution5559 = new Solution5559();
        System.out.println(solution5559.minimumMountainRemovals(new int[]{9, 8, 1, 7, 6, 5, 4, 3, 2, 1}));
    }

    /**
     * 放弃的思路
     *
     * @param nums
     * @return
     */
    public int minimumMountainRemovals_unfinishedThinking(int[] nums) {
        int[] sortArr = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortArr);
        // 存放nums里面的数字和小于这个数字的个数
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sortArr.length; i++) {
            if (!map.containsKey(sortArr[i])) {
                map.put(sortArr[i], map.size());
            }
        }
        int left = 1;
        int right = nums.length - 2;
        while (left < nums.length && nums[left - 1] >= nums[left]) {
            left++;
        }
        while (right >= 0 && nums[right + 1] >= nums[right]) {
            right--;
        }
        int min = Integer.MAX_VALUE;
        for (int i = left; i <= right; i++) {

        }
        return 0;
    }
}
