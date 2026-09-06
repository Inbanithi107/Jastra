package io.github.inbanithi.jastra.specification.core;

public final class OpCode {

    public static byte STORE = 0x01;

    public static byte LOAD = 0x10;

    public static byte ADD = 0x20;

    public static byte PRINT = (byte) 0xA0;

    public static byte CALL = 0x60;

    public static byte RETURN_VOID = 0x61;

    public static byte RETURN = 0x62;

}
