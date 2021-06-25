package com.zerox.task;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class LimitMinuteLogTask {
    private final Logger LOGGER = LogManager.getLogger("business");
    private final Logger PROB_LOGGER = LogManager.getLogger("prob");
    private final Logger MINUTE_LIMIT_LOGGER = LogManager.getLogger("minute_limit");

    private static final Map<Integer, Map<String, TimestampLog>> MINUTE_TO_LIMIT_LOG_MAP = new ConcurrentHashMap<>();

    static class TimestampLog {
        long timestamp;
        String logStr;

        TimestampLog(long timestamp, String logStr) {
            this.timestamp = timestamp;
            this.logStr = logStr;
        }
    }

    @Scheduled(cron = "5 0/1 * * * ?")
    public void task() {
        int minuteOfDay = getCurrentMinuteOfDay() - 1;
        minuteOfDay = minuteOfDay < 0 ? (1440 + minuteOfDay) : minuteOfDay;
        LOGGER.info("LimitMinuteLogTask mininute {} start...", minuteOfDay);
        long start = System.currentTimeMillis();
        try {
            Map<String, TimestampLog> identityStrToLogMap = MINUTE_TO_LIMIT_LOG_MAP.get(minuteOfDay);
            if (identityStrToLogMap != null && !identityStrToLogMap.isEmpty()) {
                identityStrToLogMap.values().stream()
                        .map(timestampLog -> timestampLog.logStr)
                        .forEach(MINUTE_LIMIT_LOGGER::info);
                Map<String, TimestampLog> remove = MINUTE_TO_LIMIT_LOG_MAP.remove(minuteOfDay);
                LOGGER.info("LimitMinuteLogTask mininute {} finished... inputted log count: {}, timecost: {}",
                        minuteOfDay, remove.size(), System.currentTimeMillis() - start);
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
        // 可能有并发问题，同时多个不一定会更新成时间戳最大的?
        Map<String, TimestampLog> idtStrToTimestampLogMap = MINUTE_TO_LIMIT_LOG_MAP.get(minuteOfDay);
        TimestampLog timestampLog = idtStrToTimestampLogMap.get(identityStr);
        if (timestampLog == null || timestampLog.timestamp <= timestamp) {
            idtStrToTimestampLogMap.put(identityStr, new TimestampLog(timestamp, logStr));
        }
    }

    public static int getCurrentMinuteOfDay() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour * 60 + minute;
    }
}
