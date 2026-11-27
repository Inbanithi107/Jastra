package io.github.inbanithi.jastra.handler.array;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;
import io.github.inbanithi.jastra.types.JastraArray;

public class StoreArrayValueHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        Value value = frame.operandStack.pop();
        int index = frame.operandStack.pop().asInt();
        JastraArray array = (JastraArray) frame.operandStack.pop().raw();
        array.setValue(index, value);
    }
}
