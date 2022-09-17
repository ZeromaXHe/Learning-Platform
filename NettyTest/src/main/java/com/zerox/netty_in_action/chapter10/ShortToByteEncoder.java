package com.zerox.netty_in_action.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/17 18:19
 */
public class ShortToByteEncoder extends MessageToByteEncoder<Short> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Short aShort, ByteBuf byteBuf) throws Exception {
        byteBuf.writeShort(aShort);
    }
}
