package io.github.inbanithi.jastra.core;

import io.github.inbanithi.jastra.loader.Program;

public class JastraVirtualMachine {

    private final Program program;

    private int pc;

    private final OperandStack operandStack;

    private final CallStack callStack;

    public JastraVirtualMachine(Program program, OperandStack operandStack, CallStack callStack) {
        this.program = program;
        this.operandStack = operandStack;
        this.callStack = callStack;
        this.pc = 0;
    }

    public JastraVirtualMachine(byte[] code){
        this.program = loadProgram(code);
        this.operandStack = new OperandStack();
        this.callStack = new CallStack();
        this.pc = 0;
    }

    private Program loadProgram(byte[] code){
        return new Program(code);
    }

}
