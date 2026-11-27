package io.github.inbanithi.jastra.runtime;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.loader.HandlerLoader;
import io.github.inbanithi.jastra.specification.vm.CallStack;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class JastraRuntime implements Runtime {

    private final Handler[] handlers = HandlerLoader.getHandlers();
    @Override
    public void execute(JastraVirtualMachine vm) {
        CallStack callStack = vm.callStack;
        while (!callStack.isEmpty()){
            Frame frame = callStack.peek();
            //System.out.println(frame.module.getCode()[frame.pc]);
            int opCode = frame.fetchByte()&0xFF;
            handlers[opCode].execute(vm, frame);
        }
    }
}
