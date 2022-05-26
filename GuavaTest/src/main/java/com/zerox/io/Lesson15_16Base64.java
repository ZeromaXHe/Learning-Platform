package com.zerox.io;

import com.google.common.base.Preconditions;

/**
 * @Author: zhuxi
 * @Time: 2022/5/26 11:21
 * @Description:
 * @ModifiedBy: zhuxi
 */
public final class Lesson15_16Base64 {
    private final static String CODE_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    private final static char[] CODE_DICT = CODE_STRING.toCharArray();

    private Lesson15_16Base64() {
    }

    public static String encode(String plain) {
        Preconditions.checkNotNull(plain);
        StringBuilder result = new StringBuilder();
        // 将字符串转换为二进制
        StringBuilder binaryStringBuilder = toBinary(plain);
        // 不足位的补零
        int delta = 6 - binaryStringBuilder.length() % 6;
        for (int i = 0; i < delta && delta != 6; i++) {
            binaryStringBuilder.append("0");
        }
        // 将二进制按照每 6 位编码
        for (int index = 0; index < binaryStringBuilder.length() / 6; index++) {
            int begin = index * 6;
            String encodingString = binaryStringBuilder.substring(begin, begin + 6);
            char encodeChar = CODE_DICT[Integer.valueOf(encodingString, 2)];
            result.append(encodeChar);
        }

        // 最后补等号
        if (delta != 6) {
            for (int i = 0; i < delta / 2; i++) {
                result.append('=');
            }
        }

        return result.toString();
    }

    private static StringBuilder toBinary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            String charBin = Integer.toBinaryString(source.charAt(index));
            int delta = 8 - charBin.length();
            for (int d = 0; d < delta; d++) {
                binaryResult.append('0');
            }
            binaryResult.append(charBin);
        }
        return binaryResult;
    }

    public static String decode(String encrypt) {
        StringBuilder resultBuilder = new StringBuilder();
        String temp = encrypt;
        int equalIndex = temp.indexOf("=");
        if(equalIndex != -1){
            temp = temp.substring(0, equalIndex);
        }
        final StringBuilder base64Binary = toBase64Binary(temp);
        for (int i = 0; i < base64Binary.length() / 8; i++) {
            int begin = i*8;
            String str = base64Binary.substring(begin, begin + 8);
            final char c = (char)Integer.valueOf(str, 2).intValue();
//            final char c = Character.toChars(Integer.valueOf(str, 2))[0];
            resultBuilder.append(c);
        }
        return resultBuilder.toString();
    }

    private static StringBuilder toBase64Binary(final String source) {
        final StringBuilder binaryResult = new StringBuilder();
        for (int index = 0; index < source.length(); index++) {
            int i = CODE_STRING.indexOf(source.charAt(index));
            String charBin = Integer.toBinaryString(i);
            int delta = 6 - charBin.length();
            for (int d = 0; d < delta; d++) {
                binaryResult.append('0');
            }
            binaryResult.append(charBin);
        }
        return binaryResult;
    }
}
