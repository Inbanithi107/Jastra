package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class SubtractInstruction extends Instruction {
    public SubtractInstruction() {
        super(OpCode.SUB);
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
    }
}
