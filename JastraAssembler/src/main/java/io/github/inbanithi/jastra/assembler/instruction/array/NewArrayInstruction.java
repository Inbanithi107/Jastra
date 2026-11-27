package io.github.inbanithi.jastra.assembler.instruction.array;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.core.Type;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class NewArrayInstruction extends Instruction {

    private Type type;

    public NewArrayInstruction(Type type) {
        super(OpCode.Array.NEW_ARRAY);
        this.type = type;
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
        out.writeByte(type.getCode());
    }
}
