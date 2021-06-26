package com.zerox.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zhuxi
 * @Time: 2021/6/26 10:29
 * @Description: 双缓冲方式实现的分钟级日志整合
 * 有很多漏洞，在可以接受的情况下可以使用
 * @Modified By: zhuxi
 */
@Component
public class LimitMinuteLogService {
    private final Logger PROB_LOGGER = LogManager.getLogger("prob");
    private final Logger MINUTE_LIMIT_LOGGER = LogManager.getLogger("minute_limit");
    private final Logger MINUTE_LIMIT_LOCAL_LOGGER = LogManager.getLogger("minute_limit_local");

    private static final ConcurrentHashMap<String, TimestampLog> IDT_TO_TIMESTAMP_LOG_MAP_ODD = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<String, TimestampLog> IDT_TO_TIMESTAMP_LOG_MAP_EVEN = new ConcurrentHashMap<>();

    static class TimestampLog {
        long timestamp;
        String logStr;
    }

    /**
     * [=== CAUTION ===]:
     * may cause concurrent problem, will print multiple minute end and minute start log.
     * for example, 3 requests:
     * request          00:01:59.999 req1               00:02:00.000 req2                   00:02:01.000 req3
     * minuteOfDay      1                               2                                   2
     * cache current    00:02:00.003                    00:02:00.001                        00:02:01.001
     * print pre        00:02:00.004 (print req2 log)   00:02:00.002 (print min1 other log) 00:02:01.002 (print req1 log)
     * <p>
     * in this circumstance,
     * will print 2 pre minute(minuteOfDay=1) end log,
     * and 1 current minute(minuteOfDay=2) start log.
     * <p>
     * for more concurrent req, may print more log.
     * (now, the superfluous log is tolerable)
     *
     * @param minuteOfDay
     * @param identityStr
     * @param timestamp
     * @param logStr
     */
    public void logLimitMinute(int minuteOfDay, String identityStr,
                               long timestamp, String logStr) {
        MINUTE_LIMIT_LOCAL_LOGGER.info(logStr);
        try {
            ConcurrentHashMap<String, TimestampLog> idtToTimestampLogMap = getCurrentMinuteIdtToTimestampLogMap(minuteOfDay);
            ConcurrentHashMap<String, TimestampLog> preIdtToTimestampLogMap = getPreMinuteIdtToTimestampLogMap(minuteOfDay);
            // cache current limit minute log
            cacheCurrentLimitMinuteLog(timestamp, logStr, identityStr, idtToTimestampLogMap);
            // print pre minute log
            printPreMinuteLog(minuteOfDay, identityStr, preIdtToTimestampLogMap);
        } catch (Throwable e) {
            PROB_LOGGER.error("logLimitMinute {} minute log error: ", minuteOfDay, e);
        }
    }

    private void cacheCurrentLimitMinuteLog(long timestamp, String logStr, String identityStr,
                                            ConcurrentHashMap<String, TimestampLog> idtToTimestampLogMap) {
        idtToTimestampLogMap.putIfAbsent(identityStr, new TimestampLog());
        TimestampLog timestampLog = idtToTimestampLogMap.get(identityStr);
        if (timestampLog.timestamp <= timestamp) {
            synchronized (idtToTimestampLogMap.get(identityStr)) {
                if (timestampLog.timestamp <= timestamp) {
                    // initial condition: timestampLog.timestamp == 0 is included here
                    timestampLog.logStr = logStr;
                    timestampLog.timestamp = timestamp;
                }
            }
        }
    }

    private void printPreMinuteLog(int minuteOfDay, String identityStr,
                                   ConcurrentHashMap<String, TimestampLog> preIdtToTimestampLogMap) {
        if (preIdtToTimestampLogMap.get(identityStr) != null) {
            synchronized (preIdtToTimestampLogMap.get(identityStr)) {
                TimestampLog timestampLogPre = preIdtToTimestampLogMap.get(identityStr);
                if (timestampLogPre != null) {
                    // Spring 的 StringUtils
                    if (StringUtils.hasLength(timestampLogPre.logStr)) {
                        try {
                            MINUTE_LIMIT_LOGGER.info(timestampLogPre.logStr);
                        } catch (Throwable e) {
                            PROB_LOGGER.error("print {} pre minute log error: ", minuteOfDay, e);
                        } finally {
                            preIdtToTimestampLogMap.remove(identityStr);
                        }
                    }
                }
            }
        }
    }

    public static int getCurrentMinuteOfDay() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        return hour * 60 + minute;
    }

    private ConcurrentHashMap<String, TimestampLog> getCurrentMinuteIdtToTimestampLogMap(int minuteOfDay) {
        if (minuteOfDay % 2 == 0) {
            return IDT_TO_TIMESTAMP_LOG_MAP_EVEN;
        } else {
            return IDT_TO_TIMESTAMP_LOG_MAP_ODD;
        }
    }

    private ConcurrentHashMap<String, TimestampLog> getPreMinuteIdtToTimestampLogMap(int minuteOfDay) {
        if (minuteOfDay % 2 == 0) {
            return IDT_TO_TIMESTAMP_LOG_MAP_ODD;
        } else {
            return IDT_TO_TIMESTAMP_LOG_MAP_EVEN;
        }
    }
}

