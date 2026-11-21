package io.github.inbanithi.jastra.assembler.instruction.array;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class LoadArrayValueInstruction extends Instruction {
    public LoadArrayValueInstruction() {
        super(OpCode.Array.LOAD_VALUE);
    }

    @Override
    public int getSize() {
        return 1;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
    }
}
