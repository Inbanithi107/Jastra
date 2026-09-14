package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class PopStoreRegisterInstruction extends Instruction {

    private int register;

    public PopStoreRegisterInstruction(int register) {
        super(OpCode.POP_STORE_REG);
        this.register = register;
    }

    @Override
    public int getSize() {
        return 2;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
        out.write(register);
    }
}
