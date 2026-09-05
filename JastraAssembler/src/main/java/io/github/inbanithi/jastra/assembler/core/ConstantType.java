package io.github.inbanithi.jastra.assembler.core;

public enum ConstantType {

    INTEGER(1),
    STRING(2),
    FLOAT(3);

    private final int tag;

    ConstantType(int tag){
        this.tag = tag;
    }

    public int getTag() {
        return tag;
    }
}
