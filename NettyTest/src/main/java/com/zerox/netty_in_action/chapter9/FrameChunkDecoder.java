package com.zerox.netty_in_action.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/17 17:30
 */
// 扩展 ByteToMessageDecoder 以将入站字节解码为消息
public class FrameChunkDecoder extends ByteToMessageDecoder {
    private final int maxFrameSize;

    public FrameChunkDecoder(int maxFrameSize) {
        // 指定将要产生的帧的最大允许大小
        this.maxFrameSize = maxFrameSize;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        int readableBytes = byteBuf.readableBytes();
        if (readableBytes > maxFrameSize) {
            // discard the bytes
            // 如果该帧太大，则丢弃它并抛出一个 TooLongFrameException
            byteBuf.clear();
            throw new TooLongFrameException();
        }
        // 否则，从 ByteBuf 中读取一个新的帧
        ByteBuf buf = byteBuf.readBytes(readableBytes);
        // 将该帧添加到解码消息的 List 中
        list.add(buf);
    }
}
