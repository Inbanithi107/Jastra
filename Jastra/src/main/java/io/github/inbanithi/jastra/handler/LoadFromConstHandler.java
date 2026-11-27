package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.core.Constant;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class LoadFromConstHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        int id = frame.fetchByte()&0xFF;
        Constant constant = frame.module.constantPoolTable.getConstant(id);
        Value value = switch (constant.type()){
            case INTEGER -> Value.ofInt((Integer) constant.value());
            case STRING -> Value.ofString((String) constant.value());
            case FLOAT -> Value.ofFloat((Float) constant.value());
            default -> throw new RuntimeException("Invalid Constant to Load");
        };
        frame.operandStack.push(value);
    }
}
