package io.github.inbanithi.jastra.handler.array;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.specification.core.Type;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;
import io.github.inbanithi.jastra.types.JastraArray;

public class NewArrayHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        Type type = Type.from(frame.fetchByte()&0xFF);
        int size = frame.operandStack.pop().asInt();
        assert type != null;
        JastraArray array = new JastraArray(type, size);
        frame.operandStack.push(Value.ofArray(array));
    }
}
