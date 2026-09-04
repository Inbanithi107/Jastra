package io.github.inbanithi.jastra.assembler.function;

import io.github.inbanithi.jastra.assembler.core.Value;
import io.github.inbanithi.jastra.assembler.instruction.Instruction;
import io.github.inbanithi.jastra.assembler.instruction.LoadInstruction;
import io.github.inbanithi.jastra.assembler.instruction.StoreInstruction;

import java.util.ArrayList;
import java.util.List;

public class FunctionBuilder {

    private String name;

    private int argCount;

    private List<Instruction> instructions;

    public FunctionBuilder(){
        instructions = new ArrayList<>();
    }

    public FunctionBuilder name(String name){
        this.name = name;
        return this;
    }

    public FunctionBuilder argCount(int count){
        argCount = count;
        return this;
    }

    public FunctionBuilder store(int register, Value value){
        instructions.add(new StoreInstruction(register, value));
        return this;
    }

    public FunctionBuilder load(int register){
        instructions.add(new LoadInstruction(register));
        return this;
    }

}
