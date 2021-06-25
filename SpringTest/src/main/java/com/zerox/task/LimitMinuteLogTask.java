package com.zerox.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Component
public class LimitMinuteLogTask {
    private final Logger LOGGER = LogManager.getLogger("business");
    private final Logger PROB_LOGGER = LogManager.getLogger("prob");
    private final Logger MINUTE_LIMIT_LOGGER = LogManager.getLogger("minute_limit");

    private static final ConcurrentHashMap<Integer, ConcurrentHashMap<String, LockedTimestampLog>>
            MINUTE_TO_LIMIT_LOG_MAP = new ConcurrentHashMap<>();

    static class LockedTimestampLog {
        long timestamp;
        String logStr;
        Lock lock;

        LockedTimestampLog() {
            this.lock = new ReentrantLock();
        }
    }

    @Scheduled(cron = "5 0/1 * * * ?")
    public void task() {
        int minuteOfDay = getCurrentMinuteOfDay() - 1;
        minuteOfDay = minuteOfDay < 0 ? (1440 + minuteOfDay) : minuteOfDay;
        LOGGER.info("LimitMinuteLogTask mininute {} start...", minuteOfDay);
        long start = System.currentTimeMillis();
        try {
            ConcurrentHashMap<String, LockedTimestampLog> identityStrToLogMap = MINUTE_TO_LIMIT_LOG_MAP.get(minuteOfDay);
            if (identityStrToLogMap != null && !identityStrToLogMap.isEmpty()) {
                identityStrToLogMap.values().stream()
                        // Spring的StringUtils.isEmpty是过时的，
                        // 注释中推荐使用 #hasLength(String) 或者 #hasText(String) 或者 ObjectUtils#isEmpty(Object)
                        .filter(lockedTimestampLog -> StringUtils.hasLength(lockedTimestampLog.logStr))
                        .map(lockedTimestampLog -> lockedTimestampLog.logStr)
                        .forEach(MINUTE_LIMIT_LOGGER::info);
                ConcurrentHashMap<String, LockedTimestampLog> remove = MINUTE_TO_LIMIT_LOG_MAP.remove(minuteOfDay);
                LOGGER.info("LimitMinuteLogTask mininute {} finished... inputted log count: {}, timecost: {}",
                        minuteOfDay, remove.size(), System.currentTimeMillis() - start);
                remove.clear();
            } else {
                LOGGER.info("LimitMinuteLogTask mininute {} finished... inputted log count: {}, timecost: {}",
                        minuteOfDay, 0, System.currentTimeMillis() - start);
            }
        } catch (Throwable e) {
            PROB_LOGGER.error("LimitMinuteLogTask mininute {} throw Throwable:", minuteOfDay, e);
        }
    }

    @Async
    public void addLogToMinuteToLimitLogMap(int minuteOfDay, String identityStr, long timestamp, String logStr) {
        MINUTE_TO_LIMIT_LOG_MAP.putIfAbsent(minuteOfDay, new ConcurrentHashMap<>());
        ConcurrentHashMap<String, LockedTimestampLog> idtStrToTimestampLogMap = MINUTE_TO_LIMIT_LOG_MAP.get(minuteOfDay);
        idtStrToTimestampLogMap.putIfAbsent(identityStr, new LockedTimestampLog());

        LockedTimestampLog lockedTimestampLog = idtStrToTimestampLogMap.get(identityStr);
        if (lockedTimestampLog.timestamp <= timestamp) {
            lockedTimestampLog.lock.lock();
            try {
                if (lockedTimestampLog.timestamp <= timestamp) {
                    // 初始化 lockedTimestampLog.timestamp == 0 的情况包含其中
                    lockedTimestampLog.logStr = logStr;
                    lockedTimestampLog.timestamp = timestamp;
                }
            } finally {
                lockedTimestampLog.lock.unlock();
            }
        }
    }

    public static int getCurrentMinuteOfDay() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour * 60 + minute;
    }
}
