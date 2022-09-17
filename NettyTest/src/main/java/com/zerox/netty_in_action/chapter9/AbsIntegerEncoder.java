package com.zerox.netty_in_action.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/17 17:20
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        // 检查是否有足够的字节用来编码
        while(byteBuf.readableBytes() >= 4) {
            // 从输入的 ByteBuf 中读取下一个整数，并且计算其绝对值
            int value = Math.abs(byteBuf.readInt());
            // 将该整数写入到编码消息的 List 中
            list.add(value);
        }
    }
}
