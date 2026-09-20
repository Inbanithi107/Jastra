package io.github.inbanithi.jastra.assembler.instruction;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class CallExternalInstruction extends Instruction {

    int moduleId;
    int functionId;
    int argCount;
    public CallExternalInstruction(int moduleId, int functionId, int argCount) {
        super(OpCode.CALL_EXTERNAL);
        this.moduleId = moduleId;
        this.functionId = functionId;
        this.argCount = argCount;
    }

    @Override
    public int getSize() {
        return 4;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
        out.write(moduleId);
        out.write(functionId);
        out.write(argCount);
    }
}
