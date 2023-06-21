package leetcode.jianzhi_offer_II;

import java.util.HashMap;

/**
 * @author zhuxi
 * @apiNote 剑指 Offer II 031. 最近最少使用缓存 | 难度：中等 | 标签：设计、哈希表、链表、双向链表
 * 运用所掌握的数据结构，设计和实现一个  LRU (Least Recently Used，最近最少使用) 缓存机制 。
 * <p>
 * 实现 LRUCache 类：
 * <p>
 * LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
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
 * 进阶：是否可以在 O(1) 时间复杂度内完成这两种操作？
 * <p>
 * 注意：本题与主站 146 题相同：https://leetcode-cn.com/problems/lru-cache/ 
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/OrIXps
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @implNote
 * @since 2023/6/20 16:08
 */
public class SolutionOfferII31 {
    /**
     * 执行用时：44 ms, 在所有 Java 提交中击败了 63.91% 的用户
     * 内存消耗：115.3 MB, 在所有 Java 提交中击败了 34.24% 的用户
     * 通过测试用例：21 / 21
     */
    class LRUCache {
        private HashMap<Integer, Node> map;
        private int capacity;
        private Node dummy;

        public LRUCache(int capacity) {
            this.map = new HashMap<>();
            this.capacity = capacity;
            this.dummy = new Node();
            dummy.next = dummy;
            dummy.prev = dummy;
        }

        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            Node node = map.get(key);
            node.prev.next = node.next;
            node.next.prev = node.prev;
            // 把 node 放到链表头部
            node.next = dummy.next;
            node.prev = dummy;
            dummy.next.prev = node;
            dummy.next = node;
            return node.val;
        }

        public void put(int key, int value) {
            Node node;
            if (map.containsKey(key)) {
                node = map.get(key);
                node.prev.next = node.next;
                node.next.prev = node.prev;
                node.val = value;
            } else {
                node = new Node(key, value);
                map.put(key, node);
            }
            // 把 node 放到链表头部
            node.next = dummy.next;
            node.prev = dummy;
            dummy.next.prev = node;
            dummy.next = node;
            if (map.size() > capacity) {
                // 移除链表尾
                Node tail = dummy.prev;
                tail.prev.next = dummy;
                dummy.prev = tail.prev;
                tail.prev = null;
                tail.next = null;
                map.remove(tail.key);
            }
        }
    }

    class Node {
        Node prev;
        Node next;
        Integer key;
        Integer val;

        Node() {
        }

        Node(int key, int val) {
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
