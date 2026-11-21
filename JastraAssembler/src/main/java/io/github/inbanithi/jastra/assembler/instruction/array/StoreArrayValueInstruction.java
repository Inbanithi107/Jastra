package io.github.inbanithi.jastra.assembler.instruction.array;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class StoreArrayValueInstruction extends Instruction {
    public StoreArrayValueInstruction() {
        super(OpCode.Array.STORE_VALUE);
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
