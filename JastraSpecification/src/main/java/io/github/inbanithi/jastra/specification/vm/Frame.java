package io.github.inbanithi.jastra.specification.vm;

import io.github.inbanithi.jastra.specification.core.Module;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.function.JastraFunction;

public class Frame {
    public Value[] registers;

    public OperandStack operandStack;

    public int pc;

    public io.github.inbanithi.jastra.specification.core.Module module;

    public JastraFunction function;

    public Frame(Module module, JastraFunction function, int offset){
        this.registers = new Value[30];
        this.operandStack = new OperandStack();
        this.pc = offset;
        this.module = module;
        this.function = function;
    }

    public byte fetchByte(){
        return module.getCode()[pc++];
    }

    public int getPc(){
        return this.pc;
    }

    public int fetchInt() {
        return (fetchByte() & 0xFF) << 24 |
                (fetchByte() & 0xFF) << 16 |
                (fetchByte() & 0xFF) << 8  |
                (fetchByte() & 0xFF);
    }

    public float fetchFloat(){
        int bits = (fetchByte() & 0xFF) << 24 |
                (fetchByte() & 0xFF) << 16 |
                (fetchByte() & 0xFF) << 8  |
                (fetchByte() & 0xFF);
        return Float.intBitsToFloat(bits);
    }

}
