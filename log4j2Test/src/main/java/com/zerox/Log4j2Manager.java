package com.zerox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @Author: zhuxi
 * @Time: 2021/6/16 18:23
 * @Description:
 * @Modified By: zhuxi
 */
public class Log4j2Manager {
    private static final Logger ADIDLOGGER = LogManager.getLogger("task_ad_id");
    private static final Logger BINDINGLOGGER = LogManager.getLogger("task_binding");
    private static final Logger TAGSLOGGER = LogManager.getLogger("task_tags");
    private static final Logger ADCONTENTLOGGER = LogManager.getLogger("task_ad_content");

    private static final Logger IMG_REPLACE = LogManager.getLogger("img_replace");
    private static final Logger ADR_SELF_REQUEST = LogManager.getLogger("adr_self_request");
    private static final Logger TASK_NONAD = LogManager.getLogger("task_nonad");
    private static final Logger FILT_LOGGER = LogManager.getLogger("filter");
    private static final Logger PROXY_DIS_LOGGER = LogManager.getLogger("proxy_dis");
    private static final Logger LBS_LOGGER = LogManager.getLogger("lbs");
    private static final Logger ORIENT_LOGGER = LogManager.getLogger("orient");


    private static final Logger PARALLEL_REQUEST_LOGGER = LogManager.getLogger("rtb_parallel_select");
    private static final Logger ADFLOW_LOGGER = LogManager.getLogger("rtb_adflow");
    private static final Logger PARALLEL_ADFLOW_LOGGER = LogManager.getLogger("rtb_parallel_adflow");
    private static final Logger USER_TAGS_LOGGER = LogManager.getLogger("user_tags");
    private static final Logger PROB_TRACKING_LOGGER = LogManager.getLogger("prob_tracking");
    private static final Logger STATISTIC_LOGGER = LogManager.getLogger("statistic");

    public static void main(String[] args) {
        for (int i = 0; i < 5 * 30; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ADIDLOGGER.info("test~");
            BINDINGLOGGER.info("test~");
            TAGSLOGGER.info("test~");
            ADCONTENTLOGGER.info("test~");
            IMG_REPLACE.info("test~");
            ADR_SELF_REQUEST.info("test~");
            TASK_NONAD.info("test~");
            FILT_LOGGER.info("test~");
            PROXY_DIS_LOGGER.info("test~");
            LBS_LOGGER.info("test~");
            ORIENT_LOGGER.info("test~");
            PARALLEL_REQUEST_LOGGER.info("test~");
            ADFLOW_LOGGER.info("test~");
            PARALLEL_ADFLOW_LOGGER.info("test~");
            USER_TAGS_LOGGER.info("test~");
            PROB_TRACKING_LOGGER.info("test~");
            STATISTIC_LOGGER.info("test~");
        }
    }
}
