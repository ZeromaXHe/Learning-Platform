package leetcode.from701to800;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/3/14 8:33
 * @Description: 706.设计哈希映射 | 难度：简单 | 标签：设计、哈希表
 * 不使用任何内建的哈希表库设计一个哈希映射（HashMap）。
 * <p>
 * 实现 MyHashMap 类：
 * <p>
 * MyHashMap() 用空映射初始化对象
 * void put(int key, int value) 向 HashMap 插入一个键值对 (key, value) 。如果 key 已经存在于映射中，则更新其对应的值 value 。
 * int get(int key) 返回特定的 key 所映射的 value ；如果映射中不包含 key 的映射，返回 -1 。
 * void remove(key) 如果映射中存在 key 的映射，则移除 key 和它所对应的 value 。
 * <p>
 * 示例：
 * 输入：
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * 输出：
 * [null, null, null, 1, -1, null, 1, null, -1]
 * <p>
 * 解释：
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // myHashMap 现在为 [[1,1]]
 * myHashMap.put(2, 2); // myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(1);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.get(3);    // 返回 -1（未找到），myHashMap 现在为 [[1,1], [2,2]]
 * myHashMap.put(2, 1); // myHashMap 现在为 [[1,1], [2,1]]（更新已有的值）
 * myHashMap.get(2);    // 返回 1 ，myHashMap 现在为 [[1,1], [2,1]]
 * myHashMap.remove(2); // 删除键为 2 的数据，myHashMap 现在为 [[1,1]]
 * myHashMap.get(2);    // 返回 -1（未找到），myHashMap 现在为 [[1,1]]
 * <p>
 * 提示：
 * 0 <= key, value <= 106
 * 最多调用 104 次 put、get 和 remove 方法
 * <p>
 * 进阶：你能否不使用内置的 HashMap 库解决此问题？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-hashmap
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @Modified By: ZeromaXHe
 */
public class Solution706 {
    /**
     * Your MyHashMap object will be instantiated and called as such:
     * MyHashMap obj = new MyHashMap();
     * obj.put(key,value);
     * int param_2 = obj.get(key);
     * obj.remove(key);
     * <p>
     * 执行用时： 21 ms , 在所有 Java 提交中击败了 78.27% 的用户
     * 内存消耗： 43.7 MB , 在所有 Java 提交中击败了 50.60% 的用户
     */
    class MyHashMap {
        private Bucket[] bucket;
        final static int BUCKET_CAPACITY = 1000;

        /**
         * Initialize your data structure here.
         */
        public MyHashMap() {
            bucket = new Bucket[BUCKET_CAPACITY];
        }

        /**
         * value will always be non-negative.
         */
        public void put(int key, int value) {
            if (bucket[key % BUCKET_CAPACITY] == null) {
                bucket[key % BUCKET_CAPACITY] = new Bucket(new LinkedList<>());
            }
            LinkedList<int[]> list = bucket[key % BUCKET_CAPACITY].list;
            for (int[] entry : list) {
                if (entry[0] == key) {
                    entry[1] = value;
                    return;
                }
            }
            bucket[key % BUCKET_CAPACITY].list.add(new int[]{key, value});
        }

        /**
         * Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key
         */
        public int get(int key) {
            if (bucket[key % BUCKET_CAPACITY] == null) {
                return -1;
            }
            LinkedList<int[]> list = bucket[key % BUCKET_CAPACITY].list;
            for (int[] entry : list) {
                if (entry[0] == key) {
                    return entry[1];
                }
            }
            return -1;
        }

        /**
         * Removes the mapping of the specified value key if this map contains a mapping for the key
         */
        public void remove(int key) {
            if (bucket[key % BUCKET_CAPACITY] == null) {
                return;
            }
            Iterator<int[]> iterator = bucket[key % BUCKET_CAPACITY].list.iterator();
            while (iterator.hasNext()) {
                int[] entry = iterator.next();
                if (entry[0] == key) {
                    iterator.remove();
                    return;
                }
            }
        }
    }

    class Bucket {
        LinkedList<int[]> list;

        public Bucket(LinkedList<int[]> list) {
            this.list = list;
        }
    }
}
