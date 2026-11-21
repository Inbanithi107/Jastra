package io.github.inbanithi.jastra.handler.array;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;
import io.github.inbanithi.jastra.types.JastraArray;

public class StoreArrayValueHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        JastraArray array = (JastraArray) frame.operandStack.pop().raw();
        int index = frame.operandStack.pop().asInt();
        Value value = frame.operandStack.pop();
        array.setValue(index, value);
    }
}
