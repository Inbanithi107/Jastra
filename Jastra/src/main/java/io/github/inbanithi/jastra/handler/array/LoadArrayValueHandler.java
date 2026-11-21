package io.github.inbanithi.jastra.handler.array;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.specification.vm.Frame;
import io.github.inbanithi.jastra.types.JastraArray;

public class LoadArrayValueHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        JastraArray array = (JastraArray) frame.operandStack.pop().raw();
        int index = frame.operandStack.pop().asInt();
        frame.operandStack.push(array.getValue(index));
    }
}
