package com.zerox.netty_in_action.chapter9;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author zhuxi
 * @apiNote
 * @implNote
 * @since 2022/9/17 17:06
 */
public class FixedLengthFrameDecoderTest {
    // 使用了注解 @Test 标注，因此 JUnit 将会执行该方法
    @Test
    // 第一个测试方法：testFramesDecoded()
    public void testFramesDecoded() {
        // 创建一个 ByteBuf 并存储 9 字节
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        // 创建一个 EmbeddedChannel，并添加一个 FixedLengthFrameDecoder，其将以 3 字节的帧长度被测试
        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // write bytes
        // 将数据写入 EmbeddedChannel
        assertTrue(channel.writeInbound(input.retain()));
        // 标记 Channel 为已完成状态
        assertTrue(channel.finish());

        // read messages
        // 读取所生成的消息，并且验证是否有 3 帧（切片），其中每帧（切片）都为 3 字节
        ByteBuf read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();
    }

    @Test
    // 第二个测试方法：testFramesDecoded2()
    public void testFramesDecoded2() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();

        EmbeddedChannel channel = new EmbeddedChannel(new FixedLengthFrameDecoder(3));
        // 返回 false，因为没有一个完整的可供读取的帧
        // 如果对 readInbound() 的后续调用将会返回数据，那么 writeInbound() 方法将会返回 true。
        // 但是只有当有 3 个或者更多的字节可供读取时，FixedLengthFrameDecoder 才会产生输出。
        assertFalse(channel.writeInbound(input.readBytes(2)));
        assertTrue(channel.writeInbound(input.readBytes(7)));

        assertTrue(channel.finish());
        ByteBuf read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        read = (ByteBuf) channel.readInbound();
        assertEquals(buf.readSlice(3), read);
        read.release();

        assertNull(channel.readInbound());
        buf.release();
    }
}
