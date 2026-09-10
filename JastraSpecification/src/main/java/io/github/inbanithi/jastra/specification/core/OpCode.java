package io.github.inbanithi.jastra.specification.core;

public final class OpCode {

    public static int STORE = 0x01;

    public static int POP_STORE_REG = 0x02;

    public static int LOAD = 0x10;

    public static int LOAD_FROM_CONST = 0x11;

    public static int ADD = 0x20;

    public static int SUB = 0x21;

    public static int MUL = 0x22;

    public static int DIV = 0x23;

    public static int PRINT =  0xA0;

    public static int CALL = 0x60;

    public static int RETURN_VOID = 0x61;

    public static int RETURN = 0x62;

}
