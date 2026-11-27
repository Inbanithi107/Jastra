package io.github.inbanithi.jastra.handler.jump;

import io.github.inbanithi.jastra.core.JastraVirtualMachine;
import io.github.inbanithi.jastra.handler.Handler;
import io.github.inbanithi.jastra.specification.core.Value;
import io.github.inbanithi.jastra.specification.vm.Frame;

public class JumpIfZeroHandler extends Handler {
    @Override
    public void execute(JastraVirtualMachine vm, Frame frame) {
        int offset = frame.fetchInt();
        Value value = frame.operandStack.pop();
        switch (value.type()){
            case INT -> {
                int condition = value.asInt();
                if(condition==0){
                    frame.pc+=offset;
                }
            }
            case BOOLEAN -> {
                boolean condition = (boolean) value.raw();
                if(!condition){
                    frame.pc+=offset;
                }
            }
        }
    }
}
