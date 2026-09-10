package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class ReturnHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        int count = frame.fetchByte()&0xFF;
        vm.callStack.pop();
        Frame callerFrame = vm.callStack.peek();
        for(int i=0;i<count;i++){
            callerFrame.operandStack.push(frame.operandStack.pop());
        }
    }
}
