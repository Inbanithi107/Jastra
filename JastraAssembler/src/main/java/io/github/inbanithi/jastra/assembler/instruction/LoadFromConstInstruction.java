package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class LoadFromConstInstruction extends Instruction {

    private final int id;
    public LoadFromConstInstruction(int id) {
        super(OpCode.LOAD_FROM_CONST);
        this.id = id;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
        out.write(id);
    }
}
