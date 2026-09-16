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

    public static int JMP = 0x50;

    public static int JIZ = 0x51;

    public static int JNZ = 0x52;

    public static int CALL = 0x60;

    public static int RETURN_VOID = 0x61;

    public static int RETURN = 0x62;

    public static class Compare {
        public static int CMP_EQ = 0x30;

        public static int CMP_NE = 0x31;

        public static int CMP_LT = 0x32;

        public static int CMP_LE = 0x33;

        public static int CMP_GT = 0x34;

        public static int CMP_GE = 0x35;
    }

    public static class OperandStack {
        public static int PUSH = 0x12;

        public static int POP = 0x13;
    }

}
