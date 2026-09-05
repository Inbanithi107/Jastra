package io.github.inbanithi.jastra.assembler.function;

import io.github.inbanithi.jastra.assembler.instruction.Instruction;

import java.util.List;

public class JastraFunction {

    private int id;

    private final String name;

    private final int argCount;

    private final List<Instruction> instructions;

    private int constantNameIndex;

    public JastraFunction(String name, int argCount, List<Instruction> instructions, int index){
        this.name = name;
        this.argCount = argCount;
        this.instructions = instructions;
        this.constantNameIndex = index;
    }

    public String getName() {
        return name;
    }

    public int getArgCount() {
        return argCount;
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public int getId() {
        return id;
    }

    public int getConstantNameIndex() {
        return constantNameIndex;
    }
}
