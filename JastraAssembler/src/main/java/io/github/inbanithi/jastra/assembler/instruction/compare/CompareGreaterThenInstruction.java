package io.github.inbanithi.jastra.assembler.instruction.compare;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class CompareGreaterThenInstruction extends Instruction {
    public CompareGreaterThenInstruction() {
        super(OpCode.Compare.CMP_GT);
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
