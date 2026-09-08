package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class LoadHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        int register = frame.fetchByte();
        frame.operandStack.push(frame.registers[register]);
    }
}
