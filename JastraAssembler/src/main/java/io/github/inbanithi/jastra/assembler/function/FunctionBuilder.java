package io.github.inbanithi.jastra.assembler.function;

import io.github.inbanithi.jastra.assembler.instruction.AddInstruction;
import io.github.inbanithi.jastra.assembler.instruction.LoadInstruction;
import io.github.inbanithi.jastra.assembler.instruction.PopStoreRegisterInstruction;
import io.github.inbanithi.jastra.assembler.instruction.PrintInstruction;
import io.github.inbanithi.jastra.assembler.instruction.ReturnVoidInstruction;
import io.github.inbanithi.jastra.assembler.instruction.StoreInstruction;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.function.JastraFunction;
import io.github.inbanithi.jastra.specification.instruction.Instruction;

import java.util.ArrayList;
import java.util.List;

public class FunctionBuilder {

    private int id;

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

    public FunctionBuilder id(int index){
        id = index;
        return this;
    }

    public FunctionBuilder store(int register, Value value){
        instructions.add(new StoreInstruction(register, value));
        return this;
    }

    public FunctionBuilder popAndStore(int register){
        instructions.add(new PopStoreRegisterInstruction(register));
        return this;
    }

    public FunctionBuilder load(int register){
        instructions.add(new LoadInstruction(register));
        return this;
    }

    public FunctionBuilder add(){
        instructions.add(new AddInstruction());
        return this;
    }

    public FunctionBuilder print(){
        instructions.add(new PrintInstruction());
        return this;
    }

    public JastraFunction returnVoid(){
        instructions.add(new ReturnVoidInstruction());
        return new JastraFunction(name, argCount, instructions, id);
    }

}
