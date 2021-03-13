package leetcode.from701to800;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2021/3/13 9:40
 * @Description: 705.设计哈希集合 | 难度：简单 | 标签：设计、哈希表
 * 不使用任何内建的哈希表库设计一个哈希集合（HashSet）。
 * <p>
 * 实现 MyHashSet 类：
 * void add(key) 向哈希集合中插入值 key 。
 * bool contains(key) 返回哈希集合中是否存在这个值 key 。
 * void remove(key) 将给定值 key 从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。
 *  
 * 示例：
 * 输入：
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * 输出：
 * [null, null, null, true, false, null, true, null, false]
 * <p>
 * 解释：
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // 返回 True
 * myHashSet.contains(3); // 返回 False ，（未找到）
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // 返回 True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // 返回 False ，（已移除）
 * <p>
 * 提示：
 * 0 <= key <= 10^6
 * 最多调用 10^4 次 add、remove 和 contains 。
 * <p>
 * 进阶：你可以不使用内建的哈希集合库解决此问题吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashset
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: zhuxi
 */
public class Solution705 {
    /**
     * Your MyHashSet object will be instantiated and called as such:
     * MyHashSet obj = new MyHashSet();
     * obj.add(key);
     * obj.remove(key);
     * boolean param_3 = obj.contains(key);
     * <p>
     * BUCKET_CAPACITY = 1000:
     * 执行用时： 19 ms , 在所有 Java 提交中击败了 68.62% 的用户
     * 内存消耗： 46.1 MB , 在所有 Java 提交中击败了 46.13% 的用户
     * <p>
     * BUCKET_CAPACITY = 10000:
     * 执行用时： 22 ms , 在所有 Java 提交中击败了 38.10% 的用户
     * 内存消耗： 44.1 MB , 在所有 Java 提交中击败了 95.75% 的用户
     */
    class MyHashSet {
        private Bucket[] bucket;
        final static int BUCKET_CAPACITY = 1000;

        /**
         * Initialize your data structure here.
         */
        public MyHashSet() {
            bucket = new Bucket[BUCKET_CAPACITY];
        }

        public void add(int key) {
            if (contains(key)) {
                return;
            }
            if (bucket[key % BUCKET_CAPACITY] == null) {
                bucket[key % BUCKET_CAPACITY] = new Bucket(new LinkedList<>());
            }
            bucket[key % BUCKET_CAPACITY].list.add(key);
        }

        public void remove(int key) {
            if (!contains(key)) {
                return;
            }
            bucket[key % BUCKET_CAPACITY].list.removeFirstOccurrence(key);
        }

        /**
         * Returns true if this set contains the specified element
         */
        public boolean contains(int key) {
            return bucket[key % BUCKET_CAPACITY] != null && bucket[key % BUCKET_CAPACITY].list.contains(key);
        }
    }

    class Bucket {
        LinkedList<Integer> list;

        public Bucket(LinkedList<Integer> list) {
            this.list = list;
        }
    }
}
