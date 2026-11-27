package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class LoadInstruction extends Instruction {

    private final int register;

    public LoadInstruction(int register) {
        super(OpCode.LOAD);
        this.register = register;
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.writeByte(opCode);
        out.writeByte(register);
    }
}
