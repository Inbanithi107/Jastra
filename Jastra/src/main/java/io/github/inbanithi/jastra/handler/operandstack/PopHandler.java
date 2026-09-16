package io.github.inbanithi.jastra.handler.operandstack;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class PopHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        frame.operandStack.pop();
    }
}
