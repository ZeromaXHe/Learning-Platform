package com.zerox.netty_in_action.chapter13;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/19 15:10
 */
public class LogEventHandler extends SimpleChannelInboundHandler<LogEvent> {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, LogEvent msg) throws Exception {
        StringBuilder builder = new StringBuilder();
        builder.append(msg.getReceivedTimestamp())
                .append(" [")
                .append(msg.getSource().toString())
                .append("] [")
                .append(msg.getLogfile())
                .append("] : ")
                .append(msg.getMsg());
        System.out.println(builder);
    }
}
