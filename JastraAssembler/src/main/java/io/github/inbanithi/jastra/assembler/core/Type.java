package io.github.inbanithi.jastra.assembler.core;

public enum Type {

    INT(0x80),
    LONG(0x81),
    FLOAT(0x82),
    DOUBLE(0x83),
    BOOLEAN(0x84),
    CHARACTER(0x85),
    STRING(0x86),
    NULL(0x87);

    private final int code;

    Type(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
