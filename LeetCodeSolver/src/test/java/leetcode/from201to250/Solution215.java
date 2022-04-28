package leetcode.from201to250;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: zhuxi
 * @Time: 2022/4/18 9:59
 * @Description: 215. 数组中的第K个最大元素 | 难度：中等 | 标签：数组、分治、快速选择、排序、堆（优先队列）
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * <p>
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * <p>
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * <p>
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * <p>
 * 提示：
 * 1 <= k <= nums.length <= 104
 * -104 <= nums[i] <= 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/kth-largest-element-in-an-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution215 {
    @Test
    public void findKthLargestTest() {
        Assert.assertEquals(5, findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        Assert.assertEquals(4, findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }

    @Test
    public void swapTest() {
        int a = 1;
        int b = 2;
        // 最原始的异或 swap 写法
        a ^= b;
        b ^= a;
        a ^= b;
        Assert.assertEquals(a, 2);
        Assert.assertEquals(b, 1);
        a = 1;
        b = 2;
        // 略微简写
        b ^= a ^= b;
        a ^= b;
        Assert.assertEquals(a, 2);
        Assert.assertEquals(b, 1);
        a = 1;
        b = 2;
        // 失败的写法
        a ^= b ^= a ^= b;
        Assert.assertEquals(a, 0);
        Assert.assertEquals(b, 1);
        a = 1;
        b = 2;
        // 失败写法的等效 class 反编译
        b = b ^ a ^ b;
        a = a ^ b;
        Assert.assertEquals(a, 0);
        Assert.assertEquals(b, 1);
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.59% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 42.17% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        for (int i = (k - 2) / 2; i >= 0; i--) {
            heapify(nums, i, k);
        }
        for (int i = k; i < nums.length; ++i) {
            // 看 k 大小的堆外的数组元素是否有大于小顶堆堆顶的，有的话替换掉堆顶，然后重新维护最小堆的状态
            if (nums[0] >= nums[i])
                continue;
            // 异或 swap
            // 小心，两数相等时不能这样交换，会变成 0。这里使用前已经判断了，所以是没问题的。
            // 这里绝对不可以将下面写法简写为一行，例如 a ^= b ^= a ^= b 的格式。
            // 这样的话，如果我们用 IDEA 查看，会发现第二个 a 是灰色的（表示其实没有用到）
            // 再看 IDEA 中的 class 反编译文件，可以发现 java 实际的实现方式等同于 a = a ^ (b = b ^ a ^ b)。
            // 意味着第一次赋值操作 a ^= b 并没有赋值给 a（正因为此，第二个 a 是灰色的）, 导致结果就是相当于 b = a, a = 0
            // 详情可以参考：https://blog.csdn.net/baisedeqingting/article/details/89178198
            nums[i] ^= nums[0] ^= nums[i];
            nums[0] ^= nums[i];
            heapify(nums, 0, k);
        }
        return nums[0];
    }

    private int heapify(int[] nums, int i, int k) {
        // i 的左子节点索引为 i * 2 + 1，右子节点索引为 i * 2 + 2
        int left = i * 2 + 1;
        // 循环去重建子树，保持最小堆的状态
        while (left < k) {
            // min 为较小的子节点索引
            int min = left + 1 < k && nums[left] > nums[left + 1] ? left + 1 : left;
            if (nums[i] <= nums[min]) {
                break;
            }
            // 异或方式 swap
            nums[min] ^= nums[i] ^= nums[min];
            nums[i] ^= nums[min];
            i = min;
            left = i * 2 + 1;
        }
        return i;
    }

    /**
     * 基础快排示例
     *
     * @param arr
     * @param left
     * @param right
     * @return
     */
    public static int[] quickSort(int arr[], int left, int right) {
        // 主元
        int pivot = arr[left];
        // 左指针
        int l = left;
        // 右指针
        int r = right;
        while (l < r) {
            // 在右边找到一个比中值小或者相等的值
            while (l < r && arr[r] > pivot) {
                r--;
            }
            // 在左边找到一个比中值大或者相等的值
            while (l < r && arr[l] < pivot) {
                l++;
            }
            if (arr[l] == arr[r] && l < r) {
                // 在 l 和 r 没有相遇时，如果 arr[l] == arr[r]，说明它们也都等于 pivot，没必要交换
                l++;
            } else {
                // 交换
                int temp = arr[l];
                arr[l] = arr[r];
                arr[r] = temp;
            }
        }
        // 左子数组继续快排
        if (l - 1 > left) {
            arr = quickSort(arr, left, l - 1);
        }
        // 右子数组继续快排
        if (r + 1 < right) {
            arr = quickSort(arr, r + 1, right);
        }
        return arr;
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 98.59% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 6.95% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest_quickSort(int[] nums, int k) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            // 如果左边界就是第 k 个数字，那么直接找区间的最大值即可
            if (left == k - 1) {
                int max = nums[left];
                for (int i = left + 1; i < right; i++) {
                    if (nums[i] > max) {
                        max = nums[i];
                    }
                }
                return max;
            }
            // 如果右边界就是第 k 个数字，那么直接找区间内最小值即可
            if (right == k) {
                int min = nums[left];
                for (int i = left + 1; i < right; i++) {
                    if (nums[i] < min) {
                        min = nums[i];
                    }
                }
                return min;
            }
            // 否则既然左右边界都不是第 k 个数字，那么第 k 位就在 left 和 right 中间，在中间尝试使用类似快速排序的思想进行寻找。
            // 随机取 pivot
            int pivot = left + (int) (Math.random() * (right - left));
            // 异或 swap，交换 pivot 和 left
            if (nums[pivot] != nums[left]) {
                nums[pivot] ^= nums[left] ^= nums[pivot];
                nums[left] ^= nums[pivot];
            }
            int l = left + 1;
            int r = right;
            int countEqual = 1;
            while (l < r) {
                if (nums[l] >= nums[left]) {
                    if (nums[l] == nums[left]) {
                        // 相等值都移到区间最左边
                        if (nums[l] != nums[left + countEqual]) {
                            nums[l] ^= nums[left + countEqual] ^= nums[l];
                            nums[left + countEqual] ^= nums[l];
                        }
                        countEqual++;
                    }
                    l++;
                } else {
                    if (nums[l] != nums[r - 1]) {
                        nums[r - 1] ^= nums[l] ^= nums[r - 1];
                        nums[l] ^= nums[r - 1];
                    }
                    r--;
                }
            }
            if (k > l) {
                // k 落在了比 pivot 小的区域（右侧）
                left = l;
                continue;
            }
            if (k > l - countEqual) {
                // k 落在了和 pivot 大小一致的区域
                return nums[left];
            }
            // k 落在了比 pivot 大的区域（左侧）
            // 这时因为我们相等值都放在了最左边，所以为了免除移动这部分相等值的耗时，我们直接修改 k 来使下个循环在比 pivot 大的区域中寻找。
            k += countEqual;
            left += countEqual;
            right = r;
        }
        return nums[left];
    }

    /**
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 46.17% 的用户
     * 内存消耗： 41.9 MB , 在所有 Java 提交中击败了 5.05% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest_maxHeap(int[] nums, int k) {
        // 大顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(k, Comparator.reverseOrder());
        for (int num : nums) {
            heap.offer(num);
        }
        for (int i = 1; i < k; i++) {
            heap.poll();
        }
        return heap.poll();
    }

    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 53.61% 的用户
     * 内存消耗： 41.4 MB , 在所有 Java 提交中击败了 65.01% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest_minHeap(int[] nums, int k) {
        // 小顶堆
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int num : nums) {
            if (heap.size() < k || num > heap.peek()) {
                if (heap.size() == k) {
                    heap.poll();
                }
                heap.offer(num);
            }
        }
        return heap.poll();
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 81.66% 的用户
     * 内存消耗： 41.7 MB , 在所有 Java 提交中击败了 16.30% 的用户
     * 通过测试用例： 32 / 32
     *
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest_arraysSort(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }
}
