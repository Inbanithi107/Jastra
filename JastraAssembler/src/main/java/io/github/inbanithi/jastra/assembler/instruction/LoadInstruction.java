package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.assembler.core.OpCode;

import java.io.DataOutputStream;
import java.io.IOException;

public class LoadInstruction extends Instruction {

    private final int register;

    public LoadInstruction(int register) {
        super(OpCode.LOAD);
        this.register = register;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.writeByte(opCode);
        out.writeByte(register);
    }
}
