package com.zerox.netty_in_action.chapter12;

import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

/**
 * @author zhuxi
 * @apiNote 为 ChannelPipeline 添加加密
 * @implNote
 * @since 2022/9/19 14:30
 */
public class SecureChatServerInitializer extends ChatServerInitializer {
    private final SslContext context;
    public SecureChatServerInitializer(ChannelGroup group, SslContext context) {
        super(group);
        this.context = context;
    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        super.initChannel(ch);
        SSLEngine engine = context.newEngine(ch.alloc());
        engine.setUseClientMode(false);
        // 将 SslHandler 添加到 ChannelPipeline 中
        ch.pipeline().addFirst(new SslHandler(engine));
    }
}
