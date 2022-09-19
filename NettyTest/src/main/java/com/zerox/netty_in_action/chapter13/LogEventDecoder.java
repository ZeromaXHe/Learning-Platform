package com.zerox.netty_in_action.chapter13;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.socket.DatagramPacket;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/19 15:04
 */
public class LogEventDecoder extends MessageToMessageDecoder<DatagramPacket> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, DatagramPacket datagramPacket, List<Object> list) throws Exception {
        // 获取对 DatagramPacket 中的数据（ByteBuf）的引用
        ByteBuf data = datagramPacket.content();
        // 获取该 SEPARATOR 的索引
        int idx = data.indexOf(0, data.readableBytes(), LogEvent.SEPARATOR);
        // 提取文件名
        String filename = data.slice(0, idx).toString(CharsetUtil.UTF_8);
        // 提取日志消息
        String logMsg = data.slice(idx+1, data.readableBytes()).toString(CharsetUtil.UTF_8);
        LogEvent event = new LogEvent(datagramPacket.sender(), filename, logMsg, System.currentTimeMillis());
        list.add(event);
    }
}
