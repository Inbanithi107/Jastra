package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class CallInstruction extends Instruction {

    private final int id;

    private final int argCount;

    public CallInstruction(int id, int argCount) {
        super(OpCode.CALL);
        this.id = id;
        this.argCount = argCount;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
        out.write(id);
        out.write(argCount);
    }
}
