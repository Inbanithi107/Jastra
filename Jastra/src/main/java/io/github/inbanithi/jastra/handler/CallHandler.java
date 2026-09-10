package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.function.JastraFunction;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class CallHandler extends Handler {

    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {

        int id = frame.fetchByte();
        //System.out.println("functionId: "+id);
        int argCount = frame.fetchByte();
        JastraFunction function = frame.module.functionTable.getFunction(id);
        Frame callFrame = new Frame(frame.module, function, frame.module.codeSectionOffset+ function.getCodeOffset());
        for(int i=argCount-1;i>=0;i--){
            //System.out.println(i+"-"+frame.operandStack.peek().toStringValue());
            callFrame.registers[i] = frame.operandStack.pop();
        }
        vm.callStack.push(callFrame);

    }
}
