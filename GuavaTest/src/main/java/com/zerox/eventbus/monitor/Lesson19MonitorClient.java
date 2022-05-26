package com.zerox.eventbus.monitor;

import com.google.common.eventbus.EventBus;

import java.nio.file.Paths;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 16:28
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson19MonitorClient {
    public static void main(String[] args) throws Exception {
        final EventBus eventBus = new EventBus();
        eventBus.register(new Lesson19FileChangeListener());

        final String targetPath = ".\\GuavaTest\\src\\test\\resources\\io";
        Lesson19TargetMonitor monitor = new Lesson19DirectoryTargetMonitor(eventBus, targetPath);
        System.out.println(Paths.get(targetPath).toAbsolutePath());

        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.schedule(() -> {
            try {
                monitor.stopMonitor();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 10, TimeUnit.SECONDS);
        executorService.shutdown();
        monitor.startMonitor();
    }
}
