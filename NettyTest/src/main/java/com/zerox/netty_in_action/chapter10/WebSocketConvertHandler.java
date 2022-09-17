package com.zerox.netty_in_action.chapter10;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.ContinuationWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.util.List;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/17 18:24
 */
public class WebSocketConvertHandler extends MessageToMessageCodec<WebSocketFrame, WebSocketConvertHandler.MyWebSocketFrame> {
    @Override
    // 将 MyWebSocketFrame 编码为指定的 WebSocketFrame 子类型
    protected void encode(ChannelHandlerContext channelHandlerContext, MyWebSocketFrame myWebSocketFrame, List<Object> list) throws Exception {
        ByteBuf payload = myWebSocketFrame.getData().duplicate().retain();
        // 实例化一个指定子类型的 WebSocketFrame
        switch (myWebSocketFrame.getType()) {
            case BINARY:
                list.add(new BinaryWebSocketFrame(payload));
                break;
            case TEXT:
                list.add(new TextWebSocketFrame(payload));
                break;
            case CLOSE:
                list.add(new CloseWebSocketFrame(true, 0, payload));
                break;
            case CONTINUATION:
                list.add(new ContinuationWebSocketFrame(payload));
                break;
            case PONG:
                list.add(new PongWebSocketFrame(payload));
                break;
            case PING:
                list.add(new PingWebSocketFrame(payload));
                break;
            default:
                throw new IllegalStateException("Unsupported websocket msg " + myWebSocketFrame);
        }
    }

    @Override
    // 将 WebSocketFrame 解码为 MyWebSocketFrame，并设置 FrameType
    protected void decode(ChannelHandlerContext channelHandlerContext, WebSocketFrame webSocketFrame, List<Object> list) throws Exception {
        ByteBuf payload = webSocketFrame.content().duplicate().retain();
        if (webSocketFrame instanceof BinaryWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.BINARY, payload));
        } else if (webSocketFrame instanceof CloseWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.CLOSE, payload));
        } else if (webSocketFrame instanceof PingWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.PING, payload));
        } else if (webSocketFrame instanceof PongWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.PONG, payload));
        } else if (webSocketFrame instanceof TextWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.TEXT, payload));
        } else if (webSocketFrame instanceof ContinuationWebSocketFrame) {
            list.add(new MyWebSocketFrame(MyWebSocketFrame.FrameType.CONTINUATION, payload));
        } else {
            throw new IllegalStateException("Unsupported websocket msg " + webSocketFrame);
        }
    }

    public static final class MyWebSocketFrame {
        public enum FrameType {
            BINARY,
            CLOSE,
            PING,
            PONG,
            TEXT,
            CONTINUATION,
        }

        private final FrameType type;
        private final ByteBuf data;

        public MyWebSocketFrame(FrameType type, ByteBuf data) {
            this.type = type;
            this.data = data;
        }

        public FrameType getType() {
            return type;
        }

        public ByteBuf getData() {
            return data;
        }
    }
}
