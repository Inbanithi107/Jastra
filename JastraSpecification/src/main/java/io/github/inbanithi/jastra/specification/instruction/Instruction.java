package io.github.inbanithi.jastra.specification.instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Instruction {

    protected byte opCode;

    public Instruction(byte opCode){
        this.opCode = opCode;
    }

    public abstract void writeTo(DataOutputStream out) throws IOException;

}
