package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class PopStoreRegisterHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        int register = frame.fetchByte()&0xFF;
        frame.registers[register] = frame.operandStack.pop();
    }
}
