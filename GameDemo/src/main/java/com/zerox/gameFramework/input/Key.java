package com.zerox.gameFramework.input;

import javafx.scene.input.KeyCode;

/**
 * @Author: ZeromaXHe
 * @Time: 2021/6/21 22:52
 * @Description: 参考 B站 小白猴LWM【JavaFX游戏框架开发】第05期-键盘输入\
 * https://www.bilibili.com/video/BV1gb411a74e
 * @Modified By: ZeromaXHe
 */
public enum Key {
    // Numbers
    NUM0(KeyCode.DIGIT0, "0"),
    NUM1(KeyCode.DIGIT1, "1"),
    NUM2(KeyCode.DIGIT2, "2"),
    NUM3(KeyCode.DIGIT3, "3"),
    NUM4(KeyCode.DIGIT4, "4"),
    NUM5(KeyCode.DIGIT5, "5"),
    NUM6(KeyCode.DIGIT6, "6"),
    NUM7(KeyCode.DIGIT7, "7"),
    NUM8(KeyCode.DIGIT8, "8"),
    NUM9(KeyCode.DIGIT9, "9"),

    // Letters
    A(KeyCode.A, "A"),
    B(KeyCode.B, "B"),
    C(KeyCode.C, "C"),
    D(KeyCode.D, "D"),
    E(KeyCode.E, "E"),
    F(KeyCode.F, "F"),
    G(KeyCode.G, "G"),
    H(KeyCode.H, "H"),
    I(KeyCode.I, "I"),
    J(KeyCode.J, "J"),
    K(KeyCode.K, "K"),
    L(KeyCode.L, "L"),
    M(KeyCode.M, "M"),
    N(KeyCode.N, "N"),
    O(KeyCode.O, "O"),
    P(KeyCode.P, "P"),
    Q(KeyCode.Q, "Q"),
    R(KeyCode.R, "R"),
    S(KeyCode.S, "S"),
    T(KeyCode.T, "T"),
    U(KeyCode.U, "U"),
    V(KeyCode.V, "V"),
    W(KeyCode.W, "W"),
    X(KeyCode.X, "X"),
    Y(KeyCode.Y, "Y"),
    Z(KeyCode.Z, "Z"),

    // Functions
    F1(KeyCode.F1, "F1"),
    F2(KeyCode.F2, "F2"),
    F3(KeyCode.F3, "F3"),
    F4(KeyCode.F4, "F4"),
    F5(KeyCode.F5, "F5"),
    F6(KeyCode.F6, "F6"),
    F7(KeyCode.F7, "F7"),
    F8(KeyCode.F8, "F8"),
    F9(KeyCode.F9, "F9"),
    F10(KeyCode.F10, "F10"),
    F11(KeyCode.F11, "F11"),
    F12(KeyCode.F12, "F12"),

    // White Spaces
    SPACE(KeyCode.SPACE, " "),
    ENTER(KeyCode.ENTER, "\n"),
    TAB(KeyCode.TAB, "\t"),

    // Marks
    WAVE(null, "~"),
    EXCLAMATION(KeyCode.EXCLAMATION_MARK, "!"),
    AT(KeyCode.AT, "@"),
    SHARP(KeyCode.NUMBER_SIGN, "#"),
    DOLLAR(KeyCode.DOLLAR, "$"),
    PERCENT(null, "%"),
    CIRCUMFLEX(KeyCode.CIRCUMFLEX, "^"),
    AND(KeyCode.AMPERSAND, "&"),
    OR(null, "|"),
    STAR(KeyCode.ASTERISK, "*"),

    PLUS(KeyCode.PLUS, "+"),
    MINUS(KeyCode.MINUS, "-"),
    EQUALS(KeyCode.EQUALS, "="),
    UNDERSCORE(null, "_"),

    OPEN_PARENTHESIS(KeyCode.LEFT_PARENTHESIS, "("),
    CLOSE_PARENTHESIS(KeyCode.RIGHT_PARENTHESIS, ")"),
    OPEN_BRACKET(KeyCode.OPEN_BRACKET, "["),
    CLOSE_BRACKET(KeyCode.CLOSE_BRACKET, "]"),
    OPEN_BRACE(KeyCode.BRACELEFT, "{"),
    CLOSE_BRACE(KeyCode.BRACERIGHT, "}"),

    COLON(KeyCode.COLON, ":"),
    SEMICOLON(KeyCode.SEMICOLON, ";"),

    QUOTE(KeyCode.QUOTE, "'"),
    DBL_QUOTE(KeyCode.QUOTEDBL, "\""),
    BACK_QUOTE(KeyCode.BACK_QUOTE, "`"),

    COMMA(KeyCode.COMMA, ","),
    PERIOD(KeyCode.PERIOD, "."),

    SLASH(KeyCode.SLASH, "/"),
    BACKSLASH(KeyCode.BACK_SLASH, "\\"),

    LESS(KeyCode.LESS, "<"),
    GREATER(KeyCode.GREATER, ">"),

    QUESTION(null, "?"),

    // Modifiers
    CONTROL(KeyCode.CONTROL, "CTRL"),
    SHIFT(KeyCode.SHIFT, "SHIFT"),
    ALT(KeyCode.ALT, "ALT"),

    // Contexts
    WINDOWS(KeyCode.WINDOWS, "WIN"),
    COMMAND(KeyCode.COMMAND, "CMD"),
    MENU(KeyCode.CONTEXT_MENU, "MENU"),

    // Editors
    BACK_SPACE(KeyCode.BACK_SPACE, "BACK_SPACE"),
    INSERT(KeyCode.INSERT, "INSERT"),
    DELETE(KeyCode.DELETE, "DELETE"),
    HOME(KeyCode.HOME, "HOME"),
    END(KeyCode.END, "END"),
    PAGE_UP(KeyCode.PAGE_UP, "PAGE_UP"),
    PAGE_DOWN(KeyCode.PAGE_DOWN, "PAGE_DOWN"),
    PAUSE(KeyCode.PAUSE, "PAUSE"),
    PRINTSCREEN(KeyCode.PRINTSCREEN, "PRINTSCREEN"),

    // Arrows
    UP(KeyCode.UP, "UP"),
    DOWN(KeyCode.DOWN, "DOWN"),
    LEFT(KeyCode.LEFT, "LEFT"),
    RIGHT(KeyCode.RIGHT, "RIGHT"),

    // Locks
    CAPS_LOCK(KeyCode.CAPS, "CAPS_LOCK"),
    NUM_LOCK(KeyCode.NUM_LOCK, "NUM_LOCK"),
    SCROLL_LOCK(KeyCode.SCROLL_LOCK, "SCROLL_LOCK"),

    // NUMPAD Numbers
    NUMPAD0(KeyCode.NUMPAD0, "0"),
    NUMPAD1(KeyCode.NUMPAD1, "1"),
    NUMPAD2(KeyCode.NUMPAD2, "2"),
    NUMPAD3(KeyCode.NUMPAD3, "3"),
    NUMPAD4(KeyCode.NUMPAD4, "4"),
    NUMPAD5(KeyCode.NUMPAD5, "5"),
    NUMPAD6(KeyCode.NUMPAD6, "6"),
    NUMPAD7(KeyCode.NUMPAD7, "7"),
    NUMPAD8(KeyCode.NUMPAD8, "8"),
    NUMPAD9(KeyCode.NUMPAD9, "9"),

    // NUMPAD Marks
    NUMPAD_PLUS(KeyCode.ADD, "+"),
    NUMPAD_MINUS(KeyCode.MINUS, "-"),
    NUMPAD_MULTIPLY(KeyCode.MULTIPLY, "*"),
    NUMPAD_DIVIDE(KeyCode.DIVIDE, "/"),
    NUMPAD_DECIMAL(KeyCode.DECIMAL, "."),

    // NUMPAD Arrows
    NUMPAD_UP(KeyCode.KP_UP, "UP"),
    NUMPAD_DOWN(KeyCode.KP_DOWN, "DOWN"),
    NUMPAD_LEFT(KeyCode.KP_LEFT, "LEFT"),
    NUMPAD_RIGHT(KeyCode.KP_RIGHT, "RIGHT"),

    // ESCAPE
    ESCAPE(KeyCode.ESCAPE, "ESC")
    ;

    private final KeyCode code;
    private final String text;

    Key(KeyCode code, String text) {
        this.code = code;
        this.text = text;
    }

    public KeyCode getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public static Key find(KeyCode code) {
        for (Key k : values()) {
            if(k.code != null && k.code == code) {
                return k;
            }
        }
        return null;
    }

    public static Key find(String text) {
        for (Key k : values()) {
            if(k.text != null && k.text.equalsIgnoreCase(text)) {
                return k;
            }
        }
        return null;
    }
}
