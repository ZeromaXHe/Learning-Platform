package com.zerox.cache;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 14:01
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson29ReferenceExample {
    /**
     * 在 JVM 设置中添加 -Xmx128M -Xms64M -XX:+PrintGCDetails
     *
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
//        strongReference();
//        softReference();
//        weakReference();

        differentReferenceTest();
    }

    private static void differentReferenceTest() throws InterruptedException {
        Ref ref = new Ref(100);
        ReferenceQueue<Ref> queue = new ReferenceQueue<>();
        // SoftReference 不能和其他一起测试，否则 gc 时，这个引用没有清除的话，可达性分析后就也不会清除软引用和虚引用
//        SoftReference<Ref> softReference = new SoftReference<>(ref);
        WeakReference<Ref> weakReference = new WeakReference<>(ref);
        MyPhantomReference phantomReference = new MyPhantomReference(ref, queue, 100);
        ref = null;
        System.out.println("ref: " + ref);
//        System.out.println("softReference: " + softReference.get());
        System.out.println("weakReference: " + weakReference.get());
        System.out.println("phantomReference: " + phantomReference.get());
        System.gc();
        // System.gc 等于下面这句
//        Runtime.getRuntime().gc();

        // 上面 gc 不会立即执行，需要等一会
        TimeUnit.SECONDS.sleep(3);
        System.out.println("------------------");
        System.out.println("ref: " + ref);
//        System.out.println("softReference: " + softReference.get());
        System.out.println("weakReference: " + weakReference.get());
        System.out.println("phantomReference: " + phantomReference.get());

        // 虚引用 ReferenceQueue 的测试貌似仅仅用 System.gc() 是无效的，必须用相关虚拟机工具强制 gc
//        Reference<? extends Ref> remove = queue.remove();
//        ((MyPhantomReference) remove).doAction();
    }

    private static void strongReference() throws InterruptedException {
        int counter = 0;

        List<Ref> container = new ArrayList<>();
        for (; ; ) {
            int current = counter++;
            container.add(new Ref(current));
            System.out.println("The Ref " + current + " inserted into container");
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }

    /**
     * 发现内存不够时，会进行 GC；但是如果 GC 过程还没完成，又有新的内存要求时，还是会 OOM
     *
     * @throws InterruptedException
     */
    private static void softReference() throws InterruptedException {
        int counter = 0;

        List<SoftReference<Ref>> container = new ArrayList<>();
        for (; ; ) {
            int current = counter++;
            SoftReference<Ref> reference = new SoftReference<>(new Ref(current));
            container.add(reference);
            System.out.println("The Ref " + current + " inserted into container");
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }

    /**
     * 任何 GC 时都会被释放
     *
     * @throws InterruptedException
     */
    private static void weakReference() throws InterruptedException {
        int counter = 0;

        List<WeakReference<Ref>> container = new ArrayList<>();
        for (; ; ) {
            int current = counter++;
            WeakReference<Ref> reference = new WeakReference<>(new Ref(current));
            container.add(reference);
            System.out.println("The Ref " + current + " inserted into container");
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }

    private static class MyPhantomReference extends PhantomReference<Ref> {
        private int index;

        public MyPhantomReference(Ref referent, ReferenceQueue<? super Ref> q, int index) {
            super(referent, q);
            this.index = index;
        }

        public void doAction() {
            System.out.println("the object " + index + " is in GC.");
        }
    }

    private static class Ref {
        private byte[] data = new byte[1024 * 1024];

        private final int index;

        public Ref(int index) {
            this.index = index;
        }

        @Override
        protected void finalize() throws Throwable {
            super.finalize();
            System.out.println("The index [" + index + "] will be GC.");
        }

        @Override
        public String toString() {
            return "Ref{" +
                    "index=" + index +
                    '}';
        }
    }
}
