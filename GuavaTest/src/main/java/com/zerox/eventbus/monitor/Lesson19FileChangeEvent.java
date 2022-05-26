package com.zerox.eventbus.monitor;

import java.nio.file.Path;
import java.nio.file.WatchEvent;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 16:23
 * @Description:
 * @ModifiedBy: zhuxi
 */
public class Lesson19FileChangeEvent {
    private final Path path;
    private final WatchEvent.Kind<?> kind;

    public Lesson19FileChangeEvent(Path path, WatchEvent.Kind<?> kind) {
        this.path = path;
        this.kind = kind;
    }

    public Path getPath() {
        return path;
    }

    public WatchEvent.Kind<?> getKind() {
        return kind;
    }
}
