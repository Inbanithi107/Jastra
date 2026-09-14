package io.github.inbanithi.jastra.assembler.instruction.compare;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class CompareLessThenOrEqualInstruction extends Instruction {
    public CompareLessThenOrEqualInstruction() {
        super(OpCode.Compare.CMP_LE);
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
