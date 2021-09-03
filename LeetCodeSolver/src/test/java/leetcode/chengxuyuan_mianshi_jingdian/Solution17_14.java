package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Author: zhuxi
 * @Time: 2021/9/3 13:53
 * @Description: 面试题 17.14. 最小K个数 | 难度：中等 | 标签：数组、分治、快速选择、排序、堆（优先队列）
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 * <p>
 * 示例：
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * <p>
 * 提示：
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/smallest-k-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution17_14 {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 5, 7, 2, 4, 6, 8};
        System.out.println(Arrays.toString(new Solution17_14().smallestK(arr, 4)));
    }

    /**
     * 执行用时： 21 ms , 在所有 Java 提交中击败了 34.90% 的用户
     * 内存消耗： 46.5 MB , 在所有 Java 提交中击败了 85.03% 的用户
     * 通过测试用例： 29 / 29
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int i : arr) {
            heap.offer(i);
        }
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = heap.poll();
        }
        return result;
    }
}
