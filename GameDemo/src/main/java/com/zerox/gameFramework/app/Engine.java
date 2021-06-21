package com.zerox.gameFramework.app;

import javafx.animation.AnimationTimer;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 22:17
 * @Description: 参考 B站 小白猴LWM JavaFX游戏框架开发 第03期 游戏主循环和帧更新
 * https://www.bilibili.com/video/BV1Pb411C76Q/
 * @Modified By: ZeromaXHe
 */
public class Engine extends AnimationTimer {
    private double nowNanos;
    private double lastNanos;
    private double deltaNanos;

    // Frames Per Second
    private double fps;
    // Nanos Per Frame
    private double npf;

    OnStart onStart;
    OnUpdate onUpdate;
    OnStop onStop;

    public Engine() {
        this(60);
    }

    public Engine(double fps) {
        setFps(fps);
    }

    public double getNowNanos() {
        return nowNanos;
    }

    public double getNowMillis() {
        return nowNanos * 1E-6;
    }

    public double getNowSec() {
        return nowNanos * 1E-9;
    }

    public double getLastNanos() {
        return lastNanos;
    }

    public double getLastMillis() {
        return lastNanos * 1E-6;
    }

    public double getLastSec() {
        return lastNanos * 1E-9;
    }

    public double getDeltaNanos() {
        return deltaNanos;
    }

    public double getDeltaMillis() {
        return deltaNanos * 1E-6;
    }

    public double getDeltaSec() {
        return deltaNanos * 1E-9;
    }

    public double getFps() {
        return fps;
    }

    public void setFps(double fps) {
        this.fps = fps;
        this.npf = 1E9 / fps;
    }

    @Override
    public void start() {
        this.reset();
        super.start();

        if (onStart != null) {
            onStart.handle();
        }
    }

    @Override
    public void handle(long now) {
        nowNanos = now;
        if (lastNanos > 0) {
            deltaNanos += nowNanos - lastNanos;
        }
        lastNanos = nowNanos;

        if (deltaNanos >= npf) {
            if (onUpdate != null) {
                onUpdate.handle(deltaNanos);
            }

            deltaNanos -= npf;
        }
    }

    @Override
    public void stop() {
        if (onStop != null) {
            onStop.handle();
        }

        super.stop();
        this.reset();
    }

    public void reset() {
        nowNanos = 0;
        lastNanos = 0;
        deltaNanos = 0;
    }

    interface OnStart {
        void handle();
    }

    interface OnUpdate {
        void handle(double time);
    }

    interface OnStop {
        void handle();
    }
}
