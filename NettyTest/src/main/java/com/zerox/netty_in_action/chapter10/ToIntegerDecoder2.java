package com.zerox.netty_in_action.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/17 17:59
 */
// 扩展 ReplayingDecoder<Void> 以将字节解码为消息
public class ToIntegerDecoder2 extends ReplayingDecoder<Void> {
    @Override
    // 传入的 ByteBuf 是 ReplayingDecoderByteBuf
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 从入站 ByteBuf 中读取一个 int，并将其添加到解码消息的 List 中
        list.add(byteBuf.readInt());
    }
}
