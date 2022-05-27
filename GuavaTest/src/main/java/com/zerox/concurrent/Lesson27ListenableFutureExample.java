package com.zerox.concurrent;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhuxi
 * @Time: 2022/5/27 11:28
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson27ListenableFutureExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);

        Future<?> future = service.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });

        // 阻塞
        Object result = future.get();
        System.out.println(result);

        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(service);
        ListenableFuture<Integer> listenableFuture = listeningExecutorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        });
//        listenableFuture.addListener(() -> System.out.println("listenableFuture finished"), service);
        Futures.addCallback(listenableFuture, new MyCallback(), service);

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 10;
        }, service).whenComplete((v, t) -> System.out.println("CompletableFuture Finished, result: " + v));

        System.out.println("==========");
        // guava 的 ListenableFuture 会和 shutdown 报错()，不知道为啥:
        // java.util.concurrent.RejectedExecutionException: Task CallbackListener
        // {com.zerox.concurrent.Lesson27ListenableFutureExample$MyCallback@ce6a4d7} rejected from
        // java.util.concurrent.ThreadPoolExecutor@3c1f5fee
        // [Shutting down, pool size = 1, active threads = 1, queued tasks = 0, completed tasks = 2]
//        service.shutdown();
    }

    static class MyCallback implements FutureCallback<Integer> {
        @Override
        public void onSuccess(@Nullable Integer result) {
            System.out.println("ListenableFuture Finished, result: " + result);
        }

        @Override
        public void onFailure(Throwable t) {
            t.printStackTrace();
        }
    }
}
