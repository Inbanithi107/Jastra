package io.github.inbanithi.jastra.assembler.instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Instruction {

    protected final byte opCode;

    protected Instruction(byte opCode) {
        this.opCode = opCode;
    }

    public abstract void writeTo(DataOutputStream out) throws IOException;

}
