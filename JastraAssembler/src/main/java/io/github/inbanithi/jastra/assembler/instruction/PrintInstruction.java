package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.assembler.core.OpCode;

import java.io.DataOutputStream;
import java.io.IOException;

public class PrintInstruction extends Instruction {

    public PrintInstruction() {
        super(OpCode.PRINT);
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
    }
}
