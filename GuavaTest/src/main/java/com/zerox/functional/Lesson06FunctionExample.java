package com.zerox.functional;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Preconditions;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/20 12:52
 * @Description: 学习 汪文君Google Guava 第06讲
 * @Modified By: ZeromaXHe
 */
public class Lesson06FunctionExample {
    public static void main(String[] args) throws IOException {
        Function<String, Integer> function = new Function<String, Integer>() {

            @Nullable
            @Override
            public Integer apply(@Nullable String s) {
                Preconditions.checkNotNull(s, "The input Stream should not be null");
                return s.length();
            }
        };

        System.out.println(function.apply("Hello"));

        process("Hello", new Handler.LengthDoubleHandler());
        System.out.println(Functions.toStringFunction().apply(new ServerSocket(8888)));

        Function<String, Double> compose = Functions.compose(new Function<Integer, Double>() {

            @Nullable
            @Override
            public Double apply(@Nullable Integer input) {
                return input * 1.0;
            }
        }, new Function<String, Integer>() {
            @Nullable
            @Override
            public Integer apply(@Nullable String input) {
                return input.length();
            }
        });

        System.out.println(compose.apply("Hello"));
    }

    interface Handler<IN, OUT> {
        OUT handle(IN input);

        class LengthDoubleHandler implements Handler<String, Integer> {

            @Override
            public Integer handle(String input) {
                return input.length() * 2;
            }
        }
    }

    private static void process(String text, Handler<String, Integer> handler) {
        System.out.println(handler.handle(text));
    }
}
