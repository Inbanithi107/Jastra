package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.assembler.core.OpCode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ReturnInstruction extends Instruction {


    public ReturnInstruction() {
        super(OpCode.RETURN);
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
    }
}
