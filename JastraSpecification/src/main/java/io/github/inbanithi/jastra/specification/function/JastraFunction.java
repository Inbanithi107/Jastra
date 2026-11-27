package io.github.inbanithi.jastra.specification.function;

import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.util.List;

public class JastraFunction {

    private final int id;

    private final String name;

    private final int argCount;

    private final List<Instruction> instructions;

    private final int codeOffset;

    private final int codeLength;

    private final byte[] code;

    public JastraFunction(String name, int argCount, List<Instruction> instructions, int index){
        this.id = index;
        this.name = name;
        this.argCount = argCount;
        this.instructions = instructions;
        this.codeOffset = 0;
        this.codeLength = 0;
        this.code = null;
    }

    public JastraFunction(int id, String name, int argCount, int codeOffset, int codeLength, byte[] code){
        this.id = id;
        this.name = name;
        this.argCount = argCount;
        this.instructions = null;
        this.codeOffset = codeOffset;
        this.codeLength = codeLength;
        this.code = code;
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

    public int getCodeOffset() {
        return codeOffset;
    }

    public int getCodeLength() {
        return codeLength;
    }

    public byte[] getCode() {
        return code;
    }
}
