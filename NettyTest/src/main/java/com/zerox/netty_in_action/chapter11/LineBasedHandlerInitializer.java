package com.zerox.netty_in_action.chapter11;

import io.netty.buffer.ByteBuf;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.LineBasedFrameDecoder;

/**
 * @author zhuxi
 * @apiNote 处理由行尾符分隔的帧
 * @implNote
 * @since 2022/9/19 10:54
 */
public class LineBasedHandlerInitializer extends ChannelInitializer<Channel> {
    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 该 LineBasedFrameDecoder 将提取的帧转发给下一个 ChannelInboundHandler
        pipeline.addLast(new LineBasedFrameDecoder(64*1024));
        // 添加 FrameHandler 以接收帧
        pipeline.addLast(new FrameHandler());
    }

    private static final class FrameHandler extends SimpleChannelInboundHandler<ByteBuf> {
        // 传入了单个帧的内容
        @Override
        protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
            // Do something with the data extracted from the frame
        }
    }
}
