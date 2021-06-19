package com.zerox.utilities;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Objects;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/20 0:03
 * @Description: 学习 汪文君Google Guava 第03讲
 * @Modified By: ZeromaXHe
 */
public class PreconditionsTest {
    @Test(expected = NullPointerException.class)
    public void testCheckNotNull() {
        checkNotNull(null);
    }

    @Test
    public void testCheckNotNullWithMessage() {
        try {
            checkNotNullWithMessage(null);
        } catch (Exception e) {
            //Assert.assertThat(e, Is.isA(NullPointerException.class));报红
            Assert.assertThat((NullPointerException) e, Is.isA(NullPointerException.class));
            Assert.assertThat(e.getMessage(), IsEqual.equalTo("The list should not be null"));
        }
    }

    @Test
    public void testCheckNotNullWithFormatMessage() {
        try {
            checkNotNullWithFormatMessage(null);
        } catch (Exception e) {
            //Assert.assertThat(e, Is.isA(NullPointerException.class));报红
            Assert.assertThat((NullPointerException) e, Is.isA(NullPointerException.class));
            Assert.assertThat(e.getMessage(), IsEqual.equalTo("The list should not be null and the size must be 2"));
        }
    }

    @Test
    public void testCheckArguments() {
        final String type = "A";
        try {
            Preconditions.checkArgument(type.equals("B"));
            Assert.fail("should not process to here");
        } catch (Exception e) {
            Assert.assertThat((IllegalArgumentException) e, Is.isA(IllegalArgumentException.class));
        }
    }

    @Test
    public void testCheckState() {
        try {
            final String state = "A";
            Preconditions.checkState(state.equals("B"), "The state is illegal");
            Assert.fail("should not process to here");
        } catch (Exception e) {
            Assert.assertThat((IllegalStateException) e, Is.isA(IllegalStateException.class));
        }
    }

    @Test
    public void testCheckIndex() {
        try {
            List<String> list = ImmutableList.of();
            Preconditions.checkElementIndex(10, list.size());
        } catch (Exception e) {
            Assert.assertThat((IndexOutOfBoundsException) e, Is.isA(IndexOutOfBoundsException.class));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testByObjects() {
        Objects.requireNonNull(null);
    }

    @Test(expected = AssertionError.class)
    public void testAssert() {
        List<String> list = null;
        assert list != null;
    }

    @Test
    public void testAssertWithMessage() {
        try {
            List<String> list = null;
            assert list != null : "The list should not be null.";
        } catch (Error e) {
            Assert.assertThat((AssertionError) e, Is.isA(AssertionError.class));
            Assert.assertThat(e.getMessage(), IsEqual.equalTo("The list should not be null."));
        }
    }

    private void checkNotNull(final List<String> list) {
        Preconditions.checkNotNull(list);
    }

    private void checkNotNullWithMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "The list should not be null");
    }

    private void checkNotNullWithFormatMessage(final List<String> list) {
        Preconditions.checkNotNull(list, "The list should not be null and the size must be %s", 2);
    }
}
