package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class DivisionInstruction extends Instruction {
    public DivisionInstruction() {
        super(OpCode.DIV);
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
    }
}
