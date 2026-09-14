package io.github.inbanithi.jastra.assembler.instruction.jump;

import io.github.inbanithi.jastra.assembler.core.Resolvable;
import io.github.inbanithi.jastra.specification.core.OpCode;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Map;

public class JumpIfZeroInstruction extends Instruction implements Resolvable {

    private String label;

    private int relativeOffset;

    public JumpIfZeroInstruction(String label) {
        super(OpCode.JIZ);
        this.label = label;
    }

    public void setOffset(int offset){
        this.offset = offset;
    }

    @Override
    public int getSize() {
        return 5;
    }

    @Override
    public void writeTo(DataOutputStream out) throws IOException {
        out.write(opCode);
        out.writeInt(relativeOffset);
    }

    public String getLabel() {
        return label;
    }

    @Override
    public void resolve(Map<String, Integer> symbols) {
        int target = symbols.get(label);
        int instructionEnd = offset+getSize();
        relativeOffset = target-instructionEnd;
    }
}
