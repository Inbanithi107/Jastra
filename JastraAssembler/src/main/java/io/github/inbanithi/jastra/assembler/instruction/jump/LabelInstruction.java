package io.github.inbanithi.jastra.assembler.instruction.jump;

import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;

public class LabelInstruction extends Instruction {

    private String label;

    public LabelInstruction(String label) {
        super(OpCode.SUB);
        this.label = label;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void writeTo(DataOutputStream dataOutputStream) throws IOException {

    }

    public String getLabel() {
        return label;
    }
}
