package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class ReturnInstruction extends Instruction {

    private final int count;
    public ReturnInstruction(int count) {
        super(OpCode.RETURN);
        this.count = count;
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
        out.write(count);
    }
}
