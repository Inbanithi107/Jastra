package io.github.inbanithi.jastra.handler;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.specification.core.Module;
import io.github.inbanithi.jastra.specification.function.JastraFunction;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class CallExternalHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        int moduleId = frame.fetchByte()&0xFF;
        int functionId = frame.fetchByte()&0xFF;
        int argCount = frame.fetchByte()&0xFF;
        String name = (String) frame.module.constantPoolTable.getConstant(moduleId).value();
        String functionName = (String) frame.module.constantPoolTable.getConstant(functionId).value();
        Module module = vm.loadModule(name);
        JastraFunction function = module.functionTable.getFunctionByName(functionName);
        Frame callFrame = new Frame(module, function, module.codeSectionOffset+function.getCodeOffset());
        for(int i=argCount-1;i>=0;i--){
            callFrame.registers[i] = frame.operandStack.pop();
        }
        vm.callStack.push(callFrame);
    }
}
