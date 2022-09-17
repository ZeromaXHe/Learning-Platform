package com.zerox.netty_in_action.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/17 17:56
 */
// 扩展 ByteToMessageDecoder 类，以将字节解码为特定的格式
public class ToIntegerDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 检查是否至少有 4 字节可读（一个 int 的字节长度）
        if (byteBuf.readableBytes() >= 4) {
            // 从入站 ByteBuf 中读取一个 int，并将其添加到解码消息的 List 中
            list.add(byteBuf.readInt());
        }
    }
}
