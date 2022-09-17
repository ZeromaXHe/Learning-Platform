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
 * @since 2022/9/17 17:24
 */
public class AbsIntegerEncoderTest {
    @Test
    public void testEncoded() {
        // 创建一个 ByteBuf，并且写入 9 个负整数
        ByteBuf buf = Unpooled.buffer();
        for (int i = 1; i < 10; i++) {
            buf.writeInt(i * -1);
        }

        // 创建一个 EmbeddedChannel 并安装一个要测试的 AbsIntegerEncoder
        EmbeddedChannel channel = new EmbeddedChannel(new AbsIntegerEncoder());
        // 写入 ByteBuf，并断言调用 readOutbound() 方法将会产生数据
        assertTrue(channel.writeOutbound(buf));
        // 将该 Channel 标记为已完成状态
        assertTrue(channel.finish());

        // read bytes
        for (int i = 1; i < 10; i++) {
            assertEquals(i, (int) channel.readOutbound());
        }
        assertNull(channel.readOutbound());
    }
}
