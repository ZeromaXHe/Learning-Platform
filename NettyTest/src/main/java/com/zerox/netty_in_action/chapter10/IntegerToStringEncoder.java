package com.zerox.netty_in_action.chapter10;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/17 18:21
 */
public class IntegerToStringEncoder extends MessageToMessageEncoder<Integer> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Integer integer, List<Object> list) throws Exception {
        // 将 Integer 转换为 String，并将其添加到 List 中
        list.add(String.valueOf(integer));
    }
}
