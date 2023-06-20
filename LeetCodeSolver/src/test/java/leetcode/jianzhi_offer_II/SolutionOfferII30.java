package leetcode.jianzhi_offer_II;

import java.util.HashMap;
import java.util.Random;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 030. 插入、删除和随机访问都是 O(1) 的容器 | 难度：中等 | 标签：设计、数组、哈希表、数学、随机化
 * 设计一个支持在平均 时间复杂度 O(1) 下，执行以下操作的数据结构：
 * <p>
 * insert(val)：当元素 val 不存在时返回 true ，并向集合中插入该项，否则返回 false 。
 * remove(val)：当元素 val 存在时返回 true ，并从集合中移除该项，否则返回 false 。
 * getRandom：随机返回现有集合中的一项。每个元素应该有 相同的概率 被返回。
 * <p>
 * 示例 :
 * 输入: inputs = ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove", "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * 输出: [null, true, false, true, 2, true, false, 2]
 * 解释:
 * RandomizedSet randomSet = new RandomizedSet();  // 初始化一个空的集合
 * randomSet.insert(1); // 向集合中插入 1 ， 返回 true 表示 1 被成功地插入
 * <p>
 * randomSet.remove(2); // 返回 false，表示集合中不存在 2
 * <p>
 * randomSet.insert(2); // 向集合中插入 2 返回 true ，集合现在包含 [1,2]
 * <p>
 * randomSet.getRandom(); // getRandom 应随机返回 1 或 2
 * <p>
 * randomSet.remove(1); // 从集合中移除 1 返回 true 。集合现在包含 [2]
 * <p>
 * randomSet.insert(2); // 2 已在集合中，所以返回 false
 * <p>
 * randomSet.getRandom(); // 由于 2 是集合中唯一的数字，getRandom 总是返回 2
 * <p>
 * 提示：
 * -231 <= val <= 231 - 1
 * 最多进行 2 * 105 次 insert ， remove 和 getRandom 方法调用
 * 当调用 getRandom 方法时，集合中至少有一个元素
 * <p>
 * 注意：本题与主站 380 题相同：https://leetcode-cn.com/problems/insert-delete-getrandom-o1/
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/FortPu
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/20 15:53
 */
public class SolutionOfferII30 {
    /**
     * 执行用时：20 ms, 在所有 Java 提交中击败了 99.41% 的用户
     * 内存消耗：86.9 MB, 在所有 Java 提交中击败了 80.37% 的用户
     * 通过测试用例：19 / 19
     */
    class RandomizedSet {

        private int[] arr;
        private int size = 0;
        private HashMap<Integer, Integer> map;
        private Random random;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            map = new HashMap<>();
            arr = new int[200000];
            random = new Random();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (map.containsKey(val)) {
                return false;
            }
            map.put(val, size);
            arr[size++] = val;
            return true;
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (!map.containsKey(val)) {
                return false;
            }
            int idx = map.get(val);
            if (idx < size - 1) {
                arr[idx] = arr[size - 1];
                map.put(arr[size - 1], idx);
            }
            map.remove(val);
            size--;
            return true;
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return arr[random.nextInt(size)];
        }
    }

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
