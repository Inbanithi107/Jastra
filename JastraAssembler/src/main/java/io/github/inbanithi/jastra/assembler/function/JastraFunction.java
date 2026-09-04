package io.github.inbanithi.jastra.assembler.function;

import io.github.inbanithi.jastra.assembler.instruction.Instruction;

import java.util.List;

public class JastraFunction {

    private String name;

    private int argCount;

    private final List<Instruction> instructions;

    private int offset;

    public JastraFunction(String name, int argCount, List<Instruction> instructions){
        this.name = name;
        this.argCount = argCount;
        this.instructions = instructions;
    }



}
