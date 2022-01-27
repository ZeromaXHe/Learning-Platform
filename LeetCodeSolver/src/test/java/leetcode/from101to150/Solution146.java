package leetcode.from101to150;

import org.junit.Test;

import java.util.HashMap;

/**
 * @Author: zhuxi
 * @Time: 2022/1/24 10:03
 * @Description: 146. LRU缓存 | 难度：中等 | 标签：设计、哈希表、链表、双向链表
 * 请你设计并实现一个满足  LRU (最近最少使用) 缓存 约束的数据结构。
 * 实现 LRUCache 类：
 * LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 key-value 。
 * 如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。
 * 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * <p>
 * 示例：
 * <p>
 * 输入
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * 输出
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 * <p>
 * 解释
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * <p>
 * 提示：
 * 1 <= capacity <= 3000
 * 0 <= key <= 10000
 * 0 <= value <= 105
 * 最多调用 2 * 105 次 get 和 put
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lru-cache
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution146 {
    @Test
    public void LRUCacheTest() {
        // ["LRUCache","put","put","get","put","get","put","get","get","get"]
        // [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        // 预期结果：[null,null,null,1,null,-1,null,-1,3,4]
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));
        cache.put(3, 3);
        System.out.println(cache.get(2));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }

    /**
     * 执行用时： 56 ms , 在所有 Java 提交中击败了 19.59% 的用户
     * 内存消耗： 110 MB , 在所有 Java 提交中击败了 35.13% 的用户
     * 通过测试用例： 22 / 22
     */
    class LRUCache {
        private int capacity;
        private HashMap<Integer, LRUNode> map;
        private LRUNode head;
        private LRUNode tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            map = new HashMap<>();
            head = new LRUNode(0, 0);
            tail = new LRUNode(0, 0);
            head.next = tail;
            tail.pre = head;
        }

        public int get(int key) {
            LRUNode node = map.get(key);
            if (node == null) {
                return -1;
            }
            moveNodeToFirst(key);
            return node.val;
        }

        public void put(int key, int value) {
            if (map.containsKey(key)) {
                // 插入之前存在的key的情况
                // 14 / 21 个通过测试用例
                // ["LRUCache","get","put","get","put","put","get","get"]
                // [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]
                // 预计结果： [null,-1,null,-1,null,null,2,6]
                map.get(key).val = value;
                moveNodeToFirst(key);
                return;
            }
            if (map.size() == capacity) {
                map.remove(tail.pre.key);
                tail.pre.pre.next = tail;
                tail.pre = tail.pre.pre;
            }
            map.put(key, addToFirst(key, value));
        }

        private void moveNodeToFirst(int key) {
            LRUNode node = map.get(key);
            node.pre.next = node.next;
            node.next.pre = node.pre;
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
        }

        private LRUNode addToFirst(int key, int val) {
            LRUNode node = new LRUNode(key, val);
            head.next.pre = node;
            node.next = head.next;
            head.next = node;
            node.pre = head;
            return node;
        }
    }

    static class LRUNode {
        int key;
        int val;
        LRUNode pre;
        LRUNode next;

        public LRUNode(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
