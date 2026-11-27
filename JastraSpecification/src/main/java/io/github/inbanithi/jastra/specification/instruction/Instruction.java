package io.github.inbanithi.jastra.specification.instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Instruction {

    protected int opCode;

    protected int offset;

    public Instruction(int opCode){
        this.opCode = opCode;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }

    public abstract int getSize();

    public abstract void writeTo(DataOutputStream out) throws IOException;

}
