package leetcode.chengxuyuan_mianshi_jingdian;

import java.util.LinkedList;

/**
 * @Author: zhuxi
 * @Time: 2022/5/5 15:34
 * @Description: 面试题 03.06. 动物收容所 | 难度：简单 | 标签：设计、队列
 * 动物收容所。有家动物收容所只收容狗与猫，且严格遵守“先进先出”的原则。
 * 在收养该收容所的动物时，收养人只能收养所有动物中“最老”（由其进入收容所的时间长短而定）的动物，或者可以挑选猫或狗（同时必须收养此类动物中“最老”的）。
 * 换言之，收养人不能自由挑选想收养的对象。请创建适用于这个系统的数据结构，实现各种操作方法，比如enqueue、dequeueAny、dequeueDog和dequeueCat。
 * 允许使用Java内置的LinkedList数据结构。
 * <p>
 * enqueue方法有一个animal参数，animal[0]代表动物编号，animal[1]代表动物种类，其中 0 代表猫，1 代表狗。
 * <p>
 * dequeue*方法返回一个列表[动物编号, 动物种类]，若没有可以收养的动物，则返回[-1,-1]。
 * <p>
 * 示例1:
 * 输入：
 * ["AnimalShelf", "enqueue", "enqueue", "dequeueCat", "dequeueDog", "dequeueAny"]
 * [[], [[0, 0]], [[1, 0]], [], [], []]
 * 输出：
 * [null,null,null,[0,0],[-1,-1],[1,0]]
 * <p>
 * 示例2:
 * 输入：
 * ["AnimalShelf", "enqueue", "enqueue", "enqueue", "dequeueDog", "dequeueCat", "dequeueAny"]
 * [[], [[0, 0]], [[1, 0]], [[2, 1]], [], [], []]
 * 输出：
 * [null,null,null,null,[2,1],[0,0],[1,0]]
 * <p>
 * 说明:
 * 收纳所的最大容量为20000
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/animal-shelter-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @ModifiedBy: zhuxi
 */
public class Solution03_06 {
    /**
     * 执行用时： 66 ms , 在所有 Java 提交中击败了 89.77% 的用户
     * 内存消耗： 49.3 MB , 在所有 Java 提交中击败了 96.18% 的用户
     * 通过测试用例： 31 / 31
     * <p>
     * 编号默认是升序的，所以可以利用这个来判断先后
     */
    class AnimalShelf {
        private final int[] DEFAULT_RESULT = new int[]{-1, -1};
        LinkedList<int[]> catQueue;
        LinkedList<int[]> dogQueue;

        public AnimalShelf() {
            catQueue = new LinkedList<>();
            dogQueue = new LinkedList<>();
        }

        public void enqueue(int[] animal) {
            if (animal[1] == 0) {
                catQueue.offer(animal);
            } else {
                dogQueue.offer(animal);
            }
        }

        public int[] dequeueAny() {
            if (catQueue.isEmpty() && dogQueue.isEmpty()) {
                return DEFAULT_RESULT;
            }
            if (catQueue.isEmpty()) {
                return dogQueue.poll();
            }
            if (dogQueue.isEmpty()) {
                return catQueue.poll();
            }
            return dogQueue.peek()[0] > catQueue.peek()[0] ?
                    catQueue.poll() : dogQueue.poll();
        }

        public int[] dequeueDog() {
            return dogQueue.isEmpty() ? DEFAULT_RESULT : dogQueue.poll();
        }

        public int[] dequeueCat() {
            return catQueue.isEmpty() ? DEFAULT_RESULT : catQueue.poll();
        }
    }

/**
 * Your AnimalShelf object will be instantiated and called as such:
 * AnimalShelf obj = new AnimalShelf();
 * obj.enqueue(animal);
 * int[] param_2 = obj.dequeueAny();
 * int[] param_3 = obj.dequeueDog();
 * int[] param_4 = obj.dequeueCat();
 */
}
