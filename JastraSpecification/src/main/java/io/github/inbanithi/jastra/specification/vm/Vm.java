package io.github.inbanithi.jastra.specification.vm;

import io.github.inbanithi.jastra.specification.core.ControlInstruction;

public abstract class Vm {

    public static ControlInstruction control;

    public OperandStack operandStack;

    public CallStack callStack;

    public Vm(){
        this.operandStack = new OperandStack();
        this.callStack = new CallStack();
    }

    public abstract void execute(String[] args);

}
