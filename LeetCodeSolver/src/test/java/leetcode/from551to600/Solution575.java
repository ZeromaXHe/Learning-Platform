package leetcode.from551to600;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhuxi
 * @Time: 2021/11/1 18:28
 * @Description: 575. 分糖果 | 难度：简单 | 标签：数组、哈希表
 * 给定一个偶数长度的数组，其中不同的数字代表着不同种类的糖果，每一个数字代表一个糖果。你需要把这些糖果平均分给一个弟弟和一个妹妹。返回妹妹可以获得的最大糖果的种类数。
 * <p>
 * 示例 1:
 * 输入: candies = [1,1,2,2,3,3]
 * 输出: 3
 * 解析: 一共有三种种类的糖果，每一种都有两个。
 * 最优分配方案：妹妹获得[1,2,3],弟弟也获得[1,2,3]。这样使妹妹获得糖果的种类数最多。
 * <p>
 * 示例 2 :
 * 输入: candies = [1,1,2,3]
 * 输出: 2
 * 解析: 妹妹获得糖果[2,3],弟弟获得糖果[1,1]，妹妹有两种不同的糖果，弟弟只有一种。这样使得妹妹可以获得的糖果种类数最多。
 * <p>
 * 注意:
 * 数组的长度为[2, 10,000]，并且确定为偶数。
 * 数组中数字的大小在范围[-100,000, 100,000]内。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distribute-candies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution575 {
    /**
     * 效率不高，待优化
     * <p>
     * 执行用时： 63 ms , 在所有 Java 提交中击败了 5.06% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 5.06% 的用户
     * 通过测试用例： 206 / 206
     *
     * @param candyType
     * @return
     */
    public int distributeCandies(int[] candyType) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int type : candyType) {
            map.putIfAbsent(type, 0);
            map.put(type, map.get(type) + 1);
        }

        int count1 = 0;
        int countOthers = 0;
        int countMoreThanOne = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == 1) {
                count1++;
            } else {
                countOthers++;
                countMoreThanOne += entry.getValue() - 1;
            }
        }

        if (countMoreThanOne > count1 + countOthers) {
            return countOthers + count1;
        } else {
            return countOthers + (countMoreThanOne - countOthers) + (count1 - Math.min(count1, countMoreThanOne - countOthers)) / 2;
        }
    }
}
