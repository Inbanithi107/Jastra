package io.github.inbanithi.jastra.assembler.instruction.compare;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class CompareLessThenInstruction extends Instruction {
    public CompareLessThenInstruction() {
        super(OpCode.Compare.CMP_LT);
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
