package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class ReturnVoidInstruction extends Instruction {

    public ReturnVoidInstruction(){
        super(OpCode.RETURN_VOID);
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
