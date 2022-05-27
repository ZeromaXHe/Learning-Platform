package com.zerox.concurrent;

import com.google.common.util.concurrent.Monitor;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 9:53
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson24MonitorExample {
    public static void main(String[] args) {
//        final Synchronized sync = new Synchronized();
//        final LockCondition sync = new LockCondition();
        final MonitorGuard sync = new MonitorGuard();
        final AtomicInteger COUNTER = new AtomicInteger();
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    int data = COUNTER.getAndIncrement();
                    System.out.println(Thread.currentThread() + " offer " + data);
                    sync.offer(data);
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    int data = sync.take();
                    System.out.println(Thread.currentThread() + " take " + data);
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    static class MonitorGuard {
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10;

        private final Monitor monitor = new Monitor();
        private final Monitor.Guard CAN_OFFER = monitor.newGuard(() -> queue.size() < MAX);
        private final Monitor.Guard CAN_TAKE = monitor.newGuard(() -> !queue.isEmpty());

        public void offer(int value) {
            try {
                monitor.enterWhen(CAN_OFFER);
                queue.addLast(value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                monitor.leave();
            }
        }

        public int take() {
            try {
                monitor.enterWhen(CAN_TAKE);
                return queue.removeFirst();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                monitor.leave();
            }
        }
    }

    static class LockCondition {
        private final ReentrantLock lock = new ReentrantLock();
        private final Condition FULL_CONDITION = lock.newCondition();
        private final Condition EMPTY_CONDITION = lock.newCondition();

        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10;

        public void offer(int value) {
            doAction(t -> {
                while (queue.size() >= MAX) {
                    try {
                        FULL_CONDITION.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(t);
                EMPTY_CONDITION.signalAll();
            }, value);
        }

        public int take() {
            return doAction(() -> {
                while (queue.isEmpty()) {
                    try {
                        EMPTY_CONDITION.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                final Integer result = queue.removeFirst();
                FULL_CONDITION.signalAll();
                return result;
            });
        }

        private <T> void doAction(Consumer<T> consumer, T t) {
            try {
                lock.lock();
                consumer.accept(t);
            } finally {
                lock.unlock();
            }
        }

        private <T> T doAction(Supplier<T> supplier) {
            try {
                lock.lock();
                return supplier.get();
            } finally {
                lock.unlock();
            }
        }
    }

    static class Synchronized {
        private final LinkedList<Integer> queue = new LinkedList<>();
        private final int MAX = 10;

        public void offer(int value) {
            synchronized (queue) {
                while (queue.size() >= MAX) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.addLast(value);
                queue.notifyAll();
            }
        }

        public int take() {
            synchronized (queue) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                final Integer result = queue.removeFirst();
                queue.notifyAll();
                return result;
            }
        }
    }
}
