package io.github.inbanithi.jastra.specification.vm;

import io.github.inbanithi.jastra.specification.core.ControlInstruction;

public abstract class Vm {

    public static ControlInstruction control;

    public final byte[] instructions;

    public OperandStack operandStack;

    public CallStack callStack;

    public Vm(byte[] instructions){
        this.instructions = instructions;
    }

    public abstract void execute(String[] args);

}
