package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.Arrays;

/**
 * @Author: zhuxi
 * @Time: 2022/5/13 17:27
 * @Description: 面试题 10.03. 搜索旋转数组 | 难度：中等 | 标签：数组、二分查找
 * 搜索旋转数组。给定一个排序后的数组，包含n个整数，但这个数组已被旋转过很多次了，次数不详。
 * 请编写代码找出数组中的某个元素，假设数组元素原先是按升序排列的。若有多个相同元素，返回索引值最小的一个。
 * <p>
 * 示例1:
 * 输入: arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 5
 * 输出: 8（元素5在该数组中的索引）
 * <p>
 * 示例2:
 * 输入：arr = [15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14], target = 11
 * 输出：-1 （没有找到）
 * <p>
 * 提示:
 * arr 长度范围在[1, 1000000]之间
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/search-rotate-array-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution10_03 {
    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42.3 MB , 在所有 Java 提交中击败了 42.38% 的用户
     * 通过测试用例： 32 / 32
     * <p>
     * 写了很多特殊逻辑，而且套用了 Java 类库的二分查找，代码有点屎。不过有点不想改了，就这样吧……
     *
     * @param arr
     * @param target
     * @return
     */
    public int search(int[] arr, int target) {
        return search(arr, 0, arr.length, target);
    }

    private int search(int[] arr, int from, int to, int target) {
        if (to <= from) {
            return -1;
        }
        if (arr[from] == target) {
            return from;
        }
        if (to == from + 1) {
            return -1;
        }
        if (to == from + 2) {
            return arr[to - 1] == target ? to - 1 : -1;
        }
        int mid = (from + to) / 2;
        if (target == arr[mid]) {
            // 找到最小的和 mid 等值的
            return search(arr, from, mid + 1, target);
        } else if (arr[mid] > arr[to - 1]) {
            if (target > arr[mid] || target < arr[from]) {
                return search(arr, mid + 1, to, target);
            } else {
                int index = Arrays.binarySearch(arr, from, mid + 1, target);
                if (index < 0) {
                    return -1;
                }
                while (index > 0 && arr[index - 1] == arr[index]) {
                    index--;
                }
                return index;
            }
        } else if (arr[mid] < arr[from]) {
            if (target < arr[mid] || target > arr[to - 1]) {
                return search(arr, from, mid, target);
            } else {
                int index = Arrays.binarySearch(arr, mid + 1, to, target);
                if (index < 0) {
                    return -1;
                }
                while (index > 0 && arr[index - 1] == arr[index]) {
                    index--;
                }
                return index;
            }
        } else {
            int index = search(arr, from, mid, target);
            if (index >= 0) {
                return index;
            }
            return search(arr, mid + 1, to, target);
        }
    }
}
