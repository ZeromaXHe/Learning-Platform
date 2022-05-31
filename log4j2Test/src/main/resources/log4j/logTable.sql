CREATE TABLE `log`
(
    `log_id`       int(11) NOT NULL AUTO_INCREMENT,
    `project_name` varchar(255)  DEFAULT NULL COMMENT '项目名',
    `create_date`  varchar(255)  DEFAULT NULL COMMENT '创建时间',
    `level`        varchar(255)  DEFAULT NULL COMMENT '优先级',
    `category`     varchar(255)  DEFAULT NULL COMMENT '所在类的全名',
    `file_name`    varchar(255)  DEFAULT NULL COMMENT '输出日志消息产生时所在的文件名称',
    `thread_name`  varchar(255)  DEFAULT NULL COMMENT '日志事件的线程名',
    `line`         varchar(255)  DEFAULT NULL COMMENT '行号',
    `all_category` varchar(255)  DEFAULT NULL COMMENT '日志事件的发生位置',
    `message`      varchar(4000) DEFAULT NULL COMMENT '输出代码中指定的消息',
    PRIMARY KEY (`log_id`)
);
# INSERT INTO `log`
# (project_name, create_date, level, category, file_name, thread_name, line, all_category, message)
# values ('zerox', '%d{yyyy-MM-dd HH:mm:ss}', '%p', '%c', '%F', '%t', '%L', '%l', '%m');