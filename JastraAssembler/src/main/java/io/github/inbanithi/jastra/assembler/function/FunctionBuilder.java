package io.github.inbanithi.jastra.assembler.function;

import io.github.inbanithi.jastra.assembler.block.CodeBlock;
import io.github.inbanithi.jastra.assembler.instruction.AddInstruction;
import io.github.inbanithi.jastra.assembler.instruction.CallInstruction;
import io.github.inbanithi.jastra.assembler.instruction.DivisionInstruction;
import io.github.inbanithi.jastra.assembler.instruction.compare.CompareEqualInstruction;
import io.github.inbanithi.jastra.assembler.instruction.compare.CompareGreaterThenInstruction;
import io.github.inbanithi.jastra.assembler.instruction.compare.CompareGreaterThenOrEqualInstruction;
import io.github.inbanithi.jastra.assembler.instruction.compare.CompareLessThenInstruction;
import io.github.inbanithi.jastra.assembler.instruction.compare.CompareLessThenOrEqualInstruction;
import io.github.inbanithi.jastra.assembler.instruction.compare.CompareNotEqualInstruction;
import io.github.inbanithi.jastra.assembler.instruction.jump.JumpIfNotZeroInstruction;
import io.github.inbanithi.jastra.assembler.instruction.jump.JumpIfZeroInstruction;
import io.github.inbanithi.jastra.assembler.instruction.jump.JumpInstruction;
import io.github.inbanithi.jastra.assembler.instruction.jump.LabelInstruction;
import io.github.inbanithi.jastra.assembler.instruction.LoadFromConstInstruction;
import io.github.inbanithi.jastra.assembler.instruction.LoadInstruction;
import io.github.inbanithi.jastra.assembler.instruction.MultiplyInstruction;
import io.github.inbanithi.jastra.assembler.instruction.PopStoreRegisterInstruction;
import io.github.inbanithi.jastra.assembler.instruction.PrintInstruction;
import io.github.inbanithi.jastra.assembler.instruction.ReturnInstruction;
import io.github.inbanithi.jastra.assembler.instruction.ReturnVoidInstruction;
import io.github.inbanithi.jastra.assembler.instruction.StoreInstruction;
import io.github.inbanithi.jastra.assembler.instruction.SubtractInstruction;
import io.github.inbanithi.jastra.assembler.instruction.operandstack.PopInstruction;
import io.github.inbanithi.jastra.assembler.instruction.operandstack.PushInstruction;
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

    private int labelCounter;

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

    public FunctionBuilder loadFromConst(int id){
        instructions.add(new LoadFromConstInstruction(id));
        return this;
    }

    public FunctionBuilder push(Value value){
        instructions.add(new PushInstruction(value));
        return this;
    }

    public FunctionBuilder pop(){
        instructions.add(new PopInstruction());
        return this;
    }

    public FunctionBuilder add(){
        instructions.add(new AddInstruction());
        return this;
    }

    public FunctionBuilder subtract(){
        instructions.add(new SubtractInstruction());
        return this;
    }

    public FunctionBuilder multiply(){
        instructions.add(new MultiplyInstruction());
        return this;
    }

    public FunctionBuilder divide(){
        instructions.add(new DivisionInstruction());
        return this;
    }

    public FunctionBuilder call(int id, int argCount){
        instructions.add(new CallInstruction(id, argCount));
        return this;
    }

    public FunctionBuilder print(){
        instructions.add(new PrintInstruction());
        return this;
    }

    public FunctionBuilder cmpEq(){
        instructions.add(new CompareEqualInstruction());
        return this;
    }

    public FunctionBuilder cmpNeq(){
        instructions.add(new CompareNotEqualInstruction());
        return this;
    }

    public FunctionBuilder cmpLt(){
        instructions.add(new CompareLessThenInstruction());
        return this;
    }

    public FunctionBuilder cmpLeq(){
        instructions.add(new CompareLessThenOrEqualInstruction());
        return this;
    }

    public FunctionBuilder cmpGt(){
        instructions.add(new CompareGreaterThenInstruction());
        return this;
    }

    public FunctionBuilder cmpGeq(){
        instructions.add(new CompareGreaterThenOrEqualInstruction());
        return this;
    }

    public FunctionBuilder ifElse(CodeBlock trueBlock, CodeBlock falseBlock, String condition){
        String elseLabel = newLabel();
        String endLabel = newLabel();
        instructions.add(condition.equals("JIZ")?new JumpIfZeroInstruction(elseLabel):new JumpIfNotZeroInstruction(elseLabel));
        trueBlock.build(this);
        instructions.add(new JumpInstruction(endLabel));
        instructions.add(new LabelInstruction(elseLabel));
        falseBlock.build(this);
        instructions.add(new LabelInstruction(endLabel));
        return this;
    }

    public FunctionBuilder ifThen(CodeBlock trueBlock, String condition){
        String endLabel = newLabel();
        instructions.add(condition.equals("JIZ")?new JumpIfZeroInstruction(endLabel): new JumpIfNotZeroInstruction(endLabel));
        trueBlock.build(this);
        instructions.add(new LabelInstruction(endLabel));
        return this;
    }

    public FunctionBuilder loop(CodeBlock condition, CodeBlock body){
        String startLabel = newLabel();
        String endLabel = newLabel();
        instructions.add(new LabelInstruction(startLabel));
        condition.build(this);
        instructions.add(new JumpIfZeroInstruction(endLabel));
        body.build(this);
        instructions.add(new JumpInstruction(startLabel));
        instructions.add(new LabelInstruction(endLabel));
        return this;
    }

    public JastraFunction returnVoid(){
        instructions.add(new ReturnVoidInstruction());
        return new JastraFunction(name, argCount, instructions, id);
    }

    public JastraFunction returnValues(int count){
        instructions.add(new ReturnInstruction(count));
        return new JastraFunction(name, argCount, instructions, id);
    }

    private String newLabel(){
        return "__L"+labelCounter++;
    }

}
