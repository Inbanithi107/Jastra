package io.github.inbanithi.jastra.specification.vm;

import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

public class Frame {
    public Value[] registers;

    public OperandStack operandStack;

    public int pc;

    public Module module;

    public JastraFunction function;

    public Frame(Module module, JastraFunction function, int offset){
        this.registers = new Value[30];
        this.operandStack = new OperandStack();
        this.pc = offset;
        this.module = module;
        this.function = function;
    }

}
