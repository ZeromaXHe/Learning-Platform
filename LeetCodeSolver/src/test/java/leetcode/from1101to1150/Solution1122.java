package leetcode.from1101to1150;

import java.util.Arrays;

/**
 * @author ZeromaXHe
 * @date 2020/11/14 22:22
 * @Description 1122.数组的相对排序 | 难度：简单 | 标签：排序、数组
 * 给你两个数组，arr1 和 arr2，
 * arr2 中的元素各不相同
 * arr2 中的每个元素都出现在 arr1 中
 * 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。
 *
 * 示例：
 * 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
 * 输出：[2,2,2,1,4,3,3,9,6,7,19]
 *
 * 提示：
 * arr1.length, arr2.length <= 1000
 * 0 <= arr1[i], arr2[i] <= 1000
 * arr2 中的元素 arr2[i] 各不相同
 * arr2 中的每个元素 arr2[i] 都出现在 arr1 中
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/relative-sort-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class Solution1122 {
    /**
     * 执行用时： 4 ms, 在所有 Java 提交中击败了 30.08% 的用户
     * 内存消耗： 37.1 MB, 在所有 Java 提交中击败了 95.21% 的用户
     * 前面找arr2的过程还可以优化，时间有点慢
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        int index = 0;
        // 将arr2中出现的数字排到前面
        for(int i=0;i<arr2.length;i++){
            for(int j=index;j<arr1.length&& index<arr1.length;j++){
                if(arr1[j]==arr2[i]){
                    int tmp = arr1[j];
                    arr1[j] = arr1[index];
                    arr1[index++] = tmp;
                    continue;
                }
            }
        }
        // 对arr2中没有的那些数字排序
        Arrays.sort(arr1,index,arr1.length);
        return arr1;
    }

    public static void main(String[] args) {
        Solution1122 solution1122 = new Solution1122();
        // [2,2,2,1,4,3,3,9,6,7,19]
        System.out.println(Arrays.toString(
                solution1122.relativeSortArray(
                        new int[]{2,3,1,3,2,4,6,7,9,2,19},
                        new int[]{2,1,4,3,9,6})));
        //[22,33,48,4,15,36,39,41,45,47]
        System.out.println(Arrays.toString(
                solution1122.relativeSortArray(
                        new int[]{33,22,48,4,39,36,41,47,15,45},
                        new int[]{22,33,48,4})));
        //[22,28,8,6,17,44]
        System.out.println(Arrays.toString(
                solution1122.relativeSortArray(
                        new int[]{28,6,22,8,44,17},
                        new int[]{22,28,8,6})));
    }
}
